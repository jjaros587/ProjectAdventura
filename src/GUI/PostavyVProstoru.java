/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import logika.IHra;
import logika.Postava;
import logika.Prostor;
import utils.Observer;
/**
 *
 * @author Jakub Jaroš
 * @version ZS 2017
 */
public class PostavyVProstoru extends ListView implements Observer{
    
    private IHra hra;
    private TextArea centralText;
    ObservableList<BorderPane> seznamPostav;
        
    /**
    *  Konstruktor třídy
    *  
    *  @param hra 
    *  @param centralText
    */ 
    public PostavyVProstoru(IHra hra, TextArea centralText){
        this.hra = hra;
        this.centralText = centralText;
        hra.getHerniPlan().registerObserver(this);
        
        init();  
        update();
    }
    /**
    *  Úvodní nastavení seznamu postav v prostoru
    *  
    */
    private void init(){
        seznamPostav = FXCollections.observableArrayList();
        this.setItems(seznamPostav);
        this.setPrefWidth(150);
        this.setPrefHeight(250);
        
    }    
    /**
     * Restartování adventury
     *
     * @param hra Nová hra
     */
    public void novaHra(IHra hra) {
        hra.getHerniPlan().removeObserver(this);
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
    /**
    *  Update seznamu postav v prostoru
    *  
    */
    @Override
    public void update() {
        seznamPostav.clear();
        Prostor prostor = hra.getHerniPlan().getAktualniProstor();
        
        BorderPane oknoPostavy = new BorderPane();
        oknoPostavy.setPrefWidth(225);
        FlowPane prikazy = new FlowPane();
               
        for (String postavaJmeno : prostor.getPostavy()) {
            
            Postava postava = prostor.vyberPostavu(postavaJmeno);
                       
            Label jmeno = new Label(postavaJmeno);
            oknoPostavy.setTop(jmeno);
                        
            if(postava.jdeZabit()){
                
                TextArea infoOPostave = new TextArea();
                infoOPostave.setPrefHeight(85);
                
                String text = hra.zpracujPrikaz("info " + postavaJmeno);
                infoOPostave.setText(text); 
                oknoPostavy.setCenter(infoOPostave);
                
                if(postava.getZije()){                   
                    Button buttonZabit = new Button("Zabít");
                    buttonZabit.setOnAction((ActionEvent event) -> {
                        String prikaz = "zabij " + postavaJmeno;
                        centralText.appendText("\n" + prikaz);
                        String odpoved = hra.zpracujPrikaz(prikaz);
                        centralText.appendText("\n\n" + odpoved + "\n");
                    });      

                    prikazy.getChildren().add(buttonZabit);
                }
            }
            
            if(postava.getJmeno().equals("trpaslik")){
                Button buttonMluv = new Button("Mluv");
                buttonMluv.setOnAction((ActionEvent event) -> {
                    String prikaz = "mluv " + postavaJmeno;
                    centralText.appendText("\n" + prikaz);
                    String odpoved = hra.zpracujPrikaz(prikaz);
                    centralText.appendText("\n\n" + odpoved + "\n");
                });    
                prikazy.getChildren().add(buttonMluv);
            }
            
            if(postava.getLzeDat()){
                Button buttonDat = new Button("Dát");
                buttonDat.setOnAction((ActionEvent event) -> {
                    
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Dej");
                    dialog.setHeaderText("Předání přemětu");
                    dialog.setContentText("Zadej věc, kterou chceš " + postavaJmeno + "dát:");

                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()){
                        
                        String prikaz = "dej " + result.get() + " " + postavaJmeno;
                        centralText.appendText("\n" + prikaz);
                        String odpoved = hra.zpracujPrikaz(prikaz);
                        centralText.appendText("\n\n" + odpoved + "\n");
                    }     
               });    
                prikazy.getChildren().add(buttonDat);
            }
            
            oknoPostavy.setBottom(prikazy);
            
            seznamPostav.add(oknoPostavy);
            
        }            
    }    
}
