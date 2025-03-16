function franjasBlancoNegro()
    %%Creamos una matriz de ceros y lo casteamos a uint8 para que 
    %%cada pixel tenga un rango de 256 grises
    imgBW = uint8(zeros([256,256]));
    %%Tenemos un bucle que recorra toda la imagen
    for i=1:1:256
        %%Igualamos cada fila completa al valor de i-1 de manera 
        %%que vaya desde 0 hasta 255
        imgBW (i,:)=i-1;
    end
    figure(1);
    subplot(1,2,1);
    imshow(imgBW);
    title('Franjas horizontales');
    %%Una manera de invertirlo es usar el operando " ' " 
    %%que nos devuelve la traspuesta de una matriz
    subplot(1,2,2);
    imshow(imgBW');
    title('Franjas verticales');
end