/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectodam;

import com.mycompany.bd.clientes; // importamos la clase clientes
import com.mycompany.bd.connect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Franco
 */
public class HomeController implements Initializable {

    @FXML
    private Button ButtonPagar;

    @FXML
    private Button buttonLogOut;

    @FXML
    private Label LabelUsuarioMenu;

    @FXML
    private Label LabelUsuarioMenu1;

    @FXML
    private Label LabelUsuarioMenu11;

    @FXML
    private Label LabelUsuarioMenu111;

    @FXML
    private Button buttonActualizar;

    @FXML
    private Button buttonBorrar;

    @FXML
    private TextField textfieldFiltroAtrasados;

    //  ---------------------- table clientes----------------
    @FXML
    private TableView<clientes> table_clientes;

    @FXML
    private TableColumn<clientes, Integer> col_id;

    @FXML
    private TableColumn<clientes, String> col_apellidos;

    @FXML
    private TableColumn<clientes, String> col_fechainicio;

    @FXML
    private TableColumn<clientes, String> col_fechanac;

    @FXML
    private TableColumn<clientes, String> col_nombre;

    @FXML
    private TableColumn<clientes, Integer> col_telefono;

    @FXML
    private TableColumn<clientes, Integer> col_telemergencias;

    @FXML
    private TableColumn<clientes, String> col_vencimientoMembresia;

    // -------------- table clientes atrasados--------------------
    @FXML
    private TableView<clientes> table_clientesAtrasados;

    @FXML
    private TableColumn<clientes, Integer> col_id1;

    @FXML
    private TableColumn<clientes, String> col_apellidos1;

    @FXML
    private TableColumn<clientes, String> col_fechainicio1;

    @FXML
    private TableColumn<clientes, String> col_fechanac1;

    @FXML
    private TableColumn<clientes, String> col_nombre1;

    @FXML
    private TableColumn<clientes, Integer> col_telefono1;

    @FXML
    private TableColumn<clientes, Integer> col_telemergencias1;

    @FXML
    private TableColumn<clientes, String> col_vencimientoMembresia1;

    // ---------------------------------------------------------
    @FXML
    private Pane PaneAtrasados;

    @FXML
    private Pane PaneClientes;

    @FXML
    private Pane PaneInicio;

    @FXML
    private Pane PaneNuevoCliente;

    @FXML
    private TextField RegistroApellidos;

    @FXML
    private DatePicker RegistroNacimiento;

    @FXML
    private TextField RegistroNombre;

    @FXML
    private TextField RegistroNumTel;

    @FXML
    private TextField RegistroTelEmergencias;

    @FXML
    private TextField RegistroEmail;

    @FXML
    private TextField textfieldFiltro;

    @FXML
    private Label labelClientesDeudores;

    @FXML
    private Label labelGananciasMensuales;

    @FXML
    private Label labelTotalClientes;

    // ----------- TABLA  CLIENTES ----------
    ObservableList<clientes> listaClientes; // creo la lista
    int index = -1;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    //--------------------------------------------

    // ----------- TABLA  CLIENTES ATRASADOS ----------
    ObservableList<clientes> listaClientesAtrasados; // creo la lista
    //--------------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UpdateTable(); // llamo a que se actualize la tabla osea que aparesca en este caso
        UpdateTableClientesAtrasados();

