	.include "inter.inc"
	onoff:  .word 0
	cuenta: .word 1
	secuen: .word 0b1000000000000000000000000000
		.word 0b0000010000000000000000000000
		.word 0b0000000000100000000000000000
		.word 0b0000000000000000100000000000
		.word 0b0000000000000000010000000000
		.word 0b0000000000000000001000000000
.text
	/* Cosa de raspberry pi 3 */
    mrs r0, cpsr
    mov r0, #0b11010011 @Modo SVC, FIQ&IRQ desact
    msr spsr_cxsf, r0
    add r0, pc, #4
    msr ELR_hyp, r0
    eret

	/* Inicializo vector de irq*/
	mov r0, #0 @Vector table base = 0
    ADDEXC 0x18, irq_handler

	/* Activar modo IRQ y SVC */
	mov     r0, #0b11010010   @ Modo IRQ, FIQ&IRQ desact
    msr     cpsr_c, r0
    mov     sp, #0x8000
    mov     r0, #0b11010011   @ Modo SVC, FIQ&IRQ desact
    msr     cpsr_c, r0
    mov     sp, #0x8000000
	/* Configuro GPIO leds y altovoz como salida */
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
	/* Add a los dos timers los tiempos deseados */
	ldr r0, =STBASE
	ldr r1, [r0, #STCLO]
	ldr r2, =200000
	add r1, r2
	str r1, [r0, #STC3]
	str r1, [r0, #STC1]
	/* Activo las interrupciones por C1 y C3 */
	ldr r0, =INTBASE
    mov r1, #0b1010
    str r1, [r0, #INTENIRQ1]
    mov r0, #0b01010011   @ Modo SVC, IRQ activo
    msr cpsr_c, r0
infi:   b infi

irq_handler:
	push {r0, r1, r2, r3}
	ldr r0, =STBASE
	ldr r1, =GPBASE
	/* Compruebo si la interrupcion es por C1 o C3 */
	ldr r2, [r0, #STCS]
	/* En caso de C1 saltamos a la etiqueta buzzer */
	ands r2, #0b0010
	bne buzzer
	/*
		LEDS
	 */
	/* Apago todos los leds */
	ldr r3, =0b00001000010000100000111000000000
	str r3, [r1, #GPCLR0]
	/* Cargo por donde iba en el indice y le resto 1 */
	ldr r2, =cuenta
	ldr r3, [r2]
	/* Si llego a 0, reinicio a 6 */
	subs r3, #1
	moveq r3, #6
	/* Avanzo en el array y enciendo el led */
	str r3, [r2]
	ldr r3, [r2, +r3, LSL #2]
	/* Reinicio la interrupcion por C3 */
	str r3, [r1, #GPSET0]
	mov r3, #0b1000
	str r3, [r0, #STCS]
	/* Actualizo el timer */
	ldr r3, [r0, #STCLO]
	ldr r2, =200000
	add r3, r2
	str r3, [r0, #STC3]
	/* TODO comprobar esto */
	ldr r3, [r0, #STCS]
	ands r3, #0b0010
	beq salir
	/*
		ALTAVOZ
	 */
buzzer: 
	/* Cargo onoff */
	ldr r2, =onoff
	ldr r3, [r2]
	/* Alterno el valor y lo guardo */
	eors r3, #1
	str r3, [r2]
	/* Si no es igual lo enciendo, en caso contrario lo enciendo */
	ldr r3, =0b10000
	strne r3, [r1, #GPSET0]
	streq r3, [r1, #GPCLR0]
	/* Reinicio la interrupcion y reinicio el timer con el valor actualizado */
	mov r3, #0b0010
	str r3, [r0, #STCS]
    ldr r3, [r0, #STCLO]
	ldr r2, =1136
    add r3, r2
    str r3, [r0, #STC1]
	/*Salgo */
salir:	
	pop     {r0, r1, r2, r3}          @ Recupero registros
    subs    pc, lr, #4        @ Salgo de la RTI
