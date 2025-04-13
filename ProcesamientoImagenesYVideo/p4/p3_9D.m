I=imread('../Imagenes/spine.tif');
I=histeq(I);
imshow(I)
B=edge(I,'canny');
figure, imshow(B)
R=double(I)/255+0.1*double(B);
figure, imshow(R)