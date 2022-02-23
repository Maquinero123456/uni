-------------------------------------------------------------------------------
-- Estructuras de Datos. Grado en Informática, IS e IC. UMA.
-- Examen de Febrero 2015.
--
-- Implementación del TAD Deque
--
-- Apellidos:
-- Nombre:
-- Grado en Ingeniería ...
-- Grupo:
-- Número de PC:
-------------------------------------------------------------------------------

module TwoListsDoubleEndedQueue
   ( DEQue
   , empty
   , isEmpty
   , first
   , last
   , addFirst
   , addLast
   , deleteFirst
   , deleteLast
   ) where

import Prelude hiding (last)
import Data.List(intercalate)
import Test.QuickCheck

data DEQue a = DEQ [a] [a]

-- Complexity:
empty :: DEQue a
empty = DEQ [] []

-- Complexity:
isEmpty :: DEQue a -> Bool
isEmpty (DEQ x y) = null x && null y

-- Complexity:
addFirst :: a -> DEQue a -> DEQue a
addFirst x (DEQ xs ys) = DEQ (x:xs) ys
-- Complexity:
addLast :: a -> DEQue a -> DEQue a
addLast x (DEQ xs ys) = DEQ xs (x:ys)

-- Complexity:
first :: DEQue a -> a
first (DEQ [] []) = error "Vacio"
first (DEQ [] y) = first (splitList y [])
first (DEQ (x:xs) _) = x

-- Complexity:
last :: DEQue a -> a
last (DEQ [] []) = error "Vacio"
last (DEQ _ (x:xs)) = x
last (DEQ x []) = last (splitList2 x [])

-- Complexity:
deleteFirst :: DEQue a -> DEQue a
deleteFirst (DEQ [] []) = error "Vacio"
deleteFirst (DEQ [] y) = deleteFirst (splitList y [])
deleteFirst (DEQ (x:xs) y) = DEQ xs y

-- Complexity:
deleteLast :: DEQue a -> DEQue a
deleteLast (DEQ [] []) = error "Vacio"
deleteLast (DEQ y []) = deleteLast (splitList2 y [])
deleteLast (DEQ y (x:xs)) = DEQ y xs

splitList :: [a] -> [a] -> DEQue a
splitList x [] = splitList (tail x) [head x]
splitList [] x = foldr addFirst empty (inverse x)
splitList x y | length x - length y <= 1 = foldr addFirst (foldr addLast empty y) (inverse x)
              | otherwise = splitList (tail x) (head x : y)

splitList2 :: [a] -> [a] -> DEQue a
splitList2 x [] = foldr addLast empty (inverse x)
splitList2 [] x = splitList (tail x) [head x]
splitList2 x y | length x - length y <= 1 = foldr addFirst (foldr addLast empty x) (inverse y)
              | otherwise = splitList (tail x) (head x : y)

inverse :: [a] -> [a]
inverse [] = []
inverse (x:xs) = inverse xs++[x]

instance (Show a) => Show (DEQue a) where
   show q = "TwoListsDoubleEndedQueue(" ++ intercalate "," [show x | x <- toList q] ++ ")"

toList :: DEQue a -> [a]
toList (DEQ xs ys) =  xs ++ reverse ys

instance (Eq a) => Eq (DEQue a) where
   q == q' =  toList q == toList q'

instance (Arbitrary a) => Arbitrary (DEQue a) where
   arbitrary =  do
      xs <- listOf arbitrary
      ops <- listOf (oneof [return addFirst, return addLast])
      return (foldr id empty (zipWith ($) ops xs))
