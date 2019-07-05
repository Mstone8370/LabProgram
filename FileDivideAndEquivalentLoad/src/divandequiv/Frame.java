package divandequiv;

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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Frame extends JFrame implements ActionListener {
	
	private JPanel contentPane;
	private JLabel l2;
	private JLabel l1;
	private JLabel l3;
	private JLabel l4;
	private JLabel l5;
	private JLabel l6;
	private JLabel l7;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField nf1;
	private JTextField nf2;
	private JTextField nf3;
	private JTextField nf4;
	private JTextField nf5;
	private JButton b1;
	private JButton b2;
	private JButton run;
	
	private String src;
	private String dir;
	
	private double[] inputArr;
	
	public Frame() {
		
		int X = 620;
		int Y = 360;
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		
		setTitle("File Divide & Equivalent Load");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((screenSize.width / 2) - (X / 2), (screenSize.height / 2) - (Y / 2), X, Y);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//-----------------------------------------------------------------------------------------
		
		JPanel filePanel = new JPanel();
		filePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "File select & Save", TitledBorder.LEADING, TitledBorder.TOP, (new Font("Gulim", Font.PLAIN, 12)), new Color(0, 0, 0)));
		filePanel.setBounds(12, 20, 590, 110);
		contentPane.add(filePanel);
		filePanel.setLayout(null);
		
		JPanel p1 = new JPanel();
		p1.setBounds(10, 20, 570, 33);
		filePanel.add(p1);
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
		
		//-----------------------------------------------------------------------------------------
		
		JPanel p2 = new JPanel();
		p2.setBounds(10, 60, 570, 33);
		filePanel.add(p2);
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
		
		//-----------------------------------------------------------------------------------------
		
		JPanel dividePanel = new JPanel();
		dividePanel.setBounds(12, 140, 590, 60);
		dividePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "File Divide", TitledBorder.LEADING, TitledBorder.TOP, (new Font("Gulim", Font.PLAIN, 12)), new Color(0, 0, 0)));
		contentPane.add(dividePanel);
		dividePanel.setLayout(null);
		
		l5 = new JLabel("시작 점:");
		l5.setHorizontalAlignment(SwingConstants.RIGHT);
		l5.setFont(new Font("굴림", Font.PLAIN, 12));
		l5.setBounds(5, 26, 75, 15);
		dividePanel.add(l5);
		
		nf1 = new JTextField();
		nf1.setHorizontalAlignment(SwingConstants.RIGHT);
		nf1.setBounds(90, 21, 61, 26);
		nf1.setFont(new Font("Gulim", Font.PLAIN, 12));
		nf1.setText("0");
		nf1.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
		dividePanel.add(nf1);
		nf1.setColumns(5);
		
		JLabel sec1 = new JLabel("(sec)");
		sec1.setFont(new Font("Gulim", Font.PLAIN, 12));
		sec1.setBounds(156, 26, 47, 15);
		dividePanel.add(sec1);
		
		JLabel endLabel = new JLabel("끝 점:");
		endLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		endLabel.setFont(new Font("Gulim", Font.PLAIN, 12));
		endLabel.setBounds(220, 26, 41, 15);
		dividePanel.add(endLabel);
		
		nf2 = new JTextField();
		nf2.setText("0");
		nf2.setHorizontalAlignment(SwingConstants.RIGHT);
		nf2.setBounds(273, 21, 61, 26);
		nf2.setFont(new Font("Gulim", Font.PLAIN, 12));
		dividePanel.add(nf2);
		nf2.setColumns(5);
		
		JLabel sec2 = new JLabel("(sec)");
		sec2.setFont(new Font("Gulim", Font.PLAIN, 12));
		sec2.setBounds(340, 26, 57, 15);
		dividePanel.add(sec2);
		
		JLabel divideLabel = new JLabel("등분:");
		divideLabel.setFont(new Font("Gulim", Font.PLAIN, 12));
		divideLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		divideLabel.setBounds(414, 26, 57, 15);
		dividePanel.add(divideLabel);
		
		nf3 = new JTextField();
		nf3.setHorizontalAlignment(SwingConstants.RIGHT);
		nf3.setBounds(483, 21, 61, 26);
		nf3.setFont(new Font("Gulim", Font.PLAIN, 12));
		nf3.setText("0");
		nf3.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
		dividePanel.add(nf3);
		nf3.setColumns(5);
		
		//-----------------------------------------------------------------------------------------
		
		JPanel equivalentPanel = new JPanel();
		equivalentPanel.setLayout(null);
		equivalentPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Equivalent Load", TitledBorder.LEADING, TitledBorder.TOP, (new Font("Gulim", Font.PLAIN, 12)), new Color(0, 0, 0)));
		equivalentPanel.setBounds(12, 210, 590, 60);
		contentPane.add(equivalentPanel);
		
		l6 = new JLabel("등분:");
		l6.setHorizontalAlignment(SwingConstants.RIGHT);
		l6.setFont(new Font("굴림", Font.PLAIN, 12));
		l6.setBounds(5, 26, 75, 15);
		equivalentPanel.add(l6);
		
		nf4 = new JTextField();
		nf4.setText("0");
		nf4.setHorizontalAlignment(SwingConstants.RIGHT);
		nf4.setFont(new Font("굴림", Font.PLAIN, 12));
		nf4.setColumns(5);
		nf4.setBounds(90, 21, 61, 26);
		equivalentPanel.add(nf4);
		
		l7 = new JLabel("λ:");
		l7.setHorizontalAlignment(SwingConstants.RIGHT);
		l7.setFont(new Font("굴림", Font.PLAIN, 14));
		l7.setBounds(220, 26, 41, 15);
		equivalentPanel.add(l7);
		
		nf5 = new JTextField();
		nf5.setText("0");
		nf5.setHorizontalAlignment(SwingConstants.RIGHT);
		nf5.setFont(new Font("굴림", Font.PLAIN, 12));
		nf5.setColumns(5);
		nf5.setBounds(273, 21, 61, 26);
		equivalentPanel.add(nf5);
		
		//-----------------------------------------------------------------------------------------
		
		JPanel p5 = new JPanel();
		p5.setBounds(12, 280, 590, 33);
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
			
			ArrayList<String> files = new ArrayList<String>();
			boolean fin = true;
			
			// file divide
			
			int fileDivide;
			double from, to;
			
			try {
				from = Double.valueOf(nf1.getText());
				to = Double.valueOf(nf2.getText());;
				fileDivide = Integer.valueOf(nf3.getText());
				
				nf1.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				nf2.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				nf3.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				tf1.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				tf2.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				
				FileRead fileRead = new FileRead(src);
				
				Divider divider = new Divider(fileRead.getResult(), from, to, fileDivide, dir);
				files = divider.getSavedDir();
				
				if(divider.isFinish()) {
					fin = true;
				} else {
					fin = false;
				}
			} catch(NumberFormatException e1) {
				nf1.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				nf2.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				nf3.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				e1.printStackTrace();
			} catch(Exception e1) {
				tf1.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				tf2.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				e1.printStackTrace();
			}
			
			// equivalent load
			
			int equivaledtDivide;
			double lambda;
			
			try {
				equivaledtDivide = Integer.valueOf(nf4.getText());
				lambda = Double.valueOf(nf5.getText());
				if(equivaledtDivide < 2) {
					nf4.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
					throw new NumberFormatException();
				}
				if(lambda == 0) {
					nf5.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
					throw new ArithmeticException();
				}
				
				nf4.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				nf5.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				tf1.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				tf2.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				
				for(int i = 0; i < files.size(); i++) {
					FileRead fileRead = new FileRead(files.get(i));
					
					Preprocess p = new Preprocess(fileRead.getResult());
					
					LoadCalculate lF = new LoadCalculate(p.getTitle(), p.getNumber(), equivaledtDivide, lambda, "F");
					LoadCalculate lR = new LoadCalculate(p.getTitle(), p.getNumber(), equivaledtDivide, lambda, "R");
					
					FileWrite_EquivalentLoad wF = new FileWrite_EquivalentLoad(lF.getResult(), equivaledtDivide, lambda, dir + "\\EquivalentLoad_result_" + (i + 1) + "_F.txt");
					FileWrite_EquivalentLoad wR = new FileWrite_EquivalentLoad(lR.getResult(), equivaledtDivide, lambda, dir + "\\EquivalentLoad_result_" + (i + 1) + "_R.txt");
					
					if(!(wF.isFinish() && wR.isFinish())) {
						fin = false;
					}
				}
				
				if(fin) {
					Dialog d = new Dialog(this, "파일이 저장되었습니다.");
				} else {
					Dialog d = new Dialog(this, "Error: fileWrite error");
				}
				
				
			} catch(NumberFormatException e1) {
				nf4.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				e1.printStackTrace();
			} catch(Exception e1) {
				tf1.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				tf2.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				e1.printStackTrace();
			}
		}
		
	}

}
