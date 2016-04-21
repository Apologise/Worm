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
	/** �������� */
	public static final int ROWS  = 35;
	/** �������� */
	public static final int COLS  = 35;
	/** ���ӵ����ش�С */
	public static final int  CELL_SIZE= 10;
	
	private Worm worm;
	private Cell food;
	
	public WormStage(){
		worm = new Worm();
		food = createFood();
	}
	/**
	 * ����ʳ�
	 * 1���������x��y
	 * 2������߰�����x��y����������һ��
	 * 3������߲�������x��y�����Ǿʹ���ʳ��
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
	
	/** ��дJPanel�еĻ��Ʒ��� */
	public void paint(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(0, 0, this.getWidth(), getHeight());
		g.setColor(Color.red);
		/** ����ʳ�� */
		g.fill3DRect(CELL_SIZE * food.getX(),CELL_SIZE * food.getY(), CELL_SIZE,CELL_SIZE, true);
		/** ������ */
		g.setColor(Color.green);
		/** ע�ⲻ��ֱ�ӵ�worm.cells.length�����Ǵ����
		 * Ӧ���Ȱ�worm�����е�Cellȡ����
		 * */
		Cell[] cells = worm.getCells();
		for(int i = 0;i<cells.length;i++){
			Cell node = cells[i];
			g.fill3DRect(CELL_SIZE * node.getX(),
					CELL_SIZE * node.getY(), CELL_SIZE,CELL_SIZE, true);
		}
	}
	
	/** ���ô��ڶ��� */
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
			/** ���п����߼� */
			public void run(){
				/** �����⵽��ײ����Ϸ�����¿�ʼ */
				if(worm.hit()){
					JOptionPane.showMessageDialog(null, "��ʧ����");
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
	
	/** ��������ߵķ���Ӧ���� */
	public void creepTo(int direction){
		if(worm.hit()){
			JOptionPane.showMessageDialog(null, "��ʧ����");
			System.out.println("ִ�е�����");
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
		JFrame frame =  new JFrame("̰����");
		WormStage pane = new WormStage();
		frame.setLayout(null);//ȡ�����ڵ�Ĭ�ϲ��֣�ȡ���Զ�����
		frame.add(pane);
		pane.setSize(10*35,10*35);
		pane.setLocation(50,50);
		frame.setSize(450,480);
		pane.setBorder(new LineBorder(Color.black));
		frame.setLocationRelativeTo(null);//frame��ʾ�������������
		frame.setVisible(true);//���û��������䴰�ڽ�������ʾ��������
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		pane.action();
	}
}
