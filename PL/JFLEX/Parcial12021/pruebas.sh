#!/bin/bash
clear
sudo jflex *.lex
javac *.java
echo ------------------------
echo "-     EJECUTANDO       -"
echo ------------------------
for i in {0..7}
do
	echo
	echo "-------------------------"
	echo
 	echo "prueba $i"
 	prueba="t0$i.txt"
 	salida="t0$i.out"
	java Ticket $prueba
	echo
	echo "-------------------------"
	echo "---RESULTADO ESPERADO----"
	echo
	cat $salida
done
