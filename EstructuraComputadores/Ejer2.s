	.set    GPBASE,   0x3F200000
	.set    GPFSEL0,  0x0
	.set    GPFSEL1,  0x04
	.set    GPFSEL2,  0x08
	.set    GPSET0,   0x1c
	.set    GPCLR0,   0x028
	.set    GPLEV0,   0x034
.text
	/* Configuro los leds como salida */
	ldr r0, =GPBASE
/* guia bits       xx999888777666555444333222111000*/
	ldr r1, =0b00000000001000000000000001000000
	str r1, [r0, #GPFSEL2]
	/* Los enciendo */
/* guia bits       xx999888777666555444333222111000*/
	ldr r1, =0b00001000010000000000000000000000
	str r1, [r0, #GPSET0]
	/* Compruebo si se pulsa un boton y hago el salto correspondiente */
tes:	
	ldr r1, [r0, #GPLEV0]
/* guia bits      xx999888777666555444333222111000*/
	ands	r2, r1, #0b00000000000000000000000000000100
	beq apa_der
	ands	r2, r1, #0b00000000000000000000000000001000
	beq apa_izq	
	b tes
/* Apago y enciendo los leds correspondientes */
apa_der:
/* guia bits       xx999888777666555444333222111000*/
	ldr r1, =0b00000000010000000000000000000000
	str r1, [r0, #GPSET0]
	ldr r1, =0b00001000000000000000000000000000
	str r1, [r0, #GPCLR0]
	b tes
apa_izq:
	ldr r1, =0b00001000000000000000000000000000
	str r1, [r0, #GPSET0]
	ldr r1, =0b00000000010000000000000000000000
	str r1, [r0, #GPCLR0]
	b tes
