# maquina-arcade

Un proyecto que integra los problemas de las Torres de Hanoi, el problema de las N Reinas y el del Recorrido del Caballo
en un entorno visualmente similar a una máquina arcade.

~~Tiene persistencia y guarda las partidas que se hacen en cada uno.~~

Los "juegos" son interactivos:
* Puedes intentar resolver las torres de hanoi por tu cuenta y ver cuantos movimientos haces.
* Puedes intentar poner N reinas (generalización del problema de las 8 reinas) en un tablero de ajedrez sin que se coman entre ellas.
* Puedes intentar hacer que un caballo recorra todas las casillas de un tablero NxN (comúnmente 8x8) sin que pase dos veces por la misma casilla.

...O puedes dejar que el ordenador los resuelva por ti.


---

# Breve comentario sobre el desarrollo

Aunque al principio no supuso mucho problema el desarrollar las soluciones iniciales (el que simplemente se resuelvan los 3 problemas presentados de forma automática).
El traspaso a Vaadin me ha costado bastante, me ha costado acostumbrarme a como se hacen las cosas.

Además, he tenido problemas al intentar montar la persistencia con Hibernate debido al uso de Genéricos en el proyecto.

Por otro lado, me costó empezar el proyecto, porque no veía la manera de integrar de forma natural ningún patrón estructural, lo que me hizo quedarme estancado.
(o por lo menos no vi ninguna forma de hacerlo sin que fuera forzado como se pide).

Esto, juntado a problemas personales y una mala gestión del tiempo en general ha llevado a lo que es el proyecto ahora.

---

# Enlace al repo

[``https://github.com/KeyKoder/maquina-arcade.git``](https://github.com/KeyKoder/maquina-arcade.git)