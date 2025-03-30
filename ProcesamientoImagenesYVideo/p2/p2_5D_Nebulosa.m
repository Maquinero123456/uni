% Cargar la imagen
I = imread('../Imagenes/ngc4024l.tif');
I = im2double(I); % Convertir a rango [0,1]
subplot(1,2,1), imshow(I);

% Factor de realce
a = 1.5; 

% Aplicamos T(x) = ax^2
% Usamos .^2 para elevar cada valor de la matriz a 2
I2 = a * I.^2;

subplot(1,2,2), imshow(I2);
