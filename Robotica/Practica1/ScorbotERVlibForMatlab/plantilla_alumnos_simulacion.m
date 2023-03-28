
%Pieza inicial x = 0.2 y = -0.3250 z = 0.0350
clear;
close all;

s=Scorbot(Scorbot.MODEVREP);
s.home;
load('posiciones.mat');
res = s.move(p1, 1);
s.changeGripper(1);
res = s.changePosXYZ(p1, [p1.xyz(1), p1.xyz(2), p1.xyz(3)-700]);
res = s.move(res, 1);
s.changeGripper(0);
res = s.changePosXYZ(p1, [p1.xyz(1), p1.xyz(2), p1.xyz(3)+700]);
res = s.move(res, 1);
res = s.move(p2, 1);
res = s.changePosXYZ(p2, [p2.xyz(1)+50, p2.xyz(2), p2.xyz(3)-700]);
s.move(res, 1);
s.changeGripper(1);
%%bajar un pelin mas(riesgo de muerte)
s.move(p2, 1);
s.move(p1,1);