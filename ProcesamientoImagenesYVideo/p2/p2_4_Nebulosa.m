%Cargo la imagen
I=imread('../Imagenes/ngc4024l.tif');
%Muesto la imagen
subplot(1,2,1), imshow(I);
%Muestro el histograma de la imagen
subplot(1,2,2), imhist(I);