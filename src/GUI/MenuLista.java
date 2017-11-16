/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logika.Hra;
import logika.IHra;
import main.Main;

/**
 *
 * @author Jakub Jaroš
 * ZS 2017
 */
public class MenuLista extends MenuBar{
    
    private IHra hra;
    private Main main;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param hra 
    *  @param main
    */ 
    public MenuLista(IHra hra, Main main){
        this.hra = hra;
        this.main = main;
        init();
        
    }
    /**
    *  Úvodní nastavení menu
    *  
    */
    private void init(){
                  
            Menu hraMenu = new Menu("Hra");           
                MenuItem itemNova = new MenuItem("Nová hra");
                itemNova.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
                
                MenuItem itemKonec = new MenuItem("Konec hry");                
            hraMenu.getItems().addAll(itemNova, itemKonec);
                
                itemNova.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        hra = new Hra();
                        main.setHra(hra);
                        main.getMapa().novaHra(hra);
                        main.getInfo().novaHra(hra);
                        main.getSeznamVychodu().novaHra(hra);
                        main.getVeciVBatohu().novaHra(hra);
                        main.getVeciVProstoru().novaHra(hra);
                        main.getPostavyVProstoru().novaHra(hra);
                        main.getPrikazy().novaHra(hra);
                        main.getCentralText().setText(hra.vratUvitani());
                    }
                });     

                itemKonec.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        System.exit(0);
                    }
                });
            
                
            Menu napovedaMenu = new Menu("Nápověda");
            
                MenuItem oProgramu = new MenuItem("O programu");
                    oProgramu.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Alert oProgramuAlert = new Alert(Alert.AlertType.INFORMATION);
                            oProgramuAlert.setTitle("O Programu");
                            oProgramuAlert.setHeaderText("Super adventura");
                            oProgramuAlert.setContentText("Toto je hra o mladíkovi, který chce získat dědictví po svém zemřelém otci.");
                            oProgramuAlert.initOwner(main.getStage());
                            oProgramuAlert.showAndWait();
                        }
                    }); 
                    
                MenuItem napoveda = new MenuItem("Nápověda");
                    napoveda.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Stage stage = new Stage();
                            stage.setTitle("Nápověda");
                            WebView webView = new WebView();
                            webView.getEngine().load(Main.class.getResource("/zdroje/napoveda.html").toExternalForm());
                            stage.setScene(new Scene(webView, 850, 750));
                            stage.show();
                        }
                    }); 
            napovedaMenu.getItems().addAll(oProgramu, napoveda);
            
        this.getMenus().addAll(hraMenu, napovedaMenu);       
    }
}
