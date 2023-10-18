	.include "inter.inc"
.data
	onoff: .word 0
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

	/* Configuro GPIO9 como salida */
	ldr r0, =GPBASE
/* guia bits       xx999888777666555444333222111000*/
	ldr r1, =0b00001000000000000000000000000000
	str r1, [r0, #GPFSEL0]
	/* Add el tiempo al contador */
	ldr r0, =STBASE
    ldr r1, [r0, #STCLO]
	/* Medio segundo */
	ldr r2, =0x07A120
    add r1, r2 
    str r1, [r0, #STC1]
	/* Add el contador a la interrupcion y la activo */
	ldr r0, =INTBASE
    mov r1, #0b0010
    str r1, [r0, #INTENIRQ1]
    mov r0, #0b01010011   @ Modo SVC, IRQ activo
    msr cpsr_c, r0

infi:   b infi

irq_handler: 
	push {r0, r1, r2}
	/* Cargo el valor de onoff */
	ldr r1, =onoff
	ldr r2, [r1]
	/* Or entre onoff y 1, guardo el resultado en onoff intercambiandolo en cada ejecucion */
	eors r2, #1
	/* Lo guardo */
	str r2, [r1]
	ldr r0, =GPBASE
	ldr r1, =0b00000000000000000000001000000000
	/* Si el valor era 0 enciendo el led*/
	strne r1, [r0, #GPSET0]
	/* Si el valor era 1 lo apago */
	streq r1, [r0, #GPCLR0]
	/* Reactivo la interrupcion */
	ldr r0, =STBASE
	mov r1, #0b0010
	str r1, [r0, #STCS]
	/* Actualizo el reloj */
    ldr r1, [r0, #STCLO]
	ldr r2, =0x07A120
    add r1, r2 
    str r1, [r0, #STC1]
	/* Salgo */
	pop     {r0, r1, r2}          @ Recupero registros
    subs    pc, lr, #4        @ Salgo de la RTI
