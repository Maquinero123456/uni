	.set    GPBASE,   0x3F200000
	.set    GPFSEL1,  0x04
	.set    GPSET0,   0x1c
	.set    GPCLR0,   0x028
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
	/* Configuro los leds como salida */
	ldr r4, =GPBASE
/* guia bits       xx999888777666555444333222111000*/
	ldr r5, =0b00000000001000000000000000000000
	str r5, [r4, #GPFSEL1]
/* guia bits     xx10987654321098765432109876543210*/
	ldr r5, =0b00000000000000100000000000000000
	ldr r0, =STBASE
	ldr r1, =1000000
	/* Secuencia basica de espera */
bucle:
	bl espera
	str r5, [r4, #GPSET0]
	bl espera
	str r5, [r4, #GPCLR0]
	b bucle 
espera: push {r4, r5}
	ldr r4, [r0, #STCLO]
	add r4, r1 
ret1:   ldr r5, [r0, #STCLO] 
	cmp r5, r4
	blo ret1
	pop {r4, r5} 
	bx lr 
	