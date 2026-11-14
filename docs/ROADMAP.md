# Thorifishing — hoja de ruta

Mod independiente de pesca realista + RPG para Minecraft Java, objetivo 26.3 y NeoForge.

## Hito 0 — validar entorno
- Verificar la distribución NeoForge compatible con Minecraft 26.3 y la versión de Java exigida.
- Generar el proyecto desde el MDK oficial de esa versión, sin inventar dependencias Gradle.
- Configurar GitHub Actions y verificar una compilación limpia antes de distribuir JAR.

## Hito 1 — primera versión jugable
- Persistencia de experiencia de pesca por jugador y cinco niveles.
- Capturas con peso, longitud y rareza.
- Catálogo objetivo: 30 especies distribuidas por bioma.
- Minijuego de tensión del sedal con validación en servidor.
- HUD pixel art con indicadores de tensión y resistencia.
- Cañas, cebos y mejoras iniciales.

## Hitos posteriores
- Tesoros, mapas y reliquias.
- Acuarios y enciclopedia.
- Jefes Leviatán, Abisal y Kraken.
- Torneos multijugador.

## Restricciones
- No depender de EvolutionCraft, Thoridrop ni AlertaEspalda.
- El servidor valida capturas y recompensas.
- Guardar datos por UUID del jugador.
- No publicar JAR hasta comprobar compilación y arranque.
