package model;

import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MovingAnimation extends Transition {

    private int x , y , X , Y ;
    private Pane pane ;
    private Group group ;

    public MovingAnimation( int x , int y , int X , int Y , Pane pane , Group group ){
        this.x = x ;
        this.y = y ;
        this.X = X ;
        this.Y = Y ;
        this.group = group ;
        this.pane = pane ;
        this.setCycleCount( 1 );
        this.setCycleDuration( Duration.millis( 1000 ) );
    }

    @Override
    protected void interpolate( double v ){
        group.setLayoutX( v * ( X - x ) + x );
        group.setLayoutY( v * ( Y - y ) + y );
    }
}
