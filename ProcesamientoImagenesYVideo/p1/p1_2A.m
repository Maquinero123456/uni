I=imread('../Imagenes/cameraman.tif');
%Cuanto mas se multplica la imagen, cuando mayor sea el valor original,
%mayor sera el crecimiento, aumentando el contraste
figure
subplot(1,3,1)
imshow(I,'InitialMagnification','fit');
subplot(1,3,2)
imshow(I*1.3,'InitialMagnification','fit');
subplot(1,3,3)
imshow(I*1.6,'InitialMagnification','fit');

%Aumenta todos los valores de la imagen de manera igual, haciendo que en
%general sea mas blanca
figure
subplot(1,3,1)
imshow(I,'InitialMagnification','fit');
subplot(1,3,2)
imshow(I+50,'InitialMagnification','fit');
subplot(1,3,3)
imshow(I+100,'InitialMagnification','fit');

