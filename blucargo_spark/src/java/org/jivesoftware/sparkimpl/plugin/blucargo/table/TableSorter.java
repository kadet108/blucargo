package org.jivesoftware.sparkimpl.plugin.blucargo.table;

import java.util.Date;

import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersBodyPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersContactPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersInfoPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersLoadPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersOfferValidPanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersSubmissionDatePanel;
import org.jivesoftware.sparkimpl.plugin.blucargo.visualpanel.CargoOffersUnloadPanel;

import com.blucargo.model.CargoOffer;

class TableSorter {

	BlucargoSortableTableModel model;

	public TableSorter(BlucargoSortableTableModel model) {
		this.model = model;
	}

	public void sort(int column, boolean isAscent) {
		int n = model.getRowCount();
		int[] indexes = model.getIndexes();

		for (int i = 0; i < n - 1; i++) {
			int k = i;
			for (int j = i + 1; j < n; j++) {
				if (isAscent) {
					if (compare(column, j, k) < 0) {
						k = j;
					}
				} else {
					if (compare(column, j, k) > 0) {
						k = j;
					}
				}
			}
			int tmp = indexes[i];
			indexes[i] = indexes[k];
			indexes[k] = tmp;
		}
	}

	// comparaters
	public int compare(int column, int row1, int row2) {
		Object o1 = model.getValueAt(row1, 0);
		Object o2 = model.getValueAt(row2, 0);
		if (o1 == null && o2 == null) {
			return 0;
		} else if (o1 == null) {
			return -1;
		} else if (o2 == null) {
			return 1;
		} else {
			Class<?> type = model.getColumnClass(column);

			int result;
			CargoOffer co1;
			CargoOffer co2;
			co1 = ((CargoOffer) model.getValueAt(row1, 0));
			co2 = ((CargoOffer) model.getValueAt(row2, 0));

			if (type == CargoOffersInfoPanel.class) {
				result = co1.getUnloadingDate().compareTo(co2.getUnloadingDate());
				return result;
			} else if (type == CargoOffersLoadPanel.class) {
				result = co1.getCountryFrom().compareTo(co2.getCountryFrom());
				if (result == 0) {
					result = co1.getCityFrom().compareTo(co2.getCityFrom());
					if (result == 0) {
						result = co1.getPostOfficeFrom().compareTo(co2.getPostOfficeFrom());
					}
				}
				return result;
			} else if (type == CargoOffersUnloadPanel.class) {
				result = co1.getCountryFrom().compareTo(co2.getCountryFrom());
				if (result == 0) {
					result = co1.getCityFrom().compareTo(co2.getCityFrom());
					if (result == 0) {
						result = co1.getPostOfficeFrom().compareTo(co2.getPostOfficeFrom());
					}
				}
				return result;
			} else if (type == CargoOffersBodyPanel.class) {
				result = co1.getWeight().compareTo(co2.getWeight());
				if (result == 0) {
					result = co1.getBody().compareTo(co2.getBody());
					if (result == 0) {
						result = co1.getCargoLength().compareTo(co2.getCargoLength());
					}
				}
				return result;
			} else if (type == CargoOffersSubmissionDatePanel.class) {
				result = co1.getSubmissionDate().compareTo(co2.getSubmissionDate());
				return result;
			} else if (type == CargoOffersOfferValidPanel.class) {
				result = co1.getOfferValid().compareTo(co2.getOfferValid());
				return result;
			} else if (type == CargoOffersContactPanel.class) {
				result = co1.getContact().compareTo(co2.getContact());
				return result;
			} else {
				return ((String) o1).compareTo((String) o2);
			}
		}
	}

	public int compare(Number o1, Number o2) {
		double n1 = o1.doubleValue();
		double n2 = o2.doubleValue();
		if (n1 < n2) {
			return -1;
		} else if (n1 > n2) {
			return 1;
		} else {
			return 0;
		}
	}

	public int compare(Date o1, Date o2) {
		long n1 = o1.getTime();
		long n2 = o2.getTime();
		if (n1 < n2) {
			return -1;
		} else if (n1 > n2) {
			return 1;
		} else {
			return 0;
		}
	}

	public int compare(Boolean o1, Boolean o2) {
		boolean b1 = o1.booleanValue();
		boolean b2 = o2.booleanValue();
		if (b1 == b2) {
			return 0;
		} else if (b1) {
			return 1;
		} else {
			return -1;
		}
	}
}
