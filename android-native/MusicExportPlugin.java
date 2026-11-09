package com.produccionessebas.djsebasmusicstudiopro30v4;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import java.io.OutputStream;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "MusicExport")
public class MusicExportPlugin extends Plugin {
    private Uri streamingItem = null;
    private OutputStream streamingOutput = null;
    private String streamingName = null;

    private Uri createMp3(String fileName) throws Exception {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Audio.Media.DISPLAY_NAME,
            fileName.endsWith(".mp3") ? fileName : fileName + ".mp3");
        values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/mpeg");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.Audio.Media.RELATIVE_PATH,
                Environment.DIRECTORY_MUSIC + "/DJ SEBAS PRO 3.0/");
            values.put(MediaStore.Audio.Media.IS_PENDING, 1);
        }
        Uri collection = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
            ? MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            : MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Uri item = getContext().getContentResolver().insert(collection, values);
        if (item == null) throw new IllegalStateException("Android no pudo crear el archivo MP3.");
        return item;
    }

    @PluginMethod
    public synchronized void beginMp3(PluginCall call) {
        try {
            if (streamingOutput != null) throw new IllegalStateException("Ya hay una exportación en curso.");
            streamingName = call.getString("fileName", "DJ-SEBAS-PRO3-MIX-TERMINADO.mp3");
            streamingItem = createMp3(streamingName);
            streamingOutput = getContext().getContentResolver().openOutputStream(streamingItem, "w");
            if (streamingOutput == null) throw new IllegalStateException("No se pudo abrir el archivo de destino.");
            JSObject result = new JSObject();
            result.put("started", true);
            call.resolve(result);
        } catch (Exception error) {
            cleanupStreaming(true);
            call.reject("No se pudo iniciar la exportación MP3: " + error.getMessage(), error);
        }
    }

    @PluginMethod
    public synchronized void writeMp3Chunk(PluginCall call) {
        try {
            if (streamingOutput == null) throw new IllegalStateException("No hay una exportación MP3 iniciada.");
            String encoded = call.getString("base64");
            if (encoded == null || encoded.isEmpty()) throw new IllegalArgumentException("El bloque de audio llegó vacío.");
            byte[] audio = Base64.decode(encoded, Base64.DEFAULT);
            streamingOutput.write(audio);
            JSObject result = new JSObject();
            result.put("bytesWritten", audio.length);
            call.resolve(result);
        } catch (Exception error) {
            cleanupStreaming(true);
            call.reject("Error escribiendo el MP3: " + error.getMessage(), error);
        }
    }

    @PluginMethod
    public synchronized void finishMp3(PluginCall call) {
        try {
            if (streamingOutput == null || streamingItem == null)
                throw new IllegalStateException("No hay un MP3 pendiente de guardar.");
            streamingOutput.flush();
            streamingOutput.close();
            streamingOutput = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ContentValues ready = new ContentValues();
                ready.put(MediaStore.Audio.Media.IS_PENDING, 0);
                getContext().getContentResolver().update(streamingItem, ready, null, null);
            }
            JSObject result = new JSObject();
            result.put("uri", streamingItem.toString());
            result.put("displayName", streamingName);
            result.put("folder", "Music/DJ SEBAS PRO 3.0");
            streamingItem = null;
            streamingName = null;
            call.resolve(result);
        } catch (Exception error) {
            cleanupStreaming(true);
            call.reject("No se pudo finalizar el MP3: " + error.getMessage(), error);
        }
    }

    @PluginMethod
    public synchronized void cancelMp3(PluginCall call) {
        cleanupStreaming(true);
        JSObject result = new JSObject();
        result.put("cancelled", true);
        call.resolve(result);
    }

    private void cleanupStreaming(boolean deleteFile) {
        if (streamingOutput != null) {
            try { streamingOutput.close(); } catch (Exception ignored) {}
            streamingOutput = null;
        }
        if (deleteFile && streamingItem != null) {
            try { getContext().getContentResolver().delete(streamingItem, null, null); }
            catch (Exception ignored) {}
        }
        streamingItem = null;
        streamingName = null;
    }

    @PluginMethod
    public synchronized void saveMp3(PluginCall call) {
        String encoded = call.getString("base64");
        String fileName = call.getString("fileName", "DJ-SEBAS-PRO3.mp3");
        if (encoded == null || encoded.isEmpty()) {
            call.reject("No se recibió audio MP3.");
            return;
        }
        Uri item = null;
        try {
            byte[] audio = Base64.decode(encoded, Base64.DEFAULT);
            item = createMp3(fileName);
            try (OutputStream output = getContext().getContentResolver().openOutputStream(item, "w")) {
                if (output == null) throw new IllegalStateException("No se pudo abrir el archivo de destino.");
                output.write(audio);
                output.flush();
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ContentValues ready = new ContentValues();
                ready.put(MediaStore.Audio.Media.IS_PENDING, 0);
                getContext().getContentResolver().update(item, ready, null, null);
            }
            JSObject result = new JSObject();
            result.put("uri", item.toString());
            result.put("displayName", fileName);
            result.put("folder", "Music/DJ SEBAS PRO 3.0");
            call.resolve(result);
        } catch (Exception error) {
            if (item != null) {
                try { getContext().getContentResolver().delete(item, null, null); } catch (Exception ignored) {}
            }
            call.reject("No se pudo guardar el MP3: " + error.getMessage(), error);
        }
    }
}
