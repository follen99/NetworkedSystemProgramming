import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MQTTClientPub {
    private static MqttClient publisher;

    public static void main(String[] args) throws MqttException {
        String brokerAddress = "tcp://127.0.0.1:1883";   // indichiamo l'indirizzo di trasporto su cui è in ascolto il broker
        publisher = new MqttClient(brokerAddress, "pub");   // creiamo un client il secondo parametro è un id
        publisher.connect();

        // scriviamo sul broker (1) rappresenta la qualità del servizio che voglio usare; (false) ci dice se deve essere mantenuto dal broker o meno
        publisher.publish("/nodes/10/sensors/10/samples", "ciao1".getBytes(), 1, false);
    }
}
