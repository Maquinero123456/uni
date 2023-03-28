#include "ros/ros.h"
#include "std_msgs/String.h"
#include "geometry_msgs/Twist.h"
#include "geometry_msgs/Pose2D.h"
#include "turtlesim/Pose.h"
#include <bits/stdc++.h>
#include <sstream>
#include <iostream>
#include <fstream>
#include <string>

using namespace std;

geometry_msgs::Twist velCommand;
geometry_msgs::Pose2D current;
geometry_msgs::Pose2D desired;

void chatterCallback(const turtlesim::PoseConstPtr &currentPose)
{
	current.x = currentPose->x;
	current.y = currentPose->y;
	current.theta = currentPose->theta;
  	ROS_INFO("X: [%f]", current.x);
	ROS_INFO("Y: [%f]", current.y);
	ROS_INFO("THETA: [%f]", current.theta);
}

int main(int argc, char **argv){
	ros::init(argc, argv, "listener");
	ros::NodeHandle n;

	ros::Publisher chatter_pub = n.advertise<geometry_msgs::Twist>("turtle1/cmd_vel", 1000);
	ros::Rate loop_rate(10);	

	ros::Subscriber sub = n.subscribe("turtle1/pose", 1000, chatterCallback);
	int count = 0;
	while(ros::ok()){
		fstream file;
		file.open("/home/viki/practica4/src/practica4/velocidades.txt", ios::in);
		if(file.is_open()){
			string line;
			while (getline(file, line, ';')){
				string arr[6];
				stringstream ss(line);
				string word;
				int i = 0;
				while(ss >> word) {
					arr[i]=word;
					i++;				
				}
				velCommand.linear.x = std::stod(arr[0]);
				velCommand.linear.y = std::stod(arr[1]);
				velCommand.linear.z = std::stod(arr[2]);
				velCommand.angular.x = std::stod(arr[3]);
				velCommand.angular.y = std::stod(arr[4]);
				velCommand.angular.z = std::stod(arr[5]);
				chatter_pub.publish(velCommand);
			}		
		}
		ros::spinOnce();
		loop_rate.sleep();
		++count;
	}
	
	return 0;
}
