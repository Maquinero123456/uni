-------------------------------------------------------------------------------
-- Apellidos, Nombre: 
-- Titulacion, Grupo: 
--
-- Estructuras de Datos. Grados en Informatica. UMA.
-------------------------------------------------------------------------------

module AVLBiDictionary( BiDictionary
                      , empty
                      , isEmpty
                      , size
                      , insert
                      , valueOf
                      , keyOf
                      , deleteByKey
                      , deleteByValue
                      , toBiDictionary
                      , compose
                      , isPermutation
                      , orbitOf
                      , cyclesOf
                      ) where

import qualified DataStructures.Dictionary.AVLDictionary as D
import qualified DataStructures.Set.BSTSet               as S

import           Data.List                               (intercalate, nub,
                                                          (\\))
import           Data.Maybe                              (fromJust, fromMaybe,
                                                          isJust)
import           Test.QuickCheck


data BiDictionary a b = Bi (D.Dictionary a b) (D.Dictionary b a)

-- | Exercise a. empty, isEmpty, size

empty :: (Ord a, Ord b) => BiDictionary a b
empty = Bi D.empty D.empty

isEmpty :: (Ord a, Ord b) => BiDictionary a b -> Bool
isEmpty (Bi x y) = D.isEmpty x && D.isEmpty y

size :: (Ord a, Ord b) => BiDictionary a b -> Int
size (Bi x y) = D.size x

-- | Exercise b. insert

insert :: (Ord a, Ord b) => a -> b -> BiDictionary a b -> BiDictionary a b
insert x y (Bi a b) | D.isDefinedAt x a = Bi (D.insert x y a) (D.insert y x (D.delete (fromJust(D.valueOf x a)) b))
                    | D.isDefinedAt y b = Bi (D.insert x y (D.delete (fromJust(D.valueOf y b)) a)) (D.insert y x b)
                    | otherwise = Bi (D.insert x y a) (D.insert y x b)

-- | Exercise c. valueOf

valueOf :: (Ord a, Ord b) => a -> BiDictionary a b -> Maybe b
valueOf x (Bi a b) = D.valueOf x a

-- | Exercise d. keyOf

keyOf :: (Ord a, Ord b) => b -> BiDictionary a b -> Maybe a
keyOf x (Bi a b) = D.valueOf x b

-- | Exercise e. deleteByKey

deleteByKey :: (Ord a, Ord b) => a -> BiDictionary a b -> BiDictionary a b
deleteByKey x (Bi a b) | D.isDefinedAt x a = Bi (D.delete x a) (D.delete (fromJust(D.valueOf x a)) b)
                       | otherwise = Bi a b

-- | Exercise f. deleteByValue

deleteByValue :: (Ord a, Ord b) => b -> BiDictionary a b -> BiDictionary a b
deleteByValue x (Bi a b) | D.isDefinedAt x b = Bi (D.delete (fromJust(D.valueOf x b)) a) (D.delete x b)
                         | otherwise = Bi a b

-- | Exercise g. toBiDictionary

toBiDictionary :: (Ord a, Ord b) => D.Dictionary a b -> BiDictionary a b
toBiDictionary x | esIn (D.values x) = foldr (uncurry insert) empty (D.keysValues x)
                 | otherwise =  error "No es inyectivo"
    where esIn [] = True
          esIn (y:ys) | elem y ys = False
                      | otherwise = esIn ys
-- | Exercise h. compose

compose :: (Ord a, Ord b, Ord c) => BiDictionary a b -> BiDictionary b c -> BiDictionary a c
compose (Bi a b) (Bi x y) = foldr (uncurry insert) empty par
  where par = [(r,n) | r <- D.keys a, n <- D.values x, (D.valueOf r a)==(D.valueOf n y)]

-- | Exercise i. isPermutation

isPermutation :: Ord a => BiDictionary a a -> Bool
isPermutation (Bi x y) = (D.keys x) == (D.keys y)



-- |------------------------------------------------------------------------


-- | Exercise j. orbitOf

orbitOf :: Ord a => a -> BiDictionary a a -> [a]
orbitOf = undefined

-- | Exercise k. cyclesOf

cyclesOf :: Ord a => BiDictionary a a -> [[a]]
cyclesOf = undefined

-- |------------------------------------------------------------------------


instance (Show a, Show b) => Show (BiDictionary a b) where
  show (Bi dk dv)  = "BiDictionary(" ++ intercalate "," (aux (D.keysValues dk)) ++ ")"
                        ++ "(" ++ intercalate "," (aux (D.keysValues dv)) ++ ")"
   where
    aux kvs  = map (\(k,v) -> show k ++ "->" ++ show v) kvs
