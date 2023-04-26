##############################################################
# Métodos estadísticos para la computación
# Escuela Técnica Superior de Ingeniería Informática.
# Universidad de Málaga.
# Tema 1. Estadística de una variable
# Relación de problemas
##############################################################
library(tidyverse)


##############################################################
# Relación 1. Problema 1
##############################################################
#A) Poblacion: Componentes de ordenador 
#   Individuos: Cada componente de ordenador
#   Muestra: 100 componentes

#B) Caracter: Cualitativo 
#   Tipo: Ordinal
#   Modalida: Funciona o no funciona

#C) Frecuencia absoluta

##############################################################
# Relación 1. Problema 2
##############################################################
#A) Distribucion discreta
litros <- c(6.7, 6.3, 6.5, 6.5, 6.4, 6.6)
fa = as.numeric(table(litros))
fr = fa*(1/length(litros))

df <- data.frame(i=seq(fa), fa, fr)
colnames(df) <- c("i","fa" ,"fr")
df
##############################################################
# Relación 1. Problema 13
##############################################################
varY <- 4
NY <- 82
m4 <- 5648
m1 <- 8
m2 <- varY + m1 ^ 2 
mu3 <- 0
m3 <- mu3 + 3 * m1 * m2 - 2 * m1 ^ 3
mu4 <- m4 - 4 * m1 * m3 + 6 * m1 ^ 2 * m2 - 3 * m1 ^ 4
mu4

# Curtosis
mu4 / varY ^ 2 - 3

##############################################################
# Relación 1. Problema 19
##############################################################
xx <- c(1.17, 1.61, 1.16, 1.38, 3.53, 1.23, 3.76, 1.94, 0.96, 4.75, 0.15, 2.41, 
        0.71, 0.02, 1.59, 0.19, 0.82, 0.47, 2.16, 2.01, 0.92, 0.75, 2.59, 3.07, 
        1.40)
q1 <- quantile(xx, .25)
q3 <- quantile(xx, .75)
rq <- q3 - q1
Ii <- q1 - 1.5 * rq
Is <- q3 + 1.5 * rq
Ei <- q1 - 3 * rq
Es <- q3 + 3 * rq

xx[xx < Ii]
xx[xx > Is]

xx[xx < Ei]
xx[xx > Es]
