import java.io.*;
import java.net.*;

public class Client {
    private static final String _IP_SERVIDOR = "192.168.100.9";
    private static final int _PUERTO = 1234;
    private static final int _TIMEOUT = 3000;

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket();
        socket.setSoTimeout(_TIMEOUT);
        socket.connect(new InetSocketAddress(InetAddress.getByName(_IP_SERVIDOR), _PUERTO),_TIMEOUT);

        System.out.println("Se ha iniciado la conexion al servidor");
        System.out.println("Cliente IP: " + socket.getLocalAddress() + " Puerto: " + socket.getLocalPort());
        System.out.println("Servidor IP: " + socket.getInetAddress() + " Puerto: " + socket.getPort());

        try {
            send(socket);
            } catch (SocketException se) {
            socket.close();
            System.exit(0);
            } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error de operación");
            }
            socket.close();
            System.out.println("El cliente termina la conexion");
            }

            private static void send(Socket client) throws IOException {

                InputStream in = System.in;
                BufferedReader input = new BufferedReader(new InputStreamReader(in));
                
                OutputStream outputStream = client.getOutputStream();
                PrintStream socketPrintStream = new PrintStream(outputStream);

                InputStream inputStream = client.getInputStream();
                BufferedReader socketBufferReader = new BufferedReader(new InputStreamReader(inputStream));

                boolean flag = true;
                while (flag) {
                    String str = input.readLine();
                    socketPrintStream.println(str);

                    String echo = socketBufferReader.readLine();
                    if (echo.equalsIgnoreCase("bye")) {
                        flag = false;
                        break;
                    } else
                       System.out.println(echo);
                    }
                    outputStream.close();
                    socketBufferReader.close();
                    inputStream.close();
                    socketBufferReader.close();
                    }
} 
