// CoClass 
package de.westlb.mgb.bloomberg.bbapi;


public class BlpData extends IBlpData4
{
   public static final String clsid = "{F2303261-4969-11D1-B305-00805F815CBF}";

   public BlpData() 
   {
      super(clsid);
   }

   public BlpData(int IDispatch) 
   {
      super(IDispatch);
   }

   public BlpData(Object theObject) 
   {
      super(theObject);
   }

   public BlpData(int canvasHWND, int nullval) 
   {
      super(clsid, canvasHWND, nullval);
   }

   public void add_BlpDataEventsListener(_BlpDataEvents l)
   {
      addListener("{029D97D0-496B-11D1-B305-00805F815CBF}", l);
   }

   public void remove_BlpDataEventsListener(_BlpDataEvents l)
   {
      removeListener("{029D97D0-496B-11D1-B305-00805F815CBF}", l);
   }
}
