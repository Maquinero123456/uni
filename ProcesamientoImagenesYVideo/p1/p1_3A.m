I=imread('../Imagenes/Fig9.11(a).jpg');
imshow(I)
%Con ginput puedo seleccionar hacer click en una imagen y me devuelve la
%posicion x e y
[c,r] = ginput(1);
%bwselect nos permite quedarnos con la parte de la imagen que se solape con
%los pixeles localizacos en c y r. Le pasamos primero la imagen, luego la
%posicion x del pixel y ultimo la posicion Y. Estos pueden ser tambien
%arrays si queremos seleccionar varias partes. El ultimo valor opcional le
%podemos decir que conectividad queremos usar, 4 u 8
I0=bwselect(I,c,r,8);
%Usamos find para encontrar todos los 1 en I0
[rows, cols] = find(I0);
%Las esquinas seran los minimos y maximos de filas y columnas
xmin = min(cols); 
xmax = max(cols); 
ymin = min(rows); 
ymax = max(rows); 
esquinas = [[xmin, ymin], [xmax, ymin], [xmax,ymax], [xmin, ymax]]

figure
subplot(1,3,1)
imshow(I)
subplot(1,3,2)
imshow(I0)
subplot(1,3,3)
imshow(I0)
rectangle('Position', [xmin, ymin, xmax - xmin, ymax - ymin], 'EdgeColor', 'r', 'LineWidth', 1);