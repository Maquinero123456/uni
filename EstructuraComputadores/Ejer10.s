	.include "inter.inc"
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
	/*Configuro los GPIO 22 y 27 como salida y los enciendo*/
	ldr r0, =GPBASE
/* guia bits       xx999888777666555444333222111000*/
	ldr r1, =0b00000000001000000000000001000000
	str r1, [r0, #GPFSEL2]
/* guia bits       xx987654321098765432109876543210*/
	ldr r1, =0b00001000010000000000000000000000
	str r1, [r0, #GPSET0]
	/* Configuro los botones como interrupcion */
	mov r1, #0b1100	
	str r1,	[r0, #GPFEN0]	
	ldr r0, =INTBASE
	/* Los añado a IRQ y activo las interrupciones IRQ */
/*guia bits        10987654321098765432109876543210*/
	mov r1, #0b00000000000100000000000000000000		
	str r1,	[r0, #INTENIRQ2]
	mov r1,	#0b01010011	
	msr cpsr_c, r1
infi:   b infi

irq_handler:
	push {r0, r1}
	/* Apago los leds */
	ldr r0, =GPBASE
	ldr r1, =0b00001000010000000000000000000000
	str r1, [r0, #GPCLR0]
	/* Compruebo de que boton viene la interrupcion */
	ldr r1, [r0, #GPEDS0]
	ands r1, #0b00000000000000000000000000000100
	beq der
	bne izq
	/* Enciendo el led del boton derecho */
der:	
/* guia bits       xx987654321098765432109876543210*/
	ldr r1, =0b00001000000000000000000000000000
	str r1, [r0, #GPSET0]
	b salir
	/* Enciendo el led del boton izquierdo */
izq:
/* guia bits       xx987654321098765432109876543210*/	
	ldr r1, =0b00000000010000000000000000000000
	str r1, [r0, #GPSET0]
	/* Reinicio la interrupcion */
salir:	
	mov r1,	#0b1100
	str r1,	[r0, #GPEDS0]
	pop {r0, r1}
	subs pc, lr, #4
