I=imread('../Imagenes/boat.512.tiff');
%Añadimos ruido gaussiano a la imagen
J=imnoise(I,'gaussian',0,0.02);
figure,imshow(J)
%Creamos un filtro de media
g=fspecial('disk',[5 5]);
%Le aplicamos el filtro a la imagen y hacemos que los valores esten en el
%rango [0, 1]
M=filter2(g,J)/255;
figure,imshow(M)
%Le aplicamos a la imagen un filtro de mediana
M1=medfilt2(J,[5 5]);
figure, imshow(M1)