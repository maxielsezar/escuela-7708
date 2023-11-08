module com.example.calendario {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;



    opens com.example.escuela7708 to javafx.fxml;
    exports com.example.escuela7708;
    exports com.example.escuela7708.controladores;
    opens com.example.escuela7708.controladores to javafx.fxml;
    exports com.example.escuela7708.modelo.clases;
    opens com.example.escuela7708.modelo.clases to javafx.fxml;
    exports com.example.escuela7708.modelo.basedatos;
    opens com.example.escuela7708.modelo.basedatos to javafx.fxml;
    exports com.example.escuela7708.modelo.basedatos.docente;
    opens com.example.escuela7708.modelo.basedatos.docente to javafx.fxml;
    exports com.example.escuela7708.modelo.basedatos.docenteCurso;
    opens com.example.escuela7708.modelo.basedatos.docenteCurso to javafx.fxml;
    exports com.example.escuela7708.modelo.basedatos.curso;
    opens com.example.escuela7708.modelo.basedatos.curso to javafx.fxml;
}