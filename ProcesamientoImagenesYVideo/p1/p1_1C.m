figure;
subplot(1,2,1)
I=imread('4.2.07.tiff');
imshow(I)
%Nos permite seleccionar una zona con el raton 
J=imcrop;
%seleccionar con el ratón la región de
%interés
subplot(1,2,2)
imshow(J)
%También se puede hacer fijando
%[xmin ymin ancho y alto]
D=imread('4.2.07.tiff');
%A imcrop le pasamos la imagen que queremos recortar
%Despues una lista con los valores x e y donde queremos empezar a recortar
%Seguidos del ancho y alto que queremos que sea el recorte
D1=imcrop(D,[60 40 100 90]);
figure;
subplot(1,2,1);
imshow(D);
subplot(1,2,2);
imshow(D1);