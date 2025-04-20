I=imread ('../Imagenes/cell.tif');
%Le aplicamos un filtro para añadirle ruido a una imagen
%Le aplicamos el tipo que le digamos, la media y la varianza del ruido
J=imnoise(I, 'gaussian' ,0,0.01);
figure
subplot(1,3,1)
imshow(J)
%Creamos un filtro average de tamaño 5x5
g=fspecial('average',[5 5])
%Le aplicamos el filtro y convertimos los valores al rango [0,1]
M1=filter2(g,J)/255;
%Creamos otro filtro para realzar los bordes
h=fspecial('unsharp')
M2=abs(filter2(h,M1));
subplot(1,3,2)
imshow(M1); subplot(1,3,3), imshow(M2)