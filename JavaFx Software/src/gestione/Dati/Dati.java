package Dati;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

/**
 *
 * @author Kamil
 */
public class Dati extends RecursiveTreeObject<Dati>{
    private SimpleStringProperty Tipo;
    private SimpleStringProperty TipoBilancio;
    private LocalDate Data; //dal DataPicker, converto il valore in string prima di passarlo
    private SimpleStringProperty Descrizione;
    private double Importo;
    private ImageView TypeMoney;
    private ImageView Arrows;

    public Dati() {
        
    }
    
    public Dati(ImageView TypeMoney, String Tipo, String TipoBilancio, String Descrizione, LocalDate Data, double Importo) {
        this.TypeMoney = TypeMoney;
        this.Tipo = new SimpleStringProperty(Tipo);
        this.TipoBilancio = new SimpleStringProperty(TipoBilancio);
        this.Descrizione = new SimpleStringProperty(Descrizione);
        this.Data = Data;
        this.Importo = Importo;
    }

    public SimpleStringProperty getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = new SimpleStringProperty(Tipo);
    }

    public SimpleStringProperty getTipoBilancio() {
        return TipoBilancio;
    }

    public void setTipoBilancio(String TipoBilancio) {
        this.TipoBilancio = new SimpleStringProperty(TipoBilancio);
    }

    public SimpleStringProperty getData() {
        return new SimpleStringProperty(Data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public void setData(LocalDate Data) {
        this.Data = Data;
    }

    public SimpleStringProperty getDescrizione() {
        return Descrizione;
    }

    public void setDescrizione(String Descrizione) {
        this.Descrizione = new SimpleStringProperty(Descrizione);
    }

    public SimpleStringProperty getImporto() {
        return new SimpleStringProperty(String.valueOf(Importo) + " €");
    }

    public void setImporto(double Importo) {
        this.Importo = Importo;
    }
    
    public void setTypeMoney(ImageView TypeMoney) {
        this.TypeMoney = TypeMoney;
    }

    public ImageView getTypeMoney() {
        return TypeMoney;
    }
    
    public void setArrows(ImageView Arrows) {
        this.Arrows = Arrows;
    }

    public ImageView getArrows() {
        return Arrows;
    }
}
