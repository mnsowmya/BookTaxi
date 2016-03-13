package com.example.booktaxi;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void cus(View v)
	{
		Intent i=new Intent(this,customer.class);
		
		startActivity(i);
	}
public void driv(View v)
{

	Intent i=new Intent(this,drivsign.class);
	startActivity(i);

}

	
	
}
