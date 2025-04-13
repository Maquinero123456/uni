I=imread('../Imagenes/boat.512.tiff');
%Le añadimos ruido a la imagen
%Le tenemos que decir que metodo queremos y cuanto ruido queremos añadir
I=imnoise(I,'salt & pepper',0.2);
figure, imshow(I)
%Ecualizamos la imagen
[J,T]=histeq(I);
figure
subplot(1,2,1)
imshow(J)
subplot(1,2,2)
plot((0:255)/255,T);
%Le aplicamos un filtro de mediana de tamaño [n x n]
%Cuanto mayor sea el tamaño, mas difuminada estara la imagen
M=medfilt2(J,[5 5]);
figure
imshow(M)
%Creamos un filtro del tipo que le digamos de tamaño [n x n]
%Cuanto mayor sea el tamaño, mas difuminada estara la imagen
g=fspecial('average',[9 9]);
%Le aplicamos el filtro y hacemos que los valores de la imagen esten en el
%rango [0,1]
M=filter2(g,J)/255;
figure
imshow(M)