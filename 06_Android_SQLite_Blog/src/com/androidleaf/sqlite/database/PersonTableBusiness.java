package com.androidleaf.sqlite.database;

import java.util.ArrayList;

import com.androidleaf.sqlite.entity.Person;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PersonTableBusiness extends DataBaseManager<Person> {

	//表的名称
	private static final String PERSON_TABLE_NAME = "person";
	private static final String PERSON_FIELD_ID = "_id";
	private static final String PERSON_FIELD_PERSONNAME = "name";
	private static final String PERSON_FIELD_EMAIL = "email";
	private static final String PERSON_FIELD_HEIGHT = "height";
	//定义主键是否为空
	private static final int PRIMAY_KEY_IS_NULL = -1;

	public PersonTableBusiness(Context mContext) {
		super(mContext);
		// TODO Auto-generated constructor stub
	}

	public void insertPerson(String name, String email, float height) {
		ContentValues mContentValues = createParams(PRIMAY_KEY_IS_NULL, name,
				email, height);
		insert(mContentValues);
	}

	public void insertPersonObject(Person mPerson) {
		insert(createParams(mPerson));
	}

	public void insertListPerson(ArrayList<Person> mPersons) {
		for (Person mPerson : mPersons) {
			insert(createParams(mPerson));
		}
	}

	public void updatePerson(int id, String name, String email, float height) {
		update(createParams(id, name, email, height));
	}

	public void updatePersonObject(Person mPerson) {
		update(createParams(mPerson));
	}

	public void updatePersonList(ArrayList<Person> mPersons) {
		for (Person mPerson : mPersons) {
			update(createParams(mPerson));
		}
	}

	public void deletePersonById(int id) {
		delete(id);
	}
	

	public ArrayList<Person> queryAllPersons() {
		String sql = "select * from " + PERSON_TABLE_NAME;
		return query(sql);
	}

	public Person querySinglePersonById(int id) {
		return query(id);
	}

	/**
	 * 实现回调方法，这里实现创建表的操作
	 */
	@Override
	public void onCreate(SQLiteDatabase mSqLiteDatabase) {
		// TODO Auto-generated method stub
		// 创建表的SQL语句
		String DATABASE_CREATE_PERSON_TABLE = "CREATE TABLE "
				+ PERSON_TABLE_NAME + " (" + "" + PERSON_FIELD_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT," + ""
				+ PERSON_FIELD_PERSONNAME + " VARCHAR(20) NOT NULL," + ""
				+ PERSON_FIELD_EMAIL + " VARCHAR(20) NOT NULL," + ""
				+ PERSON_FIELD_HEIGHT + " FLOAT(10) NOT NULL" + ")";
		// 执行创建表的操作
		mSqLiteDatabase.execSQL(DATABASE_CREATE_PERSON_TABLE);
	}

	/**
	 * 实现回调方法，这里执行删除表的操作
	 */
	@Override
	public void onUpgrade(SQLiteDatabase mSqLiteDatabase) {
		// TODO Auto-generated method stub
		mSqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PERSON_TABLE_NAME);
	}

	/**
	 * 创建参数集合
	 * @param mPerson
	 * @return mValues
	 */
	public ContentValues createParams(Person mPerson) {
		ContentValues mValues = new ContentValues();
		if (mPerson.getId() != PRIMAY_KEY_IS_NULL) {
			mValues.put(PERSON_FIELD_ID, mPerson.getId());
		}
		mValues.put(PERSON_FIELD_PERSONNAME, mPerson.getName());
		mValues.put(PERSON_FIELD_EMAIL, mPerson.getEmail());
		mValues.put(PERSON_FIELD_HEIGHT, mPerson.getHeight());
		return mValues;
	}

	/**
	 * 创建参数集合
	 * @param id
	 * @param name
	 * @param email
	 * @param height
	 * @return mValues
	 */
	public ContentValues createParams(int id, String name, String email,
			float height) {
		ContentValues mValues = new ContentValues();
		if (id != PRIMAY_KEY_IS_NULL) {
			mValues.put(PERSON_FIELD_ID, id);
		}
		mValues.put(PERSON_FIELD_PERSONNAME, name);
		mValues.put(PERSON_FIELD_EMAIL, email);
		mValues.put(PERSON_FIELD_HEIGHT, height);
		return mValues;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return PERSON_TABLE_NAME;
	}

	@Override
	public String getPrimayKeyID() {
		// TODO Auto-generated method stub
		return PERSON_FIELD_ID;
	}

	/**
	 * 通过获取到的Cursor转换成Person对象
	 */
	@Override
	public Person getResultFromCursor(Cursor mCursor) {
		// TODO Auto-generated method stub
		Person mPerson = new Person();
		mPerson.setId(mCursor.getInt(mCursor.getColumnIndex(PERSON_FIELD_ID)));
		mPerson.setName(mCursor.getString(mCursor
				.getColumnIndex(PERSON_FIELD_PERSONNAME)));
		mPerson.setEmail(mCursor.getString(mCursor
				.getColumnIndex(PERSON_FIELD_EMAIL)));
		mPerson.setHeight(mCursor.getFloat(mCursor
				.getColumnIndex(PERSON_FIELD_HEIGHT)));
		return mPerson;
	}
}
