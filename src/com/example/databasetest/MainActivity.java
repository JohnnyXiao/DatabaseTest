package com.example.databasetest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button createDbButton;
	private Button addData;
	private MyDatabaseOpenHelper dbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		createDbButton = (Button) findViewById(R.id.create_database);
		dbHelper = new MyDatabaseOpenHelper(MainActivity.this, "BookStore.db", null, 2);
		createDbButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dbHelper.getWritableDatabase();		
			}
		});
		
		addData =  (Button) findViewById(R.id.add_data);
		addData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues contentValues = new ContentValues();
				//向Book表中添加一条数据；
				contentValues.put("author", "Jevon");
				contentValues.put("price", 69);
				contentValues.put("pages", 599);
				contentValues.put("name", "《笨鸟先飞》");
				db.insert("Book", null, contentValues);
				contentValues.clear();
				//像Category表中添加一条数据
				contentValues.put("category_name", "《励志文学》");
				contentValues.put("category_code", 001);
				db.insert("Category", null, contentValues);
				Toast.makeText(MainActivity.this, "向表Category中添加数据成功..", Toast.LENGTH_SHORT).show();				
			}
		});
		
		Button upgradeButton = (Button) findViewById(R.id.upgrade_data);
		upgradeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues contentValues = new ContentValues();
				contentValues.put("name", "《笨鸟先飞》第二部");
				db.update("Book", contentValues, "id = ?", new String[] {"2"});
				
			}
		});
	}
}
