package de.westlb.mgb.client.mail;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import junit.framework.TestCase;
import de.westlb.mgb.model.definition.MailDefinition;

/**
 * @author WSY4148
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MailConnectionTest extends TestCase {

	private MailConnection mailConnection;
    /**
     * Constructor for MailConnectionTest.
     */
    public MailConnectionTest() {
        super();
    }

    /**
     * Constructor for MailConnectionTest.
     * @param arg0
     */
    public MailConnectionTest(String arg0) {
        super(arg0);
    }

    /**
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
    	Properties properties = new Properties();
        properties.put("mail.transport.protocol",	"smtp");
		properties.put("mail.smtp.host",			"ntsnotes0010019.zs.westlb.sko.de");
		properties.put("mail.smtp.port",			"25");
		properties.put("mail.sender",				"TradeControl@WestLB.de");
		properties.put("mail.mgb.subject", 		"Trade reclamation");
		properties.put("mail.mgb.content", 		"\n[This e-mail has been automatically generated.].\nPlease fill the attached form and send it back until <#~responseTimeLimit~#>\n\n");
		mailConnection = new MailConnection(properties);
    }
    
    /**
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testSendMail() throws Exception {
    	GregorianCalendar calendar = new GregorianCalendar();
    	calendar.add(Calendar.DAY_OF_YEAR, -1);
    	Date yesterday = calendar.getTime();
    	
    	byte[] attachmentContent = new byte[] { '1', '2', '3' };
        Mail mail = new Mail(MailDefinition.RECLAMATION_SUBJECT_TEMPLATE, MailDefinition.RECLAMATION_CONTENT_TEMPLATE);
//        mail.addCopyRecipient("jens_richelsen@WestLB-Systems.de");
        mail.addOriginalRecipient("manfred_boerner@WestLB-Systems.de");
        
        mail.addAttachment(new Attachment(attachmentContent, "report.txt", Attachment.TYPE_TXT));
        mail.putParameter("responseTimeLimit", yesterday.toString());
        
       
       	mailConnection.sendMail(mail);
    }

}
