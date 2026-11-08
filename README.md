# DJ SEBAS AI MUSIC STUDIO PRO 3.0

Versión Android independiente de PRO 2.0. Identificador de paquete propio: `com.produccionessebas.djsebasmusicstudiopro30`. Ambas aplicaciones podrán instalarse juntas.

## Alcance PRO 3.0
- Conserva la cabina local de dos pistas, mezcla, crossfade, mashup, BPM y ecualización de la versión anterior.
- Remix automático de una sola canción: detección aproximada de BPM, ajuste de tempo limitado, perfiles Club/Cumbia/Electro/Limpio, compresión y eco.
- Exportación MP3 a 192 kbps.
- En APK Android, guardar automáticamente en `Music/DJ SEBAS PRO 3.0/` mediante MediaStore; la carpeta será visible desde Archivos y reproductores de música.
- El procesamiento de audio local no necesita clave de OpenAI, tarjeta, suscripción ni conexión.
- Se excluye la incorporación de nuevas funciones de generación musical por IA en esta versión; ACE-Step existente no se amplía.

## Construcción
GitHub Actions ejecuta `.github/workflows/android-pro3.yml` en la rama `pro-3.0`. El artefacto se publica como `DJ-SEBAS-AI-MUSIC-STUDIO-PRO-3.0-APK`.

## Nota de alcance
Esta primera iteración incorpora la exportación MP3 y el remix automático de una pista junto con la base de mezcla actual. El editor multipista completo, la biblioteca indexada y controles de efectos avanzados adicionales requieren implementación y pruebas posteriores antes de anunciarlos como terminados.

DJ SEBAS · Producciones Sebas
