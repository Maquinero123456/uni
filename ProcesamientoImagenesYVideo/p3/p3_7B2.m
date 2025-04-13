I=imread('../Imagenes/boat.512.tiff');
%Añadimos ruido gaussiano a la imagen
J=imnoise(I,'gaussian',0,0.02);
figure,imshow(J)
a=fspecial('disk');
b=filter2(a,J)/255;
figure,imshow(b)
c=fspecial('laplacian');
d=filter2(c,J)/255;
figure,imshow(d)
e=fspecial('motion');
f=filter2(e,J)/255;
figure,imshow(f)