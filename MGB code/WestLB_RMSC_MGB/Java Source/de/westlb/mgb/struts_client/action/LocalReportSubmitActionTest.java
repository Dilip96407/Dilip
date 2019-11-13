package de.westlb.mgb.struts_client.action;

import junit.framework.TestCase;

public class LocalReportSubmitActionTest extends TestCase
{
    /**Tests the {@link LocalReportSubmitAction#getSheetName(String,String)} helper
     * method. */
    public void testGetSheetName()
    {
        LocalReportSubmitAction.maxSheetNameLength = 31;
        assertEquals("1_2",LocalReportSubmitAction.getSheetName("1","2"));
        assertEquals("01234567890123456789_0123456789",
                LocalReportSubmitAction.getSheetName("01234567890123456789","0123456789"));
        assertEquals("01234567890123456789_0123456789",
                LocalReportSubmitAction.getSheetName("012345678901234567890","0123456789"));
        assertEquals("01234567890123456789_0123456789",
                LocalReportSubmitAction.getSheetName("01234567890123456789012345678901234567890","0123456789"));
        assertEquals("012345678901234_012345678901234",
                LocalReportSubmitAction.getSheetName("01234567890123456789012345678901234567890","01234567890123456789"));
        assertEquals("012345678901234_012345678901234",
                LocalReportSubmitAction.getSheetName("01234567890123456789","01234567890123456789"));
        assertEquals("01234567890123_012345678901234",
                LocalReportSubmitAction.getSheetName("01234567890123","01234567890123456789"));
        assertEquals("012345678901234_012345678901234",
                LocalReportSubmitAction.getSheetName("012345678901234","01234567890123456789"));
        assertEquals("012345678901234_012345678901234",
                LocalReportSubmitAction.getSheetName("0123456789012345","01234567890123456789"));
        assertEquals("0123456789012345_01234567890123",
                LocalReportSubmitAction.getSheetName("0123456789012345","01234567890123"));
        assertEquals("0_012345678901234",
                LocalReportSubmitAction.getSheetName("0","01234567890123456789"));
    }
}