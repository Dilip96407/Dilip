package de.westlb.mgb.client.mask.view.shared;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.westlb.mgb.client.application.ApplicationDefinitions;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SButton;
import de.westlb_systems.xaf.swing.SComponent;
import de.westlb_systems.xaf.swing.SDialog;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.swing.SPasswordField;
import de.westlb_systems.xaf.swing.STextField;
import de.westlb_systems.xaf.swing.SUtilities;
import de.westlb_systems.xaf.util.SLocale;

/**
 */
//public class ProgressDialogImpl extends ProgressDialog {
public class InputDialog extends SDialog implements ActionListener {

	/**
     * 
     */
    private static final long serialVersionUID = -7603407364028777953L;

    // UI-Komponenten
	private Insets insets1 = new Insets(4, 8, 4, 8);

	private Insets insets2 = new Insets(0, 8, 4, 8);

	private SPanel pContent = new SPanel();

	private SLabel lInfo = new SLabel(" ");

	private SLabel lInput = new SLabel(" ");

	private SLabel lDetails = new SLabel(" ");

	Component tfInput = null;

	Object input = null;

	private SButton bOk = new SButton();

	private SButton bCancel = new SButton();

	/**
	 * ProgressDialog constructor comment.
	 * 
	 * @param owner
	 *            java.awt.Component
	 */
	public InputDialog(Component owner, boolean isPasswordDialog) {
		super(owner, "Information", true);

		if (isPasswordDialog) {
			tfInput = new SPasswordField();
		} else {
			tfInput = new STextField();
		}
		setResizable(false);

		setPreferredSize(new Dimension(400, 160));
		setMinimumSize(new Dimension(400, 160));

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		setTitle(getResourceString("INPUT_T_001"));
		lInfo.setText(getResourceString("INPUT_L_INFO"));
		lDetails.setText(getResourceString("INPUT_L_DETAILS"));
		lInput.setText(getResourceString("INPUT_L_INPUT"));
		bCancel.setText(getResourceString("INPUT_B_ABBRECHEN"));
		bCancel.addActionListener(this);
		bOk.setText(getResourceString("INPUT_B_OK"));
		bOk.addActionListener(this);

		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * actionPerformed method comment.
	 */
	@Override
    public void actionPerformed(ActionEvent event) {

		if (event.getSource() == bCancel) {
			input = null;
			dispose();
		}

		if (event.getSource() == bOk) {
			if (tfInput != null && tfInput instanceof SComponent) {
				input = ((SComponent)tfInput).getValue();
				((SComponent)tfInput).setValue(null);
			}
			dispose();
		}

	}

	/**
	 * Returnes the name of the class without package prefix as name for the
	 * resource file.
	 */
	@Override
    protected String getResourceName() {
		String className = getClass().getName();
		String name = ApplicationDefinitions.LABEL_PATH
				+ className.substring(className.lastIndexOf(".") + 1);

		return name;
	}

	/**
	 * Insert the method's description here. Creation date: (9/12/01 10:55:04
	 * AM)
	 */
	private void jbInit() {

		int EAST = GridBagConstraints2.EAST;
		int WEST = GridBagConstraints2.WEST;
		int NONE = GridBagConstraints2.NONE;
		int HORI = GridBagConstraints2.HORIZONTAL;
		int BOTH = GridBagConstraints2.BOTH;

		setContentPane(pContent);
		pContent.setMargin(insets2);

		pContent.add(lInfo, 	new GridBagConstraints2(0, 0, 2, 1, 1.0, 1.0, WEST,	BOTH, insets1, 0, 0));
		pContent.add(lDetails, 	new GridBagConstraints2(0, 1, 2, 1, 0.0, 0.0, WEST, HORI, insets1, 0, 0));
		pContent.add(lInput, 	new GridBagConstraints2(0, 2, 1, 1, 0.0, 0.0, WEST, HORI, insets1, 0, 0));
		pContent.add(tfInput, 	new GridBagConstraints2(1, 2, 1, 1, 0.0, 0.0, WEST, HORI, insets1, 0, 0));
		pContent.add(bOk, 		new GridBagConstraints2(0, 3, 1, 1, 0.0, 0.0, WEST, NONE, insets1, 0, 0));
		pContent.add(bCancel, 	new GridBagConstraints2(1, 3, 1, 1, 0.0, 0.0, EAST, NONE, insets1, 0, 0));

		lInfo.setIconTextGap(16);
		lInfo.setIcon(javax.swing.UIManager.getIcon("OptionPane.questionIcon"));

	}

	/**
	 * Insert the method's description here. Creation date: (11/16/01 4:12:47
	 * PM)
	 * 
	 * @param args
	 *            java.lang.String[]
	 */
	public static void main(String[] args) {

		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
		}

		SLocale.setDefaultLocale(java.util.Locale.GERMAN);

		final InputDialog dialog = new InputDialog(null, true);

		dialog.setLocation(100, 100);
		dialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		dialog.show();

		System.out.println(dialog.getInputString());
		System.exit(0);

	}

	/**
	 * Insert the method's description here.
	 * 
	 * @param maxValue
	 *            long
	 * @param curentValue
	 *            long
	 */
	public void setDetails(String text) {
		lDetails.setText(text);
		lDetails.repaint();
	}

	/**
	 * Insert the method's description here.
	 * 
	 * @param maxValue
	 *            long
	 * @param curentValue
	 *            long
	 */
	public void setInfo(String text) {
		lInfo.setText(text);
		lInfo.repaint();
	}

	/**
	 * Dialog anzeigen (Parent Frame disabled)
	 * 
	 * Creation date: (27.10.00 11:29:13)
	 *  
	 */
	@Override
    public void show() {
		Component frame = SUtilities.getRootFrame(this);
		boolean oldEnabled = true;
		try {
			if (frame != null) {
				oldEnabled = frame.isEnabled();
				frame.setEnabled(false);
			}
			super.show();

		} finally {
			if (frame != null)
				frame.setEnabled(oldEnabled);
		}
		bOk.requestFocus();
	}

	public String getInputString() {
		if (input != null) {
			if (input instanceof char[]) {
				return new String((char[])input);
			}
            return input.toString();
		}
        return null;
	}
}