package model;

public abstract class AbstractBlock {
    GridPoint point; // don't forget this!
    abstract void changePoint( GridPoint point);
    abstract GridPoint move( GridPoint from, Direction direction); // it is better to use recursion and then return the final
                                                                // model.GridPoint it ends up in
    abstract void animateMovement( GridPoint origin); //Best option is javafx.animation.TranslateTransition
    abstract void animateCreation(); //Best option is javafx.animation.ScaleTransition
}
