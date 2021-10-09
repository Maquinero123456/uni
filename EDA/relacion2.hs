import Test.QuickCheck
--Ejercicio 1--
{-data Direction = North | South | East | West deriving (Eq, Ord, Enum, Show)

infix 4 <<
(<<) :: Direction -> Direction -> Bool
x << y = (fromEnum x)< (fromEnum y)

p_menor x y = (x < y) == (x<<y)
instance Arbitrary Direction where
    arbitrary = do
        n <- choose (0,3)
        return $ toEnum n -}

data Direction = North | South | East | West deriving (Eq, Enum, Show)

infix 4 <<
(<<) :: Direction -> Direction -> Bool
x << y = fromEnum x< fromEnum y || fromEnum x== fromEnum y

--Ejercicio 2--
maximoYresto :: Ord a => [a] -> (a, [a])
maximoYresto [] = error "Lista vacia"
maximoYresto a = (x, y)
    where
        x = maximum a
        y = filter (<x) a

maximoYresto2 :: Ord a => [a] -> (a, [a])
maximoYresto2 = undefined 

