package model;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.AbstractBlock;

public class Block extends AbstractBlock {

    private Rectangle rectangle = new Rectangle( 0 , 0 , 100 , 100 ) ;
    private int power ;
    private Rectangle shape ;
    private Text text ;
    private Group group ;

    public Block( int power ){
        this.power = power ;
        this.shape = new Rectangle( 0 , 0 , 100 , 100 ) ;
        this.text = new Text ( "" + (int)Math.abs( Math.pow( 2 , power ) ) ) ;
        text.setFill( Color.WHITE ) ;
        shape.setFill( Colorpicker.colors[ power - 1 ] ); ;
        text.setStyle( "-fx-font-family: 'Comic Sans MS' ; -fx-font-size: 50" ) ;
        this.group = new Group() ;
        text.setY( 75 ) ;
        text.setX( 25 ) ;
        this.group.getChildren().addAll( shape , text ) ;
    }

    public Group getShape(){
        return this.group ;
    }

    @Override
    void changePoint( GridPoint point ){

    }

    @Override
    GridPoint move( GridPoint from, Direction direction ){
        return null;
    }

    @Override
    void animateMovement( GridPoint origin ){

    }

    @Override
    void animateCreation(){

    }
}
