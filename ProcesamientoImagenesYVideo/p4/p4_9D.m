I=imread('../Imagenes/cell.tif');
%Ecualizamos el histograma
I=histeq(I);
figure, subplot(1,3,1)
imshow(I)
%Detectamos bordes con canny
B=edge(I,'canny');
subplot(1,3,2), imshow(B)
%Realzamos los bordes sumandole una pequeña cantidad de intesidad
R=double(I)/255+0.1*double(B);
subplot(1,3,3), imshow(R)