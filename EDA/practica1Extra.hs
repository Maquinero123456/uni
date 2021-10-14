import Test.QuickCheck

esPrimo :: (Integral a) => a -> Bool
esPrimo x | x > 0 = esPrimo' x 2
          | otherwise = error"Argumento negativo o cero"

esPrimo' :: (Integral a) => a -> a -> Bool
esPrimo' x y | x==y = True
             | mod x y == 0 = False
             | otherwise = esPrimo' x (y+1)

libreDeCuadrados :: Integer -> Bool
libreDeCuadrados x | x > 0 = libreDeCuadrados' x 2 1
                   | otherwise = error"Argumento negativo o cero"

libreDeCuadrados' :: Integer -> Integer -> Integer -> Bool
libreDeCuadrados' 1 y z = True
libreDeCuadrados' x y z | not (esPrimo y)  = libreDeCuadrados' x (y+1) z
                        | mod x y == 0 && y == z = False
                        | mod x y == 0 && y /= z = libreDeCuadrados' (div x y) y y
                        | otherwise = libreDeCuadrados' x (y+1) z

sumaDigitos :: Integer -> Integer
sumaDigitos 0 = 0
sumaDigitos x | x>0= mod x 10 + sumaDigitos (div x 10)
              | otherwise = error "Argumento no natural"

harshad :: Integer -> Bool
harshad x | x<=0 = error "Argumento no valido"
          | mod x (sumaDigitos x) == 0 = True
          | otherwise= False

multipleHarshad :: Integer -> Bool
multipleHarshad 1 = True
multipleHarshad x | x<=0 = error "Argumento no valido"
                  | harshad x = multipleHarshad (div x (sumaDigitos x))
                  | otherwise= False

vecesHarshad :: Integer -> Integer
vecesHarshad 1 = 1
vecesHarshad x | x<=0 = error "Argumento no valido"
               | harshad x && sumaDigitos x == 1 = 1
               | harshad x = 1 + vecesHarshad (div x (sumaDigitos x))
               | otherwise= 0

prop_Boem_Harshad_OK x = x>0 ==> vecesHarshad (1008*10^x) == veces
    where
        veces = x+2

cerosDe :: Integer -> Integer
cerosDe 0 = 1
cerosDe x | mod x 10 == 0 = 1 + cerosDe (div x 10)
          | otherwise = 0

prop_cerosDe_Ok n m = m > 0 && m < 1000 ==> cerosDe construido == m
    where construido = n*10^m

fib :: Integer -> Integer
fib 0 = 0
fib 1 = 1
fib x | x<0 = error "Argumento negativo"
      | otherwise = fib(x-1)+fib(x-2)

llamadasFib :: Integer -> Integer
llamadasFib 0 = 1
llamadasFib 1 = 1
llamadasFib x | x<0 = error "Argumento negativo"
              | otherwise= 1+llamadasFib(x-1)+llamadasFib(x-2)

fib' :: Integer -> Integer
fib' n = fibAc n 0 1
    where
        fibAc n x y | n == 1 = y 
                    | otherwise = fibAc (n-1) (y) (x+y)

p_fib x = x>0 && x<=30 ==> fib x == fib' x

binet :: Integer -> Integer
binet 0 = 0
binet 1 = 1
binet x | x < 0 = error "Argumento negativo"
binet x = round ((razon^x-(1-razon)^2)/(sqrt 5))
    where
        razon = (1+sqrt 5)/2

    
p_binet x = x>0 ==> binet x == fib' x