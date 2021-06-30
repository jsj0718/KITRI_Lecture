package customer;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		CustomerFunction cf = new CustomerFunction();
		ArrayList<CustomerVO> clist = new ArrayList<>();
		
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("------------------------------");
			System.out.println("�� ���� ���� �ý���");
			System.out.println("1. ��� �� ����");
			System.out.println("2. �� ���� �� ����(��ȣ)");
			System.out.println("3. �� ���� �߰�");
			System.out.println("4. �� ���� ����(��ȣ)");
			System.out.println("5. �� ���� ����(��ȣ)");
			System.out.println("6. ����");
			System.out.println("------------------------------");
			
			System.out.print("��ȣ �Է�: ");
			int check = sc.nextInt();
			sc.nextLine();
			
			if (check == 1) {
				clist = cf.selectCustomer();
				print(clist);
			} else if (check == 2) {
				System.out.print("�� ��ȣ �Է�: ");
				int custID = sc.nextInt();
				print(cf.selectCustomer(custID));
			} else if (check == 3) {
				System.out.print("�� ��ȣ �Է�: ");
				int custID = sc.nextInt();
				sc.nextLine();
				
				System.out.print("�� �̸� �Է�: ");
				String name = sc.nextLine();
				System.out.print("�� �ּ� �Է�: ");
				String address = sc.nextLine();
				System.out.print("�� ��ȭ��ȣ �Է�: ");
				String phone = sc.nextLine();
				
				if (cf.insertCustomer(custID, name, address, phone) == 1) {
					System.out.println("�� ���� �Է� �Ϸ�");
				} else {
					System.out.println("�� ���� �Է� ����");
				}
				
			} else if (check == 4) {
				System.out.print("�� ��ȣ �Է�: ");
				int custID = sc.nextInt();
				sc.nextLine();
				System.out.print("������ �� �̸� �Է�: ");
				String name = sc.nextLine();
				System.out.print("������ �� �ּ� �Է�: ");
				String address = sc.nextLine();
				System.out.print("������ �� ��ȭ��ȣ �Է�: ");
				String phone = sc.nextLine();
				
				if (cf.updateCustomer(custID, name, address, phone) == 1) {
					System.out.println("�� ���� ���� �Ϸ�");
				} else {
					System.out.println("�� ���� ���� ����");
				}
			} else if (check == 5) {
				System.out.print("�� ��ȣ �Է�: ");
				int custID = sc.nextInt();
				
				if (cf.deleteCustomer(custID) == 1) {
					System.out.println("�� ���� ���� �Ϸ�");
				} else {
					System.out.println("�� ���� ���� ����");
				}
			} else if (check == 6) {
				break;
			}
			
		}
		sc.close();
	}
	
	public static void print(ArrayList<CustomerVO> clist) {
		for (CustomerVO cvo : clist) {
			System.out.println(cvo.toString());
		}
	}
	
	public static void print(CustomerVO cvo) {
		System.out.println(cvo.toString());
	}
}
