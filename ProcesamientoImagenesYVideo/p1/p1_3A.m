I=imread('Fig9.11(a).jpg');
imshow(I)
%Con ginput puedo seleccionar hacer click en una imagen y me devuelve la
%posicion x e y
c = ginput(1);
r = ginput(1);
%bwselect nos permite quedarnos con la parte de la imagen que se solape con
%los pixeles localizacos en c y r. Le pasamos primero la imagen, luego la
%posicion x del pixel y ultimo la posicion Y. Estos pueden ser tambien
%arrays si queremos seleccionar varias partes. El ultimo valor opcional le
%podemos decir que conectividad queremos usar, 4 u 8
I0=bwselect(I,[c(1), r(1)],[c(2), r(2)],8);
figure
subplot(1,2,1)
imshow(I)
subplot(1,2,2)
imshow(I0)
