package org.jivesoftware.sparkimpl.plugin.blucargo.table.records;

public interface Record {

  public abstract String getValueAt(int i);

  public String getColumnName(int i);
  public int getColumnCount();
}
