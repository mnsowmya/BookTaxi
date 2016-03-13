package com.example.booktaxi;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class custlogin extends Activity {
		AutoCompleteTextView sa,da;
		String source,dest;
		ImageButton nxt;
		
		EditText ed;
		String s[]={"vidyanagar","secundrabad","narayanguda"};
		@TargetApi(Build.VERSION_CODES.GINGERBREAD)
		protected void onCreate(Bundle savedInstanceState) {
			
			super.onCreate(savedInstanceState);
			setContentView(R.layout.custlogin);
						sa=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
			ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,s);
			sa.setAdapter(adapter);
			sa.setThreshold(2);
			da=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView2);
			ed=(EditText)findViewById(R.id.editText1);
			
			da.setAdapter(adapter);
			da.setThreshold(2);
			nxt=(ImageButton)findViewById(R.id.imageButton1);
			nxt.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					source=sa.getText().toString();
					dest=da.getText().toString();
					if((sa.getText().toString().isEmpty())||(da.getText().toString().isEmpty()))
					{
					Toast.makeText(getApplicationContext(), "Enter the empty fields", Toast.LENGTH_SHORT).show();
					}
						
					else{
					Intent r=getIntent();

					Bundle bundle=r.getExtras();

		        	
		        
				
					
					String so=bundle.getString("name");
					 String des=bundle.getString("phno");
					Toast.makeText(getApplicationContext(), source+dest, Toast.LENGTH_SHORT).show();	
					Intent i=new Intent(custlogin.this,confirm.class);
				
					i.putExtra("source", source);
					i.putExtra("desti", dest);
					i.putExtra("name",so );
					i.putExtra("phno",des);
					startActivity(i);
				
					}
				}
			});
	}
		 public boolean onCreateOptionsMenu(Menu menu)
		    {
		        MenuInflater menuInflater = getMenuInflater();
		        menuInflater.inflate(R.menu.menu, menu);
		        return true;
		    }

		  public boolean onOptionsItemSelected(MenuItem item)
		    {
		         
		        switch (item.getItemId())
		        {
		        case R.id.menu_gmap:
		            // Single menu item is selected do something
		            // Ex: launching new activity/screen or show alert message
		            Toast.makeText(custlogin.this, "Google map is coming soon", Toast.LENGTH_SHORT).show();
		            return true;
		 
		        case R.id.menu_logout:
		            Toast.makeText(custlogin.this, "Logging out...", Toast.LENGTH_SHORT).show();
		        	
		            Intent i=new Intent(custlogin.this,customer.class);
		            custlogin.this.finish();
		            
					startActivity(i);
					
		            return true;
		 
		        
		        default:
		            return super.onOptionsItemSelected(item);
		        }
		    }    
		
	}

