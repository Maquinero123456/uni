function umbralizacion(nombreFich)
    imgEntGris = imread(nombreFich);

    figure(1);
    subplot(1,2,1);
    imshow(imgEntGris);
    
    subplot(1,2,2);
    imgBW = im2bw(imgEntGris);
    imshow(imgBW);
end