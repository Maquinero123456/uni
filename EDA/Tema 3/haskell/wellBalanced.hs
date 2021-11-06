module WellBalanced where
import DataStructures.Stack.LinearStack as S

wellBalanced :: String -> Bool
wellBalanced xs = wellBalanced' xs S.empty

wellBalanced' :: String -> Stack Char -> Bool
wellBalanced' [] s = isEmpty s
wellBalanced' (x:xs) s | x == '{' || x == '[' || x == '(' = wellBalanced' xs (push x s)
                       | x == '}' && top s == '{' || x == ']' && top s == '[' || x == ')' && (top s) == '(' = wellBalanced' xs (pop s)
                       | otherwise = wellBalanced' xs s