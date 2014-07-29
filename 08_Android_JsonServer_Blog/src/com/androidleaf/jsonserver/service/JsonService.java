package com.androidleaf.jsonserver.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.androidleaf.jsonserver.entity.Person;

public class JsonService {

	public JsonService() {
		// TODO Auto-generated constructor stub
	}

	public com.androidleaf.jsonserver.entity.Person getPerson() {
		Person person = new Person();
		person.setId(1001);
		person.setUserName("jack");
		person.setPassword("123");
		return person;
	}

	public List<Person> getlistPerson() {
		List<Person> list = new ArrayList<Person>();
		Person person1 = new Person();
		person1.setId(1001);
		person1.setUserName("jack");
		person1.setPassword("123");
		Person person2 = new Person();
		person2.setId(1002);
		person2.setUserName("rose");
		person2.setPassword("123");
		Person person3 = new Person();
		person3.setId(1003);
		person3.setUserName("joly");
		person3.setPassword("123");
		list.add(person1);
		list.add(person2);
		list.add(person3);
		return list;
	}

	public List<String> getListString() {
		List<String> list = new ArrayList<String>();
		list.add("jack");
		list.add("rose");
		list.add("joly");
		return list;
	}
	
	public ArrayList<Map<String, Person>> getListMap(){
		ArrayList<Map<String, Person>> mList = new ArrayList<Map<String, Person>>();
		Map<String, Person> map = new HashMap<String,Person>();
		Person person1 = new Person();
		person1.setId(1001);
		person1.setUserName("jack");
		person1.setPassword("123");
		Person person2 = new Person();
		person2.setId(1002);
		person2.setUserName("rose");
		person2.setPassword("123");
		map.put("person1", person1);
		map.put("person2", person2);
		Map<String, Person> map2 = new HashMap<String,Person>();
		Person person3 = new Person();
		person3.setId(1003);
		person3.setUserName("joly");
		person3.setPassword("123");
		map2.put("person3", person3);
		Person person4 = new Person();
		person4.setId(1004);
		person4.setUserName("Steve");
		person4.setPassword("123");
		map2.put("person4", person4);
		mList.add(map);
		mList.add(map2);
		return mList;
	}
}
