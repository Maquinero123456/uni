set int c;
c = {1,2,3};
set int d;
d = {1,3,5};
int x;
int y;
int suma;
for(x : c) {
   for(y: d) {
      suma = suma + x*y;
   }
}
print(suma);

