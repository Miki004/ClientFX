package com.example.clientfx.Controllers;

/**
 * Questa classe rappresenta un'eccezione personalizzata che viene lanciata
 * quando si verificano problemi specifici legati alle impostazioni del database.
 * Estende {@link Exception} per fornire un meccanismo di gestione delle eccezioni.
 */
public class SettingDbException extends Exception {

    /**
     * Costruttore di default per {@code SettingDbException}.
     * Crea un'istanza di {@code SettingDbException} senza un messaggio dettagliato.
     */
    SettingDbException() {
        super();
    }

    /**
     * Costruttore per {@code SettingDbException} che accetta un messaggio dettagliato.
     *
     * @param str Il messaggio dettagliato che descrive l'eccezione.
     */
    SettingDbException(String str) {
        super(str);
    }

    /**
     * Verifica se una stringa è vuota e lancia una {@code SettingDbException} se lo è.
     *
     * @param str La stringa da verificare.
     * @throws SettingDbException se la stringa passata è vuota.
     */
    public static void verificaVuoto(String str) throws SettingDbException {
        if (str.isEmpty()) {
            throw new SettingDbException("La stringa è vuota.");
        }
    }
}
