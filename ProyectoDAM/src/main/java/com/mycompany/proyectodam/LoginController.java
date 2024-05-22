/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectodam;

import com.mycompany.bd.connect;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Franco
 */
public class LoginController implements Initializable {

    @FXML
    private Button LOG_ButtonIniciarSesion;

    @FXML
    private TextField LOG_NomUsuario;

    @FXML
    private PasswordField LOG_PasswordField;

    @FXML
    private Label LOG_labelmenu;

    @FXML
    private Label LOG_labeltexto;

    @FXML
    private Button REG_ButtonRegistrarse;

    @FXML
    private TextField REG_Direccion;

    @FXML
    private TextField REG_NomGYM;

    @FXML
    private TextField REG_NomUsuario;

    @FXML
    private Button LOG_TRANSICION;

    @FXML
    private Button REG_TRANSICION;

    @FXML
    private PasswordField REG_contrasena;

    @FXML
    private TextField REG_email;

    @FXML
    private Label REG_labelmenu1;

    @FXML
    private Label REG_labeltexto;

    @FXML
    private TextField REG_telefono;

    @FXML
    private AnchorPane WhitePane;

    @FXML
    private AnchorPane layer2;

    @FXML
    private Button cerrar_registro;

    @FXML
    private Button cerrrar_login;

    @FXML
    private Label REG_Label1;

    @FXML
    private Label REG_Label2;

    @FXML
    private Label REG_Label3;

    @FXML
    private Label REG_Label4;

    @FXML
    private Label REG_Label5;

    @FXML
    private Label REG_Label6;

    @FXML
    private Label LOG_Label1;

    @FXML
    private Label LOG_Label2;

    // Guardar las posiciones iniciales
    private double whitePaneInitialX; // posicion inicial del panel
    private double layer2InitialX; // posicion inicial del menu lateral

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ocultarRegistro();
        mostrarLogin();

    }

    //metodo para desplazar el panel para mostrar el registro
    @FXML
    void btn(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);
        slide.setToX(600);
        slide.play();

        WhitePane.setTranslateX(-300);
        mostrarRegistro();
        ocultarLogin();

        slide.setOnFinished((e -> {

        }));

    }

    //metodo para desplazar el panel para mostrar el login
    @FXML
    void btn2(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.6));
        slide.setNode(layer2);
        slide.setToX(layer2InitialX); // posicion inicial del panel
        slide.play();

        WhitePane.setTranslateX(whitePaneInitialX);
        mostrarLogin();
        ocultarRegistro();

        slide.setOnFinished((e -> {

        }));

    }

    // Metodo para ocultar los elementos de registro
    public void ocultarRegistro() {
        REG_TRANSICION.setVisible(false);
        REG_ButtonRegistrarse.setVisible(false);
        REG_Direccion.setVisible(false);
        REG_NomGYM.setVisible(false);
        REG_NomUsuario.setVisible(false);
        REG_contrasena.setVisible(false);
        REG_email.setVisible(false);
        REG_telefono.setVisible(false);
        REG_labelmenu1.setVisible(false);
        REG_labeltexto.setVisible(false);
        cerrar_registro.setVisible(false);
        REG_Label1.setVisible(false);
        REG_Label2.setVisible(false);
        REG_Label3.setVisible(false);
        REG_Label4.setVisible(false);
        REG_Label5.setVisible(false);
        REG_Label6.setVisible(false);
    }

// Metodo para mostrar los elementos de registro
    public void mostrarRegistro() {
        REG_TRANSICION.setVisible(true);
        REG_ButtonRegistrarse.setVisible(true);
        REG_Direccion.setVisible(true);
        REG_NomGYM.setVisible(true);
        REG_NomUsuario.setVisible(true);
        REG_contrasena.setVisible(true);
        REG_email.setVisible(true);
        REG_telefono.setVisible(true);
        REG_labelmenu1.setVisible(true);
        REG_labeltexto.setVisible(true);
        cerrar_registro.setVisible(true);
        REG_Label1.setVisible(true);
        REG_Label2.setVisible(true);
        REG_Label3.setVisible(true);
        REG_Label4.setVisible(true);
        REG_Label5.setVisible(true);
        REG_Label6.setVisible(true);
    }

// Metodo para ocultar los elementos de inicio de sesión
    public void ocultarLogin() {
        LOG_TRANSICION.setVisible(false);
        LOG_ButtonIniciarSesion.setVisible(false);
        LOG_NomUsuario.setVisible(false);
        LOG_PasswordField.setVisible(false);
        LOG_labelmenu.setVisible(false);
        LOG_labeltexto.setVisible(false);
        cerrrar_login.setVisible(false);
        LOG_Label1.setVisible(false);
        LOG_Label2.setVisible(false);
    }

// Metodo para mostrar los elementos de inicio de sesión
    public void mostrarLogin() {
        LOG_TRANSICION.setVisible(true);
        LOG_ButtonIniciarSesion.setVisible(true);
        LOG_NomUsuario.setVisible(true);
        LOG_PasswordField.setVisible(true);
        LOG_labelmenu.setVisible(true);
        LOG_labeltexto.setVisible(true);
        cerrrar_login.setVisible(true);
        LOG_Label1.setVisible(true);
        LOG_Label2.setVisible(true);
    }

    @FXML
    void LOGIN(MouseEvent event) throws IOException {
        String nombreUsuario = LOG_NomUsuario.getText();
        String contrasena = LOG_PasswordField.getText();

        // Validar el inicio de ses
        if (connect.validarLogin(nombreUsuario, contrasena)) {
            System.out.println("Inicio de sesion exitoso para el usuario: " + nombreUsuario);
            App.setRoot("home", 900, 580);
        } else {
            // Inicio de sesión fallido muestra un mensaje de error
            AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Error", "Nombre de usuario o contraseña incorrectos. Por favor, inténtalo de nuevo.");

        }
    }

    //METODO PARA EL BOTON DE REGISTRAR EL USUARIO
    @FXML
    void register(ActionEvent event) {
        String nombreUsuario = REG_NomUsuario.getText();
        String contrasena = REG_contrasena.getText();
        String email = REG_email.getText();
        String direccion = REG_Direccion.getText();
        String telefono = REG_telefono.getText();
        String nombreGym = REG_NomGYM.getText();

        // Verificar si hay algun campo vacio
        if (nombreUsuario.isEmpty() || contrasena.isEmpty() || email.isEmpty() || nombreGym.isEmpty()) {
            AlertUtils.showAlert(Alert.AlertType.WARNING, "Advertencia", "Por favor, completa todos los campos .");
            return;
        }
        // Verificar si el nombre de usuario ya está en uso
        if (connect.comprobarSiUsuarioExistente(nombreUsuario)) {
            AlertUtils.showAlert(Alert.AlertType.WARNING, "Advertencia", "El nombre de usuario ya está en uso. Por favor, elige otro.");
            return;
        }
        // Verificar si el email ya está en uso
        if (connect.comprobarEMAILRegistrado(email)) {
            AlertUtils.showAlert(Alert.AlertType.WARNING, "Advertencia", "El email ya está registrado. Por favor, utiliza otro.");
            return;
        }
        // Registrar el usuario
        if (connect.registrarUsuario(nombreUsuario, contrasena, email, direccion, telefono, nombreGym)) {
            AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Éxito", "Registro completado con éxito.");
        } else {
            AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", "Ocurrió un error durante el registro. Por favor, inténtalo de nuevo.");
        }
    }

    public void exit(ActionEvent event) { //boton cerrar con x
        Platform.exit();
    }

}
