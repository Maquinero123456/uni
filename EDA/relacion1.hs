-------------------------------------------------------------------------------
-- Estructuras de Datos. 2º Curso. ETSI Informática. UMA
--
-- (completa y sustituye los siguientes datos)
-- Titulación: Grado en Ingeniería Informática
-- Alumno: Navarro Jimena, David
-- Fecha de entrega: DIA | MES | AÑO
--
-- Relación de Ejercicios 1. Ejercicios resueltos: ..........
--
-------------------------------------------------------------------------------
import Test.QuickCheck

--Ejercicio 1--
esTerna :: Integer -> Integer -> Integer -> Bool
esTerna x y z | x^2+y^2==z^2= True
              | otherwise= False

terna :: Integer -> Integer -> (Integer, Integer, Integer)
terna x y | x>y && x>0 && y>0 = (x^2-y^2, 2*x*y, x^2+y^2)
          | otherwise= error "X debe ser mayor que Y, X e Y tienen que ser mayor o igual que 0"

pTernas x y = x>0 && y>0 && x>y ==> esTerna l1 l2 h
    where
        (l1,l2,h) = terna x y

--Ejercicio 2--
intercambia :: (a,b) -> (b,a)
intercambia (x, y) = (y, x)

--Ejercicio 3--
ordena2 :: Ord a => (a,a) -> (a,a)
ordena2 (x, y) | x>y= (y, x)
               | otherwise= (x, y)

p1_ordena2 x y = enOrden (ordena2 (x,y))
    where enOrden (x,y) = x<=y
p2_ordena2 x y = mismosElementos (x,y) (ordena2 (x,y))
    where
        mismosElementos (x,y) (z,v) = (x==z && y==v) || (x==v && y==z)

ordena3 :: Ord a => (a, a, a) -> (a, a, a)
ordena3 (x, y, z) | x>y= ordena3 (y, x, z)
                  | y>z= ordena3 (x, z, y)
                  | otherwise= (x, y, z)

p1_ordena3 x y z= enOrden (ordena3 (x,y,z))
    where enOrden (x,y,z) = x<=y && y<=z
p2_ordena3 x y z= mismosElementos (x,y,z) (ordena3 (x,y,z))
    where
        mismosElementos (x,y,t) (z,v,u) = (x==z && y==v && t==u) || (x==u && y==z && t==v) || (x==v && y==u && t==z)

--Ejercicio 4--
max2 :: Ord a => a -> a -> a
max2 x y | x > y = x
         | otherwise= y

p1_max2 x y = max2 x y==x || max2 x y==y

p2_max2 x y = max2 x y >= x || max2 x y >= y

p3_max2 x y = x>=y && max2 x y ==x

p4_max2 x y = x<=y && max2 x y ==y

--Ejercicio 5--
entre :: Ord a => a -> (a,a) -> Bool
entre x (y, z) | x>=y && x<=z = True
               | otherwise= False

--Ejercicio 6--
iguales3 :: Eq a => (a,a,a) -> Bool
iguales3 (x, y, z) | x==y && y==z && z==x = True
                   | otherwise= False

--Ejercicio 7--
type TotalSegundos = Integer
type Horas = Integer
type Minutos = Integer
type Segundos = Integer
descomponer :: TotalSegundos -> (Horas,Minutos,Segundos)
descomponer x = (horas, minutos, segundos)
    where
        horas = div x 3600
        minutos = div (x-horas*3600) 60
        segundos = x-horas*3600-minutos*60

p_descomponer x = x>=0 ==> h*3600 + m*60 + s == x && entre m (0,59) && entre s (0,59)
    where (h,m,s) = descomponer x

--Ejercicio 8--
unEuro :: Double
unEuro = 166.386

pesetasAEuros :: Double -> Double
pesetasAEuros x= x/unEuro

eurosAPesetas :: Double -> Double
eurosAPesetas x= x*unEuro

p_inversas x = eurosAPesetas (pesetasAEuros x) == x

--Ejercicio 9--
infix 4 ~=
(~=) :: Double -> Double -> Bool
x ~= y = abs (x-y) < epsilon
    where epsilon = 1/1000

p2_inversas x = eurosAPesetas (pesetasAEuros x) ~= x

--Ejercicio 10--
raices :: Double -> Double -> Double -> (Double, Double)
raices a b c | b^2-4*a*c >= 0 = ((-b+sqrt(b^2-4*a*c))/(2*a),(-b-sqrt(b^2-4*a*c))/(2*a))
             | otherwise= error "Raices no reales"

p1_raices a b c = esRaíz r1 && esRaíz r2
    where
        (r1,r2) = raices a b c
        esRaíz r = a*r^2 + b*r + c ~= 0

p2_raices a b c = b^2-4*a*c >= 0 && (a<0 || a>0) ==> esRaíz r1 && esRaíz r2
    where
        (r1,r2) = raices a b c
        esRaíz r = a*r^2 + b*r + c ~= 0

--Ejercicio 11--
esMultiplo :: Integer -> Integer -> Bool
esMultiplo x y | mod x y == 0 = True
               | otherwise= False

--Ejercicio 12--
infixl 1 ==>>
(==>>) :: Bool -> Bool -> Bool
False ==>> y = True
True ==>> True = True
True ==>> False = False

--Ejercicio 13--
esBisiesto :: Integer -> Bool
esBisiesto x | mod x 4 == 0 && mod x 100 /= 0 = True
             | mod x 100 == 0 && mod x 400 == 0 = True
             | otherwise= False

--Ejercicio 14--
potencia :: Integer -> Integer -> Integer
potencia x 0 = 1
potencia x y = x * potencia x (y-1)

potencia' :: Integer -> Integer -> Integer
potencia' x 0= 1
potencia' x 2= x*x
potencia' x y | mod y 2 == 0 = potencia' (potencia' x (div y 2)) 2
              | otherwise = x * potencia' (potencia' x (div (y-1) 2)) 2

p_pot b n = n>=0 ==> potencia b n == sol && potencia' b n == sol
    where sol = b^n

--Ejercicio 15--
factorial :: Integer -> Integer
factorial 1 = 1
factorial x = x * factorial (x-1)

--Ejercicio 16--
divideA :: Integer -> Integer -> Bool 
divideA x y | mod y x == 0 = True
            | otherwise= False

p1_divideA x y = y/=0 && y `divideA` x ==> div x y * y == x

p2_divideA x y z= y/=0 && y `divideA` x && y `divideA` z ==> y `divideA` (x+z)

--Ejercicio 17--
mediana :: Ord a => (a,a,a,a,a) -> a
mediana (x, y, z, t, u) | x > z = mediana(z, y, x, t, u)
                        | y > z = mediana(x, z, y, t, u)
                        | t < z = mediana(x, y, t, z, u)
                        | u < z = mediana(x, y, u, t, z)
                        | otherwise = z