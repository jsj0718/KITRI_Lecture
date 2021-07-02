package client.program;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import client.frame.LoginFrame;

public class Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 7777);
			System.out.println("���� ���� �Ϸ�");
			
			// Ŭ���̾�Ʈ ���� �� ȭ�� ����
			LoginFrame login = new LoginFrame(socket);
			
			// ������ ����ϴ� Ŭ���̾�Ʈ �ڵ鷯 ������� ����
			Thread clientHandler = new ClientHandler(socket, login);
			clientHandler.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
