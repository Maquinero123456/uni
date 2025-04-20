I=imread('../Imagenes/BUG.TIF');
figure 
subplot(1,2,1)
imshow(I)
I=histeq(I);
%Le aplicamos un filtro para resaltar los bordes
h=fspecial('unsharp');
J=abs(filter2(h,I));
%Le aplicamos una matriz para resaltar los bordes horizontales
gh=[ 1  1  1;
     0  0  0; 
    -1 -1 -1];
B=abs(filter2(gh,J))/255;
subplot(1,2,2)
imshow(R)


