package gestione.entrateuscite;

import Dati.Dati;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import gestione.Bilancio.Bilancio;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import javafx.scene.control.TreeTableColumn;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
//import javafx.scene.control.Cell;             //UGUALI FA ERRORE PER LO STESSO NOME
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.TreeTableView;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;        //UGUALI FA ERRORE PER LO STESSO NOME
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Kamil
 */
public class FXMLDocumentController implements Initializable {

    // ***TEMPORANEO***
    @FXML
    private Button button;
    @FXML
    private JFXDatePicker CampoData;
    // ***TEMPORANEO***
    
    /*********************************
     *           <GENERAL>           *
     *********************************/
    @FXML
    private Pane BlackPane;

    @FXML
    private AnchorPane PanelModifica;
    
    @FXML
    private AnchorPane PanelAccedi;
    
    /*********************************
     *            <LOGIN>            *
     *********************************/
    @FXML
    private JFXTextField Email_Field;
    @FXML
    private JFXPasswordField Password_Field;
    
    /******************************************
     *           <TABPANE / NAVBAR>           *
     ******************************************/
    @FXML
    private JFXTabPane TabPane;
    
    //LOGIN
    @FXML
    private JFXButton Login_Btn;
    @FXML
    private Label ID_Utente_Name;
    @FXML
    private AnchorPane User_Logged_Win;
    
    //  TAB PANORAMICA DATI
    @FXML
    private Tab TAB_PanoramicaDati;
    @FXML
    private ImageView IMG_PanoramicaDati;

    //  TAB GRAFICI
    @FXML
    private Tab TAB_Grafici;
    @FXML
    private ImageView IMG_Grafici;

    //  TAB AGGIUNGI DATI
    @FXML
    private Tab TAB_AggiungiDati;
    @FXML
    private ImageView IMG_AggiungiDati;
    
    /******************************************
     *           <PANORAMICA DATI>            *
     ******************************************/
    //  TABLEVIEW
    @FXML
    private JFXTreeTableView<Dati> Tabella;

    @FXML
    private TreeTableColumn<Dati, ImageView> TipoMoney;
    @FXML
    private TreeTableColumn<Dati, String> Tipologia;
    @FXML
    private TreeTableColumn<Dati, String> Descrizione;
    @FXML
    private TreeTableColumn<Dati, String> Data;
    @FXML
    private TreeTableColumn<Dati, String> Importo;
    @FXML
    private TreeTableColumn<Dati, ImageView> Arrows;
    
    @FXML
    private Label Label_BilancioBanca;
    @FXML
    private Label Label_BilancioContanti;
    @FXML
    private Label Label_BilancioTotale;
    @FXML
    private JFXButton SaveBtn;
    @FXML
    private JFXButton Btn_resetTable;

    /**********************************
     *           <GRAFICI>            *
     **********************************/
    //Starts Here
    
    /****************************************
     *           <AGGIUNGI DATI>            *
     ****************************************/
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
    private Label DataErrorLabel;
    @FXML
    private JFXButton AddBtn;
    
    @FXML
    private JFXButton ImportBtn;
    @FXML
    private JFXButton ChooseFileBtn;
    @FXML
    private JFXTextField FileExcelPath;
    @FXML
    private JFXProgressBar ProgressBar;
    @FXML
    private Pane PBCompletedText;
    @FXML
    private Pane PBErrorText;
    
