
package Server;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class PalIdenServer {

    private ServerSocket server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    final int PORT = 5000;
    String HOST = "127.0.0.1";
    InetSocketAddress add = new InetSocketAddress(HOST,PORT);

    public void startServer() throws IOException {
        server = new ServerSocket();
        server.bind(add);

        System.out.println("###servidor iniciado###");
        while(true){ // Se inicia el ciclo eterno en el que el servidor esperará por un cliente
            // para luego devolver una respuesta

            socket = server.accept();
            System.out.println("-----Conexión exitosa-----");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            String message = in.readUTF();
            System.out.println("Palabra a identificar: "+message);

            if(isPalindrome(message)) {
                out.writeUTF("- (Servidor): "+message + " es un palíndromo");
                System.out.println("- (Servidor): "+message + " es un palíndromo");
            }else{
                out.writeUTF("- (Servidor): "+message + " no es un palíndromo");
                System.out.println("- (Servidor): "+message + " no es un palíndromo");
            }
            System.out.println(">>>>>Respuesta enviada<<<<<");
            socket.close();

            System.out.println("-----Desconexión exitosa-----");

        }

    }
    public boolean isPalindrome(String text) {
        String clean = text.replaceAll("\\s+", "").toLowerCase(); // Se cambian todas los caracteres
        // a minúscula
        int length = clean.length();
        int forward = 0;
        int backward = length - 1;
        while (backward > forward) { // Se invierten los caracteres y se comprueba si la palabra sigue siendo igual
            char forwardChar = clean.charAt(forward++);
            char backwardChar = clean.charAt(backward--);
            if (forwardChar != backwardChar) // Si no es igual, entonces no es un palíndromo y retorna falso
                return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        PalIdenServer sv1 = new PalIdenServer();
        sv1.startServer();
    }
}
