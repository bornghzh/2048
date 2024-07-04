package model;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Random;


public class BlockGrid extends AbstractBlockgrid {

    int[][] grid = new int[4][4] ;
    Block[][] block = new Block[4][4] ;
    Rectangle gridRectangle ;
    Text score ;
    Pane pane ;

    public BlockGrid( Rectangle gridRectangle , Pane pane ){
        for(int i =0 ; i < 4 ; i++)
            for(int j = 0 ; j < 4 ; j++)
                grid[i][j] = -1 ;
        this.gridRectangle = gridRectangle ;
        this.pane = pane ;
        System.out.println( 1 ) ;
        this.score = ( Text ) pane.getChildren().get( 1 ) ;
    }

    @Override
    boolean checkEnd(){
        for(int i = 0 ; i < 4 ; i++)
            for(int j = 0 ; j < 4 ; j++)
                if( grid[i][j] == -1 )
                    return false ;
        return true;
    }

    @Override
    boolean addNewBlock(){
        boolean full = true ;
        outer :
        for(int i = 0 ; i < 4 ; i++)
            for(int j = 0 ; j < 4 ; j++)
                if(grid[i][j] == -1){
                    full = false ;
                    break outer ;
                }
        if( full ) return false ;
        ArrayList<Integer> freePoints = new ArrayList <Integer>() ;
        for(int i = 0 ; i < 16 ; i++){
            if( grid[i%4][i/4] == -1 ) freePoints.add( i );
        }
        int index = freePoints.get(Math.abs((new Random()).nextInt()) % freePoints.size()) ;

        grid[index%4][index/4] = Math.abs((new Random()).nextInt()) % 4 ;
        if( grid[index%4][index/4] == 0 ) grid[index%4][index/4] = 2 ;
        else grid[index%4][index/4] = 1 ;

        block[index%4][index/4] = new Block( grid[index%4][index/4] ) ;
        pane.getChildren().add( block[index%4][index/4].getShape() ) ;
        block[index%4][index/4].getShape().setLayoutX( 100 + (index / 4) * 100 ) ;
        block[index%4][index/4].getShape().setLayoutY( 100 + (index % 4) * 100 ) ;

        return true ;
    }

    @Override
    void down(){
        ArrayList<Integer> arr = new ArrayList<Integer>() ;
        for(int j = 0 ; j < 4 ; j++){
            arr.clear() ;
            for(int i = 3 ; i >= 0 ; i--) if(grid[i][j] != -1) arr.add( grid[i][j] ) ;
            for(int x = arr.size() - 1  ; x >= 1 ; x--){
                if( arr.get( x ) == arr.get( x - 1 ) ){
                    arr.remove( x ) ;
                    arr.set( x - 1 , arr.get( x - 1 ) + 1 ) ;
                    x-- ;
                }
            }
            int index = 4 ;
            for(int i = 0 ; i < arr.size() ; i++){
                grid[3-i][j] = arr.get(i) ;
                index = Math.min( index , 3-i ) ;
            }
            for(int i = index - 1 ; i >= 0 ; i--)
                grid[i][j] = -1 ;
        }
        for(int i = 0 ; i < 4 ; i++)
            for(int j = 0 ; j < 4 ; j++){
                if( block[i][j] != null ) pane.getChildren().remove( block[i][j].getShape() ) ;
                block[i][j] = null ;
                if( grid[i][j] != -1 ){
                    block[i][j] = new Block( grid[i][j] ) ;
                    pane.getChildren().add( block[i][j].getShape() ) ;
                    block[i][j].getShape().setLayoutY( 100 + i * 100 ) ;
                    block[i][j].getShape().setLayoutX( 100 + j * 100 ) ;
                }
            }

        this.addNewBlock() ;
        checkWin() ;
    }

    @Override
    void up(){
        ArrayList<Integer> arr = new ArrayList<Integer>() ;
        for(int j = 0 ; j < 4 ; j++){
            arr.clear() ;
            for(int i = 0 ; i < 4 ; i++) if(grid[i][j] != -1)arr.add( grid[i][j] ) ;
            for(int x = arr.size() - 1  ; x >= 1 ; x--){
                if( arr.get( x ) == arr.get( x - 1 ) ){
                    arr.remove( x ) ;
                    arr.set( x - 1 , arr.get( x - 1 ) + 1 ) ;
                    x-- ;
                }
            }
            int index = -1 ;
            for(int i = 0 ; i < arr.size() ; i++){
                grid[i][j] = arr.get(i) ;
                index = Math.max( index , i ) ;
            }
            for(int i = index + 1 ; i < 4 ; i++)
                grid[i][j] = -1 ;
        }
        for(int i = 0 ; i < 4 ; i++)
            for(int j = 0 ; j < 4 ; j++){
                if( block[i][j] != null ) pane.getChildren().remove( block[i][j].getShape() ) ;
                block[i][j] = null ;
                if( grid[i][j] != -1 ){
                    block[i][j] = new Block( grid[i][j] ) ;
                    pane.getChildren().add( block[i][j].getShape() ) ;
                    block[i][j].getShape().setLayoutY( 100 + i * 100 ) ;
                    block[i][j].getShape().setLayoutX( 100 + j * 100 ) ;
                }
            }
        this.addNewBlock() ;
        checkWin() ;
    }

