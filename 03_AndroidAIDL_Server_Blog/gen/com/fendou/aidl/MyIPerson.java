/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\AndroidWorkSpace\\AndroidBlog\\03_AndroidAIDL_Server_Blog\\src\\com\\fendou\\aidl\\MyIPerson.aidl
 */
package com.fendou.aidl;
public interface MyIPerson extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.fendou.aidl.MyIPerson
{
private static final java.lang.String DESCRIPTOR = "com.fendou.aidl.MyIPerson";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.fendou.aidl.MyIPerson interface,
 * generating a proxy if needed.
 */
public static com.fendou.aidl.MyIPerson asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.fendou.aidl.MyIPerson))) {
return ((com.fendou.aidl.MyIPerson)iin);
}
return new com.fendou.aidl.MyIPerson.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_getPerson:
{
data.enforceInterface(DESCRIPTOR);
com.fendou.aidl.Person _arg0;
if ((0!=data.readInt())) {
_arg0 = com.fendou.aidl.Person.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
java.lang.String _result = this.getPerson(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.fendou.aidl.MyIPerson
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public java.lang.String getPerson(com.fendou.aidl.Person mPerson) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((mPerson!=null)) {
_data.writeInt(1);
mPerson.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_getPerson, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_getPerson = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public java.lang.String getPerson(com.fendou.aidl.Person mPerson) throws android.os.RemoteException;
}
