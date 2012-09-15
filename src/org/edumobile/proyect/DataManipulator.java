package org.edumobile.proyect;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;	 
import java.util.ArrayList;
import java.util.Iterator;


public class DataManipulator {
	
	private static final String DATABASE_NAME = "AppMinesdb.db";
	private static final int DATABASE_VERSION = 1;
	public static final String TABLE_NAME = "level";
	private static final String INSERT = "insert into " + TABLE_NAME + " (level) values (?)";
	private Context context;
	private SQLiteDatabase db;
	private SQLiteStatement insertStmt;
	
	public DataManipulator(Context c) {
		
		context = c;
		OpenHelper openHelper = new OpenHelper(context);
		db = openHelper.getWritableDatabase();
		insertStmt = db.compileStatement(INSERT);
	}
			 
	public long insert(int value) {
		long res;
		insertStmt.bindString(1, value+"");
		res = insertStmt.executeInsert();
		return res;
	}
	
	public void delete(String name){
		if (name!=null)
			db.delete(TABLE_NAME, "level=\""+name+"\"", null);
	}
			 
	public void deleteAll() {
		db.delete(TABLE_NAME, null, null);
	}
	
	public int getLevel(){
		int res = 25;
		ArrayList<Integer> list = selectAll();
		Iterator<Integer> iterator = list.iterator();
		if(list.isEmpty()==false){
			res = iterator.next();
		}
		else{
			this.insert(res);
		}
		return res;
	}
	
	public Cursor queryAll(String table){
		return db.query(table,null,null,null,null,null,null);
	}
	
	public ArrayList<Integer> selectAll() {
		int n=1;
		ArrayList<Integer> list = new ArrayList<Integer>();
		Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getInt(n));
			} while (cursor.moveToNext());
		}
		
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		
		return list;
	}
			 
	private static class OpenHelper extends SQLiteOpenHelper {
		
		OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
			 
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE "+TABLE_NAME+" (id INTEGER PRIMARY KEY,level INTEGER)");
		}
			 
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}
	}
	
}