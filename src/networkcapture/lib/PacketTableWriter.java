/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkcapture.lib;

import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import jpcap.PacketReceiver;
import jpcap.packet.ARPPacket;
import jpcap.packet.ICMPPacket;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.packet.UDPPacket;

/**
 *
 * @author Sandaruwan
 */
public class PacketTableWriter implements PacketReceiver {

    private DefaultTableModel dtm;
    private int colCount;

    public PacketTableWriter(DefaultTableModel dtm, JComboBox jcb) {
        this.dtm = dtm;
        colCount = dtm.getColumnCount();
    }

    public DefaultTableModel getDtm() {
        return dtm;
    }

    public void setDtm(DefaultTableModel dtm) {
        colCount = dtm.getColumnCount();
        this.dtm = dtm;
    }

    @Override
    public void receivePacket(Packet packet) {
        if (packet instanceof TCPPacket) {
            TCPPacket p = (TCPPacket) packet;
            String ptcl = "TCP";
            
            String data = new String(p.data);
            System.out.println(data);
            if(data.contains("HTTP")){
                ptcl = "HTTP";
            }else if(data.contains("HTTPS")){
                ptcl = "HTTPS";
            }else if(data.contains("SMTP")){
                ptcl = "SMTP";
            }else if(data.contains("SSH")){
                ptcl = "SSH";
            }else if(data.contains("FTP")){
                ptcl = "FTP";
            }
                    
            dtm.addRow(new String[]{dtm.getRowCount()+1+"",p.src_ip.getHostAddress(),
                p.dst_ip.getHostAddress(),
                p.src_port+"", p.dst_port+"", ptcl,p.length+""
            });
            

        } else if (packet instanceof UDPPacket) {
            UDPPacket p = (UDPPacket) packet;
         
            dtm.addRow(new String[]{dtm.getRowCount()+1+"",p.src_ip.getHostAddress(),
                p.dst_ip.getHostAddress(),
                p.src_port+"", p.dst_port+"", "UDP",p.len+""
            });
        } else if (packet instanceof ICMPPacket) {
            ICMPPacket p = (ICMPPacket) packet;
            
            dtm.addRow(new String[]{dtm.getRowCount()+1+"",p.src_ip.getHostAddress(),
                p.dst_ip.getHostAddress(),
                "", "", "ICMP",p.len+""
            });
        } else if (packet instanceof ARPPacket) {
            ARPPacket p = (ARPPacket) packet;
            
            dtm.addRow(new String[]{dtm.getRowCount()+1+"","",
                "",
                "", "", "ARP",p.len+""
            });
        } else {
            dtm.addRow(new String[]{dtm.getRowCount()+1+"","N/A",
                "N/A",
                "", "", "Unknown",packet.len+""
            });
        }
        //  System.out.println(packet);
    }

}
