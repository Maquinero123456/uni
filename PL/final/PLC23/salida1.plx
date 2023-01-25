	$t0[0]= 1;
	$t0[1]= 2;
	$t0[2]= 3;
	$c_length=0;
	$t1=0;
L0:
	if ($t1<3) goto L1;
	goto L2;
L1:
	$t2=$t0[$t1];
	$t3=0;
L3:
	if ($t3==$c_length) goto L4;
	$t4=c[$t3];
	if ($t4==$t2) goto L5;
	$t3=$t3+1;
	goto L3;
L4:
	c[$c_length]=$t2;
	$c_length=$c_length+1;
L5:
	$t1=$t1+1;
	goto L0;
L2:
	$t5[0]=1;
	$t5[1]=3;
	$t5[2]=5;
L6:
	if ($t6<3) goto L7;
	goto L8;
L7:
	$t7=$t5[$t6];
	$t8=0;
L9:
	if ($c_length==$t8) goto L11;
	$t10=c[$t8];
	if ($t10==$t7) goto L12;
	$t8=$t8+1;
	goto L9;
L12:
	$c_length=$c_length-1;
L10:
	if ($c_length==$t8) goto L11;
	$t9=$t8;
	$t8=$t8+1;
	$t10=c[$t8];
	c[$t9]=$t10;
	goto L10;
L11:
	$t6=$t6+1;
	goto L6;
L8:
