/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestione.entrateuscite;

import Dati.Dati;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Kamil
 */
public class FXMLDocumentEliminaController  implements Initializable {
    
    @FXML
    private BorderPane rootPane;
    
    @FXML
    private Label BigTitle;

    @FXML
    private Label LilTitle;
    
    Dati datiToDelete = new Dati();
    String DeleteOption = "";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    void ConfirmElimina(MouseEvent event) {
        if(DeleteOption.equals("resetTable")){
            FXMLDocumentController.IsResetTableConfirmed = true;
            FXMLDocumentController.CLEARtable_Database(FXMLDocumentController.ID_Utente);// svuota la tabella del Database
        } else if(DeleteOption.equals("deleteRow")){
            FXMLDocumentController.ListDatiObj.remove(FXMLDocumentController.ListDatiObj.indexOf(datiToDelete));
            FXMLDocumentController.isDeleteRowConfirm = true;
            // ELIMINA la riga dal Database
            FXMLDocumentController.DELETErow_Database(datiToDelete.getTipo().getValue(),datiToDelete.getTipoBilancio().getValue(),
                                                    datiToDelete.getDescrizione().getValue(),datiToDelete.getData().getValue(),
                                                    datiToDelete.getImporto().getValue(), FXMLDocumentController.ID_Utente);
        }
        FXMLDocumentController.isChangeFinished = true;
        System.out.println("ELIMINA CONFERMATA!");
        LoadMainWindow();
    }

    @FXML
    void CancelElimina(MouseEvent event) {
        System.out.println("ELIMINA ANNULLATA!");
        /**/
        FXMLDocumentController.isChangeFinished = true;
        LoadMainWindow();
        
    }
    
    private void LoadMainWindow() {
        rootPane.setVisible(true);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            BorderPane Pane = loader.load();
            Pane.setTranslateX(5);
            Pane.setTranslateY(5);
            rootPane.getChildren().setAll(Pane);
        } catch (IOException ex) {Logger.getLogger(FXMLDocumentModificaController.class.getName()).log(Level.SEVERE, null, ex);}
    }
    
    public void setElementToDelete(Dati row){
        datiToDelete = row;
    }
    
    public void setDisplayMessage(String BigTitle, String LilTitle, String DeleteCase){
        if(DeleteCase.equals("resetTable")){
            DeleteOption = "resetTable";
            this.BigTitle.setText(BigTitle);
            this.LilTitle.setText(LilTitle);
        } else if(DeleteCase.equals("deleteRow")){
            DeleteOption = "deleteRow";
        }
    }
    
}
