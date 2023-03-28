function scbconsts=VREPscorbotConsts()
% Return the constant values that define the scorbot robot
% see acl ref guide - parameter section and er V manual for some of these values

    scbconsts=struct('l0',0.19+0.14,...
                     'l1',0.015,...
                     'l2',0.2211,... 
                     'l3',0.2211,...
                     'minjoints',[-155 -35 -60 -130 -180],... % minimum values in degrees of each joint of the real robot
                     'maxjoints',[155 135 100 130 180],... % max values
                     'axis1resol',90/3831,... % parameter 33 of ACL, scorbot er-v (er-v plus are different)
                     'axis2resol',90/3065,... % parameter 34
                     'axis3resol',90/3065,... % parameter 35
                     'ang0shoulder',pi/4+pi/2,...
                     'lgripper',0.1431+0.1319 ... % from rotation axis to tip of the gripper
                     );
        
end
