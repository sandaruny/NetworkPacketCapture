/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkcapture;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.jpcap.capture.*;
import net.sourceforge.jpcap.net.*;


/**
 *
 * @author Sandaruwan
 */
public class NetworkCaptureSF {

 

    public static void main(String[] args) {
         PacketCapture m_pcap;
         String m_device;
        try {
            m_pcap = new PacketCapture();
            
            // Step 2:  Check for devices
            m_device = m_pcap.findDevice();
            
            // Step 3:  Open Device for Capturing (requires root)
            m_pcap.open(m_device, true);
            
            // Step 4:  Add a BPF Filter (see tcpdump documentation)
            
            System.out.println(""+m_device);
            
            
            // Step 5:  Register a Listener for Raw Packets
            m_pcap.addRawPacketListener(new RawPacketHandler());
            
            // Step 6:  Capture Data (max. PACKET_COUNT packets)
        
        } catch (CaptureDeviceNotFoundException ex) {
            Logger.getLogger(NetworkCaptureSF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CaptureDeviceOpenException ex) {
            Logger.getLogger(NetworkCaptureSF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
class RawPacketHandler implements RawPacketListener 
{
  private static int m_counter = 0;

  public void rawPacketArrived(RawPacket data) {
    m_counter++;
    System.out.println("Received packet (" + m_counter + ")");
  }
}