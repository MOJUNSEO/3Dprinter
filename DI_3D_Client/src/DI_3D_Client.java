import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class DI_3D_Client {
	public static void main(String[] args) {
		/* ���� ���� */
		Socket socket = null;
		try {
			/* ���� ���� */
			socket = new Socket();
			System.out.println("[���� ��û]");
			
			/* �Է� IP�� ����õ� "localhost�� ���� �Է��־���ϴ� ������ �ٲپ����." */
			socket.connect(new InetSocketAddress("localhost", 5001));
			System.out.println("[���� ����]");

			/* �����͹ޱ� ���� ����Ʈ ���� */
			byte[] bytes = null;
			
			/* �޽��� ���� ���� */
			String message = null;
			
			/* ������ �ޱ� ���� InputStream ���� */
			InputStream is = socket.getInputStream();
	
			/* ���� ����Ʈ �� ���� �� ���� */
			bytes = new byte[100];
			
			/* InputStream���� ����Ʈ�� ��ŭ ���� �����͸� ����*/
			/* read�� ȣ��� , Server�� acceptó�� ���ŷ�� */
			int readByteCount = is.read(bytes);
			
			/* ���� ����Ʈ�� UTF-8�� String ��ȯ */
			message = new String(bytes, 0, readByteCount, "UTF-8");
			
			/*���� ������ ���*/
			System.out.println("[������ �ޱ� ����]: " + message);
			
			/*****************************/
			/* �ڿ��⿡ ��ȯ �˰��� �Լ� ������ �� */
			/* message = function(x)�� ���������� ������ ������ ���־����*/
			/****************************/
			
			/* ������ ������ ���� OutputStream ���� */
			OutputStream os = socket.getOutputStream();
			
			/* ������ ���� ������ ����*/
			message = "Hello Server";
			bytes = message.getBytes("UTF-8");
			/* �Լ����� ���ϵ� �޽����� ����Ʈ�� ��ȯ */
			/* �� �κе� function(x)�� ������*/
			
			/*OutputStream �� ���Ͽ� �����͸� ������*/
			os.write(bytes);
			
			/* OutputStream�� ����� */
			os.flush();
			System.out.println("[������ ������ ����]");

			

			os.close();
			is.close();
		} 
		catch (Exception e) {
		}

		if (!socket.isClosed()) {
			try {
				socket.close();
			} 
			catch (IOException e1) {
			}
		}
	}

}
