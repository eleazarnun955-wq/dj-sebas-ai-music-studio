# DJ SEBAS AI MUSIC STUDIO PRO 3.0

Aplicación Android independiente de PRO 2.0. Identificador propio: `com.produccionessebas.djsebasmusicstudiopro30`; ambas APK pueden instalarse juntas.

## Funciones incluidas
- Mezcla local de dos pistas: crossfade, mashup superpuesto o reproducción A seguida de B.
- Control de volumen independiente, ecualización de graves/medios/agudos y sincronización BPM aproximada.
- Efectos Echo/Delay y Room, más compresión/masterización seleccionable.
- Remix automático de una canción: perfiles Club, Cumbia, Electro y Limpio; recorte de inicio/final, tempo dentro de un margen limitado, EQ, filtro, compresión y eco.
- Biblioteca local persistente: guardar canciones A/B, cargarlas en cualquiera de las dos pistas y quitarlas.
- Exportación MP3 a 192 kbps. En Android se guarda mediante MediaStore en `Music/DJ SEBAS PRO 3.0/`.
- Logotipo e icono personalizados DJ Sebas.
- El audio se procesa en el dispositivo. No requiere cuenta, clave de OpenAI, Hugging Face ni servicio de generación musical externo.

## Compilación
GitHub Actions compila la rama `pro-3.0` usando `.github/workflows/android-pro3.yml` y publica el artefacto `DJ-SEBAS-AI-MUSIC-STUDIO-PRO-3.0-APK`.

## Alcance y pruebas
La automatización valida sintaxis JavaScript, referencias de controles, genera los iconos Android y compila la APK. La reproducción, exportación y compatibilidad de audio deben verificarse también en un teléfono Android real; una compilación exitosa no sustituye esa prueba de dispositivo.

DJ SEBAS · Producciones Sebas
