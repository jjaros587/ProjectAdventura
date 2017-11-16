/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.control.TextArea;
import logika.IHra;
import utils.Observer;

/**
 *
 * @author Jakub Jaroš
 * @version ZS 2017
 */
public class InfoOHraci extends TextArea implements Observer{
    
    private IHra hra;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param hra 
    */ 
    public InfoOHraci(IHra hra){
        this.hra = hra;
        hra.getHrac().registerObserver(this);
        init();       
    }
    
    /**
     * Restartování adventury
     *
     * @param hra Nová hra
     */
    public void novaHra(IHra hra) {
        hra.getHrac().removeObserver(this);
        this.hra = hra;
        hra.getHrac().registerObserver(this);
        update();
    }
    /**
     * Úvodní nastavení pole s informacemi o hráči
     */
    private void init(){
        this.setPrefWidth(200);
        this.setPrefHeight(150);
        this.setEditable(false);  
        update();
    }
    /**
     * Update obsahu pole s informacemi o hráči
     */
    @Override
    public void update() {
        
        this.setText(hra.getHrac().getInfoOHraci());
        
             
    }    
}
