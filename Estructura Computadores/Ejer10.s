	.include "inter.inc"
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
	ldr r1, =0b00000000001000000000000001000000
	str r1, [r0, #GPFSEL2]
	ldr r1, =0b00000000000000000000000000000000
	str r1, [r0, #GPFSEL0]
/* guia bits       xx987654321098765432109876543210*/
	ldr r1, =0b00001000010000000000000000000000
	str r1, [r0, #GPSET0]
	mov r1, #0b00000000000000000000000000001100	
	str r1,	[r0, #GPFEN0]	
	ldr r0, =INTBASE
	mov r1, #0b00000000000110000000000000000000	
/*guia bits        10987654321098765432109876543210*/	
	str r1,	[r0, #INTENIRQ2]
	mov r1,	#0b01010011	
	msr cpsr_c, r1
infi:   b infi
irq_handler:
	push {r0, r1}
	ldr r0, =GPBASE
	ldr r1, =0b00001000010000000000000000000000
	str r1, [r0, #GPCLR0]
	ldr r1, [r0, #GPEDS0]
	ands r1, #0b00000000000000000000000000000100
	beq der
	bne izq
/* guia bits       xx987654321098765432109876543210*/
der:	ldr r1, =0b00001000000000000000000000000000
	str r1, [r0, #GPSET0]
	b salir
/* guia bits       xx987654321098765432109876543210*/
izq:	ldr r1, =0b00000000010000000000000000000000
	str r1, [r0, #GPSET0]
salir:	mov r1,	#0b1100
	str r1,	[r0, #GPEDS0]
	pop {r0, r1}
	subs pc, lr, #4
