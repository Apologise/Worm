package com.test;

import java.util.Arrays;

import com.worm.Cell;
import com.worm.Worm;
import com.worm.WormStage;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Worm worm  = new Worm();
		System.out.println(worm);
		
		/** ������̨ʵ��*/
		System.out.println("������̨ʵ��");
		WormStage stage = new WormStage();
		System.out.println(stage);
		
		/** ���԰����㷨 */
		System.out.println(worm.contains(1, 0));
		System.out.println(worm.contains(1, 1));
		
		/** ���Լ򵥵������㷨*/
		System.out.println("����һ���ߵ������㷨");
		Worm worm1 = new Worm();
		System.out.println(worm1);
		worm1.creep();
		/** �����к��λ��*/
		System.out.println(worm1);
		
		/** �����߳�ʳ��������㷨*/
		System.out.println("�߳�ʳ��������㷨");
		Worm worm2 = new Worm();
		Cell food =  new Cell(1,2);
		System.out.println(worm2);
		System.out.println(worm2.creep(Worm.DOWN, food));
		System.out.println(worm2.creep(Worm.DOWN, food));
		System.out.println(worm2.creep(Worm.RIGHT, food));
		System.out.println("���н�����");
		System.out.println(worm2);
		
		/** �����ߵ���ײ */
		System.out.println("�ߵ���ײ���");
		Worm worm3 = new Worm();
		food = new Cell(10,10);
		System.out.println(worm3);
		System.out.println(worm3.creep(Worm.DOWN,food));
		System.out.println(worm3);
		System.out.println(worm3.creep(Worm.DOWN,food));
		System.out.println(worm3);
		System.out.println(worm3.hit(Worm.LEFT));
		System.out.println(worm3);
		System.out.println(worm3.hit(Worm.RIGHT));
		Cell[] cell_ = new Cell[13];
		
		/** �����������ݺ���*/
		System.out.println(cell_.length);
		cell_ = Arrays.copyOf(cell_, cell_.length+1);
		System.out.println(cell_.length);
		
		System.out.println("��ʳ�����");
		Worm  worm4 =  new Worm();
		Cell food4 = new Cell(0,1);		
		System.out.println(worm4);
		worm4.creep(Worm.DOWN, food4);
		System.out.println(worm4);
		
		
		
		
		
		
		
		
		
	}

}
