package gestione.Bilancio;

/**
 *
 * @author Kamil
 */
public class Bilancio {
    public Resoconto Banca;
    public Resoconto Contanti;
    public Resoconto Totale;
    
    public Bilancio() {
        this.Banca = new Resoconto();
        this.Contanti = new Resoconto();
        this.Totale = new Resoconto();
    }
    
}
