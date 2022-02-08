package com.ranauro.gln.Lezione382;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * @Project Lezione38.2
 * @AUTHOR giulianoranauro on 08/02/22
 */
public class MQTTClientPub {
    private static MqttClient publisher;

    public static void main(String[] args) throws MqttException, InterruptedException {
        String brokerAddress = "tcp://127.0.0.1:1883";
        publisher =  new MqttClient(brokerAddress,"pub");
        publisher.connect();
        while (true){
            Thread.sleep(1000);
            /**
             * Questa stringa contiene in Json dei dati riconosciuti come un oggetto di tipo sample.
             * Stiamo inviando due campi:
             * attribute : temperature
             * simplevalue : temperatura (random)
             * */
            publisher.publish("/nodes/10/sensors/10/samples", new String("{ \"attribute\" : \"temperature\", \"simplevalue\" : \" "
                    + (Math.random() * 2 +20)).getBytes(),
                    1,
                    false);
        }
    }
}