    /**/
    /*****************************/
    public static ArrayList<Dati> ListDatiObj = new ArrayList<>();
    TreeItem<Dati> root= new TreeItem<>(new Dati(null, null, null, null, null, 0));;
    int cont = ListDatiObj.size();
    static int selectedRow;
    public static boolean isChangeFinished = false;
    public static boolean isChangeConfirm = false;
    boolean isImporting = false;
    boolean isFileError = false;
    File selectedFile = new File("");
    Tab Global_TabSelected;
    String FileName = "", fileToday_Name;
    public static Bilancio Bilancio;
    public static boolean IsResetTableConfirmed = false;
    public static boolean isDeleteRowConfirm = false;
    public static String DBname = "FBwGjmCbpP";// Database Name
    public static String ID_Utente = "";
    public static String User_nome = "", User_cognome = "";
    static Timer timerSinc;
    static boolean isUserLoggedIn = false;
    /*****************************/
    /**/
    /****************************
     *          <INIT>          *
     ****************************/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*Login*/
        if(isUserLoggedIn == true){
            ID_Utente_Name.setText(User_nome + " " + User_cognome);
            User_Logged_Win.setVisible(true);
        }
        /*Panoramica Dati*/
        disableTableReordering();
        showCellValueCorrectly();
        addTooltipToColumnCells(Descrizione);
        addRowContextMenu();
        if(isChangeFinished == true){
            printDatiToTable("reverse");
            Tabella.getSelectionModel().select(selectedRow);
            if(isChangeConfirm){
                isChangeConfirm = false;
                CreateNotification("Successful", "Modifica avvenuta con successo.", new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-ok.png").toExternalForm())));
            }else if(IsResetTableConfirmed){
                deleteTableContent();
                IsResetTableConfirmed = false;
                CreateNotification("Successful", "Tabella svuotata correttamente.", new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-ok.png").toExternalForm())));
            }else if(isDeleteRowConfirm){
                isDeleteRowConfirm = false;
                CreateNotification("Successful", "Riga eliminata con successo.", new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-ok.png").toExternalForm())));
            }
            isChangeFinished = false;
        }
        fileToday_Name = "Resoconto Entrate-Uscite [" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).substring(0, 10) + "].xlsx";
        if(!ListDatiObj.isEmpty()) Btn_resetTable.setDisable(false);
        getBilancioFromTable();
        setBilancioMoney();
        /*Aggiungi Dati*/
        configureComboBox();
        /**/
        
        /**/
        System.out.println("INIT APERTO!");
    }
    
    /******************************************
     *           <TABPANE / NAVBAR>           *
     ******************************************/
    
    //When called change tabs images & Resets Aggiungi Dati Fields
    @FXML
    void cambia_IMG_tab(MouseEvent event) {
        Tab curSelected = TabPane.getSelectionModel().getSelectedItem();

        //TAB_PanoramicaDati
        Image img_1_DEFAULT = new Image(getClass().getResource("/gestione/Icons/PanoramicaDati_DEFAULT.png").toExternalForm());
        Image img_1_SELECTED = new Image(getClass().getResource("/gestione/Icons/PanoramicaDati_SELECTED.png").toExternalForm());
        //TAB_Grafici
        Image img_2_DEFAULT = new Image(getClass().getResource("/gestione/Icons/Grafici_DEFAULT.png").toExternalForm());
        Image img_2_SELECTED = new Image(getClass().getResource("/gestione/Icons/Grafici_SELECTED.png").toExternalForm());
        //TAB_AggiungiDati
        Image img_3_DEFAULT = new Image(getClass().getResource("/gestione/Icons/AggiungiDati_DEFAULT.png").toExternalForm());
        Image img_3_SELECTED = new Image(getClass().getResource("/gestione/Icons/AggiungiDati_SELECTED.png").toExternalForm());

        if(curSelected == TAB_PanoramicaDati){
            IMG_PanoramicaDati.setImage(img_1_SELECTED);
            IMG_Grafici.setImage(img_2_DEFAULT);
            IMG_AggiungiDati.setImage(img_3_DEFAULT);
            //System.out.println("1 cambiato!!!");
        }
        else if(curSelected == TAB_Grafici) {
            IMG_PanoramicaDati.setImage(img_1_DEFAULT);
            IMG_Grafici.setImage(img_2_SELECTED);
           IMG_AggiungiDati.setImage(img_3_DEFAULT);
            //System.out.println("2 cambiato!!!");
        }
        else if(curSelected == TAB_AggiungiDati){
            IMG_PanoramicaDati.setImage(img_1_DEFAULT);
            IMG_Grafici.setImage(img_2_DEFAULT);
            IMG_AggiungiDati.setImage(img_3_SELECTED);
            //System.out.println("3 cambiato!!!");
        }
        resetAddDataFields(curSelected);
    }
    
    //Reset Aggiungi Dati Fields
    private void resetAddDataFields(Tab curSelected) {
        if(curSelected != TAB_AggiungiDati){
            Global_TabSelected = curSelected;
        } else {
            if(Global_TabSelected != TAB_AggiungiDati){
                clearFields("inserisci");
                Tipologia_CBox.setStyle("-jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
                TipoMoney_CBox.setStyle("-jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
                Descrizione_TextArea.setStyle("-fx-prompt-text-fill: #4d4d4d; -jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
                DataErrorLabel.setVisible(false);
                Data_DPicker.setPromptText("Seleziona una data");
                Importo_Int.setStyle("-fx-text-inner-color: BLACK; -jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
                Importo_Cent.setStyle("-fx-text-inner-color: BLACK; -jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
                if(PBErrorText.isVisible()) {
                    clearFields("importa");
                    FileExcelPath.setText("");
                } else
                    clearFields("importa");
                
                Global_TabSelected = TAB_AggiungiDati;
            }
        }
    }
    
    //INFO BUTTON
    //When clicked opens a page where you can read all info about how to do/use something
//    @FXML
//    void JustDoIt(MouseEvent event) {
//
//        System.out.println("help_icon CLICKED!!\n");
//    }
    
    /*********************************
     *           <GENERAL>           *
     *********************************/
    
    //Create Notifications
    private void CreateNotification(String title, String text, ImageView img) {
        img.setFitHeight(50);
        img.setFitWidth(50);
        Notifications notifica = Notifications.create()
            .title(title)
            .text(text)
            .graphic(img)
            .hideAfter(Duration.seconds(5))
            .hideCloseButton()
            .position(Pos.BOTTOM_LEFT);
//            .onAction(new EventHandler<ActionEvent>(){
//                @Override
//                public void handle(ActionEvent event){
//                System.out.println("NOTIFICA CLICKED!!");
//                }
//            });
        notifica.show();
    }
    
    //Does the Window Blurred Effect
    private void WindowBlackEffect(boolean x) {
        if(x == true){
            TabPane.setEffect(new GaussianBlur(20));
            BlackPane.setVisible(x);
        } else {
            TabPane.setEffect(null);
            BlackPane.setVisible(x);
        }
    }
    
    /***NOT USING***/
    private void launchTread(){
        new Thread(() ->{
//            try{
//                while(!isChangeFinished){
//                    
//                }
//                
//            }catch(InterruptedException e){System.out.println(e);}
        }){{start();}};
    }
    
    //Capitalize every words' first character
    private String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for(int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if(Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other chars here
                found = false;
            }
        }
        return String.valueOf(chars);
    }
    
    /****    <DATABASE>    ****/
    // crea la connessione al database
    public static Connection getConnectionToDB(){
//        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gestione_entrate_uscite", "root", "");
            Connection con = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306", "FBwGjmCbpP", "AtC7S0axMi");
            return con;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // AGGIUNGE molteplici righe dal SW al Database
    private void ADDtable_Database(ArrayList<Dati> ListDati) {
        if(!ID_Utente.equals("")){
            for (int i = 0; i < ListDati.size(); i++) {
                Connection con = getConnectionToDB();
                PreparedStatement p;
                try{
                p=con.prepareStatement("INSERT INTO `" + DBname + "`.`dati`(`tipo`, `tipo_bilancio`, `descrizione`, `data`, `importo`, `ID_utente`) VALUES (?,?,?,?,?,?)");
                p.setString(1, ListDati.get(i).getTipo().get());
                p.setString(2, ListDati.get(i).getTipoBilancio().get());
                p.setString(3, ListDati.get(i).getDescrizione().get());
                p.setString(4, ListDati.get(i).getData().get());
                p.setString(5, ListDati.get(i).getImporto().get());
                p.setString(6, ID_Utente);
                p.executeUpdate();
                con.close();
            }catch(Exception e) { System.out.println(e);} 
            }   
        }
    }
    
    // AGGIUNGE una riga nella tabella dati
    private void ADDrow_Database(String tipo, String tipo_bilancio, String descrizione, String data, String importo, String ID_utente) {
        if(!ID_utente.equals("")){
            Connection con = getConnectionToDB();
            PreparedStatement p;
            try{
                p=con.prepareStatement("INSERT INTO `" + DBname + "`.`dati`(`tipo`, `tipo_bilancio`, `descrizione`, `data`, `importo`, `ID_utente`) VALUES (?,?,?,?,?,?)");
                p.setString(1, tipo);
                p.setString(2, tipo_bilancio);
                p.setString(3, descrizione);
                p.setString(4, data);
                p.setString(5, importo);
                p.setString(6, ID_utente);
                p.executeUpdate();
                con.close();
            }catch(Exception e) { System.out.println(e);}   
        }
    }
    
    // ELIMINA una riga nella tabella dati
    public static void DELETErow_Database(String tipo, String tipo_bilancio, String descrizione, String data, String importo, String ID_utente) {
        if(!ID_utente.equals("")){
            Connection con = getConnectionToDB();
            PreparedStatement p;
            try{
                p=con.prepareStatement("DELETE FROM `" + DBname + "`.`dati` WHERE `tipo`=? AND `tipo_bilancio`=? AND `descrizione`=? AND `data`=? AND `importo`=? AND `ID_utente`= ?");      
                p.setString(1, tipo);
                p.setString(2, tipo_bilancio);
                p.setString(3, descrizione);
                p.setString(4, data);
                p.setString(5, importo);
                p.setString(6, ID_utente);
                p.executeUpdate();
                con.close();
            }catch(Exception e) {
                System.out.println(e);
            }    
        }
    }
    
    // ELIMINA dalla tabella dati SOLO le righe che appartengono all'utente loggato 
    public static void CLEARtable_Database(String ID_utente) {
        if(!ID_utente.equals("")){
            Connection con = getConnectionToDB();
            PreparedStatement p;
            try{
                p=con.prepareStatement("DELETE FROM `" + DBname + "`.`dati` WHERE `ID_utente`= ?");
                p.setString(1, ID_utente);
                p.executeUpdate();
                con.close();
            }catch(Exception e) {
                System.out.println(e);
            }    
        }
    }
    
    // AGGIORNA una riga nella tabella dati
    public static void UPDATErow_Database(String NEWtipo, String NEWtipo_bilancio, String NEWdescrizione, String NEWdata, String NEWimporto,
            String OLDtipo, String OLDtipo_bilancio, String OLDdescrizione, String OLDdata, String OLDimporto, String ID_utente) {
        if(!ID_utente.equals("")){
            Connection con = getConnectionToDB();
            PreparedStatement p;
            try{
                p=con.prepareStatement("UPDATE `" + DBname + "`.`dati` SET `tipo`=?,`tipo_bilancio`=?,`descrizione`=?,`data`=?,`importo`=?"
                                     + " WHERE `tipo`=? AND `tipo_bilancio`=? AND `descrizione`=? AND `data`=? AND `importo`=? AND `ID_utente`= ?");
                p.setString(1, NEWtipo);
                p.setString(2, NEWtipo_bilancio);
                p.setString(3, NEWdescrizione);
                p.setString(4, NEWdata);
                p.setString(5, NEWimporto);
                p.setString(6, OLDtipo);
                p.setString(7, OLDtipo_bilancio);
                p.setString(8, OLDdescrizione);
                p.setString(9, OLDdata);
                p.setString(10, OLDimporto);
                p.setString(11, ID_utente);
                p.executeUpdate();
                con.close();
            }catch(Exception e) {
                System.out.println(e);
            }
        }
    }
    
    // Avvia la comunicazione e sincronizzazione col Database
    private void startSinc_toDatabase() {
        timerSinc = new Timer();
        timerSinc.schedule(new sincToDB(), 0, 5000);
    }
    // Ferma la comunicazione e sincronizzazione col Database
    public static void stopSinc_toDatabase() {
       timerSinc.cancel();
    }
    
    // crea una lista di Dati(appartenenti all'user loggato) ottenuti dal database
    private ArrayList<Dati> getDatabaseDatiList(){
        ArrayList<Dati> List = new ArrayList<>();
        Connection con = getConnectionToDB();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM `" + DBname + "`.`dati` WHERE `ID_utente` = '" + ID_Utente + "'");
            Dati element;
            while(rs.next()){
                element = new Dati(null, rs.getString("tipo"), rs.getString("tipo_bilancio"), rs.getString("descrizione"), LocalDate.parse(rs.getString("data"), DateTimeFormatter.ofPattern("dd/MM/yyyy")), Double.valueOf(rs.getString("importo").substring(0, rs.getString("importo").length()-2)));
                List.add(element);
            }
            con.close();
        } catch(Exception e){ e.printStackTrace(); }
        
        return List;
    }
    
    // sincronizza i dati del Sofrware con quelli del Database
    class sincToDB extends TimerTask {
        public void run() {
            System.out.println("Sinc . . .");
            ArrayList<Dati> ListDati_DB = getDatabaseDatiList();
            boolean sincToDatabase = false;
            // ottieni arraylist da db e confronta prima la loro lunghezza, se diversa fai sinc, se di uguale lunghezza controlla riga per riga e se ce qualche differenza fai sinc.
            
            // se listaDatiDB e ListaDatiSW hanno una lunghezza DIVERSA: copia i dati del DB nel SW (sinc)
            if(ListDati_DB.size() != ListDatiObj.size()){
                sincToDatabase = true;
            }
            else {// se le liste hanno UGUALE lunghezza, controlla se c'e qualche differenza riga per riga e se e' il caso fai sinc
                for (int i = 0; i < ListDati_DB.size(); i++) {
                    if(! ListDatiObj.get(i).getTipo().getValue().equals(ListDati_DB.get(i).getTipo().getValue()) ||
                       ! ListDatiObj.get(i).getTipoBilancio().getValue().equals(ListDati_DB.get(i).getTipoBilancio().getValue()) ||
                       ! ListDatiObj.get(i).getDescrizione().getValue().equals(ListDati_DB.get(i).getDescrizione().getValue()) ||
                       ! ListDatiObj.get(i).getData().getValue().equals(ListDati_DB.get(i).getData().getValue()) ||
                       ! ListDatiObj.get(i).getImporto().getValue().equals(ListDati_DB.get(i).getImporto().getValue()))
                    {
                        sincToDatabase = true;
                    }
                }
            }
            
            // sinc to Database code
            if (sincToDatabase == true) {
                ListDatiObj.clear();
                
                ImageView img;
                for (int i = 0; i < ListDati_DB.size(); i++) {
                    Dati element = new Dati();
                    element.setTipo(ListDati_DB.get(i).getTipo().getValue());
                    element.setTipoBilancio(ListDati_DB.get(i).getTipoBilancio().getValue());
                    element.setDescrizione(ListDati_DB.get(i).getDescrizione().getValue());
                    element.setData(LocalDate.parse(ListDati_DB.get(i).getData().getValue(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    element.setImporto(Double.valueOf(ListDati_DB.get(i).getImporto().getValue().substring(0, ListDati_DB.get(i).getImporto().getValue().length()-2)));
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

                    ListDatiObj.add(element);
                }
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        getBilancioFromTable();
                        printDatiToTable("reverse");
                        printDatiToTable("reverse");
                        setBilancioMoney();
                        setBilancioMoney();
                        CreateNotification("Successful", "Dati sincronizzati.", new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-ok.png").toExternalForm())));
                        Btn_resetTable.setDisable(false);
                        }
                });
            }
        }
    }
    
    /*********************************
     *            <LOGIN>            *
     *********************************/
    
    // Opens login screen
    @FXML
    void Open_LoginScreen(MouseEvent event) {
        TabPane.setEffect(new GaussianBlur(20));
        PanelAccedi.setVisible(true);
    }
    // Closes login screen
    @FXML
    void Close_LoginScreen(MouseEvent event) {
        TabPane.setEffect(null);
        PanelAccedi.setVisible(false);
        Email_Field.clear();
        Password_Field.clear();
        Email_Field.setStyle("-jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
        Password_Field.setStyle("-jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
    }
    
    // If typed data is correct, it connects the Software to the Database
    @FXML
    void LoginToDatabase(MouseEvent event) {
        // controlla se user esiste
        User_nome = "";
        User_cognome = "";
        int num_accessi = 0;
        Connection con = getConnectionToDB();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT `nome`, `cognome`, `num_accessi` FROM `"+ DBname +"`.`utenti` WHERE `email` ='" + Email_Field.getText() + "' AND `password` = '" + Password_Field.getText() + "'");
            while(rs.next()){
                User_nome = rs.getString("nome");
                User_cognome = rs.getString("cognome");
                num_accessi = rs.getInt("num_accessi") + 1;
            }
            con.close();
        } catch(Exception e){ e.printStackTrace(); }
        
        // se i dati login SONO corretti
        if(!User_nome.concat(User_cognome).equals("")){
            con = getConnectionToDB();
            PreparedStatement p;
            try{
                p=con.prepareStatement("UPDATE `" + DBname + "`.`utenti` SET `num_accessi`=?" + " WHERE `email`='" + Email_Field.getText() + "' AND `password` = '" + Password_Field.getText() + "'");
                p.setInt(1, num_accessi);
                p.executeUpdate();
                con.close();
            }catch(Exception e) { System.out.println(e);}
            ID_Utente = Email_Field.getText();
            // se la tabella SW e' vuota: fai partire sinc col DB & display user info
            if(ListDatiObj.isEmpty()){
                startSinc_toDatabase();
            }
            // se la tabella contiene dati: carica dati SW su DB, poi fai partire sinc col DB & display user info
            else if(!ListDatiObj.isEmpty()){
                ADDtable_Database(ListDatiObj);
                startSinc_toDatabase();
            }
            System.out.println("User is: " + User_nome + " " + User_cognome);
            //al posto di button accedi fai comparire utente nome+cognome e logout btn
            ID_Utente_Name.setText(User_nome + " " + User_cognome);
            User_Logged_Win.setVisible(true);
            isUserLoggedIn = true;// if = true vuol dire che un utente e loggato
            // closing login window
            Close_LoginScreen(event);
            Platform.runLater(new Runnable(){@Override public void run() {
                    CreateNotification("Successful", "Accesso all'account effettuato. Benvenuto " + User_nome, new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-ok.png").toExternalForm())));
                }});
        }
        else{// se i dati login NON SONO corretti fai errore
            System.out.println("User doesn't exist.");
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    CreateNotification("Errore", "Account non esistente. Email o Password errati.", new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-error.png").toExternalForm())));
                }
            });
            Email_Field.setStyle("-fx-prompt-text-fill: #4d4d4d; -jfx-focus-color: RED; -jfx-unfocus-color: RED;");
            Password_Field.setStyle("-fx-prompt-text-fill: #4d4d4d; -jfx-focus-color: RED; -jfx-unfocus-color: RED;");
        }
        
    }
    // Disconnette dal database(delete ID_Utente value) e svuota tabella SW
    @FXML
    void LogoutToDatabase(MouseEvent event) {
        stopSinc_toDatabase();
        ID_Utente = "";
        deleteTableContent();
        isUserLoggedIn = false;// if = false vuol dire che l'utente si e disconnesso
        Platform.runLater(new Runnable(){@Override public void run() {
                CreateNotification("Successful", "Disconnessione dall'account effettuata correttamente.", new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-ok.png").toExternalForm())));
            }});
        User_Logged_Win.setVisible(false);
        System.out.println("Disconnessione eseguita.");
    }
    
    // set default color style
    @FXML
    void setDefaultStyle_email(KeyEvent event) {
        Email_Field.setStyle("-jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
    }
    @FXML
    void setDefaultStyle_password(KeyEvent event) {
        Password_Field.setStyle("-jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
    }
    
    // when the label is clicked, it opens the webpage into the browser
    @FXML
    void OpenWebpageRegister(MouseEvent event) throws Exception{
        Desktop d = Desktop.getDesktop();
        d.browse(new URI("https://gestioneentrateuscite.000webhostapp.com/register.php"));
        System.out.println("link cliccato");
    }
    
    /******************************************
     *           <PANORAMICA DATI>            *
     ******************************************/
    
    // When called there'll be deleted a ObjDati element and table is refreshed
    void DeleteElementTable(ActionEvent event) {
//        TreeItem<Dati> selectedItem = Tabella.getSelectionModel().getSelectedItem();
//
//        if(selectedItem == null){
//            CreateNotification("Attenzione!", "Seleziona una riga da eliminare.", new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-error.png").toExternalForm())));
//        } else {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Elimina");
//            alert.setHeaderText("");
//            alert.setContentText("Eliminare la riga selezionata?");
//            alert.initModality(Modality.APPLICATION_MODAL);
//            
//            WindowBlackEffect(true);
//            Optional<ButtonType> answer = alert.showAndWait();
//            if(answer.get() == ButtonType.OK){
//                //Eliminare l'oggetto selezionato
//                ListDatiObj.remove(selectedItem.getValue());
//                printDatiToTable("reverse");
//                cont--;//optional...poi da eliminare
//                CreateNotification("Successful", "Eliminazione avvenuta con successo.", new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-ok.png").toExternalForm())));
//                System.out.println("ELIMINAZIONE Avvenuta\t\tListDatiObj lenght: " + this.ListDatiObj.size());//Da rimuovore poi
//            }
//            WindowBlackEffect(false);
//        }

        TreeItem<Dati> selectedItem = Tabella.getSelectionModel().getSelectedItem();

        if(selectedItem == null){
            CreateNotification("Attenzione!", "Seleziona una riga da modificare.", new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-error.png").toExternalForm())));
        } else {
            WindowBlackEffect(true);
            PanelModifica.setVisible(true);
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocumentElimina.fxml"));
                        AnchorPane Pane = loader.load();
                        FXMLDocumentEliminaController controller = (FXMLDocumentEliminaController) loader.getController();
                        controller.setElementToDelete(selectedItem.getValue());
                        controller.setDisplayMessage("","", "deleteRow");
                        PanelModifica.getChildren().setAll(Pane);
                    } catch (IOException ex) {Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);}
                }
            });
            
            System.out.println("\t\tListDatiObj lenght PRIMA: " + this.ListDatiObj.size() + " --> " + this.ListDatiObj.get(0).getData());
        }
    }

    void EditElementTable(ActionEvent event) {
        TreeItem<Dati> selectedItem = Tabella.getSelectionModel().getSelectedItem();

        if(selectedItem == null){
            CreateNotification("Attenzione!", "Seleziona una riga da modificare.", new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-error.png").toExternalForm())));
/**/
//        Alert AlertMaker = new Alert(AlertType.ERROR);
//            AlertMaker.setAlertType(AlertType.ERROR);
//            AlertMaker.getDialogPane().setContentText("Selezionare un elemento da modificare.");
//            AlertMaker.show();
/**/
        } else {
            WindowBlackEffect(true);
            PanelModifica.setVisible(true);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocumentModifica.fxml"));
                AnchorPane Pane = loader.load();
                FXMLDocumentModificaController controller = (FXMLDocumentModificaController) loader.getController();
                controller.setDataToFields(selectedItem.getValue());
                PanelModifica.getChildren().setAll(Pane);
            } catch (IOException ex) {Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);}
            
            System.out.println("\t\tListDatiObj lenght PRIMA: " + this.ListDatiObj.size() + " --> " + this.ListDatiObj.get(0).getData());
        }
    }
    
    //Apre ContextMenu(modifica,elimina) quando si clicca su una riga della tabella
    private void addRowContextMenu() {
        Tabella.setRowFactory(new Callback<TreeTableView<Dati>, TreeTableRow<Dati>>(){
            @Override
            public TreeTableRow<Dati> call(TreeTableView<Dati> treeTableView) {
                final TreeTableRow<Dati> row = new TreeTableRow<>();
                final ContextMenu rowMenu = new ContextMenu();
                ImageView img;
                //modificaItem
                MenuItem modificaItem = new MenuItem("Modifica");
                img = new ImageView(new Image(getClass().getResource("/gestione/Icons/edit-icon.png").toExternalForm()));
                img.setFitHeight(20);
                img.setFitWidth(20);
                modificaItem.setGraphic(img);
                modificaItem.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent t) {EditElementTable(t);}
                });
                //eliminaItem
                MenuItem eliminaItem = new MenuItem("Elimina");
                img = new ImageView(new Image(getClass().getResource("/gestione/Icons/delete-icon.png").toExternalForm()));
                img.setFitHeight(23);
                img.setFitWidth(17);
                eliminaItem.setGraphic(img);
                eliminaItem.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent t) {DeleteElementTable(t);}
                });

                rowMenu.getItems().addAll(modificaItem, eliminaItem);
                row.contextMenuProperty().bind(Bindings.when(Bindings.isNotNull(row.itemProperty()))
                .then(rowMenu)
                .otherwise((ContextMenu)null));
                return row;
            }
        });

    }
    
    //When rows are clicked it returns which one it is
    @FXML
    void setSelectedRow(MouseEvent event) {
        selectedRow = Tabella.getSelectionModel().getSelectedIndex();
        System.out.println("!!selectedRow!! = " + selectedRow);
    }
    
    //Impedisce di modificare l'ordine delle colonne
    private void disableTableReordering(){
        Tabella.getColumns().addListener(new ListChangeListener() {
        @Override
        public void onChanged(ListChangeListener.Change change) {
          change.next();
          if(change.wasReplaced()) {
              Tabella.getColumns().clear();
              Tabella.getColumns().addAll(TipoMoney,Tipologia,Descrizione,Data,Importo,Arrows);
          }
        }
    });
    }

    //Serve per visualizzare correttamente i Dati nella Tabella
    private void showCellValueCorrectly() {
//        Tipologia.setCellValueFactory(c-> new SimpleObjectProperty<String>(
//        c.getValue().getValue().getTipo().concat(new SimpleStringProperty("\n").concat(c.getValue().getValue().getTipoBilancio()))));
//        // SETTING THE CELL FACTORY FOR THE ALBUM ART
//        TipoMoney.setCellFactory(new Callback<TreeTableColumn<Dati,ImageView>,TreeTableCell<Dati,ImageView>>(){
//            @Override
//            public TreeTableCell<Dati,ImageView> call(TreeTableColumn<Dati,ImageView> param) {
//                TreeTableCell<Dati,ImageView> cell = new TreeTableCell<Dati,ImageView>(){
//                    //@Override
//                    public void updateItem(Dati item, boolean empty) {
//                        if(item!=null){
//                            HBox box= new HBox();
//                            box.setSpacing(5) ;
//                            /*VBox vbox = new VBox();
//                            vbox.getChildren().add(new Label("qualcosa"));*/
//
//                            ImageView imageview = new ImageView();
//                            imageview.setFitHeight(30);
//                            imageview.setFitWidth(30);
//                            imageview.setImage(new Image(getClass().getResource("/gestione/Icons/help_icon.png").toExternalForm()));
//
//                            box.getChildren().addAll(imageview/*,vbox*/);
//                            //SETTING ALL THE GRAPHICS COMPONENT FOR CELL
//                            setGraphic(box);
//                        }
//                    }
//                };
//                System.out.println(cell.getIndex());
//                return cell;
//            }
//
//        });
        Tabella.setRoot(this.root);
        Tabella.setShowRoot(false);
        Tabella.setFixedCellSize(50);

        TipoMoney.setCellValueFactory(c-> new SimpleObjectProperty<ImageView>(c.getValue().getValue().getTypeMoney()));
        Tipologia.setCellValueFactory((TreeTableColumn.CellDataFeatures<Dati, String> param) ->
                param.getValue().getValue().getTipo().concat(new SimpleStringProperty("\n").concat(param.getValue().getValue().getTipoBilancio())));
        Descrizione.setCellValueFactory((TreeTableColumn.CellDataFeatures<Dati, String> param) -> param.getValue().getValue().getDescrizione());
        Data.setCellValueFactory((TreeTableColumn.CellDataFeatures<Dati, String> param) -> param.getValue().getValue().getData());
        Importo.setCellValueFactory((TreeTableColumn.CellDataFeatures<Dati, String> param) -> param.getValue().getValue().getImporto());
//        Importo.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Dati, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Dati, String> param) {
//                String nInt="", nCent="";
//                for(int i=0; i<param.getValue().getValue().getImporto().toString().length(); i++){
//                    if(param.getValue().getValue().getImporto().toString().charAt(i) == '.'){
//                        nInt = param.getValue().getValue().getImporto().toString().substring(0, i);
//                        nCent = param.getValue().getValue().getImporto().toString().substring(i+1);
//                        //nCent = nCent.substring(0, nCent.length()-2);
//                        break;
//                    }
//                }
//                String Importo = nInt + "," + nCent + " €";
//                String ImportoNew = new SimpleStringProperty(nInt).getValue().concat(new SimpleStringProperty(",").getValue()
//                        .concat(new SimpleStringProperty(nCent).getValue().concat(new SimpleStringProperty(" €").getValue())));
//                //param.getValue().getValue().getImporto();
//                //Importo.bind(Bindings.concat(nInt, ",", nCent, " €"));
//                
//                System.out.println("Importo = " + nInt + "," );
//                return new SimpleStringProperty(Importo);
//            }
//        });
        Arrows.setCellValueFactory(c-> new SimpleObjectProperty<ImageView>(c.getValue().getValue().getArrows()));
    }

    //Stampa ListaDatiObj nella Tabella
    private void printDatiToTable(String ordine) {
        this.root.getChildren().clear();
        //Stampa ListaDatiObj al contrario(dall'oggetto inserito piu recentemente a quello meno)
        if(ordine.equals("reverse")){
            for(int i=0; i< this.ListDatiObj.size(); i++){
                TreeItem<Dati> newRow = new TreeItem<>(this.ListDatiObj.get((this.ListDatiObj.size()-1)-i));
                this.root.getChildren().add(newRow);
            }
        } else if(ordine.equals("copy")) {
        //Stampa ListaDatiObj tale e quale(dal primo all'ultimo oggetto)
            for(int i=0; i< this.ListDatiObj.size(); i++){
                TreeItem<Dati> newRow = new TreeItem<>(this.ListDatiObj.get(i));
                this.root.getChildren().add(newRow);
            }
        }

    }
    
    //Crate Tooltip when holding cursor on 'descrizione' item
    private <T> void addTooltipToColumnCells(TreeTableColumn<Dati,T> column) {
        Callback<TreeTableColumn<Dati, T>, TreeTableCell<Dati,T>> existingCellFactory
            = column.getCellFactory();

        column.setCellFactory(c -> {
            TreeTableCell<Dati, T> cell = existingCellFactory.call(c);

            Tooltip tooltip = new Tooltip();
            // can use arbitrary binding here to make text depend on cell
            // in any way you need:
            tooltip.textProperty().bind(cell.itemProperty().asString());

            cell.setTooltip(tooltip);
            cell.tooltipProperty().bind(Bindings.when(Bindings.or(cell.emptyProperty(), cell.itemProperty().isNull())).then((Tooltip) null).otherwise(tooltip));
            return cell ;
        });
    }
    
    //Salva i Dati dalla tabella nel file Excel
    @FXML
    void SaveTableToFile(MouseEvent event) throws FileNotFoundException, IOException{ 
        //if Table is Empty, don't Save on File & get Notification
        if(Tabella.getRoot().isLeaf())
            CreateNotification("Attenzione!", "Inserire dei dati prima di salvare su file Excel.", new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-warning.png").toExternalForm())));
        else {
            // else if Table has elements, Save on File
            XSSFWorkbook workbook = new XSSFWorkbook();
            CreationHelper createHelper = workbook.getCreationHelper();
            XSSFSheet sheet = workbook.createSheet("Entrate-Uscite");
            
            // Lock Sheet, that way the User cannot edit the file
            sheet.enableLocking();
            // set rows height
            sheet.setDefaultRowHeightInPoints(20);
            // set columns width
            sheet.setColumnWidth(0, 2 * 256);
            sheet.setColumnWidth(1, 23 * 256);
            sheet.setColumnWidth(2, 36 * 256);
            sheet.setColumnWidth(3, 16 * 256);
            sheet.setColumnWidth(4, 16 * 256);
            sheet.setColumnWidth(5, 3 * 256);
            // Creating header Font
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);
            headerFont.setColor(IndexedColors.BLACK.getIndex());
            // Creating header CellStyle
            XSSFCellStyle headerCellStyle = (XSSFCellStyle) workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            headerCellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(189, 215, 238)));
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // Creating Header Row
            Row headerRow = sheet.createRow(0);
            // Creating Header cells
            String[] columns = {"Tipologia", "Tipologia", "Descrizione", "Data", "Importo", ""};
            for(int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }
            // Merges the cells
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 5));

            // CellStyle for DATA column
            XSSFCellStyle dateCellStyle = (XSSFCellStyle) workbook.createCellStyle();
            dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
            dateCellStyle.setAlignment(HorizontalAlignment.RIGHT);
            dateCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            // CellStyle for IMPORTO column
            XSSFCellStyle ImportoCellStyle = (XSSFCellStyle) workbook.createCellStyle();
            ImportoCellStyle.setAlignment(HorizontalAlignment.RIGHT);
            ImportoCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            // CellStyle fo CENTERING TEXT
            XSSFCellStyle GENERALCellStyle = (XSSFCellStyle) workbook.createCellStyle();
            GENERALCellStyle.setAlignment(HorizontalAlignment.LEFT);
            GENERALCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            
            // OBTAINING BILANCIO TABLE DATA
            getBilancioFromTable();
            //PRINTING BILANCIO TABLE
            printBilancioTableEXCEL(sheet, headerCellStyle, workbook);

            //Reversing the Data Object
            ArrayList<Dati> ListDati = ListDatiObj;
            Collections.reverse(ListDati);
            // Create Other rows and cells with employees data
            int rowNum = 1;
            for(Dati element: ListDati) {
                Row row;
                // se row esiste gia, ottienila. Altrimenti crea row. (serve per scrivere correttamente nel file)
                if(rowNum >= 1 && rowNum <= 9){
                    Row Row = sheet.getRow(rowNum++);
                    row = Row;
                } else {
                    Row Row = sheet.createRow(rowNum++);
                    row = Row;
                }

                // #column 0 | TYPE MONEY(IMG)
                Cell typeM = row.createCell(0);
                if(element.getTipoBilancio().getValue().equalsIgnoreCase("Banca")){
                    XSSFCellStyle typeMCellStyle = (XSSFCellStyle) workbook.createCellStyle();
                    typeMCellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(0, 176, 240)));
                    typeMCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    typeM.setCellStyle(typeMCellStyle);
                } else {
                    XSSFCellStyle typeMCellStyle = (XSSFCellStyle) workbook.createCellStyle();
                    typeMCellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 192, 0)));
                    typeMCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    typeM.setCellStyle(typeMCellStyle);
                }
                // #column 1 | TIPOLOGIA
                row.createCell(1).setCellValue(capitalizeString(element.getTipo().getValue()));
                row.getCell(1).setCellStyle(GENERALCellStyle);
                // #column 2 | DESCRIZIONE
                row.createCell(2).setCellValue(element.getDescrizione().getValue());
                row.getCell(2).setCellStyle(GENERALCellStyle);
                // #column 3 | DATA
                Cell date = row.createCell(3);
                date.setCellValue(element.getData().getValue());
                date.setCellStyle(dateCellStyle);
                // #column 4 | IMPORTO
                Cell importo = row.createCell(4);
                importo.setCellValue(element.getImporto().getValue());
                importo.setCellStyle(ImportoCellStyle);
                // #column 5 | ARROW(IMG)
                Cell arrow = row.createCell(5);
                if(!element.getTipo().getValue().equalsIgnoreCase("Uscita Merce") && !element.getTipo().getValue().equalsIgnoreCase("Uscita Merce Sospesi")){
                    Font font = workbook.createFont();
                    font.setBold(true);
                    font.setFontHeightInPoints((short) 13);
                    font.setColor(IndexedColors.OLIVE_GREEN.getIndex());

                    XSSFCellStyle ArrowCellStyle = (XSSFCellStyle) workbook.createCellStyle();
                    ArrowCellStyle.setAlignment(HorizontalAlignment.CENTER);
                    ArrowCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                    ArrowCellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(153, 204, 0)));
                    ArrowCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    ArrowCellStyle.setFont(font);
                    arrow.setCellValue("+");
                    arrow.setCellStyle(ArrowCellStyle);
                }
                else{
                    Font font = workbook.createFont();
                    font.setBold(true);
                    font.setFontHeightInPoints((short) 13);
                    font.setColor(IndexedColors.DARK_RED.getIndex());

                    XSSFCellStyle ArrowCellStyle = (XSSFCellStyle) workbook.createCellStyle();
                    ArrowCellStyle.setAlignment(HorizontalAlignment.CENTER);
                    ArrowCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                    ArrowCellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 0, 0)));
                    ArrowCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    ArrowCellStyle.setFont(font);
                    arrow.setCellValue("-");
                    arrow.setCellStyle(ArrowCellStyle);
                }

            }
            // Syling tabella excel 
            for(int i = 0; i < ListDatiObj.size(); i++) {
                boolean isEven = sheet.getRow(i+1).getRowNum()%2 == 0;
                // if row pari, colore White
                if(!isEven){
                    for (int j = 1; j <= 4; j++) {
                        sheet.getRow(i+1).getCell(j).getCellStyle().setFillForegroundColor(IndexedColors.WHITE.getIndex());
                        sheet.getRow(i+1).getCell(j).getCellStyle().setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    }
                } else {// else if row dispari, colore Blu chiaro
                    for (int j = 1; j <= 4; j++) {
                        XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
                        style.cloneStyleFrom(sheet.getRow(i+1).getCell(j).getCellStyle());
                        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(221, 235, 247)));
                        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        sheet.getRow(i+1).getCell(j).setCellStyle(style);
                    }
                }
            }

            // Write the OUTPUT to a FILE
            try{
                FileOutputStream fileOut = new FileOutputStream("Resoconto Entrate-Uscite/" + fileToday_Name);
                workbook.write(fileOut);
                fileOut.close();
                // Closing the workbook
                workbook.close();
                if(isFileError) {
                    isFileError = false;
                    SaveTableToFile(event);
                } else
                    CreateNotification("Successful!", "Dati salvati correttamente su file Excel \"" + fileToday_Name +"\"", new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-ok.png").toExternalForm())));
            }catch(IOException e){
                CreateNotification("Attenzione!", "Impossibile salvare il file. . . Chiudere il file Excel prima di salvarlo.", new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-warning.png").toExternalForm())));
                isFileError = true;
            }
            
            System.out.println("FATTO!");
        }
    }
    
    //Prints Bilancio Table
    private void printBilancioTableEXCEL(Sheet sheet, XSSFCellStyle headerCellStyle, Workbook workbook) {
        //Creating Header Cell
        Cell cellB = sheet.getRow(0).createCell(7);
        cellB.setCellValue("Bilancio");
        cellB.setCellStyle(headerCellStyle);
        // Merges the Header Cells
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 7, 11));
        
        /* --------- */
        /* --SYLES-- */
        /* --------- */
        // BOLD FONT Style
        Font BoldFont = workbook.createFont();
        BoldFont.setBold(true);
        // LEFT ALIGN Style
        XSSFCellStyle LEFTCellStyle = (XSSFCellStyle) workbook.createCellStyle();
        LEFTCellStyle.setAlignment(HorizontalAlignment.LEFT);
        LEFTCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        LEFTCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        LEFTCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // RIGHT ALIGN Style
        XSSFCellStyle RIGHTCellStyle = (XSSFCellStyle) workbook.createCellStyle();
        RIGHTCellStyle.setAlignment(HorizontalAlignment.RIGHT);
        RIGHTCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        RIGHTCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        RIGHTCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        RIGHTCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#.##0,00 €;[Rosso]-#.##0,00 €"));
        // CENTER ALIGN Style
        XSSFCellStyle CENTERCellStyle = (XSSFCellStyle) workbook.createCellStyle();
        CENTERCellStyle.setAlignment(HorizontalAlignment.CENTER);
        CENTERCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        CENTERCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        CENTERCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // Color BANCA (Blue)
        XSSFCellStyle ColorBANCA = (XSSFCellStyle) workbook.createCellStyle();
        ColorBANCA.setFillForegroundColor(new XSSFColor(new java.awt.Color(0, 176, 240)));
        ColorBANCA.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // Color CONTANTI (Yellow)
        XSSFCellStyle ColorCONTANTI = (XSSFCellStyle) workbook.createCellStyle();
        ColorCONTANTI.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 192, 0)));
        ColorCONTANTI.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // Color TOTALE (Gray)
        XSSFCellStyle ColorTOTALE = (XSSFCellStyle) workbook.createCellStyle();
        ColorTOTALE.setFillForegroundColor(new XSSFColor(new java.awt.Color(128, 128, 128)));
        ColorTOTALE.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // ENTRATA Style
        Font fontENTRATA = workbook.createFont();
        fontENTRATA.setBold(true);
        fontENTRATA.setFontHeightInPoints((short) 13);
        fontENTRATA.setColor(IndexedColors.OLIVE_GREEN.getIndex());
        XSSFCellStyle SyleENTRATA = (XSSFCellStyle) workbook.createCellStyle();
        SyleENTRATA.cloneStyleFrom(CENTERCellStyle);
        SyleENTRATA.setFillForegroundColor(new XSSFColor(new java.awt.Color(153, 204, 0)));
        SyleENTRATA.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        SyleENTRATA.setFont(fontENTRATA);
        // USCITA Style
        Font fontUSCITA = workbook.createFont();
        fontUSCITA.setBold(true);
        fontUSCITA.setFontHeightInPoints((short) 13);
        fontUSCITA.setColor(IndexedColors.DARK_RED.getIndex());
        XSSFCellStyle SyleUSCITA = (XSSFCellStyle) workbook.createCellStyle();
        SyleUSCITA.cloneStyleFrom(CENTERCellStyle);
        SyleUSCITA.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 0, 0)));
        SyleUSCITA.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        SyleUSCITA.setFont(fontUSCITA);
        /* --------- */
        /* --SYLES-- */
        /* --------- */
        
        // ?????????????????????????????
