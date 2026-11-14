# Thorifishing — especificación inicial

**Mod ID:** `thorifishing`  
**Nombre:** Thorifishing  
**Loader previsto:** NeoForge  
**Minecraft objetivo:** 26.3 (pendiente de verificar compatibilidad oficial)

## Bucle de juego
1. Elegir bioma, caña y cebo.
2. Lanzar el anzuelo y esperar picada.
3. Mantener la tensión del sedal dentro de la zona segura mientras disminuye la resistencia del pez.
4. Resolver captura o escape desde el servidor.
5. Guardar peso, longitud, especie, experiencia y récord personal.

## Progresión
Cinco rangos: Aprendiz, Pescador, Experto, Maestro y Legendario. Umbrales de XP configurables.

## Arquitectura prevista
- `common`: especies, botín, progreso y reglas de captura.
- `server`: sesiones de pesca, validación, persistencia y recompensas.
- `client`: HUD, efectos y entrada del minijuego.
- `network`: mensajes de sesión, tensión y resultados.
- `data`: especies, biomas, rarezas y tablas de botín.

## Criterios mínimos de aceptación
- Arranque cliente y servidor dedicados sin errores.
- Sin referencias obligatorias a otros mods propios.
- Una captura completa, XP persistente y HUD funcional.
- Sin duplicación de recompensas por paquetes repetidos.
