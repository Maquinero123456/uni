function odometry()
    
    x0 = 0;
    y0 = 0;
    theta0 = 0;
    t0 = 0;
    rotl0 = 0;
    rotr0 = 0;
    
    fid = fopen('C:\Users\alumnopr13\Documents\EVLEGO\mylog.txt');
    tline = fgetl(fid);
    while ischar(tline)
       tline = split(tline);
       disp(calcOdometry(x0, y0, theta0, t0, rotl0, rotr0, tline(3), tline(1),tline(2)));
       tline = fgetl(fid);
    end

end

function [x1,y1,theta1]=calcOdometry(x0,y0,theta0,t0,rotl0,rotr0,t1,rotl1,rotr1)
    R=2.75;
    D=9.6;
    if(rotl1>=359)
        rotl1=rotl1-360*idivide(rotl1, 360);
    end
    if(rotr1>=359)
        rotr1=rotr1-360*idivide(rotr1, 360);
    end
    if(t1-t0==0)
        x1=x0;
        y1=y0;
        theta1=theta0;
    else
        x1 = ((rotl1*R+rotr1*R)/2)*cos(theta0);
        y1 = ((rotl1*R+rotr1*R)/2)*sin(theta0);
        theta1 = (rotr1*R-rotl1*R)/D;
    end
end


