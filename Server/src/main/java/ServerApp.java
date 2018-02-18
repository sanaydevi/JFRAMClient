import java.io.IOException;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Connection;

/**
 * Server
 *
 * <P>Server side application entry point.
 * <P>This opens a server on the current machine.
 *
 * @author Team 2
 * @version 1.0
 */
public class ServerApp {

    public static final int PORT = 3000;

    public static void main( String[] args ) throws IOException {

        Server server = new Server();
        server.start();
        server.bind( PORT );

        Network.register( server );

        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof Message) {
                    Message request = (Message)object;
                    System.out.println(request.text);

                    Message response = new Message();
                    response.text = "Message from server";
                    connection.sendTCP(response);
                }
            }
        });
        
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerInterface window = new ServerInterface();
					window.frmServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

    }
}
