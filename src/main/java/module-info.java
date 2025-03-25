module org.example.swiftawt {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires jgoodies.forms;

    opens org.example.swiftawt to javafx.fxml;
    exports org.example.swiftawt;
}