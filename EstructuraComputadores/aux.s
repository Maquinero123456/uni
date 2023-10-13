	.include "inter.inc"
	onoff:  .word 0
	cuenta: .word 1
	cuenta2: .word 1
	secuen2: 
		.word 0b1000000000000000000000000000
		.word 0b0000010000000000000000000000
		.word 0b0000000000100000000000000000
		.word 0b0000000000000000100000000000
		.word 0b0000000000000000010000000000
	secuen :
		.word 1275 @ Retardo para nota 25
		.word 1136 @ Retardo para nota 24
		.word 1275 @ Retardo para nota 23
		.word 1012 @ Retardo para nota 22
		.word 956 @ Retardo para nota 21
		.word 956 @ Retardo para nota 20
		.word 1515 @ Retardo para nota 19
		.word 1351 @ Retardo para nota 18
		.word 1275 @ Retardo para nota 17
		.word 1012 @ Retardo para nota 16
		.word 852 @ Retardo para nota 15
		.word 1706 @ Retardo para nota 14
		.word 1706 @ Retardo para nota 13
		.word 1275 @ Retardo para nota 12
		.word 1136 @ Retardo para nota 11
		.word 1706 @ Retardo para nota 10
		.word 1515 @ Retardo para nota 9
		.word 1706 @ Retardo para nota 8
		.word 1706 @ Retardo para nota 7
		.word 1351 @ Retardo para nota 6
		.word 1275 @ Retardo para nota 5
		.word 1706 @ Retardo para nota 4
		.word 1515 @ Retardo para nota 3
		.word 1706 @ Retardo para nota 2
		.word 1706 @ Retardo para nota 1
	
.text
	/* Cosa de raspberry pi 3 */
    mrs r0, cpsr
    mov r0, #0b11010011 @Modo SVC, FIQ&IRQ desact
    msr spsr_cxsf, r0
    add r0, pc, #4
    msr ELR_hyp, r0
    eret

	/* Inicializo vector de irq y fiq*/
	ADDEXC 0x18, irq_handler
	ADDEXC 0x1c, fiq_handler
	/* Activar modo FIQ, IRQ y SVC */
	mov r0,	#0b11010001	@ Modo FIQ
	msr cpsr_c, r0	
	mov sp,	#0x4000
	mov r0, #0b11010010   @ Modo IRQ, FIQ&IRQ desact
    msr cpsr_c, r0
	mov sp, #0x8000	
    mov r0, #0b11010011   @ Modo SVC, FIQ&IRQ desact
    msr cpsr_c, r0
    mov sp, #0x8000000

	/* Configuro leds y altavoz como salida */
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
	/* Add un valor base al reloj para que se activen las interrupciones */
	ldr r0, =STBASE
    ldr r1, [r0, #STCLO]
    add r1, #2
    str r1, [r0, #STC3]
    str r1, [r0, #STC1]
	/* Add C3 a IRQ */
	ldr r0, =INTBASE
    mov r1, #0b1000
    str r1, [r0, #INTENIRQ1]
	/* Add C1 a FIQ */
	mov r1, #0b10000001
	str r1, [r0, #INTFIQCON]
    mov r2, #0b00010011   @ Modo SVC, IRQ y FIQ activo
    msr cpsr_c, r2

tes:	
    ldr r0, =GPBASE
	ldr r1, [r0, #GPLEV0]
/* guia bits      	   xx999888777666555444333222111000*/
	ands	r2, r1, #0b00000000000000000000000000000100
	beq activ
	ands	r2, r1, #0b00000000000000000000000000001000
	beq nacti
	b tes
    /* Activo el modo IRQ, FIQ y SVC */
activ:  
    mov r2, #0b00010011   @ Modo SVC, IRQ y FIQ activo
    msr cpsr_c, r2
    /* Lo desactivo */
nacti: 	
    mov r2, #0b11010011    @ Modo SVC, IRQ y FIQ no activo
    msr cpsr_c, r2

irq_handler: 
	push {r0, r1, r2, r3}
	/* Apago todos los leds */
	ldr r0, =STBASE
	ldr r1, =GPBASE
	ldr r3, =0b00001000010000100000111000000000
	str r3, [r1, #GPCLR0]
	/* Cargo la secuencia y le quito 1
	   Si llega a 0 se reinicia */
	ldr r2, =cuenta2
	ldr r3, [r2]
	subs r3, #1
	moveq r3, #6
	/* Cargo el valor y enciendo el led */
	str r3, [r2]
	ldr r2, =secuen2
	ldr r3, [r2, +r3, LSL #2]
	str r3, [r1, #GPSET0]
	/* Reinicio la interrupcion y el timer */
	mov r3, #0b1000
	str r3, [r0, #STCS]
	ldr r3, [r0, #STCLO]
	ldr r2, =500000
	add r3, r2
	str r3, [r0, #STC3]
	/* Salgo */
	pop     {r0, r1, r2, r3}          @ Recupero registros
    subs    pc, lr, #4        @ Salgo de la RTI

fiq_handler :
	ldr r8, =GPBASE
	ldr r9, =onoff
	ldr r10, [r9]
	eors r10, #1
	str r10, [r9]
	/* Cargo la secuencia*/
	ldr r9, =cuenta2
	ldr r10, [r9]
	bne noSumar
	/* Si no toca avanzar en el array no lo hago */
	subs r10, #1
	moveq r10, #25
	str r10, [r9]
	/* Cargo el valor */
noSumar:
	ldr r9, =secuen
	ldr r12, [r9, +r10, LSL #2]
	str r10, [r8, #GPSET0]
	mov r10, #0b10000
	streq r10, [r8, #GPSET0 ]
	strne r10, [r8, #GPCLR0 ]
	/* Reinicio la interrupcion */
	ldr r8, =STBASE
	mov r10, #0b0010
	str r10, [r8, #STCS ]
	ldr r10, [r8, #STCLO ]
	add r10, r12
	str r10, [r8, #STC1 ]
    subs pc, lr, #4 
