package com.test;

import javax.swing.*;//���
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
		JFrame frame =  new JFrame("����");
		Stage pane = new Stage();
		frame.setLayout(null);//ȡ�����ڵ�Ĭ�ϲ��֣�ȡ���Զ�����
		frame.add(pane);
		pane.setSize(10*35,10*35);
		pane.setLocation(50,50);
		frame.setSize(450,480);
		pane.setBorder(new LineBorder(Color.black));
		frame.setLocationRelativeTo(null);//frame��ʾ�������������
		frame.setVisible(true);//���û��������䴰�ڽ�������ʾ��������
		
		pane.requestFocus();//������Ľ��㣬�ǵļ��̵�����Ŀ��������pane��
		pane.addKeyListener(new KeyAdapter(){

			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				/** UP          38
				 *   DOWN   40
				 *   LEFT       37
				 *   RIGHT    39
				 *  */
				System.out.println(" �����з�Ӧ�ˣ�����");
				System.out.println(" �����з�Ӧ�ˣ�����"+arg0.getKeyCode());
				
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
