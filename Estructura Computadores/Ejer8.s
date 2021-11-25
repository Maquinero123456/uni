	.include "inter.inc"
	onoff: .word 0
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
	ldr r1, =0b00001000000000000000000000000000
	str r1, [r0, #GPFSEL0]
	ldr r3, =0b00000000000000000000000000000001
	ldr r0, =STBASE
        ldr r1, [r0, #STCLO]
	/* Medio segundo */
	ldr r2, =0x07A120
        add r1, r2 
        str r1, [r0, #STC1]
	ldr     r0, =INTBASE
        mov     r1, #0b0010
        str     r1, [r0, #INTENIRQ1]
        mov     r0, #0b01010011   @ Modo SVC, IRQ activo
        msr     cpsr_c, r0

infi:   b infi
irq_handler: 
	push {r0, r1, r2}
	ldr r1, =onoff
	ldr r2, [r1]
	/* Or entre onoff y 1, guardo el resultado en onoff intercambiandolo en cada ejecucion */
	eors r2, #1
	str r2, [r1]
	ldr r0, =GPBASE
	ldr r1, =0b00000000000000000000001000000000
	strne r1, [r0, #GPSET0]
	streq r1, [r0, #GPCLR0]
	ldr r0, =STBASE
	mov r1, #0b0010
	str r1, [r0, #STCS]
        ldr r1, [r0, #STCLO]
	ldr r2, =0x07A120
        add r1, r2 
        str r1, [r0, #STC1]
	pop     {r0, r1, r2}          @ Recupero registros
        subs    pc, lr, #4        @ Salgo de la RTI
