module graf {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;

    opens graf to javafx.fxml;
    exports graf;
}
