package com.worm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

//import com.test.Stage;

public class WormStage extends JPanel{
	/** 定义行数 */
	public static final int ROWS  = 35;
	/** 定义列数 */
	public static final int COLS  = 35;
	/** 格子的像素大小 */
	public static final int  CELL_SIZE= 10;
	
	private Worm worm;
	private Cell food;
	
	public WormStage(){
		worm = new Worm();
		food = createFood();
	}
	/**
	 * 生成食物！
	 * 1）随机生成x和y
	 * 2）如果蛇包含（x，y），继续第一步
	 * 3）如果蛇不包含（x，y），那就创建食物
	 * */
	private Cell createFood(){
		int x;
		int y;
		Random random = new Random();
		do{
			x = random.nextInt(ROWS);
			y = random.nextInt(COLS);
		}while(worm.contains(x,y));
		return 	new Cell(x,y);
	}
	
	/** 重写JPanel中的绘制方法 */
	public void paint(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(0, 0, this.getWidth(), getHeight());
		g.setColor(Color.red);
		/** 绘制食物 */
		g.fill3DRect(CELL_SIZE * food.getX(),CELL_SIZE * food.getY(), CELL_SIZE,CELL_SIZE, true);
		/** 绘制蛇 */
		g.setColor(Color.green);
		/** 注意不能直接点worm.cells.length这样是错误的
		 * 应该先把worm中所有的Cell取出来
		 * */
		Cell[] cells = worm.getCells();
		for(int i = 0;i<cells.length;i++){
			Cell node = cells[i];
			g.fill3DRect(CELL_SIZE * node.getX(),
					CELL_SIZE * node.getY(), CELL_SIZE,CELL_SIZE, true);
		}
	}
	
	/** 设置窗口动作 */
	public void action(){
		this.requestFocus();
		this.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				int key = e.getKeyCode();
				switch(key){
				/** UP          38
				 *   DOWN   40
				 *   LEFT       37
				 *   RIGHT    39
				 *  */
					case KeyEvent.VK_UP :
						creepTo(Worm.UP);break;
					case KeyEvent.VK_DOWN :
						creepTo(Worm.DOWN);break;
					case KeyEvent.VK_LEFT :
						creepTo(Worm.LEFT);break;
					case KeyEvent.VK_RIGHT :
						creepTo(Worm.RIGHT);break;
				}
			}
		});
		Timer timer  = new Timer();
		timer.schedule(new TimerTask(){
			/** 爬行控制逻辑 */
			public void run(){
				/** 如果检测到碰撞后游戏将重新开始 */
				if(worm.hit()){
					JOptionPane.showMessageDialog(null, "你失败了");
					worm = new Worm();
					food  = createFood(); 
				}else{
					boolean eat = worm.creep(food);
					if(eat)
						food =  createFood();
				}
			
				repaint();
			}
		},0,1000/5);
		
	}
	
	public String toString(){
		return "worm"+worm+"\nfood"+food;
	}
	
	/** 按键检测蛇的方向反应方法 */
	public void creepTo(int direction){
		if(worm.hit()){
			JOptionPane.showMessageDialog(null, "你失败了");
			System.out.println("执行到这了");
			worm = new Worm();
			System.out.println(worm);
			food = createFood();
		}else{
			boolean eat = worm.creep(direction,food);
			if(eat)food = createFood();
			repaint();
		}
	}

	
	public static void main(String[] args){
		JFrame frame =  new JFrame("贪吃蛇");
		WormStage pane = new WormStage();
		frame.setLayout(null);//取消窗口的默认布局，取消自动充满
		frame.add(pane);
		pane.setSize(10*35,10*35);
		pane.setLocation(50,50);
		frame.setSize(450,480);
		pane.setBorder(new LineBorder(Color.black));
		frame.setLocationRelativeTo(null);//frame显示在桌面的正中央
		frame.setVisible(true);//如果没有这条语句窗口将不会显示在桌面上
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		pane.action();
	}
}
