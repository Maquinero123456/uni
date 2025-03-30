%Cargo la imagen y la muestro
I=imread('../Imagenes/coins.png');
subplot(1,2,1), imshow(I);
%Transformo la imagen a rango [0,1]
I1=im2double(I);
%Factor de reduccion del contraste
a=0.1 ;
%Punto donde cambiamos la transformacion
c=80/255 ;
for n=1:size(I,2)
    for m=1:size(I,1)
        %Si el pixel tiene una intensidad menor a c
        %Lo multiplicamos por a reduciendo el contraste
        if I1(m,n)<c
            I2(m,n)=I1(m,n)*a;
        %En otro caso, aplicamos esa formula aumentando el contraste
        else
            I2(m,n)=a*c+(I1(m,n)-c)*(1-a*c)/(1-c);
        end
    end
end
subplot(1,2,2), imshow(I2);