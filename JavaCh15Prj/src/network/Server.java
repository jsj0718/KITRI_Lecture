package network;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(7777);
			while (true) {
				// Ŭ���̾�Ʈ ������ ���� �������� ���� (�湮 ������ֱ�)
				System.out.println("���� �����...");
				
				// Ŭ���̾�Ʈ ���� ������ ���
				socket = serverSocket.accept();
				
				InputStream is = socket.getInputStream();
				DataInputStream dis = new DataInputStream(is);
				String id = dis.readUTF();
				
				System.out.println(id + "���� �����Ͽ����ϴ�.");				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
