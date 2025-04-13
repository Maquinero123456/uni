I=imread('../Imagenes/saturno.tif');
%Crea un filtro del tipo que le digamos
h=fspecial('unsharp');
%Usamos el filtro y nos quedamos con los valores absolutos del resultado
J=abs(filter2(h,I));
%Convertimos los valores a un rango [0,1]
J1=J/255; % /max(J(:));
imshow(J1)