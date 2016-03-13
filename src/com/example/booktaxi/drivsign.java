package com.example.booktaxi;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class drivsign extends Activity {
	String name,lic;
	ImageButton tv;
    EditText ed,ed1,ed2;
	String phno,add;
	AutoCompleteTextView sa;
	String s[]={"vidyanagar","secundrabad","narayanguda"};
public static SQLiteDatabase db=null;

TableLayout tl;
 dbhelper help2;

		
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
protected void onCreate(Bundle savedInstanceState) {
		
super.onCreate(savedInstanceState);
setContentView(R.layout.drivsign);

		
sa=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,s);
sa.setAdapter(adapter);
sa.setThreshold(2);
help2=new dbhelper(this,"final",null,1);
		
db=help2.getWritableDatabase();
			
			
	tv=(ImageButton)findViewById(R.id.imageButton2);
	tv.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			ed=(EditText)findViewById(R.id.editText1);
			ed1=(EditText)findViewById(R.id.editText2);
			ed2=(EditText)findViewById(R.id.editText3);
			
			phno=ed2.getText().toString();
			lic=ed1.getText().toString();
			name=ed.getText().toString();
	
			add=sa.getText().toString();
			if((sa.getText().toString().isEmpty())||(ed.getText().toString().isEmpty())||(ed1.getText().toString().isEmpty())||(ed2.getText().toString().isEmpty()))
			{
			Toast.makeText(getApplicationContext(), "fill the empty details to sign in", Toast.LENGTH_SHORT).show();
			}
			//Toast.makeText(getApplicationContext(), phno+lic+name, Toast.LENGTH_SHORT).show();
			else{
				insert(null);
			
			Toast.makeText(getApplicationContext(), "you are signed", Toast.LENGTH_SHORT).show();
			Intent i=new Intent(drivsign.this,MainActivity.class);
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
		cv.put("lic",lic);
		cv.put("phno",phno);
	    cv.put("address", add);
		cv.put("ava", "1");
		
		db.insert("driver", null, cv);
	
		
		




	}
	/*void sho()
	{
		tl=(TableLayout) findViewById(R.id.tablelayout1);
		TableRow tr=new TableRow(getApplicationContext());
		TextView t2=new TextView(getApplicationContext());
		TextView t1=new TextView(getApplicationContext());
		TextView t3=new TextView(getApplicationContext());
		TextView t4=new TextView(getApplicationContext());
		TextView t5=new TextView(getApplicationContext());
		tl.removeAllViews();
		t1.setText("name");
		t2.setText("lic");
		t3.setText("phno");
		t4.setText("add");
		t5.setText("ava");
		tr.addView(t1);
		tr.addView(t2);
		tr.addView(t3);
		tr.addView(t4);
		tr.addView(t5);
		tl.addView(tr);
		Cursor cr=db.query("driver", new String[]{"name,lic,phno,address,ava"}, null,null, null, null, null);	
		if(cr.moveToFirst())
		{do{
			TableRow tr1=new TableRow(getApplicationContext());
			TextView t2o=new TextView(getApplicationContext());
			TextView t1o=new TextView(getApplicationContext());
			TextView t3o=new TextView(getApplicationContext());
			TextView t4o=new TextView(getApplicationContext());
			TextView t5o=new TextView(getApplicationContext());
			String i=cr.getString(0);
			String d=cr.getString(1);
			String s=cr.getString(3);
			String p=cr.getString(3);
			String e=cr.getString(3);
			t1o.setText(i+"");
			t2o.setText(d+"");

			t3o.setText(s+"");
			t3o.setText(p+"");
			t3o.setText(e+"");
			tr1.addView(t1o);
			tr1.addView(t2o);
			tr1.addView(t3o);
			tr1.addView(t4o);
			tr1.addView(t5o);
			tl.addView(tr1);
		
		}while(cr.moveToNext());
			
			cr.close();
			
		}
			
	}*/
	 public boolean onCreateOptionsMenu(Menu menu)
	    {
	        MenuInflater menuInflater = getMenuInflater();
	        menuInflater.inflate(R.menu.menu1, menu);
	        return true;
	    }

	  public boolean onOptionsItemSelected(MenuItem item)
	    {
	         
	        switch (item.getItemId())
	        {
	        case R.id.menu_gmap:
	            // Single menu item is selected do something
	            // Ex: launching new activity/screen or show alert message
	            Toast.makeText(drivsign.this, "Bookmark is Selected", Toast.LENGTH_SHORT).show();
	            return true;
	 
	        
	        
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	}
}



