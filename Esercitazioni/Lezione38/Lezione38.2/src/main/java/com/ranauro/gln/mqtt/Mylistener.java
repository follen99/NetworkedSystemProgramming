package com.ranauro.gln.mqtt;

import com.ranauro.gln.entity.Sample;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;

/**
 * @Project Lezione38.2
 * @AUTHOR giulianoranauro on 08/02/22
 */
public class Mylistener implements IMqttMessageListener {
    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("Un messaggio è arrivato: ");
        try {
            Sample ns = new SampleDecoder().decode(new String(mqttMessage.getPayload()));

            for (Session session : WebSocketEndpoint.session){
                session.getBasicRemote().sendObject(ns);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }
}
