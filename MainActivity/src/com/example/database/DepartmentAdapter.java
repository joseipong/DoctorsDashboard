package com.example.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import com.example.model.Department;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

public class DepartmentAdapter extends Data {

	public  DepartmentAdapter(Context context) 
	{
		try {
			dbHandler = new DatabaseHandler(context, DATABASE_NAME, null, DATABASE_VERSION);
			Log.d("DatabaseHandler", "Database Created");
		} catch (Exception e) {
			Log.d("DatabaseHandler Exception", Log.getStackTraceString(e));
		}
	}
	
	public boolean checkDepartments(){
		//Add code for query getting number of accounts in mobile DB
		
		db = dbHandler.getReadableDatabase();
		
		String query = 
			"SELECT count(" + DEPT_ID + ")" +
			" FROM " + TABLE_DEPARTMENT;
		
		
		Cursor cursor = db.rawQuery(query, null);
		
		if(cursor.moveToFirst()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void insertDepartments(ArrayList<Department> dept){
		db = dbHandler.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		try {
			db.beginTransaction();
			for(int i = 0; i < dept.size(); i++) {
				values.put(DEPT_ID, dept.get(i).getDepartmentNumber());	
				values.put(DEPT, dept.get(i).getDepartmentName());	
				values.put(SHORT_DEPT, dept.get(i).getDepartmentId());	
			    db.insert(TABLE_DEPARTMENT, null, values);
			  }
			  db.setTransactionSuccessful();
				Log.d("DepartmentAdapter insertDepartments", "setTransactionSuccessful");
			}
			catch (SQLException se) {
				Log.d("DepartmentAdapter insertDepartments", Log.getStackTraceString(se));
			}
			finally
			{
			  db.endTransaction();
			}
	}
	
}
