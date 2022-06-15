package chess.piece;

import java.util.List;

import chess.plateau.Case;
import chess.plateau.EchecRegle;
import chess.plateau.Plateau;
import chess.plateau.PlateauF;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Cheval extends Piece{
	private String name;
	private ImageView image;
	public Cheval( Color color, int xPos, int yPos)
	 {
		 super( color,xPos,yPos);
		 this.name = "cheval";
		 //startCase.addPiece(this);
		 if(color.equals(Color.BLACK))
		 {
	 ImageView i = new ImageView(getClass().getResource("chevalBlack.png").toString());
	 image = i;
		 }else {
 ImageView i = new ImageView(getClass().getResource("chevalWhite.png").toString());
 image = i;
 }
	 }
	public ImageView getImage()
	 {
		 return this.image;
	 }
	 public void getMovePossible(PlateauF plateau ,  EchecRegle ruleChess)
	 {
		 
		plateau.colorSquare(xPos, yPos, true);
		if(ruleChess.getRoiEchec() == false)
		{
		int x;
		for(int y = -2 ; y<= 2 ; y++)
		{
			if(y!= 0)
			{
				if(y<0)
				{
					x =(-y%2)+1;
				}else{x = (y%2)+1;}
				if( this.yPos + y >= 0 && this.yPos+ y< plateau.getHeightBoard() && this.xPos + x>= 0 && this.xPos + x < plateau.getWidthBoard() && plateau.getPosition(this.xPos +x, this.yPos+y ) != this.color && !plateau.isKing(this.xPos + x ,this.yPos + y))
				{
					plateau.colorSquare(xPos+x, this.yPos+y, false);
				}
				if( this.yPos + y >= 0 && this.yPos+ y< plateau.getHeightBoard() && this.xPos - x>= 0 && this.xPos - x < plateau.getWidthBoard() && plateau.getPosition(this.xPos -x , this.yPos+y ) != this.color && !plateau.isKing(this.xPos - x ,this.yPos + y))
				{
					plateau.colorSquare(xPos-x , this.yPos +y, false);
				}
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
	 	return   " Cheval : " ;
	 			
	 }
}
