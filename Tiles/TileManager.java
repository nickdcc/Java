//nick dechant

//This is a class that contains the programming logic for
//how a tile will behave depending on the controls clicked/pushed
//by the user. Each tile has its own x,y position and is stored by
//random order in an array list.

import java.util.*;
import java.awt.*;

public class TileManager{
    private ArrayList<Tile> list; //access arrayList<Tile> in any method
    
  	//Method constructs an arraylist of tiles		  
	 public TileManager(){
        this.list = new ArrayList<Tile>(); //new contructor
    }
    
	 //Method adds 1 tile when they press 'N'
    public void addTile(Tile rect){
        list.add(rect); //add a rectangle to the GUI
    }
    
	 //Method initially draws 20 tiles and random positions
    public void drawAll(Graphics g){
        for (int i = 0; i< list.size(); i++){ //for all tiles, draw them
            list.get(i).draw(g);
            
        }
    }
    
	 //Method raises tile to very top of the drawingPanel
	 //and end of the arrayList when user left-clicks
    public void raise(int x, int y){
        for(int i = list.size()-1; i >= 0; i--){ //for all tiles, start at the top of the arrayList
            Tile t = list.get(i);					//because I don't care about the tile's I can't see
            int x1,x2,y1,y2;
            x1 = t.getX();
            x2 = x1 + t.getWidth(); //logic to see if a given x,y coordinate is
            y1 = t.getY();				//inside a tile
            y2 = y1 + t.getHeight();
            if(x>= x1 && x<= x2 && y>= y1 && y<=y2){ //if inside
                list.remove(i); //remove the current highest tile
                list.add(t);   //and add the one the user clicks to
					 					//the end of the arrayList.
                break;
            } //stop the loop 
        }
    }
    
	 //Method lowers tile to the very bottom of drawingPanel
	 //and the very beginning of the arrayList when user
	 //shift+left clicks
    public void lower(int x, int y){
        for(int i = list.size()-1; i >= 0; i--){
            Tile t = list.get(i);
            int x1,x2,y1,y2;
            x1 = t.getX();  
            x2 = x1 + t.getWidth();
            y1 = t.getY();
            y2 = y1 + t.getHeight();
            if(x>= x1 && x<= x2 && y>= y1 && y<=y2){ //if x,y is inside a tile
                list.remove(i); //remove this one from the top
                list.add(0, t);//put it to the bottom of the GUI
                break; //stop
            }
        }
    }
    
	 //Method deletes the topmost tile that
	 //the coordinates are touching and removes
	 //it from the arrayList when a user right-clicks
    public void delete(int x, int y){
        for(int i = list.size()-1; i >= 0; i--){
            Tile t = list.get(i);
            int x1,x2,y1,y2;
            x1 = t.getX();
            x2 = x1 + t.getWidth();
            y1 = t.getY();
            y2 = y1 + t.getHeight();
            if(x>= x1 && x<= x2 && y>= y1 && y<=y2){
                list.remove(i); //remove just one tile
                break;
            }
        }
    }
    
	 //Method deletes the topmost tile that
	 //the coordinates are touching and removes
	 //any other tiles that the coordinates are  
	 //touching from the arrayList when a user 
	 //right clicks+shift.
    public void deleteAll(int x, int y){
        for (int i = list.size()-1; i >= 0; i--){
            int a = list.get(i).getX(); //get top tile position
            int b = list.get(i).getY();
            int width = list.get(i).getWidth(); //get width and height of tile
            int height = list.get(i).getHeight();
            int x_area = a + (width-1); 
            int y_area = b + (height-1);
            if ((x >= a && x <= x_area) && (y >= b && y <= y_area)){ //if x,y is inside more than 1 rect then
                Tile tile = list.get(i); //don't break, delete all tiles
                list.remove(tile); 
            }
        }
    }
    
	 //Method changes the x,y poition of all
	 //of the tiles in the arrayList and then
	 //shuffles them by a user presses 'S'
    public void shuffle(int width, int height){ //width and height of drawingPanel
        for (int i = 0; i <= list.size()-1; i++){ //for all tiles in the list
            Random r = new Random(); //lets get random
            Tile t = list.get(i);
            int w_tile = list.get(i).getWidth(); //get the width and height of
            int h_tile = list.get(i).getHeight();//a random tile.
            int new_width = width - w_tile; //Must make sure the tile is inside the box
            int new_height = height - h_tile;
            int x = r.nextInt(new_width);
            int y = r.nextInt(new_height);//get random coordinates
            list.get(i).setX(x);
            list.get(i).setY(y); //set randoms
            Collections.shuffle(list);//sort
        }
    }
}




