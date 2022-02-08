import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MQTTClientSub {
    private static MqttClient subscriber;

    public static void main(String[] args) throws MqttException {
        String brokerAccess = "rcp://127.0.0.1:1883";
        subscriber = new MqttClient(brokerAccess, "sub1");
        subscriber.connect();

        /**
         * In questo caso dichiamo che il sottoscrittore è interessato a ricevere tutti i sample
         * da qualsiasi sensore e da qualsiasi nodo.
         *
         * Aggiungiamo un listener presente nella classe Sublistener
         * */
        subscriber.subscribe("/nodes/+/sensors/+/samples", new Sublistener());
    }
}
