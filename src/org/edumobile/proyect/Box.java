package org.edumobile.proyect;

public class Box {
	
	public static final boolean HIDDEN=true;
	public static final boolean SHOWN=false;
	public static final boolean MINE=true;
	public static final boolean NOMINE=false;
	public static final boolean FLAG=true;
	public static final boolean NOFLAG=false;
	public static final int BADFLAGGED = 9;
	public static final int NOTFLAGGED = 10;
	
	private Coord pos = new Coord();
	private boolean state = HIDDEN;
	private boolean mine = NOMINE;
	private boolean flag = NOFLAG;
	private boolean bad_flagged = false;
	private int num_mines = 0;
	
	public Box(){
		pos.setX(0);
		pos.setY(0);
	}
	public Box(int x,int y){
		pos.setX(x);
		pos.setY(y);
	}
	public Box(int x){
		pos.setX(x);
		pos.setY(x);
	}
	public Box(Box copy){
		pos.setX(copy.getPos().getX());
		pos.setY(copy.getPos().getY());
	}
	
	public void setNumMines(int value){
		num_mines=value;
	}
	public void setPos(Coord x){
		pos.setCoord(x);
	}
	public void setToShown(){
		state=SHOWN;
	}
	public void setToBadFlagged(){
		bad_flagged = true;
	}
	public void setToNotBadFlagged(){
		bad_flagged = false;
	}
	public void setToMine(){
		mine=MINE;
	}
	public void setToFlag(){
		flag=FLAG;
	}
	public void setToNotFlag(){
		flag=NOFLAG;
	}
	public void removeFlag(){
		flag=NOFLAG;
	}
	public void setStatus(int status){
		num_mines=status;
	}
	
	public Coord getPos(){
		return pos;
	}
	public boolean isShown(){
		boolean res = false;
		if (state==SHOWN) res=true;
		return res;
	}
	public boolean hasMine(){
		return mine; 
	}
	public boolean hasFlag(){
		return flag;
	}
	public boolean isBadFlagged(){
		return bad_flagged;
	}
	public int getNumMines(){
		return num_mines;
	}
	
	public void addMineAround(){
		num_mines++;
	}
	

}
