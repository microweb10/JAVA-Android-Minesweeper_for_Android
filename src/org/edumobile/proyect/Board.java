package org.edumobile.proyect;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

public class Board {
	
	public static final int SIZEX = 10;
	public static final int SIZEY = 15;
	public static final int NUMMINESDEFAULT = 25;
	public static final String msg_won = "!!!CONGRATULATIONS!!!\nYou Completed the Board in ";
	public static final String msg_explod = "GAME OVER\nMINE HAS EXPLODED!";
	public static final String msg_wrong = "GAME OVER\nBOX WRONG FLAGGED!";
	public static final String msg_start = "Long Click to Open!";
	
	public ImageView views[][] = new ImageView[SIZEX][SIZEY];
	private Box boxes[][] = new Box[SIZEX][SIZEY];
	private int mines = 0;
	private int flags = 0;
	private Date start;

	
	public Board(){
		//Initialize variables
		for (int x=0;x<SIZEX;x++){
			for (int y=0;y<SIZEY;y++){
				boxes[x][y] = new Box(x+1,y+1);
			}
		} 
		//I don't initialize the ImageViews variable called views yet
		//we'll do it in the application to set up the listeners
	}
	
	public void initBoard(int nummines){
		  
		start = new Date();
		//Establishing mines randomly
		int ramdx,ramdy;
		Random randomGenerator = new Random();
		while(mines<nummines){
			ramdx = randomGenerator.nextInt(SIZEX); if(ramdx==0) ramdx=1;
			ramdy = randomGenerator.nextInt(SIZEY); if(ramdy==0) ramdy=1;
			if(boxes[ramdx-1][ramdy-1].hasMine()==false){
				boxes[ramdx-1][ramdy-1].setToMine();
				mines++;
			}
		}
		
		//Establish number of mines around
		for (int x=0;x<SIZEX;x++){
			for (int y=0;y<SIZEY;y++){
				if(boxes[x][y].hasMine()){
					boxes[x][y].setNumMines(11);
				}
				else{
					setNumMinesAround(boxes[x][y]);
				}
			}
		}
	}
	
	public ArrayList<Coord> getBoxesAround(Coord pos){//gets an array with the positions Around the box
		ArrayList<Coord> res = new ArrayList<Coord>();
		if((getBox(new Coord(pos.getX()-1,pos.getY()+1)))!=null) res.add(new Coord(pos.getX()-1,pos.getY()+1));
		if((getBox(new Coord(pos.getX(),pos.getY()+1)))!=null) res.add(new Coord(pos.getX(),pos.getY()+1));
		if((getBox(new Coord(pos.getX()+1,pos.getY()+1)))!=null) res.add(new Coord(pos.getX()+1,pos.getY()+1));
		if((getBox(new Coord(pos.getX()-1,pos.getY())))!=null) res.add(new Coord(pos.getX()-1,pos.getY()));
		if((getBox(new Coord(pos.getX()+1,pos.getY())))!=null) res.add(new Coord(pos.getX()+1,pos.getY()));
		if((getBox(new Coord(pos.getX()-1,pos.getY()-1)))!=null) res.add(new Coord(pos.getX()-1,pos.getY()-1));
		if((getBox(new Coord(pos.getX(),pos.getY()-1)))!=null) res.add(new Coord(pos.getX(),pos.getY()-1));
		if((getBox(new Coord(pos.getX()+1,pos.getY()-1)))!=null) res.add(new Coord(pos.getX()+1,pos.getY()-1));
		return res;	
	}
	
	public Box getBox(Coord pos){
		if((pos.getX()<1)||(pos.getY()<1)||(pos.getX()>SIZEX)||(pos.getY()>SIZEY)) return null;
		else return boxes[pos.getX()-1][pos.getY()-1];
	}
	
	public ImageView getView(Coord pos){
		if((pos.getX()<1)||(pos.getY()<1)||(pos.getX()>SIZEX)||(pos.getY()>SIZEY)) return null;
		else return views[pos.getX()-1][pos.getY()-1];
	}
	
	public void setNumMinesAround(Box box){//set the numbers of mines around for every box in the board	
		ArrayList<Coord> around = getBoxesAround(box.getPos());
		Iterator<Coord> iterator = around.iterator();
		Coord i = new Coord();
		while (iterator.hasNext()){
			i = iterator.next();
			if(getBox(i).hasMine())
				box.addMineAround();
		}
	}
	
	public int numFlagsAround(Box box){//returns the number of flags around a box
		int res = 0;
		ArrayList<Coord> around = getBoxesAround(box.getPos());
		Iterator<Coord> iterator = around.iterator();
		Coord i = new Coord();
		while (iterator.hasNext()){
			i = iterator.next();
			if(getBox(i).hasFlag())
				res++;
		}
		return res;
	}
	
	public String click(Coord pos, int type){
		
		String msg = "";
		
		if (type==Main.CLICK){//Procedure for a SORT CLICK
			if(getBox(pos)!=null){
				if(getBox(pos).isShown()){//if box is already shown, try to clean around
					msg=clean(pos);
				}
				else{//if box is hidden, flag it or remove the flag
					if(getBox(pos).hasFlag()){//if box was flagged, remove the flag
						flags--;
						getBox(pos).removeFlag();
						getView(pos).setImageResource(R.drawable.free);
					}
					else{
						if(flags<=mines){//if still positions to put flags
							if(noBoxIsShown()) msg = msg_start;
							flags++;
							getBox(pos).setToFlag();
							getView(pos).setImageResource(R.drawable.flag);
						}
					}
				}
			}
		}
		if(type==Main.LONGCLICK){//Procedure for a LONG CLICK
			
			if(getBox(pos).hasMine()){//if box is a mine -> Game Over!
				msg = msg_explod;
				getBox(pos).setStatus(Box.NOTFLAGGED);
				uncover(pos);
				deleteListeners();
			}
			else{
				uncover(pos);
			}
		}
		//check for finish (if there is no more boxes to open or to flag)
		if (isFinished())
			if((msg.compareTo("")==0)||(msg.compareTo("clean")==0)){
				if(this.getFlags()==this.getMines()){
					Date end = new Date();
					long timeUsed = (end.getTime() - start.getTime())/1000;
					msg = msg_won + timeUsed + " seconds!";
					deleteListeners();
				}
		}
		return msg;
	}
	
