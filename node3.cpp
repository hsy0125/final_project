#include "ros/ros.h"
#include "std_msgs/Int16.h"

int latest_cmd = 0;

void velCmdCallback(const std_msgs::Int16::ConstPtr& msg)
{
    latest_cmd = msg->data;
}

int main(int argc, char **argv)
{
    ros::init(argc, argv, "node3");
    ros::NodeHandle n;

    ros::Subscriber sub = n.subscribe("vel_cmd", 1000, velCmdCallback);

    ros::Publisher pub_feedback = n.advertise<std_msgs::Int16>("vel_feedback", 1000);
    ros::Publisher pub_feedback2 = n.advertise<std_msgs::Int16>("vel_feedback2", 1000);

    ros::Rate rate_main(10);  // 10Hz
    ros::Time last_feedback2 = ros::Time::now();

    while (ros::ok())
    {
        // vel_feedback = vel_cmd + 100
        std_msgs::Int16 feedback;
        feedback.data = latest_cmd + 100;
        pub_feedback.publish(feedback);
        ROS_INFO("Published vel_feedback: %d", feedback.data);

        // vel_feedback2 = vel_feedback / 10 (1Hz)
        if ((ros::Time::now() - last_feedback2).toSec() >= 1.0)
        {
            std_msgs::Int16 feedback2;
            feedback2.data = feedback.data / 10;
            pub_feedback2.publish(feedback2);
            ROS_INFO("Published vel_feedback2: %d", feedback2.data);
            last_feedback2 = ros::Time::now();
        }

        ros::spinOnce();
        rate_main.sleep();
    }

    return 0;
}
