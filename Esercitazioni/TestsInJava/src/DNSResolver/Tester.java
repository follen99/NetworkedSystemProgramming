package DNSResolver;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @Project TestsInJava
 * @AUTHOR giulianoranauro on 02/02/22
 */
public class Tester {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress[] googleAddresses = InetAddress.getAllByName("www.google.com");
        System.out.println("Indirizzi per www.google.com");
        for (InetAddress address : googleAddresses)
            System.out.println(address.getHostAddress());

        InetAddress[] unisannioAddresses = InetAddress.getAllByName("www.unisannio.it");
        System.out.println("\nIndirizzi per www.unisannio.it");
        for (InetAddress address : unisannioAddresses)
            System.out.println(address.getHostAddress());
    }
}
