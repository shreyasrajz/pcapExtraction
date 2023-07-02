package com.example.network;

import org.jnetpcap.Pcap;
import org.jnetpcap.PcapDumper;
import org.jnetpcap.PcapHandler;
import org.jnetpcap.packet.PcapPacket;

import java.nio.ByteBuffer;

public class Pcapextraction {
    public static void main(String[] args) {
        System.out.println("hello");
        String pcapFile = "fuzz-2006-06-26-2594.pcap";
        Pcap pcap = Pcap.openOffline(pcapFile, new StringBuilder());

        PcapHandler<PcapDumper> pcapHandler = new PcapHandler<PcapDumper>() {
            public void nextPacket(PcapDumper pcapDumper, long l, int i, int i1, int i2, ByteBuffer byteBuffer) {
                PcapPacket packet = new PcapPacket(byteBuffer);
                byte[] payload = packet.getByteArray(0, packet.size());
                System.out.println("Payload: " + new String(payload));
            }
        };
        pcap.loop(Pcap.LOOP_INFINITE, pcapHandler, null);
        pcap.close();
    }
}
