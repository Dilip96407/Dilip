package de.westlb.mgb.client.mask.view.shared;

import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.util.Iterator;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.apache.axis.AxisFault;

import de.westlb.mgb.client.application.ApplicationDefinitions;
import de.westlb.mgb.client.application.ErrorDef;
import de.westlb.mgb.client.application.MayBeRefreshSupport;
import de.westlb.mgb.client.application.RefreshHelper;
import de.westlb_systems.xaf.application.Synchronizer;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SButton;
import de.westlb_systems.xaf.swing.SComponent;
import de.westlb_systems.xaf.ui.components.VLinkButton;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.ui.view.View;
import de.westlb_systems.xaf.util.SCommand;
import de.westlb_systems.xaf.util.SResourceBundle;

/**
 * @author M. Boerner
 *
 * Abstract superclass of mgb views containing common
 * behavior.
 */
public abstract class AbstractView extends View implements MayBeRefreshSupport {

	/**
     * 
     */
    private static final long serialVersionUID = -3840740328922145697L;
    private static SResourceBundle errorResourceBundle = new SResourceBundle(ApplicationDefinitions.ERRORBOX_RESOURCE);
	private Color emphasizedFieldColor;
	private Color attentionFieldColor;

	protected static final int
		DEF_COL_01		= 1,
		DEF_COL_02		= 2,
		DEF_COL_05		= 5,
		DEF_COL_08		= 8,
		DEF_COL_10		= 10,
		DEF_COL_12		= 12,
		DEF_COL_20		= 20
	;
	
	protected static final int 
		NONE	= GridBagConstraints2.NONE,
		BOTH	= GridBagConstraints2.BOTH,
		CENTER	= GridBagConstraints2.CENTER,
		WEST	= GridBagConstraints2.WEST,
		HORIZ	= GridBagConstraints2.HORIZONTAL,
		NWEST  = GridBagConstraints2.NORTHWEST,
		NORTH  = GridBagConstraints2.NORTH,
		SOUTH  = GridBagConstraints2.SOUTH,
		SEAST	= GridBagConstraints2.SOUTHEAST;
	;
	
	protected static final Insets insets0000 = new Insets(0, 0, 0, 0);
	protected static final Insets insets8800 = new Insets(8, 8, 0, 0);
	protected static final Insets insets8880 = new Insets(8, 8, 8, 0);
	protected static final Insets insets8808 = new Insets(8, 8, 0, 8);		
	protected static final Insets insets4480 = new Insets(4, 4, 8, 0);
	protected static final Insets insets4488 = new Insets(4, 4, 8, 8);
	protected static final Insets insets4800 = new Insets(4, 8, 0, 0);
	protected static final Insets insets4808 = new Insets(4, 8, 0, 8);	
	protected static final Insets insets4880 = new Insets(4, 8, 8, 0);
	protected static final Insets insets4080 = new Insets(4, 8, 8, 0);	
	protected static final Insets insets4888 = new Insets(4, 8, 8, 8);
	protected static final Insets insets4088 = new Insets(4, 0, 8, 8);
	protected static final Insets insets0088 = new Insets(0, 0, 8, 8);
	protected static final Insets insets8888 = new Insets(8, 8, 8, 8);
	protected static final Insets insets4000 = new Insets(4, 0, 0, 0);
	protected static final Insets insets4008 = new Insets(4, 0, 0, 8);
	protected static final Insets insets4408 = new Insets(4, 4, 0, 8);
	protected static final Insets insets0800 = new Insets(0, 8, 0, 0);
	
		
	/**
	 * Constructor for AbstractView.
	 */
	public AbstractView() {
		super();
//		errorResourceBundle = new SResourceBundle(ApplicationDefinitions.ERRORBOX_RESOURCE);
	}

	/**
	 * Constructor for AbstractView.
	 * @param model
	 */
	public AbstractView(Model model) {
		super(model);
	}
	
	/**
	 * Returnes the prefix for resource strings
	 */
	protected String getResourceBase() {
		String className = getClass().getName();
		String name = className.substring(className.lastIndexOf(".") + 1);
		if (name.endsWith("View")) {
			name = name.substring(0, name.length()-4);
		} else if (name.endsWith("Dialog")) {
			name = name.substring(0, name.length()-6);
		} else if (name.endsWith("Model")) {
			name = name.substring(0, name.length()-5);
		} 
		
		return name + "_";		
	}
	
