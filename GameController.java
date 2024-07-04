package model;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.BlockGrid;

public class GameController {

    private Pane pane ;
    private BlockGrid blockGrid ;
    private Rectangle gridRectangle ;
    private Text score ;

    public void set( Pane pane ){
        Text score = new Text() ;
        Text foo = new Text( "YOUR SCORE : " ) ;
        score.setFill( Color.RED ) ;
        foo.setFill( Color.RED ) ;
        foo.setStyle( "-fx-font-size: 30" ) ;
        score.setStyle( "-fx-font-size: 30" ) ;
        pane.getChildren().add( foo ) ;
        foo.setLayoutX( 50 ) ;
        foo.setLayoutY( 50 ) ;
        score.setLayoutX( 250 );
        score.setLayoutY( 50 ) ;
        pane.getChildren().add( score ) ;
        this.pane = pane ;
        pane.requestFocus();
        initBlockGrid() ;
        initKeyboard() ;
    }

    private void initBlockGrid(){
        gridRectangle = new Rectangle( 100 , 100 , 400 , 400 ) ;
        this.blockGrid = new BlockGrid( gridRectangle , pane ) ;
        pane.getChildren().add( gridRectangle ) ;
        gridRectangle.setFill( Color.BLACK ) ;
    }

    private void initKeyboard(){
        blockGrid.addNewBlock() ;
        pane.setOnKeyPressed( new EventHandler <KeyEvent>() {
            @Override
            public void handle( KeyEvent keyEvent ){
                if( keyEvent.getCode() == KeyCode.W ){
                    blockGrid.up() ;
                } else if ( keyEvent.getCode() == KeyCode.A ){
                    blockGrid.left() ;
                } else if ( keyEvent.getCode() == KeyCode.S ){
                    blockGrid.down() ;
                } else if( keyEvent.getCode() == KeyCode.D ){
                    blockGrid.right() ;
                }
            }
        } );
    }

}
