#Navarro Jimena, David
library(tidyverse)
#1
#Cargamos el csv
datos<- read_csv("./17456.csv", 
                 col_types = 
                   cols(.default = col_double(), 
                        sexo = col_factor(), 
                        dietaEsp = col_factor()))
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
na

#Ahora seleccionamos las filas que no tengan valores na
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
numeric
numeric_df <- datos %>% select(any_of(rownames(subset(numeric, numeric$V1 == TRUE))))
numeric_df

#Calculamos la media de todas las columnas
colMeans(numeric_df)

#Calculas la desviacion tipica
#Creamos una funcion que la calcule
desviacion_tipica_fn <- function(ap) {
  return(sqrt(mean(ap^2) - mean(ap)^2))
}

#Aplicamos funcion a todo el dataframe
desviacion_tipica <-
  cbind(
    lapply(
      numeric_df, desviacion_tipica_fn)
  )

desviacion_tipica


#5
#Calculamos la regresion lineal de todas las variables usando  y=IMC
#Creamos funcion para hacerlo de manera mas comoda
regresion_lineal_unidimensional <- function(dt, x, y) {
  rl <- lm(y~x, dt)
  list(coeficiente_de_regresion = coef(rl)[[1]], coeficiente_de_determinacion = summary(rl)$r.squared, regresion_lineal = rl)
}

#Guardamos las regresiones lineales
rlineales <- numeric_df %>% map(regresion_lineal_unidimensional, dt=numeric_df, y=numeric_df$IMC)
rlineales

#6
#Grafico variables numericas

plot(x=numeric_df$peso, y=numeric_df$IMC, xlab = "Peso", ylab="IMC")
abline(rlineales$peso$regresion_lineal,col="red")

plot(x=numeric_df$altura, y=numeric_df$IMC, xlab = "Altura", ylab="IMC")
abline(rlineales$altura$regresion_lineal,col="red")

plot(x=numeric_df$edad, y=numeric_df$IMC, xlab = "Edad", ylab="IMC")
abline(rlineales$edad$regresion_lineal,col="red")

plot(x=numeric_df$tabaco, y=numeric_df$IMC, xlab = "Tabaco", ylab="IMC")
abline(rlineales$tabaco$regresion_lineal,col="red")

plot(x=numeric_df$ubes, y=numeric_df$IMC, xlab = "Ubes", ylab="IMC")
abline(rlineales$ubes$regresion_lineal,col="red")

plot(x=numeric_df$carneRoja, y=numeric_df$IMC, xlab = "Carne Roja", ylab="IMC")
abline(rlineales$carneRoja$regresion_lineal,col="red")

plot(x=numeric_df$verduras, y=numeric_df$IMC, xlab = "Verduras", ylab="IMC")
abline(rlineales$verduras$regresion_lineal,col="red")

plot(x=numeric_df$deporte, y=numeric_df$IMC, xlab = "Deporte", ylab="IMC")
abline(rlineales$deporte$regresion_lineal,col="red")

plot(x=numeric_df$nivEstPad, y=numeric_df$IMC, xlab = "nivEstPad", ylab="IMC")
abline(rlineales$nivEstPad$regresion_lineal,col="red")

plot(x=numeric_df$nivEstudios, y=numeric_df$IMC, xlab = "nivEstudios", ylab="IMC")
abline(rlineales$nivEstudios$regresion_lineal,col="red")

plot(x=numeric_df$nivIngresos, y=numeric_df$IMC, xlab = "nivIngresos", ylab="IMC")
abline(rlineales$nivIngresos$regresion_lineal,col="red")

#Boxplot
#Grafico variables no numericas
boxplot(as.numeric(datos$sexo=="M"), horizontal = TRUE, xlab = "Sexo", ylab="IMC")
stripchart(as.numeric(datos$sexo=="M"), method = "jitter", pch = 19, add = TRUE, col = "blue")


boxplot(as.numeric(datos$dietaEsp=="S"), horizontal = TRUE, xlab = "dietaEsp", ylab="IMC")
stripchart(as.numeric(datos$dietaEsp=="S"), method = "jitter", pch = 19, add = TRUE, col = "blue")

