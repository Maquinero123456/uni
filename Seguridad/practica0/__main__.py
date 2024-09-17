def cifradoCesarAlfabetoInglesMAY(cadena):
	"""Devuelve	un	cifrado	Cesar	tradicional	(+3)"""
	#	Definir	la	nueva	cadena	resultado
	resultado	=	''
	#	Realizar	el	"cifrado",	sabiendo	que	A	=	65,	Z	=	90,	a	=	97,	z	=	122
	i	=	0
	while i	< len(cadena):
		#	Recoge	el	caracter	a	cifrar
		ordenClaro	=	ord(cadena[i])
		ordenCifrado	=	0
		#	Cambia	el	caracter	a	cifrar
		if (ordenClaro	>=	65 and ordenClaro	<=	90):
			ordenCifrado	=	(((ordenClaro	- 65)	+	3)	%	26)	+	65
			#	Añade	el	caracter	cifrado	al	resultado
			resultado	=	resultado	+	chr(ordenCifrado)
			i	=	i	+	1
			#	devuelve	el	resultado
	return resultado

def descifradoCesarAlfabetoInglesMAY(cadena):
	"""Devuelve	un	descifrado	Cesar	tradicional	(-3)"""
	#	Definir	la	nueva	cadena	resultado
	resultado	=	''
	#	Realizar	el	"cifrado",	sabiendo	que	A	=	65,	Z	=	90,	a	=	97,	z	=	122
	i	=	0
	while i	< len(cadena):
		#	Recoge	el	caracter	a	cifrar
		ordenClaro	=	ord(cadena[i])
		ordenCifrado	=	0
		#	Cambia	el	caracter	a	cifrar
		if (ordenClaro	>=	65 and ordenClaro	<=	90):
			ordenCifrado	=	(((ordenClaro - 65)	- 3) % 26) + 65
			#	Añade	el	caracter	cifrado	al	resultado
			resultado = resultado + chr(ordenCifrado)
			i = i	+ 1
			#	devuelve	el	resultado
	return resultado

def cifradoCesarAlfabetoIngles(cadena):
	"""Devuelve	un	cifrado	Cesar	tradicional	(+3)"""
	#	Definir	la	nueva	cadena	resultado
	resultado	=	''
	#	Realizar	el	"cifrado",	sabiendo	que	A	=	65,	Z	=	90,	a	=	97,	z	=	122
	i	=	0
	while i	< len(cadena):
		#	Recoge	el	caracter	a	cifrar
		ordenClaro	=	ord(cadena[i])
		ordenCifrado	=	0
		#	Cambia	el	caracter	a	cifrar
		if (ordenClaro	>=	65 and ordenClaro	<=	90):
			ordenCifrado	=	(((ordenClaro	- 65)	+	3)	%	26)	+	65
			#	Añade	el	caracter	cifrado	al	resultado
			resultado	=	resultado	+	chr(ordenCifrado)
			i	=	i	+	1
			#	devuelve	el	resultado
		else:
			ordenCifrado	=	(((ordenClaro	- 97)	+	3)	%	26)	+	97
			#	Añade	el	caracter	cifrado	al	resultado
			resultado	=	resultado	+	chr(ordenCifrado)
			i	=	i	+	1
			#	devuelve	el	resultado
	return resultado

