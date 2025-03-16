%%Bacteria
disp("Bacteria:")
%Con imread le podemos pasar la direccion a una imagen para que la cargue
imgEnt = imread('../Imagenes/bacteria.tif');
%whos nos muestra informacion sobre una variable o el entorno
whos("imgEnt")
%imshow nos muestra la imagen en una ventana nueva
imshow(imgEnt);
%Con min y max podemos conseguir los valores maximos y minimos de una lista
%o matriz
disp("Valor minimo: "+min(imgEnt(:)))
disp("Valor maximo: "+max(imgEnt(:)))

%%Flowers
disp("Flowers:")
imgEnt = imread('../Imagenes/flowers.tif');
whos("imgEnt")
%Las imagenes a color reparten la intensidad de sus 3 colores(Este caso
%rgb) en 3 matrices, de esta manera podemos mostrar cada una por separado
imshow([imgEnt(:,:,1),imgEnt(:,:,2),imgEnt(:,:,3)]);
disp("Valor minimo: "+min(imgEnt(:)))
disp("Valor maximo: "+max(imgEnt(:)))
%Con imtool podemos inspeccionar la imagen
imtool('../Imagenes/flowers.tif');