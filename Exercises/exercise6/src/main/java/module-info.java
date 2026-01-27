module programming.java.javafx {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.base;

    opens programming.java.javafx to javafx.fxml;
    exports programming.java.javafx;
}
