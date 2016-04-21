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
		
		/** 测试舞台实例*/
		System.out.println("创建舞台实例");
		WormStage stage = new WormStage();
		System.out.println(stage);
		
		/** 测试包含算法 */
		System.out.println(worm.contains(1, 0));
		System.out.println(worm.contains(1, 1));
		
		/** 测试简单的爬行算法*/
		System.out.println("测试一下蛇的爬行算法");
		Worm worm1 = new Worm();
		System.out.println(worm1);
		worm1.creep();
		/** 蛇爬行后的位置*/
		System.out.println(worm1);
		
		/** 测试蛇吃食物的爬行算法*/
		System.out.println("蛇吃食物的爬行算法");
		Worm worm2 = new Worm();
		Cell food =  new Cell(1,2);
		System.out.println(worm2);
		System.out.println(worm2.creep(Worm.DOWN, food));
		System.out.println(worm2.creep(Worm.DOWN, food));
		System.out.println(worm2.creep(Worm.RIGHT, food));
		System.out.println("爬行结束后：");
		System.out.println(worm2);
		
		/** 测试蛇的碰撞 */
		System.out.println("蛇的碰撞检测");
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
		
		/** 测试数组扩容函数*/
		System.out.println(cell_.length);
		cell_ = Arrays.copyOf(cell_, cell_.length+1);
		System.out.println(cell_.length);
		
		System.out.println("吃食物测试");
		Worm  worm4 =  new Worm();
		Cell food4 = new Cell(0,1);		
		System.out.println(worm4);
		worm4.creep(Worm.DOWN, food4);
		System.out.println(worm4);
		
		
		
		
		
		
		
		
		
	}

}
