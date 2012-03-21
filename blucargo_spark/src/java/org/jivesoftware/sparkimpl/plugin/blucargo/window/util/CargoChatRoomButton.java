package org.jivesoftware.sparkimpl.plugin.blucargo.window.util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

import javax.swing.Icon;

import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.spark.ui.ChatRoomButton;

public class CargoChatRoomButton extends ChatRoomButton {
	private static final long serialVersionUID = -2292789979004158240L;

	/**
     * Create a new ChatRoomButton.
     */
    public CargoChatRoomButton() {
    	super();
    	this.decorate();
    }

    /**
     * Create a new ChatRoomButton
     *
     * @param icon the icon to use on the button.
     */
    public CargoChatRoomButton(Icon icon) {
        super(icon);
        this.decorate();
    }

    /**
     * Create a new ChatRoomButton.
     *
     * @param text the button text.
     * @param icon the button icon.
     */
    public CargoChatRoomButton(String text, Icon icon) {
        super(text, icon);
        this.decorate();
    }

    /**
     * Creates a new ChatRoomButton.
     *
     * @param text the text to display on the button.
     */
    public CargoChatRoomButton(String text) {
        super(text);
        this.decorate();
    }
    
    
    private void decorate(){
		this.setRolloverIcon(SparkRes.getImageIcon(SparkRes.SPARK_IKONKA_WIZYTOWKA));
		this.setBorderPainted(false);
		this.setRolloverEnabled(false);
		this.setOpaque(false);
		this.setBorder(null);
		this.setBorderPainted(false);

		MouseListener [] mouseListeners = this.getMouseListeners();
		for (MouseListener mouseListener : mouseListeners) {
			if (mouseListener instanceof MouseAdapter){
				this.removeMouseListener(mouseListener);
			}
		}

    }
}
