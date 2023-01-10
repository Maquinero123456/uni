#!/bin/sh
pattern="test/*.plx"
if [ "$1" != "" ]
then
   pattern=$1;
fi
for i in `find . -wholename "$pattern" -print`
do
   echo "---------------------------";
   echo $i;
   echo "----";
   cat $i;
   echo "----";
   ecup PLXC $i ${i%.*}.ctd;
   echo ${i%.*}.ctd;
   echo "----";
   cat ${i%.*}.ctd;
   echo "----";
   ctd ${i%.*}.ctd
done
