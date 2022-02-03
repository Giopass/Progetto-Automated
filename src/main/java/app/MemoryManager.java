package app;

import java.io.*;
import java.util.ArrayList;

public class MemoryManager {
    private static final MemoryManager INSTANCE = new MemoryManager();
    private static final String SAVEDPAZIENTI = "Pazienti.ser";

    private MemoryManager(){
    }

    public static MemoryManager getInstance() {
        return INSTANCE;
    }

    public void salva(ArrayList lista) throws IOException{
        FileOutputStream file = new FileOutputStream(SAVEDPAZIENTI);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(lista);
        out.close();
        file.close();
    }

    public ArrayList<Paziente> loadData() throws IOException, ClassNotFoundException {
        ArrayList<Paziente> serie = new ArrayList<>();
        File filePaziente = new File(SAVEDPAZIENTI);
        if(filePaziente.exists()) {
            FileInputStream file = new FileInputStream(SAVEDPAZIENTI);
            ObjectInputStream in = new ObjectInputStream(file);
            serie = (ArrayList<Paziente>) in.readObject();
        }
        return serie;
    }
}

