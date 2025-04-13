I=imread('../Imagenes/WAFER1.TIF');
%Una matriz kernel para detectar bordes horizontales
g=[1 0 -1;
   1 0 -1; 
   1 0 -1];
%Filtramos usando la matriz
J=filter2(g,I);
%Nos quedamos con los numeros enteros
J=abs(J);
%Le aplicamos un threshold
B=J>0.27*(max(J(:))-min(J(:)));
imshow(B)