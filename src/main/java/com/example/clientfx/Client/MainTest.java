package com.example.clientfx.Client;

import com.example.clientfx.Controllers.Scena1Controller;
import com.example.clientfx.Controllers.Scena3Controller;

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
    private String nameTable;
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
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

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
            out.writeObject(0);//spediamo al server 0
            out.writeObject(getNameTable()); //spediamo al server il nome della tabella
            String risposta = (String) (in.readObject()); //il server deve rispondere al client con OK
            if (risposta.equals("OK")) {
                flag = true;
            } else {
                mainScene.writeOnArea(risposta);

            }
        }while(!flag);
    }

    public void loadDedrogramFromFileOnServer() throws IOException, ClassNotFoundException {
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
        out.writeObject(depth);
        out.writeObject(dType);
        String risposta = (String) (in.readObject());
        if(risposta.equals("OK")) {
            output= (String) in.readObject();
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

    public void setScene(Scena3Controller scena3Controller) {
        scena=scena3Controller;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public String getNameTable() {
        return nameTable;
    }
}


