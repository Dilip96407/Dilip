// Dispatch Proxies 
package de.westlb.mgb.bloomberg.bbapi;
import com.ibm.bridge2java.COMconstants;
import com.ibm.bridge2java.Dispatch;
import com.ibm.bridge2java.Jvariant;

public class IBlpData2 extends Dispatch implements COMconstants
{
   public static final String clsid = "{88CDB8B0-B671-11D4-BBC9-00D0B7AE63AD}";

   public IBlpData2() 
   {
      super(clsid);
   }

   public IBlpData2(String clsidin) 
   {
      super(clsidin);
   }

   public IBlpData2(int IDispatch) 
   {
      super(IDispatch);
   }

   public IBlpData2(Object theObject) 
   {
      super(theObject);
   }

   public IBlpData2(int canvasHWND, int nullval) 
   {
      super(clsid, canvasHWND);
   }

   public IBlpData2(String clsidin, int canvasHWND, int nullval) 
   {
      super(clsidin, canvasHWND);
   }

   public void Subscribe(Object Security, int cookie, Object Fields, Object OverrideFields, Object Overrides, Object[] Results, Object Monitor)
   {
      Jvariant args[] = {new Jvariant(Security,VT_VARIANT), new Jvariant(cookie,VT_I4), new Jvariant(Fields,VT_VARIANT), new Jvariant(OverrideFields,VT_VARIANT), new Jvariant(Overrides,VT_VARIANT), new Jvariant(Results,VT_VARIANT | VT_BYREF), new Jvariant(Monitor,VT_VARIANT)};
      invoke_method_void(args, 0x1, DISPATCH_METHOD);
   }

   public void Subscribe(Object Security, int cookie, Object Fields, Object OverrideFields, Object Overrides, Object[] Results)
   {
      Jvariant args[] = {new Jvariant(Security,VT_VARIANT), new Jvariant(cookie,VT_I4), new Jvariant(Fields,VT_VARIANT), new Jvariant(OverrideFields,VT_VARIANT), new Jvariant(Overrides,VT_VARIANT), new Jvariant(Results,VT_VARIANT | VT_BYREF)};
      invoke_method_void(args, 0x1, DISPATCH_METHOD);
   }

   public void Subscribe(Object Security, int cookie, Object Fields, Object OverrideFields, Object Overrides)
   {
      Jvariant args[] = {new Jvariant(Security,VT_VARIANT), new Jvariant(cookie,VT_I4), new Jvariant(Fields,VT_VARIANT), new Jvariant(OverrideFields,VT_VARIANT), new Jvariant(Overrides,VT_VARIANT)};
      invoke_method_void(args, 0x1, DISPATCH_METHOD);
   }

   public void Subscribe(Object Security, int cookie, Object Fields, Object OverrideFields)
   {
      Jvariant args[] = {new Jvariant(Security,VT_VARIANT), new Jvariant(cookie,VT_I4), new Jvariant(Fields,VT_VARIANT), new Jvariant(OverrideFields,VT_VARIANT)};
      invoke_method_void(args, 0x1, DISPATCH_METHOD);
   }

   public void Subscribe(Object Security, int cookie, Object Fields)
   {
      Jvariant args[] = {new Jvariant(Security,VT_VARIANT), new Jvariant(cookie,VT_I4), new Jvariant(Fields,VT_VARIANT)};
      invoke_method_void(args, 0x1, DISPATCH_METHOD);
   }

   public int get_SubscriptionMode()
   {
      return invoke_method(null, 0x2, DISPATCH_PROPERTYGET).intVal();
   }

   public void set_SubscriptionMode(int Value)
   {
      Jvariant args[] = {new Jvariant(Value,VT_I4)};
      invoke_method_void(args, 0x2, DISPATCH_PROPERTYPUT);
   }

   public void Desubscribe(String Security)
   {
      Jvariant args[] = {new Jvariant(Security,VT_BSTR)};
      invoke_method_void(args, 0x3, DISPATCH_METHOD);
   }

   public int get_Timeout()
   {
      return invoke_method(null, 0x8, DISPATCH_PROPERTYGET).intVal();
   }

   public void set_Timeout(int Value)
   {
      Jvariant args[] = {new Jvariant(Value,VT_I4)};
      invoke_method_void(args, 0x8, DISPATCH_PROPERTYPUT);
   }

   public int get_PollingTime()
   {
      return invoke_method(null, 0x9, DISPATCH_PROPERTYGET).intVal();
   }

