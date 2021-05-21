# Siedler von Catan
An updated version of the german board game Siedler von Catan of my fellow student Marcel Vollmer.

## Run the game
1. Install Java 16 or newer. You can download Java [here](https://www.oracle.com/de/java/technologies/javase-downloads.html).
2. Download JavaFX [here](https://gluonhq.com/products/javafx/) and extract it to a location of your choice. 
3. Head over to the release page [Releases](https://github.com/Sharknoon/siedler-von-catan/releases) and download the latest .jar file.
4. Finally you can launch the game with the following command
```bash
java --module-path <path-to-your-extracted-javafx>/lib --add-modules javafx.controls,javafx.fxml,javafx.media,java.desktop -jar siedler-von-catan-X.X.X.jar
```
> Important: Replace `<path-to-your-extracted-javafx>` and `X.X.X` with the appropriate values
