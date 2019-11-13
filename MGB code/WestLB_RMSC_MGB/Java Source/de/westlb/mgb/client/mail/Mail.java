package de.westlb.mgb.client.mail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Created on 06.03.2003
 *
 * MgbMail.java
 * 
 * Copyright (c) 2004, WestLB Systems
 * 
 * All rights reserved
 * This information contained herin may not be used in whole 
 * or in part without the expressed written consent of WestLB Systems
 *
 * Description:
 * Containg information about a MGB Mail.
 *
 *@version 1.0
 *@author WSY4148
 *
 * 
*/
public class Mail {
	private Collection<String> copyRecipients = new ArrayList<String>();
	private Collection<String> originalRecipients = new ArrayList<String>();
	private Collection<Attachment> attachments = new ArrayList<Attachment>();
	private Map<String, Object> parameter = new HashMap<String, Object>();
	private String textFormName;
	private String subjectFormName;
	private String text;
	private String subject;
	private String senderFormName;
	private String sender;
	private String acknowledgment;
	private boolean loadFromProperties;
	
	public Mail() {
	}
	
	public Mail(String senderFormName, String subjectFormName, String contentFormName) {
		this.senderFormName = senderFormName;
		this.textFormName = contentFormName;
		this.subjectFormName = subjectFormName;		
	}
	
	public Mail(String subjectFormName, String contentFormName) {
		this("mail.sender", subjectFormName, contentFormName);
	}
	
	/**
	 * Returns a specific form for the message body. The form may contain 
	 * besides fixed text elements that are dynamically replaced.
	 */
	public String getTextFormName() {
		return textFormName;
	}
	
	/**
	 * @see de.westlb.mgb.client.mail.AbstractMail#loadSubjectText()
	 */
	public String getSubjectFormName() {
		return subjectFormName;
	}


    /**
     * Returns the copyRecipient.
     * @return String[]
     */
    public Collection<String> getCopyRecipients() {
        return copyRecipients;
    }

    /**
     * Adds ad copyRecipient.
     * @param copyRecipient The copyRecipient to set
     */
    public void addCopyRecipient(String copyRecipient) {
        copyRecipients.add(copyRecipient);
    }

    /**
     * Returns the attachments.
     * @return Collection
     */
    public Collection<Attachment> getAttachments() {
        return attachments;
    }

    /**
     * Sets the attachments.
     * @param attachments The attachments to set
     */
	public void addAttachment(Attachment attachment) {
		attachments.add(attachment);
	}

   
    /**
     * Returns the originalRecipients.
     * @return String[]
     */
    public Collection<String> getOriginalRecipients() {
        return originalRecipients;
    }

    /**
     * Sets the originalRecipient.
     * @param originalRecipient The originalRecipient to set
     */
    public void addOriginalRecipient(String originalRecipient) {
        originalRecipients.add(originalRecipient);
    }

    /**
     * @see de.westlb.mgb.client.mail.Mail#getParameter()
     */
    public Map<String, Object> getParameter() {
        return parameter;
    }

    /**
     * @see de.westlb.mgb.client.mail.Mail#putParameter(String, String)
     */
    public void putParameter(String name, Object value) {
    	parameter.put(name, value);
    }
    
	/* (non-Javadoc)
	 * @see de.westlb.mgb.client.mail.Mail#getContent()
	 */
	public String getText() {
		return text;
	}

	/* (non-Javadoc)
	 * @see de.westlb.mgb.client.mail.Mail#getSubject()
	 */
	public String getSubject() {
		return subject;
	}

	/* (non-Javadoc)
	 * @see de.westlb.mgb.client.mail.Mail#setText(java.lang.String)
	 */
	public void setText(String content) {
		this.text = content;

	}

	/* (non-Javadoc)
	 * @see de.westlb.mgb.client.mail.Mail#setSubject(java.lang.String)
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return
	 */
	public String getSenderFormName() {
		return senderFormName;
	}

	/**
	 * @return
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * @param string
	 */
	public void setSender(String string) {
		sender = string;
	}

	/**
	 * @return
	 */
	public boolean isLoadFromProperties() {
		return loadFromProperties;
	}

	/**
	 * @param b
	 */
	public void setLoadFromProperties(boolean b) {
		loadFromProperties = b;
	}

	public String getAcknowledgment() {
		return acknowledgment;
	}
	
	public void setAcknowledgment(String acknowledgment) {
		this.acknowledgment = acknowledgment;
	}
}


