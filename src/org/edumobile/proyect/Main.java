package org.edumobile.proyect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener,OnLongClickListener{
	
	public static final int CLICK = 1;
	public static final int LONGCLICK = 2;
	
	private Board game;
	private Context context;
	private String messageClick;
	private DataManipulator manipulator;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initialize();
    }
    
    public void initialize(){
    	
    	manipulator = new DataManipulator(this);
    	context = getApplicationContext();
    	game = new Board();
    	initializeListeners();
    	game.initBoard(manipulator.getLevel());
    	coverBoxes();
    	//showMessage("Game starts - "+game.getMines()+" mines to go");
    }
    
    public void showMessage(String msg){
    	int duration = Toast.LENGTH_SHORT;
    	Toast toast = Toast.makeText(context, msg, duration);
    	toast.show();
    }
    
    //Establish the Listener for the Images (boxes)
  	@Override
  	public void onClick(View arg0) {
  		// TODO Auto-generated method stub
  		for (int x=0;x<Board.SIZEX;x++){
  			for (int y=0;y<Board.SIZEY;y++){
  				if(arg0.getId()==game.views[x][y].getId()){
  					messageClick = game.click(new Coord(x+1,y+1),CLICK);
  					if (messageClick.compareTo("")!=0){
  						if(messageClick.compareTo("clean")!=0) showMessage(messageClick);
  						else game.autoUncover(new Coord(x+1,y+1));
  					}
  				}
  			}
  		}	
  	}
  	@Override
	public boolean onLongClick(View arg0) {
		// TODO Auto-generated method stub
		for (int x=0;x<Board.SIZEX;x++){
  			for (int y=0;y<Board.SIZEY;y++){
  				if(arg0.getId()==game.views[x][y].getId()){
  					messageClick = game.click(new Coord(x+1,y+1),LONGCLICK);
  					if (messageClick.compareTo("")!=0)
  						showMessage(messageClick);
  				}
  			}
  		}
		return true;
	}
  	//Establish listeners for menu
  	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    	case R.id.menu_new:
    		initialize();
    		return true;
    	case R.id.menu_pref:
    		startActivity(new Intent(this, Preferences.class));
    		return true;
		case R.id.menu_exit:
			finish();
			return true;
		default: return false;
    	}	
    }
  	
  	public void coverBoxes(){
  		for (int x=0;x<Board.SIZEX;x++)
			for (int y=0;y<Board.SIZEY;y++){
				game.views[x][y].setImageResource(R.drawable.free);
			}
  	}
    
    private void initializeListeners(){
    	
    	for (int x=0;x<Board.SIZEX;x++){
			for (int y=0;y<Board.SIZEY;y++){
				game.views[x][y] = new ImageView(this);
			}
		}
    	
    	game.views[0][0]=(ImageView)findViewById(R.id.imageView0101);
		game.views[0][1]=(ImageView)findViewById(R.id.imageView0102);
		game.views[0][2]=(ImageView)findViewById(R.id.imageView0103);
		game.views[0][3]=(ImageView)findViewById(R.id.imageView0104);
		game.views[0][4]=(ImageView)findViewById(R.id.imageView0105);
		game.views[0][5]=(ImageView)findViewById(R.id.imageView0106);
		game.views[0][6]=(ImageView)findViewById(R.id.imageView0107);
		game.views[0][7]=(ImageView)findViewById(R.id.imageView0108);
		game.views[0][8]=(ImageView)findViewById(R.id.imageView0109);
		game.views[0][9]=(ImageView)findViewById(R.id.imageView0110);
		game.views[0][10]=(ImageView)findViewById(R.id.imageView0111);
		game.views[0][11]=(ImageView)findViewById(R.id.imageView0112);
		game.views[0][12]=(ImageView)findViewById(R.id.imageView0113);
		game.views[0][13]=(ImageView)findViewById(R.id.imageView0114);
		game.views[0][14]=(ImageView)findViewById(R.id.imageView0115);
	
		game.views[1][0]=(ImageView)findViewById(R.id.imageView0201);
		game.views[1][1]=(ImageView)findViewById(R.id.imageView0202);
		game.views[1][2]=(ImageView)findViewById(R.id.imageView0203);
		game.views[1][3]=(ImageView)findViewById(R.id.imageView0204);
		game.views[1][4]=(ImageView)findViewById(R.id.imageView0205);
		game.views[1][5]=(ImageView)findViewById(R.id.imageView0206);
		game.views[1][6]=(ImageView)findViewById(R.id.imageView0207);
		game.views[1][7]=(ImageView)findViewById(R.id.imageView0208);
		game.views[1][8]=(ImageView)findViewById(R.id.imageView0209);
		game.views[1][9]=(ImageView)findViewById(R.id.imageView0210);
		game.views[1][10]=(ImageView)findViewById(R.id.imageView0211);
		game.views[1][11]=(ImageView)findViewById(R.id.imageView0212);
		game.views[1][12]=(ImageView)findViewById(R.id.imageView0213);
		game.views[1][13]=(ImageView)findViewById(R.id.imageView0214);
		game.views[1][14]=(ImageView)findViewById(R.id.imageView0215);
		
		game.views[2][0]=(ImageView)findViewById(R.id.imageView0301);
		game.views[2][1]=(ImageView)findViewById(R.id.imageView0302);
		game.views[2][2]=(ImageView)findViewById(R.id.imageView0303);
		game.views[2][3]=(ImageView)findViewById(R.id.imageView0304);
		game.views[2][4]=(ImageView)findViewById(R.id.imageView0305);
		game.views[2][5]=(ImageView)findViewById(R.id.imageView0306);
		game.views[2][6]=(ImageView)findViewById(R.id.imageView0307);
		game.views[2][7]=(ImageView)findViewById(R.id.imageView0308);
		game.views[2][8]=(ImageView)findViewById(R.id.imageView0309);
		game.views[2][9]=(ImageView)findViewById(R.id.imageView0310);
		game.views[2][10]=(ImageView)findViewById(R.id.imageView0311);
		game.views[2][11]=(ImageView)findViewById(R.id.imageView0312);
		game.views[2][12]=(ImageView)findViewById(R.id.imageView0313);
		game.views[2][13]=(ImageView)findViewById(R.id.imageView0314);
		game.views[2][14]=(ImageView)findViewById(R.id.imageView0315);
		
		game.views[3][0]=(ImageView)findViewById(R.id.imageView0401);
		game.views[3][1]=(ImageView)findViewById(R.id.imageView0402);
		game.views[3][2]=(ImageView)findViewById(R.id.imageView0403);
		game.views[3][3]=(ImageView)findViewById(R.id.imageView0404);
		game.views[3][4]=(ImageView)findViewById(R.id.imageView0405);
		game.views[3][5]=(ImageView)findViewById(R.id.imageView0406);
		game.views[3][6]=(ImageView)findViewById(R.id.imageView0407);
		game.views[3][7]=(ImageView)findViewById(R.id.imageView0408);
		game.views[3][8]=(ImageView)findViewById(R.id.imageView0409);
		game.views[3][9]=(ImageView)findViewById(R.id.imageView0410);
		game.views[3][10]=(ImageView)findViewById(R.id.imageView0411);
		game.views[3][11]=(ImageView)findViewById(R.id.imageView0412);
		game.views[3][12]=(ImageView)findViewById(R.id.imageView0413);
		game.views[3][13]=(ImageView)findViewById(R.id.imageView0414);
		game.views[3][14]=(ImageView)findViewById(R.id.imageView0415);
		
		game.views[4][0]=(ImageView)findViewById(R.id.imageView0501);
		game.views[4][1]=(ImageView)findViewById(R.id.imageView0502);
		game.views[4][2]=(ImageView)findViewById(R.id.imageView0503);
		game.views[4][3]=(ImageView)findViewById(R.id.imageView0504);
		game.views[4][4]=(ImageView)findViewById(R.id.imageView0505);
		game.views[4][5]=(ImageView)findViewById(R.id.imageView0506);
		game.views[4][6]=(ImageView)findViewById(R.id.imageView0507);
		game.views[4][7]=(ImageView)findViewById(R.id.imageView0508);
		game.views[4][8]=(ImageView)findViewById(R.id.imageView0509);
		game.views[4][9]=(ImageView)findViewById(R.id.imageView0510);
		game.views[4][10]=(ImageView)findViewById(R.id.imageView0511);
		game.views[4][11]=(ImageView)findViewById(R.id.imageView0512);
		game.views[4][12]=(ImageView)findViewById(R.id.imageView0513);
		game.views[4][13]=(ImageView)findViewById(R.id.imageView0514);
		game.views[4][14]=(ImageView)findViewById(R.id.imageView0515);
	
		game.views[5][0]=(ImageView)findViewById(R.id.imageView0601);
		game.views[5][1]=(ImageView)findViewById(R.id.imageView0602);
		game.views[5][2]=(ImageView)findViewById(R.id.imageView0603);
		game.views[5][3]=(ImageView)findViewById(R.id.imageView0604);
		game.views[5][4]=(ImageView)findViewById(R.id.imageView0605);
		game.views[5][5]=(ImageView)findViewById(R.id.imageView0606);
		game.views[5][6]=(ImageView)findViewById(R.id.imageView0607);
		game.views[5][7]=(ImageView)findViewById(R.id.imageView0608);
		game.views[5][8]=(ImageView)findViewById(R.id.imageView0609);
		game.views[5][9]=(ImageView)findViewById(R.id.imageView0610);
		game.views[5][10]=(ImageView)findViewById(R.id.imageView0611);
		game.views[5][11]=(ImageView)findViewById(R.id.imageView0612);
		game.views[5][12]=(ImageView)findViewById(R.id.imageView0613);
		game.views[5][13]=(ImageView)findViewById(R.id.imageView0614);
		game.views[5][14]=(ImageView)findViewById(R.id.imageView0615);
	
		game.views[6][0]=(ImageView)findViewById(R.id.imageView0701);
		game.views[6][1]=(ImageView)findViewById(R.id.imageView0702);
		game.views[6][2]=(ImageView)findViewById(R.id.imageView0703);
		game.views[6][3]=(ImageView)findViewById(R.id.imageView0704);
		game.views[6][4]=(ImageView)findViewById(R.id.imageView0705);
		game.views[6][5]=(ImageView)findViewById(R.id.imageView0706);
		game.views[6][6]=(ImageView)findViewById(R.id.imageView0707);
		game.views[6][7]=(ImageView)findViewById(R.id.imageView0708);
		game.views[6][8]=(ImageView)findViewById(R.id.imageView0709);
		game.views[6][9]=(ImageView)findViewById(R.id.imageView0710);
		game.views[6][10]=(ImageView)findViewById(R.id.imageView0711);
		game.views[6][11]=(ImageView)findViewById(R.id.imageView0712);
		game.views[6][12]=(ImageView)findViewById(R.id.imageView0713);
		game.views[6][13]=(ImageView)findViewById(R.id.imageView0714);
		game.views[6][14]=(ImageView)findViewById(R.id.imageView0715);
		
		game.views[7][0]=(ImageView)findViewById(R.id.imageView0801);
		game.views[7][1]=(ImageView)findViewById(R.id.imageView0802);
		game.views[7][2]=(ImageView)findViewById(R.id.imageView0803);
		game.views[7][3]=(ImageView)findViewById(R.id.imageView0804);
		game.views[7][4]=(ImageView)findViewById(R.id.imageView0805);
		game.views[7][5]=(ImageView)findViewById(R.id.imageView0806);
		game.views[7][6]=(ImageView)findViewById(R.id.imageView0807);
		game.views[7][7]=(ImageView)findViewById(R.id.imageView0808);
		game.views[7][8]=(ImageView)findViewById(R.id.imageView0809);
		game.views[7][9]=(ImageView)findViewById(R.id.imageView0810);
		game.views[7][10]=(ImageView)findViewById(R.id.imageView0811);
		game.views[7][11]=(ImageView)findViewById(R.id.imageView0812);
		game.views[7][12]=(ImageView)findViewById(R.id.imageView0813);
		game.views[7][13]=(ImageView)findViewById(R.id.imageView0814);
		game.views[7][14]=(ImageView)findViewById(R.id.imageView0815);
		
		game.views[8][0]=(ImageView)findViewById(R.id.imageView0901);
		game.views[8][1]=(ImageView)findViewById(R.id.imageView0902);
		game.views[8][2]=(ImageView)findViewById(R.id.imageView0903);
		game.views[8][3]=(ImageView)findViewById(R.id.imageView0904);
		game.views[8][4]=(ImageView)findViewById(R.id.imageView0905);
		game.views[8][5]=(ImageView)findViewById(R.id.imageView0906);
		game.views[8][6]=(ImageView)findViewById(R.id.imageView0907);
		game.views[8][7]=(ImageView)findViewById(R.id.imageView0908);
		game.views[8][8]=(ImageView)findViewById(R.id.imageView0909);
		game.views[8][9]=(ImageView)findViewById(R.id.imageView0910);
		game.views[8][10]=(ImageView)findViewById(R.id.imageView0911);
		game.views[8][11]=(ImageView)findViewById(R.id.imageView0912);
		game.views[8][12]=(ImageView)findViewById(R.id.imageView0913);
		game.views[8][13]=(ImageView)findViewById(R.id.imageView0914);
		game.views[8][14]=(ImageView)findViewById(R.id.imageView0915);
		
		game.views[9][0]=(ImageView)findViewById(R.id.imageView1001);
		game.views[9][1]=(ImageView)findViewById(R.id.imageView1002);
		game.views[9][2]=(ImageView)findViewById(R.id.imageView1003);
		game.views[9][3]=(ImageView)findViewById(R.id.imageView1004);
		game.views[9][4]=(ImageView)findViewById(R.id.imageView1005);
		game.views[9][5]=(ImageView)findViewById(R.id.imageView1006);
		game.views[9][6]=(ImageView)findViewById(R.id.imageView1007);
		game.views[9][7]=(ImageView)findViewById(R.id.imageView1008);
		game.views[9][8]=(ImageView)findViewById(R.id.imageView1009);
		game.views[9][9]=(ImageView)findViewById(R.id.imageView1010);
		game.views[9][10]=(ImageView)findViewById(R.id.imageView1011);
		game.views[9][11]=(ImageView)findViewById(R.id.imageView1012);
		game.views[9][12]=(ImageView)findViewById(R.id.imageView1013);
		game.views[9][13]=(ImageView)findViewById(R.id.imageView1014);
		game.views[9][14]=(ImageView)findViewById(R.id.imageView1015);
		
    	for (int x=0;x<Board.SIZEX;x++){
    		for (int y=0;y<Board.SIZEY;y++){
				game.views[x][y].setOnClickListener(this);
			}
		}
    	for (int x=0;x<Board.SIZEX;x++){
    		for (int y=0;y<Board.SIZEY;y++){
				game.views[x][y].setOnLongClickListener(this);
			}
		}
    }

}