        UpdateDashboard();

    }

    public void UpdateDashboard() {
        int totalClientesActivos = listaClientes.size();
        labelTotalClientes.setText("" + totalClientesActivos);

        int totalClientesDeudores = listaClientesAtrasados.size();
        labelClientesDeudores.setText("" + totalClientesDeudores);

        double dineroRecaudadoEsteMes = connect.obtenerDineroRecaudadoEsteMes();
        labelGananciasMensuales.setText("$" + dineroRecaudadoEsteMes);

    }

    // metodo que pone los datos de la consulta en la tabla
    public void UpdateTable() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        col_apellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        col_fechanac.setCellValueFactory(new PropertyValueFactory<>("fechanacimiento"));
        col_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        col_telemergencias.setCellValueFactory(new PropertyValueFactory<>("telemergencias"));
        col_fechainicio.setCellValueFactory(new PropertyValueFactory<>("fechainicio"));
        col_vencimientoMembresia.setCellValueFactory(new PropertyValueFactory<>("membresia_hasta"));

        listaClientes = connect.getDatausers(); // obtengo los datos de la consulta y los pongo la lista
        table_clientes.setItems(listaClientes); //pongo la lista en la tabla

    }

    public void UpdateTableClientesAtrasados() {
        col_id1.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nombre1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        col_apellidos1.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        col_fechanac1.setCellValueFactory(new PropertyValueFactory<>("fechanacimiento"));
        col_telefono1.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        col_telemergencias1.setCellValueFactory(new PropertyValueFactory<>("telemergencias"));
        col_fechainicio1.setCellValueFactory(new PropertyValueFactory<>("fechainicio"));
        col_vencimientoMembresia1.setCellValueFactory(new PropertyValueFactory<>("membresia_hasta"));

        listaClientesAtrasados = connect.getClientesAtrasados(); // Obtiene los datos de los clientes atrasados desde la base de datos
        table_clientesAtrasados.setItems(listaClientesAtrasados); // Pone los datos en el TableView
    }

    public void AgregarClientes() {
        conn = connect.ConnectDb();
        String sql = "insert into clientes (nombre,apellidos,fechanacimiento,telefono,telemergencias,fechainicio,email)values(?,?,?,?,?,CURRENT_DATE,?)";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, RegistroNombre.getText());
            pst.setString(2, RegistroApellidos.getText());
            pst.setString(4, RegistroNumTel.getText());
            pst.setString(5, RegistroTelEmergencias.getText());
            pst.setString(6, RegistroEmail.getText());

            // Convertir la fecha de DatePicker a String
            LocalDate fechaNacimiento = RegistroNacimiento.getValue();
            String fechaNacimientoStr = fechaNacimiento.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            pst.setString(3, fechaNacimientoStr);

            pst.execute();

            AlertUtils.showAlert(AlertType.INFORMATION, "Éxito", "Cliente agregado exitosamente");

            UpdateTable(); // llamo a que se actualize la tabla osea que aparesca en este caso
            UpdateTableClientesAtrasados(); // actualizo los clientes atrasados tmb
            //  search_user();
        } catch (Exception e) {
            AlertUtils.showAlert(AlertType.ERROR, "Error", "Error al agregar cliente: " + e.getMessage());
        }
    }

    // metodo para borrrar un cliente de la tableview
    public void DeleteFromTable() {

        if (index >= 0) { // Verifica si se ha seleccionado un cliente en la tabla
            clientes selectedCliente = table_clientes.getSelectionModel().getSelectedItem(); // Obtiene el cliente seleccionado
            String selectedClienteNombre = selectedCliente.getNombre(); // Obtiene el nombre del cliente seleccionado

            // mostrar dialogo de confirmacion 
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Eliminación");
            alert.setHeaderText("¿Estás seguro de que deseas eliminar al cliente " + selectedClienteNombre + "?");
            alert.setContentText("Esta acción no se puede deshacer.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Si el usuario da a OK=
                conn = connect.ConnectDb();
                String sql = "DELETE FROM clientes WHERE id = ?";
                try {
                    pst = conn.prepareStatement(sql);
                    pst.setInt(1, selectedCliente.getId());
                    pst.executeUpdate();

                    AlertUtils.showAlert(AlertType.INFORMATION, "Éxito", "Cliente eliminado exitosamente");

                    UpdateTable(); // Actualiza la tabla para reflejar los cambios
                    UpdateTableClientesAtrasados(); // actualizo los clientes atrasados tmb
                } catch (Exception e) {
                    AlertUtils.showAlert(AlertType.ERROR, "Error", "Error al eliminar el cliente: " + e.getMessage());
                }
            }
        } else {
            AlertUtils.showAlert(AlertType.WARNING, "Advertencia", "Por favor, selecciona un cliente para borrar");
        }
    }

    // BOTON PARA QUE AL CLICKEAR EL TABLEVIEW EL CLIENTE PUEDA REGISTRAR UN PAGO DEL 
    public void PagarButton() {
        if (index >= 0) { // Verifica si se ha seleccionado un cliente en la tabla
            clientes selectedCliente = table_clientes.getSelectionModel().getSelectedItem(); // Obtiene el cliente seleccionado
            String selectedClienteNombre = selectedCliente.getNombre(); // Obtiene el nombre del cliente seleccionado

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Seleccionar Tipo de Pago");
            alert.setHeaderText("Selecciona el tipo de pago para el cliente: " + selectedClienteNombre);
            alert.setContentText("Elige una opción:");

            ButtonType mensualidadButton = new ButtonType("Mensualidad");
            ButtonType trimestreButton = new ButtonType("Trimestre");
            ButtonType semestreButton = new ButtonType("Semestre");
            ButtonType anoButton = new ButtonType("Año");

            alert.getButtonTypes().setAll(mensualidadButton, trimestreButton, semestreButton, anoButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent()) {
                String selectedOption = result.get().getText();
                LocalDate fechaPago = LocalDate.now(); // Obtiene la fecha actual para el pago

                // Calcula el monto del pago dependiendo del tipo de pago seleccionado
                double montoPago = calcularMontoPago(selectedOption);

                // Calcula la fecha de vencimiento de la membresía
                LocalDate membresiaHasta = calcularFechaVencimiento(selectedOption, fechaPago);

                // Antes de insertar un nuevo pago, elimina los pagos anteriores para el cliente seleccionado DE ESTE MODO NO SE REPETIRAN EN LA TABLA DEUDORES
                String eliminarPagosAntiguosQuery = "DELETE FROM pagos WHERE cliente_id = ?";
                try {
                    Connection conn = connect.ConnectDb();
                    PreparedStatement eliminarPagosAntiguosStmt = conn.prepareStatement(eliminarPagosAntiguosQuery);
                    eliminarPagosAntiguosStmt.setInt(1, selectedCliente.getId());
                    eliminarPagosAntiguosStmt.executeUpdate();
                    conn.close();
                } catch (SQLException e) {
                    AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", "Error al eliminar pagos antiguos: " + e.getMessage());
                    return; // Si hay un error al eliminar pagos antiguos, salir del método sin insertar el nuevo pago
                }

                // Inserta el pago en la base de datos junto con la fecha de vencimiento
                try {
                    Connection conn = connect.ConnectDb();
                    String sql = "INSERT INTO pagos (cliente_id, fecha_pago, tipo_pago, membresia_hasta, cantidad) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement pst = conn.prepareStatement(sql);
                    pst.setInt(1, selectedCliente.getId());
                    pst.setDate(2, java.sql.Date.valueOf(fechaPago));
                    pst.setString(3, selectedOption);
                    pst.setDate(4, java.sql.Date.valueOf(membresiaHasta)); // Establece la fecha de vencimiento
                    pst.setDouble(5, montoPago); // Establece el monto del pago
                    pst.executeUpdate();
                    conn.close();

                    String message = "Se ha registrado el pago de tipo " + selectedOption + " para el cliente " + selectedClienteNombre;
                    AlertUtils.showAlert(Alert.AlertType.INFORMATION, "Pago Confirmado", message);
                    UpdateTableClientesAtrasados();
                    UpdateTable();
                } catch (SQLException e) {
                    e.printStackTrace();
                    AlertUtils.showAlert(Alert.AlertType.ERROR, "Error", "Error al registrar el pago en la base de datos.");
                }
            }
        }
    }

    // METODO PARA INTRODUCIR EL PRECIO DEL PAQUETE DEL GYM
    private double calcularMontoPago(String tipoPago) {
        switch (tipoPago) {
            case "Mensualidad":
                return 30.0;
            case "Trimestre":
                return 80.0;
            case "Semestre":
                return 140.0;
            case "Año":
                return 200.0;
            default:
                return 0.0; //  devuelve 0 si el tipo de pago no coincide con ninguno de los casos anteriores
        }
    }

    // metodo para calcular la fecha de vencimiento de la membnresia
    private LocalDate calcularFechaVencimiento(String tipoPago, LocalDate fechaPago) {
        switch (tipoPago) {
            case "Mensualidad":
                return fechaPago.plusMonths(1);
            case "Trimestre":
                return fechaPago.plusMonths(3);
            case "Semestre":
                return fechaPago.plusMonths(6);
            case "Año":
                return fechaPago.plusYears(1);
            default:
                return fechaPago;
        }
    }

    //METODO QUE PERMITE ENCONTRAR UN CLIENTE EN EL TABLEVIEW de clientes
    @FXML
    void filtrarClientes(KeyEvent event) {
        String searchText = removeAccents(textfieldFiltro.getText().toLowerCase()); // Obtener el texto del TextField y convertirlo a minúsculas y sin acentos

        ObservableList<clientes> clientesFiltrados = FXCollections.observableArrayList(); // Crear una nueva lista observable para almacenar los clientes filtrados

        for (clientes cliente : listaClientes) { // Iterar sobre la lista de clientes original
            if (removeAccents(cliente.getNombre().toLowerCase()).contains(searchText) || removeAccents(cliente.getApellidos().toLowerCase()).contains(searchText)) { // Verificar si el nombre o los apellidos del cliente contienen el texto de búsqueda
                clientesFiltrados.add(cliente); // Agregar el cliente a la lista filtrada
            }
        }

        table_clientes.setItems(clientesFiltrados); // Actualizar la tabla con la lista de clientes filtrados
    }

    //METODO QUE PERMITE ENCONTRAR UN CLIENTE EN EL TABLEVIEW de atrasado
    @FXML
    void filtrarClientesAtrasados(KeyEvent event) {
        String searchText = removeAccents(textfieldFiltroAtrasados.getText().toLowerCase()); // Obtener el texto del TextField y convertirlo a minúsculas y sin acentos

        ObservableList<clientes> clientesFiltrados2 = FXCollections.observableArrayList(); // Crear una nueva lista observable para almacenar los clientes filtrados

        for (clientes cliente : listaClientesAtrasados) { // Iterar sobre la lista de clientes atrasados
            if (removeAccents(cliente.getNombre().toLowerCase()).contains(searchText) || removeAccents(cliente.getApellidos().toLowerCase()).contains(searchText)) { // Verificar si el nombre o los apellidos del cliente contienen el texto de búsqueda
                clientesFiltrados2.add(cliente); // Agregar el cliente a la lista filtrada
            }
        }

        table_clientesAtrasados.setItems(clientesFiltrados2); // Actualizar la tabla con la lista de clientes filtrados
    }

    // metodo para quitar acentos
    public String removeAccents(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    // metodo para seleccionar los clientes de la tabla con un moouseclick 
    @FXML
    void getSelected(MouseEvent event) {
        index = table_clientes.getSelectionModel().getSelectedIndex();
    }

    public void exit(ActionEvent event) { //boton cerrar con x
        Platform.exit();
    }

    // -------------------CAMBIO DE PANEL CON BOTONES------------------------
    @FXML
    void GoInicio(ActionEvent event) {
        hideAllPanes();
        UpdateDashboard(); // actualizo el dasbhoard antes de entrar al inicio
        PaneInicio.setVisible(true);
    }

    @FXML
    void GoClientes(ActionEvent event) {
        hideAllPanes();
        PaneClientes.setVisible(true);
    }

    @FXML
    void GoAtrasados(ActionEvent event) {
        hideAllPanes();
        PaneAtrasados.setVisible(true);
    }

    @FXML
    void GoNuevoCliente(ActionEvent event) {
        hideAllPanes();
        PaneNuevoCliente.setVisible(true);
    }

    private void hideAllPanes() { //metodo que oculta todos los paneles
        PaneInicio.setVisible(false);
        PaneClientes.setVisible(false);
        PaneAtrasados.setVisible(false);
        PaneNuevoCliente.setVisible(false);
    }
    // -------------------CAMBIO DE PANEL CON BOTONES------------------------
    
        @FXML
    void LogOut(ActionEvent event) throws IOException {
    App.setRoot("login",900, 580); 
    }
    

}
