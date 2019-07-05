package calccycle;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JList;

public class FrameTest extends JFrame {
	
	final Font font = new Font("굴림", Font.PLAIN, 12);

	private JPanel contentPane;
	private JTextField dataFileTextField;
	private JTextField saveTextField;
	JTextField diameterTextField;
	JButton dataFileButton;
	JButton saveButton;
	JButton runButton;
	JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameTest frame = new FrameTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameTest() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			System.err.println("Look and feel not set.");
		}
		
		setTitle("상대가혹도");
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
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
		
		String items[] = {"=10^(6-6.097*LOG({C2}/223))", "2"};
		
		JLabel label = new JLabel("함수:");
		label.setFont(new Font("굴림", Font.PLAIN, 12));
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(15, 67, 75, 15);
		inputPanel.add(label);
		
		JComboBox comboBox = new JComboBox(items);
		comboBox.setBounds(102, 61, 285, 26);
		inputPanel.add(comboBox);
		
		//-----------------------------------------------------------------------------------------
		
		JPanel runPanel = new JPanel();
		runPanel.setBounds(12, 260, 410, 33);
		contentPane.add(runPanel);
		
		runButton = new JButton("실행");
		runButton.setFont(font);
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
	}
}
