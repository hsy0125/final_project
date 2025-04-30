#include "ros/ros.h"
#include "std_msgs/Int16.h"

int main(int argc, char **argv)
{
    ros::init(argc, argv, "sensor1");
    ros::NodeHandle n;

    ros::Publisher sensor1_pub = n.advertise<std_msgs::Int16>("sensor_1", 1000);
    ros::Rate loop_rate(10);  // 10 Hz

    while (ros::ok())
    {
        std_msgs::Int16 msg;
        msg.data = 100;

        sensor1_pub.publish(msg);
        ROS_INFO("Published sensor_1: %d", msg.data);

        loop_rate.sleep();
    }

    return 0;
}
