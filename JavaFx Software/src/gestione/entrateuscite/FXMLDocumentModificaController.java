/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestione.entrateuscite;

import Dati.Dati;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Kamil
 */
public class FXMLDocumentModificaController implements Initializable {

    @FXML
    private JFXComboBox<String> Tipologia_CBox;
    @FXML
    private JFXComboBox<String> TipoMoney_CBox;
    @FXML
    private JFXTextArea Descrizione_TextArea;
    @FXML
    private JFXDatePicker Data_DPicker;
    @FXML
    private JFXTextField Importo_Int;
    @FXML
    private JFXTextField Importo_Cent;
    @FXML
    public static JFXButton AnnullaBtn;
    @FXML
    private BorderPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configureComboBox();
    } 
    
    Dati datiToModify = new Dati();
    @FXML
    void ConfirmModifica(MouseEvent event) {
        
        boolean isErrorCent = false;
        boolean isErrorInt = false;
        boolean isErrorBilancio = false;
        
        //CONTROLLA SE I CAMPI IMPORTO HANNO SOLO NUMERI & SE IL TIPO BILANCIO E' == NULL
        //
        //CENT
        try{
            int x;
            if(Importo_Cent.getText().equals(""))Importo_Cent.setText("00");
            else x= Integer.parseInt(Importo_Cent.getText());
        }catch (NumberFormatException e) {
            Importo_Cent.setStyle("-fx-text-inner-color: RED; -jfx-focus-color: RED; -jfx-unfocus-color: RED;");
            isErrorCent = true;
        }
        finally {
            if(!isErrorCent)Importo_Cent.setStyle(Data_DPicker.getStyle());
        }
        //INT
        try{
            int x;
            if(Importo_Int.getText().equals(""))Importo_Int.setText("0");
            else x = Integer.parseInt(Importo_Int.getText());
        }catch (NumberFormatException e) {
            Importo_Int.setStyle("-fx-text-inner-color: RED; -jfx-focus-color: RED; -jfx-unfocus-color: RED;");
            isErrorInt = true;
        }
        finally {
            if(!isErrorInt)Importo_Int.setStyle(Data_DPicker.getStyle());
        }
        //BILANCIO
        if(TipoMoney_CBox.getSelectionModel().getSelectedIndex() == -1){
            TipoMoney_CBox.setStyle("-fx-prompt-text-fill: RED; -jfx-focus-color: RED; -jfx-unfocus-color: RED;");
            isErrorBilancio = true;
        }
        else
            TipoMoney_CBox.setStyle(Data_DPicker.getStyle());
        
        //SE TUTTI I CAMPI SONO RIEMPITI CORRETTAMENTE VAI AD AGGIORNARE I DATI
        if(!isErrorCent && !isErrorInt && !isErrorBilancio){
            Dati element = new Dati();
            ImageView img;
            
            element.setTipo(Tipologia_CBox.getSelectionModel().getSelectedItem().toUpperCase());//Entrata Hotel
            element.setTipoBilancio(TipoMoney_CBox.getSelectionModel().getSelectedItem());
            element.setDescrizione(Descrizione_TextArea.getText());
            element.setData(Data_DPicker.getValue());
            element.setImporto(Double.valueOf(Importo_Int.getText() + "." + Importo_Cent.getText()));
            //element.setTypeMoney(...); (IMG)
            if(element.getTipoBilancio().getValue().equalsIgnoreCase("Banca")){
                img = new ImageView(new Image(getClass().getResource("/gestione/Icons/Type_Banca.png").toExternalForm()));
                img.setTranslateX(9);
                element.setTypeMoney(img);
            }else if(element.getTipoBilancio().getValue().equalsIgnoreCase("Contanti")) {
                img = new ImageView(new Image(getClass().getResource("/gestione/Icons/Type_Contanti.png").toExternalForm()));
                img.setTranslateX(9);
                element.setTypeMoney(img);
            }
            //element.setArrows(...); (IMG)
            if(!element.getTipo().getValue().equalsIgnoreCase("Uscita Merce") && !element.getTipo().getValue().equalsIgnoreCase("Uscita Merce Sospesi")){
                img = new ImageView(new Image(getClass().getResource("/gestione/Icons/Arrow_green.png").toExternalForm()));
                img.setTranslateX(0);
                element.setArrows(img);
            }else if(element.getTipo().getValue().equalsIgnoreCase("Uscita Merce") || element.getTipo().getValue().equalsIgnoreCase("Uscita Merce Sospesi")) {
                img = new ImageView(new Image(getClass().getResource("/gestione/Icons/Arrow_red.png").toExternalForm()));
                img.setTranslateX(0);
                element.setArrows(img);
            }
            /**/
            FXMLDocumentController.ListDatiObj.set(FXMLDocumentController.ListDatiObj.indexOf(datiToModify), element);
            // Modifica riga nel Database
            FXMLDocumentController.UPDATErow_Database(element.getTipo().getValue(), element.getTipoBilancio().getValue(),
                                                    element.getDescrizione().getValue(), element.getData().getValue(), element.getImporto().getValue(),
                                                    datiToModify.getTipo().getValue(),datiToModify.getTipoBilancio().getValue(),
                                                    datiToModify.getDescrizione().getValue(),datiToModify.getData().getValue(),
                                                    datiToModify.getImporto().getValue(), FXMLDocumentController.ID_Utente);
            
            System.out.println("MODIFICA CONFERMATA!");
            /**/
            FXMLDocumentController.isChangeFinished = true;
            FXMLDocumentController.isChangeConfirm = true;
            LoadMainWindow();
        }
        
    }

    @FXML
    void CancelModifica(MouseEvent event) {
        System.out.println("MODIFICA ANNULLATA!");
        /**/
        FXMLDocumentController.isChangeFinished = true;
        LoadMainWindow();
        
    }
    
    @FXML
    void setTipoBilancio(Event event) {
        setTipoMoney();
    }
    
    @FXML
    void onSelectedTipoBilancio(Event event) {
        if(TipoMoney_CBox.getSelectionModel().getSelectedIndex() != -1)
            TipoMoney_CBox.setStyle(Tipologia_CBox.getStyle());
    }
    
    @FXML
    void onNumChanged_Int(KeyEvent event) {
        boolean isErrorInt = false;
        //INT
        try{
            int x;
            if(Importo_Int.getText().equals(""));
            else x = Integer.parseInt(Importo_Int.getText());
        }catch (NumberFormatException e) {
            Importo_Int.setStyle("-fx-text-inner-color: RED; -jfx-focus-color: RED; -jfx-unfocus-color: RED;");
            isErrorInt = true;
        }
        finally {
            if(!isErrorInt)Importo_Int.setStyle(Data_DPicker.getStyle());
        }
    }
    
    @FXML
    void onNumChanged_Cent(KeyEvent event) {
        boolean isErrorCent = false;
        //CENT
        try{
            int x;
            if(Importo_Cent.getText().equals(""));
            else x= Integer.parseInt(Importo_Cent.getText());
        }catch (NumberFormatException e) {
            Importo_Cent.setStyle("-fx-text-inner-color: RED; -jfx-focus-color: RED; -jfx-unfocus-color: RED;");
            isErrorCent = true;
        }
        finally {
            if(!isErrorCent)Importo_Cent.setStyle(Data_DPicker.getStyle());
        }
    }
    
    /**/
    /**/
    private void configureComboBox(){
        Tipologia_CBox.getItems().addAll(
                                        "Entrata Hotel",
                                        "Entrata Ristorante",
                                        "Uscita Merce",
                                        "Uscita Merce Sospesi",
                                        "Incasso POS",
                                        "Assegno",
                                        "Deposito Banca");
        
        TipoMoney_CBox.getItems().addAll(
                                        "Banca",
                                        "Contanti");
        
    }
    
    //Imposta i dati della riga selezionata nei campi appena si apre la finestra
    public void setDataToFields(Dati row) {
        datiToModify = row;
        //TIPOLOGIA
        if(row.getTipo().getValue().equalsIgnoreCase("Incasso POS")) Tipologia_CBox.getSelectionModel().select("Incasso POS");
        else Tipologia_CBox.getSelectionModel().select(capitalizeString(row.getTipo().getValue()));
        setTipoMoney();
        TipoMoney_CBox.getSelectionModel().select(row.getTipoBilancio().getValue());
        //DESCRIZIONE
        Descrizione_TextArea.setText(row.getDescrizione().getValue());
        //DATA
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTime = LocalDate.parse(row.getData().getValue(), formatter);
        Data_DPicker.setValue(dateTime);
        //IMPORTO
        String nInt="", nCent="";
        for(int i=0; i<row.getImporto().getValue().length(); i++){
            if(row.getImporto().getValue().charAt(i) == '.'){
                nInt = row.getImporto().getValue().substring(0, i);
                nCent = row.getImporto().getValue().substring(i+1);
                nCent = nCent.substring(0, nCent.length()-2);
                break;
            }
        }
        Importo_Int.setText(nInt);
        Importo_Cent.setText(nCent);
        
    }
    
    //Capitalize every words' first character
    private String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
          if (!found && Character.isLetter(chars[i])) {
            chars[i] = Character.toUpperCase(chars[i]);
            found = true;
          } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other chars here
            found = false;
          }
  }
  return String.valueOf(chars);
}
    
    private void setTipoMoney() {
        String ItemSelected = Tipologia_CBox.getSelectionModel().getSelectedItem();
        if(Tipologia_CBox.getSelectionModel().isEmpty()){}
        else if(ItemSelected.equalsIgnoreCase("Incasso POS") || ItemSelected.equalsIgnoreCase("Assegno") || ItemSelected.equalsIgnoreCase("Deposito Banca")){
            TipoMoney_CBox.getSelectionModel().select("Banca");
            TipoMoney_CBox.setDisable(true);
        } else {
            TipoMoney_CBox.getSelectionModel().clearSelection();
            TipoMoney_CBox.setDisable(false);
        }
    }
    
    private void LoadMainWindow() {
        rootPane.setVisible(true);
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                BorderPane Pane = loader.load();
                Pane.setTranslateX(5);
                Pane.setTranslateY(5);
                //NON MI SERVE PER ORA
                //FXMLDocumentController controller = (FXMLDocumentController) loader.getController();
                //controller.resetSelectedRow(selectedItem.getValue());
                rootPane.getChildren().setAll(Pane);
            } catch (IOException ex) {Logger.getLogger(FXMLDocumentModificaController.class.getName()).log(Level.SEVERE, null, ex);}
    }
    
    /**/
    /**/
    
}
