package model;

import model.AbstractBlock;

public abstract class AbstractBlockgrid {
    AbstractBlock[][] blocks = new AbstractBlock[4][4];

    abstract boolean checkEnd(); //use model.GridPoint.neighbors here
    abstract boolean addNewBlock(); // return false if is full

    void up(){ // example of how one might implement this function
        for (int row = 1; row < 4; row++)
            for (int column = 0; column < 4; column++) // the 'for' parameters might change for other directions
                if (!isBlockEmpty(row,column)) {
                    AbstractBlock block = getBlock(new GridPoint(row,column));
                    GridPoint origin = block.point;
                    block.changePoint(block.move(block.point, Direction.UP));
                    block.animateMovement(origin);
                }
    }
    abstract void down();
    abstract void right();
    abstract void left();
    AbstractBlock getBlock( GridPoint point){
        return blocks[point.x()][point.y()];
    }

    abstract void setBlock( GridPoint point, AbstractBlock toBlock); // in the end you might want to cast to
                                                                    // the "model.Block" class you will implement
    abstract GridPoint getRandomEmptyPoint();
    abstract boolean isBlockEmpty(int i, int j);
    boolean isBlockEmpty( GridPoint point){
        return isBlockEmpty(point.x(), point.y());
    }
    abstract void debugMessage();
}
