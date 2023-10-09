	@Incluyo las notas que usare y las direcciones de memoria de cosas que usare
	.include "inter.inc"
	.include "notas.inc"
.data
	@Contador y array para los leds
	cuenta: .word 0
	secuen: .word 0b1000000000000000000000000000, 0b0000010000000000000000000000, 0b0000000000100000000000000000, 0b0000000000000000100000000000, 0b0000000000000000010000000000, 0b0000000000000000001000000000, 0b0000000000000000010000000000, 0b0000000000000000100000000000, 0b0000000000100000000000000000, 0b0000010000000000000000000000,0b1000000000000000000000000000
	@Variable para saber si se ha pulsado el boton correcto
	pulsado:.word 0
	@Led con el que se gana para compara y variable para saber si se ha ganado
	led_gan:.word 0b0000000000000000001000000000
	ganar:  .word 0
	@Tiempo para cada dificultado y variable para indicar la dificultad
	facil:	.word 0x20000
	normal: .word 0x10000
	dificil:.word 0x5000
	df:     .word 1
	dn:     .word 0
	dd:     .word 0
	@Contador y arrays de musica
	contador:.word 0
	cancion:.word LA, MI, FA, SOL, FA, MI, RE, RE, FA, LA, SOL, FA, MI, MI, FA, SOL, LA, FA, RE, RE, SILEN, SOL, SIb, REs, REs, DOs, SIb, LA, FA, LA, LA, SOL, FA, MI, MI, FA, SOL, LA, FA, RE, RE	
	cancionp:.word LA, SIb, SIb,  LA,  FA, SOL,  LA, LA, LA,  SOL,  LA
	canciong:.word SIb, RE, FA, SI, SILEN, FA, SI, RE, FA, SOL
	@Variable para cambiar el array de musica cuando se gana y para saltar el fiq
	primeravez:.word 0
	desactivado:.word 0
.text
	@Codigo necesario para iniciar la raspberry pi e interrupciones
	mov r0, #0b11010001
	msr cpsr_c, r0
	mov sp, #0x4000 
	mov r0, #0b11010010   @ Modo IRQ, FIQ&IRQ desact
        msr cpsr_c, r0
        mov sp, #0x8000
        mov r0, #0b11010011   @ Modo SVC, FIQ&IRQ desact
        msr cpsr_c, r0
        mov sp, #0x8000000
	@Cargo arrays en registros
	ldr r7, =secuen
	ldr r6, =cancion
	@Configuro puertos
	ldr r0, =GPBASE
