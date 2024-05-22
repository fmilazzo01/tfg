module com.mycompany.proyectodam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires javafx.graphics;
    requires jbcrypt;
  //  requires org.simplejavamail;
    
    
    opens com.mycompany.proyectodam to javafx.fxml;
    opens com.mycompany.bd to javafx.base;
    exports com.mycompany.proyectodam;
}
