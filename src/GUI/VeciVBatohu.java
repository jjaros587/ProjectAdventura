/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import utils.Observer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import logika.IHra;
import main.Main;

/**
 * Třída VeciVInventari vytváří seznam obrázků vecí, které jsou v inventáři.
 * Aktualizuje se při nové hře, přejití do jiného prostoru a sebrání/položení
 * věci.
 *
 * @author Jakub Jaroš
 * @version ZS 2017
 */
public class VeciVBatohu extends GridPane implements Observer {

    public IHra hra;
    public TextArea centralText;

    /**
    *  Konstruktor třídy
    *  
    *  @param hra 
    *  @param centralText
    */ 
    public VeciVBatohu(IHra hra, TextArea centralText) {
        this.hra = hra;
        this.centralText = centralText;
        hra.getBatoh().registerObserver(this);
        init();
        update();
    }

    /**
     * Restartování adventury
     *
     * @param hra Nová hra
     */
    public void novaHra(IHra hra) {
        hra.getBatoh().removeObserver(this);
        this.hra = hra;
        hra.getBatoh().registerObserver(this);
        update();
    }

    /**
     * Update seznamu věcí v batohu
     */
    @Override
    public void update() {
        this.getChildren().clear();
        int i = 0;
        for (String vec : hra.getBatoh().getVeciVBatohu()) {


            String nazev = "/zdroje/" + vec + ".jpg";
            
            ImageView imageView = new ImageView(new Image(Main.class.getResourceAsStream(nazev), 50, 50, false, false));
            

            imageView.setOnMouseClicked(event -> {
                
                String prikaz = "vyhod " + vec;
                centralText.appendText(prikaz);
                String odpoved = hra.zpracujPrikaz(prikaz);
                centralText.appendText("\n\n" + odpoved + "\n");
            });

            this.add((imageView), i, 0);
            i++;
        }
    }

    /**
     * Úvodní nastavení seznamu věcí v batohu
     */
    private void init() {
        this.setPrefWidth(170);
        this.setPrefHeight(150);
        
    }
}
