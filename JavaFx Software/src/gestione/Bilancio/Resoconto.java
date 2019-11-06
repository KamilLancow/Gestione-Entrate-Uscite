package gestione.Bilancio;

/**
 *
 * @author Kamil
 */
public class Resoconto{
    private double Totale = 0;
    public Detail Entrate;
    public Detail Uscite;

    public Resoconto() {
        this.Entrate = new Detail();
        this.Uscite = new Detail();
    }

    public String getTotaleEuro() {
        String Euro = "";
        char[] chars = "".toCharArray();
        boolean found = false;
        int virgola = 0;// index posizione virgola
        int c = 0;// caratteri totali che compongono la stringa (verso: dx --> sx)
        int numChar = 0;// caratteri dopo la virgola (verso: dx --> sx)
        
        if (Totale >= 0)
            chars = String.valueOf(Totale).toCharArray();
        else if(Totale < 0)
            chars = String.valueOf(Totale * -1).toCharArray();
        
        for (int i = chars.length - 1; i >= 0; i--) {
            c++;
            if(found) {
                numChar++;
                if(numChar == 4){
                    numChar = 0;
                    if(((chars.length-1) - virgola) > 2)
                        Euro = String.valueOf(chars).substring(0, chars.length-(c-1)) + "." + String.valueOf(chars).substring(chars.length-(c-1), virgola+3) + " €";
                    else
                        Euro = String.valueOf(chars).substring(0, chars.length-(c-1)) + "." + String.valueOf(chars).substring(chars.length-(c-1)) + " €";
                } else {
                    if(((chars.length-1) - virgola) > 2)
                        Euro = String.valueOf(chars).substring(0, virgola+3) + " €";
                    else
                        Euro = String.valueOf(chars) + " €";
                }
            }
            if (chars[i]=='.') {
                chars[i] = ',';
                virgola = i;
                if(((chars.length-1) - virgola) > 2)
                    Euro = String.valueOf(chars).substring(0, virgola+3) + " €";
                else 
                    Euro = String.valueOf(chars) + " €";
                found = true;
            }
        }
        if(Totale < 0) Euro = "-" + Euro;
        
        return Euro;
    }
    
    public double getTotale() {
        return Totale;
    }

    public void setTotale(double Totale) {
        this.Totale = Totale;
    }
    
}
