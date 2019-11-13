// CoClass 
package de.westlb.mgb.bloomberg.bbapi;
import com.ibm.bridge2java.Dispatch;

public class BlpProps extends Dispatch
{
   public static final String clsid = "{FAF1EA90-98F4-11D2-A0AB-00805F01C257}";

   public BlpProps() 
   {
      super(clsid);
   }

   public BlpProps(int IDispatch) 
   {
      super(IDispatch);
   }

   public BlpProps(Object theObject) 
   {
      super(theObject);
   }

   public BlpProps(int canvasHWND, int nullval) 
   {
      super(clsid, canvasHWND);
   }
}
