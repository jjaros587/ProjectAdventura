/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.FlowPane;
import logika.Batoh;
import logika.IHra;
import utils.Observer;

/**
 *
 * @author Jakub Jaroš
 * @version ZS 2017
 */
public class SeznamPrikazu extends FlowPane implements Observer{
    
    private IHra hra;
    private TextArea centralText;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param hra 
    *  @param centralText
    */ 
    public SeznamPrikazu (IHra hra, TextArea centralText){
        this.hra = hra;
        this.centralText = centralText;
        hra.getHerniPlan().registerObserver(this);
        init();
    }
    /**
    *  Úvodní nastavení seznamu příkazů
    *  
    */
    private void init(){
        this.setPrefWidth(100);
        this.setPrefHeight(300);
        update();
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
    *  Update seznamu příkazů
    *  
    */
    @Override
    public void update() {
        
        this.getChildren().clear();
        
        String aktualniProstor = hra.getHerniPlan().getAktualniProstor().getNazev();
        Batoh batoh = hra.getBatoh();
        
        if(aktualniProstor.equals("hospoda")){
            Button buttonPivo = new Button("Pivo");
                buttonPivo.setMaxWidth(Double.MAX_VALUE);
                this.getChildren().add(buttonPivo);
                
                buttonPivo.setOnAction((ActionEvent event) -> {
                    centralText.appendText("\n pivo");
                    String odpoved = hra.zpracujPrikaz("pivo");
                    centralText.appendText("\n\n" + odpoved + "\n");
            });
        } 
        if(aktualniProstor.equals("kovarna") & batoh.obsahujeVec("mec")){
            Button buttonNabrousit = new Button("Nabrousit meč");
                buttonNabrousit.setMaxWidth(Double.MAX_VALUE);
                this.getChildren().add(buttonNabrousit);
                
                buttonNabrousit.setOnAction((ActionEvent event) -> {
                    centralText.appendText("\n Nabrousit mec");
                    String odpoved = hra.zpracujPrikaz("nabrousit mec");
                    centralText.appendText("\n\n" + odpoved + "\n");
            });
        }
        if(hra.getBatoh().obsahujeVec("lano")){
            
            Button lano = new Button("Natáhnout lano");
                lano.setMaxWidth(Double.MAX_VALUE);
                this.getChildren().add(lano);
                
                lano.setOnAction((ActionEvent event) -> {
                    
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setTitle("Lano");
                    dialog.setHeaderText("Natáhnutí lana");
                    dialog.setContentText("Zadej prostor, kam natáhnout lano:");

                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()){
                        centralText.appendText("\n lano " + result.get());
                        String odpoved = hra.zpracujPrikaz("lano " + result.get());
                        centralText.appendText("\n\n" + odpoved + "\n");
                    }     
            });           
        }
    }    
}
