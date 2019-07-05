package calccycle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Frame extends JFrame implements ActionListener,ItemListener {
	
	String src;
	String dir;
	
	int frameWidth = 440;
	int frameHeight = 500;
	
	JTextField dataFileTextField;
	JTextField saveTextField;
	JTextField diameterTextField;
	JTextField functionTextField;
	JComboBox functionComboBox;
	JButton dataFileButton;
	JButton saveButton;
	JButton runButton;
	JTextArea textArea;
	
	final Font font = new Font("굴림", Font.PLAIN, 12);
	Border defaultTextFieldBorder;
	
	public Frame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			System.err.println("Look and feel not set.");
		}
		defaultTextFieldBorder = new JTextField().getBorder();
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		
		this.setTitle("상대가혹도");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds((screenSize.width / 2) - (frameWidth / 2), (screenSize.height / 2) - (frameHeight / 2), frameWidth, frameHeight);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//-----------------------------------------------------------------------------------------
		
		JPanel filePanel = new JPanel();
		filePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "File select & Save", TitledBorder.LEADING, TitledBorder.TOP, font, new Color(0, 0, 0)));
		filePanel.setBounds(12, 20, 410, 110);
		contentPane.add(filePanel);
		filePanel.setLayout(null);
		
		JPanel dataFilePanel = new JPanel();
		dataFilePanel.setBounds(10, 20, 390, 38);
		filePanel.add(dataFilePanel);
		dataFilePanel.setLayout(null);
		
		JLabel dataFileLabel = new JLabel("데이터 파일:");
		dataFileLabel.setFont(font);
		dataFileLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dataFileLabel.setBounds(5, 10, 75, 15);
		dataFilePanel.add(dataFileLabel);
		
		dataFileTextField = new JTextField();
		dataFileTextField.setFont(font);
		dataFileTextField.setBounds(90, 6, 253, 26);
		dataFilePanel.add(dataFileTextField);
		dataFileTextField.setColumns(30);
		
		dataFileButton = new JButton("...");
		dataFileButton.setFont(font);
		dataFileButton.setBounds(355, 7, 23, 23);
		dataFileButton.addActionListener(this);
		dataFilePanel.add(dataFileButton);
		
		//-----------------------------------------------------------------------------------------
		
		JPanel savePanel = new JPanel();
		savePanel.setBounds(10, 60, 390, 38);
		filePanel.add(savePanel);
		savePanel.setLayout(null);
		
		JLabel saveLabel = new JLabel("저장 위치:");
		saveLabel.setFont(font);
		saveLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		saveLabel.setBounds(5, 10, 75, 15);
		savePanel.add(saveLabel);
		
		saveTextField = new JTextField();
		saveTextField.setFont(font);
		saveTextField.setBounds(90, 6, 253, 26);
		savePanel.add(saveTextField);
		saveTextField.setColumns(30);
		
		saveButton = new JButton("...");
		saveButton.setFont(font);
		saveButton.setBounds(355, 7, 23, 23);
		saveButton.addActionListener(this);
		savePanel.add(saveButton);
		
		//-----------------------------------------------------------------------------------------

		JPanel inputPanel = new JPanel();
		inputPanel.setBounds(12, 140, 410, 110);
		inputPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Input", TitledBorder.LEADING, TitledBorder.TOP, (new Font("Gulim", Font.PLAIN, 12)), new Color(0, 0, 0)));
		contentPane.add(inputPanel);
		inputPanel.setLayout(null);
		
		JLabel diameterLabel = new JLabel("직경:");
		diameterLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		diameterLabel.setFont(font);
		diameterLabel.setBounds(15, 30, 75, 15);
		inputPanel.add(diameterLabel);
		
		diameterTextField = new JTextField();
		diameterTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		diameterTextField.setBounds(102, 25, 61, 26);
		diameterTextField.setFont(font);
		diameterTextField.setText("0");
		inputPanel.add(diameterTextField);
		diameterTextField.setColumns(5);
		
		JLabel mmLabel = new JLabel("(mm)");
		mmLabel.setHorizontalAlignment(SwingConstants.LEFT);
		mmLabel.setFont(font);
		mmLabel.setBounds(175, 30, 40, 15);
		inputPanel.add(mmLabel);
		
		JLabel functionLabel = new JLabel("함수:");
		functionLabel.setFont(new Font("굴림", Font.PLAIN, 12));
		functionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		functionLabel.setBounds(15, 67, 75, 15);
		inputPanel.add(functionLabel);
		
		String items[] = {"=10^(6-6.097*LOG({C2}/223))", "직접입력"};
		
		functionComboBox = new JComboBox(items);
		functionComboBox.setBounds(102, 61, 285, 26);
		functionComboBox.addItemListener(this);
		inputPanel.add(functionComboBox);
		
		//-----------------------------------------------------------------------------------------
		
		JPanel runPanel = new JPanel();
		runPanel.setBounds(12, 260, 410, 33);
		contentPane.add(runPanel);
		
		runButton = new JButton("실행");
		runButton.setFont(font);
		runButton.addActionListener(this);
		runPanel.add(runButton);
		
		//-----------------------------------------------------------------------------------------
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 303, 410, 158);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea("...");
		textArea.setEditable(false);
		textArea.setFont(new Font("돋움", Font.PLAIN, 12));
		scrollPane.setViewportView(textArea);
		
		//-----------------------------------------------------------------------------------------

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == runButton) {
			diameterTextField.setBorder(defaultTextFieldBorder);
			dataFileTextField.setBorder(defaultTextFieldBorder);
			saveTextField.setBorder(defaultTextFieldBorder);
			
			double diameter;
			String formula = functionComboBox.getSelectedItem().toString();
			
			if(dataFileTextField.getText().isEmpty() || saveTextField.getText().isEmpty()) {
				dataFileTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				saveTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				println("Error: Data file or save direction is empty\n...");
				return;
			}

			println("...");
			try {
				diameter = Double.valueOf(diameterTextField.getText());
				if(diameter <= 0.0)
					throw new NumberFormatException();
			} catch(NumberFormatException err) {
				println("Error: Invalid diameter\n...");
				diameterTextField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				err.printStackTrace();
				return;
			}
			
			try {
				println("...");
				FileRead fileRead = new FileRead(src);
				
				println("...");
				Calc calc = new Calc(fileRead.getData(), new Double(diameter), formula);
				
				println("...");
				FileWrite fileWrite = new FileWrite(dir, "상대가혹도_계산결과", calc.getResult());
				if(fileWrite.write()) {
					println("File saved!\n");
				} else {
					println("Error: File write error\n...");
				}
			} catch(IOException err) {
				println("Error: " + err + "\n...");
				err.printStackTrace();
				return;
			} catch(org.apache.poi.ss.formula.FormulaParseException err) {
				println("Error: Formula error\n...");
				err.printStackTrace();
				return;
			}
		} else if(e.getSource() == dataFileButton) {
			JFileChooser jfc = new JFileChooser();
			
			jfc.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("*.xlsx", "xlsx");
			jfc.addChoosableFileFilter(filter);
			
			int returnValue = jfc.showOpenDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jfc.getSelectedFile();
				dataFileTextField.setText(selectedFile.getAbsolutePath());
				src = selectedFile.getAbsolutePath();
				println("Selected file: " + src);
				dataFileTextField.setBorder(defaultTextFieldBorder);
			}
		} else if(e.getSource() == saveButton) {
			JFileChooser jfc = new JFileChooser();
			
			jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			
			int returnValue = jfc.showSaveDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				if (jfc.getSelectedFile().isDirectory()) {
					saveTextField.setText(jfc.getSelectedFile().toString());
					dir = jfc.getSelectedFile().toString();
					println("Save direction: " + dir);
					saveTextField.setBorder(defaultTextFieldBorder);
				}
			}
		}
	}
	
	private void println(String str) {
		textArea.setText(textArea.getText() + "\n" + str);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == functionComboBox) {
			String select = functionComboBox.getSelectedItem().toString();
			if(select.equals("직접입력")) {
				functionComboBox.setEditable(true);
			} else {
				functionComboBox.setEditable(false);
			}
		} 
	}

}
