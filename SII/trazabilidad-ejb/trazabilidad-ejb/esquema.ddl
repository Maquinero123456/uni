CREATE TABLE INGREDIENTE (NOMBRE VARCHAR NOT NULL, PRIMARY KEY (NOMBRE))
CREATE TABLE LOTE (CODIGO VARCHAR NOT NULL, CANTIDAD NUMERIC(10,2), FECHAFABRICACION DATE, PRODUCTO_NOMBRE VARCHAR NOT NULL, PRIMARY KEY (CODIGO, PRODUCTO_NOMBRE))
CREATE TABLE PRODUCTO (NOMBRE VARCHAR NOT NULL, PRIMARY KEY (NOMBRE))
CREATE TABLE LoteIngredientes (CODIGO VARCHAR, PRODUCTO_NOMBRE VARCHAR, lote VARCHAR, ingrediente VARCHAR)
CREATE TABLE PRODUCTO_INGREDIENTE (Producto_NOMBRE VARCHAR NOT NULL, ingredientes_NOMBRE VARCHAR NOT NULL, PRIMARY KEY (Producto_NOMBRE, ingredientes_NOMBRE))
ALTER TABLE LOTE ADD CONSTRAINT FK_LOTE_PRODUCTO_NOMBRE FOREIGN KEY (PRODUCTO_NOMBRE) REFERENCES PRODUCTO (NOMBRE)
ALTER TABLE LoteIngredientes ADD CONSTRAINT FK_LoteIngredientes_CODIGO FOREIGN KEY (CODIGO, PRODUCTO_NOMBRE) REFERENCES LOTE (CODIGO, PRODUCTO_NOMBRE)
ALTER TABLE LoteIngredientes ADD CONSTRAINT FK_LoteIngredientes_ingrediente FOREIGN KEY (ingrediente) REFERENCES INGREDIENTE (NOMBRE)
ALTER TABLE PRODUCTO_INGREDIENTE ADD CONSTRAINT FK_PRODUCTO_INGREDIENTE_Producto_NOMBRE FOREIGN KEY (Producto_NOMBRE) REFERENCES PRODUCTO (NOMBRE)
ALTER TABLE PRODUCTO_INGREDIENTE ADD CONSTRAINT FK_PRODUCTO_INGREDIENTE_ingredientes_NOMBRE FOREIGN KEY (ingredientes_NOMBRE) REFERENCES INGREDIENTE (NOMBRE)