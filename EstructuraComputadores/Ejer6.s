	.set    GPBASE,   0x3F200000
	.set    GPFSEL0,  0x00
	.set    GPFSEL1,  0x04
	.set    GPSET0,   0x1c
	.set    GPCLR0,   0x028
	.set    GPLEV0,   0x034
	.set	STBASE,	  0x3F003000
	.set 	STCLO,	  0x04

.text
     /* Cosa de raspberry pi 3 */
    mrs r0, cpsr
    mov r0, #0b11010011 @Modo SVC, FIQ&IRQ desact
    msr spsr_cxsf, r0
    add r0, pc, #4
    msr ELR_hyp, r0
    eret

	/* Activar modo SVC */
    mov     r0, #0b11010011   @ Modo SVC, FIQ&IRQ desact
    msr     cpsr_c, r0
    mov     sp, #0x8000000
	/* Configuro leds y altavoz como salida */
	ldr r4, =GPBASE
/* guia bits       xx999888777666555444333222111000*/
	ldr r5, =0b00001000000000000001000000000000
	str r5, [r4, #GPFSEL0]
/* guia bits       xx999888777666555444333222111000*/
	ldr r5, =0b00000000001000000000000000000000
	str r5, [r4, #GPFSEL1]
	ldr r0, =STBASE
/* guia bits            xx999888777666555444333222111000*/
	ldr r2, =0b00000000000000000000000000010000
	ldr r1, =0
	/* Compruebo que boton se ha pulsado y hago el salto correspondiente*/
bucle:	
	ldr r5, [r4, #GPLEV0]
/* guia bits      xx999888777666555444333222111000*/
	ands	r6, r5, #0b00000000000000000000000000000100
	beq enc_der
	ands	r6, r5, #0b00000000000000000000000000001000
	beq enc_izq	
	/* Secuencia basica de espera */
	bl espera
	str r2, [r4, #GPSET0]
	bl espera
	str r2, [r4, #GPCLR0]
	b tes

espera: 
	push {r4, r5}
	ldr r4, [r0, #STCLO]
	add r4, r1
ret1:   
	ldr r5, [r0, #STCLO]
	cmp r5, r4
	blo ret1
	pop {r4, r5}
	bx lr
/* Apago y enciendo los leds correspondientes
   Ademas de poner bien los tiempos para los altavoces */
enc_der:
/* guia bits       xx999888777666555444333222111000*/
	ldr r5, =0b00000000000000000000001000000000
	str r5, [r4, #GPSET0]
	ldr r5, =0b00000000000000100000000000000000
	str r5, [r4, #GPCLR0]
	ldr r1, =1908
	b bucle
enc_izq:
/* guia bits       xx999888777666555444333222111000*/
	ldr r5, =0b00000000000000100000000000000000
	str r5, [r4, #GPSET0]
	ldr r5, =0b00000000000000000000001000000000
	str r5, [r4, #GPCLR0]
	ldr r1, =1348
	b bucle


	
