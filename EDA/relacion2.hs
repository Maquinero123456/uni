import Test.QuickCheck
import Data.List (nub)
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
                                && null (filter (/=x) xs)
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
intersect (x:xs) ys | x `elem` ys = x : intersect xs ys
                    | otherwise = intersect xs ys

mcd :: Integer -> Integer -> Integer
mcd x y = maximum ((divisores x) `intersect` (divisores y))

p_mcd x y z = x/=0 && y/=0 && z/=0 ==> mcd (z*x) (z*y) == abs z*mcd x y

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

--Ejercicio 11--
take' :: Int -> [a] -> [a]
take' n xs = [ x | (p,x) <- zip [0..(n-1)] xs]

drop' :: Int -> [a] -> [a]
drop' n xs = [ x | (p,x) <- zip [0..length xs] xs, p>=n]

p_take_drop n xs = n>=0 ==> take' n xs ++ drop' n xs == xs

--Ejercicio 12--
concat' :: [[a]] -> [a]
concat' = foldr (++) []

concat'' :: [[a]] -> [a]
concat'' xs = [ x | x <- [], y <- []]

--Ejercicio 13--
desconocida :: (Ord a) => [a] -> Bool
desconocida xs = and [ x<=y | (x,y) <- zip xs (tail xs)]
--Comprueba si el primer valor es menor o igual que el resto de la lista

--Ejercicio 14--
inserta :: (Ord a) => a -> [a] -> [a]
inserta x [] = [x]
inserta x ys = takeWhile (<x) ys ++ [x] ++ dropWhile (<x) ys

inserta' :: (Ord a) => a -> [a] -> [a]
inserta' x [] = [x]
inserta' x (y:ys) | y<=x = y : inserta' x ys
                  | otherwise = x : (y:ys)

p1_inserta x xs = desconocida xs ==> desconocida (inserta x xs)

ordena :: (Ord a) => [a] -> [a]
ordena [] = []
ordena xs = foldr inserta [] xs

sorted :: (Ord a) => [a] -> Bool
sorted [] = True
sorted [_] = True
sorted (x:y:zs) = x<=y && sorted (y:zs)

p_ordena xs = sorted (ordena xs)

--Ejercicio 15--
geometrica :: Integer -> Integer -> [Integer]
geometrica x y = iterate (*y) x

p1_geometrica x r = x>0 && r>0 ==>
                            and [ div z y == r | (y,z) <- zip xs (tail xs)]
                                where xs = take 100 (geometrica x r)

multiplosDe :: Integer -> [Integer]
multiplosDe x | x>0 = iterate (+x) 0
              | otherwise = error "Tiene que ser mayor que 0"

potenciasDe :: Integer -> [Integer]
potenciasDe x = iterate (*x) 1

--Ejercicio 16--
primeroComun :: Ord a => [a] -> [a] -> a
primeroComun xs [] = error "No hay comun"
primeroComun [] ys = error "No hay comun"
primeroComun (x:xs) (y:ys) | x<y = primeroComun xs (y:ys)
                           | x>y = primeroComun (x:xs) ys
                           | otherwise = x

mcm' :: Integer -> Integer -> Integer
mcm' x y | x==0 || y==0 = 0
         | x>0 && y>0 = primeroComun (drop 1 (multiplosDe x)) (drop 1 (multiplosDe y))
         | otherwise = error "Los dos numeros deben ser naturales"

p_mcm x y = x>=0 && y>=0 ==> mcm' x y == lcm x y

--Ejercicio 17--
primeroComunDeTres :: Ord a => [a] -> [a] -> [a] -> a
primeroComunDeTres (x:xs) (y:ys) (z:zs) | x > y = primeroComunDeTres (x:xs) ys (z:zs)
                                        | y > z = primeroComunDeTres (x:xs) (y:ys) zs
                                        | z > x = primeroComunDeTres xs (y:ys) (z:zs)
                                        | otherwise = x

