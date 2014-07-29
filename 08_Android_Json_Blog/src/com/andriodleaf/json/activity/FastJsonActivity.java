package com.andriodleaf.json.activity;

import java.util.ArrayList;
import java.util.Map;

import com.andriodleaf.json.entity.Person;
import com.andriodleaf.json.tools.JsonTools;


public class FastJsonActivity extends BaseActivity {

	@Override
	public void onDownloaded(String result, int typeId) {
		// TODO Auto-generated method stub
		ArrayList<String> mList = new ArrayList<String>();
		switch (typeId) {
		case R.id.json_person:
			Person mPerson = JsonTools.fastJsonToObject(result, Person.class);
			mList.add(mPerson.toString());
			break;
		case R.id.json_string:
			ArrayList<String> mArrayList = JsonTools.fastJsonToListObjectOrString(result, String.class);
			mList.addAll(mArrayList);
			break;
		case R.id.json_list_person:
			ArrayList<Person> mPersons = JsonTools.fastJsonToListObjectOrString(result, Person.class);
			mList.addAll(personsToString(mPersons));
			break;
		case R.id.json_listmap_person:
			ArrayList<Map<String, Person>> maps = JsonTools.fastJsonGetListMapObject(result, JsonTools.fastJsonGetetListMapTypeReference(Person.class));
			mList.addAll(listmappersonsToString(maps));
			break;
		default:
			break;
		}
		bindData(mList);
	}

}
