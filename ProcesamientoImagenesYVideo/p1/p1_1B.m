%imagen de tamaño 30x30
%Creamos una matriz de solo unos del tamaño especificado(Imagen en blanco)
I=ones(100,100);
%Añadimos 0 a una region para mostrar una figura
%En este caso, para las filas entre 40 y 60, y las columnas 20 y 80
I(40:60,20:80)=0;
%Con fit hacemos que la imagen se adapte a tamaño de la ventana
imshow(I,'InitialMagnification','fit')
