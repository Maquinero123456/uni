function [motors,mpitch,mroll]=SSCthetasToMotors(scbconsts,thetas,pitch,roll)
% The inverse of SSCmotorToThetas()

    mpitch=pitch*180/pi*10;
    mroll=roll*180/pi*10;
    
    motors=[thetas(1)*180/pi/scbconsts.axis1resol,...                          
            (scbconsts.ang0shoulder-thetas(2))*180/pi/scbconsts.axis2resol,...
            (thetas(2)+thetas(3))*180/pi/scbconsts.axis3resol]; % this depends on two angles because in motor space it is measured w.r.t. the universal frame and not w.r.t. the previous robot link
        
end 