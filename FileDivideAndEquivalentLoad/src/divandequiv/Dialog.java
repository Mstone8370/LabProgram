package divandequiv;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dialog extends JDialog implements ActionListener {
	
	private JButton b;
	
	public Dialog(Frame parent, String str) {
		super(parent, "", true);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		
		this.setLocation((screenSize.width / 2) - 100, (screenSize.height / 2) - 55);
		this.setSize(200,110);
		this.setLayout(new FlowLayout());
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		JLabel l = new JLabel();
		l.setText(str);
		l.setFont(new Font("Gulim", Font.PLAIN, 12));
		
		b = new JButton("»Æ¿Œ");
		b.addActionListener(this);
		b.setFont(new Font("Gulim", Font.PLAIN, 12));
		
		p1.add(l);
		p2.add(b);
		
		add(p1);
		add(p2);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b) {
			this.setVisible(false);
		}
		
	}

}