--Ejercicio 18--
factPrimos :: Integer -> [Integer]
factPrimos x = fp x 2
    where
        fp x d
         | x' < d = [x]
         | r == 0 = d : fp x' d
         | otherwise = fp x (d+1)
         where (x' , r) = divMod x d

factPrimos' :: Integer -> [Integer]
factPrimos' x = fp x 2
    where
        fp x d
         | d>2 && even d = fp x (d+1)
         | x' < d = [x]
         | r == 0 = d : fp x' d
         | otherwise = fp x (d+1)
         where (x' , r) = divMod x d

p_factores x = product (factPrimos' x) == x

--Ejercicio 19--
mezcla :: [Integer] -> [Integer] -> [Integer]
mezcla [] ys = ys
mezcla xs [] = xs
mezcla (x:xs) (y:ys) | x==y = x : mezcla xs ys
                     | x<y = x : mezcla xs (y:ys)
                     | otherwise = y : mezcla (x:xs) ys

mcm'' :: Integer -> Integer -> Integer
mcm'' x y | x>=0 && y>=0 = product (mezcla (factPrimos' x) (factPrimos' y))
          | otherwise = error "Los dos numeros deben ser naturales"

p_mcm' x y = x>=0 && y>=0 ==> mcm'' x y == lcm x y

--Ejercicio 20--
mezc' :: [Integer] -> [Integer] -> [Integer]
mezc' [] ys = []
mezc' xs [] = []
mezc' (x:xs) (y:ys) | x==y = x : mezc' xs ys
                    | x<y = mezc' xs (y:ys)
                    | otherwise = mezc' (x:xs) ys

mcd'' :: Integer -> Integer -> Integer
mcd'' x y = product (mezc' (factPrimos x) (factPrimos y))

p_mcd'' x y = x>0 && y>0 ==> mcd'' x y == gcd x y

--Ejercicio 21--
nub' :: (Eq a) => [a] -> [a]
nub' [] = []
nub' (x:xs) = x:nub' [ j | (p,j) <- zip [0..length xs] xs, x/=j]

p_nub' xs = nub xs == nub' xs

p_sinRepes xs = distintos (nub' xs)

todosEn :: (Eq a) => [a] -> [a] -> Bool
ys `todosEn` xs = all (`elem` xs) ys

p_sinRepes' xs = todosEn xs (nub' xs)

--Ejercicio 22--
binarios :: Integer -> [String]
binarios 0 = [""]
binarios 1 = ["0","1"]
binarios x = map ("0"++) (binarios (x-1)) ++ map ("1"++) (binarios (x-1))
--binarios x = sumarBinarios ([a | a <- [1..(div (2^x) 2)], let a = "0"] ++ [b | b <- [1..(div (2^x) 2)], let b = "1"]) (binarios (x-1))

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

--Ejercicio 23--
varRep :: Integer -> String -> [String]
varRep _ "" = [""]
varRep 0 _ = [""]
varRep x xs = [ x:y | x <- xs, y <- siguiente]
    where siguiente = varRep (x-1) xs

p_varRep m xs = m>=0 && m<=5 && n<=5 && distintos xs ==>
                    long vss == n^m
                    && distintos vss
                    && all (`todosEn` xs) vss
        where vss = varRep m xs
              n   = long xs

--Ejercicio 24--
var :: Integer -> String -> [String]
var _ "" = [""]
var 0 _ = [""]
var x xs = [ x:y | y <- siguiente, x <- xs, notElem x y]
    where siguiente = var (x-1) xs

p_var m xs = n<=5 && distintos xs && m>=0 && m<=n ==>
             long vss == fact n `div` fact (n-m)
             && distintos vss
             && all distintos vss
             && all (`todosEn` xs) vss
    where
        vss = var m xs
        n = long xs
        fact :: Integer -> Integer
        fact x = product [1..x]

--Ejercicio 25--
intercala :: Integer -> [Integer] -> [[Integer]]
intercala x [] = [[x]]
intercala x xs = [ y | y <- [xs]]
