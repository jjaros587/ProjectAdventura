/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import logika.Hra;
import logika.IHra;
import main.Main;

/**
 *
 * @author Acer
 */
public class MenuLista extends MenuBar{
    
    private IHra hra;
    private Main main;
    
    public MenuLista(IHra hra, Main main){
        this.hra = hra;
        this.main = main;
        init();
        
    }
    
    private void init(){
                  
            Menu hraMenu = new Menu("Hra");
            
                MenuItem itemNova = new MenuItem("Nová hra");
                //, new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/ikona.png"))
                itemNova.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
                
                MenuItem itemKonec = new MenuItem("Konec hry");
                
            hraMenu.getItems().addAll(itemNova, itemKonec);
                
                itemNova.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        hra = new Hra();
                        main.getMapa().newGame(hra);
                        main.setHra(hra);
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
            
        this.getMenus().addAll(hraMenu, napovedaMenu);
        
    }
}
