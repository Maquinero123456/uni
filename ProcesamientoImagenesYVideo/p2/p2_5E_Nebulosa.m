% Cargar la imagen y la muestro
I = imread('../Imagenes/ngc4024l.tif');
subplot(1,2,1), imshow(I);
% Convertir a rango [0,1]
I = im2double(I);

% Definir el umbral para detectar los objetos brillantes
% Este valor dependera de la imagen
threshold = 0.7; 

% Creamos la imagen binario, como este caso los colores van a ser 0 o 1.
% Podemos simplemente comparar la matriz contra el valor de threshold y nos
% quedamos con los valores booleanos que al final seran 0 o 1
I_bin = I > threshold;

% Mostrar la imagen binaria resultante
subplot(1,2,2), imshow(I_bin);
