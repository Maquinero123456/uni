I=imread('../Imagenes/saturno.tif');
figure
subplot(1,2,1)
imshow(I);
subplot(1,2,2)
imhist(I);
figure
%subplot(1,3,1)
%imshow(histeq(I))
%subplot(1,3,2)
%imhist(histeq(I))
%Ecualizamos la imagen y obtenemos la imagen y el vector de la ecualizacion
%[J,T]=adapthisteq(I);
%subplot(1,3,3)
%Ploteamos el vector para ver como ha afectado a la imagen
%plot((0:255)/255,T);
subplot(1,2,1)
imshow(adapthisteq(I, ClipLimit= 0.1))
subplot(1,2,2)
imhist(adapthisteq(I, ClipLimit= 0.1))