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

--Ejercicio 3--
reparte :: [a] -> ([a], [a])
reparte [] = ([], [])
reparte [x] = ([x], [])
reparte (x:y:ys) = (x:a, y:b)
    where (a, b) = reparte ys

--Ejercicio 4--
distintos :: Eq a => [a] -> Bool
distintos [] = True
distintos [_] = True
distintos (x:xs) | length xs == length (filter (/=x) xs) = distintos xs
                 | otherwise = False

--Ejercicio 5--
replicate' :: Int -> a -> [a]
replicate' x y = [ y | j <- [1..x]]

p_replicate' n x = n >= 0 && n <= 1000 ==>
                                length (filter (==x) xs) == n
                                && length (filter (/=x) xs) ==0
                                    where xs = replicate' n x

--Ejercicio 6--
divideA :: Integer -> Integer -> Bool
divideA x y | mod y x == 0 = True
            | otherwise= False

divisores :: Integer -> [Integer]
divisores x = [ y | y <- [1..a], divideA y a]
    where a = abs x

divisores' :: Integer -> [Integer]
divisores' x = [ y | y <- [(-a)..a], y/=0, divideA y a]
    where a = abs x

--Ejercicio 7--
intersect :: Eq a => [a] -> [a] -> [a]
intersect [] _ = []
intersect _ [] = []
intersect (x:xs) ys | elem x ys = x : intersect xs ys
                    | otherwise = intersect xs ys

mcd :: Integer -> Integer -> Integer
mcd x y = maximum (intersect (divisores x) (divisores y))

p_mcd x y z = x/=0 && y/=0 && z/=0 ==> mcd (z*x) (z*y) == (abs z)*mcd x y

mcm :: Integer -> Integer -> Integer
mcm x y = div (x*y) (mcd x y)

--Ejercicio 8--
esPrimo :: Integer -> Bool
esPrimo 1 = False
esPrimo x | length(divisores x) > 2 = False
          | otherwise = True

primosHasta :: Integer -> [Integer]
primosHasta x = [ x | x <- [1..x], esPrimo x ]

primosHasta' :: Integer -> [Integer]
primosHasta' x = filter esPrimo [1..x]

p1_primos x = primosHasta x == primosHasta' x

--Ejercicio 9--
pares :: Integer -> [(Integer, Integer)]
pares x | x>2 && even x = [ (y,z) | y <- [1..x], z <- [1..x] , esPrimo y && esPrimo z, z>=y ,z+y==x]
        | otherwise = []

goldbach :: Integer -> Bool 
goldbach x | null (pares x) = False
           | otherwise = True

goldbachHasta :: Integer -> Bool
goldbachHasta x = and [goldbach x | x <- [1..x], x>2, even x]

goldbachDebilHasta :: Integer -> Bool
goldbachDebilHasta x = and [goldbach x | x <- [1..x], x>5 && x-3>2, odd x && even(x-3)]

--Ejercicio 10--
esPerfecto :: Integer -> Bool 
esPerfecto x = sum [ y | y <- [1..x], divideA y x, y<x] == x

perfectosMenoresQue :: Integer -> [Integer]
perfectosMenoresQue x = [ x | x <- [1..x], esPerfecto x]