package com.genius.main;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity{
	private Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		if(ShellUtils.checkRootPermission())
		{
			System.out.println("isroot¡­¡­");
			Log.e("isroot", "isroot&*&*%%^$%&%*^*");
		}
		else {
			System.out.println("isroot false");
			Log.e("isroot", "false&*&*%%^$%&%*^*");
			
		}
//		button.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//			}
//		});
	}

	
}
