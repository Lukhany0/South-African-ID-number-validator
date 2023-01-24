import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

//South African Identity Number Validator
//Application GUI
//PROGRAM FRAME
public class SAIDNoValidatorGUI extends JFrame implements ActionListener{
	
	JButton exit_btn = new JButton();
	JButton reset_btn = new JButton();
	
	ApplicationPanel app;
	
	public SAIDNoValidatorGUI(){
		
		exit_btn.setBounds(130,400,90,30);
		exit_btn.setText("Exit");
		exit_btn.setFocusable(false);
		exit_btn.setBackground(Color.red);
		exit_btn.addActionListener(this);
		
		reset_btn.setBounds(230,400,90,30);
		reset_btn.setText("Reset");
		reset_btn.setFocusable(false);
		reset_btn.setBackground(Color.green);
		reset_btn.addActionListener(this);
		
		
		this.setTitle("SA ID number validator");
		this.setSize(500,500);
		this.getContentPane().setBackground(new Color(163, 234, 192));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		
		app = new ApplicationPanel();
		this.add(exit_btn);
		this.add(reset_btn);
		this.add(app);
		this.setVisible(true);
		
	}
	//reset 
	public void Restart() {
		
		this.remove(app);
		app = new ApplicationPanel();
		this.add(app);
		SwingUtilities.updateComponentTreeUI(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == exit_btn) {
			System.exit(0);
		}
		if(e.getSource() == reset_btn) {
			Restart();
		}
	}
}
