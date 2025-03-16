function imgRotada = girar90(img)
    [x, y] = size(img);
    %Al girar la imagen 90 grados, dimensiones de la imagen se invierten
    %Por lo que creamos una matriz de ceros del tamaño inverso de la matriz
    %original
    imgRotada = uint8(zeros(y, x)); 

    for i = 1:x
        for j = 1:y
            %Recorremos la imagen original colocando en la nueva matrz pero
            %en la nueva matriz invertimos los indices, de esta manera
            %vamos colocando los pixeles de la imagen original que irian,
            %por ejemplo, en la primera fila en la ultima columna de la
            %imagen rotada
            imgRotada(j, x-i+1) = img(i, j);
        end
    end
end
