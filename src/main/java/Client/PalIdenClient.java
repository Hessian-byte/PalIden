package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class PalIdenClient {

    final String HOST = "127.0.0.1"; // Dirección del servidor
    DataInputStream in; // En esta variable se almacenan los paquetes recibidos desde el servidor
    DataOutputStream out; // En esta variable se almacenan los paquetes a enviar al servidor
    final int PORT = 5000; // Puerto de ingreso al servidor
    SocketAddress add = new InetSocketAddress(HOST,PORT); // Dirección y puerto del servidor
    Socket socket = new Socket(); // Se crea el socket del cliente
    public void startClient(){
        try {
            String palabra = menu(); // Despliega mensajes de inicio y pregunta por una palabra a enviar al servidor
            socket.connect(add); // Inicia la conexión con el servidor

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF(palabra); // Se envía la palabra al servidor

            String mensaje = in.readUTF(); // Se lee la respuesta del servidor

            System.out.println(mensaje); // Se despliega el mensaje del servidor en el terminal

            socket.close(); // Se termina la conexión con el servidor

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String menu(){ // Despliega mensajes de inicio y pregunta al usuario la palabra que desea comprobar
        String palabra;
        System.out.println("||||||||||||||||||||||||||||||||||");
        System.out.println("|||Identificador de palíndromos|||");
        System.out.println("Escribe la palabra para comprobar:");
        Scanner keyboard = new Scanner(System.in);
        palabra = keyboard.nextLine();
        return  palabra;

    }

    public static void main(String[] args) throws IOException {
        PalIdenClient cl1 = new PalIdenClient();
        cl1.startClient();
    }
    
}
