-------------------------------------------------------------------------------
-- Estructuras de Datos. 2º Curso. ETSI Informática. UMA
--
-- PRACTICA 2ª (Características de la Programación Funcional)
--
-- (completa y sustituye los siguientes datos)
-- Titulación: Grado en Ingeniería Informática
-- Alumno: Navarro Jimena, David
-- Fecha de entrega:  DIA | MES | AÑO
--
-- Ejercicios resueltos de la Relación : ..........
--
-------------------------------------------------------------------------------
module Practica2 where

import Test.QuickCheck
import Data.Char


-------------------------------------------------------------------------------
-- Ejercicio 4
-------------------------------------------------------------------------------
distintos :: Eq a => [a] -> Bool
distintos [] = True
distintos [_] = True
distintos (x:xs) | length xs == length (filter (/=x) xs) = distintos xs
                 | otherwise = False

-------------------------------------------------------------------------------
-- Ejercicio 11
-------------------------------------------------------------------------------
take' :: Int -> [a] -> [a]
take' n xs = [ x | (p,x) <- zip [0..(n-1)] xs]

drop' :: Int -> [a] -> [a]
drop' n xs = [ x | (p,x) <- zip [0..] xs, p>=n]

p_take_drop n xs = n>=0 ==> take' n xs ++ drop' n xs == xs

-------------------------------------------------------------------------------
-- Ejercicio 13
-------------------------------------------------------------------------------
desconocida :: (Ord a) => [a] -> Bool
desconocida xs = and [ x<=y | (x,y) <- zip xs (tail xs) ]
-- Qué hace?
--Comprueba si el primer valor es menor o igual que el resto de la lista
-------------------------------------------------------------------------------
-- Ejercicio 14
-------------------------------------------------------------------------------
-- apartados a, b, e y f
-- a)
inserta :: (Ord a) => a -> [a] -> [a]
inserta x [] = [x]
inserta x ys = takeWhile (<x) ys ++ [x] ++ dropWhile (<x) ys


-- b)
inserta' :: (Ord a ) => a -> [a] -> [a]
inserta' x [] = [x]
inserta' x (y:ys) | y<=x = y : inserta' x ys
                  | otherwise = x : (y:ys)

-- e)

ordena :: (Ord a) => [a] -> [a]
ordena [] = []
ordena xs = foldr inserta [] xs
-- f)  Utiliza para ello la función sorted definida en las transarencias
sorted :: (Ord a) => [a] -> Bool
sorted [] = True
sorted [_] = True
sorted (x:y:zs) = x<=y && sorted (y:zs)

p_ordena xs = sorted (ordena xs)


-------------------------------------------------------------------------------
-- Ejercicio 22
-------------------------------------------------------------------------------
binarios :: Integer -> [String]
binarios 0 = [""]
binarios x = sumarBinarios ([a | a <- [1..(div (2^x) 2)], let a = "0"] ++ [b | b <- [1..(div (2^x) 2)], let b = "1"]) (binarios (x-1))

sumarBinarios :: [String] -> [String] -> [String]
sumarBinarios [] _ = []
sumarBinarios x [] = x
sumarBinarios (x:xs) (y:ys) = (x++y) : sumarBinarios xs (ys++[y])

p_binarios n = n>=0 && n<=10 ==>
                            long xss == 2^n
                            && distintos xss
                            && all (`todosEn` "01") xss
                where xss = binarios n

long :: [a] -> Integer
long xs = fromIntegral (length xs)

todosEn :: (Eq a) => [a] -> [a] -> Bool 
ys `todosEn` xs = all (`elem` xs) ys
-------------------------------------------------------------------------------
-- Ejercicio 34
-------------------------------------------------------------------------------

type Izdo = Double
type Dcho = Double
type Epsilon = Double
type Función = Double -> Double

biparticion :: Función -> Izdo -> Dcho -> Epsilon -> Double
biparticion f a b epsilon | (f a)<0 && (f b)<0 || (f a)>0 && (f b)>0 = error "No hay cambio de signo"
                          | long <= epsilon = c
                          | f c <= epsilon = c
                          | (f a)<0 && (f c)>0 || (f a)>0 && (f c)<0= biparticion f a c epsilon
                          | otherwise = biparticion f c b epsilon
  where
      long = b - a
      c = a + long/2



-------------------------------------------------------------------------------
-- Lista de ejercicios extra. Ejercicio [lista de pares] 
-------------------------------------------------------------------------------
empareja :: [a] -> [b] -> [(a,b)]
empareja [] _ = []
empareja _ [] = []
empareja (x:xs) (y:ys) = (x,y) : empareja xs ys

p_empareja xs ys = empareja xs ys == zip xs ys

emparejaCon :: (a -> b -> c) -> [a] -> [b] -> [c]
emparejaCon f [] _ = []
emparejaCon f _ [] = []
emparejaCon f (x:xs) (y:ys) = (f x y) : emparejaCon f xs ys

p_emparejaCon :: (Eq c) => (a -> b -> c) -> [a] -> [b] -> Bool
p_emparejaCon f xs ys = emparejaCon f xs ys == zipWith f xs ys

cotizacion :: [(String, Double)]
cotizacion = [("apple", 116), ("intel", 35), ("google", 824), ("nvidia", 67)]

-- buscarRec
-- buscarC
-- buscarP
-- valorCartera. DIFICIL

-------------------------------------------------------------------------------
-- Lista de ejercicios extra. Ejercicio [mezcla]
-------------------------------------------------------------------------------
-- mezcla

-------------------------------------------------------------------------------
-- Lista de ejercicios extra. Ejercicio [agrupar]
-------------------------------------------------------------------------------
-- agrupar. DIFICIL