import java.util.Scanner;

/**
 * @author Serenki 
 * @version 2020/12/13
 * This version will be updated soon. It is really messy. It works though. Currently it is also in German.
 */

public class TicTacToe
{
    byte[][] feld;
    boolean zug;
    boolean zuletztAngefangen;
    int score1;
    int score2;
    int gleichstaende;
    byte letztesErgebnis;
    int streak;
    Scanner userInput;

    public TicTacToe()
    {
        userInput = new Scanner(System.in);
        neustart();
    }

    public void neustart() {
        feld = new byte[3][3];      // 0 --> unbesetztes Feld | 1 --> X | 2 --> O
        zug = false;    // false --> X | true --> O
        zuletztAngefangen = zug;
        score1 = 0;
        score2 = 0;
        gleichstaende = 0;
        letztesErgebnis = 0;    // 1 --> Xgewann | 2 --> Ogewann | 3 --> Gleichstand
        streak = 1;     // muss bei 1 starten da jede Streak logischerweise mindestens 1 ist. Wenn man nur 1x gewinnt, ist es eine 1er streak.
        resetFeld();
        spielen();
    }

    public void resetFeld() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                feld[i][j] = 0;
            }
        }
    }

    public void feldAusgeben() {
        System.out.println();
        System.out.println("\\ | A | B | C");
        System.out.println("--+---+---+---");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + 1 + " | ");
            for (int j = 0; j < 3; j++) {
                if (feld[i][j] == 0) {
                    System.out.print(" ");
                }
                else if (feld[i][j] == 1) {
                    System.out.print("X");
                }
                else if (feld[i][j] == 2) {
                    System.out.print("O");
                }
                else {
                    System.err.println("Uff was ist da passiert");
                    System.exit(-1);
                }
                if (j < 2) {
                    System.out.print(" | ");
                }
                else {
                    System.out.println();
                }
            }
            if (i < 2) {
                System.out.println("--+---+---+---");
            }
        }
        System.out.println();
        System.out.println();
    }
    
    public String checkUserInput() {
        String user = userInput.next();
        user = user.toUpperCase().replace(" ", "");
        if(user.length() != 2) {
            System.out.println("Du brauchst einen Buchstabe f¸r die Spalte UND eine Zahl f¸r die Zeile.");
            System.out.println("Du bist immer noch dran!");
            user = checkUserInput();
        }
        if (!user.contains("A") && !user.contains("B") && !user.contains("C") || !user.contains("1") && !user.contains("2") && !user.contains("3")) {
            System.out.println("Du musst einen Buchstabe von A bis C f¸r die Spalte w‰hlen, dazu noch eine Zahl von 1 bis 3 f¸r die Zeile.");
            System.out.println("Du bist immer noch dran!");
            user = checkUserInput();
        }
        int x = -1;
        int y = -1;
        if (user.charAt(0) == '1' || user.charAt(0) == '2' || user.charAt(0) == '3') {
            x = user.charAt(0) - 49;        //https://www.torsten-horn.de/techdocs/ascii.htm
            y = user.charAt(1) - 65; 
        }
        else {
            x = user.charAt(1) - 49;        //https://www.torsten-horn.de/techdocs/ascii.htm
            y = user.charAt(0) - 65; 
        }
        if (feld[x][y] != 0) {
            System.out.println("Dieses Feld ist schon belegt.");
            System.out.println("Du bist immer noch dran!");
            user = checkUserInput();
        }
        return user;
    }
    
    public void ziehen() {
        if (this.zug) {
            System.out.println("Spieler 2 (O) ist dran!");
            this.zug = false;
        }
        else {
            this.zug = true;
            System.out.println("Spieler 1 (X) ist dran!");
        }

        String user = checkUserInput();

        for (int i = 0; i < 3; i++) {
            int zahl = i + 1;
            String reihe = zahl + "";
            if (user.contains("A") && user.contains(reihe)) {
                if (!this.zug) {     //true --> 2 bzw. O        //  verneint, da der Wert zuvor umgedreht wurde.
                    feld[i][0] = 2;
                } else {
                    feld[i][0] = 1;
                }
            }

            else if (user.contains("B") && user.contains(reihe)) {
                if (!this.zug) {     //true --> 2 bzw. O
                    feld[i][1] = 2;
                } else {
                    feld[i][1] = 1;
                }
            }

            else if (user.contains("C") && user.contains(reihe)) {
                if (!this.zug) {     //true --> 2 bzw. O
                    feld[i][2] = 2;
                } else {
                    feld[i][2] = 1;
                }
            }
        }
    }

    public byte kannWeiterspielen() {
        for (int i = 0; i < 3; i++) {
            if (feld[i][0] == feld [i][1] && feld[i][1] == feld [i][2] && feld[0][0] != 0) {
                return feld[i][0];
            }
            if (feld[0][i] == feld [1][i] && feld[1][i] == feld [2][i] && feld[0][0] != 0) {
                return feld[0][i];
            }
        }
        if (feld[0][0] == feld[1][1] && feld[1][1] == feld[2][2] && feld[0][0] != 0) {
            return feld[0][0];
        }
        if (feld[0][2] == feld[1][1] && feld[1][1] == feld[2][0] && feld[0][0] != 0) {
            return feld[0][0];
        }
        return 0;
    }

    public void ende(byte ergebnis) {
        if (letztesErgebnis == ergebnis) {
            streak++;
        }
        else {
            streak = 1;
        }
        if (zuletztAngefangen) {
            zuletztAngefangen = false;
            zug = false;
        }
        else {
            zuletztAngefangen = true;
            zug = true;
        }
        
        if (ergebnis == 0) {
            gleichstaende++;
            this.letztesErgebnis = 0;
            System.out.println("Unentschieden!");
        }
        else if (ergebnis == 1) {
            score1++;
            this.letztesErgebnis = 1;
            System.out.println("Spieler 1 (X) hat gewonnen!");
        }
        else if (ergebnis == 2) {
            score2++;
            this.letztesErgebnis = 2;
            System.out.println("Spieler 2 (O) hat gewonnen!");
        }
        if (streak >= 3) {
            System.out.println("Dieses Ergebnis gab es nun schon zum " + streak + ". Mal in Folge!");
        }
        System.out.println();
        System.out.println("Punktestand:");
        System.out.println("Spieler 1 (X): " + score1 + " <|> Unentschieden: " + gleichstaende + " <|> Spieler 2 (O): " + score2);
        System.out.println();
    }
    
    public boolean nochmal() {
        System.out.println("Was wollt ihr nun tun? Noch eine Runde: \"nochmal\" oder \"1\". Reset der Punkte: \"neustart\" oder \"2\". Fertig?: \"schlieﬂen\" oder \"0\".");
        String user = userInput.next();
        user.toLowerCase().trim();
        if(user.equals("nochmal") || user.equals("1")) {
            System.out.println("Los geht eine neue Runde!");
            return true;
        }
        else if(user.equals("neustart") || user.equals("2")) {
            System.out.println("Der Punktestand wird auf Null gesetzt.");
            return false;
        }
        else if(user.equals("schlieﬂen") || user.equals("0")) {
            System.out.println("Das Programm wird geschlossen.");
            System.exit(0);
        }
        else {
            System.out.println("Befehl wurde nicht verstanden!");
            nochmal();
        }
        return false;
    }

    public void spielen() {
        feldAusgeben();
        byte resultat = 0;
        for (int i = 0; i < 9; i++) {
            ziehen();
            feldAusgeben();
            resultat = kannWeiterspielen();
            if (resultat != 0) {
                break;
            }
        }
        ende(resultat);
        
        if (nochmal()) {
            resetFeld();
            spielen();
        }
        else {
            neustart();
        }
    }
}
