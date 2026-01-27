module programming.Menu {
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;
    requires javafx.base;
    requires java.xml;
    requires java.sql;

    opens Menu to javafx.fxml;
    exports Menu;
}
