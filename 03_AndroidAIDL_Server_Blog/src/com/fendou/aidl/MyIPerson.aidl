package com.fendou.aidl;
import com.fendou.aidl.Person;

interface MyIPerson{
	String getPerson(in Person mPerson);
}