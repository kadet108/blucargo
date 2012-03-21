package org.jivesoftware.sparkimpl.plugin.blucargo.manager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.spark.SparkManager;

import com.blucargo.model.CargoOffer;

public class CargoTransIQ extends IQ {

    private String chatID;
    private XMPPConnection con;
    private boolean isOk;
    private String agentName;
    private ArrayList<CargoOffer> offerList;

    public CargoTransIQ(XMPPConnection connection, String chatid) {
        con = connection;
        this.chatID = chatid;
        agentName = SparkManager.getUserManager().getNickname();
    }

    public boolean getResultOfRelance() {
        return isOk;
    }

    public synchronized void addData(ArrayList<CargoOffer> offerList) throws XMPPException, IOException {
        ProviderManager.getInstance().addIQProvider("query", "http://myCargo.com",
                new IQCargoOffersProvider());

        this.setType(IQ.Type.SET);
        this.offerList=offerList;

        this.setTo(con.getServiceName());
        PacketFilter filter = new AndFilter(new PacketIDFilter(this.getPacketID()));
        PacketCollector collector = con.createPacketCollector(null); // add the filter for handling the response from the server
        con.sendPacket(this); // send the packet to the openfire server
        IQ result = (IQ) collector.nextResult(SmackConfiguration.getPacketReplyTimeout());
        // Stop queuing results
        collector.cancel();
    }

    @Override
    public String getChildElementXML() {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream out = null;
        try {
            bos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(bos);
            out.writeObject(offerList);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        byte[] encodedText;
        Base64 base64 = new Base64();

        encodedText = base64.encode(bos.toByteArray());
        String sEncodedText = new String(encodedText);

        DocumentFactory documentFactory = new DocumentFactory();
        Element element = documentFactory.createElement("query", "http://myCargo.com");
        element.setText(sEncodedText);

        return element.toString();

    }
}


