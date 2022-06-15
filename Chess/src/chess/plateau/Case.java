package chess.plateau;



import java.util.ArrayList;
import java.util.List;
import chess.piece.*;
import javafx.scene.paint.Color;

public class Case {
   private int xPosition;
   private int yPosition;
   private Color color;
   private List<Piece> pieces = new ArrayList<Piece>();
   
   public Case(int x, int y ,Color color)
   
   {
	   this.xPosition = x;
	   this.yPosition = y; 
	  this.color = color;
   }
   public Case(int x, int y )
   {
	   this.xPosition = x;
	   this.yPosition = y; 
   }
   public Case()
   {   
   }
   public int getXPosition()
   {
	   return this.xPosition;
   }
   public int getYPosition()
   {
	   return this.yPosition;
   }
   public void setXPosition(int x)
   {
	   this.xPosition = x;
   }
   public void setYPosition(int y)
   {
	  this.yPosition = y;
   }
   public void addPiece(Piece piece)
   {
		pieces.add(piece);
	}

	public void removePawn(Piece piece) {
		pieces.remove(piece);
	}
	public List<Piece> getPiecesCase()
	{
		return this.pieces;
	}
	
   public ArrayList<Integer> getPosition()
   {
	   
	   ArrayList<Integer> position = new ArrayList<Integer>();
	   position.add(this.getXPosition());
	   position.add(this.getYPosition());
	   return  position;
   }
public Color getColor() {
	return color;
}

public String toString()
{
	return  " coordonnee x : " + this.xPosition + " | coordonnee y : " + this.yPosition ;
			
}
}

