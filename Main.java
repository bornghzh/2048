package view;

import model.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start( Stage stage ) throws Exception{
        Pane pane = FXMLLoader.load( Main.class.getResource( "/Main.fxml" ) ) ;
        Scene scene = new Scene ( pane ) ;
        stage.setScene( scene ) ;
        new GameController().set( pane ) ;
        stage.show() ;
    }
}
