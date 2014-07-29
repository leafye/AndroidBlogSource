package com.andriodleaf.json.activity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.andriodleaf.json.entity.Person;
import com.andriodleaf.json.tools.JsonTools;

import android.os.Bundle;
import android.widget.ArrayAdapter;

public class JsonActivity extends BaseActivity {

	@Override
	public void onDownloaded(String result,int typeId) {
		// TODO Auto-generated method stub
		ArrayList<String> mList = new ArrayList<String>();
		switch (typeId) {
		case R.id.json_person:
			Person mPerson = JsonTools.JsonToPerson("person", result);
			mList.add(mPerson.toString());
			break;
		case R.id.json_string:
			ArrayList<String> mArrayList = JsonTools.jsonToListString("liststring", result);
			mList.addAll(mArrayList);
			break;
		case R.id.json_list_person:
			ArrayList<Person> mPersons = JsonTools.JsonToListPerson("listperson", result);
			mList.addAll(personsToString(mPersons));
			break;
		case R.id.json_listmap_person:
			ArrayList<Map<String, Person>> maps = JsonTools.jsonToListMapPerson("listmapperson", result);
			mList.addAll(listmappersonsToString(maps));
			break;
		default:
			break;
		}
		bindData(mList);
	}
	
}
