package com.example.android.navigationdrawerexample;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.database.LabServiceAdapter;
import com.example.model.LabService;

public class LaboratoryRequest extends Activity {
	private LabService labservice;
	private ArrayList<LabService> labservices;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_laboratory_request);
		try{
			Intent intent = getIntent();
			ArrayList<String> service_ids = intent.getStringArrayListExtra("SERVICE_IDS");
			//System.out.println(service_ids);
			LabServiceAdapter db = new LabServiceAdapter(this);
			labservices = new ArrayList<LabService>();
			for(int i = 0; i < service_ids.size(); i++){
				System.out.println(service_ids.get(i));
				labservice = db.getLabService(service_ids.get(i));
				labservices.add(labservice);
			}
			System.out.println(labservices);
			ListView listview = (ListView) findViewById(R.id.servicesList);
			ArrayAdapter<LabService> array_adapter = new ArrayAdapter<LabService>(this, android.R.layout.simple_list_item_1, labservices);
			listview.setAdapter(array_adapter);
		}
		catch(Exception ex){
			Log.d("Getting intent", "No intent available");
		}
		
		
		
		// Show the Up button in the action bar.
		setupActionBar();
		//ListView listview = (ListView) findViewById(R.id.servicesList);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.laboratory_request, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void showAddServices(View view){
		Intent intent = new Intent(this, LaboratoryRequestAddService.class);
		startActivity(intent);
	}
	
	public void addRequest(View view){
		
		Toast.makeText(this, "clicked add request", Toast.LENGTH_SHORT).show();
	}

}
