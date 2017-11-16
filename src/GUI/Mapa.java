/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import logika.IHra;
import main.Main;
import utils.Observer;

/**
 *
 * @author Jakub Jaroš
 * @version ZS 2017
 */
public class Mapa extends AnchorPane implements Observer{
    
    private IHra hra;
    private Circle tecka;
    private ImageView obrazekImageView;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param hra 
    */ 
    public Mapa (IHra hra){
        this.hra = hra;
        hra.getHerniPlan().registerObserver(this);
        init();
    }
    /**
    *  Úvodní nastavení mapy hry
    *  
    */ 
    private void init(){

        tecka = new Circle(10, Paint.valueOf("black"));
        
        obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa1.png"),400,600,false,true));
        
        this.getChildren().addAll(obrazekImageView, tecka);
        update();
    }
    /**
     * Restartování adventury
     *
     * @param hra Nová hra
     */
    public void novaHra(IHra novaHra){
        hra.getHerniPlan().removeObserver(this);
        hra = novaHra;
        hra.getHerniPlan().registerObserver(this);
        update();
    }
    /**
    *  Update mapy hry a pohybu po mapě
    *  
    */
    @Override
    public void update() {
                    
        if(hra.getHerniPlan().vyberProstor("jeskyne").isViditelna()){
            
            this.getChildren().clear();

            obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/mapa2.png"),400,600,false,true));
            this.getChildren().addAll(obrazekImageView, tecka);
        }
     
        this.setTopAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosTop() + 10);
        this.setLeftAnchor(tecka, hra.getHerniPlan().getAktualniProstor().getPosLeft() + 20);       
    }   
}
