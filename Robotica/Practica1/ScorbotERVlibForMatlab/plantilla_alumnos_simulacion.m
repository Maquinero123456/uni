%---------------------------------------------------
% AYUDA GENERAL: help Scorbot
% AYUDA FUNCIÓN CONCRETA: help Scorbot.nombrefunción
% GRABAR SÓLO LAS POSICIONES, NO EL OBJETO ROBOT
%---------------------------------------------------

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
s.changeGripper(0);

s.move(s.changePosXYZ(p2, [p2, p2.xyz(2), p2.xyz(3)+700]), 1);
s.move(p1);

% teach = 0;
% 
% %%abrir cerrar pinza:
% %  s.changeGripper(1); %%0 cerrar pinza, 1 abrirla
% %s.move(p,1) //0,1,2
% if (~teach)
% 	load('posiciones_conpieza.mat');
%     for i=1:3
%         res = s.move(aprox1,1);
%         s.changeGripper(1); %%0 cerrar pinza, 1 abrirla
%        
%     end
% else
% 	fprintf('----> Teach the robot where is the location for picking items and press Enter to finish.\n\n');
%     % Mover con pendant, y después pulsar Enter en la botonera para
%     % almacenar la posición.
%     % Tras guardar cada posición, salvar el fichero de posiciones
%     % ¡Sólo las posiciones, no el objeto robot!
% 	p1 = s.pendant();   % situar pieza para asegurarse de cogerla. Guardar x,y pieza
%     aprox1 = s.pendant(); % moverse en...
%     aprox2 = s.pendant(); % moverse en...
%     p2 = s.pendant();   % moverse en...
% end
% 
% %**************
% % START PROGRAM
% %**************
% 
% fprintf('Press any key to start picking-and-placing.\n');
% pause;
