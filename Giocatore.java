import java.util.Scanner;

public class Giocatore {
    private String nome;
    private int fiches;

    Scanner input_stringhe = new Scanner(System.in);
    Scanner input_numeri = new Scanner(System.in);

    //costruttore
    public Giocatore(String nome, int fiches) {
        setNome(nome);
        setFiches(fiches);
    }

    //getters
    public String getNome() {
        return nome;
    }

    public int getFiches() {
        return fiches;
    }

    //setters
    public void setNome(String nome) {
        while(true) {
            if(convalidazioneNome(nome)) {  //faccio in modo che non accetti nomi composti da solo numeri
                if(nome.length() > 3) {
                    this.nome = nome;
                    break;
                }
                else {
                    System.out.print("Benvenuto/a! Come ti chiami? (Inserisci un nome di almeno 4 caratteri) ");
                    nome = input_stringhe.nextLine();
                }
            }
            else {
                System.out.print("Non puoi inserire un valore numerico come nome, riprova: ");
                nome = input_stringhe.nextLine();
            }
        }
    }

    public void setFiches(int fiches) {
        while(true) {
            if(fiches >= 0) {
                this.fiches = fiches;
                break;
            }
            else {
                try {
                    System.out.print("Il numero deve essere positivo, riprova: ");
                    fiches = input_numeri.nextInt();
                }
                catch(Exception exc) {
                    System.out.println("Input non valido, riprova");
                    input_numeri.nextLine();
                }
            }
        }
    }

    //metodi che ritornano
    public boolean convalidazioneNome(String nome) { //convalido il nome solo se non è un valore numerico
        boolean validazione = false;
        try {
            Integer.valueOf(nome);
        }
        catch(NumberFormatException exc) {
        validazione = true;
        }
        try {
            Double.valueOf(nome);
            validazione = false;
        }
        catch(NumberFormatException exc) {
        validazione = true;
        }
        return validazione;
        
    }
}