-------------------------------------------------------------------------------
-- Estructuras de Datos. Grado en Ingeniería INF, IS e IC. UMA.
--  Ejercicio especial del examen ordinario del 2-Febrero-2015.

-- Una implementación del Algoritmo de Prim vía un montículo (heap) de aristas.

-- APELLIDOS:                   NOMBRE:                  GRUPO:
-------------------------------------------------------------------------------

-- por favor, cambie el nombre del módulo con sus apellidos en la forma:

module PrimHeap_RuizLopezManuel where

import Data.List(delete,nub)
import DataStructures.Graph.WeightedGraph
import qualified DataStructures.Heap.WBLeftistHeap as H

instance Eq w => Eq (WeigthedEdge a w)
   where (WE _ w _) == (WE _ w' _) = w == w'

instance Ord w => Ord (WeigthedEdge a w)
  where compare (WE _ w _) (WE _ w' _) = compare w w'

type SpanningTree a w = [WeigthedEdge a w]

type HeapOfEdge a w = H.Heap (WeigthedEdge a w)

prim :: (Eq a, Ord w) => WeightedGraph a w -> a -> SpanningTree a w
prim g v = fst $ primAux g vs [] $ initHeap g v vs
   where vs = delete v (vertices g)

initHeap g v vs = addEdges g v vs H.empty

---------------------------------------
-- A.- Completa la función addEdges.
---------------------------------------

-- añade al heap h las aristas (WE src w dest) con destino en unvisited
addEdges:: (Eq a, Ord w) => WeightedGraph a w -> a -> [a] -> HeapOfEdge a w -> HeapOfEdge a w
addEdges g src unvisited h = undefined

---------------------------------------------------
-- B.- Escribe las ecuaciones para la función primAux.
---------------------------------------------------

primAux :: (Eq a, Ord w) => WeightedGraph a w -> [a] -> SpanningTree a w -> HeapOfEdge a w -> (SpanningTree a w, [a])
primAux g rs st h = undefined

-----------------------------------------------------------------------------
-- C.- Estima la complejidad de la función prim en términos de |E| y |V|.
-----------------------------------------------------------------------------
{- a esta cuestión conteste directamente dentro de este comentario


-}

-------------------------------------------------------------------------------
-- D.- Escribe la función minimumSpanningForest que devuelve un bosque de
--     árboles recubridores mínimos de cada componente conexa del grafo.
-------------------------------------------------------------------------------

type SpanningForest a w = [SpanningTree a w]

minimumSpanningForest :: (Eq a, Ord w) => WeightedGraph a w -> SpanningForest a w

minimumSpanningForest g = undefined

-- ******************
-- ejemplos de grafos
-- ******************

g1 :: WeightedGraph Char Int
g1 = mkWeigthedGraphEdges ['a' .. 'g']
                              [ WE 'a' 7 'b', WE 'a' 5 'd'
                              , WE 'b' 9 'd', WE 'b' 8 'c', WE 'b' 7 'e'
                              , WE 'c' 5 'e'
                              , WE 'd' 15 'e', WE 'd' 6 'f'
                              , WE 'e' 8 'f', WE 'e' 9 'g'
                              , WE 'f' 11 'g'
                              ]

-- un grafo con dos componentes conexas
g2 = mkWeigthedGraphEdges ['a'..'j']
                              [ WE 'a' 7 'b', WE 'a' 5 'd'
                              , WE 'b' 9 'd', WE 'b' 8 'c', WE 'b' 7 'e'
                              , WE 'c' 5 'e'
                              , WE 'd' 15 'e', WE 'd' 6 'f'
                              , WE 'e' 8 'f', WE 'e' 9 'g'
                              , WE 'f' 11 'g'
                              , WE 'h' 5 'i' , WE 'h' 4 'i', WE 'h' 6 'j' , WE 'j' 2 'i'
                              ]

{-

*PrimHeap> prim g2 'j'
[WE 'i' 4 'h',WE 'j' 2 'i']
it :: SpanningTree Char Integer

*PrimHeap> length $ minimumSpanningForest g1
1
it :: Int

*PrimHeap> minimumSpanningForest g1
[[WE 'e' 9 'g',WE 'e' 5 'c',WE 'b' 7 'e',WE 'a' 7 'b',WE 'd' 6 'f',WE 'a' 5 'd']]
it :: SpanningForest Char Int

*PrimHeap> length $ minimumSpanningForest g2
2
it :: Int

*PrimHeap> minimumSpanningForest g2
[[WE 'e' 9 'g',WE 'e' 5 'c',WE 'b' 7 'e',WE 'a' 7 'b',WE 'd' 6 'f',WE 'a' 5 'd'],[WE 'i' 2 'j',WE 'h' 4 'i']]
it :: SpanningForest Char Integer

-}
