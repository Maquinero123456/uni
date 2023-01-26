set int a;
set int b;
set int c;
a = {1,2,3,4,5,6,7};
b = {1,3,5};
c = {2,4,5,6,7,8};
c *=  a*b + b*c + {7};
print(c);

