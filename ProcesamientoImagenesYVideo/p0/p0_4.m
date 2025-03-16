%%Asi creamos una matriz de ceros del tamaño que queramos
imgBW = false([400,600])
%Tenemos un bucle for que va desde 1 hasta 600 de 50 en 50
for i=1:50:600
    %%Con esto ponenmos las filas de i hasta i+25 y todas las columnas a 1
    imgBW (i:i+25,:)=true;
end
figure(1);
subplot(1,2,1);
imshow(imgBW);
%%Una manera de invertirlo es usar el operando " ' " que nos devuelve la
%%traspuesta de una matriz
subplot(1,2,2);
imshow(imgBW')