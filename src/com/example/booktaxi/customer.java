package com.example.booktaxi;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class customer extends Activity {
	SQLiteDatabase db=null;
	dbhelper help2;
	EditText ed,ed1;
	@Override
 protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer);
		

	 }
	public void custsign(View v)
	{
		Intent i=new Intent(this,cussign.class);
	         startActivity(i);
	}
     public void custlogin(View v)
     {
	    help2=new dbhelper(this,"final",null,1);
            db=help2.getWritableDatabase();
	   ed=(EditText)findViewById(R.id.editText2);
       ed1=(EditText)findViewById(R.id.editText1);

       String s=ed.getText().toString();
       String p=ed1.getText().toString();
           if((ed.getText().toString().isEmpty())||(ed1.getText().toString().isEmpty()))
                 {
                      Toast.makeText(getApplicationContext(), "fill all details to login", Toast.LENGTH_SHORT).show();
	
                  }
           else{	Cursor cr=db.query("customer",new String[]{"name,phno,dphno"}, "phno=?",new String [] {String.valueOf(s)},null,null,null);
	                if(cr.moveToFirst())
	                 {
		                       if(p.equals(cr.getString(0)))

		                       {
		                    	   Toast.makeText(this,cr.getString(1), Toast.LENGTH_LONG).show();
		                             Intent i=new Intent(this,custlogin.class);
		                                i.putExtra("name",p );
		                                i.putExtra("phno",s );
		                                startActivity(i);
		                        }
		                      else
			                     Toast.makeText(this,"you typed wrong username", Toast.LENGTH_LONG).show();

	                 }
	              else 
	                 {
		                 Toast.makeText(this,"you typed wrong password", Toast.LENGTH_LONG).show();
                   	}

           }
	
}







}


