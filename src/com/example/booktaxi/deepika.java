package com.example.booktaxi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class deepika extends Activity{
	public static SQLiteDatabase db=null;
	String so,des, name,lic,phno,add,s,cname,cphno;
	TableLayout tl;
	TextView t2;
	dbhelper help2;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deepika);
		t2=(TextView)findViewById(R.id.textView1);
		 help2=new dbhelper(this,"final",null,1);
		db=help2.getWritableDatabase();
		
		
			Intent i=getIntent();
		Bundle bundle=i.getExtras();
		cphno=bundle.getString("phno");
		cname=bundle.getString("name");
		so=bundle.getString("source");
		des=bundle.getString("desti");
		t2.setText(so+" "+des);
		check(null);
		
	}
	public void check(View v)
	{
		String s = null;
		Cursor cr=db.query("customer",new String[]{"name,phno,dphno"}, "phno=?",new String [] {String.valueOf(cphno)},null,null,null);
		if(cr.moveToFirst())
		{
			s=cr.getString(2);
			
		
		if(s.equals("1"))
		{
			match(null);
		}
		else{
			TextView t=(TextView) findViewById(R.id.textView1);
			t.setText("you booked ticket already");
		}
		}
	}

	public void match(View v)
	{
		int flag=0;
		String sp = null;
		TextView t=(TextView) findViewById(R.id.textView1);
		Cursor cr=db.query("driver", new String[]{"name,lic,phno,address,ava"}, null,null, null, null, null);
		
		if(cr.moveToFirst())
		{
			do{ name=cr.getString(0);
					 lic=cr.getString(1);
			 phno=cr.getString(2);
			 add=cr.getString(3);
			 
			String s=cr.getString(4);
			
			if((s.equals("1"))&&(add.equals(so)))
			{
				
				sp="Number Of The Driver: "+phno;
				Toast.makeText(getApplicationContext(),"Contact The Driver " , Toast.LENGTH_LONG).show();	
				flag=1;
				break;
			}
			}while(cr.moveToNext());
			
		}
            if(flag==1)
               {
            	t.setText(sp);
		        update(null);
		        update2(null);
	           }
             else
               {
            	 t.setText("No Driver Is Avaliable");
	             Toast.makeText(getApplicationContext(), "login any other time to check status", Toast.LENGTH_LONG).show();	
	           }
	    }
	public void update(View v)
	{
		ContentValues cv=new ContentValues();
		
		cv.put("name", name);
		cv.put("lic",lic);
		cv.put("phno",phno);
	    cv.put("address", add);
		cv.put("ava", "0");
		
		
	db.update("driver", cv, "phno=?",new String[]{String.valueOf(phno)});
	
//sho();

	}
	public void update2(View v)
	{
		ContentValues cv=new ContentValues();
		
		cv.put("name", cname);
		cv.put("phno",cphno);
		cv.put("dphno", phno);
		db.update("customer", cv, "phno=?",new String[]{String.valueOf(cphno)});
		
		
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
		t4.setText("address");
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
			String s=cr.getString(2);
			String p=cr.getString(3);
			String e=cr.getString(4);
			t1o.setText(i+"");
			t2o.setText(d+"");

			t3o.setText(s+"");
			t4o.setText(p+"");
			t5o.setText(e+"");
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
}
