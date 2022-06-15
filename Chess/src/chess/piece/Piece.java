package chess.piece;
import java.util.List;

import chess.plateau.Case;
import chess.plateau.EchecRegle;
import chess.plateau.Plateau;
import chess.plateau.PlateauF;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public abstract class Piece extends Group{

	 protected Color color; 
	 //private String name;
	 private Case startCase;
	 private int number;
	 protected int xPos;
	 protected int yPos;
	 private boolean isFirstTime;
	 private boolean isASavior;
	 private boolean canEat;
	 private ImageView image = new ImageView();
	 
	 
	 public Piece()
	 {
		 
	 }
	 public Piece( Color color, int xPos , int yPos)
	 {
		 
		 this.color = color;
		 this.xPos = xPos;
		 this.yPos = yPos;
		 this.isFirstTime = true;
		 this.canEat = true;
	 }
 
	 public ImageView getImage()
	 {
		 return this.image;
	 }
	 public abstract void getMovePossible(PlateauF plateau,  EchecRegle ruleChess);
	 public abstract String getName();
	
	 /*
	 public void resize(double width, double height) {
			image.setFitWidth(width);
			image.setFitHeight(height);
		}
		*/
	 public void movePiece(PlateauF plateau, int newX , int newY)
	 {
		 int x = this.xPos;
		 int y = this.yPos;
		 //on déplace
		 
		 if( plateau.getPosition(newX, newY) != null)
		 {
			 if(canEat)
			 {
			 plateau.getPiece(newX, newY).capturePiece(plateau);
			 }
			
		 }
		 this.xPos = newX;
		 this.yPos = newY;
		 
		 plateau.setPiece(this.xPos,this.yPos, this);
		 plateau.setBoard(this.xPos,this.yPos, this.color);
		 
		 plateau.setPiece(x, y, null);
		 plateau.setBoard(x, y, null);
		 
		 this.isFirstTime = false;
		 
	 }
	 public void capturePiece(PlateauF plateau)
	 {
			 plateau.getChildren().remove(this.getImage());
	 }
	 public Color getColor()
	 {
		 return this.color;
	 }
	 public int getXPos()
	 {
		 return this.xPos;
	 }
	 public int getYPos()
	 {
		 return this.yPos;
	 }
	 
	 /*
	 public void relocate(double x, double y) {
			image.setTranslateX(x);
			image.setTranslateY(y);	
		}
		*/
	 public void resetPiece()
		{
			this.isFirstTime = true;
			
			this.isASavior = false;
		}
		
	 public boolean isFirstTime() {
			return isFirstTime;
		}
	 public boolean  getCanEat() {
			return canEat;
		}
	 public void setCanEat( boolean canEat)
	 {
		 this.canEat = canEat;
	 }
	 /*
	 public void centerImage() {
	        Image img = image.getImage();
	        if (img != null) {
	            double w = 0;
	            double h = 0;

	            double ratioX = image.getFitWidth() / img.getWidth();
	            double ratioY = image.getFitHeight() / img.getHeight();

	            double reducCoeff = 0;
	            if(ratioX >= ratioY) {
	                reducCoeff = ratioY;
	            } else {
	                reducCoeff = ratioX;
	            }

	            w = img.getWidth() * reducCoeff;
	            h = img.getHeight() * reducCoeff;

	            image.setX((image.getFitWidth() - w) / 2);
	            image.setY((image.getFitHeight() - h) / 2);

	        }
	    }
	    */
	 /*
	  * 
	  *  public Case getCurrentCase()
	 {
		 return this.currentCase;
	 }
	 
	 public void setCurrentCase(Case c )
	 {
		 this.currentCase = c;
		 c.addPiece(this);
	 }
	 */
}
