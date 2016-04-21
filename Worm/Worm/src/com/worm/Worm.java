package com.worm;

import java.util.Arrays;

public class Worm {
	/** 蛇的默认长度*/
	public static final int DEFAULT_LENGTH = 12;
	/** 定义方向值*/
	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	
	private Cell[] cells;
	/** 蛇当前的运动方向 */
	private int currentDirection;
	
	public Worm(){
		cells = new Cell[DEFAULT_LENGTH];
		for(int i = 0;i<cells.length;i++)
			cells[i] = new Cell(i,0);
		/** 蛇的初始默认运动方向为下*/
		currentDirection  =DOWN;
	}
	
	public boolean contains(int x,int y){
		for(int i = 0;i<cells.length ;i++){
			Cell node =  cells[i];
			if(x==node.getX()&&y==node.getY())
				return true;
		}
		return false;
	}
	
	public void creep(){
		for(int i = cells.length-1;i>0;i--)
			cells[i] = cells[i-1];
		cells[0] = createHead(currentDirection);
	}
	
	public Cell createHead(int dirention){
		int x,y;
		switch(dirention){
		case UP :
			 x = cells[0].getX();
			 y  =cells[0].getY();
			return new Cell(x,--y);
		case DOWN :
			 x = cells[0].getX();
			 y  =cells[0].getY();
			return new Cell(x,++y);
		case LEFT :
			 x = cells[0].getX();
			 y  =cells[0].getY();
			return new Cell(--x,y);
		case RIGHT :
			 x = cells[0].getX();
			 y  =cells[0].getY();
			return new Cell(++x,y);
		default:
			break;
		}
		return null;
	}
	
	/**
	 * 吃食物的爬行算法
	 * 1）如果指定的方向与当前方向相反，不做任何动作
	 * 2）如果改变当前方向，就将指定的方向赋给当前的方向，作为下次运行的方向，判断创建的头节点当前头结点的坐标与食物的坐标
	 * 		是否一致，如果一致，则表明吃到食物，对数组进行扩容
	 * 
	 * */
	public boolean creep(int direction,Cell food){
		if(direction+currentDirection ==0)
			return false;
		 currentDirection = direction;
		 Cell head = createHead(currentDirection);
		 boolean eat = false;
		 eat = head.getX()==food.getX()&&head.getY()==food.getY();
		 if(eat){
			 System.out.println("吃了");
		 	System.out.println(cells.length);
			 /** 注意数组扩容中的2个参数的格式 
			  * 同时函数Arrays.copyOf()一定要赋给数组
			  * */
			 cells = Arrays.copyOf(cells, cells.length+1);
		 }
		 	
		 for(int i = cells.length-1;i>=1;i--)
			 cells[i] = cells[i-1];
		 cells[0] = head;
		 System.out.println(cells.length);
		 return eat;
	}
	
	/** 重载方法 */
	public boolean creep(Cell food){
		return creep(currentDirection,food);
	}
	
	/** 检查碰撞方法
	 * 1)碰到墙壁
	 * 2）碰到自己（注意当头结点运动方向的下一个节点是自己的尾巴，则表示不会碰到
	 * 		因为下一运动时刻不会碰到）
	 * */
	public boolean hit(int direction){
		if(direction+currentDirection == 0)
			return false;
		else{
			Cell head = createHead(direction);
			
		/** 检测是否碰到墙壁*/
		if(head.getX()<0||head.getX()>=WormStage.COLS ||
			head.getY()<0||head.getY()>=WormStage.ROWS ){
			System.out.println(head+"peng");
			return true;
		}
		/** 检测是否会碰到自己 */
		for(int i =  0;i<cells.length-1;i++){
			if(head.getX()==cells[i].getX()&&head.getY() ==cells[i].getY()){
				return true;
			}
		}
		}
		return false;
	}
	
	/** 重载方法：检查在当前方向是否会撞到墙（即不按任何方向的情况下检测是否会撞到墙）*/
	public boolean hit(){
		return hit(currentDirection);
	}
	
	/** 取出蛇中所有的Cell */
	public Cell[] getCells(){
		/** 返回数组的副本，防止数组被不法分子篡改*/
		return Arrays.copyOf(cells, cells.length);
		/** 其实可以return cells ,但是这样不安全 */
		
	}
	
 /** 调试用的方法 */
	public String toString(){
		return Arrays.toString(cells);
	}
}


