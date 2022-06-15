package chess.piece;

import java.util.ArrayList;
import java.util.List;

import chess.plateau.Case;
import chess.plateau.EchecRegle;
import chess.plateau.Plateau;
import chess.plateau.PlateauF;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Fou extends Piece {
	private String name;
	private ImageView image;
	public Fou( Color color, int xPos, int yPos)
	 {
		 super(color,xPos,yPos);
		 this.name = "fou";
		// startCase.addPiece(this);
		 if(color.equals(Color.BLACK))
		 {
	 ImageView i = new ImageView(getClass().getResource("fouBlack.png").toString());
	 image = i;
		 }else {
 ImageView i = new ImageView(getClass().getResource("fouWhite.png").toString());
 image = i;
 }
		
	 }
	 public ImageView getImage()
	 {
		 return this.image;
	 }
	
	 public void getMovePossible(PlateauF plateau,  EchecRegle ruleChess)
	 {
		 
		 plateau.colorSquare(this.xPos,this.yPos,true);
		 if(ruleChess.getRoiEchec() == false)
		 {
		 int y = this.yPos +1;	 
		for(int x = this.xPos +1; x < plateau.getWidthBoard() && y < plateau.getHeightBoard() ; x++ , y++)
		{
			if(plateau.getPosition(x, y) == null)
			{
				plateau.colorSquare(x, y, false);
			}else {
				if(!plateau.getPosition(x, y).equals(this.color) && !plateau.isKing(x,y))
				{
					plateau.colorSquare(x, y, false);
					
				}
				x = plateau.getWidthBoard();
			}
		}
		
		y = this.yPos -1 ;
		for(int x = this.xPos -1; x >=0 && y >= 0 ; x-- , y--)
		{
			if(plateau.getPosition(x, y) == null)
			{
				plateau.colorSquare(x, y, false);
			}else {
				if(!plateau.getPosition(x, y).equals(this.color) && !plateau.isKing(x,y))
				{
					plateau.colorSquare(x, y, false);
				}
				x = -1;
			}
		}
		y= this.yPos +1;
		 for(int x = this.xPos -1; x >= 0 && y < plateau.getHeightBoard() ; x-- , y++)
		 {
			if(plateau.getPosition(x, y) == null)
			{
				plateau.colorSquare(x, y, false);
			}else {
				if(!plateau.getPosition(x, y).equals(this.color) && !plateau.isKing(x,y))
				{
					plateau.colorSquare(x, y, false);
				}else {
				}
				y = plateau.getWidthBoard();
			}
		 }
		 y= this.yPos -1;
		 for(int x = this.xPos +1; x < plateau.getWidthBoard() && y >= 0 ; x++ , y--)
		 {
			if(plateau.getPosition(x, y) == null)
			{
				plateau.colorSquare(x, y, false);
			}else {
				if(!plateau.getPosition(x, y).equals(this.color) && !plateau.isKing(x,y))
				{
					plateau.colorSquare(x, y, false);
				}
				x = plateau.getWidthBoard();
			}
		 }
		 }
	 }

	 public void relocate(double x, double y) {
			this.image.setTranslateX(x);
			this.image.setTranslateY(y);	
		}
	 public void resize(double width, double height) {
			this.image.setFitWidth(width);
			this.image.setFitHeight(height);
		}
	 public String getName()
	 {
		 return this.name;
	 }
	 public String toString()
	 {
	 	return   " Fou : " ;
	 			
	 }
}
