package client.network;
import client.views.menu.*;
import shared.object.*;
import shared.transferObject.*;

import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketClient implements Client {
    Scanner sc = new Scanner(System.in);
    private String host = "localhost";
    private int port = 2004;
    private PropertyChangeSupport support;
    private Customer customer;
    private MenuView menu;


    public SocketClient(){
        support = new PropertyChangeSupport(this);
        menu = new MenuView();
    }

    public void startClient() {
        try {
            Socket socket = new Socket(host, port);
            ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
            new Thread(() -> listenToServer(outToServer, inFromServer)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createAccount() {
        sc = new Scanner(System.in);
        String[] messageArr = {
                "<Invalid name>",
                "<Invalid phone number>",
                "<Invalid password>",
                "<Password does not match>"};
        try {
            System.out.println("\u001B[36m" + "#Create Account:" + "\u001B[0m");
            String name = "";
            do {
                System.out.print("Enter your name: ");
                name = sc.nextLine();
                if(!name.matches("^[a-zA-Z\\s]+$")){
                    System.out.println("\u001B[31m" + messageArr[0] + "\u001B[0m");
                }
            } while(!name.matches("^[a-zA-Z\\s]+$"));

            String tel = "";
            do {
                System.out.print("Enter your phone number: ");
                tel = sc.nextLine();
                if(!tel.matches("^[0-9]{7}$")){
                    System.out.println("\u001B[31m" + messageArr[1] + "\u001B[0m");
                }
            } while(!tel.matches("^[0-9]{7}$"));

            String pass = "";
            do {
                System.out.print("Enter your password: ");
                pass = sc.nextLine();
                if(!pass.matches("^.{8,}$")){
                    System.out.println("\u001B[31m" + messageArr[2] + "\u001B[0m");
                }
            } while (!pass.matches("^.{8,}$"));

            String repass = "";
            do {
                System.out.print("Re-enter your password: ");
                repass = sc.nextLine();
                if (!repass.equals(pass)) {
                    System.out.println("\u001B[31m" + messageArr[3] + "\u001B[0m");
                }
            } while (!repass.equals(pass));

            String data = name + "," + tel + "," + pass;
            Response response = request("CreateAccount", data);

            String message = (String)response.getArg();
            System.out.println(message);
        }
        catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Response request(String type, String arg) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(host, port);
        ObjectOutputStream outToServer = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inFromServer = new ObjectInputStream(socket.getInputStream());
        outToServer.writeObject(new Request(type, arg));
        Response response = (Response) inFromServer.readObject();
        return response;
    }

    private void listenToServer(ObjectOutputStream outToServer, ObjectInputStream inFromServer) {
        try {
            outToServer.writeObject(new Request("Listener", null));
            while (true) {
                Request request = (Request) inFromServer.readObject();
                support.firePropertyChange(request.getType(), null, request.getArg());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
