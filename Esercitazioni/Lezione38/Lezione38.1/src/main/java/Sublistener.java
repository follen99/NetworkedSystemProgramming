import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;


/**
 * The type Sublistener.
 * Il listener è un'istanza della classe Sublistener che implementa l'interfaccia IMqttMessageListener
 * che mette a disposizione il metodo messageArrived che dobbiamo implementare.
 * Questo metodo viene eseguito quando deve essere elaborato il messaggio ricevuto.
 */
public class Sublistener implements IMqttMessageListener {

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("Un messaggio è arrivato: " + mqttMessage);
    }
}