   public void set_PollingTime(int Value)
   {
      Jvariant args[] = {new Jvariant(Value,VT_I4)};
      invoke_method_void(args, 0x9, DISPATCH_PROPERTYPUT);
   }

   public short get_Port()
   {
      return invoke_method(null, 0xa, DISPATCH_PROPERTYGET).shortVal();
   }

   public void set_Port(short Value)
   {
      Jvariant args[] = {new Jvariant(Value,VT_I2)};
      invoke_method_void(args, 0xa, DISPATCH_PROPERTYPUT);
   }

   public short get_RetryCount()
   {
      return invoke_method(null, 0xb, DISPATCH_PROPERTYGET).shortVal();
   }

   public void set_RetryCount(short Value)
   {
      Jvariant args[] = {new Jvariant(Value,VT_I2)};
      invoke_method_void(args, 0xb, DISPATCH_PROPERTYPUT);
   }

   public String get_RemoteHost()
   {
      return invoke_method(null, 0xc, DISPATCH_PROPERTYGET).StringVal();
   }

   public void set_RemoteHost(String Value)
   {
      Jvariant args[] = {new Jvariant(Value,VT_BSTR)};
      invoke_method_void(args, 0xc, DISPATCH_PROPERTYPUT);
   }

   public Object get_StartTime()
   {
      return invoke_method(null, 0xd, DISPATCH_PROPERTYGET).ObjectVal();
   }

   public void GetHistoricalData(Object Security, int cookie, Object Fields, Object StartDate, Object EndDate, Object BarSize, Object BarFields, Object[] Results)
   {
      Jvariant args[] = {new Jvariant(Security,VT_VARIANT), new Jvariant(cookie,VT_I4), new Jvariant(Fields,VT_VARIANT), new Jvariant(StartDate,VT_VARIANT), new Jvariant(EndDate,VT_VARIANT), new Jvariant(BarSize,VT_VARIANT), new Jvariant(BarFields,VT_VARIANT), new Jvariant(Results,VT_VARIANT | VT_BYREF)};
      invoke_method_void(args, 0xe, DISPATCH_METHOD);
   }

   public void GetHistoricalData(Object Security, int cookie, Object Fields, Object StartDate, Object EndDate, Object BarSize, Object BarFields)
   {
      Jvariant args[] = {new Jvariant(Security,VT_VARIANT), new Jvariant(cookie,VT_I4), new Jvariant(Fields,VT_VARIANT), new Jvariant(StartDate,VT_VARIANT), new Jvariant(EndDate,VT_VARIANT), new Jvariant(BarSize,VT_VARIANT), new Jvariant(BarFields,VT_VARIANT)};
      invoke_method_void(args, 0xe, DISPATCH_METHOD);
   }

   public void GetHistoricalData(Object Security, int cookie, Object Fields, Object StartDate, Object EndDate, Object BarSize)
   {
      Jvariant args[] = {new Jvariant(Security,VT_VARIANT), new Jvariant(cookie,VT_I4), new Jvariant(Fields,VT_VARIANT), new Jvariant(StartDate,VT_VARIANT), new Jvariant(EndDate,VT_VARIANT), new Jvariant(BarSize,VT_VARIANT)};
      invoke_method_void(args, 0xe, DISPATCH_METHOD);
   }

   public void GetHistoricalData(Object Security, int cookie, Object Fields, Object StartDate, Object EndDate)
   {
      Jvariant args[] = {new Jvariant(Security,VT_VARIANT), new Jvariant(cookie,VT_I4), new Jvariant(Fields,VT_VARIANT), new Jvariant(StartDate,VT_VARIANT), new Jvariant(EndDate,VT_VARIANT)};
      invoke_method_void(args, 0xe, DISPATCH_METHOD);
   }

   public void GetHistoricalData(Object Security, int cookie, Object Fields, Object StartDate)
   {
      Jvariant args[] = {new Jvariant(Security,VT_VARIANT), new Jvariant(cookie,VT_I4), new Jvariant(Fields,VT_VARIANT), new Jvariant(StartDate,VT_VARIANT)};
      invoke_method_void(args, 0xe, DISPATCH_METHOD);
   }

   public void set_ReverseChronological(boolean Value)
   {
      Jvariant args[] = {new Jvariant(Value,VT_BOOL)};
      invoke_method_void(args, 0xf, DISPATCH_PROPERTYPUT);
   }

