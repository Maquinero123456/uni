%Apartado 1
%Coordenadas del foco (a,b)
a=50;
b=200;
%Construcción imagen MxN
M=300; N=300;
for x=1:M
 for y=1:N
    %El 255 lo usamos para indicar que valor en la escala de grises vamos a
    %usar. Siendo 255 blanco y 0 negro.
    %Para indicar esto usamos un valor decimal entre 0 y 1, ambos
    %incluidos, por lo que usando la primera parte del calculo conseguimos que
    %cuanto mas nos acercamos al foco, mas blanco sea en la escala de grises.
    %Y diviendo entre 255 conseguimos que el valor sera decimal entre 0 y 1.
    I(x,y)=(255-sqrt((x-a)^2+(y-b)^2))/255;
 end
end
imshow(I)