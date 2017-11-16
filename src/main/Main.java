/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import GUI.InfoOHraci;
import GUI.Mapa;
import GUI.MenuLista;
import GUI.PostavyVProstoru;
import GUI.SeznamPrikazu;
import GUI.SeznamVychodu;
import GUI.VeciVBatohu;
import GUI.VeciVProstoru;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

/**
 *
 * @author Jakub Jaroš
 */
public class Main extends Application {  
  
    private Stage primaryStage;
    private TextArea centralText = new TextArea();
    private IHra hra;
    private TextField zadejPrikazTextField = new TextField();
    private Mapa mapa;
    private MenuLista menu;
    private InfoOHraci info;
    private SeznamVychodu seznamVychodu;
    private VeciVBatohu veciVBatohu;
    private VeciVProstoru veciVProstoru;
    private PostavyVProstoru postavyVProstoru;
    private SeznamPrikazu prikazy;

    /**
     * Metoda slouží ke spuštění grafického rozhraní hry
     * a nastavení základního layoutu
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        hra             = new Hra();
        mapa            = new Mapa(hra);
        menu            = new MenuLista(hra, this);
        info            = new InfoOHraci(hra);
        seznamVychodu   = new SeznamVychodu(hra, centralText);
        veciVBatohu     = new VeciVBatohu(hra, centralText);
        veciVProstoru   = new VeciVProstoru(hra, centralText);
        postavyVProstoru= new PostavyVProstoru(hra, centralText);
        prikazy         = new SeznamPrikazu(hra, centralText);

        
        BorderPane borderPane = new BorderPane();
        
        // Text s prubehem hry
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);
        //------------------------------------------------------------------
        // Bottom - Prikazy
        Label zadejPrikazLabel = new Label("Zadej prikaz: ");
        zadejPrikazLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        
        zadejPrikazTextField.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                String vstupniPrikaz = zadejPrikazTextField.getText();
                String odpovedHry = hra.zpracujPrikaz(vstupniPrikaz);
                centralText.appendText("\n" + vstupniPrikaz + "\n");
                centralText.appendText("\n" + odpovedHry + "\n");
                
                zadejPrikazTextField.setText("");
                
                if(hra.konecHry()){
                    zadejPrikazTextField.setEditable(false);
                    centralText.appendText(hra.vratEpilog());
                }
            }
        });
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel,zadejPrikazTextField);
        
        //------------------------------------------------
        borderPane.setCenter(centralText);
        borderPane.setTop(menu);
        borderPane.setLeft(levyPanel());
        borderPane.setRight(pravyPanel());
        borderPane.setBottom(dolniLista);
              
        Scene scene = new Scene(borderPane, 1500, 1000);

        primaryStage.setTitle("Adventura");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        zadejPrikazTextField.requestFocus();
    }  
    /**
     * Metoda slouží k nastavení levého panelu hry
     *
     */
    private BorderPane levyPanel(){
             
        BorderPane vychody = new BorderPane();
        Label textSeznamVychodu = new Label("\nSeznam východů:");
        textSeznamVychodu.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vychody.setTop(textSeznamVychodu);
        vychody.setCenter(seznamVychodu);
        
        BorderPane seznamPrikazu = new BorderPane();
        seznamPrikazu.setPrefHeight(50);
        Label textSeznamPrikazu = new Label("\nSeznam příkazů:");
        textSeznamPrikazu.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        seznamPrikazu.setTop(textSeznamPrikazu);
        seznamPrikazu.setCenter(prikazy);
       
        BorderPane levyPanel = new BorderPane();
        levyPanel.setTop(mapa);
        levyPanel.setCenter(seznamPrikazu);
        levyPanel.setBottom(vychody);
        
        return levyPanel;
    }
    /**
     * Metoda slouží k nastavení pravého panelu hry
     *
     */
    private BorderPane pravyPanel(){
        
        // TOP       
        BorderPane hrac = new BorderPane();
        hrac.setPrefWidth(425);
        
            BorderPane infoOHraci = new BorderPane();
            Label textInfoOHraci = new Label("Info o hráči:");
            textInfoOHraci.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            infoOHraci.setTop(textInfoOHraci);
            infoOHraci.setCenter(info);

            BorderPane seznamVeciVBatohu = new BorderPane();
            Label textVeciVBatohu = new Label("\nSeznam věcí v batohu:");
            textVeciVBatohu.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            seznamVeciVBatohu.setTop(textVeciVBatohu);
            seznamVeciVBatohu.setCenter(veciVBatohu);
        
        hrac.setTop(infoOHraci);
        hrac.setCenter(seznamVeciVBatohu);
        //--------------------------------------------
        // CENTER    
        BorderPane seznamVeciVProstoru = new BorderPane();
        Label textveciVProstoru = new Label("Seznam věcí v prostoru:");
        textveciVProstoru.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        seznamVeciVProstoru.setTop(textveciVProstoru);
        seznamVeciVProstoru.setCenter(veciVProstoru); 
        //--------------------------------------------
        // BOTTOM
        BorderPane seznamPostavVProstoru = new BorderPane();
        Label textPostavVProstoru = new Label("Seznam postav:");
        textPostavVProstoru.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        seznamPostavVProstoru.setTop(textPostavVProstoru);
        seznamPostavVProstoru.setBottom(postavyVProstoru);      
        //---------------------------------------------------------------
        
        BorderPane pravyPanel = new BorderPane();
        pravyPanel.setTop(hrac);
        pravyPanel.setCenter(seznamVeciVProstoru);
        pravyPanel.setBottom(seznamPostavVProstoru);
        return pravyPanel;
    }
    /**
     * Vrací odkaz na mapu prostorů
     *
     * @return mapa
     */
    public Mapa getMapa() {
        return mapa;
    }
    /**
     * Metoda nastavuje hru
     *
     * @param instance hry
     */
    public void setHra(IHra hra) {
        this.hra = hra;
    }   
    /**
     * Vrací odkaz na primární stage
     *
     * @return primaryStage
     */
    public Stage getStage() {
        return primaryStage;
    }  
    /**
     * Vrací odkaz na pole s textem o průběhu hry
     *
     * @return pole s průběhem hry
     */
    public TextArea getCentralText() {
        return centralText;
    }
    /**
     * Vrací odkaz na objekt s informacemi o hráči
     *
     * @return informace o hráči
     */
    public InfoOHraci getInfo() {
        return info;
    }
    /**
     * Vrací odkaz na objekt se seznamem východů
     *
     * @return seznam východů
     */
    public SeznamVychodu getSeznamVychodu() {
        return seznamVychodu;
    }
    /**
     * Vrací odkaz objekt se seznamem věcí v batohu
     *
     * @return věcí v batohu
     */
    public VeciVBatohu getVeciVBatohu() {
        return veciVBatohu;
    }
    /**
     * Vrací odkaz na objekt se seznamem věcí v prostoru
     *
     * @return věci v prostoru
     */
    public VeciVProstoru getVeciVProstoru() {
        return veciVProstoru;
    }
    /**
     * Vrací odkaz na objekt se seznamem postav v prostoru
     *
     * @return postavy v prostoru
     */
    public PostavyVProstoru getPostavyVProstoru() {
        return postavyVProstoru;
    }
    /**
     * Vrací odkaz na objekt se seznamem příkazů
     *
     * @return seznam příkazů
     */
    public SeznamPrikazu getPrikazy() {
        return prikazy;
    }   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            launch(args);
        }
        else{
            if (args[0].equals("-txt")) {
                IHra hra = new Hra();
                TextoveRozhrani textHra = new TextoveRozhrani(hra);
                textHra.hraj();
            }
            else{
                System.out.println("Neplatny parametr");
                System.exit(1);
            }
        }
    }
}
