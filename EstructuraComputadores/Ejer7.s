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
	
	ldr r0, =GPBASE
        /* Configuro GPIO9 como salida */
/* guia bits       xx999888777666555444333222111000*/
	ldr r1, =0b00001000000000000000000000000000
	str r1, [r0, #GPFSEL0]
	ldr     r0, =STBASE
	/* Sumo a C3 el tiempo para iniciar la interrupcion */
        ldr     r1, [r0, #STCLO]
        add     r1, #0x600000     
        str     r1, [r0, #STC3]
	ldr     r0, =INTBASE
	/* Le digo a irq que use C3 para las interrupciones y lo activo */
        mov     r1, #0b1000
        str     r1, [r0, #INTENIRQ1]
        mov     r0, #0b01010011   @ Modo SVC, IRQ activo
        msr     cpsr_c, r0
infi:   b infi
irq_handler:
	push {r0, r1}
	ldr r0, =GPBASE
/* guia bits       xx999888777666555444333222111000*/
	ldr r1, =0b00000000000000000000001000000000
	str r1, [r0, #GPSET0]
	pop {r0, r1}
	subs pc, lr, #4
	
