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
	ADDEXC  0x18, irq_handler
        mrs r0, cpsr
        mov r0, #0b11010011 @Modo SVC, FIQ&IRQ desact
        msr spsr_cxsf, r0
        add r0, pc, #4
        msr ELR_hyp, r0
        eret
	mov     r0, #0b11010010   @ Modo IRQ, FIQ&IRQ desact
        msr     cpsr_c, r0
        mov     sp, #0x8000
        mov     r0, #0b11010011   @ Modo SVC, FIQ&IRQ desact
        msr     cpsr_c, r0
        mov     sp, #0x8000000
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
	ldr r0, =STBASE
	ldr r1, [r0, #STCLO]
	ldr r2, =200000
	add r1, r2
	str r1, [r0, #STC3]
	str r1, [r0, #STC1]
	ldr     r0, =INTBASE
        mov     r1, #0b1010
        str     r1, [r0, #INTENIRQ1]
        mov     r0, #0b01010011   @ Modo SVC, IRQ activo
        msr     cpsr_c, r0
infi:   b infi
irq_handler:
	push {r0, r1, r2, r3}
	ldr r0, =STBASE
	ldr r1, =GPBASE
	ldr r2, [r0, #STCS]
	ands r2, #0b0010
	bne buzzer
	ldr r3, =0b00001000010000100000111000000000
	str r3, [r1, #GPCLR0]
	ldr r2, =cuenta
	ldr r3, [r2]
	subs r3, #1
	moveq r3, #6
	str r3, [r2]
	ldr r3, [r2, +r3, LSL #2]
	str r3, [r1, #GPSET0]
	mov r3, #0b1000
	str r3, [r0, #STCS]
	ldr r3, [r0, #STCLO]
	ldr r2, =200000
	add r3, r2
	str r3, [r0, #STC3]
	ldr r3, [r0, #STCS]
	ands r3, #0b0010
	beq salir
buzzer: ldr r2, =onoff
	ldr r3, [r2]
	eors r3, #1
	str r3, [r2]
	ldr r3, =0b10000
	strne r3, [r1, #GPSET0]
	streq r3, [r1, #GPCLR0]
	mov r3, #0b0010
	str r3, [r0, #STCS]
        ldr r3, [r0, #STCLO]
	ldr r2, =1136
        add r3, r2
        str r3, [r0, #STC1]
	
salir:	pop     {r0, r1, r2, r3}          @ Recupero registros
        subs    pc, lr, #4        @ Salgo de la RTI