   public void set_DisplayNonTradingDays(int Value)
   {
      Jvariant args[] = {new Jvariant(Value,VT_I4)};
      invoke_method_void(args, 0x10, DISPATCH_PROPERTYPUT);
   }

   public void set_NonTradingDayValue(int Value)
   {
      Jvariant args[] = {new Jvariant(Value,VT_I4)};
      invoke_method_void(args, 0x11, DISPATCH_PROPERTYPUT);
   }

   public void set_Periodicity(int Value)
   {
      Jvariant args[] = {new Jvariant(Value,VT_I4)};
      invoke_method_void(args, 0x12, DISPATCH_PROPERTYPUT);
   }

   public void TickerLookup(String Name, int MarketSector, int cookie, Object Continue, Object[] Result)
   {
      Jvariant args[] = {new Jvariant(Name,VT_BSTR), new Jvariant(MarketSector,VT_I4), new Jvariant(cookie,VT_I4), new Jvariant(Continue,VT_VARIANT), new Jvariant(Result,VT_VARIANT | VT_BYREF)};
      invoke_method_void(args, 0x13, DISPATCH_METHOD);
   }

   public void TickerLookup(String Name, int MarketSector, int cookie, Object Continue)
   {
      Jvariant args[] = {new Jvariant(Name,VT_BSTR), new Jvariant(MarketSector,VT_I4), new Jvariant(cookie,VT_I4), new Jvariant(Continue,VT_VARIANT)};
      invoke_method_void(args, 0x13, DISPATCH_METHOD);
   }

   public void TickerLookup(String Name, int MarketSector, int cookie)
   {
      Jvariant args[] = {new Jvariant(Name,VT_BSTR), new Jvariant(MarketSector,VT_I4), new Jvariant(cookie,VT_I4)};
      invoke_method_void(args, 0x13, DISPATCH_METHOD);
   }

   public boolean get_AutoRelease()
   {
      return invoke_method(null, 0x14, DISPATCH_PROPERTYGET).booleanVal();
   }

   public void set_AutoRelease(boolean Value)
   {
      Jvariant args[] = {new Jvariant(Value,VT_BOOL)};
      invoke_method_void(args, 0x14, DISPATCH_PROPERTYPUT);
   }

   public void Flush()
   {
      invoke_method_void(null, 0x15, DISPATCH_METHOD);
   }

   public boolean get_ActivateRealtime()
   {
      return invoke_method(null, 0x16, DISPATCH_PROPERTYGET).booleanVal();
   }

   public void set_ActivateRealtime(boolean Value)
   {
      Jvariant args[] = {new Jvariant(Value,VT_BOOL)};
      invoke_method_void(args, 0x16, DISPATCH_PROPERTYPUT);
   }

   public void GenericSend(int cookie, int ServiceCode, Object MessageData, Object[] Results)
   {
      Jvariant args[] = {new Jvariant(cookie,VT_I4), new Jvariant(ServiceCode,VT_I4), new Jvariant(MessageData,VT_VARIANT), new Jvariant(Results,VT_VARIANT | VT_BYREF)};
      invoke_method_void(args, 0x17, DISPATCH_METHOD);
   }

   public void GenericSend(int cookie, int ServiceCode, Object MessageData)
   {
      Jvariant args[] = {new Jvariant(cookie,VT_I4), new Jvariant(ServiceCode,VT_I4), new Jvariant(MessageData,VT_VARIANT)};
      invoke_method_void(args, 0x17, DISPATCH_METHOD);
   }

   public void set_ParentWnd(int Value)
   {
      Jvariant args[] = {new Jvariant(Value,VT_I4)};
      invoke_method_void(args, 0x18, DISPATCH_PROPERTYPUT);
   }

   public int get_NumberPoints()
   {
      return invoke_method(null, 0x19, DISPATCH_PROPERTYGET).intVal();
   }

   public void set_NumberPoints(int Value)
   {
      Jvariant args[] = {new Jvariant(Value,VT_I4)};
      invoke_method_void(args, 0x19, DISPATCH_PROPERTYPUT);
   }

   public boolean get_ShowHistoricalDates()
   {
      return invoke_method(null, 0x1a, DISPATCH_PROPERTYGET).booleanVal();
   }

   public void set_ShowHistoricalDates(boolean Value)
   {
      Jvariant args[] = {new Jvariant(Value,VT_BOOL)};
      invoke_method_void(args, 0x1a, DISPATCH_PROPERTYPUT);
   }

