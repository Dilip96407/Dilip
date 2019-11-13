// Event Interface 
package de.westlb.mgb.bloomberg.bbapi;

public class _BlpDataEvents_DataEvent extends java.util.EventObject
{
   /**
     * 
     */
    private static final long serialVersionUID = -9111341228603016279L;
Object Security;
   int[] cookie;
   Object Fields;
   Object Data;
   int[] Status;

   public _BlpDataEvents_DataEvent(Object source, Object Security, int[] cookie, Object Fields, Object Data, int[] Status)
   {
      super(source);
      this.Security = Security;
      this.cookie = cookie;
      this.Fields = Fields;
      this.Data = Data;
      this.Status = Status;
   }


   public Object get_Security()
   {
      return Security;
   }


   public int get_cookie()
   {
      return cookie[0];
   }


   public void set_cookie(int cookie)
   {
      this.cookie[0] = cookie;
   }


   public Object get_Fields()
   {
      return Fields;
   }


   public Object get_Data()
   {
      return Data;
   }


   public int get_Status()
   {
      return Status[0];
   }


   public void set_Status(int Status)
   {
      this.Status[0] = Status;
   }

}