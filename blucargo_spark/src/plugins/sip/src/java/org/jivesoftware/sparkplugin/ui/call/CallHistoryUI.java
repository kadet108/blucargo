/**
 * $Revision: $
 * $Date: $
 *
 * Copyright (C) 2007 Jive Software. All rights reserved.
 *
 * This software is published under the terms of the GNU Lesser Public License (LGPL),
 * a copy of which is included in this distribution.
 */

package org.jivesoftware.sparkplugin.ui.call;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListCellRenderer;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.java.sipmack.softphone.SoftPhoneManager;

import org.jdesktop.swingx.JXList;
//import org.jdesktop.swingx.decorator.Filter;
//import org.jdesktop.swingx.decorator.FilterPipeline;
//import org.jdesktop.swingx.decorator.PatternFilter;
import org.jdesktop.swingx.sort.RowFilters;
import org.jivesoftware.resource.SparkRes;
import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.spark.component.RolloverButton;
import org.jivesoftware.spark.component.tabbedPane.SparkTab;
import org.jivesoftware.spark.component.tabbedPane.SparkTabbedPane;
import org.jivesoftware.spark.component.tabbedPane.SparkTabbedPaneListener;
import org.jivesoftware.spark.plugin.phone.resource.PhoneRes;
import org.jivesoftware.spark.util.GraphicUtils;
import org.jivesoftware.spark.util.ModelUtil;
import org.jivesoftware.sparkplugin.callhistory.HistoryCall;
import org.jivesoftware.sparkplugin.calllog.CallLog;
import org.jivesoftware.sparkplugin.calllog.LogManager;

/**
 *
 */
public class CallHistoryUI extends JPanel implements ActionListener, ListSelectionListener {

    private SparkTabbedPane tabs;
    private final LogManager logManager;

    private final JFrame callHistoryFrame;

    private RolloverButton callButton;
    private RolloverButton deleteButton;

    private JXList activeList;
    private DefaultListModel model;

    private CallHistoryRenderer renderer;

    public CallHistoryUI() {
        setLayout(new BorderLayout());

        setBackground(Color.white);

        renderer = new CallHistoryRenderer();

        callHistoryFrame = new JFrame(PhoneRes.getIString("phone.callhistory"));
        callHistoryFrame.setIconImage(SparkRes.getImageIcon(SparkRes.HISTORY_16x16).getImage());
        callHistoryFrame.add(this);
        callHistoryFrame.pack();
        callHistoryFrame.setSize(300, 300);

        logManager = SoftPhoneManager.getInstance().getLogManager();

        List<HistoryCall> calls = new ArrayList<HistoryCall>(logManager.getCallHistory());
        Collections.sort(calls, itemComparator);
        
        model = new DefaultListModel();
        for (HistoryCall call : calls) {
            final CallEntry callEntry = new CallEntry(call);
            model.addElement(callEntry);
        }        

        tabs = new SparkTabbedPane(JTabbedPane.BOTTOM);

        add(tabs, BorderLayout.CENTER);

        addAllPanel(model);

        addDialedCalls(model);

        addCallsReceived(model);

        addCallsMissed(model);

        callButton = new RolloverButton(PhoneRes.getIString("phone.tocall"), PhoneRes.getImageIcon("PHONE_CALL_24x24_IMAGE"));
        deleteButton = new RolloverButton(PhoneRes.getIString("phone.delete"), PhoneRes.getImageIcon("DELETE_24x24_IMAGE"));
        callButton.setHorizontalAlignment(JLabel.CENTER);
        deleteButton.setHorizontalAlignment(JLabel.CENTER);
        final Font buttonFont = new Font("Dialog", Font.BOLD, 13);
        callButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);

        final JPanel flowPanel = new JPanel(new FlowLayout());
        flowPanel.setOpaque(false);
        flowPanel.add(callButton);
        flowPanel.add(deleteButton);

        add(flowPanel, BorderLayout.SOUTH);

        callButton.setEnabled(false);
        deleteButton.setEnabled(false);

        callButton.addActionListener(this);
        deleteButton.addActionListener(this);

