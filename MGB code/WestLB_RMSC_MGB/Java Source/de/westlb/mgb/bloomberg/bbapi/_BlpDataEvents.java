// Event Interface 
package de.westlb.mgb.bloomberg.bbapi;

public interface _BlpDataEvents extends java.util.EventListener
{
   public void Data(_BlpDataEvents_DataEvent e);
   public String id_1 = "_BlpDataEvents.Data.V";

   public void Status(_BlpDataEvents_StatusEvent e);
   public String id_2 = "_BlpDataEvents.Status.V";

   public String event_package = "bbl12";
}