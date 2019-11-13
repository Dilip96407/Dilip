package de.westlb.mgb.client.mail;

import java.beans.PropertyChangeSupport;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import de.westlb.mgb.client.mail.parser.Parser;



/**
 * MailConnection.java
 * 
 * Copyright (c) 2003, WestLB Systems
 * 
 * All rights reserved
 * This information contained herin may not be used in whole 
 * or in part without the expressed written consent of WestLB Systems
 *
 * Description:
 * 
 * Contains the functionality to send an mgb email.
 *
 *@version 1.0
 *@author M. Boerner
 *
 * 
*/
public class MailConnection {
   private static final Logger log = Logger.getLogger(MailConnection.class);
	private Vector<MailConnectionListener> listeners = new Vector<MailConnectionListener>();
    private Properties properties;
    private Session mailSession;

	private static final String DEFAULT_MAIL_PROPERTY_FILE = "mail.properties";
	    
	/**
	 * Constructor for MailConnection.
	 */
	public MailConnection() {
		this(DEFAULT_MAIL_PROPERTY_FILE);
	}
	
	public MailConnection(Properties properties) {
		this.properties = properties;
		init();
	}
	
	public MailConnection(String propertyFile) {
		if (propertyFile != null) {
			properties = new Properties();
			try {
				properties.load(MailConnection.class.getClassLoader().getResourceAsStream(propertyFile));
			} catch (IOException e) {
				throw new IllegalArgumentException("Create mail connection failed. Can not load class file " + propertyFile);
			}
		}		
		init();
	}
	
	
	/**
	 * Merges dynamic content into the mail content and subject
	 * and starts the mail send.
	 *
	 * @param The Mail entity to send.
	 * @param parameter Contains a list of paramters which could be merged into the mail.
	 * @throws 
	 */
	public synchronized void sendMail(Mail mail) throws MailException {
		Map<String, Object> parameter = mail.getParameter();
		MimeMessage mimeMessage = new MimeMessage(mailSession);
		
		if (mail.getSubject() == null) {
			mail.setSubject(computeForm(mail.getSubjectFormName(), parameter, mail.isLoadFromProperties()));
		}

		if (mail.getText() == null) {
			mail.setText(computeForm(mail.getTextFormName(), parameter, mail.isLoadFromProperties()));
		}
				
		try {				
		    String sender = mail.getSender();
		    if (sender != null) {
		    	InternetAddress fromAddress;
				fromAddress = new InternetAddress(sender);
				mimeMessage.setFrom(fromAddress);
				log.debug("fromAddress "+fromAddress);
		    }

			if (mail.getAcknowledgment() != null) {
				mimeMessage.addHeader("Return-Receipt-To", mail.getAcknowledgment());
			}
			log.debug("acknowledgment "+mail.getAcknowledgment());

	
		    // Empfaenger der Mail eintragen
		    ArrayList<InternetAddress> originalRecipientsList = new ArrayList<InternetAddress>();
		    Iterator<String> originalRecipientsIterator = mail.getOriginalRecipients().iterator();
		    if (originalRecipientsIterator.hasNext()) {
		    	while (originalRecipientsIterator.hasNext()) {
		    	    originalRecipientsList.add(new InternetAddress(originalRecipientsIterator.next()));
		    	}
		    	InternetAddress[] recipientAdress = originalRecipientsList.toArray(new InternetAddress[originalRecipientsList.size()]);
				mimeMessage.setRecipients(Message.RecipientType.TO, recipientAdress); 	
		    }
		    
		    // Kopie-Empfaenger der Mail eintragen
		    ArrayList<InternetAddress> copyRecipientsList = new ArrayList<InternetAddress>();
		    Iterator<String> copyRecipientsIterator = mail.getCopyRecipients().iterator();
		    if (copyRecipientsIterator.hasNext()) {
		    	while (copyRecipientsIterator.hasNext()) {
		    	    copyRecipientsList.add(new InternetAddress(copyRecipientsIterator.next()));
		    	}
		    	InternetAddress[] recipientAdress = copyRecipientsList.toArray(new InternetAddress[copyRecipientsList.size()]);
				mimeMessage.setRecipients(Message.RecipientType.CC, recipientAdress);
		    }
		    	    
		    mimeMessage.setSentDate(new Date());
			mimeMessage.setSubject(mail.getSubject());
		    
		   // create the message part 
			MimeBodyPart messageBodyPart =  new MimeBodyPart();
		    //fill message
		    messageBodyPart.setText(mail.getText());
		
		    Multipart multipart = new MimeMultipart();
		    multipart.addBodyPart(messageBodyPart);
			
		    // Add all attachments
		    Iterator<Attachment> iterator = mail.getAttachments().iterator();
			while (iterator.hasNext()) {
				Attachment attachment = iterator.next();
				
			    messageBodyPart = new MimeBodyPart();
			    DataSource source =  new ByteArrayDataSource(attachment.getContent(), attachment.getType());
			    messageBodyPart.setDataHandler(new DataHandler(source));
			    messageBodyPart.setFileName(attachment.getFileName());
			    multipart.addBodyPart(messageBodyPart);
			}
		
		    // Put parts in message
		    mimeMessage.setContent(multipart);
		    Transport.send(mimeMessage); 
		} catch (MessagingException me) {
			log.error(me);
			throw new MailException(me.getMessage(), me);			
		}
	    
	    MailConnectionEvent mailEvent = new MailConnectionEvent();
	    mailEvent.setCopyRecipients(mail.getCopyRecipients());
	    mailEvent.setOriginalRecipients(mail.getOriginalRecipients());
	    mailEvent.setSubject(mail.getSubject());
	    mailEvent.setText(mail.getText());
	    mailEvent.setParameters(mail.getParameter());
	    
	   	fireSendMailCompletedEvent(mailEvent);
	} 
	
