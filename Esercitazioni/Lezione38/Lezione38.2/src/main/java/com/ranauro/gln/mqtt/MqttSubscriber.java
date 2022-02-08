package com.ranauro.gln.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;

/**
 * @Project Lezione38.2
 * @AUTHOR giulianoranauro on 08/02/22
 */
public class MqttSubscriber {
    private MqttClient subscriber;
    private String brokerAddress;
    public MqttSubscriber(String addr){
        this.brokerAddress = addr;
    }

    public void subscribe() throws Exception{
        subscriber = new MqttClient(brokerAddress, "sub1");
        subscriber.connect();
        subscriber.subscribe("/nodes/+/sensors/+/samples", new Mylistener());
    }

}
