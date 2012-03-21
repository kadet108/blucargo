package org.jivesoftware.sparkimpl.plugin.blucargo.test;


import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

 public class WindowSnapper extends ComponentAdapter {

        private boolean locked = false;
        private int sd = 50;
        private JFrame target;
        private JFrame other;


        public WindowSnapper(JFrame targetComponent, JFrame otherComponent)
        {
            target=targetComponent;
            other=otherComponent;
        }

          public void componentMoved(ComponentEvent evt) {
                if (locked)
                  return;
                //Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
                int nx = evt.getComponent().getX();
                int ny = evt.getComponent().getY();
                int width = evt.getComponent().getWidth();
                int height = evt.getComponent().getHeight();
                // top
//                if (ny < 0 + sd) {
//                  ny = 0;
//                }
//                // left
//                if (nx < 0 + sd) {
//                  nx = 0;
//                }
                // right
//                if (nx > size.getWidth() - evt.getComponent().getWidth() - sd) {
//                  nx = (int) size.getWidth() - evt.getComponent().getWidth();
//                }
                if ((nx +width+sd > other.getX()) && (nx +width < other.getX()+other.getWidth()) && (ny>other.getY()) && (ny<other.getY()+other.getHeight() )) {
                  nx = other.getX()-width;
                }
//                // bottom
//                if (ny+height > size.getHeight() - evt.getComponent().getHeight() - sd) {
//                  ny = (int) size.getHeight() - evt.getComponent().getHeight();
//                }
                // bottom
                if ((ny+height+sd > other.getY()) && (nx>other.getX()) && (nx<other.getX()+other.getWidth()))
                {
                  ny = other.getY()-height;
                }
                // make sure we don't get into a recursive loop when the
                // set location generates more events
                locked = true;
                evt.getComponent().setLocation(nx, ny);
                locked = false;
        //            evt.getComponent().setEnabled(false);
        //            locked = true;
        //            evt.getComponent().setLocation(nx, ny);
        //            locked = false;
        //            evt.getComponent().setEnabled(true);
                   ///evt.setSource(null);

              }
    }
