package client.frame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.program.ClientHandler;
import customer.CustomerVO;
import message.CustomerMessage;

public class JoinFrame extends JFrame implements ActionListener {
	JPanel joinPanel;
	
	// ȸ������ ȭ�鿡 �ʿ��� ���� ����
	JLabel joinL, idL, passwordL, passwordCheckL, nameL, addressL, phoneL;	
	JTextField idField, passwordField, passwordCheckField, nameField, addressField, phoneField; 	
	JButton idCheckBtn, joinBtn, cancelBtn;

	
	public JoinFrame() {
		this.setTitle("ȸ������");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setBounds(400, 200, 350, 500);
		this.setLayout(null);	// Absolute Layout
		setComponent();
		this.setVisible(true);
	}
	
	public void setComponent() {
		joinPanel = new JPanel();
		joinPanel.setLayout(null);
		
		// ��
		joinL = new JLabel();
		idL = new JLabel();
		passwordL = new JLabel();
		passwordCheckL = new JLabel();
		nameL= new JLabel();
		addressL = new JLabel();
		phoneL = new JLabel();
		
		// �ؽ�Ʈ �ʵ�
		idField = new JTextField();
		passwordField = new JTextField();
		passwordCheckField = new JTextField();
		nameField = new JTextField();
		addressField = new JTextField();
		phoneField = new JTextField();
		
		// ��ư
		idCheckBtn = new JButton();
		cancelBtn = new JButton();
		joinBtn = new JButton();
		
		// �ؽ�Ʈ �ʵ�, ��ư �� �ֱ�
		joinL.setText("ȸ������");
		joinL.setFont(new Font("���� ���", Font.BOLD, 30));
		joinL.setHorizontalAlignment(JLabel.CENTER);
		idL.setText("���̵� : ");
		passwordL.setText("��й�ȣ : ");
		passwordCheckL.setText("��й�ȣ Ȯ�� : ");
		nameL.setText("�̸� : ");
		addressL.setText("�ּ� : ");
		phoneL.setText("��ȣ : ");
		
		idCheckBtn.setText("�ߺ� Ȯ��");
		joinBtn.setText("ȸ������");
		cancelBtn.setText("���");
		
		joinL.setHorizontalAlignment(JLabel.CENTER);
		
		// ��, �ؽ�Ʈ �ʵ�, ��ư ũ�� ����
		joinL.setBounds(56, 22, 227, 44);
		idL.setBounds(12, 84, 85, 32);
		passwordL.setBounds(12, 126, 85, 32);
		passwordCheckL.setBounds(12, 170, 85, 32);
		nameL.setBounds(12, 213, 85, 32);
		addressL.setBounds(12, 255, 85, 32);
		phoneL.setBounds(12, 297, 85, 32);
		
		idField.setBounds(98, 90, 113, 21);
		passwordField.setBounds(98, 130, 180, 21);
		passwordCheckField.setBounds(98, 174, 180, 21);
		nameField.setBounds(98, 217, 180, 21);
		addressField.setBounds(98, 259, 180, 21);
		phoneField.setBounds(98, 301, 180, 21);
		
		idCheckBtn.setBounds(230, 90, 100, 19);
		joinBtn.setBounds(70, 400, 90, 30);
		cancelBtn.setBounds(180, 400, 90, 30);
		
		// �гο� �ֱ�
		joinPanel.add(joinL);
		joinPanel.add(idL);
		joinPanel.add(passwordL);
		joinPanel.add(passwordCheckL);
		joinPanel.add(nameL);
		joinPanel.add(addressL);
		joinPanel.add(phoneL);
		joinPanel.add(idField);
		joinPanel.add(passwordField);
		joinPanel.add(passwordCheckField);
		joinPanel.add(nameField);
		joinPanel.add(addressField);
		joinPanel.add(phoneField);
		joinPanel.add(idCheckBtn);
		joinPanel.add(joinBtn);
		joinPanel.add(cancelBtn);
		
		// �г� ��ġ �� ũ�� ����
		joinPanel.setBounds(0, 0, 300, 230);
				
		// �г��� �����ӿ� �ø���
		this.setContentPane(joinPanel);
		eventList();
	}
	
	public void eventList() {
		cancelBtn.addActionListener(this);
		idCheckBtn.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (cancelBtn == e.getSource()) {
			// ȸ������ â�� ����
			dispose();
		} 
		
		if (idCheckBtn == e.getSource()) {
			// ID �Է� -> ������ ID�� �������ϴ� ��Ȳ
			String id = idField.getText();
			
			if (id == null || id.equals("")) {
				JOptionPane.showConfirmDialog(null, "���̵� �Է����ּ���.", 
						"���", JOptionPane.DEFAULT_OPTION);
				idField.requestFocus();
				return;
			}
			CustomerMessage cmsg = new CustomerMessage();
			CustomerVO cvo = new CustomerVO();
			cvo.setCustID(id);
			cmsg.setCvo(cvo);
			cmsg.setState(1);	// 1 : ID Check (in CUSTOMER)
						
			// write
			ObjectOutputStream oos = ClientHandler.oos;
			try {
				oos.writeObject(cmsg);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
	}
	
	public void idCheckOk(int result) {
		// result == 1
		// ��� ������ ���̵��Դϴ�.
		if (result == 0) {
			JOptionPane.showConfirmDialog(null, "��� ������ ���̵��Դϴ�.", 
					"Ȯ��", JOptionPane.DEFAULT_OPTION);
			return;
		}
		// result == ?
		// �̹� �����ϴ� ���̵��Դϴ�.
		// idField�� �ִ� ���� ����� ��Ŀ���Ѵ�.
		else {
			JOptionPane.showConfirmDialog(null, "�̹� �����ϴ� ���̵��Դϴ�.", 
					"���", JOptionPane.DEFAULT_OPTION);
			idField.setText("");
			idField.requestFocus();
			return;
		}
	}

	public static void main(String[] args) {
		new JoinFrame();
	}

	
	
}
