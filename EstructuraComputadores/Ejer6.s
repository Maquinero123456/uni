	.set    GPBASE,   0x3F200000
	.set    GPFSEL0,  0x00
	.set    GPFSEL1,  0x04
	.set    GPSET0,   0x1c
	.set    GPCLR0,   0x028
	.set    GPLEV0,   0x034
	.set	STBASE,	  0x3F003000
	.set 	STCLO,	  0x04

.text
        mrs r0, cpsr
        mov r0, #0b11010011 @Modo SVC, FIQ&IRQ desact
        msr spsr_cxsf, r0
        add r0, pc, #4
        msr ELR_hyp, r0
        eret
	mov 	r0, #0b11010011
	msr	cpsr_c, r0
	mov 	sp, #0x8000000	@ Inicializ. pila en modo SVC
	ldr r4, =GPBASE
/* guia bits       xx999888777666555444333222111000*/
	ldr r5, =0b00001000000000000001000000000000
	str r5, [r4, #GPFSEL0]
/* guia bits       xx999888777666555444333222111000*/
	ldr r5, =0b00000000001000000000000000000000
	str r5, [r4, #GPFSEL1]
	ldr r0, =STBASE
	ldr r5, [r4, #GPLEV0]
	ldr r1, =0
	ldr r5, =0b00000000000000000000000000000000
/* guia bits            xx999888777666555444333222111000*/
tes:	ldr r5, [r4, #GPLEV0]
/* guia bits      xx999888777666555444333222111000*/
	ands	r6, r5, #0b00000000000000000000000000000100
	beq enc_der
	ands	r6, r5, #0b00000000000000000000000000001000
	beq enc_izq	
bucle:	bl espera
	str r5, [r4, #GPSET0]
	bl espera
	str r5, [r4, #GPCLR0]
	b tes
/* guia bits       xx999888777666555444333222111000*/
enc_der:ldr r5, =0b00000000000000000000001000000000
	str r5, [r4, #GPSET0]
	ldr r5, =0b00000000000000000000000000010000
	ldr r1, =1908
	b bucle
enc_izq:ldr r5, =0b00000000000000100000000000000000
	str r5, [r4, #GPSET0]
	ldr r5, =0b00000000000000000000000000010000
	ldr r1, =1278
	b bucle
espera: push {r4, r5}
	ldr r4, [r0, #STCLO]
	add r4, r1
ret1:   ldr r5, [r0, #STCLO]
	cmp r5, r4
	blo ret1
	pop {r4, r5}
	bx lr
	
