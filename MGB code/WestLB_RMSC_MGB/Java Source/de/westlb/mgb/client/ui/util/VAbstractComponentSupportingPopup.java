/*
 * Created on Sep 1, 2004
 *
 * Field that supports 
 */
package de.westlb.mgb.client.ui.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.ui.components.ValueChangeField;
import de.westlb_systems.xaf.ui.components.ValueChangeSupport;
import de.westlb_systems.xaf.ui.components.event.ValueChangeListener;

/**
 * @author wsy4148
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class VAbstractComponentSupportingPopup extends SPanel implements ValueChangeField {

	/**
     * 
     */
    private static final long serialVersionUID = 6444482022821228476L;

    private static final int GAP = 4;
	
	private JButton button = new JButton("...");
	private Listener listener = new Listener();
	private VChoiceFieldLayout layout = new VChoiceFieldLayout();
	private ValueChangeSupport valueChangeSupport = new ValueChangeSupport();
	private Component component;
	private Object value = null;
	
	@SuppressWarnings("unused")
    private boolean mandatory;
	@SuppressWarnings("unused")
    private boolean editable;
	
	/**
	 * 
	 */
	public  VAbstractComponentSupportingPopup(Component component) {
		super();
		this.component = component;
		initComponents();
		initLayout();
	}
	
	private void initComponents() {
		button.addActionListener(listener);
	}
	
	private void initLayout() {
		add(getComponent(), null);
		add(button, null);
		setLayout(layout);
	}
	
	@Override
    public void setEditable(boolean newValue) {
		this.editable = newValue;
	}

	/**
	 * The openPopup method is implemented in 
	 * subclasses.
	 */
	protected abstract void openPopup();
	
	protected Component getComponent() {
		return component;
	}
	
	protected Point getPopupLocation() {
		double x = getLocationOnScreen().getX() + button.getBounds().x + button.getBounds().width;
		double y = getLocationOnScreen().getY();
		return new Point((int)x, (int)y);
	}
	
	private class VChoiceFieldLayout implements LayoutManager {
	   public VChoiceFieldLayout() {}
	   @Override
    public void addLayoutComponent(String name, Component comp) {}
	   @Override
    public void removeLayoutComponent(Component comp) {}
	   
	   @Override
    public Dimension preferredLayoutSize(Container parent) {
		  Dimension dim = getComponent().getPreferredSize() ;
		  dim.width += dim.height + GAP;
		  return dim;
	   }

	   @Override
    public Dimension minimumLayoutSize(Container parent) {
		  Dimension dim = getComponent().getMinimumSize();
		  dim.width += dim.height + GAP;
		  return dim;
	   }

	   @Override
    public void layoutContainer(Container parent) {
		  int h = parent.getSize().height;
		  int w = parent.getSize().width;
		  int buttonHeight = h<20? h : 20 ;
		  getComponent().setBounds(0, 0, w - buttonHeight - GAP, h);
		  button.setBounds(  w - buttonHeight, 0, buttonHeight, buttonHeight );
	   }
	}
	
	
	private class Listener implements ActionListener {
		/** 
		 * Handles the button pressed event. 
		 */
		@Override
        public void actionPerformed(ActionEvent e) {
			requestFocus();
			if (isEditable()) {
				openPopup();
			}
		}
	}
	/* (non-Javadoc)
	 * @see de.westlb_systems.xaf.ui.components.ValueChangeField#recalcValue()
	 */
	@Override
    public void recalcValue() {
		return;
	}

	/* (non-Javadoc)
	 * @see de.westlb_systems.xaf.ui.components.event.ValueChangeSource#addValueChangeListener(de.westlb_systems.xaf.ui.components.event.ValueChangeListener)
	 */
	@Override
    public void addValueChangeListener(ValueChangeListener listener) {
		valueChangeSupport.addValueChangeListener(listener);
	}

	/* (non-Javadoc)
	 * @see de.westlb_systems.xaf.ui.components.event.ValueChangeSource#removeValueChangeListener(de.westlb_systems.xaf.ui.components.event.ValueChangeListener)
	 */
	@Override
    public void removeValueChangeListener(ValueChangeListener listener) {
		valueChangeSupport.removeValueChangeListener(listener);
	}
	
	public void setMandatory(boolean newMandatory) {
		mandatory = newMandatory;
	}
	
	/**
	 * Fires a ValueChangeEvent to all ValueChangeListener
	 * The event is fired only if oldValue not equals newValue. 
	 * 
	 * @param oldValue java.lang.Object
	 * @param newValue java.lang.Object
	 *
	 */
	protected void fireValueChanged(Object oldValue, Object newValue) {
		valueChangeSupport.fireValueChanged(this, oldValue, newValue);
	}
	
	public abstract Object getComponentValue();
	
	public abstract void setComponentValue(Object newValue);
	
	/**
	  * @see de.westlb_systems.xsfa.ui.swing.SComponent#getValue()
	  */
	 @Override
    public Object getValue() {
		 return getComponentValue();
	 }
	 
	/**
	 * Sets the field content.
	 * @param: newValue 
	 *
	 */
	@Override
    public void setValue(Object newValue) {
		Object oldValue = value;
		setComponentValue(newValue);
		fireValueChanged(oldValue, newValue);
	}

}