	/**
	 * Returnes the name of the class without package prefix as
	 * name for the resource file.
	 */
	@Override
    public String getResourceName() {
		String className = getClass().getName();
		String name = className.substring(className.lastIndexOf(".") + 1);
		if (name.endsWith("View")) {
			name = name.substring(0, name.length()-4);
		} else if (name.endsWith("Dialog")) {
			name = name.substring(0, name.length()-6);
		}
		
		name = ApplicationDefinitions.LABEL_PATH + name;
		
		return name;		
	}
	
		
    /**
     * Overwrites saveData() method from parent class. The message
     * is to be called by 
     */
    @Override
    public boolean saveData() {
        boolean oldC = Synchronizer.setWaitCursor(true, this);
        boolean saved = (getModel() != null) ? getModel().saveModel() : true;
        setReturnvalue((getModel() != null) ? getModel().getReturnvalue() : 0);
        Synchronizer.setWaitCursor(oldC, this);

        if (!saved) {
			handleModelError();
        } else {
        	setDataChanged(false);
        }

        return saved;
    }
    
    public void handleModelError() {
        int error = getModel().getError();

        if ((error == Model.APPLICATION_ERROR) || (error == Model.DATABASE_ERROR)) {
            Object message = getModel().getErrorMessage();
            Object details = getModel().getErrorDetails();
            showMessageBox(SHOW_ERROR, message, details);
        }
    }
	
    /**
     * @see de.westlb_systems.xaf.ui.view.View#showMessageBox(int, Object, Object)
     */
    public boolean showError(Object details) {
    	String message = null;
    	if (details instanceof AxisFault) {
    		AxisFault axisFault = (AxisFault)details;
    		String code = axisFault.getFaultCode().toString();
    		String text = errorResourceBundle.getResourceString(code);
    		if (text == null) {
    			message = getErrorResStr(ErrorDef.AXIS_FAULT);
    		} else {
    			message = text;
    		}
   		}
   		
	    return super.showMessageBox(SHOW_ERROR, message, details);
     }
    
    private String getErrorResStr(String key) {
    	String text;
    	if (errorResourceBundle != null) {
    		text = errorResourceBundle.getResourceString(key);
    	} else {
    		text = key;
    	}
    	
    	return text; 
    }
    
    /*
     * Checks if a refresh of the view is required and
     * performs the refresh if necessary
     */
    @Override
    public void mayBeRefresh() {
    	Model model = getModel();	
    	if (model != null && RefreshHelper.isRefreshRequired(model)) {
			model.reload();
    	}    
    }
    
    public void setReadOnly() {
        SCommand readOnlySetter = new SCommand() {
        	@Override
            public void exec(Object component) {
				if (component instanceof SComponent && 
					!(component instanceof SButton)) {
					((SComponent)component).setEditable(false);
				} 
        	}
        };
        iterateAllComponents(readOnlySetter);    	
    }
    
    /**
     * Hides all fields of a give type, if a corresponding
     * property of the model is null.
     */
    protected void setInvisibleIfNull(
    	final Class<?> type,
    	final PropertyHandler propertyHandler, 
    	final Model model 
    ) {
        SCommand invisibleSetter = new SCommand() {
            @Override
            public void exec(Object component) {
                if (!(component instanceof JComponent) || (!(component.getClass() == type))) {
                	return;
                }
                @SuppressWarnings("rawtypes")
                Iterator iter = propertyHandler.getPropertyNames(component).iterator();
                while (iter.hasNext()) {
                    if (model.getProperty((String) iter.next()) == null) {
                        ((JComponent) component).setVisible(false);
                    }
                }
            }
        };
        iterateAllComponents(invisibleSetter);
    }

    /**
     * @see java.awt.Component#setBackground(Color)
     */
    public void setBackgroundForAllPanels(final Color c) {
        setBackground(c);
        SCommand bgSetter = new SCommand() {
        	@Override
            public void exec(Object component) {
        		if (	component instanceof JPanel || 
						component instanceof JCheckBox ||
        				component instanceof JRadioButton || 
        				component instanceof VLinkButton ||
        				component instanceof JTextField
        		) {
        			((Component)component).setBackground(c);
        		}
        	}
        };
        iterateAllComponents(bgSetter);
    }

    /* (Kein Javadoc)
     * @see de.westlb_systems.xaf.ui.view.View#showMessageBox(int, java.lang.Object)
     */
    @Override
    public boolean showMessageBox(int type, Object message) {
        return super.showMessageBox(type, message);
    }
    
    public void setEmphasized(Component component) {
		component.setBackground(getEmphasizedFieldColor());
    }
    
	public void setAttention(Component component, boolean on) {
		if (on) {
			component.setBackground(getAttentionFieldColor());
		} else {
			setEmphasized(component);
		}
	}

    /**
     * @return
     */
    private Color getAttentionFieldColor() {
    	if (attentionFieldColor == null) {
    		attentionFieldColor = (Color)UIManager.get("Field.attentionColor");
    	}
        return attentionFieldColor;
    }

    /**
     * @return
     */
    private Color getEmphasizedFieldColor() {
    	if (emphasizedFieldColor == null) {
    		emphasizedFieldColor = (Color)UIManager.get("Field.emphasizedColor");
    	}
        return emphasizedFieldColor;
    }

}
