package org.jivesoftware.sparkimpl.plugin.blucargo.manager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.ProviderManager;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class IQCargoOffers extends IQ {

	private String query;
	private String request;
	private XMPPConnection connection;

	public IQCargoOffers(XMPPConnection connection, String query) {
		this.connection = connection;
		this.query = query;
	}

	public synchronized Object getValues() throws XMPPException, IOException {

		IQ result = constructQueryThenSendAndReturnResult();
		return processGetResult(result);
	}

	
	public synchronized Object getValues(Object values) throws XMPPException, IOException {

		IQ result = constructQueryThenSendAndReturnResult(values);
		return processGetResult(result);
	}

	public synchronized void setValues(Object values) throws XMPPException, IOException {

		IQ result = constructSetQueryThenSendAndReturnResult(values);
		processSetResult(result);
		
	}

	private Object processGetResult(IQ result) throws XMPPException, IOException {
		if (result == null) {
			throw new XMPPException("No response from server.");
		} else if (result.getType() == IQ.Type.ERROR) {
			throw new XMPPException(result.getError());
		} else {
			return decodeResult(result);
		}
	}

	private Object processSetResult(IQ result) throws XMPPException, IOException {
		if (result == null) {
			throw new XMPPException("No response from server.");
		} else if (result.getType() == IQ.Type.ERROR) {
			throw new XMPPException(result.getError());
		} else {
			return null;
		}
	}

	private void constructRequestWithoutParams() {
		StringBuilder buf = new StringBuilder();
		buf.append("<query xmlns=\"" + query + "\" >");
		buf.append("</query>");
		this.request = buf.toString();

	}

	private void constructRequestWithParams(Object values) {
		StringBuilder buf = new StringBuilder();
		buf.append("<query xmlns=\"" + query + "\" >");

		ByteArrayOutputStream bos = serialize(values);
		String sEncodedText = encodeWithBase64(bos);

		buf.append(sEncodedText);
		buf.append("</query>");
		this.request = buf.toString();

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

	private IQ constructQueryThenSendAndReturnResult() {
		constructRequestWithoutParams();
		ProviderManager.getInstance().addIQProvider("query", query, new IQCargoOffersProvider());
		this.setType(IQ.Type.GET);
		
		this.setTo(connection.getServiceName());
		return sendAndGetResult();
	}

	private IQ constructQueryThenSendAndReturnResult(Object values) {
		constructRequestWithParams(values);
		ProviderManager.getInstance().addIQProvider("query", query, new IQCargoOffersProvider());
		this.setType(IQ.Type.GET);

		this.setTo(connection.getServiceName());
		return sendAndGetResult();
	}


	private IQ constructSetQueryThenSendAndReturnResult(Object values) {
		constructRequestWithParams(values);
		ProviderManager.getInstance().addIQProvider("query", query, new IQCargoOffersProvider());
		this.setType(IQ.Type.SET);

		this.setTo(connection.getServiceName());
		return sendAndGetResult();
	}

	private IQ sendAndGetResult() {
		PacketFilter filter = new AndFilter(new PacketIDFilter(this.getPacketID()));
		PacketCollector collector = connection.createPacketCollector(filter);
		IQ result = null;
		
		//Try 3 times
		for (int i=0; i<3 && result == null; i++) {
			try {
				connection.sendPacket(this);
			}
			catch (IllegalStateException e){
				try {
					connection.disconnect();
					connection.connect();
				} catch (XMPPException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				continue;
			}
				
			//collector.n
			result = (IQ) collector.nextResult(SmackConfiguration.getPacketReplyTimeout()*10);
			
		}
		collector.cancel();
		return result;
	}

	private Object decodeResult(IQ result) throws IOException {
		String text = extractContentWithXmlPullParser(result);
		byte[] decodedText = decodeWithBase64(text);
		return readInTheStream(decodedText);
	}

	private Object readInTheStream(byte[] decodedText) {
		//ArrayList<CargoOffer> offerList = new ArrayList<CargoOffer>();
		Object object=null;
		ByteArrayInputStream bis = null;
		ObjectInputStream in = null;
		try {
			bis = new ByteArrayInputStream(decodedText);
			in = new ObjectInputStream(bis);
//			offerList = (ArrayList<CargoOffer>) in.readObject();
			object = in.readObject();
			in.close();
			return object;
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	private byte[] decodeWithBase64(String text) {
		Base64 base64 = new Base64();
		byte[] decodedText = base64.decode(text.getBytes());
		return decodedText;
	}

	private String extractContentWithXmlPullParser(IQ result) throws IOException {
		XmlPullParserFactory factory;
		String text = "";
		try {
			factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new StringReader(result.getChildElementXML()));
			int eventType = parser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.TEXT) {
					text = parser.getText();
					break;
				}
				eventType = parser.next();
			}
		} catch (XmlPullParserException ex) {
			Logger.getLogger(IQCargoOffers.class.getName()).log(Level.SEVERE, null, ex);
		}
		return text;
	}

	@Override
	public String getChildElementXML() {
		return this.request;
	}
}
