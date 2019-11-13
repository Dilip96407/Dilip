/**
 * MgbServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server;

public class MgbServiceTestCase extends junit.framework.TestCase {
    public MgbServiceTestCase(java.lang.String name) {
        super(name);
    }

    public void testMgbWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new de.westlb.mgb.client.server.MgbServiceLocator().getMgbAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new de.westlb.mgb.client.server.MgbServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1MgbClose() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.close();
        // TBD - validate results
    }

    public void test2MgbStartReadAttachment() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        long value = -3;
        value = binding.startReadAttachment(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test3MgbEndReadAttachment() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.endReadAttachment(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test4MgbAssignTraderToEmployee() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.assignTraderToEmployee(new java.lang.Long(0), new java.lang.Long(0));
        // TBD - validate results
    }

    public void test5MgbGetCurrentReclamationState() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.TradeHistoryEntryVo value = null;
        value = binding.getCurrentReclamationState(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test6MgbGetTradeParameter() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.TradeParameterVo value = null;
        value = binding.getTradeParameter(new java.lang.String());
        // TBD - validate results
    }

    public void test7MgbGetStateStatistic() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.StateStatisticEntryVo value = null;
        value = binding.getStateStatistic(new java.lang.String());
        // TBD - validate results
    }

    public void test8MgbGetTradeReclamation() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.TradeReclamationVo value = null;
        value = binding.getTradeReclamation(new java.lang.String());
        // TBD - validate results
    }

    public void test9MgbFindTradesOverviewVo() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.TradeOverviewVo[] value = null;
        value = binding.findTradesOverviewVo(new de.westlb.mgb.client.server.vo.TradeSearchParamsVo());
        // TBD - validate results
    }

    public void test10MgbGetCurrentDataSelection() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.DataSelectionVo value = null;
        value = binding.getCurrentDataSelection();
        // TBD - validate results
    }

    public void test11MgbFindAllConditions() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.ConditionVo[] value = null;
        value = binding.findAllConditions();
        // TBD - validate results
    }

    public void test12MgbFindAllSourceSystems() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.SourceSystemVo[] value = null;
        value = binding.findAllSourceSystems();
        // TBD - validate results
    }

    public void test13MgbFindAllPriceCheckCategories() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.PriceCheckCategoryVo[] value = null;
        value = binding.findAllPriceCheckCategories();
        // TBD - validate results
    }

    public void test14MgbGetDualControlJobVo() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.DualControlJobVo value = null;
        value = binding.getDualControlJobVo(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test15MgbFindDualControlJobs() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.DualControlJobSearchResultEntryVo[] value = null;
        value = binding.findDualControlJobs(new de.westlb.mgb.client.server.vo.DualControlJobSearchParamsVo());
        // TBD - validate results
    }

    public void test16MgbDeleteExchangeMapping() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.deleteExchangeMapping(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test17MgbDeletePriceCheckCategory() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.deletePriceCheckCategory(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test18MgbFindAllBloombergMaturityCodes() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.BloombergMaturityCodeVo[] value = null;
        value = binding.findAllBloombergMaturityCodes();
        // TBD - validate results
    }

    public void test19MgbFindAllBloombergCurrencyCodes() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.BloombergCurrencyCodeVo[] value = null;
        value = binding.findAllBloombergCurrencyCodes();
        // TBD - validate results
    }

    public void test20MgbFindTradesMiscReclReqByEmployee() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.TradeSearchResultEntryVo[] value = null;
        value = binding.findTradesMiscReclReqByEmployee(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test21MgbFindTradesLateReclReqByEmployee() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.TradeSearchResultEntryVo[] value = null;
        value = binding.findTradesLateReclReqByEmployee(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test22MgbFindNewInstruments() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.InstrumentSearchResultEntryVo[] value = null;
        value = binding.findNewInstruments();
        // TBD - validate results
    }

    public void test23MgbSaveExchangeMapping() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.saveExchangeMapping(new de.westlb.mgb.client.server.vo.ExchangeMappingVo());
        // TBD - validate results
    }

    public void test24MgbGetPriceCheckCategory() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.PriceCheckCategoryVo value = null;
        value = binding.getPriceCheckCategory(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test25MgbSavePriceCheckCategory() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.savePriceCheckCategory(new de.westlb.mgb.client.server.vo.PriceCheckCategoryVo());
        // TBD - validate results
    }

    public void test26MgbGetUnsolvedRequests() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.RequestVo[] value = null;
        value = binding.getUnsolvedRequests(new java.lang.String());
        // TBD - validate results
    }

    public void test27MgbGetUnvalidatedRequests() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.RequestVo[] value = null;
        value = binding.getUnvalidatedRequests(new java.lang.String());
        // TBD - validate results
    }

    public void test28MgbConfirmDualControlJobs() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.confirmDualControlJobs(new java.lang.Long[0]);
        // TBD - validate results
    }

    public void test29MgbDeleteDualControlJobs() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.deleteDualControlJobs(new java.lang.Long[0]);
        // TBD - validate results
    }

    public void test30MgbGetAutoCheckTrades() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.AutoCheckVo[] value = null;
        value = binding.getAutoCheckTrades();
        // TBD - validate results
    }

    public void test31MgbSaveInitialTradeReclamationState() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.saveInitialTradeReclamationState(new java.lang.Long(0), new byte[0], new java.lang.String());
        // TBD - validate results
    }

    public void test32MgbGetRepoTradeOverview() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.RepoTradeOverviewVo value = null;
        value = binding.getRepoTradeOverview(new java.lang.String());
        // TBD - validate results
    }

    public void test33MgbGetMgbConfigurationValue() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.getMgbConfigurationValue(new java.lang.String());
        // TBD - validate results
    }

    public void test34MgbFindAllMgbConfigurations() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.MgbConfigurationVo[] value = null;
        value = binding.findAllMgbConfigurations();
        // TBD - validate results
    }

    public void test35MgbSaveMgbConfiguration() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.saveMgbConfiguration(new de.westlb.mgb.client.server.vo.MgbConfigurationVo());
        // TBD - validate results
    }

    public void test36MgbSetMarketDataProxyDirectory() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.setMarketDataProxyDirectory(new java.lang.String());
        // TBD - validate results
    }

    public void test37MgbGetStatisticReport() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.StatisticReportVo value = null;
        value = binding.getStatisticReport(new de.westlb.mgb.client.server.vo.StatisticReportParamsVo());
        // TBD - validate results
    }

    public void test38MgbGetStatisticCubeReport() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.StatisticCubeReportVo value = null;
        value = binding.getStatisticCubeReport(new de.westlb.mgb.client.server.vo.StatisticReportParamsVo());
        // TBD - validate results
    }

    public void test39MgbFindMailsByTradeId() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.MailSearchResultEntryVo[] value = null;
        value = binding.findMailsByTradeId(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test40MgbFindTraderInboxMails() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.InboxMailVo[] value = null;
        value = binding.findTraderInboxMails();
        // TBD - validate results
    }

    public void test41MgbSendTraderResponseMail() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.sendTraderResponseMail(new java.lang.Long(0), new java.lang.String());
        // TBD - validate results
    }

    public void test42MgbFindTradesReclRequired() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.TradeReclRequiredVo[] value = null;
        value = binding.findTradesReclRequired();
        // TBD - validate results
    }

    public void test43MgbPrepareAndSendMail() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.prepareAndSendMail(new de.westlb.mgb.client.server.vo.SendMailParamsVo[0]);
        // TBD - validate results
    }

    public void test44MgbRunAndSaveAutomaticCheck() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.runAndSaveAutomaticCheck(new de.westlb.mgb.client.server.vo.PriceVo[0]);
        // TBD - validate results
    }

    public void test45MgbFindTradesForStatisticRow() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.TradeSearchResultEntryVo[] value = null;
        value = binding.findTradesForStatisticRow(new de.westlb.mgb.client.server.vo.StatisticReportParamsVo());
        // TBD - validate results
    }

    public void test46MgbSaveReclStateAfterEmployeeReport() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.saveReclStateAfterEmployeeReport(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test47MgbIsTradeAccessGranted() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        boolean value = false;
        value = binding.isTradeAccessGranted(new java.lang.Long(0), new java.lang.String());
        // TBD - validate results
    }

    public void test48MgbSaveEmployeeAndRoles() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.Long value = null;
        value = binding.saveEmployeeAndRoles(new de.westlb.mgb.client.server.vo.EmployeeVo());
        // TBD - validate results
    }

    public void test49MgbGetStornoGroupFromTrade() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.Boolean value = null;
        value = binding.getStornoGroupFromTrade(new java.lang.String());
        // TBD - validate results
    }

    public void test50MgbFindPriceDeviations() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.PriceDeviationVo[] value = null;
        value = binding.findPriceDeviations(new de.westlb.mgb.client.server.vo.TradeSearchParamsVo());
        // TBD - validate results
    }

    public void test51MgbGetPriceDeviationStatistic() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.PriceDeviationStatisticVo[] value = null;
        value = binding.getPriceDeviationStatistic(new de.westlb.mgb.client.server.vo.TradeSearchParamsVo());
        // TBD - validate results
    }

    public void test52MgbFindAllTimePeriods() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String[] value = null;
        value = binding.findAllTimePeriods(new java.lang.String());
        // TBD - validate results
    }

    public void test53MgbFindAllReportClients() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String[] value = null;
        value = binding.findAllReportClients();
        // TBD - validate results
    }

    public void test54MgbLogin() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.login(new java.lang.String(), new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test55MgbDeleteTrader() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.deleteTrader(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test56MgbAdminLogin() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        boolean value = false;
        value = binding.adminLogin(new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test57MgbDeleteAddon() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.deleteAddon(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test58MgbDeleteJob() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.deleteJob(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test59MgbDeleteEmployee() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.deleteEmployee(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test60MgbGetUserStatistic() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.UserStatisticVo[] value = null;
        value = binding.getUserStatistic();
        // TBD - validate results
    }

    public void test61MgbGetDomainUser() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String value = null;
        value = binding.getDomainUser();
        // TBD - validate results
    }

    public void test62MgbDeleteExchange() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.deleteExchange(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test63MgbDeleteStateCode() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.deleteStateCode(new java.lang.String());
        // TBD - validate results
    }

    public void test64MgbFindAllAddons() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.AddonSearchResultEntryVo[] value = null;
        value = binding.findAllAddons();
        // TBD - validate results
    }

    public void test65MgbFindInstruments() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.InstrumentSearchResultEntryVo[] value = null;
        value = binding.findInstruments(new de.westlb.mgb.client.server.vo.InstrumentSearchParamsVo());
        // TBD - validate results
    }

    public void test66MgbGetExchange() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.ExchangeVo value = null;
        value = binding.getExchange(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test67MgbFindNewLocations() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.TradeSearchResultEntryVo[] value = null;
        value = binding.findNewLocations();
        // TBD - validate results
    }

    public void test68MgbGetInstrument() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.InstrumentVo value = null;
        value = binding.getInstrument(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test69MgbGetStateCode() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.StateCodeVo value = null;
        value = binding.getStateCode(new java.lang.String());
        // TBD - validate results
    }

    public void test70MgbFindAllJobs() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.JobVo[] value = null;
        value = binding.findAllJobs(new de.westlb.mgb.client.server.vo.JobSearchParamsVo());
        // TBD - validate results
    }

    public void test71MgbFindAllExchanges() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.ExchangeSearchResultEntryVo[] value = null;
        value = binding.findAllExchanges();
        // TBD - validate results
    }

    public void test72MgbGetDataSelection() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.DataSelectionVo value = null;
        value = binding.getDataSelection();
        // TBD - validate results
    }

    public void test73MgbGetAddon() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.AddonVo value = null;
        value = binding.getAddon(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test74MgbSetDataSelection() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.setDataSelection(new de.westlb.mgb.client.server.vo.DataSelectionVo());
        // TBD - validate results
    }

    public void test75MgbGetCheckState() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.CheckStateVo value = null;
        value = binding.getCheckState(new java.lang.Long[0]);
        // TBD - validate results
    }

    public void test76MgbGetCheckState() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.CheckStateVo value = null;
        value = binding.getCheckState();
        // TBD - validate results
    }

    public void test77MgbGetStateCodes() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.StateCodeVo[] value = null;
        value = binding.getStateCodes(new java.lang.String());
        // TBD - validate results
    }

    public void test78MgbFindTrades() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.TradeSearchResultEntryVo[] value = null;
        value = binding.findTrades(new de.westlb.mgb.client.server.vo.TradeSearchParamsVo());
        // TBD - validate results
    }

    public void test79MgbGetTradeOverview() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.TradeOverviewVo value = null;
        value = binding.getTradeOverview(new java.lang.String());
        // TBD - validate results
    }

    public void test80MgbCreateNewJob() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.createNewJob(new java.lang.String(), new byte[0]);
        // TBD - validate results
    }

    public void test81MgbGetHistory() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.TradeHistoryEntryVo[] value = null;
        value = binding.getHistory(new java.lang.String());
        // TBD - validate results
    }

    public void test82MgbSaveStateCode() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.saveStateCode(new de.westlb.mgb.client.server.vo.StateCodeVo());
        // TBD - validate results
    }

    public void test83MgbSaveTradeState() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.saveTradeState(new java.lang.Long[0], new de.westlb.mgb.client.server.vo.TradeHistoryEntryVo(), new byte[0]);
        // TBD - validate results
    }

    public void test84MgbSaveTradeState() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.saveTradeState(new de.westlb.mgb.client.server.vo.TradeHistoryEntryVo(), new byte[0]);
        // TBD - validate results
    }

    public void test85MgbReadAttachment() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        byte[] value = null;
        value = binding.readAttachment(new java.lang.Long(0), 0, 0);
        // TBD - validate results
    }

    public void test86MgbCloseReclamation() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.closeReclamation(new java.lang.Long(0), new java.lang.String());
        // TBD - validate results
    }

    public void test87MgbFindEmployees() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.EmployeeSearchResultEntryVo[] value = null;
        value = binding.findEmployees(new de.westlb.mgb.client.server.vo.EmployeeSearchParamsVo());
        // TBD - validate results
    }

    public void test88MgbFindNewTrader() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.TraderSearchResultEntryVo[] value = null;
        value = binding.findNewTrader();
        // TBD - validate results
    }

    public void test89MgbSaveAddon() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.saveAddon(new de.westlb.mgb.client.server.vo.AddonVo());
        // TBD - validate results
    }

    public void test90MgbGetEmployee() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.EmployeeVo value = null;
        value = binding.getEmployee(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test91MgbSaveExchange() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.saveExchange(new de.westlb.mgb.client.server.vo.ExchangeVo());
        // TBD - validate results
    }

    public void test92MgbSaveEmployee() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.Long value = null;
        value = binding.saveEmployee(new de.westlb.mgb.client.server.vo.EmployeeVo());
        // TBD - validate results
    }

    public void test93MgbGetPrice() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.PriceVo value = null;
        value = binding.getPrice(new de.westlb.mgb.client.server.vo.RequestVo());
        // TBD - validate results
    }

    public void test94MgbGetSessionInfo() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.SessionInfoVo value = null;
        value = binding.getSessionInfo();
        // TBD - validate results
    }

    public void test95MgbSavePrice() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.savePrice(new de.westlb.mgb.client.server.vo.RequestVo(), new de.westlb.mgb.client.server.vo.PriceVo());
        // TBD - validate results
    }

    public void test96MgbSavePrices() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.savePrices(new de.westlb.mgb.client.server.vo.RequestVo[0], new de.westlb.mgb.client.server.vo.PriceVo[0]);
        // TBD - validate results
    }

    public void test97MgbUpdateRequest() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.updateRequest(new de.westlb.mgb.client.server.vo.RequestVo());
        // TBD - validate results
    }

    public void test98MgbGetTradeState() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.TradeHistoryEntryVo value = null;
        value = binding.getTradeState(new java.lang.String());
        // TBD - validate results
    }

    public void test99MgbUpdateRequests() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.updateRequests(new de.westlb.mgb.client.server.vo.RequestVo[0]);
        // TBD - validate results
    }

    public void test100MgbGetStateRules() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.StateRulesVo[] value = null;
        value = binding.getStateRules();
        // TBD - validate results
    }

    public void test101MgbGetStornoGroup() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.StornoGroupVo[] value = null;
        value = binding.getStornoGroup(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test102MgbSetJobStatus() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.setJobStatus(new java.lang.Long(0), new java.lang.String());
        // TBD - validate results
    }

    public void test103MgbKeepAlive() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.keepAlive();
        // TBD - validate results
    }

    public void test104MgbFindAllBooks() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.BookSearchResultEntryVo[] value = null;
        value = binding.findAllBooks();
        // TBD - validate results
    }

    public void test105MgbAssignPriceCheckCategoryToInstrument() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.assignPriceCheckCategoryToInstrument(new java.lang.Long(0), new java.lang.Long(0));
        // TBD - validate results
    }

    public void test106MgbFindEmployeesByReclamationRequired() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.EmployeeSearchResultEntryVo[] value = null;
        value = binding.findEmployeesByReclamationRequired();
        // TBD - validate results
    }

    public void test107MgbFindTradeOverviewVosForStatisticRow() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.TradeOverviewVo[] value = null;
        value = binding.findTradeOverviewVosForStatisticRow(new de.westlb.mgb.client.server.vo.StatisticReportParamsVo());
        // TBD - validate results
    }

    public void test108MgbGetRequestPriceConversionFactorMap() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.Object value = null;
        value = binding.getRequestPriceConversionFactorMap();
        // TBD - validate results
    }

    public void test109MgbGetMail() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.MailVo value = null;
        value = binding.getMail(0);
        // TBD - validate results
    }

    public void test110MgbPrepareMail() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.MailVo value = null;
        value = binding.prepareMail(new de.westlb.mgb.client.server.vo.SendMailParamsVo());
        // TBD - validate results
    }

    public void test111MgbSendMail() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.sendMail(new de.westlb.mgb.client.server.vo.MailVo(), new byte[0]);
        // TBD - validate results
    }

    public void test112MgbIsTradeOwned() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        boolean value = false;
        value = binding.isTradeOwned(new java.lang.Long(0), new java.lang.String());
        // TBD - validate results
    }

    public void test113MgbFindAllEmployees() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.EmployeeVo[] value = null;
        value = binding.findAllEmployees(new de.westlb.mgb.client.server.vo.EmployeeSearchParamsVo());
        // TBD - validate results
    }

    public void test114MgbGetChildMails() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.MailVo[] value = null;
        value = binding.getChildMails(new java.lang.Long(0), true, new java.lang.String[0]);
        // TBD - validate results
    }

    public void test115MgbGetRecentJobs() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.JobVo[] value = null;
        value = binding.getRecentJobs(new java.lang.String());
        // TBD - validate results
    }

    public void test116MgbGetMgbTask() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.MgbTaskVo value = null;
        value = binding.getMgbTask(new java.lang.Long(0));
        // TBD - validate results
    }

    public void test117MgbSaveMgbTask() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.Long value = null;
        value = binding.saveMgbTask(new de.westlb.mgb.client.server.vo.MgbTaskVo());
        // TBD - validate results
    }

    public void test118MgbFetchPrices() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        boolean value = false;
        value = binding.fetchPrices(new java.lang.String(), new byte[0]);
        // TBD - validate results
    }

    public void test119MgbIsBusy() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        boolean value = false;
        value = binding.isBusy();
        // TBD - validate results
    }

    public void test120MgbGetClientConfig() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.Object value = null;
        value = binding.getClientConfig(new java.lang.String());
        // TBD - validate results
    }

    public void test121MgbSetClientConfig() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.setClientConfig(new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test122MgbRunSampleChecks() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        binding.runSampleChecks();
        // TBD - validate results
    }

    public void test123MgbGetStateCodeMap() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.Object value = null;
        value = binding.getStateCodeMap(new java.lang.String(), new java.lang.String());
        // TBD - validate results
    }

    public void test124MgbFindAllReports() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String[] value = null;
        value = binding.findAllReports();
        // TBD - validate results
    }

    public void test125MgbFindReports() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        java.lang.String[] value = null;
        value = binding.findReports(new java.lang.String());
        // TBD - validate results
    }

    public void test126MgbDownloadJobFile() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        byte[] value = null;
        value = binding.downloadJobFile(new java.lang.Long(0), new java.lang.String());
        // TBD - validate results
    }

    public void test127MgbFindAllMandants() throws Exception {
        de.westlb.mgb.client.server.MgbSoapBindingStub binding;
        try {
            binding = (de.westlb.mgb.client.server.MgbSoapBindingStub)
                          new de.westlb.mgb.client.server.MgbServiceLocator().getMgb();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test operation
        de.westlb.mgb.client.server.vo.MandantVo[] value = null;
        value = binding.findAllMandants();
        // TBD - validate results
    }

}
