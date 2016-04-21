package com.worm;

import java.util.Arrays;

public class Worm {
	/** �ߵ�Ĭ�ϳ���*/
	public static final int DEFAULT_LENGTH = 12;
	/** ���巽��ֵ*/
	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	
	private Cell[] cells;
	/** �ߵ�ǰ���˶����� */
	private int currentDirection;
	
	public Worm(){
		cells = new Cell[DEFAULT_LENGTH];
		for(int i = 0;i<cells.length;i++)
			cells[i] = new Cell(i,0);
		/** �ߵĳ�ʼĬ���˶�����Ϊ��*/
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
	 * ��ʳ��������㷨
	 * 1�����ָ���ķ����뵱ǰ�����෴�������κζ���
	 * 2������ı䵱ǰ���򣬾ͽ�ָ���ķ��򸳸���ǰ�ķ�����Ϊ�´����еķ����жϴ�����ͷ�ڵ㵱ǰͷ����������ʳ�������
	 * 		�Ƿ�һ�£����һ�£�������Ե�ʳ��������������
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
			 System.out.println("����");
		 	System.out.println(cells.length);
			 /** ע�����������е�2�������ĸ�ʽ 
			  * ͬʱ����Arrays.copyOf()һ��Ҫ��������
			  * */
			 cells = Arrays.copyOf(cells, cells.length+1);
		 }
		 	
		 for(int i = cells.length-1;i>=1;i--)
			 cells[i] = cells[i-1];
		 cells[0] = head;
		 System.out.println(cells.length);
		 return eat;
	}
	
	/** ���ط��� */
	public boolean creep(Cell food){
		return creep(currentDirection,food);
	}
	
	/** �����ײ����
	 * 1)����ǽ��
	 * 2�������Լ���ע�⵱ͷ����˶��������һ���ڵ����Լ���β�ͣ����ʾ��������
	 * 		��Ϊ��һ�˶�ʱ�̲���������
	 * */
	public boolean hit(int direction){
		if(direction+currentDirection == 0)
			return false;
		else{
			Cell head = createHead(direction);
			
		/** ����Ƿ�����ǽ��*/
		if(head.getX()<0||head.getX()>=WormStage.COLS ||
			head.getY()<0||head.getY()>=WormStage.ROWS ){
			System.out.println(head+"peng");
			return true;
		}
		/** ����Ƿ�������Լ� */
		for(int i =  0;i<cells.length-1;i++){
			if(head.getX()==cells[i].getX()&&head.getY() ==cells[i].getY()){
				return true;
			}
		}
		}
		return false;
	}
	
	/** ���ط���������ڵ�ǰ�����Ƿ��ײ��ǽ���������κη��������¼���Ƿ��ײ��ǽ��*/
	public boolean hit(){
		return hit(currentDirection);
	}
	
	/** ȡ���������е�Cell */
	public Cell[] getCells(){
		/** ��������ĸ�������ֹ���鱻�������Ӵ۸�*/
		return Arrays.copyOf(cells, cells.length);
		/** ��ʵ����return cells ,������������ȫ */
		
	}
	
 /** �����õķ��� */
	public String toString(){
		return Arrays.toString(cells);
	}
}


