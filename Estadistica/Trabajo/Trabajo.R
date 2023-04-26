library(tidyverse)

datos <- read_csv("./17456.csv", col_types = cols(.default = col_double(), sexo = col_character(), dietaEsp = col_character()))
