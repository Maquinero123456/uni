.include "inter.inc"
cuenta: .word 0
secuen:
	.word 0b0000010000000000000000000000
	.word 0b0000000000100000000000000000
	.word 0b0000000000000000100000000000
	.word 0b0000000000000000010000000000
	.word 0b0000000000000000001000000000
.text
	ADDEXC 0x18, irq_handler
 	mrs r0,cpsr
 	mov r0, #0b11010011 @ Modo SVC, FIQ&IRQ desact
 	msr spsr_cxsf,r0
 	add r0,pc,#4
 	msr ELR_hyp,r0
 	eret
	mov r0, #0b11010010 
	msr cpsr_c, r0 
	mov sp, #0x8000
	mov 	r0, #0b11010011
	msr	cpsr_c, r0
	mov 	sp, #0x8000000	@ Inicializ. pila en modo SVC
	
	/*Configuro puertos de entrada y salida*/
	ldr r0,=GPBASE 
	ldr r1,=0b00001000000000000001000000010000
	str r1,[r0,#GPFSEL0] 
	ldr r1,=0b00000000001000000000000000001001 
	str r1, [r0,#GPFSEL1] 
	ldr r1,=0b00000000001000000000000001000000 
	str r1, [r0,#GPFSEL2]
	
	/*Apago los leds, enciendo el verde y espero un poco*/
inicio:
	ldr r1, =0b00001000010000100000111000000000
	str r1, [r0, #GPCLR0]
	ldr r1, =0b00001000000000000000000000000000
	str r1, [r0, #GPSET0]
	bl espera500
	
	/*Bucle, hasta que no se pulse el boton 1 no empieza el juego*/
start1:
	ldr r1, [r0, #GPLEV0]
	ands r2, r1, #0b00000000000000000000000000001000
	bne start1

	/*Inicio interrupcion 5 segundos perder*/
	ldr r2,	=STBASE		
	mov	 r1,	#0b0010		@C1	
	str r1,[r2,#STCS]
	ldr r2, =STBASE	
	ldr r1,	[r2,	#STCLO]
	ldr r3, =10000000
	add r1,	r1, r3					@y	microseconds
	str r1, [r2,	#STC1]
	ldr r2,=INTBASE	
	mov r1,	#0b0010	
	str r1, [r2,#INTENIRQ1]	
	mov	r1, #0b01010011	
	msr cpsr_c, r1
	b boton1
	
	/*Bucle normal, si pulsa boton 1 se enciende el siguiente led, si pulsa boton 2 se enciende led rojo y si llega a la ultima posicion el array gana*/
poll:
	ldr r2, =cuenta
	ldr r3, [r2]
	cmp r3, #5
	beq ganar
	ldr r1, [r0, #GPLEV0]
	ands r2, r1, #0b00000000000000000000000000000100
	beq boton2
	ands r2, r1, #0b00000000000000000000000000001000
	beq boton1
	b  poll
	
/*Apago el led verde y enciendo el rojo*/
boton2:
	ldr r1, =0b00001000000000000000000000000000
	str r1, [r0, #GPCLR0]
	ldr r1, =0b00000000000000000000001000000000
	str r1, [r0, #GPSET0]
	ldr r2, =STBASE
	ldr r3, =1000000
	ldr r4, [r2, #STCLO]
	add r4, r3 
infi: ldr r1, [r0, #GPLEV0]
	ands r1, #0b00000000000000000000000000001000
	beq perder
	ldr r5, [r2, #STCLO] 
	cmp r5, r4
	blo infi
salirboton2:
	ldr r1, =0b00000000000000000000001000000000
	str r1, [r0, #GPCLR0]
	ldr r1, =0b00001000000000000000000000000000
	str r1, [r0, #GPSET0]
	b poll
	
/*Apaga los leds del array, enciende el siguiente, +1 a la pos y espera 400ms para volver al bucle normal*/	
boton1:
	ldr r3, =0b00000000010000100000110000000000
	str r3, [r0, #GPCLR0]
	ldr r2, =cuenta
	ldr r3, [r2]
	add r3, #1
	str r3, [r2]
	ldr r3, [r2, +r3, LSL #2]
	str r3, [r0, #GPSET0]
pulsado:	bl espera200
	b poll
	
/*Espera*/	
espera200: push {r0, r1, r4, r5}
	ldr r0, =STBASE
	ldr r1, =400000
	ldr r4, [r0, #STCLO]
	add r4, r1 
ret1:   ldr r5, [r0, #STCLO] 
	cmp r5, r4
	blo ret1
	pop {r0, r1, r4, r5} 
	bx lr 

espera500: push {r0, r1, r4, r5}
	ldr r0, =STBASE
	ldr r1, =500000
	ldr r4, [r0, #STCLO]
	add r4, r1 
ret2:   ldr r5, [r0, #STCLO] 
	cmp r5, r4
	blo ret2
	pop {r0, r1, r4, r5} 
	bx lr
	
/*Bucle ganar, parpadean los leds y si se pulsa boton uno se resetea*/	
ganar:
	mov	r1, #0b11010011	
	msr cpsr_c, r1
	ldr r1, =0b00001000010000100000111000000000
	str r1, [r0, #GPSET0]
	bl espera500
	str r1, [r0, #GPCLR0]
	bl espera500
	ldr r1, [r0, #GPLEV0]
	ands r2, r1, #0b00000000000000000000000000000100
	beq reset
	b ganar

/*Reseteamos la pos del array y volvemos al inicio*/
reset:
	ldr r2, =cuenta
	ldr r3, =0
	str r3, [r2]
	b inicio

perder:
	mov	r1, #0b11010011	
	msr cpsr_c, r1 
	ldr r1, =0b00001000010000100000111000000000
	str r1, [r0, #GPSET0]
	ldr r1, =0b00000000000000000000000000010000
	str r1, [r0, #GPSET0]
	bl notacont
	str r1, [r0, #GPCLR0]
	bl notacont
	ldr r1, [r0, #GPLEV0]
	ands r2, r1, #0b00000000000000000000000000000100
	beq reset
	b perder


notacont:
	push {r0, r1, r4, r5}
	ldr r0, =STBASE
	ldr r1, =390
	ldr r4, [r0, #STCLO]
	add r4, r1 
ret3:   ldr r5, [r0, #STCLO] 
	cmp r5, r4
	blo ret3
	pop {r0, r1, r4, r5} 
	bx lr 



	
pitido2:
	push {r0, r1, r4, r5}
	ldr r0, =STBASE
	ldr r1, =390
	ldr r4, [r0, #STCLO]
	add r4, r1 
ret4:   ldr r5, [r0, #STCLO] 
	cmp r5, r4
	blo ret4
	pop {r0, r1, r4, r5} 
	bx lr 

	
irq_handler:
	b perder
