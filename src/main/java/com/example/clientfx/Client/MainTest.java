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
import java.util.Objects;

public class MainTest {
    /**
     * @param args
     */
    private Scena1Controller c1;
    private Scena2Controller c2;
    private int depth;
    private int option;
    private String output;
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
        String risposta;
        out.writeObject(0);
        out.writeObject(nameTable);
        risposta= (String) in.readObject();
        if(Objects.equals(risposta, "OK")) {
            output=risposta;
        }else{
            throw new ClassNotFoundException();
        }
    }

    public void setDatabase(String Server,String database, Integer Port, String User,String pw) throws IOException{
        out.writeObject(Server);
        out.writeObject(database);
        out.writeObject(Port);
        out.writeObject(User);
        out.writeObject(pw);

    }

    public List<String> request() throws IOException, ClassNotFoundException {
        return (List<String>) in.readObject();

    }

    public void loadDedrogramFromFileOnServer(String nameFile) throws IOException, ClassNotFoundException {
        out.writeObject(2);
        out.writeObject(nameFile);
        String risposta= (String) (in.readObject());
        if(risposta.equals("OK")) {
            output=in.readObject().toString();
        } else{
           output=risposta;
        }

    }

    public void mineDedrogramOnServer() throws IOException, ClassNotFoundException {
        out.writeObject(1);
        out.writeObject(depth);
        out.writeObject(option);
        String risposta = (String) in.readObject();
        if(risposta.equals("OK")) {
            output=(String) in.readObject();
        }else{
            output=risposta; // stampo il messaggio di errore
        }
    }

    public void setDepth(int text) {
        this.depth=text;
    }

    public void setOption(String value) {
        if(value.equals("Single-Link-Distance")) {
            option=1;
        } else if (value.equals("Average-Link-Distance")) {
            option=2;
        } else if (value.equals("Save")) {
            option=3;
        }
    }

    public String getOutput() {
        return output;
    }

    public void save() throws IOException {
        out.writeObject(3);
        out.writeObject(c2.getNameFile());

    }

    public void setControllerScena2(Scena2Controller controller) {
        this.c2=controller;
    }

    public boolean getAnswer() throws IOException, ClassNotFoundException {
        if(in.readObject()=="OK") {
            return false;
        }else {
            return true;
        }
    }
}