#7
#Dividimos los datos en 3 partes: Entrenamiento, test y validacion
#Creamos la funcion para que tome samples aleatorias del dataframe
dividirDataSet <- function(data, p1, p2){
  rDf    <- 1:nrow(data)
  rTrain <- sample(rDf, p1 * length(rDf))
  rTemp  <- setdiff(rDf, rTrain)
  rTest  <- sample(rTemp, p2 * length(rTemp))
  rValid <- setdiff(rTemp, rTest)
  return(list(entrenamiento = data[rTrain, ], test=  data[rTest, ], validacion=data[rValid, ]))
}

#Guardamos los dataset
dataSet <- dividirDataSet(datos[-c(1,2)], 0.6, 0.2)
dataSet

#8
#Calculamos la regresion lineal de todas las varirables con IMC para el conjunto de entrenamiento
modTrain <- dataSet$entrenamiento %>% map(regresion_lineal_unidimensional, dt=dataSet$entrenamiento, y=dataSet$entrenamiento$IMC)

#Creamos una funcion que calcule r2 ajustado
R2ajustado<-function(df,mod, y){
  MSE  <- mean((y - predict.lm(mod[["regresion_lineal"]], df)) ^ 2)
  varY <- mean(y ^ 2) - mean(y) ^ 2
  print(MSE/varY)
  R2   <- 1 - (MSE / varY)
  aR2  <- 1 - (1- R2) * (nrow(df) - 1) / (nrow(df) - mod$regresion_lineal$rank)
  aR2
}
#Aplicamos la funcion de r2 ajustado a todo el modelo creado con el conjunto de entrenamiento
#Le pasamos como parametros todas las variables excepto IMC ya que no tiene sentiendo, lo estamos entrenando para predecir IMC
bestFit <-modTrain[-which(names(modTrain) == "IMC")]%>% map_dbl(R2ajustado, df=dataSet$test, y=dataSet$test$IMC)
bestFit

#Mostramos que variable es la que mejor predice IMC
which.max(bestFit)[1]

#9
#Creamos una funcion para hacer calculos de regresiones lineales multiples
regresion_lineal_multiple <- function(dt, x, y) {
  rl<-lm(str_c(y, "~", str_c(x, collapse="+")), dt)
  list(coeficiente_de_regresion = coef(rl)[[1]], coeficiente_de_determinacion = summary(rl)$r.squared, regresion_lineal = rl)
}

#Creamos una funcion que haga r2 ajustado directamente
calcModR2 <- function(dfTrain, dfTest, varPre,y, x) {
  mod <- regresion_lineal_multiple(dfTrain, y, x)
  R2ajustado(dfTest, mod, varPre)
}

#Creamos una funcion que calcule que variable es mejor
encontrarMejorAjuste <- function(dfTrain, dfTest, varPos) {
  bestVars <- varPos[1]
  aR2      <- 0
  #Repetimos en bucle
  #Añadimos cada vez una variable nueva para calcular R2 hasta que encontremos la mejor variable
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
  #Guardamos en una lista
  mod <- regresion_lineal_multiple(dt = dfTrain, y="IMC", x=bestVars)
  
  list(vars=bestVars, mod=mod)
}

#Vemos que variable predice mejor IMC
bestMod1 <- encontrarMejorAjuste(dataSet$entrenamiento, dataSet$test, names(datos[-c(1,2,14)]))
bestMod1$vars

#10
#Comprobamos como de bien lo hace nuestro modelo
R2ajustado(df=dataSet$validacion, mod=bestMod1$mod, y=dataSet$validacion$IMC)

#11
#Cargamos el archivo de evaluacion
eval <- read_csv("./eval.csv", col_types = cols(.default = col_double(), sexo = col_factor(), dietaEsp = col_factor()))
#Predecimos la columna IMC con nuestro modelo
eval$IMC <- predict.lm(bestMod1$mod$regresion_lineal, eval)
#Y calculamos la columna peso con los datos predichos
eval <- add_column(eval, peso = eval$IMC*(eval$altura^2))
eval
#Guardamos el dataframe en evalX.csv
write.csv(eval, "./evalX.csv", row.names=TRUE)
