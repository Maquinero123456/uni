library(tidyverse)
library(dplyr)
#1
#Cargamos el csv
datos <- read_csv("./17456.csv", col_types = cols(.default = col_double(), sexo = col_character(), dietaEsp = col_character()))
datos

#2
#Añadimos la columna IMC
datos <- add_column(datos, IMC = datos$peso/(datos$altura^2))
datos

#3
#Eliminamos las columnas con valores na
#Primero vemos que columnas tienen valores na
na <- as.data.frame(
  cbind(
    lapply(
      lapply(datos, is.na), sum)
  )
)
#Ahora seleccionamos las columnas que no tengan valores na
datos <- datos %>% select(-contains(rownames(subset(na, na$V1 != 0))))
datos

#4
#Nos quedamos con las columnas que tengan valores solo numericos
numeric <- as.data.frame(
  cbind(
    lapply(
      datos, is.numeric)
  )
)
numeric_df <- datos %>% select(any_of(rownames(subset(numeric, numeric$V1 == TRUE))))
numeric_df
#Calculamos la media de todas las columnas
media <-
  cbind(
    lapply(
      numeric_df, mean)
  )

media
#Calculas la desviacion tipica
desviacion_tipica_fn <- function(ap) {
  return(sqrt(mean(ap^2) - mean(ap)^2))
}

desviacion_tipica <-
  cbind(
    lapply(
      numeric_df, desviacion_tipica_fn)
  )

desviacion_tipica


#5
regresion_lineal_unidimensional <- function(dt, x, y) {
  rl <- lm(y~x, dt)
  list(coeficiente_de_regresion = coef(rl)[[1]], coeficiente_de_determinacion = summary(rl)$r.squared, regresion_lineal = rl)
}

rlineales <- numeric_df %>% map(regresion_lineal_unidimensional, dt=numeric_df, y=numeric_df$IMC)
#6
#Grafico variables numericas
plot(x=numeric_df$peso, y=numeric_df$IMC, xlab = "Peso", ylab="IMC")
abline(rlineales$peso$regresion_lineal,col="red")

plot(x=numeric_df$altura, y=numeric_df$IMC)
abline(rlineales$altura$regresion_lineal,col="red")

plot(x=numeric_df$edad, y=numeric_df$IMC)
abline(rlineales$edad$regresion_lineal,col="red")

plot(x=numeric_df$tabaco, y=numeric_df$IMC)
abline(rlineales$tabaco$regresion_lineal,col="red")

plot(x=numeric_df$ubes, y=numeric_df$IMC)
abline(rlineales$ubes$regresion_lineal,col="red")

plot(x=numeric_df$carneRoja, y=numeric_df$IMC)
abline(rlineales$carneRoja$regresion_lineal,col="red")

plot(x=numeric_df$verduras, y=numeric_df$IMC)
abline(rlineales$verduras$regresion_lineal,col="red")

plot(x=numeric_df$deporte, y=numeric_df$IMC)
abline(rlineales$deporte$regresion_lineal,col="red")

plot(x=numeric_df$nivEstPad, y=numeric_df$IMC)
abline(rlineales$nivEstPad$regresion_lineal,col="red")

plot(x=numeric_df$nivEstudios, y=numeric_df$IMC)
abline(rlineales$nivEstudios$regresion_lineal,col="red")

plot(x=numeric_df$nivIngresos, y=numeric_df$IMC)
abline(rlineales$nivIngresos$regresion_lineal,col="red")

#Boxplot
boxplot(as.numeric(datos$sexo=="M"), horizontal = TRUE)
stripchart(as.numeric(datos$sexo=="M"), method = "jitter", pch = 19, add = TRUE, col = "blue")


boxplot(as.numeric(datos$dietaEsp=="S"), horizontal = TRUE)
stripchart(as.numeric(datos$dietaEsp=="S"), method = "jitter", pch = 19, add = TRUE, col = "blue")

#7

dividirDataSet <- function(data, p1, p2){
  sesenta <- round(p1*nrow(datos))
  veinte <- round(p2*nrow(datos))
  return(list(entrenamiento = datos[1:sesenta,], test=  datos[sesenta:(sesenta+veinte), ], validacion=datos[-(sesenta:(sesenta+veinte)), ]))
}
dataSet <- dividirDataSet(datos, 0.6, 0.2)
