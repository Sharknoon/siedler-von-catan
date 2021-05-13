module de.sharknoon.siedlervoncatan {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    opens de.sharknoon.siedlervoncatan to javafx.graphics;
    opens de.sharknoon.siedlervoncatan.view.controller to javafx.fxml;
    exports de.sharknoon.siedlervoncatan;
}