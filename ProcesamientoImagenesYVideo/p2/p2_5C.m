% Cargar la imagen
I = imread('../Imagenes/coins.png');
I = im2double(I); % Convertir a rango [0,1]
subplot(1,2,1), imshow(I);

c1 = 0.0; % Límite inferior
c2 = 0.4; % Límite superior
a = 2; % Factor de aumento de contraste

% Inicializar la imagen transformada
I2 = I;

for m = 1:size(I,1)
    for n = 1:size(I,2)
        %Si el valor del pixel esta entre [c1, c2] estiramos el valor
        %Si es igual a c1 el valor sera 0 y si (c1+c2)/2 sera 1
        if I(m,n) >= c1 && I(m,n) <= c2
            I2(m,n) = a * (I(m,n) - c1) / (c2 - c1);
        end
    end
end

subplot(1,2,2),  imshow(I2);
