import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class DI_3D_Server {
	public static void main(String[] args) {
		/*���� ����*/
		ServerSocket serverSocket = null;
		try {
			/* ���� ���� ���� */
			serverSocket = new ServerSocket();
			/* ���ϰ� ��Ʈ��ȣ ���ε� */
			serverSocket.bind(new InetSocketAddress("localhost", 5001));
			while (true) {
				System.out.println("[���� ��ٸ�]");
				
				/* ���� accept, ��ϻ��� */
				Socket socket = serverSocket.accept();
				
				/* ����� ip�޾ƿ� */
				InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
				System.out.println("[���� ������] " + isa.getHostName());
				
				/* byte���� */
				byte[] bytes = null;
				/* �޽������� String ���� */
				String message = null;

				/* Ŭ���̾�Ʈ�κ��� inputStream�� �޾ƿ� ���� */
				InputStream is = socket.getInputStream();
				bytes = new byte[100];
				
				/* ����Ʈ��ŭ ������ InputStream���� ������ �о�� */
				int readByteCount = is.read(bytes);
				
				/* UTF-8Ÿ������ �о�µ����͸� String���� ��ȯ�� */
				message = new String(bytes, 0, readByteCount, "UTF-8");
				
				/* �޾Ҵ� ������ ��� */
				System.out.println("[������ �ޱ� ����]: " + message);
				
				/* Ŭ���̾�Ʈ�� �����͸� ������ ���� socket�� ���� OutputStream ���� */
				OutputStream os = socket.getOutputStream();
				
				/* ������ �޽��� ���� */
				message = "Hello Client";
				
				/*�Ž����� ����Ʈ�� �ٲ� */
				bytes = message.getBytes("UTF-8");
				
				/* OutputStream���� �޽����� ���� */
				os.write(bytes);
				
				/* OutputStream�� ��� */
				os.flush();
				
				/* ������ ���� �޽��� ��� */
				System.out.println("[������ ������ ����]");

				is.close();
				os.close();
				socket.close();
			}
		} 
		catch (Exception e) {
		}

		if (!serverSocket.isClosed()) {
			try {
				serverSocket.close();
			} 
			catch (IOException e1) {
			}
		}
	}

}