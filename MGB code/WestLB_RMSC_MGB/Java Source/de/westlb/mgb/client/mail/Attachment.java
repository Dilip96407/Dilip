package de.westlb.mgb.client.mail;

/**
 * @author WSY4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Attachment {
    public static final String TYPE_PDF = "application/pdf";
    public static final String TYPE_TXT = "text/txt";
    
    private byte[] content;
    private String fileName;
    private String type;

	public Attachment() {
	}
	
	public Attachment(byte[] content, String fileName, String type) {
		this.content = content;
		this.fileName = fileName;
		this.type = type;
	}

	/**
	 * @return
	 */
	public byte[] getContent() {
		return content;
	}

	/**
	 * @return
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param bs
	 */
	public void setContent(byte[] bs) {
		content = bs;
	}

	/**
	 * @param string
	 */
	public void setFileName(String string) {
		fileName = string;
	}

	/**
	 * @param string
	 */
	public void setType(String string) {
		type = string;
	}

}
