import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Roulette {
    int[] numeri_rossi = new int[]{1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
    ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
    ArrayList<Integer> puntate_giocatori = new ArrayList<Integer>(List.of(0, 0, 0, 0)); // lo riempio per poterlo mantenere sempre della stessa dimensione utilizzando il set()
    ArrayList<String> giocata_scelta = new ArrayList<String>(List.of("", "", "", "")); //riempio la lista in base al numero massimo di giocatori
    ArrayList<String> giocate_vincenti = new ArrayList<String>();

    Croupier croupier = new Croupier();

    Scanner input_stringhe = new Scanner(System.in);
    Scanner input_numeri = new Scanner(System.in);

    //metodi che ritornano
    public int avvia_roulette() {
        double n = Math.floor(Math.random()*37);
        int valore = (int)n;
        return valore;
    }

    public String chiediNome(int n_giocatore) {
        System.out.println();
        System.out.println("----------");
        System.out.print("Benvenuto/a giocatore numero " + n_giocatore + " ! Come ti chiami? ");
        String nome = input_stringhe.nextLine();
        return nome;
    }

    public int cambiaFiches() {
        int valore;
        while(true) {
            try {
                System.out.print("Quanti euro vorresti cambiare in fiches? ");
                int fiches = input_numeri.nextInt();
                valore = fiches;
                break;
            }
            catch(Exception exc) {
                System.out.println("Input non valido, riprova");
                input_numeri.nextLine();
            }
        }
        return valore;
    }

    public boolean validazione_numero_giocatori(int scelta) {
        if(scelta > 0 && scelta < 5) {
            return true;
        }
        else {
            System.out.println();
            System.out.println("Puoi scegliere massimo 4 giocatori");
            return false;
        }
    }

    public boolean validazione_scelta_gioco(int scelta) {
        if(scelta > 0 && scelta < 5) {
            return true;
        }
        else {
            System.out.println();
            System.out.println("Puoi scegliere un numero tra 1 e 4");
            return false;
        }
    }

    public char validazione_gioco_si_no() {
        char scelta;
        while(true) {
            System.out.println();
            System.out.print("Vuoi giocare? Si/No ");
            scelta = input_stringhe.nextLine().toLowerCase().charAt(0);
            if(scelta == 's' || scelta == 'n') {
                break; 
            }
            else {
                System.out.println("Puoi scrivere solo Si o No, riprova");
            }
        }
        return scelta;
    }

    public int puntata(Giocatore giocatore) {
        int fiches_disponibili = giocatore.getFiches();
        int valore;
        while(true) {
            try {
                System.out.print("Quanto vorresti puntare? ");
                valore = input_numeri.nextInt();
                if(validazione_puntata(valore, giocatore)) {
                    break;
                }
            }
            catch(Exception exc) {
                System.out.println("Input non valido, riprova");
                input_numeri.nextLine();
            }
        }
        int fiches_finali = giocatore.getFiches();
        return (fiches_disponibili - fiches_finali);
    }

    public boolean validazione_puntata(int puntata, Giocatore giocatore) {
        boolean validazione = false;
        if(puntata < 1) {
            System.out.println("Devi inserire una puntata di valore positivo, riprova");
        }
        else if(puntata > giocatore.getFiches()) {
            System.out.println();
            System.out.println("La tua puntata ha superato il numero di fiches disponibili. Puntata aggiornata a " + giocatore.getFiches() + " fiches.");
            giocatore.setFiches(0);
            validazione = true;
        }
        else {
            giocatore.setFiches(giocatore.getFiches() - puntata);
            validazione = true;
        }
        return validazione;
    }

    public String scelta_1() {
        char scelta;
        while(true) {
            System.out.println();
            System.out.print("Scrivi R per puntare sul rosso o N per puntare sul nero: ");
            scelta = input_stringhe.nextLine().toLowerCase().charAt(0);
            switch(scelta) {
                case 'r':
                    return "r";           
                case 'n':
                    return "n";
                default:
                    System.out.println("Input non valido, riprova");
                    System.out.println();
                    break;
            }
        }
    }

    public String scelta_2() {
        char scelta;
        while(true) {
            System.out.println();
            System.out.print("Scrivi P per puntare su un numero pari o D per puntare su un numero dispari: ");
            scelta = input_stringhe.nextLine().toUpperCase().charAt(0);
            switch(scelta) {
                case 'P':
                    return "p";
                case 'D':
                    return "d";
                default:
                    System.out.println("Input non valido, riprova");
                    System.out.println();
                    break;
            }
        }
    }

    public String scelta_3() {
        String scelta;
        while(true) {
            System.out.println();
            System.out.print("Su che numero vuoi puntare? Scegli un numero da 1 a 36: ");
            scelta = input_stringhe.nextLine();
            switch(scelta) {
                case "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36":
                    return scelta;
                default:
                    System.out.println("Input non valido, riprova");
                    break;
            }
        }
    }

    public String scelta_4() {
        String scelta;
        while(true) {
            System.out.println();
            System.out.print("Scrivi + per puntare sulla prima dozzina, ++ per puntare sulla seconda o +++ per puntare sulla terza: ");
            scelta = input_stringhe.nextLine();
            switch(scelta) {
                case "+", "++", "+++":
                    return scelta;
                default:
                    System.out.println("Input non valido, riprova");
                    break;
            }
        }
    }

    //metodi
    public void creazione_giocatori() {
        int numero_giocatori;
        while(true) {
            try {
                System.out.print("Quanti giocatori parteciperanno? Puoi inserire massimo 4 giocatori: ");
                numero_giocatori = input_numeri.nextInt();
                if(validazione_numero_giocatori(numero_giocatori)) {
                    break;
                }
            }
            catch(Exception exc) {
                System.out.println("Input non valido, riprova");
                input_numeri.nextLine();
            }
        }

        String nome;
        int fiches;
        for(int i = 0; i < numero_giocatori; i++) {
            nome = chiediNome(i+1);
            fiches = cambiaFiches();
            Giocatore giocatore = new Giocatore(nome, fiches);
            giocatori.add(giocatore);
        }
    }

    public void gioco(Giocatore giocatore, int indice) {
        int scelta;
        int puntata = puntata(giocatore);
        puntate_giocatori.set(indice, puntata);
        while(true) {
            try {
                System.out.println();
                System.out.println("Premi 1 per puntare su rosso o nero");
                System.out.println("Premi 2 per puntare su un numero pari o dispari");
                System.out.println("Premi 3 per puntare su un numero secco");
                System.out.println("Premi 4 per puntare sulla prima, seconda o terza dozzina");
                scelta = input_numeri.nextInt();
                if(validazione_scelta_gioco(scelta)) {
                    break;
                }
            }
            catch(Exception exc) {
                System.out.println("Input non valido, riprova");
                input_numeri.nextLine();
            }
        }
        switch(scelta) {
            case 1:
                giocata_scelta.set(indice, scelta_1());
                break;
            case 2:
                giocata_scelta.set(indice, scelta_2());
                break;
            case 3:
                giocata_scelta.set(indice, scelta_3());
                break;
            case 4:
                giocata_scelta.set(indice, scelta_4());
                break;
        }
    }

    public void iniziaPartita() {
        ArrayList<Giocatore> da_rimuovere = new ArrayList<Giocatore>();
        String colore = "";
        int indice;
        int numero_ottenuto;
        Giocatore istanza_giocatore;
        String giocata;
        int puntata;
        System.out.println();
        System.out.println("--- INIZIO PARTITA ---");
        while(giocatori.size() > 0) {
            for(Giocatore x : giocatori) {
                indice = giocatori.indexOf(x);
                if(x.getFiches() == 0) {
                    System.out.println();
                    System.out.println("Mi dispiace non puoi più giocare! " + x.getNome() + " sei fuori!");
                    da_rimuovere.add(x);
                }
                else {
                    System.out.println();
                    System.out.println("............................");
                    System.out.println("Tocca a --> " + x.getNome());
                    char scelta = validazione_gioco_si_no();
                    switch(scelta) {
                        case 'n':
                            System.out.println();
                            System.out.println("===========================================");
                            System.out.println("Hai deciso di terminare la partita " + x.getNome() + ", le fiches a tua disposizione che potrai cambiare in contante sono " + x.getFiches() + " fiches.");
                            System.out.println("===========================================");
                            da_rimuovere.add(x);
                            break;
                        case 's':
                            gioco(x, indice);
                            break;
                    }
                }
            }
            for(Giocatore y : da_rimuovere) {
                giocatori.remove(y);
            }
            //controllo le giocate 
            if(giocatori.size() > 0) {
                numero_ottenuto = avvia_roulette();

                if(numero_ottenuto == 0) {
                    System.out.println();
                    System.out.println("Il numero ottenuto e' 0, vince il banco!");
                }
                else {
                    for (int n : numeri_rossi) {
                        if(n == numero_ottenuto) {
                            colore = "rosso";
                            break;
                        }
                        else {
                            colore = "nero";
                        }
                    }
                    System.out.println();
                    System.out.println("**********************************************");
                    System.out.println("Il numero ottenuto e' " + colore + " : " + numero_ottenuto);
                }    

                giocate_vincenti = croupier.giocate_vincenti(numero_ottenuto);
                croupier.stampa_giocate_vincenti(giocate_vincenti);

                for(int i = 0; i < giocatori.size(); i++) {
                    boolean hai_perso = true;
                    istanza_giocatore = giocatori.get(i);
                    puntata = puntate_giocatori.get(i);
                    giocata = giocata_scelta.get(i);
                    ciclo: for(String s : giocate_vincenti) {
                        if(giocata.equals(s)) {
                            switch(s) {
                                case "r":
                                case "n":
                                    System.out.println();
                                    System.out.println("Hai vinto " + (puntata*2) + " fiches! " + istanza_giocatore.getNome());
                                    istanza_giocatore.setFiches(istanza_giocatore.getFiches() + (puntata*2));
                                    System.out.println("Le tue fiches disponibili ora sono " + istanza_giocatore.getFiches());
                                    hai_perso = false;                        
                                    break ciclo;
                                case "p":
                                case "d":
                                    System.out.println();
                                    System.out.println("Hai vinto " + (puntata*2) + " fiches! " + istanza_giocatore.getNome());
                                    istanza_giocatore.setFiches(istanza_giocatore.getFiches() + (puntata*2));
                                    System.out.println("Le tue fiches disponibili ora sono " + istanza_giocatore.getFiches());
                                    hai_perso = false;
                                    break ciclo;
                                case "0":
                                    System.out.println();
                                    System.out.println("Hai perso " + (puntata) + " fiches! " + istanza_giocatore.getNome());
                                    break ciclo;
                                case "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",     "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36":
                                    System.out.println();
                                    System.out.println("Hai vinto " + (puntata*36) + " fiches! " + istanza_giocatore.getNome());
                                    istanza_giocatore.setFiches(istanza_giocatore.getFiches() + (puntata*36));
                                    System.out.println("Le tue fiches disponibili ora sono " + istanza_giocatore.getFiches());
                                    hai_perso = false;  
                                    break ciclo;
                                case "+", "++", "+++":
                                    System.out.println();
                                    System.out.println("Hai vinto " + (puntata*3) + " fiches! " + istanza_giocatore.getNome());
                                    istanza_giocatore.setFiches(istanza_giocatore.getFiches() + (puntata*3));
                                    System.out.println("Le tue fiches disponibili ora sono " + istanza_giocatore.getFiches());
                                    hai_perso = false;
                                    break ciclo;
                            }
                        }
                    }
                    if(hai_perso) { 
                        System.out.println();
                        System.out.print("Hai perso! " + istanza_giocatore.getNome());
                        System.out.println(" le tue fiches disponibili ora sono " + istanza_giocatore.getFiches());
                    }
                }
                giocate_vincenti.clear();
            }  
            else {
                System.out.println();
                System.out.println("Non ci sono più giocatori!");
                System.out.println();
                System.out.println("FINE");
                System.out.println();
            }  
        }
    }
}
