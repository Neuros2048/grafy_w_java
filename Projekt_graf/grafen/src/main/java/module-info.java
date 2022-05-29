module graf {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires junit;

    opens graf to javafx.fxml;
    exports graf;
}
