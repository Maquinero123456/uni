	.include "inter.inc"
.data
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
	/* Configuro todos los GPIO con leds como salida */
	ldr r0, =GPBASE
/* guia bits       xx999888777666555444333222111000*/
	ldr r1, =0b00001000000000000000000000000000
	str r1, [r0, #GPFSEL0]
/* guia bits       xx999888777666555444333222111000*/
	ldr r1, =0b00001000001000000000000000001001
	str r1, [r0, #GPFSEL1]
/* guia bits       xx999888777666555444333222111000*/
	ldr r1, =0b00001000001000000000000001000000
	str r1, [r0, #GPFSEL2]
	/* Configuro el timer como interrupcion */
	ldr r0, =STBASE
    ldr r1, [r0, #STCLO]
    add r1, #2
    str r1, [r0, #STC1]
	ldr     r0, =INTBASE
    mov     r1, #0b0010
    str     r1, [r0, #INTENIRQ1]
    mov     r0, #0b01010011   @ Modo SVC, IRQ activo
    msr     cpsr_c, r0

infi:   b infi
irq_handler: 
	push {r0, r1, r2, r3}
	ldr r0, =STBASE
	/* Apago todos los leds */
	ldr r1, =GPBASE
	ldr r3, =0b00001000010000100000111000000000
	str r3, [r1, #GPCLR0]
	/* Cargo el indice */
	ldr r2, =cuenta
	ldr r3, [r2]
	/* Le resto 1 al indice */
	subs r3, #1
	/* En caso de que llegue a 0, vuelvo a 6 */
	moveq r3, #6
	/* Avanzo por la secuencia*/
	str r3, [r2]
	ldr r3, [r2, +r3, LSL #2]
	str r3, [r1, #GPSET0]
	/* Reinicio la interrupcion por reloj y el comparador */
	mov r3, #0b0010
	str r3, [r0, #STCS]
	ldr r3, [r0, #STCLO]
	ldr r2, =0x61A80
	add r3, r2
	str r3, [r0, #STC1]
	/* Salgo */
	pop     {r0, r1, r2, r3}          
    subs    pc, lr, #4        
