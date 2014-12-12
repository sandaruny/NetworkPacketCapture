/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkcapture;

/**
 *
 * @author Sandaruwan
 */
import java.io.IOException;
import jpcap.*;
import jpcap.packet.*;

public class NetworkCapture {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
  //      System.load("C:/JAVA/jnetpcap-1.3.0-1.win32/jnetpcap-1.3.0/jpcap.dll");
        NetworkInterface[] devices = JpcapCaptor.getDeviceList();

        //for each network interface
        for (int i = 0; i < devices.length; i++) {
            //print out its name and description
            System.out.println(i + ": " + devices[i].name + "(" + devices[i].description + ")");

            //print out its datalink name and description
            System.out.println(" datalink: " + devices[i].datalink_name + "(" + devices[i].datalink_description + ")");

            //print out its MAC address
            System.out.print(" MAC address:");
            for (byte b : devices[i].mac_address) {
                System.out.print(Integer.toHexString(b & 0xff) + ":");
            }
            System.out.println();

            //print out its IP address, subnet mask and broadcast address
            for (NetworkInterfaceAddress a : devices[i].addresses) {
                System.out.println(" address:" + a.address + " " + a.subnet + " " + a.broadcast);
            }

//            while (true) {
//                Packet packet = captor.getPacket();
//                TCPPacket tcp = (TCPPacket) packet;
//                if (tcp == null) {
//                    break;
//                }
//                TCPPacket tcppacl = (TCPPacket) packet;
//                if (tcppacl.psh) {
//                    System.out.println("Packet: " + tcppacl.dst_ip);
//                }
//            }
        }

        JpcapCaptor captor = JpcapCaptor.openDevice(devices[1], 65535, false, 20);
        //   captor.setFilter("tcp", true);

//call processPacket() to let Jpcap call PacketPrinter.receivePacket() for every packet capture.
        for (int j = 0; j < 10; j++) {
            //capture a single packet and print it out
           // captor.loopPacket(1, new PacketPrinter());
        }
        //       captor.loopPacket(10, new PacketPrinter());

        captor.close();
      
    }
}

class PacketPrinter implements PacketReceiver {

    //this method is called every time Jpcap captures a packet
    @Override
    public void receivePacket(Packet packet) {

        System.out.println("Packet " + packet);
//        if (packet instanceof TCPPacket) {
//            TCPPacket p = (TCPPacket) packet;
//            p.dst_ip.getAddress();
//            System.out.println(p.dst_ip.getHostAddress());
//        } else if (packet instanceof UDPPacket) {
//            UDPPacket p = (UDPPacket) packet;
//            p.dst_ip.getAddress();
//            System.out.println(p.dst_ip.getHostAddress());
//        }
    }
}
