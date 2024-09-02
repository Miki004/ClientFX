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

/**
 * Gestisce la comunicazione tra il client e il server.
 * Gestisce le richieste al server e le risposte ricevute,
 * tra cui il caricamento dei dati, la richiesta delle tabelle,
 * l'elaborazione dei dendrogrammi e il salvataggio.
 */
public class MainTest {

    private Scena2Controller c2;
    private int depth;
    private int option;
    private String output;
    private ObjectOutputStream out;
    private ObjectInputStream in ;// stream con richieste del client
    private Socket socket;

    /**
     * Costruisce un'istanza di {@code MainTest} e stabilisce una connessione al server.
     *
     * @param ip l'indirizzo IP del server.
     * @param port la porta del server.
     * @throws IOException se si verifica un errore durante la connessione al server.
     */
    public  MainTest(String ip, int port) throws IOException{
        InetAddress addr = InetAddress.getByName(ip); //ip
        System.out.println("addr = " + addr);
         socket = new Socket(addr, port); //Port
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Carica i dati sul server per la tabella specificata.
     *
     * @param nameTable il nome della tabella da caricare.
     * @throws IOException se si verifica un errore durante l'invio o la ricezione dei dati.
     * @throws ClassNotFoundException se la risposta ricevuta dal server non è valida.
     */
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

    /**
     * Imposta i dettagli della connessione al database sul server.
     *
     * @param Server l'indirizzo del server del database.
     * @param database il nome del database.
     * @param Port la porta di connessione al database.
     * @param User il nome utente per la connessione al database.
     * @param pw la password per la connessione al database.
     * @throws IOException se si verifica un errore durante l'invio dei dettagli al server.
     */
    public void setDatabase(String Server,String database, Integer Port, String User,String pw) throws IOException{
        out.writeObject(Server);
        out.writeObject(database);
        out.writeObject(Port);
        out.writeObject(User);
        out.writeObject(pw);

    }

    /**
     * Richiede e riceve la lista delle tabelle dal server.
     *
     * @return la lista delle tabelle ricevuta dal server.
     * @throws IOException se si verifica un errore durante la comunicazione con il server.
     * @throws ClassNotFoundException se si verifica un errore nella lettura dei dati.
     */
    public List<String> request() throws IOException, ClassNotFoundException {
        List<String> risposta = null;
        try {
            risposta = (List<String>) in.readObject();
            System.out.println(risposta);
        }catch(IOException e){
            System.out.println("erroe ");
        }
        return risposta;
    }

    /**
     * Carica un dendrogramma dal file specificato sul server.
     *
     * @param nameFile il nome del file contenente il dendrogramma.
     * @throws IOException se si verifica un errore durante l'invio o la ricezione dei dati.
     * @throws ClassNotFoundException se la risposta ricevuta dal server non è valida.
     */
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

    /**
     * Esegue l'elaborazione del dendrogramma sul server.
     *
     * @throws IOException se si verifica un errore durante l'invio o la ricezione dei dati.
     * @throws ClassNotFoundException se la risposta ricevuta dal server non è valida.
     */
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

    /**
     * Imposta la profondità per il clustering.
     *
     * @param text la profondità del clustering.
     */
    public void setDepth(int text) {
        this.depth=text;
    }

    /**
     * Imposta l'opzione per l'algoritmo di clustering.
     *
     * @param value il tipo di algoritmo di clustering.
     */
    public void setOption(String value) {
        if(value.equals("Single-Link-Distance")) {
            option=1;
        } else if (value.equals("Average-Link-Distance")) {
            option=2;
        } else if (value.equals("Save")) {
            option=3;
        }
    }

    /**
     * Restituisce l'output ricevuto dal server.
     *
     * @return l'output ricevuto dal server.
     */
    public String getOutput() {
        return output;
    }

    /**
     * Salva il dendrogramma utilizzando il nome del file fornito.
     *
     * @throws IOException se si verifica un errore durante l'invio dei dati al server.
     */
    public void save() throws IOException {
        out.writeObject(3);
        out.writeObject(c2.getNameFile());

    }

    /**
     * Imposta il controller per la scena 2.
     *
     * @param controller il controller da impostare.
     */
    public void setControllerScena2(Scena2Controller controller) {
        this.c2=controller;
    }

    /**
     * Legge la risposta del server per verificare se l'operazione è andata a buon fine.
     *
     * @return {@code true} se la risposta è "OK", altrimenti {@code false}.
     * @throws IOException se si verifica un errore durante la comunicazione con il server.
     * @throws ClassNotFoundException se si verifica un errore nella lettura dei dati.
     */
    public boolean getAnswer() throws IOException, ClassNotFoundException {
        String risposta = (String) in.readObject();
        System.out.println(risposta);
        if(Objects.equals(risposta, "OK")) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * Chiude la connessione al server.
     */
    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


