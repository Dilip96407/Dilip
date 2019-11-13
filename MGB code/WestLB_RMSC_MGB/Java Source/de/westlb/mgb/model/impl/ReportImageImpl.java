package de.westlb.mgb.model.impl;

import java.io.Serializable;
import java.sql.Blob;

import de.westlb.mgb.model.Entity;

/**
 * @author D055625
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
/**
 * 
 * @modelguid {EC525431-9834-4EED-BDA2-07B08D7CAEA6}
 */
public class ReportImageImpl implements Entity, Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 6717123164766900644L;

    /** @modelguid {68A10D5A-E346-4687-A5CE-73B22C9F89BB} */
	private Long longId;

	/** @modelguid {4A3C2F2C-253C-4251-8D07-59B28C69DF49} */
	private String name;

	/** @modelguid {E2C2DB34-4CA0-448D-AC89-705750620617} */
	private Blob image;

	public String getUniqueFileName() {
	    return getLongId()+"_"+getName();
	}
	/**
	 * Returns the longId.
	 * @return long
	 * @modelguid {679996BD-0423-4131-89F8-EC05823F941D}
	 */
	public Long getLongId() {
		return longId;
	}

	/** @modelguid {51A31808-A8FE-4467-A1E5-4259EF461D49} */
	@Override
    public Serializable getId() {
		return longId;
	}

	/**
	 * Sets the longId.
	 * @param longId The longId to set
	 * @modelguid {5361E2D4-D3AA-463F-B836-41B9491B18BA}
	 */
	@SuppressWarnings("unused")
    private void setLongId(Long longId) {
		this.longId = longId;
	}

	/**
	 * Returns the name.
	 * @return String
	 * @modelguid {F2E909BB-5112-476F-ADC2-7CA8C9D152A2}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * @param name The name to set
	 * @modelguid {40350C6B-CAB8-459B-BDDB-ED5A86C60DB9}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @see de.westlb.mgb.model.Entity#setNullId()
	 * @modelguid {B7943621-D4FA-49D9-8F76-1A5BF0B4552D}
	 */
	@Override
    public void setNullId() {
		this.longId = null;
	}

	/**
	 * Returns the image.
	 * @return Blob
	 * @modelguid {BD96B8B2-9BBB-49DA-9655-8A16E1163BD8}
	 */
	public Blob getImage() {
		return image;
	}

	/**
	 * Sets the image.
	 * @param image The image to set
	 * @modelguid {8ED1F1C4-085B-49B6-A432-DD62208427D9}
	 */
	public void setImage(Blob image) {
		this.image = image;
	}

}
