package chess.plateau;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.transform.Translate;

public class CaseAffichage extends Group{

	private Rectangle r ;
	private Translate pos;
	private boolean isHighlight;
	
	public CaseAffichage (int i)
	{
		pos = new Translate();
		r = new Rectangle(80,80);
		r.getTransforms().add(pos);
		if(i==0)
		r.setFill(Color.BROWN);
		else
		{
		r.setFill(Color.BEIGE);
		}
		getChildren().add(r);
	}
	public Rectangle getRectangle() {
		return (r);
	}
	
	public void highlightSquare(Color color)
	{
		this.r.setStrokeType(StrokeType.INSIDE);
		this.r.setStrokeWidth(6);
		this.r.setStroke(color);
		this.isHighlight = true;
	}
	public void unHighlightSquare()
	{
		if(this.isHighlight)
		{
		this.r.setStroke(null);
		this.isHighlight = false;
		}
	}
	public void resize(double width, double height) {
		// call the super class method and update the height and the width of the rectangle representing the window 
		super.resize(width, height);
		r.setHeight(height);
		r.setWidth(width);
	}
	public boolean isHighlightSquare()
	{
		return this.isHighlight;
	}
}
