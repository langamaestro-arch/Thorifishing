# Prueba de Thorifishing

## Alcance de esta iteración
- Al pescar un pez vanilla con caña se conceden entre 10 y 25 XP.
- Se registra el número de capturas en datos persistentes del jugador.
- Existe una probabilidad del 12 % de añadir una captura especial (pez cristal o carpa dorada).
- Los niveles del 1 al 5 se calculan según umbrales de XP.
- La pesca vanilla no se sustituye; los peces especiales son bonificaciones.

## Cómo probar
1. Compilar en GitHub Actions y descargar el JAR del artefacto verde.
2. Instalarlo en cliente y servidor NeoForge 26.3 compatibles.
3. Pescar bacalao, salmón, pez tropical o pez globo con caña vanilla.
4. Comprobar mensajes de XP y, tras varias capturas, recompensas especiales.
5. Reiniciar y confirmar que las estadísticas persisten; si no lo hacen, habrá que cambiar el mecanismo de guardado.

## Todavía no implementado
- Minijuego interactivo de tensión, HUD, condiciones por bioma y clima, peces con tamaño/peso, cañas y cebos funcionales, guardado explícito de progreso entre muertes, jefes y torneos.

No considerar este prototipo una versión final.
