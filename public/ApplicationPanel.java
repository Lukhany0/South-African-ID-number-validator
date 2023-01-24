
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ApplicationPanel extends JPanel implements ActionListener{
	
	String idno;
	JButton submit_btn = new JButton();
	JButton exit_btn = new JButton();
	JButton reset_btn = new JButton();
	JTextField textField;
	JTextArea textArea = new JTextArea();
	JLabel results_lbl;	//results label
 	JLabel heading_lbl;	//heading label 
	JLabel info_lbl;
	JLabel det_lbl;
	
	SAIDValidator idc = new SAIDValidator();
	
	public ApplicationPanel() {

			textField = new JTextField(13);
			textField.setFont(new Font("Calibri", Font.PLAIN, 20));
			textField.setBounds(130,50,200,50);
			
			
			submit_btn.setBounds(130,110,90,30);
			submit_btn.setText("Submit");
			submit_btn.setFocusable(false);
			submit_btn.setBackground(Color.cyan);
			submit_btn.addActionListener(this);
			
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
			
			results_lbl = new JLabel();
			results_lbl.setFont(new Font("MV Boli", Font.BOLD, 15));
			results_lbl.setBounds(20,150,450,50);
			
			JLabel label = new JLabel();
			label.setText("Enter ID no: ");
			label.setFont(new Font("Calibri", Font.PLAIN, 20));
			label.setBounds(20, 50, 120, 50);
			label.setBackground(Color.blue);
			
			heading_lbl = new JLabel();
			heading_lbl.setFont(new Font("MV Boli", Font.PLAIN, 20));
			heading_lbl.setBounds(20, 10, 440,30);
			heading_lbl.setText("Check whether ID no is a valid SA ID no");
			
			det_lbl = new JLabel();
			det_lbl.setFont(new Font("MV Boli", Font.BOLD, 15));
			det_lbl.setBounds(20,200,450,50);
			det_lbl.setText("Details:");
			det_lbl.setVisible(false); // hide label; only show if results are positive
			
			info_lbl = new JLabel();
			info_lbl.setFont(new Font("MV Boli", Font.PLAIN, 20));
			info_lbl.setBounds(20, 200, 440,200);
			
			this.add(info_lbl);
			this.add(det_lbl);
			this.add(heading_lbl);
			this.add(label);
			this.add(submit_btn);
			this.add(exit_btn);
			this.add(reset_btn);
			this.add(textField);
			this.add(results_lbl);
			
			this.setSize(500,400);
			this.setBackground(new Color(163, 234, 192));
			this.setLayout(null);
			this.setVisible(true);
				
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == submit_btn) {
				textField.setEnabled(false);
				idno = textField.getText();
				
				idc.Validate(idno);
				boolean val = idc.isValid;
				
				if(val) {
					results_lbl.setText(idno + " is a valid South African ID number");
					results_lbl.setForeground(new Color(52,148,18));
					det_lbl.setVisible(true);
					info_lbl.setText("<html>Date of Birth: " + idc.getDob() + "<br/>Age: " + idc.getAge() + "<br/>Gender: " + idc.getGender() + " <br/>Citizen status: "+ idc.getCitizen() +" <br/></html>");
					
				}
				else {
					results_lbl.setText(idno + " is not a valid South African ID number");
					results_lbl.setForeground(Color.red);
				}
			}
		}
}
