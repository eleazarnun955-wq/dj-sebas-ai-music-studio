# DJ SEBAS AI MUSIC STUDIO

Aplicación web instalable (PWA) para Android y escritorio. La mezcla de archivos locales se procesa en el navegador; no requiere cuenta, clave de OpenAI, Hugging Face, tarjeta ni suscripción.

## Funciones actuales
- Usar el generador oficial ACE-Step 1.5 integrado en la pantalla mediante un marco web (la generación se ejecuta en Hugging Face y está sujeta a su cola y límites).
- Preparar prompts de canción con género, ambiente, voz, duración y letra.
- Importar dos archivos de audio desde el dispositivo.
- Reproducir/escuchar los originales.
- Crear transición crossfade, superposición tipo mashup o reproducción A seguida de B.
- Descargar el audio generado en ACE-Step y cargarlo en el mezclador local para producir y exportar el mashup.
- Ajustar volumen de ambas pistas.
- Escuchar el resultado y exportarlo como WAV.
- Interfaz instalable como PWA y caché de la interfaz para uso sin conexión después de la primera visita.

## Límites conocidos
La versión actual no hace análisis automático de BPM/tonalidad, beatmatching, separación de voces/instrumentos ni composición generativa con IA. La exportación WAV usa memoria del dispositivo; se recomienda probar primero con canciones cortas.

## Instalar
Abre la web publicada en Chrome Android, menú ⋮ → Instalar aplicación (o Agregar a pantalla principal).

## Privacidad
Los archivos se leen localmente con Web Audio API y no se envían a un servidor.

DJ SEBAS · Producciones Sebas
