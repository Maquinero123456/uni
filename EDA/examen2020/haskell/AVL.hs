{-# OPTIONS_GHC -Wno-overlapping-patterns #-}
{------------------------------------------------------------------------------
 - Student's name:
 -
 - Student's group:
 -----------------------------------------------------------------------------}

module AVL
  (
    Weight
  , Capacity
  , AVL (..)
  , Bin
  , emptyBin
  , remainingCapacity
  , addObject
  , maxRemainingCapacity
  , height
  , nodeWithHeight
  , node
  , rotateLeft
  , addNewBin
  , addFirst
  , addAll
  , toList
  , linearBinPacking
  , seqToList
  , addAllFold
  ) where

type Capacity = Int
type Weight= Int

data Bin = B Capacity [Weight]

data AVL = Empty | Node Bin Int Capacity AVL AVL deriving Show


emptyBin :: Capacity -> Bin
emptyBin x = B x []

remainingCapacity :: Bin -> Capacity
remainingCapacity (B x y) = x

addObject :: Weight -> Bin -> Bin
addObject x (B a b) | a-x>=0 = B (a-x) (x:b)
                    | otherwise = error "No cabe"

maxRemainingCapacity :: AVL -> Capacity
maxRemainingCapacity Empty = 0
maxRemainingCapacity (Node _ _ x _ _) = x

height :: AVL -> Int
height Empty = 0
height (Node _ x _ y z) | a > b = a
                        | b > a = b
                        | otherwise = x
  where
    a = height y
    b = height z


nodeWithHeight :: Bin -> Int -> AVL -> AVL -> AVL
nodeWithHeight c@(B x y) z Empty Empty = Node c z x Empty Empty
nodeWithHeight c@(B x y) z d@(Node bin1 _ a node1 node2) Empty | x>a = Node c z x (nodeWithHeight bin1 (z+1) node1 node2) Empty
                                                               | otherwise = Node c z a (nodeWithHeight bin1 (z+1) node1 node2) Empty
nodeWithHeight c@(B x y) z Empty d@(Node bin1 _ a node1 node2) | x>a = Node c z x Empty (nodeWithHeight bin1 (z+1) node1 node2)
                                                               | otherwise = Node c z a Empty (nodeWithHeight bin1 (z+1) node1 node2)
nodeWithHeight c@(B x y) z d@(Node bin1 _ a node1 node2) e@(Node bin2 _ b node3 node4) | x>a && x>b = Node c z x (nodeWithHeight bin1 (z+1) node1 node2) (nodeWithHeight bin2 (z+1) node3 node4)
                                                                                       | a>x && a>b = Node c z a (nodeWithHeight bin1 (z+1) node1 node2) (nodeWithHeight bin2 (z+1) node3 node4)
                                                                                       | otherwise = Node c z b (nodeWithHeight bin1 (z+1) node1 node2) (nodeWithHeight bin2 (z+1) node3 node4)


node :: Bin -> AVL -> AVL -> AVL
node c@(B x y) d@(Node bin1 altura1 a node1 node2) e@(Node bin2 altura2 b node3 node4) = nodeWithHeight c 1 d e
node c@(B x y) d@(Node bin1 altura1 a node1 node2) Empty = nodeWithHeight c 1 d Empty
node c@(B x y) Empty d@(Node bin1 altura1 a node1 node2) = nodeWithHeight c 1 Empty d
node c@(B x y) Empty Empty = nodeWithHeight c 1 Empty Empty

rotateLeft :: Bin -> AVL -> AVL -> AVL
rotateLeft c@(B x y) d@(Node bin1 altura1 a node1 node2) e@(Node bin2 altura2 b node3 node4) = node bin2 (Node c 1 x d node3) node4
rotateLeft c@(B x y) Empty e@(Node bin2 altura2 b node3 node4) = node bin2 (Node c 1 x Empty node3) node4
rotateLeft c@(B x y) d@(Node bin1 altura1 a node1 node2) Empty = error "Arbol derecho no puede ser vacio"
rotateLeft c@(B x y) Empty Empty = error "Arbol derecho no puede ser vacio"

addNewBin :: Bin -> AVL -> AVL
addNewBin b@(B x y) (Node bin1 altura1 a node1 node2) | height node1 > (height node2 + 1)= addNewBin b node2
                                                      | otherwise = rotateLeft bin1 node1 (addNewBin b node2)
addNewBin b@(B x y) Empty = Node b 1 x Empty Empty

addFirst :: Capacity -> Weight -> AVL -> AVL
addFirst x y Empty = Node (addObject y (emptyBin x)) 1 (x-y) Empty Empty
addFirst x y f@(Node a b c d e) | y<c = addNewBin (addObject y (emptyBin x)) f
                                | maxRemainingCapacity d >= y = addFirst x y d
                                | remainingCapacity a >= y = Node (addObject y a) b c d e
                                | otherwise = addFirst x y e

addAll:: Capacity -> [Weight] -> AVL
addAll x [] = Node (emptyBin x) 1 x Empty Empty
addAll x [y:ys] = 

toList :: AVL -> [Bin]
toList _ = undefined

{-
	SOLO PARA ALUMNOS SIN EVALUACION CONTINUA
  ONLY FOR STUDENTS WITHOUT CONTINUOUS ASSESSMENT
 -}

data Sequence = SEmpty | SNode Bin Sequence deriving Show

linearBinPacking:: Capacity -> [Weight] -> Sequence
linearBinPacking _ _ = undefined

seqToList:: Sequence -> [Bin]
seqToList _ = undefined

addAllFold:: [Weight] -> Capacity -> AVL
addAllFold _ _ = undefined



{- No modificar. Do not edit -}

objects :: Bin -> [Weight]
objects (B _ os) = reverse os


instance Show Bin where
  show b@(B c os) = "Bin("++show c++","++show (objects b)++")"