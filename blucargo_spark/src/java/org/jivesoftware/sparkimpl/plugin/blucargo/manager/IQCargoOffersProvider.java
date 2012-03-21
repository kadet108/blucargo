package org.jivesoftware.sparkimpl.plugin.blucargo.manager;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;

public class IQCargoOffersProvider implements IQProvider {

    public IQCargoOffersProvider() {
        super();
    }

    public IQ parseIQ(XmlPullParser parser) throws Exception {

        final StringBuilder text = new StringBuilder();

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_DOCUMENT) {
            } else if (eventType == XmlPullParser.END_DOCUMENT) {
            } else if (eventType == XmlPullParser.START_TAG) {
            } else if (eventType == XmlPullParser.END_TAG) {
            } else if (eventType == XmlPullParser.TEXT) {
                text.append(parser.getText());
                break;
            }
            eventType = parser.next();
        }

        IQ iq = new IQ() {

            @Override
            public String getChildElementXML() {
                StringBuffer buf = new StringBuffer();
                buf.append("<query xmlns=\"http://mycargo.com\">" + text + "</query>");
                return buf.toString();
            }
        };
        return iq ;
    }
}
