package org.edumobile.proyect;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Preferences extends Activity implements OnClickListener{
    
	private Button easy;
	private Button normal;
	private Button hard;
	private DataManipulator manipulator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level);
		
		manipulator = new DataManipulator(this);
		
		easy = (Button)findViewById(R.id.buttonEasy);
		normal = (Button)findViewById(R.id.buttonNormal);
		hard = (Button)findViewById(R.id.buttonHard);
		easy.setOnClickListener(this);
		normal.setOnClickListener(this);
		hard.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		manipulator.deleteAll();
		switch (v.getId()){
		case R.id.buttonEasy: manipulator.insert(15); break;
		case R.id.buttonNormal:	manipulator.insert(25);	break;
		case R.id.buttonHard: manipulator.insert(35); break;
		}
		finish();
	}
}
