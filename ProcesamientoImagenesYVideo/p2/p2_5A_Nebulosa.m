%Cargo la imagen y la muestro
I=imread('../Imagenes/ngc4024l.tif');
subplot(3,1,1), imshow(I);
%Transformo la imagen a valores entre [0,1] y creo la segunda imagen
I1=im2double(I);
I2=I1;
%Creo los bucles para recorrer la imagen pixel a pixel
for n=1:size(I,2),
    for m=1:size(I,1),
        %Multiplico el valor de cada pixel por 255/40
        %Por lo que cualquier valor por encima de 40 sera igual a 1
        I2(m,n)=I1(m,n)*255/40;
        %Si es mayor que 1, lo igualamos a 1
        if I2(m,n)>1,
            I2(m,n)=1;
        end
    end
end
subplot(3,1,2), imshow(I2);

%Con imadjust podemos tambien hacerlo pasandole primero la imagen, como
%segundo argumento el rango de tonos de gris que queremos ajustar y tercero
%el rango al que queremos ajustarlo.
J=imadjust(I,[0 (40/255)],[]);
subplot(3,1,3),imshow(J);