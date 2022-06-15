package chess.piece;

import java.util.ArrayList;
import java.util.List;

import chess.plateau.Case;
import chess.plateau.EchecRegle;
import chess.plateau.Plateau;
import chess.plateau.PlateauF;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Pion extends Piece {
	
	private String name;
	private ImageView image;

	public Pion( Color color, int xPos, int yPos)
	 {
		 super(color,xPos,yPos);
		 this.name = "pion";
		 //startCase.addPiece(this);
		 if(color.equals(Color.BLACK))
				 {
			 ImageView i = new ImageView(getClass().getResource("pionBlack.png").toString());
			 image = i;
				 }else {
		 ImageView i = new ImageView(getClass().getResource("pionWhite.png").toString());
		 image = i;
		 }
		 this.setCanEat(false);
		 
	 }
	 public ImageView getImage()
	 {
		 return this.image;
	 }
	 
	 public void getMovePossible(PlateauF plateau ,EchecRegle ruleChess)
	 {
		 this.setCanEat(false);
		 
		 plateau.colorSquare(this.xPos, this.yPos, true);
		 
		 
		 
			    if(this.color.equals(Color.WHITE))
				 {
			    	if(plateau.getPosition(xPos, yPos-1) == null)
			    	{
					   if(this.isFirstTime() == true && plateau.getPosition(xPos, yPos-2) == null)
					   {
						   if(ruleChess.getRoiEchec() == false)
						   {
							   plateau.colorSquare(xPos, yPos-2, false);
						   }else {
							    if(ruleChess.DiagonaleListContainCase(xPos, yPos-2))
							    {
							    	plateau.colorSquare(xPos, yPos-2, false);
							    }
						   }
					   }
					   if(ruleChess.getRoiEchec() == false)
					   {
						   plateau.colorSquare(xPos, yPos-1, false);
					   }else {
						    if(ruleChess.DiagonaleListContainCase(xPos, yPos-1))
						    {
						    	plateau.colorSquare(xPos, yPos-1, false);
						    }
					   }
			    	}
			    	if( xPos -1 >= 0 && yPos >= 0 && plateau.getPosition(xPos-1, yPos-1) != null && !plateau.getPosition(xPos-1, yPos-1).equals(this.color) && !plateau.isKing(xPos-1, yPos-1))
			    	{
			    		if(ruleChess.getRoiEchec() == false)
						   {
							   plateau.colorSquare(xPos-1, yPos-1, false);
							   this.setCanEat(true);
						   }else {
							    if(ruleChess.DiagonaleListContainCase(xPos-1, yPos-1))
							    {
							    	plateau.colorSquare(xPos-1, yPos-1, false);
							    	this.setCanEat(true);
							    }
						   }
			    	}
			    	if( xPos +1 < plateau.getWidthBoard() && yPos >= 0 && plateau.getPosition(xPos+1, yPos-1) != null && !plateau.getPosition(xPos+1, yPos-1).equals(this.color) && !plateau.isKing(xPos+1, yPos-1))
			    	{
			    		if(ruleChess.getRoiEchec() == false)
						   {
							   plateau.colorSquare(xPos+1, yPos-1, false);
							   this.setCanEat(true);
						   }else {
							    if(ruleChess.DiagonaleListContainCase(xPos+1, yPos-1))
							    {
							    	plateau.colorSquare(xPos+1, yPos-1, false);
							    	this.setCanEat(true);
							    }
						   }
			    	}
				
				 }else if ( this.color.equals(Color.BLACK))
				 {
					 if(plateau.getPosition(xPos, yPos+1) == null)
					 {
					   if(this.isFirstTime() == true && plateau.getPosition(xPos, yPos+2) == null)
					   {
						   if(ruleChess.getRoiEchec() == false)
						   {
							   plateau.colorSquare(xPos, yPos+2, false);
						   }else {
							    if(ruleChess.DiagonaleListContainCase(xPos, yPos+2))
							    {
							    	plateau.colorSquare(xPos, yPos+2, false);
							    }
						   }
					   }
					   if(ruleChess.getRoiEchec() == false)
					   {
						   plateau.colorSquare(xPos, yPos+1, false);
					   }else {
						    if(ruleChess.DiagonaleListContainCase(xPos, yPos+1))
						    {
						    	plateau.colorSquare(xPos, yPos+1, false);
						    }
					   }
					 }
					 
					 if( xPos +1 < plateau.getWidthBoard() && yPos +1 < plateau.getWidthBoard() && plateau.getPosition(xPos+1, yPos+1) != null && !plateau.getPosition(xPos+1, yPos+1).equals(this.color) && !plateau.isKing(xPos+1, yPos+1))
				    	{
						 if(ruleChess.getRoiEchec() == false)
						   {
							   plateau.colorSquare(xPos+1, yPos+1, false);
							   this.setCanEat(true);
						   }else {
							    if(ruleChess.DiagonaleListContainCase(xPos+1, yPos+1))
							    {
							    	plateau.colorSquare(xPos+1, yPos+1, false);
							    	this.setCanEat(true);
							    }
						   }
				    	}
					 if(xPos -1 >= 0 && yPos +1 < plateau.getWidthBoard() &&  plateau.getPosition(xPos-1, yPos+1) != null && !plateau.getPosition(xPos-1, yPos+1).equals(this.color) && !plateau.isKing(xPos-1, yPos+1))
				    	{
						 if(ruleChess.getRoiEchec() == false)
						   {
							   plateau.colorSquare(xPos-1, yPos+1, false);
							   this.setCanEat(true);
						   }else {
							    if(ruleChess.DiagonaleListContainCase(xPos-1, yPos+1))
							    {
							    	plateau.colorSquare(xPos-1, yPos+1, false);
							    	this.setCanEat(true);
							    }
						   }
				    	}
				 }
	
	 }
	 public void relocate(double x, double y) {
			image.setTranslateX(x);
			image.setTranslateY(y);	
		}
	 public void resize(double width, double height) {
			image.setFitWidth(width);
			image.setFitHeight(height);
		}
	
	 public String getName()
	 {
		 return this.name;
	 }
	 
	 public String toString()
	 {
	   	return   " Pion : " ;
	 			
	 }
}
	 
