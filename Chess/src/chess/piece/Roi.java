package chess.piece;

import java.util.ArrayList;
import java.util.List;

import chess.plateau.Case;
import chess.plateau.EchecRegle;
import chess.plateau.Plateau;
import chess.plateau.PlateauF;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Roi  extends Piece {
	private String name;
	private ImageView image;
	public Roi(Color color, int xPos, int yPos)
	 {
		 super(color,xPos,yPos);
		 this.name = "roi";
		 //startCase.addPiece(this);
		 if(color.equals(Color.BLACK))
		 {
	 ImageView i = new ImageView(getClass().getResource("roiBlack.png").toString());
	 image = i;
		 }else {
 ImageView i = new ImageView(getClass().getResource("roiWhite.png").toString());
 image = i;
 }
	 }
	public ImageView getImage()
	 {
		 return this.image;
	 }
	public void getMovePossible(PlateauF plateau ,  EchecRegle ruleChess)
	{
		int caseDispo = 0;
		
		plateau.colorSquare(xPos, yPos, true);
		for( int y = this.yPos-1; y <=  this.yPos+1 ; y++)
		{
			for(int x = this.xPos-1; x<= this.xPos+1 ; x++)
			{
				if( x == this.xPos && y == this.yPos)
				{	
				}else {
					if( y>=0 && y< plateau.getHeightBoard() && x >=0  && x < plateau.getWidthBoard() && plateau.getPosition(x, y) != this.color && !plateau.isKing(x,y))
					{
						if(!ruleChess.isEchec(color, plateau, x, y))
						{
							plateau.colorSquare(x, y, false);
							caseDispo++;
						}	
					}
				}
			}
		}
		if(caseDispo == 0 && ruleChess.getRoiEchec() == true)
		{
			ruleChess.setRoiEchecMat(true);
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
	 	return   " Roi : ";
	 			
	 }
}
