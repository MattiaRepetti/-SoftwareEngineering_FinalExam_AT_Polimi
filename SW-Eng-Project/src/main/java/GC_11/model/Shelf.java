package GC_11.model;

import java.util.List;
import GC_11.exceptions.*;

public class Shelf {


    // Attributes
    private Tile[][] myShelf;

    // Methods
    public Shelf(){
        myShelf= new Tile[6][5];
    }

    private void addTile(Tile t, int column){


    }

    private int freeSpaces(int column){
        int free = 0;
        for(int i=0; i<6; i++){
            if(myShelf[i][column].getColor() == TileColor.EMPTY){
                free++;
            }
        }
        return free;
    }

    public void addTiles(List<Tile> tileList, int column) throws notEnoughFreeSpacesException, columnIndexOutOfBoundsException {
        if(column <0 || column >4){
            throw new columnIndexOutOfBoundsException(column);
        }
        else
        {
            if(freeSpaces(column) < tileList.size()){

                throw new notEnoughFreeSpacesException(column, tileList.size());
            }
            else {
                int firstFreeSpace = 6- freeSpaces(column);
                for (Tile t : tileList){
                    myShelf[firstFreeSpace][column] = t;
                    firstFreeSpace++;
                }
            }
        }
    }

    public Tile getTile(int x, int y){
        return myShelf[x][y];
    }
}
