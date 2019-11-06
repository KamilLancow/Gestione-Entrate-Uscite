/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestione.entrateuscite;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Kamil
 */
public class GestioneEntrateUscite extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.getIcons().add(new Image(getClass().getResource("/gestione/Icons/sw-icon.png").toExternalForm()));
        stage.setTitle("Gestione Entrate-Uscite");
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
        //stage.onCloseRequestProperty();
        
    }
    @Override
    public void stop(){
        FXMLDocumentController.stopSinc_toDatabase();// ferma la sincronizzazione dei dati col database
        System.out.println("Stage is closed.");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
