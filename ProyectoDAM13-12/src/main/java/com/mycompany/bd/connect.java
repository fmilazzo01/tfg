package com.mycompany.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Franco
 */
public class connect {

    Connection conn = null;

    // conexion con la BD
    public static Connection ConnectDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/gym", "root", "123");
            // JOptionPane.showMessageDialog(null, "Connection Established");

            System.out.println("//////////////////////////////////conexion establecida//////////////////////////////////");
            return conn;
        } catch (Exception e) {

            return null;
        }

    }

    public static ObservableList<clientes> getDatausers() {
        Connection conn = ConnectDb();
        ObservableList<clientes> list = FXCollections.observableArrayList();
        try {

            String query = "SELECT clientes.*, MAX(pagos.membresia_hasta) AS membresia_hasta "
                    + "FROM clientes "
                    + "LEFT JOIN pagos ON clientes.id = pagos.cliente_id "
                    + "GROUP BY clientes.id";

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String membresiaHastaStr = rs.getString("membresia_hasta");  // Obtenemos el valor de la columna "membresia_hasta" como una cadena
                LocalDate membresiaHasta = null;    // inicimaos  la variable membresiaHasta como null
                if (membresiaHastaStr != null) {     // verificamos si el valor de membresia_hasta es diferente de null

                    membresiaHasta = LocalDate.parse(membresiaHastaStr);     // si el valor no es null, parseamos la cadena a un objeto LocalDate
                }
                // Agregamos un nuevo objeto clientes a la lista pasando los valores   si membresiaHasta es null, se pasara null al constructor de clientes
                list.add(new clientes(
                        Integer.parseInt(rs.getString("id")),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        LocalDate.parse(rs.getString("fechanacimiento")),
                        rs.getString("telefono"),
                        rs.getString("telemergencias"),
                        LocalDate.parse(rs.getString("fechainicio")),
                        membresiaHasta // Fecha de vencimiento de la membresía del cliente (puede ser null)
                ));
            }
        } catch (NumberFormatException | SQLException e) {
        }
        return list;
    }

    public static ObservableList<clientes> getClientesAtrasados() {
        Connection conn = ConnectDb();
        ObservableList<clientes> list = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM clientes "
                    + "INNER JOIN pagos ON clientes.id = pagos.cliente_id "
                    + "WHERE pagos.membresia_hasta < CURDATE()";

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new clientes(
                        Integer.parseInt(rs.getString("id")),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        LocalDate.parse(rs.getString("fechanacimiento")),
                        rs.getString("telefono"),
                        rs.getString("telemergencias"),
                        LocalDate.parse(rs.getString("fechainicio")),
                        LocalDate.parse(rs.getString("membresia_hasta"))
                ));
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    public static double obtenerDineroRecaudadoEsteMes () {
                double dineroRecaudado = 0.0;
        String query = "SELECT SUM(cantidad) AS dinero_total FROM pagos WHERE MONTH(fecha_pago) = MONTH(CURDATE())";
        try (Connection conn = connect.ConnectDb(); PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                dineroRecaudado = rs.getDouble("dinero_total");
            }
        } catch (SQLException e) {
        }
        return dineroRecaudado;
    }

}
