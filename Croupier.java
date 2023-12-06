import java.util.ArrayList;

public class Croupier {
    int[] numeri_rossi = new int[]{1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
    ArrayList<String> giocate_vincenti = new ArrayList<String>();

    public ArrayList<String> giocate_vincenti(int numero_ottenuto) {
        if(numero_ottenuto == 0) {
            giocate_vincenti.add("0");
        }
        else {
            giocate_vincenti.add("n");
            for(int n : numeri_rossi) {
                if(n == numero_ottenuto) {
                    giocate_vincenti.set(0, "r");
                    break;
                }
            }
            if((numero_ottenuto % 2) == 0) {
                giocate_vincenti.add("p");
            }
            else {
                giocate_vincenti.add("d");
            }
            if(numero_ottenuto > 0 && numero_ottenuto < 13) {
                giocate_vincenti.add("+");
            }
            else if(numero_ottenuto > 12 && numero_ottenuto < 25) {
                giocate_vincenti.add("++");
            }
            else {
                giocate_vincenti.add("+++");
            }
            giocate_vincenti.add(String.valueOf(numero_ottenuto));
        }    
        return giocate_vincenti;
    }

    public void stampa_giocate_vincenti(ArrayList<String> giocate_vincenti) {
        System.out.println();
        System.out.println("--- Parla il croupier ---");
        System.out.print("Le giocate vincenti sono: ");
        for(String s : giocate_vincenti) {
            switch(s) {
                case "r":
                    System.out.print("* Rosso ");
                    break;
                case "n":
                    System.out.print("* Nero ");
                    break;
                case "p":
                    System.out.print("* Pari ");
                    break;
                case "d":
                    System.out.print("* Dispari ");
                    break;
                case "+":
                    System.out.print("* Prima dozzina ");
                    break;
                case "++":
                    System.out.print("* Seconda dozzina ");
                    break;
                case "+++":
                    System.out.print("* Terza dozzina ");
                    break;
                case "0":
                    System.out.print("* 0 | Mi dispiace, ha vinto il banco! | ");
                    break;
                case "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36":
                    System.out.print("* " + s);
                    break;
            }
        }
        System.out.println(); // mi serve per mandare a capo
    }
}