package de.westlb.mgb.client.mask.view.shared;

import java.awt.Image;
import java.awt.Insets;

import javax.swing.UIManager;

import org.apache.commons.lang3.StringUtils;

import de.westlb_systems.xaf.swing.GridBagConstraints2;
import de.westlb_systems.xaf.swing.SImageComponent;

/**
 * Mgb start mask.
 *
 * @author: Manfred Boerner
 *
 */
public class StartView extends AbstractView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6268478025246452283L;
	private SImageComponent pLogo = new SImageComponent();
	private String title = null;

	/**
	 * Konstruktor
	 *
	 */
	public StartView() {
		try {
			initComponents();
			initLayout();
			initLabels();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private Image getBackgroundImage() {
		return (Image)UIManager.get("HompageImage");
	}
	
	private String getMandantName() {
		return (String)UIManager.get("MandantName");
	}
	
	@Override
    public String getTitle() {
		if (title != null) {
			return title;
		}
		
		title = super.getTitle();
		if (title != null) {
			title = StringUtils.replace(title, "<MandantName>", getMandantName());
		}
		
		return title;
		
	}
	

	/**
	 * Initialization 
	 */
	private void initComponents() {
	}

	/**
	 * Intialization of labels
	 */
	private void initLabels() {
		pLogo.setImage(getBackgroundImage());
	}
	/**
	 * Create layout
	 */
	void initLayout() throws Exception {
		this.add(pLogo, new GridBagConstraints2(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints2.CENTER, GridBagConstraints2.BOTH, new Insets(10, 10, 10, 10), 0, 0));
	}

	/**
	 * Update of language dependent labels
	 *
	 */
	@Override
    public void updateLabels() {
		initLabels();
	}
}