        tabs.addSparkTabbedPaneListener(new SparkTabbedPaneListener() {
            public void tabRemoved(SparkTab tab, Component component, int index) {
            }

            public void tabAdded(SparkTab tab, Component component, int index) {
            }

            public void tabSelected(SparkTab tab, Component component, int index) {
                JScrollPane pane = (JScrollPane)component;
                JXList list = (JXList)pane.getViewport().getView();
                activeList = list;

                boolean selections = list.getSelectedValue() != null;
                callButton.setEnabled(selections);
                deleteButton.setEnabled(selections);
            }

            public void allTabsRemoved() {
            }

            public boolean canTabClose(SparkTab tab, Component component) {
                return false;
            }
        });
    }

    private void addAllPanel(DefaultListModel model) {
        final JXList list = new JXList(model);
        list.addListSelectionListener(this);
        list.setCellRenderer(renderer);

        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    CallEntry entry = (CallEntry)list.getSelectedValue();
                    callHistoryFrame.dispose();
                    SoftPhoneManager.getInstance().getDefaultGuiManager().dial(entry.getNumber());
                }
            }
        });

        tabs.addTab(PhoneRes.getIString("phone.all"), null, new JScrollPane(list), PhoneRes.getIString("phone.allcalls"));
    }

    private void addDialedCalls(DefaultListModel model) {
        final JXList list = new JXList(model);
        list.addListSelectionListener(this);
        list.setCellRenderer(renderer);
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    CallEntry entry = (CallEntry)list.getSelectedValue();
                    callHistoryFrame.dispose();
                    SoftPhoneManager.getInstance().getDefaultGuiManager().dial(entry.getNumber());
                }
            }
        });

        //list.setFilters(null);

//        model
//        list.setRowFilter(filter)

//        RowFilter[] filterArray = {
        	RowFilter filter = RowFilters.regexFilter("\\Q" + CallLog.Type.dialed.toString() + "",0,0);
//        };
        
//        RowFilters rowFilters = new RowFilters();
//        list.setFilterEnabled(true);
//        Filter[] filterArray = { 
//            new PatternFilter("\\Q" + CallLog.Type.dialed.toString() + "",0,0)
//        };
//        list.setFilters(new FilterPipeline(filterArray));

        list.setRowFilter(filter);
        tabs.addTab(PhoneRes.getIString("phone.dialed"), null, new JScrollPane(list), PhoneRes.getIString("phone.dialedcalls"));
    }

    private void addCallsReceived(DefaultListModel model) {
        final JXList list = new JXList(model);
        list.addListSelectionListener(this);
        list.setCellRenderer(renderer);
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    CallEntry entry = (CallEntry)list.getSelectedValue();
                    callHistoryFrame.dispose();
                    SoftPhoneManager.getInstance().getDefaultGuiManager().dial(entry.getNumber());
                }
            }
        });

//        list.setFilterEnabled(true);
//        Filter[] filterArray = { 
//            new PatternFilter("\\Q" + CallLog.Type.received.toString() +  "\\E",0,0)
//        };
//        
//        list.setFilters(new FilterPipeline(filterArray));

    	RowFilter filter = RowFilters.regexFilter("\\Q" + CallLog.Type.received.toString() +  "\\E",0,0);
        list.setRowFilter(filter);

        
        
        tabs.addTab(PhoneRes.getIString("phone.received"), null, new JScrollPane(list), PhoneRes.getIString("phone.receivedcalls"));
    }

    private void addCallsMissed(DefaultListModel model) {
        final JXList list = new JXList(model);
        list.addListSelectionListener(this);
        list.setCellRenderer(renderer);
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    CallEntry entry = (CallEntry)list.getSelectedValue();
                    callHistoryFrame.dispose();
                    SoftPhoneManager.getInstance().getDefaultGuiManager().dial(entry.getNumber());
                }
            }
        });

