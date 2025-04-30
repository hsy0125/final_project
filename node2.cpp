#include "ros/ros.h"
#include "std_msgs/Int16.h"

int sensor_1 = 0;
int sensor_2 = 0;
int vel_feedback2 = 0;

void sensor1Callback(const std_msgs::Int16::ConstPtr& msg)
{
    sensor_1 = msg->data;
}

void sensor2Callback(const std_msgs::Int16::ConstPtr& msg)
{
    sensor_2 = msg->data;
}

void feedback2Callback(const std_msgs::Int16::ConstPtr& msg)
{
    vel_feedback2 = msg->data;
}

int main(int argc, char **argv)
{
    ros::init(argc, argv, "node2");
    ros::NodeHandle n;

    // 구독자 설정
    ros::Subscriber sub1 = n.subscribe("sensor_1", 1000, sensor1Callback);
    ros::Subscriber sub2 = n.subscribe("sensor_2", 1000, sensor2Callback);
    ros::Subscriber sub_feedback2 = n.subscribe("vel_feedback2", 1000, feedback2Callback);

    // 퍼블리셔 설정
    ros::Publisher vel_cmd_pub = n.advertise<std_msgs::Int16>("vel_cmd", 1000);
    ros::Publisher feedback2_pub = n.advertise<std_msgs::Int16>("vel_feedback2_relay", 1000);

    ros::Rate rate_cmd(10); // 10Hz for vel_cmd
    ros::Rate rate_feedback2(1); // 1Hz for relay

    ros::Time last_feedback2 = ros::Time::now();

    while (ros::ok())
    {
        // vel_cmd 계산 및 전송 (10Hz)
        std_msgs::Int16 vel_cmd;
        vel_cmd.data = sensor_1 + sensor_2;
        vel_cmd_pub.publish(vel_cmd);
        ROS_INFO("Published vel_cmd: %d", vel_cmd.data);

        // vel_feedback2_relay 전송 (1Hz)
        if ((ros::Time::now() - last_feedback2).toSec() >= 1.0)
        {
            std_msgs::Int16 relay;
            relay.data = vel_feedback2;
            feedback2_pub.publish(relay);
            ROS_INFO("Relayed vel_feedback2: %d", relay.data);
            last_feedback2 = ros::Time::now();
        }

        ros::spinOnce();
        rate_cmd.sleep();
    }

    return 0;
}