	public void computeForms(Mail mail) throws MailException {
		boolean l = mail.isLoadFromProperties();
		mail.setSubject(computeForm(mail.getSubjectFormName(), mail.getParameter(), l));
		mail.setText(computeForm(mail.getTextFormName(), mail.getParameter(), l));
		mail.setSender(computeForm(mail.getSenderFormName(), mail.getParameter(), l));
	}
	
	/**
	 * Fills the form with dynamic content.
	 */
	private String computeForm(String formName, Map<String, Object> params, boolean loadFromProperties) throws MailException {
		String form = null;
		if (loadFromProperties) {
			form = properties.getProperty(formName.toLowerCase());
			if (form == null) {
				throw new MailException("FormName " + formName + " does not exist in properties!");
			}
		} else {
			form = formName;
		}
		
		String result = null;
		try {
			result = Parser.parseTemplate(form, params);
		} catch (Exception e) {
			throw new MailException("Parsing of mail template failed. Msg = <" + e.getMessage() + ">", e);
		}
		
		return result;
	}
		
	/**
	 * Initializes the mail session.
	 */
	private void init() {
		Properties msp = new Properties();
		msp.setProperty("mail.transport.protocol", properties.getProperty("mail.transport.protocol"));
		msp.setProperty("mail.smtp.host", properties.getProperty("mail.smtp.host"));
		msp.setProperty("mail.smtp.port", properties.getProperty("mail.smtp.port"));
		mailSession = Session.getInstance(msp);
	}
	

	/**
	 * This class implements a DataSource from: 
	 *  an InputStream, a byte array or a String.
	 *
	 */
	public static class ByteArrayDataSource implements DataSource {
	    private byte[] data;	// data
	    private String type;	// content-type
	
	    /* Create a DataSource from an input stream */
	    public ByteArrayDataSource(InputStream is, String type) {
	        this.type = type;
	        try { 
	            ByteArrayOutputStream os = new ByteArrayOutputStream();
		    int ch;
	
		    while ((ch = is.read()) != -1) {
		    	// ToDo buffered read would improve the performance.
		        os.write(ch);
		    }
		    data = os.toByteArray();
	
	        } catch (IOException ioex) { }
	    }
	
        /* Create a DataSource from a byte array */
        public ByteArrayDataSource(byte[] data, String type) {
            this.data = data;
            this.type = type;
        }
	
        /* Create a DataSource from a String */
        public ByteArrayDataSource(String data, String type) {
            try {
                // Assumption that the string contains only ASCII
                // characters!  Otherwise just pass a charset into this
                // constructor and use it in getBytes()
                this.data = data.getBytes("iso-8859-1");
            } catch (UnsupportedEncodingException uex) {
            }
            this.type = type;
        }
	
	    /**
	     * Return an InputStream for the data.
	     * Note - a new stream must be returned each time.
	     */
	    @Override
        public InputStream getInputStream() throws IOException {
			if (data == null)
			    throw new IOException("no data");
			return new ByteArrayInputStream(data);
	    }
	
	    @Override
        public OutputStream getOutputStream() throws IOException {
		throw new IOException("cannot do this");
	    }
	
	    @Override
        public String getContentType() {
	        return type;
	    }
	
	    @Override
        public String getName() {
	        return "dummy";
	    }
	}
	
	protected transient PropertyChangeSupport propertyChange;
	
	private void fireSendMailCompletedEvent(MailConnectionEvent event) throws MailException {
		Iterator<MailConnectionListener> it = listeners.iterator();
		while (it.hasNext()) {
			MailConnectionListener listener = it.next();
			listener.sendMailCompleted(event);
		}
	}

	/**
	 * Add a PropertyChangeListener to listener list
	 */
	public synchronized void addMailConnectionListener(MailConnectionListener listener) {
		listeners.add(listener);
	}


	/**
	 * Remove a PropertyChangeListener from listener list
	 */
	public synchronized void removeMailConnectionListener(MailConnectionListener listener) {
		listeners.remove(listener);
	}
	
	@SuppressWarnings("unused")
    private PropertyChangeSupport getPropertyChangeSupport() {
		if (propertyChange == null) {
			propertyChange = new PropertyChangeSupport(this);
		}

		return propertyChange;
	}

}
