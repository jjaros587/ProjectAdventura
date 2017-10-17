/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

/**
 *
 * @author xzenj02
 */
public class Main extends Application {  
    
    private TextArea centralText = new TextArea();
    private IHra hra = new Hra();
    private TextField zadejPrikazTextField = new TextField();

    @Override
    public void start(Stage primaryStage) {
        
        BorderPane borderPane = new BorderPane();
        
        // Text s prubehem hry
        centralText.setText(hra.vratUvitani());
        centralText.setEditable(false);
        borderPane.setCenter(centralText);
        
        //-------Bottom-----------
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
        // Obrazek s mapou
        FlowPane obrazekFlowPane = new FlowPane();
        obrazekFlowPane.setPrefSize(200,200);
        ImageView obrazekImageView = new ImageView(new Image(Main.class.getResourceAsStream("/zdroje/PlanHry2.jpg"),300,200,false,true));
        obrazekFlowPane.setAlignment(Pos.CENTER);
        obrazekFlowPane.getChildren().add(obrazekImageView);
        
        
        FlowPane dolniLista = new FlowPane();
        dolniLista.setAlignment(Pos.CENTER);
        dolniLista.getChildren().addAll(zadejPrikazLabel,zadejPrikazTextField);
        
        borderPane.setLeft(obrazekFlowPane);
        borderPane.setBottom(dolniLista);
              
        Scene scene = new Scene(borderPane, 1000, 600);

        primaryStage.setTitle("Adventura");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        zadejPrikazTextField.requestFocus();
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
