package com.produccionessebas.djsebasmusicstudiopro30v4;

import android.os.Bundle;
import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        registerPlugin(MusicExportPlugin.class);
        super.onCreate(savedInstanceState);
    }
}