imgEntGris = imread(fullfile("../Imagenes/coins.png"));
figure(1);
subplot(1,2,1);
imshow(imgEntGris);
%Muestra la informacion del pixel sobre el que tengamos el raton
impixelinfo
%Convertimos la imagen a blanco y negro dependiendo de un umbra opcional.
%Los pixeles con mayor intensidad al umbral se convertiran en blanco
%Los que tengan un valor menor o igual a negro
subplot(1,2,2);
imgBW = im2bw(imgEntGris);
imshow(imgBW);
impixelinfo

figure(2);
I = imread("../Imagenes/acantilado.png");
subplot(1,2,1);
imshow(I);
numColors = 6; %Número de colores
%El comando rgb2ind nos permite convertir una imagen a una imagen indexada
%con una paleta de colores. 
%Nos devuelve una matriz que sera del tamaño de la imagen pero en vez de
%guardar los colores, guardara un indice para cada pixel indicando una
%posicion en cmap
%cmap es una tabla o matriz que guarda los colores en formato rgb
[indexedImage, cmap] = rgb2ind(I, numColors);
subplot(1,2,2);
imshow(indexedImage, cmap);
impixelinfo