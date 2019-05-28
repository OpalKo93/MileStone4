package ServerClient;

import java.net.Socket;

public interface Client {

	void connect(String ip,int port);
	void setPathValue(String path, Double value);
	Socket getSocket();
	void disconnect();
	void close();
}
