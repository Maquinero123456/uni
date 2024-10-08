	.include "inter.inc"
	onoff:  .word 0
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
	/* Configuro los leds amarillos como salida */
	ldr r0, =GPBASE
/* guia bits       xx999888777666555444333222111000*/
	ldr r1, =0b00000000001000000000000000001000
	str r1, [r0, #GPFSEL1]
	/* Activo las interrupciones por los dos botones */
	ldr r1, =0b1100
	str r1, [r0, #GPFEN0]
	ldr r0,	=INTBASE
	mov r1,	#0b00000000000100000000000000000000	
	str r1,	[r0, #INTENIRQ2]
	/* Activo IRQ y SVC */
	mov r1,	#0b01010011	
	msr cpsr_c, r1
	
bucle: 	
	ldr r0, =STBASE
	ldr r1, =GPBASE
	ldr r2, =500000
	ldr r3, =onoff
/* guia bits     xx10987654321098765432109876543210*/
	ldr r5, =0b00000000000000100000100000000000
	/* Cargo si toca encender o apagar */
	ldr r4, [r3]
	cmp r4, #0
	beq enc
	bne bucle
	/* Rutina basica de parpadeo */
enc:	
	bl espera
	str r5, [r1, #GPSET0]
	bl espera
	str r5, [r1, #GPCLR0]
	b bucle
	
espera: 
	push {r4, r5} 
	ldr r4, [r0, #STCLO] 
	add r4, r2 
ret1: 
	ldr r5, [r0, #STCLO] 
	cmp r5, r4 
	blo ret1 
	pop {r4, r5} 
	bx lr
	
irq_handler:
	push {r0, r1, r2, r3}
	ldr r2, =onoff
	ldr r0, =GPBASE
	/* Compruebo de donde viene la interrupcion 
	   y hago el salto correspodiente */
	ldr r1, [r0, #GPEDS0]
	ands r1, #0b100
	beq p2
	bne p3
	/* Actualizo el valor dependiendo del boton */
p2:    	
	mov r3, #0
	str r3, [r2]
	b salir

p3:     
	mov r3, #1
	str r3, [r2]
	/* Reinicio la interrupcion y salgo */
salir:  
	ldr r1, =0b1100
	str r1, [r0, #GPEDS0]
	pop {r0, r1, r2, r3}
	subs pc, lr, #4




