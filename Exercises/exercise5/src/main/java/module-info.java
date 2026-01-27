module programming.exercise5.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens programming.exercise5.javafx to javafx.fxml;
    exports programming.exercise5.javafx;
}
