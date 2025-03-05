I=imread('arquitectura-en-leeds-uk.jpg');
%imresize nos permite reducir la imagen aplicandole una escala y un
%algoritmo
J=imresize(I,0.5,'nearest');
%Asi podemos reducir la imagen que queramos al numero de filas y columnas
%que queramos, incluso cambiando el aspect ratio
J1=imresize(I,[50 50]) %reduce la
%imagen al tamaño 40x30; 30 filas y
%40 columnas
figure
subplot(1,3,1)
imshow(I)
subplot(1,3,2)
imshow(J)
subplot(1,3,3)
imshow(J1)
