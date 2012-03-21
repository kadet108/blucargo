package com.blucargo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.jivesoftware.openfire.CargoTransHandlerInfo;
import org.jivesoftware.openfire.auth.UnauthorizedException;
import org.jivesoftware.openfire.handler.IQHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmpp.packet.IQ;

import com.blucargo.BlucargoRequestHandler;

public abstract class BlucargoRequestHandler extends IQHandler {

    private CargoTransHandlerInfo info;
    private String query;
    protected String from;

    public BlucargoRequestHandler(String query) {
        super("XMPP Test Handler");
        this.query = query;
        info = new CargoTransHandlerInfo("query", query);
        System.out.println("IQMyTestHandler instance created serving "+query);
    }

    @Override
    public CargoTransHandlerInfo getInfo() {
        System.out.println("IQMyTestHandler getInfo called. Returned "+info);
        return info;
    }

    @Override
    public org.xmpp.packet.IQ handleIQ(org.xmpp.packet.IQ packet) throws UnauthorizedException {
    	this.from=packet.getFrom().getNode();
        if (packet.getType() == IQ.Type.get) {
            return getData(packet);
        } else if (packet.getType() == IQ.Type.set) {
            return setData(packet);
        }
        return null;
    }

    public abstract Object process(Object object);
    
    private IQ getData(IQ packet) {

    	//Testing if input parameters can be passed in
    	String text = extractContentWithXmlPullParser(packet);
    	byte[] decodedText = decodeWithBase64(text);
    	Object inputObject=null;

    	if(text.length()>0){
    		inputObject=deserialize(decodedText);
    	}
    	
    	//Get the data object in the subclass
    	//this.setObject(inputObject);
    	//Testing if input parameters can be passed in
    	
    	//Get the data object provided by subclass
    	Object object=process(inputObject);
        
        System.out.println("packet called " + packet.toString());
        System.out.println("packet called with childXML" + packet.getChildElement().asXML());        
        
        org.xmpp.packet.IQ result = org.xmpp.packet.IQ.createResultIQ(packet);
        
        ByteArrayOutputStream bos = serialize(object);
        String sEncodedText = encodeWithBase64(bos);
        
        createDocument(result, sEncodedText);
        return result;
    }


	private IQ setData(IQ packet) {    	
    	String text = extractContentWithXmlPullParser(packet);
    	byte[] decodedText = decodeWithBase64(text);
    	Object object=deserialize(decodedText);
    	    	
    	//Get the data object in the subclass
    	this.process(object);
    	
    	IQ result=packet.createResultIQ(packet);
    	return result;
    }


    private void createDocument(org.xmpp.packet.IQ result, String sEncodedText) {
		DocumentFactory documentFactory = new DocumentFactory();
        Element element = documentFactory.createElement("query", query);
        element.setText(sEncodedText);
        result.setChildElement(element);
        System.out.println("sending back: " + result.toXML());
        if (result.getChildElement() == null) {
            System.out.println("child element is null");
        } else {
            System.out.println("sending back child element: " + result.getChildElement());
        }
	}

	private ByteArrayOutputStream serialize(Object object) {
		ByteArrayOutputStream bos = null;
        ObjectOutputStream out = null;
        try {
            bos = new ByteArrayOutputStream();
            out = new ObjectOutputStream(bos);
            out.writeObject(object);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		return bos;
	}

	private String encodeWithBase64(ByteArrayOutputStream bos) {
		byte[] encodedText;
        Base64 base64 = new Base64();
        encodedText = base64.encode(bos.toByteArray());
        String sEncodedText = new String(encodedText);
		return sEncodedText;
	}

	private Object deserialize(byte[] decodedText) {
		Object object;
		ByteArrayInputStream bis = null;
		ObjectInputStream in = null;
		try {
			bis = new ByteArrayInputStream(decodedText);
			in = new ObjectInputStream(bis);
			object = in.readObject();
			return object;			
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		finally{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}


	private byte[] decodeWithBase64(String text) {
		Base64 base64 = new Base64();
        byte[] decodedText = base64.decode(text.getBytes());
		return decodedText;
	}

	private String extractContentWithXmlPullParser(IQ packet) {
		XmlPullParserFactory factory;
        String text = "";
        try {
            factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(packet.getChildElement().asXML()));
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.TEXT) {
                    text = parser.getText();
                    break;
                }
                eventType = parser.next();
            }
        } catch (IOException ex) {
            Logger.getLogger(BlucargoRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XmlPullParserException ex) {
            Logger.getLogger(BlucargoRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
		return text;
	}
}


