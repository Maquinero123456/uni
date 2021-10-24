-------------------------------------------------------------------------------
-- Estructuras de Datos. 2º Curso. ETSI Informática. UMA
--
-- PRACTICA 2ª (Características de la Programación Funcional)
--
-- (completa y sustituye los siguientes datos)
-- Titulación: Grado en Ingeniería …………………………………… [Informática | del Software | de Computadores].
-- Alumno: APELLIDOS, NOMBRE
-- Fecha de entrega:  DIA | MES | AÑO
--
-- Ejercicios resueltos de la Relación : ..........
--
-------------------------------------------------------------------------------
module Practica2 where

import Test.QuickCheck



-------------------------------------------------------------------------------
-- Ejercicio 4
-------------------------------------------------------------------------------
distintos :: Eq a => [a] -> Bool
distintos [] = True
distintos (x:xs) = undefined

-------------------------------------------------------------------------------
-- Ejercicio 11
-------------------------------------------------------------------------------
-- take'
-- drop'

-------------------------------------------------------------------------------
-- Ejercicio 13
-------------------------------------------------------------------------------
desconocida :: (Ord a) => [a] -> Bool
desconocida xs = and [ x<=y | (x,y) <- zip xs (tail xs) ]
-- Qué hace?

-------------------------------------------------------------------------------
-- Ejercicio 14
-------------------------------------------------------------------------------
-- apartados a, b, e y f
-- a)
inserta :: (Ord a) => a -> [a] -> [a]
inserta x s = undefined


-- b)
inserta' :: (Ord a ) => a -> [a] -> [a]
inserta' x [] = undefined
inserta' x (y:ys) = undefined

-- e)

ordena :: (Ord a) => [a] -> [a]
ordena xs = undefined

-- f)  Utiliza para ello la función sorted definida en las transarencias



-------------------------------------------------------------------------------
-- Ejercicio 22
-------------------------------------------------------------------------------
binarios ::Integer -> [String]
binarios 0 = [""]
binarios x | x > 0 = undefined

-------------------------------------------------------------------------------
-- Ejercicio 34
-------------------------------------------------------------------------------

type Izdo = Double
type Dcho = Double
type Epsilon = Double
type Función = Double -> Double
biparticion :: Función -> Izdo -> Dcho -> Epsilon -> Double

biparticion f a b epsilon
  | long < epsilon    = undefined
-- sigue aqui
  where
      long = b - a
-- sigue aqui


-------------------------------------------------------------------------------
-- Lista de ejercicios extra. Ejercicio [lista de pares] 
-------------------------------------------------------------------------------

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