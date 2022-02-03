package interfaccia;

import app.*;
import app.eccezioni.WrongCodiceFiscaleException;

import java.io.IOException;
import java.util.Scanner;

public class UI {
    private static final UI INSTANCE = new UI();

    private UI(){
    }

    public static UI getINSTANCE() {
        return INSTANCE;
    }

    public void menuPazienti() {
        Scanner tastiera = new Scanner(System.in);
        int scelta;

        try {
            Ospedale.getINSTANCE().setPazienti(MemoryManager.getInstance().loadData());
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

        do{
            this.menu();
            scelta = tastiera.nextInt();
            switch(scelta){

                case 1:
                    Ospedale.getINSTANCE().aggiungiPaziente(this.creaPaziente());
                    break;

                case 2:
                    Ospedale.getINSTANCE().stampaPazienti(this.scegliOrdinamento());
                    break;

                case 3:
                    Ospedale.getINSTANCE().trovaPaziente(this.prendiCodiceFiscale());
                    break;

                case 0:
                    try{
                        MemoryManager.getInstance().salva(Ospedale.getINSTANCE().getPazienti());
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    break;
            }
        }while(scelta != 0);
    }

    private void menu() {
        System.out.println("1)Inserisci paziente");
        System.out.println("2)Stampa pazienti");
        System.out.println("3)Cerca paziente");
        System.out.println("0)Esci");
    }

    private Paziente creaPaziente(){
        Scanner tastiera = new Scanner(System.in);
        System.out.println("Inserisci il nome");
        String nome = tastiera.nextLine();
        System.out.println("Inserisci il cognome");
        String cognome = tastiera.nextLine();
        System.out.println("Inserisci l'eta");
        int eta = tastiera.nextInt();
        tastiera.nextLine();
        System.out.println("Inserisci il codice fiscale");
        String codiceFiscale = tastiera.nextLine();

        try {
            if (Ospedale.getINSTANCE().controllaCodice(codiceFiscale)) {
                throw new WrongCodiceFiscaleException("Codice fiscale già utilizzato!");
            }
        } catch (WrongCodiceFiscaleException e) {
            System.out.println("Inserisci un codice fiscale valido");
        }

        Paziente paziente = new Paziente(nome, cognome, eta, codiceFiscale);
        return paziente;
    }

    private int scegliOrdinamento(){
        Scanner tastiera = new Scanner(System.in);
        System.out.println("In che ordine vuoi vedere i risultati?");
        System.out.println("1)Ordina per nome");
        System.out.println("2)Ordina per cognome");
        System.out.println("3)Ordina per eta");
        int scelta = tastiera.nextInt();

        return scelta;
    }

    private String prendiCodiceFiscale(){
        Scanner tastiera = new Scanner(System.in);
        System.out.println("Inserisci il codice fiscale del paziente cercato");
        String codice = tastiera.nextLine();
        return codice;
    }
}
