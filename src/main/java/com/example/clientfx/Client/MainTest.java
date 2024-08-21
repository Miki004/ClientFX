package com.example.clientfx.Client;

import com.example.clientfx.Controllers.Scena1Controller;
import com.example.clientfx.Controllers.Scena3Controller;
import javafx.application.Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class MainTest {
    /**
     * @param args
     */
    private Scena3Controller scena;
    private Scena1Controller mainScene;
    private String output;
    private String nameFile;
    private int dType;
    private int depth;
    private ObjectOutputStream out;
    private ObjectInputStream in ; // stream con richieste del client

    public void setNameFile(String nameFile) {
        this.nameFile=nameFile;
    }

    public void setMainScene(Scena1Controller mainScene) {
        this.mainScene = mainScene;
    }

    public String getOutput() {
    return  this.output;
    }

    public  MainTest(String ip, int port) throws IOException{
        InetAddress addr = InetAddress.getByName(ip); //ip
        System.out.println("addr = " + addr);
        Socket socket = new Socket(addr, port); //Port
        System.out.println(socket);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());	; // stream con richieste del client
    }

    private int menu(){
        int answer;
        System.out.println("Scegli una opzione");
        do{
            System.out.println("(1) Carica Dendrogramma da File");
            System.out.println("(2) Apprendi Dendrogramma da Database");
            System.out.print("Risposta:");
            answer=Keyboard.readInt();
        }
        while(answer<=0 || answer>2);
        return answer;

    }

    public void loadDataOnServer() throws IOException, ClassNotFoundException {
        boolean flag=false;
        do {
            //System.out.println("Nome tabella:");
            //String tableName = Keyboard.readString(); //inserisco il nome della tabella all'utente
            out.writeObject(0);//spediamo al server 0
            out.writeObject(nameFile); //spediamo al server il nome della tabella
            String risposta = (String) (in.readObject()); //il server deve rispondere al client con OK
            if (risposta.equals("OK")) {
                flag = true;
            } else {
                mainScene.writeOnArea(risposta);
                //System.out.println(risposta);
            }
        }while(!flag);
    }

    public void loadDedrogramFromFileOnServer() throws IOException, ClassNotFoundException {
        //System.out.println("Inserire il nome dell'archivio (comprensivo di estensione):");
        //String fileName=Keyboard.readString();
        out.writeObject(2);
        out.writeObject(nameFile); //spediamo il nome sul quale cercare il file
        String risposta= (String) (in.readObject()); //Se il sever risponde Ok
        if(risposta.equals("OK"))
            output=in.readObject().toString();// stampo il dendrogramma che il server mi sta inviando
        else
            System.out.println(risposta); // stampo il messaggio di errore
    }

    public void mineDedrogramOnServer() throws IOException, ClassNotFoundException {
        out.writeObject(1);
        //System.out.println("Introdurre la profondita' del dendrogramma:");
        //int depth=Keyboard.readInt();
        out.writeObject(depth);
        /*
        int dType=-1;
        do {
            System.out.println("Distanza: single-link (1), average-link (2):");
            dType=Keyboard.readInt();
        }while (dType<=0 || dType>2);
        */
        out.writeObject(dType);
        String risposta = (String) (in.readObject());
        if(risposta.equals("OK")) {
            output= (String) in.readObject();
            // System.out.println(in.readObject()); // stampo il dendrogramma che il server mi sta inviando
            //System.out.println("Inserire il nome dell'archivio (comprensivo di estensione):");
            //String fileName=Keyboard.readString();
            System.out.println(nameFile);
            out.writeObject(nameFile);
        }
        else
            System.out.println(risposta); // stampo il messaggio di errore
    }
    public static void main(String[] args) {
        String ip=args[0];
        int port= Integer.getInteger(args[1]);

        MainTest main;
        try{
            main=new MainTest(ip,port);
            //main.loadDataOnServer();//Spediamo al sever il nome della tabella su cui effettuare il clustering
            int scelta=main.menu();
            if(scelta==1)
                main.loadDedrogramFromFileOnServer();//al server spedirà 2
            else
                main.mineDedrogramOnServer();//al server spedirà 1
        }
        catch (IOException |ClassNotFoundException  e){
            System.out.println(e);
            return;
        }
    }

    public void setDepth(int i) {
        depth=i;
    }

    public void setType(int i) {
        dType=i;
    }

    public int getDepth() {
        return depth;
    }
    public int getdType() {
        return dType;
    }

    public void setScene(Scena3Controller scena3Controller) {
        scena=scena3Controller;
    }
}


