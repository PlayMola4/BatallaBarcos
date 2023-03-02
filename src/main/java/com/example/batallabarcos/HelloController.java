package com.example.batallabarcos;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelloController {

    @FXML
    private AnchorPane ventana;
    @FXML
    private AnchorPane anchorUSA;
    @FXML
    private AnchorPane anchorES;
    @FXML
    private AnchorPane globalMark;

    Barcos lanchaES;
    Barcos acorazadoES;
    Barcos destructorES;
    Barcos submarinoES;

    Barcos lanchaUSA;
    Barcos acorazadoUSA;
    Barcos destructorUSA;
    Barcos submarinoUSA;

    ControlDeJuego control;
    MediaPlayer mediaPlayer;
    List<Integer> posicionBarco = new ArrayList<>();

    public void initialize(){
        Image fondo = new Image(getClass().getResourceAsStream("imagenes/water.jpg"));
        ImageView back = new ImageView(fondo);
        ventana.setBackground(new Background(new BackgroundImage(back.getImage(), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));
        iniciarMusica();
        creacionBarcos();
    }

    public void creacionBarcos(){
        posicionBarco.add(1);
        posicionBarco.add(2);
        posicionBarco.add(3);
        posicionBarco.add(4);

        Collections.shuffle(posicionBarco);
        control = new ControlDeJuego();

        //Destructor Azul
        /*ImageView imagenDestructor = new ImageView();
        imagenDestructor.setImage(new Image(getClass().getResourceAsStream("")));*/

        //Lancha España
        ImageView imagenLancha = new ImageView();
        imagenLancha.setImage(new Image(getClass().getResourceAsStream("imagenes/destructorRojo.png")));
        posicionRojo(imagenLancha, posicionBarco.remove(0));
        control.aniadirBarco(lanchaES = new Barcos("lancha", "Rojo", imagenLancha, control.getBarcos(), ventana));

        //Acorazado España
        ImageView imagenAcorazado = new ImageView();
        imagenAcorazado.setImage(new Image(getClass().getResourceAsStream("imagenes/destructorRojo.png")));
        posicionRojo(imagenAcorazado, posicionBarco.remove(0));
        control.aniadirBarco(acorazadoES = new Barcos("acorazado", "Rojo", imagenAcorazado, control.getBarcos(), ventana));

        //Destructor España
        ImageView imagenDestructor = new ImageView();
        imagenDestructor.setImage(new Image(getClass().getResourceAsStream("imagenes/destructorRojo.png")));
        posicionRojo(imagenAcorazado,posicionBarco.remove(0));
        control.aniadirBarco(destructorES = new Barcos("destructor", "Rojo", imagenDestructor, control.getBarcos(), ventana));

        //Submarino España
        ImageView imagenSubmarino = new ImageView();
        imagenSubmarino.setImage(new Image(getClass().getResourceAsStream("imagenes/destructorRojo.png")));
        posicionRojo(imagenSubmarino, posicionBarco.remove(0));
        control.aniadirBarco(submarinoES = new Barcos("submarino", "Rojo", imagenSubmarino, control.getBarcos(), ventana));

        posicionBarco.add(1);
        posicionBarco.add(2);
        posicionBarco.add(3);
        posicionBarco.add(4);
        Collections.shuffle(posicionBarco);

        //Lancha USA
        ImageView imagenLancha2 = new ImageView();
        imagenLancha2.setImage(new Image(getClass().getResourceAsStream("imagenes/destructorAzul.png")));
        posicionAzul(imagenLancha2, posicionBarco.remove(0));
        control.aniadirBarco(lanchaUSA = new Barcos("lancha", "Azul", imagenLancha2, control.getBarcos(), ventana));

        //Acorazado USA
        ImageView imagenAcorazado2 = new ImageView();
        imagenAcorazado2.setImage(new Image(getClass().getResourceAsStream("imagenes/destructorAzul.png")));
        posicionAzul(imagenAcorazado2, posicionBarco.remove(0));
        control.aniadirBarco(acorazadoUSA = new Barcos("acorazado", "Azul", imagenAcorazado2, control.getBarcos(), ventana));

        //Destructor USA
        ImageView imagenDestructor2 = new ImageView();
        imagenDestructor2.setImage(new Image(getClass().getResourceAsStream("imagenes/destructorAzul.png")));
        posicionAzul(imagenAcorazado2,posicionBarco.remove(0));
        control.aniadirBarco(destructorUSA = new Barcos("destructor", "Azul", imagenDestructor2, control.getBarcos(), ventana));

        //Submarino USA
        ImageView imagenSubmarino2 = new ImageView();
        imagenSubmarino2.setImage(new Image(getClass().getResourceAsStream("imagenes/destructorAzul.png")));
        posicionAzul(imagenSubmarino2, posicionBarco.remove(0));
        control.aniadirBarco(submarinoUSA = new Barcos("submarino", "Azul", imagenSubmarino2, control.getBarcos(), ventana));


        ventana.getChildren().addAll(lanchaES.getImagen(), acorazadoES.getImagen(), destructorES.getImagen(), submarinoES.getImagen(),
                                    lanchaUSA.getImagen(), acorazadoUSA.getImagen(), destructorUSA.getImagen(), submarinoUSA.getImagen());



        control.finDeJuego();

    }

    public void posicionRojo(ImageView imagen, int num){
        if(num==1){
            imagen.setLayoutX(28);
            imagen.setLayoutY(371);
        }
        if(num==2){
            imagen.setLayoutX(28);
            imagen.setLayoutY(75);
        }
        if(num==3){
            imagen.setLayoutX(28);
            imagen.setLayoutY(149);
        }
        if(num==4){
            imagen.setLayoutX(28);
            imagen.setLayoutY(575);
        }
    }

    public void posicionAzul(ImageView imagen, int num) {

        if (num == 1) {
            imagen.setLayoutX(882);
            imagen.setLayoutY(371);
        }

        if (num == 2) {
            imagen.setLayoutX(876);
            imagen.setLayoutY(75);
        }

        if (num == 3) {
            imagen.setLayoutX(876);
            imagen.setLayoutY(147);
        }

        if (num == 4) {
            imagen.setLayoutX(876);
            imagen.setLayoutY(575);
        }

    }

    public void iniciarMusica(){
        Platform.runLater(()->{
            Media pick = new Media(this.getClass().getResource("Sonidos/musicaFondo.mp3").toString());
            mediaPlayer = new MediaPlayer(pick);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(0.45);
            mediaPlayer.play();
        });
    }

}