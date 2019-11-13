package de.westlb.mgb.client.mask.view.shared;

import java.awt.GridLayout;
import java.awt.Insets;

import de.westlb.mgb.client.mask.model.shared.LoginModel;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SImageComponent;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.swing.SPasswordField;
import de.westlb_systems.xaf.swing.STextField;
import de.westlb_systems.xaf.ui.misc.IconKatalog;
import de.westlb_systems.xaf.ui.model.Model;
import de.westlb_systems.xaf.ui.view.EditDialog;
import de.westlb_systems.xaf.ui.view.PropertyHandler;
import de.westlb_systems.xaf.util.Debug;


/**
 * @author WSY4148
 *
 * Dialog to display all new traders which not assigned to an employee in MGB.
 */
public class LoginDialog extends AbstractView implements EditDialog {

	/**
     * 
     */
    private static final long serialVersionUID = -7645529501081781958L;
    private String RESOURCE_BASE = getResourceBase();
	private PropertyHandler propertyHandler = new PropertyHandler();

    GridLayout gridLayout1 = new GridLayout();
    SPanel pDialog = new SPanel();
    SPanel pCommand = new SPanel();
    SImageComponent iLogo = new SImageComponent();
    SLabel lLogin = new SLabel();
    SLabel lPassword = new SLabel();
    STextField tfLogin = new STextField(DEF_COL_10);
    SPasswordField tfPassword = new SPasswordField();
    	
	/**
	 * Constructor for CheckStateView.
	 */
	public LoginDialog() {
		try {
			initComponents();
			initLayout();
			initLabels();
			initProperties();
		} catch (Exception ex) {
			Debug.log(Debug.ERROR, this, "Exception in View-Constuctor");
			Debug.log(ex);
			ex.printStackTrace();
		}
	}
	
	/**
	 * Constructor which sets the model
	 */
	public LoginDialog(Model model) {
		this();
		setModel(model);
	}
	
	
    /**
    * Fill the fields with the values of the model
    */
    private void fillView() {
        propertyHandler.syncFields();
    }

	
    /**
     * Method initProperties.
     */
    private void initProperties() {
		propertyHandler.add(LoginModel.P_USER_ID,	tfLogin);	
		propertyHandler.add(LoginModel.P_PASSWORD,	tfPassword);	
    }

    /**
     * Method initLabels.
     */
    private void initLabels() {
		setTitle			(getResourceString(RESOURCE_BASE + "T_001")	);
		lLogin.setText		(getResourceString(RESOURCE_BASE + "L_USER_ID"));
		lPassword.setText	(getResourceString(RESOURCE_BASE + "L_PASSWORD"));
    }

    /**
     * Method initLayout.
     */
    private void initLayout() {
        pCommand.setLayout(gridLayout1);
        gridLayout1.setHgap(10);

		iLogo.setImage(IconKatalog.getInstance().getImage("LOGIN_LOGO"));
		iLogo.setPreferredSize(iLogo.getImageSize());
 
        lLogin.setText("Login:");
        lPassword.setText("Password:");

        pDialog.add(iLogo,			new GridBagConstraints2(0, 0, 2, 1, 1.0, 1.0, CENTER, HORIZ, new Insets(10, 10, 0, 10), 0, 0));
        pDialog.add(lLogin,			new GridBagConstraints2(0, 1, 1, 1, 0.0, 0.0, WEST, NONE, new Insets(10, 10, 0, 0), 0, 0));
        pDialog.add(lPassword,		new GridBagConstraints2(0, 2, 1, 1, 0.0, 0.0, WEST, NONE, new Insets(10, 10, 10, 0), 0, 0));
        pDialog.add(tfLogin,		new GridBagConstraints2(1, 1, 1, 1, 1.0, 0.0, CENTER, HORIZ, new Insets(10, 5, 0, 10), 0, 0));
        pDialog.add(tfPassword,		new GridBagConstraints2(1, 2, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(10, 5, 10, 10), 0, 0));

        this.add(pDialog,	new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, CENTER, BOTH, new Insets(10, 10, 0, 10), 0, 0));
     }


    /**
     * Method initComponents.
     */
    private void initComponents() {
		tfPassword.setColumns(DEF_COL_10);
    }

	/**
	 * Sets the view model and creates the property handler from model view synchronization
	 */
	@Override
    public void setModel(Model newModel) {
	    if (!(newModel instanceof LoginModel)) {
	        throw new IllegalArgumentException("Model not instance of LoginModel");
	    }
	    propertyHandler.setModel(newModel);
	    super.setModel(newModel);
	    fillView();
	}

}
