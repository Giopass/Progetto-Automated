package app;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Classe che si occupa della gestione dei pazienti
 * @author Giovanni Passaro
 * @version 1.0
 */

public class Ospedale {
    private static final Ospedale INSTANCE = new Ospedale();

    private Ospedale(){
    }

    /**
     *pazienti contiene tutti i pazienti noti all'ospedale
     */
    private ArrayList<Paziente> pazienti = new ArrayList<>();


    public ArrayList<Paziente> getPazienti() {
        return pazienti;
    }

    public void setPazienti(ArrayList<Paziente> pazienti) {
        this.pazienti = pazienti;
    }

    /**
     *
     * @return true true se il codice fiscale è già stato utlizzato
     */
    public boolean controllaCodice(String codice){
        for (Paziente paziente : pazienti) {
            if(paziente.getCodiceFiscale().equals(codice))
                return true;
        }
        return false;
    }

    public static Ospedale getINSTANCE() {
        return INSTANCE;
    }

    /**
     * aggiunge il paziente inserito
     */
    public void aggiungiPaziente(Paziente paziente){
        pazienti.add(paziente);
    }

    /**
     * stampa i pazienti in ordine alfabetico di nome, cognome oppure crescente per età
     */
    public void stampaPazienti(int ordinamento){
        if(pazienti.isEmpty())
            System.out.println("Nessun paziente registrato");
        else {
            switch (ordinamento) {
                case 1:
                    this.stampaPerNome();
                    break;
                case 2:
                    this.stampaPerCognome();
                    break;
                case 3:
                    this.stampaPerEta();
                    break;
                default:
                    System.out.println("Tasto sbagliato, riprovare");
            }

            Stream<Paziente> stream = pazienti.stream();
            stream.forEach((Paziente element) -> {
                System.out.println(element.toString());
            });
        }

    }

    /**
     * ordina l'arraylist in base alla scelta dell'utente
     */
    public void stampaPerEta(){
        pazienti.sort((Paziente p1, Paziente p2) -> Integer.compare(p1.getEta(),p2.getEta()));
    }

    public void stampaPerCognome(){
        pazienti.sort((Paziente p1, Paziente p2) -> p1.getCognome().compareTo(p2.getCognome()));
    }

    public void stampaPerNome(){
        pazienti.sort((Paziente p1, Paziente p2) -> p1.getNome().compareTo(p2.getNome()));
    }

    /**
     * cerca un paziente richiesto dall'utente, se esiste viene mostrato
     * @param codice
     */
    public void trovaPaziente(String codice){
        Paziente paziente = cerca(codice);
        if(paziente != null)
            System.out.println(paziente.toString());
        else
            System.out.println("Paziente inesistente");
    }

    /**
     *
     * @param codice
     * @return paziente cercato dall'utente
     */
    public Paziente cerca(String codice){
        for (Paziente paziente : pazienti) {
            if(paziente.getCodiceFiscale().equals(codice))
                return paziente;
        }
        return null;
    }
}
