module com.example.dekstopShopProject {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires mysql.connector.j;
    requires org.apache.logging.log4j;


    opens com.example.dekstopshopproject to javafx.fxml;
    exports com.example.dekstopshopproject;
}