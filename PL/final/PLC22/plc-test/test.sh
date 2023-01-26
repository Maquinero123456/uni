#!/bin/sh
pattern="*.plx"  
if [ "$1" != "" ]
then
   pattern=$1;
fi
for i in `find . -name "$pattern" -print`
do
   echo "---------------------------";
   echo $i;
   echo "----";
   cat $i;
   echo "----";
   java PLXC $i ${i%.*}.ctd;
   echo ${i%.*}.ctd;
   echo "----";
   cat ${i%.*}.ctd;
   echo "----";
   ctd ${i%.*}.ctd
done
