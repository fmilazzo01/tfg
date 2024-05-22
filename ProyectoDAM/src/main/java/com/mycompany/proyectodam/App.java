package com.mycompany.proyectodam;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    // variables para mover el frame
    public static double xOffset = 0;
    public static double yOffset = 0;
    


    public static void configureStage(Stage stage, Scene scene) { // METODO PARA MOVER EL FRAME y que no aparesca la barra de cerrar default
        
        scene.setOnMousePressed(e -> {
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });

        scene.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() - xOffset);
            stage.setY(e.getScreenY() - yOffset);
        });

        // Set the stage style to UNDECORATED
     //   stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT); // Cambia UNDECORATED a TRANSPARENT PARA Q SE VEA EL BORDE
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("home"), 900, 580); // abro este al iniciar
        scene.setFill(Color.TRANSPARENT); // LINEA PARA QUE EL FONDO SE VEA TRANSPARENTE Y PONER LOS BORDES CRUVOS
        stage.setScene(scene);
     //   File directorio=new File(System.getProperty("user.home")+"\\Desktop\\IMGS");
     //   directorio.mkdir();

        configureStage(stage, scene); //LLAMO AL METODO PARA MOVER EL FRAME
        
        stage.show();
    }
    
    public  Stage cargarVentana(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
 
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
 
            newStage.setScene(scene);
 
            return newStage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

   
     // Metodo para aceptar alto y acho de cada frame
    static void setRoot(String fxml, double width, double height) throws IOException {
        scene.setRoot(loadFXML(fxml));
        scene.getWindow().setWidth(width);
        scene.getWindow().setHeight(height);
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}