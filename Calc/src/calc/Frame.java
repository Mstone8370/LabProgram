package calc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
	private JLabel l1;
	private JLabel l2;
	private JLabel mainP_l;
	private JLabel mainP_E_l;
	private JLabel rearD_l;
	private JLabel load_l;
	private JTextField src_tf;
	private JTextField dir_tf;
	private JTextField mainP_tf;
	private JTextField mainP_E_tf;
	private JTextField subP_tf;
	private JTextField subP_E_tf;
	private JTextField frontD_tf;
	private JTextField rearD_tf;
	private JTextField load_tf;
	private JButton b1;
	private JButton b2;
	private JButton run;
	
	private String src;
	private String dir;
	
	private double[] inputArr;
	
	public Frame() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			System.err.println("Look and feel not set.");
		}
		
		int X = 440;
		int Y = 490;
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		
		setTitle("Calc");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((screenSize.width / 2) - (X / 2), (screenSize.height / 2) - (Y / 2), X, Y);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//-----------------------------------------------------------------------------------------
		
		JPanel filePanel = new JPanel();
		filePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "File select & Save", TitledBorder.LEADING, TitledBorder.TOP, (new Font("굴림", Font.PLAIN, 12)), new Color(0, 0, 0)));
		filePanel.setBounds(12, 20, 410, 110);
		contentPane.add(filePanel);
		filePanel.setLayout(null);
		
		JPanel p1 = new JPanel();
		p1.setBounds(10, 20, 390, 33);
		filePanel.add(p1);
		p1.setLayout(null);
		
		l1 = new JLabel("데이터 파일:");
		l1.setFont(new Font("굴림", Font.PLAIN, 12));
		l1.setHorizontalAlignment(SwingConstants.RIGHT);
		l1.setBounds(5, 10, 75, 15);
		p1.add(l1);
		
		src_tf = new JTextField();
		src_tf.setFont(new Font("굴림", Font.PLAIN, 12));
		src_tf.setBounds(90, 6, 253, 26);
		src_tf.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
		p1.add(src_tf);
		src_tf.setColumns(30);
		
		b1 = new JButton("...");
		b1.setFont(new Font("굴림", Font.PLAIN, 12));
		b1.setBackground(new Color(220, 220, 220));
		b1.setBounds(355, 7, 23, 23);
		b1.addActionListener(this);
		p1.add(b1);
		
		//-----------------------------------------------------------------------------------------
		
		JPanel p2 = new JPanel();
		p2.setBounds(10, 60, 390, 33);
		filePanel.add(p2);
		p2.setLayout(null);
		
		l2 = new JLabel("저장 위치:");
		l2.setFont(new Font("굴림", Font.PLAIN, 12));
		l2.setHorizontalAlignment(SwingConstants.RIGHT);
		l2.setBounds(5, 10, 75, 15);
		p2.add(l2);
		
		dir_tf = new JTextField();
		dir_tf.setFont(new Font("굴림", Font.PLAIN, 12));
		dir_tf.setBounds(90, 6, 253, 26);
		dir_tf.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
		p2.add(dir_tf);
		dir_tf.setColumns(30);
		
		b2 = new JButton("...");
		b2.setFont(new Font("굴림", Font.PLAIN, 12));
		b2.setBackground(new Color(220, 220, 220));
		b2.setBounds(355, 7, 23, 23);
		b2.addActionListener(this);
		p2.add(b2);
		
		//-----------------------------------------------------------------------------------------
		
		JPanel inputPanel = new JPanel();
		inputPanel.setBounds(12, 140, 410, 260);
		inputPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Input", TitledBorder.LEADING, TitledBorder.TOP, (new Font("Gulim", Font.PLAIN, 12)), new Color(0, 0, 0)));
		contentPane.add(inputPanel);
		inputPanel.setLayout(null);
		
		mainP_l = new JLabel("Main 배제용적:");
		mainP_l.setHorizontalAlignment(SwingConstants.RIGHT);
		mainP_l.setFont(new Font("굴림", Font.PLAIN, 12));
		mainP_l.setBounds(10, 35, 95, 15);
		inputPanel.add(mainP_l);
		
		mainP_tf = new JTextField();
		mainP_tf.setHorizontalAlignment(SwingConstants.RIGHT);
		mainP_tf.setBounds(117, 30, 61, 26);
		mainP_tf.setFont(new Font("굴림", Font.PLAIN, 12));
		mainP_tf.setText("0");
		mainP_tf.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
		inputPanel.add(mainP_tf);
		mainP_tf.setColumns(5);
		
		JLabel subP_l = new JLabel("Sub 배제용적:");
		subP_l.setHorizontalAlignment(SwingConstants.RIGHT);
		subP_l.setFont(new Font("굴림", Font.PLAIN, 12));
		subP_l.setBounds(201, 35, 95, 15);
		inputPanel.add(subP_l);
		
		subP_tf = new JTextField();
		subP_tf.setText("0");
		subP_tf.setHorizontalAlignment(SwingConstants.RIGHT);
		subP_tf.setBounds(308, 30, 61, 26);
		subP_tf.setFont(new Font("굴림", Font.PLAIN, 12));
		subP_tf.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
		inputPanel.add(subP_tf);
		subP_tf.setColumns(5);
		
		mainP_E_l = new JLabel("Main 용적 효율:");
		mainP_E_l.setHorizontalAlignment(SwingConstants.RIGHT);
		mainP_E_l.setFont(new Font("굴림", Font.PLAIN, 12));
		mainP_E_l.setBounds(10, 95, 95, 15);
		inputPanel.add(mainP_E_l);
		
		mainP_E_tf = new JTextField();
		mainP_E_tf.setHorizontalAlignment(SwingConstants.RIGHT);
		mainP_E_tf.setBounds(117, 90, 61, 26);
		mainP_E_tf.setFont(new Font("굴림", Font.PLAIN, 12));
		mainP_E_tf.setText("0");
		mainP_E_tf.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
		inputPanel.add(mainP_E_tf);
		mainP_E_tf.setColumns(5);
		
		JLabel subP_E_l = new JLabel("Sub 용적 효율:");
		subP_E_l.setHorizontalAlignment(SwingConstants.RIGHT);
		subP_E_l.setFont(new Font("굴림", Font.PLAIN, 12));
		subP_E_l.setBounds(201, 95, 95, 15);
		inputPanel.add(subP_E_l);
		
		subP_E_tf = new JTextField();
		subP_E_tf.setText("0");
		subP_E_tf.setHorizontalAlignment(SwingConstants.RIGHT);
		subP_E_tf.setBounds(308, 90, 61, 26);
		subP_E_tf.setFont(new Font("굴림", Font.PLAIN, 12));
		subP_E_tf.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
		inputPanel.add(subP_E_tf);
		subP_E_tf.setColumns(5);
		
		JLabel frontD_l = new JLabel("전륜 지름:");
		frontD_l.setFont(new Font("굴림", Font.PLAIN, 12));
		frontD_l.setHorizontalAlignment(SwingConstants.RIGHT);
		frontD_l.setBounds(10, 155, 95, 15);
		inputPanel.add(frontD_l);
		
		frontD_tf = new JTextField();
		frontD_tf.setHorizontalAlignment(SwingConstants.RIGHT);
		frontD_tf.setBounds(117, 150, 61, 26);
		frontD_tf.setFont(new Font("굴림", Font.PLAIN, 12));
		frontD_tf.setText("0");
		frontD_tf.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
		inputPanel.add(frontD_tf);
		frontD_tf.setColumns(5);
		
		rearD_l = new JLabel("후륜 지름:");
		rearD_l.setHorizontalAlignment(SwingConstants.RIGHT);
		rearD_l.setFont(new Font("굴림", Font.PLAIN, 12));
		rearD_l.setBounds(201, 155, 95, 15);
		inputPanel.add(rearD_l);
		
		rearD_tf = new JTextField();
		rearD_tf.setText("0");
		rearD_tf.setHorizontalAlignment(SwingConstants.RIGHT);
		rearD_tf.setFont(new Font("굴림", Font.PLAIN, 12));
		rearD_tf.setColumns(5);
		rearD_tf.setBounds(308, 150, 61, 26);
		rearD_tf.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
		inputPanel.add(rearD_tf);
		
		load_l = new JLabel("동하중:");
		load_l.setHorizontalAlignment(SwingConstants.RIGHT);
		load_l.setFont(new Font("굴림", Font.PLAIN, 12));
		load_l.setBounds(10, 215, 95, 15);
		inputPanel.add(load_l);
		
		load_tf = new JTextField();
		load_tf.setText("0");
		load_tf.setHorizontalAlignment(SwingConstants.RIGHT);
		load_tf.setFont(new Font("굴림", Font.PLAIN, 12));
		load_tf.setColumns(5);
		load_tf.setBounds(117, 210, 61, 26);
		load_tf.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
		inputPanel.add(load_tf);
		
		//-----------------------------------------------------------------------------------------
		
		JPanel runPanel = new JPanel();
		runPanel.setBounds(12, 410, 410, 33);
		contentPane.add(runPanel);
		
		run = new JButton("실행");
		run.setBackground(new Color(220, 220, 220));
		run.setFont(new Font("Gulim", Font.PLAIN, 12));
		run.addActionListener(this);
		runPanel.add(run);
		
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
				src_tf.setText(selectedFile.getAbsolutePath());
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
					dir_tf.setText(jfc.getSelectedFile().toString());
					dir = jfc.getSelectedFile().toString();
				}
			}
		} else if(e.getSource() == run) {
			//run
			boolean fin;
			double mainP, mainP_E, subP, subP_E, load, frontD, rearD;
			
			try {
				mainP = Double.valueOf(mainP_tf.getText());
				mainP_E = Double.valueOf(mainP_E_tf.getText());
				subP = Double.valueOf(subP_tf.getText());
				subP_E = Double.valueOf(subP_E_tf.getText());
				frontD = Double.valueOf(frontD_tf.getText());
				rearD = Double.valueOf(rearD_tf.getText());
				load = Double.valueOf(load_tf.getText());
				
				if(mainP <= 0 || subP <= 0 || frontD <= 0 || rearD <= 0 || load <= 0) {
					throw new NumberFormatException();
				}
				
				mainP_tf.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				mainP_E_tf.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				subP_tf.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				subP_E_tf.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				frontD_tf.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				rearD_tf.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				load_tf.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				src_tf.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				dir_tf.setBorder(BorderFactory.createLineBorder(new Color(122, 138, 153), 1));
				
				FileRead fileRead = new FileRead(src);
				Preprocess preprocess = new Preprocess(fileRead.getResult());
				Calc calc = new Calc(preprocess.getTitle(), preprocess.getNumber(), mainP, mainP_E, subP, subP_E, load, frontD, rearD);
				FileWrite fileWrite = new FileWrite(calc.getResult(), dir + "\\calc_result.txt");
				
				if(fileWrite.isFinish()) {
					fin = true;
				} else {
					fin = false;
				}
				
				if(fin) {
					Dialog d = new Dialog(this, "파일이 저장되었습니다.");
				} else {
					Dialog d = new Dialog(this, "Error: fileWrite error");
				}
				
			} catch(NumberFormatException e1) {
				mainP_tf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				mainP_E_tf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				subP_tf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				subP_E_tf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				frontD_tf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				rearD_tf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				load_tf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				e1.printStackTrace();
			} catch(ArithmeticException e1) {
				Dialog d = new Dialog(this, "Arithmetic Exception: divide by zero");
				e1.printStackTrace();
			} catch(Exception e1) {
				src_tf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				dir_tf.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				e1.printStackTrace();
			}
			
		}
		
	}

}
