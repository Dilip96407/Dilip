/*
 * Created on Dec 8, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.client.ui.util.poi;

/**
 * @author WSY4148
 *
 */
public interface PoiLoader {
	public final int COLUMN_TYPE_STRING = 0;
	public final int COLUMN_TYPE_DATE = 1;
	public final int COLUMN_TYPE_DOUBLE = 2;
	public final int COLUMN_TYPE_FLOAT = 3;
	public final int COLUMN_TYPE_BOOLEAN = 4;
	
	public int getColumnCount();
	public int getColumnType(int col);
	public boolean isColumnMandatory(int col);
	public Object getColumnDefaultValue(int col);	
	
	public void startLoad();
	public String validateRecord(Object[] record);
	public String storeRecord(Object[] record);
	public void endLoad();
}
