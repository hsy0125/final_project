#include "ros/ros.h"
#include "std_msgs/Int16.h"

int main(int argc, char **argv)
{
    ros::init(argc, argv, "sensor2");
    ros::NodeHandle n;

    ros::Publisher sensor2_pub = n.advertise<std_msgs::Int16>("sensor_2", 1000);
    ros::Rate loop_rate(1);  // 1 Hz

    int count = 0;

    while (ros::ok())
    {
        std_msgs::Int16 msg;
        msg.data = count;

        sensor2_pub.publish(msg);
        ROS_INFO("Published sensor_2: %d", msg.data);

        count += 2;  // 2씩 증가

        loop_rate.sleep();
    }

    return 0;
}
