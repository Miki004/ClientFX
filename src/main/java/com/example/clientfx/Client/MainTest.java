package com.example.clientfx.Client;

import com.example.clientfx.Controllers.Scena1Controller;
import com.example.clientfx.Controllers.Scena2Controller;
import com.example.clientfx.Controllers.Scena3Controller;
import com.example.clientfx.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class MainTest {
    /**
     * @param args
     */
    private Scena1Controller c1;
    private Scena2Controller c2;
    private Scena3Controller c3;
    private ObjectOutputStream out;
    private ObjectInputStream in ; // stream con richieste del client


    public  MainTest(String ip, int port) throws IOException{
        InetAddress addr = InetAddress.getByName(ip); //ip
        System.out.println("addr = " + addr);
        Socket socket = new Socket(addr, port); //Port
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

    }

    public void loadDataOnServer(String nameTable) throws IOException, ClassNotFoundException {
        out.writeObject(0);//spediamo al server 0
        out.writeObject(nameTable);
    }
    public List<String> request() throws IOException, ClassNotFoundException {
        out.writeObject(3);
        return (List<String>) in.readObject();

    }

    public void loadDedrogramFromFileOnServer() throws IOException, ClassNotFoundException {
        out.writeObject(2);
      //  out.writeObject(nameFile); //spediamo il nome sul quale cercare il file
        String risposta= (String) (in.readObject()); //Se il sever risponde Ok
        if(risposta.equals("OK")) {
            // output=in.readObject().toString();// stampo il dendrogramma che il server mi sta inviando
        } else{
            System.out.println(risposta); // stampo il messaggio di errore
        }

    }

    public void mineDedrogramOnServer() throws IOException, ClassNotFoundException {
        out.writeObject(1);
        //out.writeObject();
       // out.writeObject();
        String risposta = (String) (in.readObject());
        if(risposta.equals("OK")) {
            //= (String) in.readObject();
            System.out.println();
            //out.writeObject();
        }
        else
            System.out.println(risposta); // stampo il messaggio di errore
    }


    public void setController1(Scena1Controller controller) {
        this.c1=controller;
    }

    public Scena1Controller getController1() {
        return this.c1;
    }
}


