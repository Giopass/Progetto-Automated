package app;

import java.io.Serializable;

public class Paziente implements Serializable {
    private final String nome;
    private final String cognome;
    private final int eta;
    private final String codiceFiscale;

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public int getEta() {
        return eta;
    }

    public Paziente(String nome, String cognome, int eta, String codiceFiscale) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.codiceFiscale = codiceFiscale;
    }

    @Override
    public String toString() {
        return "Paziente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", eta=" + eta +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                '}';
    }
}
