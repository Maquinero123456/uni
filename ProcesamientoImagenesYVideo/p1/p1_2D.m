IR=imread('../Imagenes/arquitectura-en-leeds-uk.jpg');
%A imrotate le pasamos una imagen y los grados por los que queremos rotar
%la imagen. Nos rellena los bordes con negro si no la rotamos por algun
%multiplo de 90
IR=imrotate(J,75);
imshow(IR)
