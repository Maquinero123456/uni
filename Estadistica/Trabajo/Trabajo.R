library(tidyverse)
library(dplyr)
library(modelr)
#1
#Cargamos el csv
datos <- read_csv("./17456.csv", col_types = cols(.default = col_double(), sexo = col_factor(), dietaEsp = col_factor()))
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
colMeans(numeric_df)
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
  rDf    <- 1:nrow(data)
  rTrain <- sample(rDf, p1 * length(rDf))
  rTemp  <- setdiff(rDf, rTrain)
  rTest  <- sample(rTemp, p2 * length(rTemp))
  rValid <- setdiff(rTemp, rTest)
  return(list(entrenamiento = data[rTrain, ], test=  data[rTest, ], validacion=data[rValid, ]))
}
dataSet <- dividirDataSet(datos, 0.6, 0.2)

#8

modTrain <- dataSet$entrenamiento %>% map(regresion_lineal_unidimensional, dt=dataSet$entrenamiento, y=dataSet$entrenamiento$IMC)

R2ajustado<-function(df,mod, y){
  MSE  <- mean((y - predict.lm(mod[["regresion_lineal"]], df)) ^ 2)
  varY <- mean(y ^ 2) - mean(y) ^ 2
  R2   <- 1 - MSE / varY
  aR2  <- -(1 - (1- R2) * (nrow(df) - 1) / (nrow(df) - mod$regresion_lineal$rank))
  aR2
}

bestFit <-modTrain[-which(names(modTrain) == "IMC")]%>% map_dbl(R2ajustado, df=dataSet$test, y=dataSet$test$IMC)
bestFit
which.max(bestFit)[1]

#9

regresion_lineal_multiple <- function(dt, x, y) {
  rl<-lm(str_c(y, "~", str_c(x, collapse="+")), dt)
  list(coeficiente_de_regresion = coef(rl)[[1]], coeficiente_de_determinacion = summary(rl)$r.squared, regresion_lineal = rl)
}

calcModR2 <- function(dfTrain, dfTest, varPre,y, x) {
  mod <- regresion_lineal_multiple(dfTrain, y, x)
  R2ajustado(dfTest, mod, varPre)
}


encontrarMejorAjuste <- function(dfTrain, dfTest, varPos) {
  bestVars <- character(0)
  aR2      <- 0
  
  repeat {
    aR2v <- map_dbl(varPos, ~calcModR2(dfTrain, dfTest, dfTest$IMC,"IMC", c(bestVars, .)))
    i    <- which.max(aR2v)
    aR2M <- aR2v[i]
    if (aR2M <= aR2) break
    
    cat(sprintf("%1.4f %s\n", aR2M, varPos[i]))
    aR2 <- aR2M
    bestVars <- c(bestVars, varPos[i])
    varPos   <- varPos[-i]
  }
  
  mod <- regresion_lineal_multiple(dfTrain, "IMC", bestVars)
  
  list(vars=bestVars, mod=mod)
}

bestMod1 <- encontrarMejorAjuste(dataSet$entrenamiento, dataSet$test, names(datos[-14]))
R2ajustado(df=dataSet$validacion, mod=bestMod1$mod, y=dataSet$validacion$IMC)

#11
eval <- read_csv("./eval.csv", col_types = cols(.default = col_double(), sexo = col_factor(), dietaEsp = col_factor()))
eval <- add_column(eval, IMC=0)
eval <- add_column(eval, peso=0)
eval$IMC <- predict(bestMod1$mod$regresion_lineal, newdata = eval)
eval$peso <- predict(bestMod1$mod$regresion_lineal, newdata = eval)