def descifradoCesarAlfabetoIngles(cadena):
	"""Devuelve	un	descifrado	Cesar	tradicional	(-3)"""
	#	Definir	la	nueva	cadena	resultado
	resultado	=	''
	#	Realizar	el	"cifrado",	sabiendo	que	A	=	65,	Z	=	90,	a	=	97,	z	=	122
	i	=	0
	while i	< len(cadena):
		#	Recoge	el	caracter	a	cifrar
		ordenClaro	=	ord(cadena[i])
		ordenCifrado	=	0
		#	Cambia	el	caracter	a	cifrar
		if (ordenClaro	>=	65 and ordenClaro	<=	90):
			ordenCifrado	=	(((ordenClaro - 65)	- 3) % 26) + 65
			#	Añade	el	caracter	cifrado	al	resultado
			resultado = resultado + chr(ordenCifrado)
			i = i	+ 1
			#	devuelve	el	resultado
		else:
			ordenCifrado	=	(((ordenClaro	- 97) - 3)	%	26)	+	97
			#	Añade	el	caracter	cifrado	al	resultado
			resultado	=	resultado	+	chr(ordenCifrado)
			i	=	i	+	1
			#	devuelve	el	resultado
	return resultado

def cifradoCesarAlfabetoInglesI(cadena, desplazamiento):
	"""Devuelve	un	cifrado	Cesar	tradicional	(+3)"""
	#	Definir	la	nueva	cadena	resultado
	resultado	=	''
	#	Realizar	el	"cifrado",	sabiendo	que	A	=	65,	Z	=	90,	a	=	97,	z	=	122
	i	=	0
	while i	< len(cadena):
		#	Recoge	el	caracter	a	cifrar
		ordenClaro	=	ord(cadena[i])
		ordenCifrado	=	0
		#	Cambia	el	caracter	a	cifrar
		if (ordenClaro	>=	65 and ordenClaro	<=	90):
			ordenCifrado	=	(((ordenClaro	- 65)	+	desplazamiento)	%	26)	+	65
			#	Añade	el	caracter	cifrado	al	resultado
			resultado	=	resultado	+	chr(ordenCifrado)
			i	=	i	+	1
			#	devuelve	el	resultado
		else:
			ordenCifrado	=	(((ordenClaro	- 97)	+	desplazamiento)	%	26)	+	97
			#	Añade	el	caracter	cifrado	al	resultado
			resultado	=	resultado	+	chr(ordenCifrado)
			i	=	i	+	1
			#	devuelve	el	resultado
	return resultado

def descifradoCesarAlfabetoInglesI(cadena, desplazamiento):
	"""Devuelve	un	descifrado	Cesar	tradicional	(-3)"""
	#	Definir	la	nueva	cadena	resultado
	resultado	=	''
	#	Realizar	el	"cifrado",	sabiendo	que	A	=	65,	Z	=	90,	a	=	97,	z	=	122
	i	=	0
	while i	< len(cadena):
		#	Recoge	el	caracter	a	cifrar
		ordenClaro	=	ord(cadena[i])
		ordenCifrado	=	0
		#	Cambia	el	caracter	a	cifrar
		if (ordenClaro	>=	65 and ordenClaro	<=	90):
			ordenCifrado	=	(((ordenClaro - 65)	- desplazamiento) % 26) + 65
			#	Añade	el	caracter	cifrado	al	resultado
			resultado = resultado + chr(ordenCifrado)
			i = i	+ 1
			#	devuelve	el	resultado
		else:
			ordenCifrado	=	(((ordenClaro	- 97) - desplazamiento)	%	26)	+	97
			#	Añade	el	caracter	cifrado	al	resultado
			resultado	=	resultado	+	chr(ordenCifrado)
			i	=	i	+	1
			#	devuelve	el	resultado
	return resultado



def main():
    palabra = "HOLA"
    cifradoMayusculas = cifradoCesarAlfabetoInglesMAY(palabra)
    print(cifradoMayusculas)
    print(descifradoCesarAlfabetoInglesMAY(cifradoMayusculas))
    palabra = "hOlA"
    cifradoNormal = cifradoCesarAlfabetoIngles(palabra)
    print(cifradoNormal)
    print(descifradoCesarAlfabetoIngles(cifradoNormal))
    cifradoI = cifradoCesarAlfabetoInglesI(palabra, 6)
    print(cifradoI)
    print(descifradoCesarAlfabetoInglesI(cifradoI, 6))
	

if __name__ == "__main__":
    main()