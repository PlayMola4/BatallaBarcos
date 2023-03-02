package com.example.batallabarcos;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class ControlDeJuego {
    //MediaPlayer mediaPlayer;
    String nombreGanador = "";
    Timeline ganador;
    DialogPane dialogGanador;
    ArrayList<Barcos> listaBarcos;

    public ControlDeJuego(){
        listaBarcos = new ArrayList<Barcos>();
        dialogGanador = new DialogPane();
    }

    public synchronized void aniadirBarco(Barcos barco){
        listaBarcos.add(barco);
    }
    public synchronized ArrayList<Barcos> getBarcos(){return listaBarcos;}

    public void finDeJuego(){
        ganador = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
            int barcosRojo = 0;
            int barcosAzul = 0;

            for(Barcos barco: listaBarcos){
                if (barco.getVida()>0){
                    if(barco.getEquipo().equals("Rojo")){
                        barcosRojo++;
                    }
                    if (barco.getEquipo().equals("Azul")){
                        barcosAzul++;
                    }
                }
            }
            if(barcosRojo == 0 && barcosAzul >=1){
                nombreGanador = "Azul";
                mostrarGanador(nombreGanador);
                ganador.stop();
            }
            if(barcosAzul == 0 && barcosRojo >=1){
                nombreGanador = "Rojo";
                mostrarGanador(nombreGanador);
                ganador.stop();
            }
        }));
        ganador.setCycleCount(Timeline.INDEFINITE);
        ganador.play();
    }

    public void mostrarGanador(String nombreGanador){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(0.5));
        pauseTransition.setOnFinished(event -> {
            Platform.runLater(() -> {
                dialogGanador = alert.getDialogPane();
                if (nombreGanador.equals("Rojo")) {

                    alert.setTitle("El equipo ganador es el: " + nombreGanador);


                } else {

                    alert.setTitle("El equipo ganador es el: " + nombreGanador);
                }

                //mediaPlayer2.stop();
                dialogGanador.getStyleClass().add("dialog");
                alert.setContentText("El equipo ganador es el: " + nombreGanador);
                //Inicio inicio = new Inicio();
                alert.showAndWait().ifPresent(response -> {
                    // mediaPlayer.stop();
                    // inicio.start(new Stage());
                });
            });
        });
        pauseTransition.play();
    }

}
