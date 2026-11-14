# Thorifishing — prueba del minijuego (prototipo)

## Mecánica implementada
Al recoger la caña tras una picada de pez vanilla, se retiene temporalmente el botín y comienza un encuentro. El jugador debe **pulsar repetidamente clic derecho con la caña** para reducir la resistencia. Cada pulsación aumenta la tensión; dejar de pulsar permite que baje. Si llega a 100, el pez escapa. Si la resistencia llega a cero, el jugador recibe el botín y XP.

## HUD
La barra de acción (encima de la barra rápida) muestra tensión, resistencia y dificultad (común, raro, legendario). Los mensajes de XP repetitivos dejan de ocupar el chat.

## Prueba
1. Esperar una picada y recoger la caña.
2. Pulsar clic derecho con la caña, alternando pulsaciones y descansos.
3. Verificar que el pez no se entrega hasta ganar.
4. Provocar una fuga aumentando la tensión y verificar que no se entrega botín.
5. Verificar pesca repetida, subida de nivel y comportamiento al morir.

## Limitaciones conocidas
- Este prototipo inicia el minijuego **al recoger tras la picada**, no en el instante inicial de la picada.
- El control es mediante **pulsaciones**, no mantener pulsado; no existe todavía un paquete de red para registrar el estado continuo del botón.
- El HUD es una barra de acción textual, no un panel gráfico con texturas.
- El progreso sigue usando datos persistentes del jugador y debe comprobarse en cambios de dimensión y tras morir.
- Falta compilar y probar esta iteración dentro de Minecraft.
