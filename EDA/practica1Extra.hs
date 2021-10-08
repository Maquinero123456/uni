import Test.QuickCheck
import GHC.Integer (Integer)

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