//        XSSFCellStyle CellStyle_Type = (XSSFCellStyle) workbook.createCellStyle();
//        CellStyle_Type.cloneStyleFrom(LEFTCellStyle);
//        CellStyle_Type.setFont(BoldFont);
        // ?????????????????????????????
        
        /* TABLE ROWS */
        sheet.setColumnWidth(7, 1 * 256);
        sheet.setColumnWidth(8, 2 * 256);
        sheet.setColumnWidth(9, 3 * 256);
        sheet.setColumnWidth(10, 9 * 256);
        sheet.setColumnWidth(11, 12 * 256);
        
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 9, 10));
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 9, 10));
        sheet.addMergedRegion(new CellRangeAddress(9, 9, 9, 10));
        
        for (int row = 1; row <= 9; row++) {
            sheet.createRow(row).createCell(7);
            sheet.getRow(row).createCell(8);
            sheet.getRow(row).createCell(9);
            sheet.getRow(row).createCell(10);
            sheet.getRow(row).createCell(11);
            
            //if rowNum <= 3
            if (row <= 3) {
                sheet.getRow(row).getCell(7).setCellStyle(ColorBANCA);
                switch(row){
                    case 1:
                        sheet.getRow(row).getCell(8).setCellValue("+");
                        sheet.getRow(row).getCell(8).setCellStyle(SyleENTRATA);
                        sheet.getRow(row).getCell(9).setCellValue(Bilancio.Banca.Entrate.getNum());// numero entrate banca
                        sheet.getRow(row).getCell(9).setCellStyle(CENTERCellStyle);
                        sheet.getRow(row).getCell(10).setCellValue("Entrate");
                        sheet.getRow(row).getCell(10).setCellStyle(LEFTCellStyle);
                        sheet.getRow(row).getCell(11).setCellValue(Bilancio.Banca.Entrate.getMoneyEuro());// money entrate banca
                        sheet.getRow(row).getCell(11).setCellStyle(RIGHTCellStyle);
                        break;
                    case 2:
                        sheet.getRow(row).getCell(8).setCellValue("-");
                        sheet.getRow(row).getCell(8).setCellStyle(SyleUSCITA);
                        sheet.getRow(row).getCell(9).setCellValue(Bilancio.Banca.Uscite.getNum());// numero uscite banca
                        sheet.getRow(row).getCell(9).setCellStyle(CENTERCellStyle);
                        sheet.getRow(row).getCell(10).setCellValue("Uscite");
                        sheet.getRow(row).getCell(10).setCellStyle(LEFTCellStyle);
                        sheet.getRow(row).getCell(11).setCellValue(Bilancio.Banca.Uscite.getMoneyEuro());// money uscite banca
                        sheet.getRow(row).getCell(11).setCellStyle(RIGHTCellStyle);
//                            sheet.getRow(row).getCell(11).getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#.##0,00 €;[Rosso]-#.##0,00 €"));
                        break;
                    case 3:
                        sheet.getRow(row).getCell(8).setCellStyle(ColorBANCA);
                        XSSFCellStyle style1 = (XSSFCellStyle) workbook.createCellStyle();
                        style1.cloneStyleFrom(LEFTCellStyle);
                        style1.setFont(BoldFont);
                        style1.setFillForegroundColor(new XSSFColor(new java.awt.Color(221, 235, 247)));
                        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        sheet.getRow(row).getCell(9).setCellStyle(style1);
                        sheet.getRow(row).getCell(9).setCellValue("Banca");
                        XSSFCellStyle style2 = (XSSFCellStyle) workbook.createCellStyle();
                        style2.cloneStyleFrom(style1);
                        style2.setAlignment(HorizontalAlignment.RIGHT);
//                            style2.setDataFormat(HSSFDataFormat.getBuiltinFormat("#.##0,00 €;[Rosso]-#.##0,00 €"));
                        if(Bilancio.Banca.getTotale() < 0){
                            Font font = workbook.createFont();
                            font.setBold(true);
                            font.setColor(IndexedColors.RED.getIndex());
                            style2.setFont(font);
                        }
                        sheet.getRow(row).getCell(11).setCellStyle(style2);
                        sheet.getRow(row).getCell(11).setCellValue(Bilancio.Banca.getTotaleEuro());// settare money (prima di settare il Value convertire 123.5 in 123,50 €)
                        break;
                }
            } else if (row >= 4 && row <= 6) {
                //System.out.println(row + "MBHA IO NON LO SO!");
                sheet.getRow(row).getCell(7).setCellStyle(ColorCONTANTI);
                switch(row){
                    case 4:
                        sheet.getRow(row).getCell(8).setCellValue("+");
                        sheet.getRow(row).getCell(8).setCellStyle(SyleENTRATA);
                        sheet.getRow(row).getCell(9).setCellValue(Bilancio.Contanti.Entrate.getNum());// numero entrate banca
                        sheet.getRow(row).getCell(9).setCellStyle(CENTERCellStyle);
                        sheet.getRow(row).getCell(10).setCellValue("Entrate");
                        sheet.getRow(row).getCell(10).setCellStyle(LEFTCellStyle);
                        sheet.getRow(row).getCell(11).setCellValue(Bilancio.Contanti.Entrate.getMoneyEuro());// money uscite banca
                        sheet.getRow(row).getCell(11).setCellStyle(RIGHTCellStyle);
                        break;
                    case 5:
                        sheet.getRow(row).getCell(8).setCellValue("-");
                        sheet.getRow(row).getCell(8).setCellStyle(SyleUSCITA);
                        sheet.getRow(row).getCell(9).setCellValue(Bilancio.Contanti.Uscite.getNum());// numero uscite banca
                        sheet.getRow(row).getCell(9).setCellStyle(CENTERCellStyle);
                        sheet.getRow(row).getCell(10).setCellValue("Uscite");
                        sheet.getRow(row).getCell(10).setCellStyle(LEFTCellStyle);
                        sheet.getRow(row).getCell(11).setCellValue(Bilancio.Contanti.Uscite.getMoneyEuro());// settare money (prima di settare il Value convertire 123.5 in 123,50 €)
                        sheet.getRow(row).getCell(11).setCellStyle(RIGHTCellStyle);
                        break;
                    case 6:
                        sheet.getRow(row).getCell(8).setCellStyle(ColorCONTANTI);
                        XSSFCellStyle style1 = (XSSFCellStyle) workbook.createCellStyle();
                        style1.cloneStyleFrom(LEFTCellStyle);
                        style1.setFont(BoldFont);
                        style1.setFillForegroundColor(new XSSFColor(new java.awt.Color(221, 235, 247)));
                        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        sheet.getRow(row).getCell(9).setCellStyle(style1);
                        sheet.getRow(row).getCell(9).setCellValue("Contanti");
                        XSSFCellStyle style2 = (XSSFCellStyle) workbook.createCellStyle();
                        style2.cloneStyleFrom(style1);
                        style2.setAlignment(HorizontalAlignment.RIGHT);
                        if(Bilancio.Contanti.getTotale() < 0){
                            Font font = workbook.createFont();
                            font.setBold(true);
                            font.setColor(IndexedColors.RED.getIndex());
                            style2.setFont(font);
                        }
                        sheet.getRow(row).getCell(11).setCellStyle(style2);
                        sheet.getRow(row).getCell(11).setCellValue(Bilancio.Contanti.getTotaleEuro());// settare money (prima di settare il Value convertire 123.5 in 123,50 €)
                        break;
                }
            } else if (row <= 9) {
                sheet.getRow(row).getCell(7).setCellStyle(ColorTOTALE);
                switch(row){
                    case 7:
                        sheet.getRow(row).getCell(8).setCellValue("+");
                        sheet.getRow(row).getCell(8).setCellStyle(SyleENTRATA);
                        sheet.getRow(row).getCell(9).setCellValue(Bilancio.Totale.Entrate.getNum());// numero entrate banca
                        sheet.getRow(row).getCell(9).setCellStyle(CENTERCellStyle);
                        sheet.getRow(row).getCell(10).setCellValue("Entrate");
                        sheet.getRow(row).getCell(10).setCellStyle(LEFTCellStyle);
                        sheet.getRow(row).getCell(11).setCellValue(Bilancio.Totale.Entrate.getMoneyEuro());// settare money (prima di settare il Value convertire 123.5 in 123,50 €)
                        sheet.getRow(row).getCell(11).setCellStyle(RIGHTCellStyle);
                        break;
                    case 8:
                        sheet.getRow(row).getCell(8).setCellValue("-");
                        sheet.getRow(row).getCell(8).setCellStyle(SyleUSCITA);
                        sheet.getRow(row).getCell(9).setCellValue(Bilancio.Totale.Uscite.getNum());// numero uscite banca
                        sheet.getRow(row).getCell(9).setCellStyle(CENTERCellStyle);
                        sheet.getRow(row).getCell(10).setCellValue("Uscite");
                        sheet.getRow(row).getCell(10).setCellStyle(LEFTCellStyle);
                        sheet.getRow(row).getCell(11).setCellValue(Bilancio.Totale.Uscite.getMoneyEuro());// settare money (prima di settare il Value convertire 123.5 in 123,50 €)
                        sheet.getRow(row).getCell(11).setCellStyle(RIGHTCellStyle);
                        break;
                    case 9:
                        sheet.getRow(row).getCell(8).setCellStyle(ColorTOTALE);
                        XSSFCellStyle style1 = (XSSFCellStyle) workbook.createCellStyle();
                        style1.cloneStyleFrom(LEFTCellStyle);
                        style1.setFont(BoldFont);
                        style1.setFillForegroundColor(new XSSFColor(new java.awt.Color(221, 235, 247)));
                        style1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        sheet.getRow(row).getCell(9).setCellStyle(style1);
                        sheet.getRow(row).getCell(9).setCellValue("Totale");
                        XSSFCellStyle style2 = (XSSFCellStyle) workbook.createCellStyle();
                        style2.cloneStyleFrom(style1);
                        style2.setAlignment(HorizontalAlignment.RIGHT);
                        if(Bilancio.Totale.getTotale() < 0){
                            Font font = workbook.createFont();
                            font.setBold(true);
                            font.setColor(IndexedColors.RED.getIndex());
                            style2.setFont(font);
                        }
                        sheet.getRow(row).getCell(11).setCellStyle(style2);
                        sheet.getRow(row).getCell(11).setCellValue(Bilancio.Totale.getTotaleEuro());// settare money (prima di settare il Value convertire 123.5 in 123,50 €)
                        break;
                }
            }
//            if(row != 3 || row != 6 || row != 9){
//                sheet.getRow(row).getCell(9).getCellStyle().setFillForegroundColor(IndexedColors.WHITE.getIndex());
//                sheet.getRow(row).getCell(9).getCellStyle().setFillPattern(FillPatternType.SOLID_FOREGROUND);
//                
//                sheet.getRow(row).getCell(10).getCellStyle().setFillForegroundColor(IndexedColors.WHITE.getIndex());
//                sheet.getRow(row).getCell(10).getCellStyle().setFillPattern(FillPatternType.SOLID_FOREGROUND);
//                
//                sheet.getRow(row).getCell(11).getCellStyle().setFillForegroundColor(IndexedColors.WHITE.getIndex());
//                sheet.getRow(row).getCell(11).getCellStyle().setFillPattern(FillPatternType.SOLID_FOREGROUND);
//            }
        }
    }
    
    //Prima di stampare la Tabella Bilancio, ottiene i valori da scrivere dalla Tabella Principale (dall'arraylist Obj)
    private void getBilancioFromTable() {
        //Bilancio bilancio = new Bilancio();
        Bilancio = new Bilancio();
        ListDatiObj.forEach((element) -> {
            if (element.getTipoBilancio().getValue().equalsIgnoreCase("Banca")) {
            // BANCA
                if(element.getTipo().getValue().equalsIgnoreCase("Uscita Merce") || element.getTipo().getValue().equalsIgnoreCase("Uscita Merce Sospesi")) {
                // if element is Uscita
                    Bilancio.Banca.Uscite.setNum(Bilancio.Banca.Uscite.getNum() + 1);
                    Bilancio.Banca.Uscite.setMoney(Bilancio.Banca.Uscite.getMoney() + Double.valueOf(element.getImporto().getValue().substring(0, element.getImporto().getValue().length() - 2)));
                } else {
                // if element is Entrata
                    Bilancio.Banca.Entrate.setNum(Bilancio.Banca.Entrate.getNum() + 1);
                    Bilancio.Banca.Entrate.setMoney(Bilancio.Banca.Entrate.getMoney() + Double.valueOf(element.getImporto().getValue().substring(0, element.getImporto().getValue().length() - 2)));
                }
                Bilancio.Banca.setTotale(Bilancio.Banca.Entrate.getMoney() - Bilancio.Banca.Uscite.getMoney());
            } else if (element.getTipoBilancio().getValue().equalsIgnoreCase("Contanti")) {
            // CONTANTI
                if(element.getTipo().getValue().equalsIgnoreCase("Uscita Merce") || element.getTipo().getValue().equalsIgnoreCase("Uscita Merce Sospesi")) {
                // if element is Uscita
                    Bilancio.Contanti.Uscite.setNum(Bilancio.Contanti.Uscite.getNum() + 1);
                    Bilancio.Contanti.Uscite.setMoney(Bilancio.Contanti.Uscite.getMoney() + Double.valueOf(element.getImporto().getValue().substring(0, element.getImporto().getValue().length() - 2)));
                } else {
                // if element is Entrata
                    Bilancio.Contanti.Entrate.setNum(Bilancio.Contanti.Entrate.getNum() + 1);
                    Bilancio.Contanti.Entrate.setMoney(Bilancio.Contanti.Entrate.getMoney() + Double.valueOf(element.getImporto().getValue().substring(0, element.getImporto().getValue().length() - 2)));
                }
                Bilancio.Contanti.setTotale(Bilancio.Contanti.Entrate.getMoney() - Bilancio.Contanti.Uscite.getMoney());
            }
        });
        // TOTALE
        // Entrate
        Bilancio.Totale.Entrate.setNum(Bilancio.Banca.Entrate.getNum() + Bilancio.Contanti.Entrate.getNum());
        Bilancio.Totale.Entrate.setMoney(Bilancio.Banca.Entrate.getMoney() + Bilancio.Contanti.Entrate.getMoney());
        // Uscite
        Bilancio.Totale.Uscite.setNum(Bilancio.Banca.Uscite.getNum() + Bilancio.Contanti.Uscite.getNum());
        Bilancio.Totale.Uscite.setMoney(Bilancio.Banca.Uscite.getMoney() + Bilancio.Contanti.Uscite.getMoney());
        // Totale
        Bilancio.Totale.setTotale(Bilancio.Banca.getTotale() + Bilancio.Contanti.getTotale());
        
        // Assegna(sovrascrivi) all'oggetto locale i valori ottenuti dalla tabella
        Bilancio = Bilancio;
        System.out.println("---------------------------------------");
        System.out.println("    TOTALE Banca: " + Bilancio.Banca.getTotale() + " // " + Bilancio.Banca.getTotaleEuro());
        System.out.println("    TOTALE Contanti: " + Bilancio.Contanti.getTotale() + " // " + Bilancio.Contanti.getTotaleEuro());
        System.out.println("    TOTALE Totale: " + Bilancio.Totale.getTotale()+ " // " + Bilancio.Totale.getTotaleEuro());
        System.out.println("---------------------------------------");
    }
    
    //Setta i valori del bilancio in-app
    private void setBilancioMoney(){
        Label_BilancioBanca.setText(Bilancio.Banca.getTotaleEuro());
        Label_BilancioContanti.setText(Bilancio.Contanti.getTotaleEuro());
        Label_BilancioTotale.setText(Bilancio.Totale.getTotaleEuro());
    }
    
    //Svuota la Tabella(asks the user)
    @FXML
    void resetTableContent(MouseEvent event){
        WindowBlackEffect(true);
        PanelModifica.setVisible(true);
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocumentElimina.fxml"));
                AnchorPane Pane = loader.load();
                FXMLDocumentEliminaController controller = (FXMLDocumentEliminaController) loader.getController();
                controller.setDisplayMessage("Sei sicuro di svuotare la tabella?","Una volta eliminate, le informazioni non potranno essere recuperate", "resetTable");
                PanelModifica.getChildren().setAll(Pane);
            } catch (IOException ex) {Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);}
        });

        System.out.println("\t\tListDatiObj lenght PRIMA: " + this.ListDatiObj.size() + " --> " + this.ListDatiObj.get(0).getData());
        
    }
    
    //Svuota il contenuto della Tabella e quindi dell' arraylist contenente i dati
    private void deleteTableContent(){
        this.root.getChildren().clear();
        ListDatiObj.clear();
        getBilancioFromTable();
        setBilancioMoney();
        Btn_resetTable.setDisable(true);
    }
    
    /**********************************
     *           <GRAFICI>            *
     **********************************/
    
    //Starts Here
    
    /****************************************
     *           <AGGIUNGI DATI>            *
     ****************************************/
    
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
    
    //in base all'item bilacio selezionato, imposta il tipo Bilancio. Che puo essere unico o si puo scegliere(banca, contanti)
    //and if itemselected != -1 set default style
    @FXML
    void setTipoBilancio(Event event) {
        String ItemSelected = Tipologia_CBox.getSelectionModel().getSelectedItem();
        if(Tipologia_CBox.getSelectionModel().getSelectedIndex() == -1){}
        else if(ItemSelected.equalsIgnoreCase("Incasso POS") || ItemSelected.equalsIgnoreCase("Assegno") || ItemSelected.equalsIgnoreCase("Deposito Banca")){
            TipoMoney_CBox.getSelectionModel().select("Banca");
            TipoMoney_CBox.setDisable(true);
        } else {
            TipoMoney_CBox.getSelectionModel().clearSelection();
            TipoMoney_CBox.setDisable(false);
        }
         //style Bilancio
        if(Tipologia_CBox.getSelectionModel().getSelectedIndex() != -1)
            Tipologia_CBox.setStyle("-jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
        //style TipoBilancio
        if(TipoMoney_CBox.getSelectionModel().getSelectedIndex() != -1)
            TipoMoney_CBox.setStyle("-jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
    }
    
    //if there is no item selected, color the field to red(error)
    @FXML
    void onSelectedTipoBilancio(Event event) {
        if(TipoMoney_CBox.getSelectionModel().getSelectedIndex() != -1)
            TipoMoney_CBox.setStyle("-jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
    }
    
    //if any char entered != [0-9], color the field to red(error)
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
            if(!isErrorInt) Importo_Int.setStyle("-fx-text-inner-color: BLACK; -jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
        }
    }
    
    //if any char entered != [0-9], color the field to red(error) 
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
            if(!isErrorCent) Importo_Cent.setStyle("-fx-text-inner-color: BLACK; -jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
        }
    }
    
    @FXML
    void onDescChanged(KeyEvent event) {
        if(Descrizione_TextArea.getText().length() >=0)
             Descrizione_TextArea.setStyle("-fx-prompt-text-fill: #000; -jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
    }
    
    @FXML
    void onCalendarShowing(ActionEvent   event) {
        if(Data_DPicker.getValue() != null){
        DataErrorLabel.setVisible(false);
        Data_DPicker.setPromptText("Seleziona una data");
        }
    }
    
    // When called there'll be added a new ObjDati and a new row in the table (SINGLE ROW)
    @FXML
    void AddElementTable(MouseEvent event) {
        boolean isErrorBilancio = false;
        boolean isErrorTypeBilancio = false;
        boolean isErrorDesc = false;
        boolean isErrorData = false;
        boolean isErrorInt = false;
        boolean isErrorCent = false;
        boolean isImportoZero = false;
        
        //CONTROLLA SE TUTTI I CAMPI != NULL
        /**/
        //BILANCIO
        if(Tipologia_CBox.getSelectionModel().getSelectedIndex() == -1){
            Tipologia_CBox.setStyle("-fx-prompt-text-fill: RED; -jfx-focus-color: RED; -jfx-unfocus-color: RED;");
            isErrorBilancio = true;
        }
        else
            Tipologia_CBox.setStyle("-jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
        //TIPO BILANCIO
        if(TipoMoney_CBox.getSelectionModel().getSelectedIndex() == -1){
            TipoMoney_CBox.setStyle("-fx-prompt-text-fill: RED; -fx-prompt-text-fill: RED; -jfx-focus-color: RED; -jfx-unfocus-color: RED;");
            isErrorTypeBilancio = true;
        }
        else
            TipoMoney_CBox.setStyle("-jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
        //DESCRIZIONE
        if(Descrizione_TextArea.getText().length() <= 0){
            Descrizione_TextArea.setStyle("-fx-prompt-text-fill: RED; -jfx-focus-color: RED; -jfx-unfocus-color: RED;");
            isErrorDesc = true;
        }
        else
            Descrizione_TextArea.setStyle("-fx-prompt-text-fill: #4d4d4d; -jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
        //DATA
        if(Data_DPicker.getValue() == null){
            DataErrorLabel.setVisible(true);
            Data_DPicker.setPromptText(null);
            isErrorData = true;
        }
        else{
            DataErrorLabel.setVisible(false);
            Data_DPicker.setPromptText("Seleziona una data");
        }
        //IMPORTO_INT
        try{
            int x;
            if(Importo_Int.getText().equals(""))Importo_Int.setText("0");
            else x = Integer.parseInt(Importo_Int.getText());
        }catch (NumberFormatException e) {
            Importo_Int.setStyle("-fx-text-inner-color: RED; -jfx-focus-color: RED; -jfx-unfocus-color: RED;");
            isErrorInt = true;
        }
        finally {
            if(!isErrorInt)Importo_Int.setStyle("-fx-text-inner-color: BLACK; -jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
        }
        //IMPORTO_CENT
        try{
            int x;
            if(Importo_Cent.getText().equals(""))Importo_Cent.setText("00");
            else x= Integer.parseInt(Importo_Cent.getText());
        }catch (NumberFormatException e) {
            Importo_Cent.setStyle("-fx-text-inner-color: RED; -jfx-focus-color: RED; -jfx-unfocus-color: RED;");
            isErrorCent = true;
        }
        finally {
            if(!isErrorCent)Importo_Cent.setStyle("-fx-text-inner-color: BLACK; -jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
        }
        //if Importo has only number and not general text
        if(!isErrorInt && !isErrorCent){
            //if Importo is == 0,00€ allora do error
            if(new Integer(Importo_Int.getText()) == 0 && new Integer(Importo_Cent.getText()) == 0){
                Importo_Int.setStyle("-fx-text-inner-color: RED; -jfx-focus-color: RED; -jfx-unfocus-color: RED;");
                Importo_Cent.setStyle("-fx-text-inner-color: RED; -jfx-focus-color: RED; -jfx-unfocus-color: RED;");
                isImportoZero = true;
            } else {
            Importo_Int.setStyle("-fx-text-inner-color: BLACK; -jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
            Importo_Cent.setStyle("-fx-text-inner-color: BLACK; -jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
            }
        }
        
        
        //SE TUTTI I CAMPI SONO RIEMPITI CORRETTAMENTE VAI AD AGGIORNARE I DATI
        if(!isErrorBilancio && !isErrorTypeBilancio && !isErrorDesc && !isErrorData && !isErrorInt && !isErrorCent && !isImportoZero){
            Dati element = new Dati();
            ImageView img;

            //assegnare ad ogni campo i corrispondenti valori inseriti dall'utente
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

            ListDatiObj.add(element);
            //aggiunge l'element (una riga) al Database
            ADDrow_Database(element.getTipo().getValue(),element.getTipoBilancio().getValue(),
                            element.getDescrizione().getValue(),element.getData().getValue(),
                            element.getImporto().getValue(), ID_Utente);
            cont++;//opzionale (poi da ELIMINARE, ora la uso per info)

            printDatiToTable("reverse");
            CreateNotification("Successful", "Dati aggiunti con successo.", new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-ok.png").toExternalForm())));
            clearFields("inserisci");
            getBilancioFromTable();
            setBilancioMoney();
            Btn_resetTable.setDisable(false);
            System.out.println("NUOVA RIGHA AGGIUNTA!\t\tListDatiObj lenght: " + ListDatiObj.size());
        }
    }
    
    //svuota i campi dell'inserimento dati
    private void clearFields(String method) {
        if(method.equals("inserisci")){
            //INSERISCI DATI SCREEN
            Tipologia_CBox.getSelectionModel().select(-1);
            TipoMoney_CBox.getSelectionModel().select(-1);
            if(TipoMoney_CBox.isDisabled()) TipoMoney_CBox.setDisable(false);
            Descrizione_TextArea.setText("");
            Data_DPicker.setValue(null);
            Importo_Int.setText("");
            Importo_Cent.setText("");
        } else if(method.equals("importa")){
            //IMPORTA DATI SCREEN
            FileExcelPath.setStyle("-fx-prompt-text-fill: #4d4d4d; -jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
            ProgressBar.setProgress(0);
            PBCompletedText.setVisible(false);
            PBErrorText.setVisible(false);
        }
    }
    
    //Apre FileChooser when button is clicked
    @FXML
    void OpenFileChooser(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);
        
        if (selectedFile != null) {
            //IMPORTA DATI SCREEN
            FileExcelPath.setText(selectedFile.getAbsolutePath());
            FileExcelPath.setStyle("-fx-prompt-text-fill: #4d4d4d; -jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
            //INSERISCI DATI SCREEN
            Tipologia_CBox.setStyle("-jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
            TipoMoney_CBox.setStyle("-jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
            Descrizione_TextArea.setStyle("-fx-prompt-text-fill: #4d4d4d; -jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
            DataErrorLabel.setVisible(false);
            Data_DPicker.setPromptText("Seleziona una data");
            Importo_Int.setStyle("-fx-text-inner-color: BLACK; -jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
            Importo_Cent.setStyle("-fx-text-inner-color: BLACK; -jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
            try{
                if(new Integer(Importo_Int.getText()) == 0 && new Integer(Importo_Cent.getText()) == 0){
                    //nothing
                }
                else {
                    Importo_Int.setStyle("-fx-text-inner-color: BLACK; -jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
                    Importo_Cent.setStyle("-fx-text-inner-color: BLACK; -jfx-focus-color: linear-gradient(135deg, #00b2ff 0%,#018ed0 50%,#0067bc 100%); -jfx-unfocus-color: #4d4d4d;");
                }
            }catch(Exception e){
                if(!Importo_Int.getText().equals("") && !Importo_Cent.getText().equals("")){
                    Importo_Int.setStyle("-fx-text-inner-color: RED; -jfx-focus-color: RED; -jfx-unfocus-color: RED;");
                    Importo_Cent.setStyle("-fx-text-inner-color: RED; -jfx-focus-color: RED; -jfx-unfocus-color: RED;");
                }
            }
        }
    }
    
    // When called there'll be added a new ObjDati_ArrayList and new rows in the table (.XLSX)(depends on how much data has the imported file)
    @FXML
    void ImportElementTable(MouseEvent event) throws InterruptedException, IOException{
        if(isImporting == false) {
            System.out.println("IMPORTING. . .");
            clearFields("importa");
            //if file NON SELEZIONATO
            if(FileExcelPath.getText().equalsIgnoreCase("")){
                FileExcelPath.setStyle("-fx-prompt-text-fill:RED; -fx-text-inner-color: RED; -jfx-focus-color: RED; -jfx-unfocus-color: RED;");
            } else if(!FileExcelPath.getText().equalsIgnoreCase("")){
            //if file SELEZIONATO
                selectedFile = new File(FileExcelPath.getText());
                //controllare se il FILE E' un file excel & SE E' compatibile
                if (selectedFile.exists() && selectedFile.isFile() && selectedFile.getPath().endsWith(".xlsx")) {
                    //se selectedFile == al file creato oggi && Table ha elementi
                    if (selectedFile.getName().equals(fileToday_Name) && !ListDatiObj.isEmpty()) {
                        CreateNotification("Attenzione!", "Non puoi importare lo stesso file su cui stai attualmente lavorando.", new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-warning.png").toExternalForm())));
                    }//se si tratta di un file non creato dal programma
                    else if(!selectedFile.getName().contains("Resoconto Entrate-Uscite [") || !selectedFile.getName().endsWith("].xlsx")) {
                        CreateNotification("Attenzione!", "Non puoi importare un file creato al di fuori del programma.", new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-warning.png").toExternalForm())));
                    }// se non ce nessun problema importa il file
                     else {
                        //SE SI(FILE.XLSX), allora leggi il file e fai scritta "operazione completata" (elimina campo seleziona file)
                        ProgressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
                        isImporting = true;
                        new Thread(() ->{
                            try{
                                Thread.sleep(2000);
                                fileSelected_Importing();// CARICAMENTO FILE
                                isImporting = false;
                                Btn_resetTable.setDisable(false);
                                Platform.runLater(new Runnable(){
                                    @Override
                                    public void run() {
                                        getBilancioFromTable();
                                        setBilancioMoney();
                                        // Animation ProgressBar & Notification
                                        ProgressBar.setProgress(1);
                                        PBCompletedText.setVisible(true);
                                        CreateNotification("Successful!", "File Importato con successo.", new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-ok.png").toExternalForm())));
                                    }
                                });
                            }catch(InterruptedException e){} catch(IOException ex){Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);}
                        }){{start();}};
                    }
                } else {
                    //SE NON E' un file .xlsx fai scritta "errore.File non compatibile." (NON eliminare campo seleziona file, ma evidenzialo di rosso)
                    ProgressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
                    isImporting = true;
                    new Thread(() ->{
                        try{
                            Thread.sleep(2000);
                            isImporting = false;
                            Platform.runLater(() -> {
                                // Animation ProgressBar & Notification
                                ProgressBar.setProgress(0);
                                PBErrorText.setVisible(true);
                                FileExcelPath.setStyle("-fx-prompt-text-fill:RED; -jfx-focus-color: RED; -jfx-unfocus-color: RED;");
                                CreateNotification("Errore!", "File non supportato o non esistente.", new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-error.png").toExternalForm())));
                            });
                        }catch(InterruptedException e){}
                    }){{start();}};
                }
            }
        } else {
            CreateNotification("Attenzione!", "Impossibile importare il file. . . Riprova a breve.", new ImageView(new Image(getClass().getResource("/gestione/Icons/icon-warning.png").toExternalForm())));
        }
    }
    
    //importa i dati dal file .xlsx alla tabella
    private void fileSelected_Importing() throws FileNotFoundException, IOException{
        FileInputStream fis = new FileInputStream(selectedFile);
        
        XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        Iterator<Row> rowIterator = mySheet.iterator();
        
        //Qui si memorizza la tabella del file .xlsx --> poi si reversa questa lista temporanea e la si aggiunge alla lista principale
        ArrayList<Dati> ListImported = new ArrayList<>();
        
        // Traversing over each row of XLSX file
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if(row.getRowNum() == 0) continue;
            
            Dati element = new Dati();
            ImageView img;
            Iterator<Cell> cellIterator = row.cellIterator();
            int numCol = 0;
            // For each row, iterate through each columns
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String cellValue = dataFormatter.formatCellValue(cell);
                XSSFCellStyle ColorStyle = myWorkBook.createCellStyle();
                ColorStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(0, 176, 240)));
                ColorStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                
                switch(cell.getColumnIndex()){
                    case 0:// element.setTypeMoney(...)(IMG) & element.setBilancio(...)
                        if(cell.getCellStyle().getFillForegroundColorColor().equals(new XSSFColor(new java.awt.Color(255, 192, 0)))){
                            //if colorCell is Yellow
                            img = new ImageView(new Image(getClass().getResource("/gestione/Icons/Type_Contanti.png").toExternalForm()));
                            img.setTranslateX(9);
                            element.setTypeMoney(img);
                            element.setTipoBilancio("Contanti");
                        } else {//else if colorCell is Blue
                            img = new ImageView(new Image(getClass().getResource("/gestione/Icons/Type_Banca.png").toExternalForm()));
                            img.setTranslateX(9);
                            element.setTypeMoney(img);
                            element.setTipoBilancio("Banca");
                        }
                        break;
                    case 1:// element.setTipo(...)
                        element.setTipo(cellValue.toUpperCase());
                        break;
                    case 2:// element.setDescrizione(...)
                         element.setDescrizione(cellValue);
                        break;
                    case 3:// element.setData(...)
                        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
//                        LocalDate dateTime = cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();//LocalDate.parse(cellValue, formatter);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate dateTime = LocalDate.parse(cellValue, formatter);
                        element.setData(dateTime);
                        break;
                    case 4:// element.setImporto(...)
                        element.setImporto(Double.valueOf(cellValue.substring(0, cellValue.length()-1)));
                        break;
                    case 5:// element.setArrows(...); (IMG)
                        if(cellValue.equals("+")){
                            img = new ImageView(new Image(getClass().getResource("/gestione/Icons/Arrow_green.png").toExternalForm()));
                            img.setTranslateX(0);
                            element.setArrows(img);
                        } else {
                            img = new ImageView(new Image(getClass().getResource("/gestione/Icons/Arrow_red.png").toExternalForm()));
                            img.setTranslateX(0);
                            element.setArrows(img);
                        }
                        break;
                    default:
                        //DON'T DO ANYTHING
                        break;
                }
                numCol++;
            }
            //if row contiene Dati Tabella, aggiungi element alla Lista (se contiene solo dati Bilancio, non aggiungere element)
            if (numCol > 5) ListImported.add(element);
        }
        //reverse the current dataList imported into the ArrayList Obj
        Collections.reverse(ListImported);
        for (int i = 0; i < ListImported.size(); i++) {
            ListDatiObj.add(ListImported.get(i));
        }
        
        System.out.println("ListDatiObj elements --> " + ListDatiObj.size());
        printDatiToTable("reverse");
    }
    
    /***NOT USING THIS***/
    //Aggiunge listener al Pane sotto ProgressBar. Esegue codice ogni volta che il Pane e' Visible e !Visible
    private void AddListener_StatusFileLoaded() {
        PBCompletedText.visibleProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println("hey hey hey");
                if (newValue == false) {
                    // not showing...
                    System.out.println("NOT SHOWING");
                } else {
                    // showing ...
                    System.out.println("SHOWING");
                }
            }
        });
        PBErrorText.visibleProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println("hey hey hey");
                if (newValue == false) {
                    // not showing...
                    System.out.println("NOT SHOWING");
                } else {
                    // showing ...
                    System.out.println("SHOWING");
                }
            }
        });
    }
    
    /*continue here*/

    
}
