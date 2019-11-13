/**
 * MgbServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server;

public class MgbServiceLocator extends org.apache.axis.client.Service implements de.westlb.mgb.client.server.MgbService {

    public MgbServiceLocator() {
    }


    public MgbServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MgbServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Mgb
    private java.lang.String Mgb_address = "http://localhost:8080/mgb/services/Mgb";

    public java.lang.String getMgbAddress() {
        return Mgb_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String MgbWSDDServiceName = "Mgb";

    public java.lang.String getMgbWSDDServiceName() {
        return MgbWSDDServiceName;
    }

    public void setMgbWSDDServiceName(java.lang.String name) {
        MgbWSDDServiceName = name;
    }

    public de.westlb.mgb.client.server.Mgb getMgb() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Mgb_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getMgb(endpoint);
    }

    public de.westlb.mgb.client.server.Mgb getMgb(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            de.westlb.mgb.client.server.MgbSoapBindingStub _stub = new de.westlb.mgb.client.server.MgbSoapBindingStub(portAddress, this);
            _stub.setPortName(getMgbWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setMgbEndpointAddress(java.lang.String address) {
        Mgb_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (de.westlb.mgb.client.server.Mgb.class.isAssignableFrom(serviceEndpointInterface)) {
                de.westlb.mgb.client.server.MgbSoapBindingStub _stub = new de.westlb.mgb.client.server.MgbSoapBindingStub(new java.net.URL(Mgb_address), this);
                _stub.setPortName(getMgbWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("Mgb".equals(inputPortName)) {
            return getMgb();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:mgb", "MgbService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:mgb", "Mgb"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Mgb".equals(portName)) {
            setMgbEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
