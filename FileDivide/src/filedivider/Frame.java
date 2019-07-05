package filedivider;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Frame extends JFrame implements ActionListener {
	
	private JPanel contentPane;
	private JLabel l2;
	private JLabel l1;
	private JLabel l5;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField nf1;
	private JTextField nf2;
	private JTextField nf3;
	private JButton b1;
	private JButton b2;
	private JButton run;
	
	private String src;
	private String dir;
	
	public Frame() {
		
		int X = 600;
		int Y = 230;
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		
		setTitle("Divider");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((screenSize.width / 2) - (X / 2), (screenSize.height / 2) - (Y / 2), X, Y);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel p1 = new JPanel();
		p1.setBounds(12, 12, 570, 33);
		contentPane.add(p1);
		p1.setLayout(null);
		
		l1 = new JLabel("데이터 파일:");
		l1.setFont(new Font("Gulim", Font.PLAIN, 12));
		l1.setHorizontalAlignment(SwingConstants.RIGHT);
		l1.setBounds(5, 10, 75, 15);
		p1.add(l1);
		
		tf1 = new JTextField();
		tf1.setFont(new Font("Gulim", Font.PLAIN, 12));
		tf1.setBounds(90, 6, 433, 26);
		tf1.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
		p1.add(tf1);
		tf1.setColumns(30);
		
		b1 = new JButton("...");
		b1.setFont(new Font("Gulim", Font.PLAIN, 12));
		b1.setBackground(new Color(220, 220, 220));
		b1.setBounds(535, 7, 23, 23);
		b1.addActionListener(this);
		p1.add(b1);
		
		JPanel p2 = new JPanel();
		p2.setBounds(12, 55, 570, 33);
		contentPane.add(p2);
		p2.setLayout(null);
		
		l2 = new JLabel("저장 위치:");
		l2.setFont(new Font("Gulim", Font.PLAIN, 12));
		l2.setHorizontalAlignment(SwingConstants.RIGHT);
		l2.setBounds(5, 10, 75, 15);
		p2.add(l2);
		
		tf2 = new JTextField();
		tf2.setFont(new Font("Gulim", Font.PLAIN, 12));
		tf2.setBounds(90, 6, 433, 26);
		tf2.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
		p2.add(tf2);
		tf2.setColumns(30);
		
		b2 = new JButton("...");
		b2.setFont(new Font("Gulim", Font.PLAIN, 12));
		b2.setBackground(new Color(220, 220, 220));
		b2.setBounds(535, 7, 23, 23);
		b2.addActionListener(this);
		p2.add(b2);
		
		JPanel p3 = new JPanel();
		p3.setBounds(12, 98, 570, 33);
		contentPane.add(p3);
		p3.setLayout(null);
		
		l5 = new JLabel("시작 점:");
		l5.setHorizontalAlignment(SwingConstants.RIGHT);
		l5.setFont(new Font("굴림", Font.PLAIN, 12));
		l5.setBounds(5, 10, 75, 15);
		p3.add(l5);
		
		nf1 = new JTextField();
		nf1.setHorizontalAlignment(SwingConstants.RIGHT);
		nf1.setBounds(90, 6, 61, 26);
		nf1.setFont(new Font("Gulim", Font.PLAIN, 12));
		nf1.setText("0");
		nf1.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
		p3.add(nf1);
		nf1.setColumns(5);
		
		JLabel lblS = new JLabel("(sec)");
		lblS.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblS.setBounds(156, 11, 47, 15);
		p3.add(lblS);
		
		JLabel endLabel = new JLabel("끝 점:");
		endLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		endLabel.setFont(new Font("Gulim", Font.PLAIN, 12));
		endLabel.setBounds(215, 10, 41, 15);
		p3.add(endLabel);
		
		nf2 = new JTextField();
		nf2.setText("0");
		nf2.setHorizontalAlignment(SwingConstants.RIGHT);
		nf2.setBounds(268, 5, 61, 26);
		nf2.setFont(new Font("Gulim", Font.PLAIN, 12));
		p3.add(nf2);
		nf2.setColumns(5);
		
		JLabel lblSec = new JLabel("(sec)");
		lblSec.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblSec.setBounds(335, 10, 57, 15);
		p3.add(lblSec);
		
		JLabel lblNewLabel_1 = new JLabel("등분:");
		lblNewLabel_1.setFont(new Font("Gulim", Font.PLAIN, 12));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(404, 10, 57, 15);
		p3.add(lblNewLabel_1);
		
		nf3 = new JTextField();
		nf3.setHorizontalAlignment(SwingConstants.RIGHT);
		nf3.setBounds(473, 5, 61, 26);
		nf3.setFont(new Font("Gulim", Font.PLAIN, 12));
		nf3.setText("0");
		nf3.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
		p3.add(nf3);
		nf3.setColumns(5);
		
		JPanel p5 = new JPanel();
		p5.setBounds(12, 152, 570, 33);
		contentPane.add(p5);
		
		run = new JButton("실행");
		run.setBackground(new Color(220, 220, 220));
		run.setFont(new Font("Gulim", Font.PLAIN, 12));
		run.addActionListener(this);
		p5.add(run);
		
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b1) {
			JFileChooser jfc = new JFileChooser();
			
			jfc.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("*.txt", "txt");
			jfc.addChoosableFileFilter(filter);
			
			int returnValue = jfc.showOpenDialog(null);
			//int returnValue = jfc.showSaveDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jfc.getSelectedFile();
				System.out.println(selectedFile.getAbsolutePath());
				tf1.setText(selectedFile.getAbsolutePath());
				src = selectedFile.getAbsolutePath();
			}
		} else if(e.getSource() == b2) {
			JFileChooser jfc = new JFileChooser();
			
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
			//int returnValue = jfc.showOpenDialog(null);
			int returnValue = jfc.showSaveDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				if (jfc.getSelectedFile().isDirectory()) {
					System.out.println("You selected the directory: " + jfc.getSelectedFile());
					tf2.setText(jfc.getSelectedFile().toString());
					dir = jfc.getSelectedFile().toString();
				}
			}
		} else if(e.getSource() == run) {
			//run
			int divide;
			double from, to;
			
			try {
				from = Double.valueOf(nf1.getText());
				to = Double.valueOf(nf2.getText());;
				divide = Integer.valueOf(nf3.getText());
				
				nf1.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				nf2.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				nf3.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				tf1.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				tf2.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				
				FileRead fileRead = new FileRead(src);
				
				Divider divider = new Divider(fileRead.getResult(), from, to, divide, dir);
				
				ArrayList<ArrayList<String>> averageList = divider.getAverageList();
				
				AverageFileWrite averageFileWrite = new AverageFileWrite(averageList, divider.getName(), dir + "\\AverageResult.txt");
				
				if(divider.isFinish()) {
					Dialog d = new Dialog(this, "파일이 저장되었습니다.");
				} else {
					Dialog d = new Dialog(this, "Error: fileWrite error");
				}
			} catch(NumberFormatException e1) {
				nf1.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				nf2.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				nf3.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			} catch(Exception e1) {
				tf1.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				tf2.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
			}
		}
		
	}

}
