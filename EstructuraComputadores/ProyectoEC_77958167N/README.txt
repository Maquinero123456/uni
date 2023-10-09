Mi aplicacion es un juego con varias dificultades en el cual los leds se van encendiendo de manera secuencial de ida y vuelta desde el primer led,
 y tienes que pulsar un boton para parar este encendido y apagado en el primer led rojo durante la ida
Las funcionalidades son, los leds se encienden de manera secuencial, un boton varia el tiempo de encendido entre leds, el otro para los leds, el altavoz reproduce una musica
y tras pulsar el boton se encendera un led u otro dependiendo de si has ganado o perdido
Me surgieron dificultades a la hora de reproducir una melodia por el tema de la duracion, cosa que soluciones haciendo que la musica sonara con un bucle polling donde se apagan 
y enciende el altavoz. Luego, para la duracion use una interrupcion fiq el cual avanzaria el array de notas tras una duracion estipulada. No consegui poner musicas distintas para cuando 
ganas y pierdes porque por alguna razon, la duracion de las notas no funciona.
La implementacion final es practicamente igual a la pensada, solo he cambiado el led que se usa para ganar y desgraciadamente no tengo musica para ganar o perder.
