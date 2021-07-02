package client.frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginFrame extends JFrame implements ActionListener {
	JPanel loginPanel;
	
	// �α��� ȭ�鿡 �ʿ��� ���� ����
	JLabel loginL, idL, passwordL;	
	JTextField idField, passwordField; 	
	JButton loginBtn, joinBtn;
	
	public JoinFrame join;
	
	Socket socket;
	
	public LoginFrame(Socket socket) {
		this.socket = socket;
		this.setTitle("�α���");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(700, 400, 300, 230);
		this.setLayout(null);	// Absolute Layout
		setComponent();
		this.setVisible(true);
		eventList();
	}
	
	public void setComponent() {
		loginPanel = new JPanel();
		loginPanel.setLayout(null);
		
		// ��
		loginL = new JLabel();
		idL = new JLabel();
		passwordL = new JLabel();
		
		// �ؽ�Ʈ �ʵ�
		idField = new JTextField();
		passwordField = new JTextField();
		
		// ��ư
		loginBtn = new JButton();
		joinBtn = new JButton();
		
		// �ؽ�Ʈ �ʵ�, ��ư �� �ֱ�
		loginL.setText("�� �� ��");
		idL.setText("ID : ");
		passwordL.setText("Password : ");
		
		loginBtn.setText("�α���");
		joinBtn.setText("ȸ������");
		
		loginL.setHorizontalAlignment(JLabel.CENTER);
		
		// ��, �ؽ�Ʈ �ʵ�, ��ư ũ�� ����
		loginL.setBounds(0, 5, 300, 15);
		idL.setBounds(20, 25, 100, 30);
		passwordL.setBounds(20, 65, 100, 30);
		
		idField.setBounds(120, 25, 150, 30);
		passwordField.setBounds(120, 65, 150, 30);
		
		loginBtn.setBounds(50, 115, 90, 30);
		joinBtn.setBounds(150, 115, 90, 30);
		
		// �гο� �ֱ�
		loginPanel.add(loginL);
		loginPanel.add(idL);
		loginPanel.add(passwordL);
		loginPanel.add(idField);
		loginPanel.add(passwordField);
		loginPanel.add(loginBtn);
		loginPanel.add(joinBtn);
		
		// �г� ��ġ �� ũ�� ����
		loginPanel.setBounds(0, 0, 300, 230);
				
		// �г��� �����ӿ� �ø���
		this.setContentPane(loginPanel);
				
	}
	
	public void eventList() {
		// ȸ������ ��ư(actionPerformed()�� ����)
		joinBtn.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (joinBtn == e.getSource()) {
			join = new JoinFrame();		// ������ ȣ��� �����Ŵ
		}
	}

//	public static void main(String[] args) {
//		new LoginFrame();
//	}
	
	
}
