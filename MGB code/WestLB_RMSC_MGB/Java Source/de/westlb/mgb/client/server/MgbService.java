/**
 * MgbService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package de.westlb.mgb.client.server;

public interface MgbService extends javax.xml.rpc.Service {
    public java.lang.String getMgbAddress();

    public de.westlb.mgb.client.server.Mgb getMgb() throws javax.xml.rpc.ServiceException;

    public de.westlb.mgb.client.server.Mgb getMgb(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