//        list.setFilterEnabled(true);
//        Filter[] filterArray = { 
//            new PatternFilter("\\Q" + CallLog.Type.missed.toString() + "\\E",0,0)
//        };
//        
//        list.setFilters(new FilterPipeline(filterArray));
    	RowFilter filter = RowFilters.regexFilter("\\Q" + CallLog.Type.missed.toString() + "\\E",0,0);
        list.setRowFilter(filter);

        
        
        tabs.addTab(PhoneRes.getIString("phone.missed"), null, new JScrollPane(list), PhoneRes.getIString("phone.missedcalls"));
    }

    public void invoke() {
        GraphicUtils.centerWindowOnComponent(callHistoryFrame, SparkManager.getMainWindow());
        callHistoryFrame.setVisible(true);
    }

    /**
     * Represents a single entry into the phone history list.
     */
    public class CallEntry extends JPanel {
        private String number;
        private HistoryCall call;
        private String type;

        public CallEntry(HistoryCall call) {
            setLayout(new GridBagLayout());

            this.number = call.getNumber();
            this.call = call;
            this.type = call.getGroupName();
            long time = call.getTime();
            long duration = call.getCallLength();


            String title = call.getCallerName();
            if (title == null) {
                title = PhoneRes.getIString("phone.unknown");
            }


            number = call.getNumber();

            StringBuilder sb = new StringBuilder();

            if (call.getGroupName().equals(CallLog.Type.dialed.toString())) {
                setBackground(new Color(40, 147, 40).brighter().brighter());
                sb.append(PhoneRes.getIString("phone.placecallto")+" ");
            }
            else if (call.getGroupName().equals(CallLog.Type.received.toString())) {
                setBackground(Color.blue);
                sb.append(PhoneRes.getIString("phone.placecallto")+" ");
            }
            else if (call.getGroupName().equals(CallLog.Type.missed.toString())) {
                setBackground(Color.red);
                sb.append(PhoneRes.getIString("phone.missedcallfrom")+" ");
            }

            sb.append(title);

            final JLabel titleLabel = new JLabel(sb.toString());
            titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));

            final SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");

            StringBuilder builder = new StringBuilder();
            builder.append(formatter.format(time));
            builder.append(" ");

            if (duration > 0) {
                builder.append(PhoneRes.getIString("phone.duration")+": ");
                builder.append(ModelUtil.getTimeFromLong(duration*1000));
            }

            final JLabel descriptionLabel = new JLabel(builder.toString());
            descriptionLabel.setForeground(Color.gray);
            descriptionLabel.setFont(new Font("Dialog", Font.PLAIN, 11));

            // Add Title Label
            add(titleLabel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 2, 0, 2), 0, 0));

            // Add Phone Number Label
            final JLabel numberLabel = new JLabel();
            numberLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
            numberLabel.setText(number);
            add(numberLabel, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(2, 2, 0, 2), 0, 0));

            // Add description of call
            add(descriptionLabel, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 2, 2, 2), 0, 0));
        }

        public String getNumber() {
            return number;
        }

        public HistoryCall getCall() {
            return call;
        }
        
        // needed by PatternFilter
        public String toString()
        {
        	return type;
        }
    }


    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == callButton) {
            // Get Selected Call Entry
            CallEntry entry = (CallEntry)activeList.getSelectedValue();
            SoftPhoneManager.getInstance().getDefaultGuiManager().dial(entry.getNumber());
        }
        else if (actionEvent.getSource() == deleteButton) {
        	int[] selected = activeList.getSelectedIndices();
        	for (int i = selected.length - 1; i >= 0 ; i--)
        	{
        		int modelindex = activeList.convertIndexToModel(selected[i]);
                logManager.getCallHistory().remove( ( (CallEntry)model.elementAt(modelindex)).getCall() );
        		model.remove(modelindex);	        		
        	}        
        }
    }

    final Comparator itemComparator = new Comparator() {
        public int compare(Object contactItemOne, Object contactItemTwo) {
            final HistoryCall time1 = (HistoryCall)contactItemOne;
            final HistoryCall time2 = (HistoryCall)contactItemTwo;
            if (time1.getTime() < time2.getTime()) {
                return 1;
            }
            else if (time1.getTime() > time2.getTime()) {
                return -1;
            }
            return 0;

        }
    };


    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if (listSelectionEvent.getValueIsAdjusting()) {
            return;
        }

        final JXList list = (JXList)listSelectionEvent.getSource();
        activeList = list;
        CallEntry callEntry = (CallEntry)list.getSelectedValue();
        callButton.setEnabled(callEntry != null);
        deleteButton.setEnabled(callEntry != null);
    }

    /**
     * Internal ListRenderer for CallEntry panel.
     */
    private static class CallHistoryRenderer extends JPanel implements ListCellRenderer {


        public Component getListCellRendererComponent(JList list,
                                                      Object value,
                                                      int index,
                                                      boolean isSelected,
                                                      boolean cellHasFocus) {
            CallEntry panel = (CallEntry)value;
            panel.setFocusable(false);

            if (isSelected) {
                panel.setForeground((Color)UIManager.get("List.selectionForeground"));
                panel.setBackground((Color)UIManager.get("List.selectionBackground"));
                panel.setBorder(BorderFactory.createLineBorder((Color)UIManager.get("List.selectionBorder")));
            }
            else {
                if (panel.getCall().getGroupName().equals(CallLog.Type.dialed.toString())) {
                    panel.setBackground(new Color(231, 248, 228));
                }
                else if (panel.getCall().getGroupName().equals(CallLog.Type.received.toString())) {
                    panel.setBackground(new Color(211, 237, 240));
                }
                else if (panel.getCall().getGroupName().equals(CallLog.Type.missed.toString())) {
                    panel.setBackground(new Color(255, 224, 224));
                }

                panel.setForeground(list.getForeground());
                panel.setBorder(BorderFactory.createLineBorder((Color)UIManager.get("List.background")));
            }

            list.setBackground((Color)UIManager.get("List.background"));


            return panel;
        }
    }

}
