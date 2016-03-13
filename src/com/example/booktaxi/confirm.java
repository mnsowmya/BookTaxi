package com.example.booktaxi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.AlertDialog.Builder;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class confirm extends Activity {
	public static SQLiteDatabase db=null;
		public custlogin cl;
		TableLayout tl;
		ImageButton b,b1;
		String so,des;
		String cphno;
		String cname;
		dbhelper help2;
		String dphno;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) 
		{
			
			super.onCreate(savedInstanceState);
			setContentView(R.layout.confirm);
		 
			 help2=new dbhelper(this,"final",null,1);
		    db=help2.getWritableDatabase();
		      Intent i=getIntent();
		      Bundle bundle=i.getExtras();
		    cname=bundle.getString("name");
		     cphno=bundle.getString("phno");	
	      	so=bundle.getString("source");
		    des=bundle.getString("desti");
		    b1=(ImageButton) findViewById(R.id.imagebutton2);
			b=(ImageButton) findViewById(R.id.imagebutton1);
		    b.setOnClickListener(new OnClickListener()
			{

				@Override
				public void onClick(View arg0) 
				{
					AlertDialog.Builder alertDialogBuilder=	new AlertDialog.Builder(confirm.this);
					alertDialogBuilder.setTitle("Book Taxi");
					alertDialogBuilder.setMessage("Do you want to confirm booking?");
					alertDialogBuilder.setCancelable(false);
					alertDialogBuilder.setPositiveButton("Yes",new DialogInterface.OnClickListener() 
					{
						
					@Override
					       public void onClick(DialogInterface dialog, int which)
					               {
							
					                  	NotificationManager nm=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
						@SuppressWarnings("deprecation")
						                 Notification n=new Notification(R.drawable.noti,"Driver Details ",System.currentTimeMillis());
						                  Context c=getApplicationContext();
						                   String title="Book Taxi";
					                    	String text="Confiramtion Details";
			                             Intent myintent=new Intent(getApplicationContext(),deepika.class);
						                  myintent.putExtra("source", so);
							              myintent.putExtra("desti", des);
							              myintent.putExtra("name", cname);
							              myintent.putExtra("phno",cphno );
	           				      PendingIntent pi=PendingIntent.getActivity(getApplicationContext(),0, myintent,Intent.FLAG_ACTIVITY_NEW_TASK);
					            		n.setLatestEventInfo(c, title, text, pi);
						                        nm.notify(1,n);
					    
					                }
					    }).setNegativeButton("No", new DialogInterface.OnClickListener() 
					        {
					           @Override
					             public void onClick(DialogInterface dialog, int which)
					                 {
					                     dialog.cancel();
					                    Toast.makeText(getApplicationContext(), "Registration Cancelled", Toast.LENGTH_SHORT).show();
					                    confirm.this.finish();
					                    
                    
					                  }
				          	});
					AlertDialog alertDialog=alertDialogBuilder.create();
					alertDialog.show();
					
				}
				

			
			});
			
			
			
			
			
		b1.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0) 
			{
				Cursor cr=db.query("customer",new String[]{"name,phno,dphno"}, "phno=?",new String [] {String.valueOf(cphno)},null,null,null);
				
				if(cr.moveToFirst())
				{
					dphno=cr.getString(2);
					ContentValues cv=new ContentValues();
					
					cv.put("name", cname);
					cv.put("phno",cphno);
					cv.put("dphno", "1");
					db.update("customer", cv, "phno=?",new String[]{String.valueOf(cphno)});
				}
				Cursor c=db.query("driver",new String[]{"name,lic,phno,address,ava"}, "phno=?",new String [] {String.valueOf(dphno)},null,null,null);
				
				if(c.moveToFirst())
				{
					
					ContentValues cv=new ContentValues();
					
					cv.put("name", c.getString(0));
					cv.put("lic",c.getString(1));
					cv.put("phno",dphno);
				    cv.put("address", c.getString(3));
					cv.put("ava", "1");
					

					db.update("driver", cv, "phno=?",new String[]{String.valueOf(dphno)});
					
				}
				
               
				Toast.makeText(getApplicationContext(), "you booking is cancelled", Toast.LENGTH_SHORT).show();
				
				
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
		            Toast.makeText(confirm.this, "Google map coming soon", Toast.LENGTH_SHORT).show();
		            return true;
		 
		        case R.id.menu_logout:
		            Toast.makeText(confirm.this, "Logging out...", Toast.LENGTH_SHORT).show();
		       

		            Intent i=new Intent(confirm.this,custlogin.class);
		                 
		         
		            confirm.this.finish();
		            
		            
					startActivity(i);
	
		            return true;
		 
		        
		        default:
		            return super.onOptionsItemSelected(item);
		        }
		    }    
		
		
		
	}



