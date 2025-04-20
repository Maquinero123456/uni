I=imread('../Imagenes/pimientos1.jpg');
figure
imshow(I)
%Devuelve una imagen binaria con los bordes. Tiene como argumentos la
%imagen, el metodo a usar, threshold y direccion.
B1=edge(I,'zerocross');
B2=edge(I,'Roberts');
B3=edge(I,'canny');
figure
imshow(B1)
figure
imshow(B2)
figure
imshow(B3)