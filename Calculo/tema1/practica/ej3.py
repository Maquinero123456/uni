import numpy as np
import matplotlib.pyplot as plt

# 1. Calcular raíces octavas de -i
k = np.arange(8)
roots = np.exp(1j * (-np.pi/2 + 2*np.pi*k) / 8)

# 2. Imprimir con 5 decimales
print("Raíces octavas de -i:\n")
for i, r in enumerate(roots):
    print(f"z{i} = {r.real:.5f} + {r.imag:.5f}j")

# 3. Representación en el plano complejo
plt.figure()
plt.scatter(roots.real, roots.imag)

for i, r in enumerate(roots):
    plt.text(r.real, r.imag, f'z{i}')

plt.axhline(0)
plt.axvline(0)
plt.gca().set_aspect('equal', adjustable='box')
plt.title('Raíces octavas de -i')
plt.grid()

plt.show()

# 4. Suma de las raíces
suma = np.sum(roots)
print("\nSuma de las raíces:", suma)