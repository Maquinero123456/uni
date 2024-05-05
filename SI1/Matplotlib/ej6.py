import matplotlib as mpl
import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

MAXIMO_ITERACIONES = 80
def mandelbrot(a,b):
    c = complex(a,b)
    z = 0
    n = 0
    while abs(z) <= 2 and n < MAXIMO_ITERACIONES:
        z = z*z + c
        n += 1
    color_pixel = 255 - int(n * 255 / MAXIMO_ITERACIONES)
    return color_pixel

entero = np.linspace(-2,1, 600)
imaginario = np.linspace(-1,1,400)
resultado = np.vectorize(mandelbrot)(entero, imaginario[:, np.newaxis])
plt.imshow(resultado)
plt.savefig('ej6.png')
