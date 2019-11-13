package de.westlb.mgb.client.mask.view.shared;

import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.UIManager;
import javax.swing.WindowConstants;

import de.westlb.mgb.client.application.ApplicationDefinitions;
import de.westlb.mgb.client.application.lookandfeel.LookAndFeel;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SButton;
import de.westlb_systems.xaf.swing.SDialog;
import de.westlb_systems.xaf.swing.SGridLayout;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.swing.SUtilities;
import de.westlb_systems.xaf.util.SLocale;
;

/**
 * Insert the type's description here.
 * Creation date: (11/16/01 3:42:40 PM)
 * @author: Herbert Benkhoff
 */
public class OptionDialog extends SDialog implements ActionListener, FocusListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3719308914774353328L;

	private static final String RESOURCE_BASE = "OPTION_";

    // UI-Komponenten
    private Insets insets1 = new Insets(4, 8, 4, 8);
    private Insets insets2 = new Insets(8, 8, 4, 8);
    private Insets insets3 = new Insets(12, 8, 4, 8);
    private int GAP = 8;

    private SPanel pContent = new SPanel();
    private SLabel lIcon = new SLabel("Icon");
    private SLabel lInfo1 = new SLabel("Info 1");
    private SLabel lInfo2 = new SLabel("Info 2");
    private SLabel lInfo3 = new SLabel("Info 3");

    private SButton bYes = new SButton("Yes");
    private SButton bNo = new SButton("No");
    private SButton bAll = new SButton("All");
    private SButton bCancel = new SButton("Cancel");
    private SButton bOK = new SButton("OK");
    private SButton bDefault = bCancel;

    private SPanel pButtons = new SPanel();
    private SGridLayout glButtons = new SGridLayout();

    private boolean initFocus = true;

    //****************

    public static final int TYPE_OVERWRITE 			= 1;
    public static final int TYPE_CANCEL_OPERATION 	= 2;
    public static final int TYPE_USE_LOCAL_FILE 		= 3;
	public static final int TYPE_TEMP_CHANGED		= 4;
    public static final int TYPE_SAVE_CHANGES 		= 5;
	
    private String typeParameter = null;

    public static final int OPTION_YES = 1;
    public static final int OPTION_NO = 2;
    public static final int OPTION_CANCEL = 4;
    public static final int OPTION_ALL = 8;
    public static final int OPTION_OK = 16;
    private int closeOption = -1;

    /**
     * OptionDialog constructor comment.
     */
    public OptionDialog(Component owner) {
        super(owner, null, true);

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.setResizable(false);

        jbInit();

        bYes.addActionListener(this);
        bNo.addActionListener(this);
        bAll.addActionListener(this);
        bOK.addActionListener(this);
        bCancel.addActionListener(this);

        bYes.addFocusListener(this);
        bNo.addFocusListener(this);
        bAll.addFocusListener(this);
        bOK.addFocusListener(this);
        bCancel.addFocusListener(this);

    }
    /**
     * actionPerformed method comment.
     */
    @Override
    public void actionPerformed(ActionEvent event) {

        Object source = event.getSource();

        if (source == bYes) {
            closeOption = OPTION_YES;
        } else if (source == bNo) {
            closeOption = OPTION_NO;
        } else if (source == bAll) {
            closeOption = OPTION_ALL;
        } else if (source == bOK) {
            closeOption = OPTION_OK;
        } else if (source == bCancel) {
            closeOption = OPTION_CANCEL;
        } else {
            return;
        }

        dispose();

    }
    /**
     * Insert the method's description here.
     * Creation date: (11/21/01 10:12:57 AM)
     * @param type int
     */
    private void configure(int type) {

        int buttons = OPTION_CANCEL;
        String base = RESOURCE_BASE;

        bYes.setText(getResourceString(base + "B_YES"));
        bNo.setText(getResourceString(base + "B_NO"));
        bAll.setText(getResourceString(base + "B_ALL"));
        bCancel.setText(getResourceString(base + "B_CANCEL"));
        bOK.setText(getResourceString(base + "B_OK"));

/*
	UIManager.getIcon("OptionPane.errorIcon");
	UIManager.getIcon("OptionPane.informationIcon");
	UIManager.getIcon("OptionPane.warningIcon");
	UIManager.getIcon("OptionPane.questionIcon");
*/
        switch (type) {
            case TYPE_CANCEL_OPERATION :
                setTitle(getResourceString(base + "T_QUESTION"));
                lIcon.setIcon(javax.swing.UIManager.getIcon("OptionPane.questionIcon"));
                lIcon.setText(getResourceString(base + "Q_101"));
                lInfo1.setText(null);
                lInfo2.setText(null);
                lInfo3.setText(null);
                buttons = OPTION_YES | OPTION_NO;
                bDefault = bNo;
                closeOption = OPTION_NO;
                break;
            case TYPE_OVERWRITE :
                setTitle(getResourceString(base + "T_QUESTION"));
                lIcon.setIcon(javax.swing.UIManager.getIcon("OptionPane.questionIcon"));
                lIcon.setText(getResourceString(base + "Q_201"));
                lInfo1.setText(insertInlays(getResourceString(base + "Q_202"), new String[] { typeParameter }));
                lInfo2.setText(getResourceString(base + "Q_203"));
                lInfo3.setText(null);
                buttons = OPTION_YES | OPTION_NO | OPTION_ALL | OPTION_CANCEL;
                bDefault = bAll;
                closeOption = OPTION_CANCEL;
                break;
            case TYPE_USE_LOCAL_FILE :
                setTitle(getResourceString(base + "T_QUESTION"));
                lIcon.setIcon(javax.swing.UIManager.getIcon("OptionPane.questionIcon"));
                lIcon.setText(getResourceString(base + "Q_301"));
                lInfo1.setText(insertInlays(getResourceString(base + "Q_302"), new String[] { typeParameter }));
                lInfo2.setText(getResourceString(base + "Q_303"));
                lInfo3.setText(null);
                buttons = OPTION_YES | OPTION_NO | OPTION_CANCEL;
                bDefault = bYes;
                closeOption = OPTION_CANCEL;
                break;
            case TYPE_TEMP_CHANGED :
                setTitle(getResourceString(base + "T_WARNING"));
                lIcon.setIcon(javax.swing.UIManager.getIcon("OptionPane.warningIcon"));
                lIcon.setText(getResourceString(base + "Q_401"));
                lInfo1.setText(insertInlays(getResourceString(base + "Q_402"), new String[] { typeParameter }));
                lInfo2.setText(getResourceString(base + "Q_403"));
                lInfo3.setText(null);
                buttons = OPTION_YES;
                bDefault = bYes;
                closeOption = OPTION_YES;
                break;            
           case TYPE_SAVE_CHANGES :
                setTitle(getResourceString(base + "T_QUESTION"));
                lIcon.setIcon(javax.swing.UIManager.getIcon("OptionPane.questionIcon"));
                lIcon.setText(getResourceString(base + "Q_501"));
                lInfo1.setText(typeParameter);
                lInfo2.setText(null);
                lInfo3.setText(null);
                buttons = OPTION_YES | OPTION_NO | OPTION_CANCEL;
                bDefault = bYes;
                closeOption = OPTION_CANCEL;
                break;
            default :
                break;
        }

        lInfo1.setVisible(lInfo1.getText() != null);
        lInfo2.setVisible(lInfo2.getText() != null);
        lInfo3.setVisible(lInfo3.getText() != null);

        pButtons.removeAll();
        if ((buttons & OPTION_YES) != 0) {
            pButtons.add(bYes);
        }
        if ((buttons & OPTION_NO) != 0) {
            pButtons.add(bNo);
        }
        if ((buttons & OPTION_ALL) != 0) {
            pButtons.add(bAll);
        }
        if ((buttons & OPTION_OK) != 0) {
            pButtons.add(bOK);
        }
        if ((buttons & OPTION_CANCEL) != 0) {
            pButtons.add(bCancel);
        }

    }

    /**
     * Returnes the name of the class without package prefix as
     * name for the resource file.
     */
    @Override
    protected String getResourceName() {
        String className = getClass().getName();
        String name = ApplicationDefinitions.LABEL_PATH + className.substring(className.lastIndexOf(".") + 1);

        return name;
    }

    /**
     * Insert the method's description here.
     * Creation date: (11/21/01 10:58:38 AM)
     */
    public void dummy() {
        return;
    }
    /**
     * focusGained method comment.
     */
    @Override
    public void focusGained(FocusEvent e) {
        requestDefaultFocus();
    }
    /**
     * focusLost method comment.
     */
    @Override
    public void focusLost(java.awt.event.FocusEvent e) {
    }


    /**
     * Insert the method's description here.
     *
     * @param base java.lang.String
     * @param inlays java.lang.String[]
     */
    public static String insertInlays(String base, String[] inlays) {

        StringBuffer result = new StringBuffer();

        for (int a = 0; a < base.length(); a++) {
            char c = base.charAt(a);
            if (c == '%') {
                a++;
                c = base.charAt(a);
                if (c >= '1' && c <= '9') {
                    int b = (c - '1');
                    if (inlays != null && inlays.length > b) {
                        result.append(inlays[b]);
                    }
                } else {
                    result.append(c);
                }
            } else {
                result.append(base.charAt(a));
            }
        }

        return result.toString();

    }
    /**
     * Insert the method's description here.
     * Creation date: (9/12/01 10:55:04 AM)
     */
    private void jbInit() {

        int WEST = GridBagConstraints2.WEST;
        int CENT = GridBagConstraints2.CENTER;
        int NONE = GridBagConstraints2.NONE;
        int HORI = GridBagConstraints2.HORIZONTAL;
        int BOTH = GridBagConstraints2.BOTH;

        setContentPane(pContent);
        pContent.setMargin(insets2);

        pContent.add(lIcon, new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, WEST, BOTH, insets1, 0, 0));
        pContent.add(lInfo1, new GridBagConstraints2(0, 1, 1, 1, 1.0, 1.0, WEST, HORI, insets1, 0, 0));
        pContent.add(lInfo2, new GridBagConstraints2(0, 2, 1, 1, 0.0, 0.0, WEST, HORI, insets1, 0, 0));
        pContent.add(lInfo3, new GridBagConstraints2(0, 3, 1, 1, 0.0, 0.0, WEST, HORI, insets1, 0, 0));
        pContent.add(pButtons, new GridBagConstraints2(0, 4, 1, 1, 0.0, 0.0, CENT, NONE, insets3, 0, 0));

        pButtons.setLayout(glButtons);

        glButtons.setHgap(GAP);
        glButtons.setHorizontalSplitPosition(0);

        lIcon.setIconTextGap(GAP);

    }
    /**
     * Insert the method's description here.
     * Creation date: (11/21/01 11:05:01 AM)
     * @param args java.lang.String[]
     */
    public static void main(String[] args) {

        try {
            //javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
            javax.swing.UIManager.setLookAndFeel(new LookAndFeel());
    	System.out.println("" + UIManager.getIcon("OptionPane.errorIcon"));
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SLocale.setDefaultLocale(java.util.Locale.GERMAN);

        OptionDialog dialog = new OptionDialog(null);
        dialog.showDialog(TYPE_TEMP_CHANGED, "c:\\temp\\test.doc");

        System.exit(0);
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/21/01 11:16:23 AM)
     * @return boolean
     */
    private boolean requestDefaultFocus() {

        if (initFocus) {
            initFocus = false;
            bDefault.requestFocus();
        }

        return true;
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

        initFocus = true;

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
    }
    /**
     * Insert the method's description here.
     * 
     *  Creation date: (20.11.01 15:52:57)
     * @return int
     * @param type int
     */
    public int showDialog(int type) {

        return showDialog(type, null);

    }
    /**
     * Insert the method's description here.
     * 
     *  Creation date: (20.11.01 15:52:57)
     * @return int
     * @param type int
     */
    public int showDialog(int type, String parameter) {

        typeParameter = parameter;

        configure(type);

        show();

        return closeOption;
    }
}
