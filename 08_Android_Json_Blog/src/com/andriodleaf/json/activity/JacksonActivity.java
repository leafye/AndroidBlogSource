package com.andriodleaf.json.activity;

import java.util.ArrayList;
import java.util.Map;

import com.andriodleaf.json.entity.Person;
import com.andriodleaf.json.tools.JsonTools;


public class JacksonActivity extends BaseActivity {

	@Override
	public void onDownloaded(String result, int typeId) {
		// TODO Auto-generated method stub
		ArrayList<String> mList = new ArrayList<String>();
		JsonTools mJsonTools = new JsonTools();
		switch (typeId) {
		case R.id.json_person:
			Person mPerson = mJsonTools.JacksonToObject(result, Person.class);
			mList.add(mPerson.toString());
			break;
		case R.id.json_string:
			ArrayList<String> mArrayList = mJsonTools.JacksonToListObjectOrString(result,JsonTools.getListTypeReference(String.class));
			mList.addAll(mArrayList);
			break;
		case R.id.json_list_person:
			ArrayList<Person> mPersons = mJsonTools.JacksonToListObjectOrString(result,JsonTools.getListTypeReference(Person.class));
			mList.addAll(personsToString(mPersons));
			break;
		case R.id.json_listmap_person:
			ArrayList<Map<String, Person>> maps = mJsonTools.JacksonToListMapObject(result,JsonTools.getListMapTypeReference(Person.class));
			mList.addAll(listmappersonsToString(maps));
			break;
		default:
			break;
		}
		bindData(mList);
	}

}
