	.set    GPBASE,   0x3F200000
	.set    GPFSEL1,  0x04
	.set    GPSET0,   0x1c
	.set    GPCLR0,   0x028
.text
	/* Configuro el led como salida */
	ldr r0, =GPBASE
/* guia bits       xx999888777666555444333222111000*/
	ldr r1, =0b00000000001000000000000000000000
	str r1, [r0, #GPFSEL1]
	/* Hago que se apague y encienda en bucle */
infi:
/* guia bits       xx987654321098765432109876543210*/
	ldr r1, =0b00000000000000100000000000000000
	str r1, [r0, #GPSET0]
	ldr r1, =0b00000000000000100000000000000000
	str r1, [r0, #GPCLR0]
    b infi
	