   public int get_SendQueueSize()
   {
      return invoke_method(null, 0x1b, DISPATCH_PROPERTYGET).intVal();
   }

   public void set_SendQueueSize(int Value)
   {
      Jvariant args[] = {new Jvariant(Value,VT_I4)};
      invoke_method_void(args, 0x1b, DISPATCH_PROPERTYPUT);
   }

   public Object get_BLPSubscribe(Object Security, Object Fields, Object OverrideFields, Object Overrides)
   {
      Jvariant args[] = {new Jvariant(Security,VT_VARIANT), new Jvariant(Fields,VT_VARIANT), new Jvariant(OverrideFields,VT_VARIANT), new Jvariant(Overrides,VT_VARIANT)};
      return invoke_method(args, 0x1c, DISPATCH_PROPERTYGET).ObjectVal();
   }

   public Object get_BLPSubscribe(Object Security, Object Fields, Object OverrideFields)
   {
      Jvariant args[] = {new Jvariant(Security,VT_VARIANT), new Jvariant(Fields,VT_VARIANT), new Jvariant(OverrideFields,VT_VARIANT)};
      return invoke_method(args, 0x1c, DISPATCH_PROPERTYGET).ObjectVal();
   }

   public Object get_BLPSubscribe(Object Security, Object Fields)
   {
      Jvariant args[] = {new Jvariant(Security,VT_VARIANT), new Jvariant(Fields,VT_VARIANT)};
      return invoke_method(args, 0x1c, DISPATCH_PROPERTYGET).ObjectVal();
   }

   public Object get_BLPGetHistoricalData(Object Security, Object Fields, Object StartDate, Object EndDate, Object BarSize, Object BarFields)
   {
      Jvariant args[] = {new Jvariant(Security,VT_VARIANT), new Jvariant(Fields,VT_VARIANT), new Jvariant(StartDate,VT_VARIANT), new Jvariant(EndDate,VT_VARIANT), new Jvariant(BarSize,VT_VARIANT), new Jvariant(BarFields,VT_VARIANT)};
      return invoke_method(args, 0x1d, DISPATCH_PROPERTYGET).ObjectVal();
   }

   public Object get_BLPGetHistoricalData(Object Security, Object Fields, Object StartDate, Object EndDate, Object BarSize)
   {
      Jvariant args[] = {new Jvariant(Security,VT_VARIANT), new Jvariant(Fields,VT_VARIANT), new Jvariant(StartDate,VT_VARIANT), new Jvariant(EndDate,VT_VARIANT), new Jvariant(BarSize,VT_VARIANT)};
      return invoke_method(args, 0x1d, DISPATCH_PROPERTYGET).ObjectVal();
   }

   public Object get_BLPGetHistoricalData(Object Security, Object Fields, Object StartDate, Object EndDate)
   {
      Jvariant args[] = {new Jvariant(Security,VT_VARIANT), new Jvariant(Fields,VT_VARIANT), new Jvariant(StartDate,VT_VARIANT), new Jvariant(EndDate,VT_VARIANT)};
      return invoke_method(args, 0x1d, DISPATCH_PROPERTYGET).ObjectVal();
   }

   public Object get_BLPGetHistoricalData(Object Security, Object Fields, Object StartDate)
   {
      Jvariant args[] = {new Jvariant(Security,VT_VARIANT), new Jvariant(Fields,VT_VARIANT), new Jvariant(StartDate,VT_VARIANT)};
      return invoke_method(args, 0x1d, DISPATCH_PROPERTYGET).ObjectVal();
   }

   public boolean get_ShowYields()
   {
      return invoke_method(null, 0x1e, DISPATCH_PROPERTYGET).booleanVal();
   }

   public void set_ShowYields(boolean Value)
   {
      Jvariant args[] = {new Jvariant(Value,VT_BOOL)};
      invoke_method_void(args, 0x1e, DISPATCH_PROPERTYPUT);
   }

   public boolean get_DataOnlyInEvent()
   {
      return invoke_method(null, 0x1f, DISPATCH_PROPERTYGET).booleanVal();
   }

   public void set_DataOnlyInEvent(boolean Value)
   {
      Jvariant args[] = {new Jvariant(Value,VT_BOOL)};
      invoke_method_void(args, 0x1f, DISPATCH_PROPERTYPUT);
   }

} 
