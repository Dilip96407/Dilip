package de.westlb.mgb.client.application;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import de.westlb_systems.xaf.application.Mosaik;
import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SButton;
import de.westlb_systems.xaf.swing.SIcon;
import de.westlb_systems.xaf.swing.SLabel;
import de.westlb_systems.xaf.swing.SPanel;
import de.westlb_systems.xaf.ui.misc.IconKatalog;
import de.westlb_systems.xaf.ui.view.Dialog;
import de.westlb_systems.xaf.ui.view.DialogViewer;


public class AboutDialog extends Dialog implements ActionListener {

  /**
     * 
     */
    private static final long serialVersionUID = 4120507563498716826L;
SPanel pBackground = new SPanel();
  SPanel pTop = new SPanel();
  SPanel pButton = new SPanel();
  SPanel pImage = new SPanel();
  SPanel pText = new SPanel();
  SButton bOk = new SButton();
  SLabel imageControl1 = new SLabel();
  SIcon iLogo;
  SLabel lProduct = new SLabel();
  SLabel lVersion = new SLabel();
  SLabel lCopyright = new SLabel();
  SLabel lComments = new SLabel();
  FlowLayout flowLayout2 = new FlowLayout();
  GridLayout gridLayout1 = new GridLayout();
  String title     = null;
  String product   = null;
  String version   = null;
  String copyright = null;
  String comments  = null;

  public AboutDialog(Component parent) {
	super(parent);
	try  {
	  title     = getResourceString("ABOUT_T_001");
	  product   = getResourceString("ABOUT_L_PRODUCT");
	  version   = Version.instance().getVersionString();
	  copyright = getResourceString("ABOUT_L_COPYRIGHT");
	  comments  = getResourceString("ABOUT_L_COMMENTS");
	  jbInit();
	}
	catch(Exception e) {
	  e.printStackTrace();
	}
	imageControl1.setIcon(iLogo);
	pack();

	imageControl1.addMouseListener(new MouseAdapter(){
		@Override
        public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() >= 2){
				if((e.getModifiers() & InputEvent.CTRL_MASK) != 0) {
//					setVisible(false);
					new DialogViewer(new Mosaik(), AboutDialog.this).showDialog();
//					setVisible(true);
				}
			}
		}
	});
  }              
  @Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource() == bOk) {
	  cancel();
	}
  }  
  void cancel() {
	dispose();
  }  
  @Override
  public String getResourceName() {
	return ApplicationDefinitions.LABEL_PATH + "About";
  }  
  
  private void jbInit() throws Exception  {
	Font font = new Font("Arial Narrow", Font.PLAIN, 12);

	this.setTitle(title);
	pText.setLayout(gridLayout1);
	iLogo = IconKatalog.getInstance().getIcon("MGB_LOGO");
	setResizable(false);
	gridLayout1.setRows(4);
	gridLayout1.setColumns(1);
	lProduct.setFont(font);
	lProduct.setText(product);
	lVersion.setFont(font);
	lVersion.setText(version);
	lCopyright.setFont(font);
	lCopyright.setText(copyright);
	lComments.setFont(font);
	lComments.setText(comments);
	bOk.setFont(font);
	bOk.setText("OK");
	bOk.addActionListener(this);

	pImage.add(imageControl1, new GridBagConstraints2(0, 0, GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER, 1.0, 1.0
			,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	pTop.add(pImage, new GridBagConstraints2(0, 0, 1, 1, 1.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
	this.getContentPane().add(pBackground, null);
	pText.add(lVersion, null);
	pText.add(lCopyright, null);
	pText.add(lComments, null);
	pTop.add(pText, new GridBagConstraints2(0, 1, 1, 1, 1.0, 1.0
			,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(25, 46, 0, 0), 0, 0));
	pButton.add(bOk, null);
	pBackground.add(pButton, new GridBagConstraints2(0, 1, 1, 1, 1.0, 0.0
			,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(5, 10, 20, 10), 0, 0));
	pBackground.add(pTop, new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0
			,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(20, 60, 0, 60), 0, 0));
  }        
  @Override
  public void show() {
	getRootPane().setDefaultButton(bOk);
	bOk.requestFocus();
	super.show();
  }  
}
