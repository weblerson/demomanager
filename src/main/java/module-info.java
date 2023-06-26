module com.lerson.demomanager {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
                        
    opens com.lerson.demomanager to javafx.fxml;
    exports com.lerson.demomanager;
    exports com.lerson.demomanager.application;
    opens com.lerson.demomanager.application to javafx.fxml;
}