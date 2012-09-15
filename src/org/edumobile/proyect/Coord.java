package org.edumobile.proyect;

import java.util.ArrayList;
import java.util.Iterator;

public class Coord {
	
	private int x;
	private int y;
	
	public Coord(){
		x=0;
		y=0;
	}
	
	public Coord(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	public Coord(int x){
		this.x=x;
		this.y=x;
	}
	public Coord(Coord a){
		this.x=a.getX();
		this.y=a.getY();
	}
	
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setCoord(Coord x){
		this.x=x.x;
		this.y=x.y;
	}
	
	public boolean isEqual(Coord a){
		boolean res = false;
		if((getX()==a.getX())&&(getY()==a.getY())){
			res = true;
		}
		return res;
	}
	
	public boolean isGood(){
		boolean res=true;
		if((x<1)||(x>Board.SIZEX)||(y<1)||(y>Board.SIZEY)) res=false;
		return res;
	}
	
	public boolean isAdyacent(Coord pos){
		boolean res = false;
		int difx = 0;
		int dify = 0;
		if(this.isGood()){
			if(this.isEqual(pos)==false){
				difx = this.getX()-pos.getX();
				dify = this.getY()-pos.getY();
				if((difx>=-1)&&(difx<=1))
					if((dify>=-1)&&(dify<=1))
						res=true;
			}
		}
		return res;
	}
	
	public boolean isAdyacent(ArrayList<Coord> list){
		boolean res = false;
		Iterator<Coord> iterator = list.iterator();
		while(iterator.hasNext())
			if(isAdyacent(iterator.next())) return true;
		return res;
	}
	
	public boolean isInList(ArrayList<Coord> list){
		boolean res=false;
		Iterator<Coord> iterator = list.iterator();
		while(iterator.hasNext())
			if(iterator.next().isEqual(this)) return true;
		return res;
	}
}
