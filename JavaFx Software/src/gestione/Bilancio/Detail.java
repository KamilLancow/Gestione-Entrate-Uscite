package gestione.Bilancio;

import java.util.Arrays;

/**
 *
 * @author Kamil
 */
public class Detail {
    private int Num = 0;
    private double Money = 0;

    public Detail() {
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int Num) {
        this.Num = Num;
    }
    
    public String getMoneyEuro() {
        String Euro = "";
        char[] chars = String.valueOf(Money).toCharArray();
        boolean found = false;
        int virgola = 0;// index posizione virgola
        int c = 0;// caratteri totali che compongono la stringa (verso: dx --> sx)
        int numChar = 0;// caratteri dopo la virgola (verso: dx --> sx)
        for (int i = chars.length - 1; i >= 0; i--) {
            c++;
            if(found) {
               // c++;
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
//                System.out.println("    numChar: " + numChar + "  " + chars.length + "  " + c);
            }
            if (chars[i]=='.') {
                chars[i] = ',';
                virgola = i;
                if(((chars.length-1) - virgola) > 2)
                    Euro = String.valueOf(chars).substring(0, virgola+3) + " €";
                else 
                    Euro = String.valueOf(chars) + " €";
                found = true;
//                System.out.println("    found");
            }
        }
        
        return Euro;
    }
    
    public double getMoney() {
        return Money;
    }

    public void setMoney(double Money) {
        this.Money = Money;
    }
    
}
