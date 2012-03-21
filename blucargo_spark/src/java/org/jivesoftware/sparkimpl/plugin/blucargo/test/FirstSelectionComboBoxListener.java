/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jivesoftware.sparkimpl.plugin.blucargo.test;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JComboBox;

/**
 *
 * @author kadet
 */
public class FirstSelectionComboBoxListener implements FocusListener{

    public boolean firstTime=true;

    public void focusGained(FocusEvent e) {
        if (firstTime){
            JComboBox object=(JComboBox)e.getComponent();
            object.removeItemAt(0);
            firstTime=false;
        }
    }

    public void focusLost(FocusEvent e) {
    }

}