    @Override
    void right(){
        ArrayList<Integer> arr = new ArrayList<>() ;
        for(int i = 0 ; i < 4 ; i++){
            arr.clear() ;
            for(int j = 3 ; j >= 0 ; j--) if( grid[i][j] != -1 ) arr.add( grid[i][j] ) ;
            for(int x = arr.size() - 1  ; x >= 1 ; x--){
                if( arr.get( x ) == arr.get( x - 1 ) ){
                    arr.remove( x ) ;
                    arr.set( x - 1 , arr.get( x - 1 ) + 1 ) ;
                    x-- ;
                }
            }
            int index = 4 ;
            for(int j = 3 ; 3 - j < arr.size() ; j--){
                grid[i][j] = arr.get( 3 - j );
                index = Math.min( index, j );
            }
            for(int j = index - 1 ;j >= 0 ; j--){
                grid[i][j] = -1 ;
            }
        }
        for(int i = 0 ; i < 4 ; i++)
            for(int j = 0 ; j < 4 ; j++){
                if( block[i][j] != null ) pane.getChildren().remove( block[i][j].getShape() ) ;
                block[i][j] = null ;
                if( grid[i][j] != -1 ){
                    block[i][j] = new Block( grid[i][j] ) ;
                    pane.getChildren().add( block[i][j].getShape() ) ;
                    block[i][j].getShape().setLayoutY( 100 + i * 100 ) ;
                    block[i][j].getShape().setLayoutX( 100 + j * 100 ) ;
                }
            }
        this.addNewBlock() ;
        checkWin() ;
    }

    @Override
    void left(){

        ArrayList<Integer> arr = new ArrayList<>() ;
        for(int i = 0 ; i < 4 ; i++){
            arr.clear() ;
            for(int j = 0 ; j < 4 ; j++) if(grid[i][j]!=-1) arr.add( grid[i][j] ) ;
            for(int x = arr.size() - 1  ; x >= 1 ; x--){
                if( arr.get( x ) == arr.get( x - 1 ) ){
                    arr.remove( x ) ;
                    arr.set( x - 1 , arr.get( x - 1 ) + 1 ) ;
                    x-- ;
                }
            }
            int index = -1 ;
            for(int j = 3 ; 3 - j < arr.size() ; j--){
                grid[i][3-j] = arr.get( 3 - j ) ;
                index = Math.max( index , 3 - j ) ;
            }
            for(int j = index + 1 ; j < 4 ; j++ ) grid[i][j] = -1 ;
        }

        for(int i = 0 ; i < 4 ; i++)
            for(int j = 0 ; j < 4 ; j++){
                if( block[i][j] != null ) pane.getChildren().remove( block[i][j].getShape() ) ;
                if( grid[i][j] != -1 ){
                    block[i][j] = new Block( grid[i][j] ) ;
                    pane.getChildren().add( block[i][j].getShape() ) ;
                    block[i][j].getShape().setLayoutY( 100 + i * 100 ) ;
                    block[i][j].getShape().setLayoutX( 100 + j * 100 ) ;
                } else block[i][j] = null ;
            }
        this.addNewBlock() ;
        checkWin() ;
    }

    @Override
    void setBlock( GridPoint point, AbstractBlock toBlock ){
        int I = point.x() ;
        int J = point.y() ;
        block[I][J] = (Block)toBlock ;
    }

    @Override
    GridPoint getRandomEmptyPoint(){
        return null;
    }

    @Override
    boolean isBlockEmpty( int i, int j ){
        return grid[i][j] == -1 ;
    }

    @Override
    void debugMessage(){

    }

    public void checkWin(){
        int scoreInt = -1 ;
        boolean full = true ;
        boolean has2048 = false ;
        for(int i = 0 ; i< 4 ; i++)
            for(int j = 0 ; j < 4 ; j++){
                if( grid[i][j] == - 1 ) full = false;
                if( grid[i][j] == 11 ) has2048 = true ;
                scoreInt = Math.max( grid[i][j] , scoreInt ) ;
            }
        score.setText( "" + (int)Math.pow( 2 , scoreInt ) ) ;
        int[][] n = { { 0 , -1 } , { 0 , 1 } , { 1 , 0 } , { -1 , 0 } } ;
        boolean hasMoveLeft = false ;
        for(int i = 0 ; i < 4 ; i++)
            for(int j = 0 ; j < 4 ; j++)
                for(int x = 0 ; x < 4 ; x++)
                    if(validPos(i+n[x][0] , j+n[x][1]) && grid[i+n[x][0]][j+n[x][1]] == grid[i][j]) hasMoveLeft = true ;

        if( full && !has2048 && !hasMoveLeft ) score.setText( "YOU lOSE" ) ;
        else if ( has2048 ) score.setText( "YOU WON" ) ;
    }

    private boolean validPos( int x , int y ){
        return x < 4 && x > -1 && y < 4 && y > -1 ;
    }
}
