I=imread('../Imagenes/rice.tif');
%Resalta los bordes verticales
gv=[-1 0 1;
    -1 1 1; 
    -1 0 1];
%Resalta los bordes horizontales
gh=[ 1  1  1;
     0  1  0; 
    -1 -1 -1];
Jv=abs(filter2(gv,I))/255;
Jh=abs(filter2(gh,I))/255;
imshow(Jv); figure, imshow(Jh)