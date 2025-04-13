I=imread('../Imagenes/escalera2.jpg');
figure
subplot(1,2,1)
imshow(I);
subplot(1,2,2)
%Mostramos el histograma de la imagen
imhist(I);
figure
subplot(1,2,1)
%Aumenta el contraste de una imagen ecualizando el histograma.
%Solo funciona con imagenes de tonos de grises
%Devuelve la imagen y el vector de la transformacion
imshow(histeq(I))
subplot(1,2,2)
imhist(histeq(I))
figure, imshow(I-histeq(I))