package com.test;

import javax.swing.*;//框架
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Test_JPanel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame =  new JFrame("窗口");
		Stage pane = new Stage();
		frame.setLayout(null);//取消窗口的默认布局，取消自动充满
		frame.add(pane);
		pane.setSize(10*35,10*35);
		pane.setLocation(50,50);
		frame.setSize(450,480);
		pane.setBorder(new LineBorder(Color.black));
		frame.setLocationRelativeTo(null);//frame显示在桌面的正中央
		frame.setVisible(true);//如果没有这条语句窗口将不会显示在桌面上
		
		pane.requestFocus();//获得面板的焦点，是的键盘的输入目标作用在pane上
		pane.addKeyListener(new KeyAdapter(){

			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				/** UP          38
				 *   DOWN   40
				 *   LEFT       37
				 *   RIGHT    39
				 *  */
				System.out.println(" 按键有反应了！！！");
				System.out.println(" 按键有反应了！！！"+arg0.getKeyCode());
				
			}

			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}

}
 class Stage extends JPanel{
	public void paint(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(0, 0, this.getWidth(), getHeight());
		g.setColor(Color.red);
		g.fill3DRect(10,10, 50,50, true);
	}
}