/* guia bits       xx999888777666555444333222111000*/
	ldr r1, =0b00001000000000000001000000000000
	str r1, [r0, #GPFSEL0]
/* guia bits       xx999888777666555444333222111000*/
	ldr r1, =0b00000000001000000000000000001001
	str r1, [r0, #GPFSEL1]
/* guia bits       xx999888777666555444333222111000*/
	ldr r1, =0b00000000001000000000000001000000
	str r1, [r0, #GPFSEL2]
	@Configuro comparadores
	ldr r0, =STBASE
        ldr r1, [r0, #STCLO]
        add r1, #2
        str r1, [r0, #STC1]
	str r1, [r0, #STC3]
	@Configuro interrupciones para que salten con los comparadores
	ldr r0, =INTBASE
        mov r1, #0b0010
        str r1, [r0, #INTENIRQ1]
	mov r1,	#0b10000011	
	str r1,	[r0, #INTFIQCON]	
	@Inicio interrupciones
        mov r1,	#0b00010011	
	msr cpsr_c, r1
	
	@Bucle infinito en el cual compruebo que boton seha pulsado, salto al estado de ganar o perder y enciendo y apago el altavoz
infi:   ldr r0, =GPBASE
	ldr r1, [r0, #GPLEV0]
	@Compruebo si se ha pulsado el boton necesario para ganar o perder
	ands r4, r1, #0b1000
	beq pulsa
	@Compruebo si se ha pulsado el boton de cambiar dificultad
	ands r4, r1, #0b100
	beq difi
	@Compruebo si se ha ganad
	ldr r0, =ganar
	ldr r1, [r0]
	cmp r1, #1
	beq ganador
	@Compruebo si se ha pulsado el boton pero no ganado
	ldr r0, =pulsado
	ldr r1, [r0]
	cmp r1, #1
	beq perdedor
	@Enciendo y apago el altavoz, cuya nota cargo en r2 en fiq  y mando a espera
	ldr r0, =GPBASE
	ldr r3, =0b10000
	str r3, [r0, #GPSET0]
	bl espera
	str r3, [r0, #GPCLR0]
	bl espera
	b infi
	
	@Actualizo la variable pulsado a 1
pulsa:  ldr r0, =pulsado
	mov r1, #1
	str r1, [r0]
	b infi
	
	@Compruebo en que dificultad esta para cambiarla
difi:   ldr r0, =df
	ldr r1, [r0]
	cmp r1, #1
	beq camnormal
	ldr r0, =dn
	ldr r1, [r0]
	cmp r1, #1
	beq camdificil
	ldr r0, =dd
	ldr r1, [r0]
	cmp r1, #1
	beq camfacil
	b infi
	
	@Cambio a dificultad normal
camnormal:
	ldr r0, =df
	mov r1, #0
	str r1, [r0]
	ldr r0, =dn
	mov r1, #1
	str r1, [r0]
	b infi
	
	@Cambio a dificultad dificil
camdificil:
	ldr r0, =dn
	mov r1, #0
	str r1, [r0]
	ldr r0, =df
	mov r1, #1
	str r1, [r0]
	b infi

	@Cambio a dificultad facil
camfacil:
	ldr r0, =dd
	mov r1, #0
	str r1, [r0]
	ldr r0, =df
	mov r1, #1
	str r1, [r0]
	b infi
	
	@Cuando se gana, se llega aqui
ganador:
	@Actualizo variable para saltar irq
	ldr r4, =desactivado
	mov r3, #1
	str r3, [r4]
	ldr r4, =GPBASE
	@Apago leds
	ldr r3, =0b00001000010000100000111000000000
	str r3, [r4, #GPCLR0]
	@Cargo en r5 un led, en r1 una duracion, y en r3 el altavoz
	ldr r0, =STBASE
	ldr r5, =0b10000000000
	ldr r1, =500000
	ldr r3, =0b10000
bucle:  @Bucle de encender y apagar leds y altavoz, la nota lo haria igual que en el primer bucle
	str r5, [r4, #GPSET0]
	bl esperaled
	str r5, [r4, #GPCLR0]
	bl esperaled
	ldr r3, =0b10000
	str r3, [r4, #GPSET0]
	bl espera
	ldr r3, =0b10000
	str r3, [r4, #GPCLR0]
	bl espera
	b bucle 
	
	@Se llega cuando se pierde, unica diferencia el led que se enciende
perdedor:
	ldr r4, =desactivado
	mov r3, #1
	str r3, [r4]
	ldr r4, =GPBASE
	ldr r3, =0b00001000010000100000111000000000
	str r3, [r4, #GPCLR0]
	ldr r0, =STBASE
	ldr r5, =0b1000000000000000000000000000
	ldr r1, =500000
	ldr r3, =0b10000
bucle2:  
	str r5, [r4, #GPSET0]
	bl esperaled
	str r5, [r4, #GPCLR0]
	bl esperaled
	ldr r3, =0b10000
	str r3, [r4, #GPSET0]
	bl espera
	ldr r3, =0b10000
	str r3, [r4, #GPCLR0]
	bl espera
	b bucle2
	
	
	@Bucle que uso para los altavoces, donde r2 es la nota
espera: push {r0, r4, r5}
	ldr r0, =STBASE
	ldr r4, [r0, #STCLO]
	add r4, r2 
ret1:   ldr r5, [r0, #STCLO] 
	cmp r5, r4
	blo ret1
	pop {r0, r4, r5} 
	bx lr 
	
	@Bucle para espera leds, donde r1 es la espera entre encendido y apagado
esperaled: push {r0, r4, r5}
	ldr r0, =STBASE
	ldr r4, [r0, #STCLO]
	add r4, r1 
ret2:   ldr r5, [r0, #STCLO] 
	cmp r5, r4
	blo ret2
	pop {r0, r4, r5} 
	bx lr 


irq_handler: 
	push {r0, r1, r3, r4, r5}
	@Compruebo si me salgo irq
	ldr r0, =desactivado
	ldr r1, [r0]
	cmp r1, #1
	beq sali
	@Cargo direccion de contador y puertos, y apago leds
	ldr r0, =STBASE
	ldr r1, =GPBASE
	ldr r3, =0b00001000010000100000111000000000
	str r3, [r1, #GPCLR0]
	
	@Compruebo si se ha pulsado el boton
	ldr r5, =pulsado
	ldr r4, [r5]
	cmp r4, #1
	beq pulsad
	
	@Aqui voy avanzando por el array de leds, cargo la cuenta, a la cual le voy sumando hasta que llega 
	@al tamaño del array donde vuelvo a cargar el array para reiniciarlo
secuenc:ldr r4, =cuenta
	ldr r3, [r4]
	cmp r3, #11
	addne r3, #1
	moveq r3, #1
	ldreq r7, =secuen
	str r3, [r4]
	ldr r4, [r7], #4
	str r4, [r1, #GPSET0]
	b salir


	@Se llega aqui cuando pulsas el boton y se comprueba el led que esta encendido con que usa para ganar, si es igual se va a ganar
pulsad:	ldr r4, [r7], #0
	cmp r4, #0b0000000000000000001000000000
	bne salir
	
	@Si ganas se actualiza esta variable a 1
gan:    ldr r4, =ganar
	mov r1, #1
	str r1, [r4]
	
	@Para salir, donde actualizo el comparador con el tiempo dependiendo de la dificultad 
salir:	ldr r0, =STBASE
	mov r3, #0b0010
	str r3, [r0, #STCS]
	ldr r3, [r0, #STCLO]
	@Compruebo la dificultad y salto a donde tenga que ser
	ldr r1, =df
	ldr r4, [r1]
	cmp r4, #1
	beq facil2
	ldr r1, =dn
	ldr r4, [r1]
	cmp r4, #1
	beq normal2
	ldr r1, =dd
	ldr r4, [r1]
	cmp r4, #1
	beq dificil2
	
	@Aqui llego dependiendo de la dificultad y pongo el tiempo en un registro
facil2:  ldr r1, =facil
	ldr r4, [r1]
	b tiempo
	
normal2: ldr r1, =normal
	ldr r4, [r1]
	b tiempo	
dificil2:  ldr r1, =dificil
	ldr r4, [r1]
	@Añado el tiempo de antes al comparador
tiempo:	ldr r3, [r0, #STCLO]
	add r3, r4
	str r3, [r0, #STC1]
	b fuera
	@Para saltar irq, al comparador le sumo un numero muy grande y no hace nada mas
sali:	ldr r0, =STBASE
	mov r3, #0b0010
	str r3, [r0, #STCS]
	ldr r3, [r0, #STCLO]
	ldr r4, =0x50000000
	add r3, r4
	str r3, [r0, #STC1]

	@SALGO
fuera:  pop     {r0, r1, r3, r4, r5}          
        subs    pc, lr, #4     
	
	@Uso fiq para avanzar por el array de musica cada cierto tiempo para que suene bien
fiq_handler :
	@Compruebo si se ha ganado o perdido para saltar a la musica que correspoda
	ldr r8, =ganar
	ldr r9, [r8]
	cmp r9, #1
	beq hasganado
	ldr r8, =pulsado
	ldr r9, [r8]
	cmp r9, #1
	beq hasperdido
	
	
	@Se reproduce la musica mientras se juega, en r6 tengo en array ed la cancion y en r2 la nota
	ldr r9, =contador
	ldr r10, [r9]
	cmp r10, #41
	addne r10, r10, #1
	moveq r10, #1
	ldreq r6, =cancion
	str r10, [r9]
	ldr r2, [r6], #4
	b salirfi
	@Si ganas se reproduce esta musica
hasganado:
	@Cuando ganas, uso primera vez para cambiar el contador a 1 y cambiar el array de musica a la cancion que tiene que ser, luego se pone en 1 y no se corre mas
	ldr r8, =primeravez
	ldr r9, [r8]
	cmp r9, #0
	moveq r9, #1
	streq r9, [r8]
	ldreq r9, =contador
	moveq r10, #1
	streq r10, [r9]
	@Igual que arriba, se reproduce la cancion
	ldr r9, =contador
	ldr r10, [r9]
	cmp r10, #10
	addne r10, r10, #1
	moveq r10, #1
	ldreq r6, =canciong
	str r10, [r9]
	ldr r2, [r6], #4
	b salirfi
	
	@Lo mismo que ganado pero cuando pierdes
hasperdido:
	ldr r8, =primeravez
	ldr r9, [r8]
	cmp r9, #0
	moveq r9, #1
	streq r9, [r8]
	ldreq r9, =contador
	ldreq r10, [r9]
	moveq r10, #1
	streq r10, [r9]
	ldreq r6, =cancionp
	
	ldr r9, =contador
	ldr r10, [r9]
	cmp r10, #11
	addne r10, r10, #1
	moveq r10, #1
	ldreq r6, =cancionp
	str r10, [r9]
	ldr r2, [r6], #4
	
	
	@Salgo del fiq, actualizand oel contador con un valor que me conviene para que suene bien
	
salirfi:
	ldr r8, =0x50000
	ldr r9, =STBASE
	mov r10, # 0b1000
	str r10, [r9, # STCS ]
	ldr r10, [r9, # STCLO ]
	add r10, r8
	str r10, [r9, # STC3 ]
        subs    pc, lr, #4 
	