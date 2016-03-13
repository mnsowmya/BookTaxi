package com.example.booktaxi;


import android.app.Activity;
import android.os.Bundle;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
public class cussign extends Activity {
	ImageButton tv;
	    EditText ed,ed1;
		String phno,name;

	public static SQLiteDatabase db=null;

	TableLayout tl;
	 dbhelper help2;

			
	protected void onCreate(Bundle savedInstanceState) {
			
	super.onCreate(savedInstanceState);
	setContentView(R.layout.cussign);

			
			
	help2=new dbhelper(this,"final",null,1);
			
	db=help2.getWritableDatabase();
				
				
		tv=(ImageButton)findViewById(R.id.imageButton2);
		tv.setOnClickListener(new View.OnClickListener(){

			
			@Override
			public void onClick(View arg0) {
				ed=(EditText)findViewById(R.id.editText1);
				ed1=(EditText)findViewById(R.id.editText2);
				
				if((ed.getText().toString().isEmpty())||(ed1.getText().toString().isEmpty()))
				{
				Toast.makeText(getApplicationContext(), "fill all details to sign in", Toast.LENGTH_SHORT).show();
					
				}
				else{	phno=ed1.getText().toString();
				
				name=ed.getText().toString();
			
				
				insert(null);
				Toast.makeText(getApplicationContext(), "you are signed in", Toast.LENGTH_SHORT).show();
				Intent i=new Intent(cussign.this,customer.class);
				startActivity(i);
				
				tv.setEnabled(false);
				
				}
				
				

				
			}
			
			
		});
		
			}
			
		public void insert(View v)
		{
			
			
			ContentValues cv=new ContentValues();
			
			cv.put("name", name);
			
			cv.put("phno",phno);
		   cv.put("dphno", "1");
			
			db.insert("customer", null, cv);
			
			




		}
		/*void sho()
		{
			tl=(TableLayout) findViewById(R.id.tablelayout1);
			TableRow tr=new TableRow(getApplicationContext());
			TextView t2=new TextView(getApplicationContext());
			TextView t1=new TextView(getApplicationContext());
			
			tl.removeAllViews();
			t1.setText("name");
			t2.setText("phno");
			
			tr.addView(t1);
			tr.addView(t2);
			
			tl.addView(tr);
			Cursor cr=db.query("customer", new String[]{"name,phno,dphno"}, null,null, null, null, null);	
			if(cr.moveToFirst())
			{do{
				TableRow tr1=new TableRow(getApplicationContext());
				TextView t2o=new TextView(getApplicationContext());
				TextView t1o=new TextView(getApplicationContext());
				
				String i=cr.getString(0);
				String d=cr.getString(1);
				
				t1o.setText(i+"");
				t2o.setText(d+"");
				
				tr1.addView(t1o);
				tr1.addView(t2o);
				
				tl.addView(tr1);
			
			}while(cr.moveToNext());
				
				cr.close();
				
			}*/
				
		}
		






