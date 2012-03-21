package org.jivesoftware.sparkimpl.plugin.blucargo.window.menu;
/*package org.jivesoftware.sparkimpl.plugin.cargo.window.menu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ContextMenu implements ActionListener {
  JTextArea textArea = new JTextArea();

  public ContextMenu() {
    final JPopupMenu contextMenu = new JPopupMenu("Edit");
    contextMenu.add(new ContactMenuItem());

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    frame.add(panel);
    panel.setComponentPopupMenu(contextMenu);

    textArea.setInheritsPopupMenu(true);
    panel.add(BorderLayout.CENTER, textArea);

    JTextField textField = new JTextField();
    textField.setInheritsPopupMenu(true);
    panel.add(BorderLayout.SOUTH, textField);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 200);
    frame.setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    textArea.append(e.getActionCommand() + "\n");
  }

  private JMenuItem makeMenuItem(String label) {
    JMenuItem item = new JMenuItem(label);
    item.addActionListener(this);
    return item;
  }

  public static void main(String[] args) {
    new ContextMenu();
  }
}*/