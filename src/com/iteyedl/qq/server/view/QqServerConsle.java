/**
 * Server control GUI, start server, shut server, manage and surveillant customers.
 */
package com.iteyedl.qq.server.view;

import javax.swing.*;

import com.iteyedl.qq.server.model.QqServer;

import java.awt.*;
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QqServerConsle extends JFrame implements ActionListener {

	JPanel jp1;
	JButton jb1, jb2;
//	QqServer qs;
	
	public static void main(String[] args) {
		QqServerConsle qqServerConsle = new QqServerConsle();
	}
	
	public QqServerConsle() {
		jp1 = new JPanel();
		jb1 = new JButton("Start Server");
		jb1.addActionListener(this);
		jb2 = new JButton("Shut Server");
//		jb2.addActionListener(this);
		jp1.add(jb1);
		jp1.add(jb2);
		
		this.add(jp1);
		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jb1) {
			new QqServer();
		} 
//		else if(e.getSource() == jb2) {
//			qs.shut();
//		}
	}
	
}
