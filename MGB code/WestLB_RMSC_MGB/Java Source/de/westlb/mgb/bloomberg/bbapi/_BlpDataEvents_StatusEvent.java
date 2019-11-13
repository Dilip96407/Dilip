// Event Interface 
package de.westlb.mgb.bloomberg.bbapi;

public class _BlpDataEvents_StatusEvent extends java.util.EventObject
{
   /**
     * 
     */
    private static final long serialVersionUID = -5071112399416835626L;
int[] Status;
   int[] SubStatus;
   Object StatusDescription;

   public _BlpDataEvents_StatusEvent(Object source, int[] Status, int[] SubStatus, Object StatusDescription)
   {
      super(source);
      this.Status = Status;
      this.SubStatus = SubStatus;
      this.StatusDescription = StatusDescription;
   }


   public int get_Status()
   {
      return Status[0];
   }


   public void set_Status(int Status)
   {
      this.Status[0] = Status;
   }


   public int get_SubStatus()
   {
      return SubStatus[0];
   }


   public void set_SubStatus(int SubStatus)
   {
      this.SubStatus[0] = SubStatus;
   }


   public Object get_StatusDescription()
   {
      return StatusDescription;
   }

}