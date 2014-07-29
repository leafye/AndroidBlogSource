package com.andriodleaf.json.activity;

import java.util.ArrayList;
import java.util.Map;

import com.andriodleaf.json.entity.Person;
import com.andriodleaf.json.tools.JsonTools;


public class GsonActivity extends BaseActivity {

	@Override
	public void onDownloaded(String result, int typeId) {
		// TODO Auto-generated method stub
		System.out.println("result = "+ result);
		ArrayList<String> mList = new ArrayList<String>();
		switch (typeId) {
		case R.id.json_person:
			Person mPerson = JsonTools.gsonToObject(result, Person.class);
			mList.add(mPerson.toString());
			break;
		case R.id.json_string:
			ArrayList<String> mArrayList = JsonTools.gsonToListObjectOrString(result,JsonTools.getListType(String.class));
			mList.addAll(mArrayList);
			break;
		case R.id.json_list_person:
			ArrayList<Person> mPersons = JsonTools.gsonToListObjectOrString(result,JsonTools.getListType(Person.class));
			mList.addAll(personsToString(mPersons));
			break;
		case R.id.json_listmap_person:
			ArrayList<Map<String, Person>> maps = JsonTools.gsonGetListMapObject(result,JsonTools.getListMapType(Person.class));
			mList.addAll(listmappersonsToString(maps));
			break;
		default:
			break;
		}
		bindData(mList);
	}
}