	public String clean(Coord pos){
		
		String msg = "";
		if(getBox(pos).getNumMines()==numFlagsAround(getBox(pos))){	
			//if num of mines around is equal to the boxes flagged around, we can uncover the boxes around
			ArrayList<Coord> around = getBoxesAround(pos);
			Iterator<Coord> iterator = around.iterator();
			Coord i = new Coord();
			if(badFlagged(pos)){ 
				msg = msg_wrong;
				while(iterator.hasNext()){
					i = iterator.next();
					uncover(i);
				} 
				deleteListeners();
			}
			else{
				while(iterator.hasNext()){
					i = iterator.next();
					uncover(i);
					if(getBox(i).getNumMines()==0) msg="clean";
				}
			}
		}
		return msg;
	}
	
	public void autoUncover(Coord pos){
		Coord aux = new Coord(pos);
    	ArrayList<Coord> array = new ArrayList<Coord>();
    	boolean more=true;
    	array.add(pos);
    	while(more){
    		more=false;
    		for (int x=0;x<SIZEX;x++){
      			for (int y=0;y<SIZEY;y++){
      				aux = new Coord(x+1,y+1);
      				if(aux.isAdyacent(array)) if(getBox(aux).getNumMines()==0) if(aux.isInList(array)==false){
      					array.add(aux);
      					more=true;
      				}
      			}
    		}
    	}
    	
    	Iterator<Coord> iterator=array.iterator();
    	while(iterator.hasNext()){
    		aux = iterator.next();
    			uncover(aux);
    			click(aux,Main.CLICK);
    	}
    }
	
	public boolean isFinished(){
		if ((getNumBoxesShown()+getFlags())==(Board.SIZEX*Board.SIZEY)) return true;
		else return false;
	}
	
	public int getNumBoxesShown(){
		int res=0;
		for (int x=0;x<SIZEX;x++){
			for (int y=0;y<SIZEY;y++){
				if(boxes[x][y].isShown()) res++;
			}
		}
		return res;
	}
	
	public boolean noBoxIsShown(){
		boolean res=true;
		for (int x=0;x<SIZEX;x++){
			for (int y=0;y<SIZEY;y++){
				if(boxes[x][y].isShown()) return false;
			}
		}
		return res;
	}
	
	public boolean badFlagged(Coord pos){
		
		boolean res = false;
		ArrayList<Coord> around = getBoxesAround(pos);
		Iterator<Coord> iterator = around.iterator();
		Coord i = new Coord();
		while(iterator.hasNext()){
			i = iterator.next();
			if((getBox(i).hasFlag())&&(!getBox(i).hasMine())){//if is a bad flagged box
				getBox(i).setStatus(Box.BADFLAGGED);
				getBox(i).setToNotFlag();
				res=true;
			}
			if((!getBox(i).hasFlag())&&(getBox(i).hasMine())){//if is a not flagged box
				getBox(i).setStatus(Box.NOTFLAGGED);
				getBox(i).setToNotFlag();
				res=true;
			}
		}
		return res;
		
	}
		
	
	public void AddFlag(){
		flags++;
	}
	
	public void deleteListeners(){
		for (int x=0;x<SIZEX;x++){
    		for (int y=0;y<SIZEY;y++){
				views[x][y].setOnClickListener(null);
				views[x][y].setOnLongClickListener(null);
				if(boxes[x][y].hasMine()) uncover(new Coord(x+1,y+1));
			}
		}
	}
	
	public void uncover(Coord pos){
		
		if (getBox(pos)!=null){
			
			if (getBox(pos).hasFlag()==false){
				getBox(pos).setToShown();
		
				switch(getBox(pos).getNumMines()){
				case 0: 
					getView(pos).setImageResource(R.drawable.c0);
					return;
				case 1:
					getView(pos).setImageResource(R.drawable.c1);
					return;
				case 2:
					getView(pos).setImageResource(R.drawable.c2);
					return;
				case 3:
					getView(pos).setImageResource(R.drawable.c3);
					return;
				case 4:
					getView(pos).setImageResource(R.drawable.c4);
					return;
				case 5:
					getView(pos).setImageResource(R.drawable.c5);
					return;
				case 6:
					getView(pos).setImageResource(R.drawable.c6);
					return;
				case 7:
					getView(pos).setImageResource(R.drawable.c7);
					return;
				case 8:
					getView(pos).setImageResource(R.drawable.c8);
					return;
				case 9:
					getView(pos).setImageResource(R.drawable.flag_wrong);
					return;
				case 10:
					getView(pos).setImageResource(R.drawable.mine_wrong);
					return;
				case 11:
					getView(pos).setImageResource(R.drawable.mine);
					return;
				}
			}
		}
	}
	
	public int getMines(){
		return mines;
	}
	public int getFlags(){
		return flags;
	}
}
