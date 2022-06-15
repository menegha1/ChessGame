package chess.plateau;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import chess.piece.*;

public class Player {

	private String name;
	private int id;
	private Color color;
	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	
    private ArrayList<EventHandler<javafx.scene.input.MouseEvent>> events= new ArrayList<EventHandler<javafx.scene.input.MouseEvent>>();
    private ArrayList<EventHandler<javafx.scene.input.MouseEvent>> nextEvents= new ArrayList<EventHandler<javafx.scene.input.MouseEvent>>();
	
	public Player(String name, int number, Color color)
	{
		this.name = name;
		this.id = number;
		this.color = color;
		
	}
	public Player(String name)
	{
		this.name = name;
	}
	public void createPieceEvent(Stage stage ,Player player,Plateau plateau)
	{
		for(int i =0; i< 16;i++)
		{
			final int finalI = i ;
		    EventHandler<javafx.scene.input.MouseEvent> pieceHandler = new EventHandler<javafx.scene.input.MouseEvent>(){
	        public void handle(javafx.scene.input.MouseEvent event){
	        	plateau.setCurrentPiece(player.getPieces().get(finalI));
	            stage.setScene(plateau.nextPlateau(stage, player));
	        }
	    };
	    this.events.add(pieceHandler);
	   }
	}
	
	public void createNextEvent(Stage stage ,Player player, Plateau plateau, Case currentCase)
	{
		int a =0;
		Piece piece = plateau.getCurrentPiece();
		for(int i = 0;i<8 ; i++)
		{
			for(int j = 0; j<8;j++)
			{
				final int finalI = i;
				final int finalJ = j;
				final int finalA =a;
				//Rectangle rec =  plateau.getRectangle().get(i).get(j);
				Case caseClicked = plateau.getGrille().get(i).get(j);
				if(caseClicked.equals(currentCase) )
				{
					 System.out.println(" Event n° " + a + " coordonnee x : "+ i + "  | y : " + j);
		              EventHandler<javafx.scene.input.MouseEvent> removeEvent = new EventHandler<javafx.scene.input.MouseEvent>(){
	                  public void handle(javafx.scene.input.MouseEvent event){
	                  System.out.println(" On modif pas" + plateau.getGrille().get(finalI).get(finalJ));
	                  plateau.resetColorCase(stage);
	                  plateau.setAncienPlayer(player);
	                  stage.setScene(plateau.showPlateau( stage, player)); 
	                  }};
	            a++;
		        this.nextEvents.add(removeEvent);
		        
				}
				/*
				else if(piece.getMovePossible(plateau).contains(caseClicked))
	            {
				
					  System.out.println(" Event n° " + a + " coordonnee x : "+ i + "  | y : " + j);
		              EventHandler<javafx.scene.input.MouseEvent> removeEvent2 = new EventHandler<javafx.scene.input.MouseEvent>(){
	                  public void handle(javafx.scene.input.MouseEvent event){
	      
	        	      //piece.getCurrentCase().removePawn(piece);
	        	      //piece.setCurrentCase(caseClicked);
	        	      
                      System.out.println(" on change la pos de la piece  sur la case : " + caseClicked );
                      System.out.println(" valeur event x + " + (int)event.getX() + "valeur y event ; " + (int)event.getY() );
                      System.out.println("on change la pos de la piece  sur la case avec grille  : " + plateau.getGrille().get(finalI).get(finalJ));
                      //GridPane.setColumnIndex(piece.getImage(), piece.getCurrentCase().getXPosition());
                      //GridPane.setRowIndex(piece.getImage(), piece.getCurrentCase().getYPosition());
                      plateau.resetColorCase(stage);
                      plateau.setAncienPlayer(player);
                      if(player.equals(plateau.getListPlayer().get(0)))
                      {
                    	  plateau.setCurrentPlayers(plateau.getListPlayer().get(1));
                      }else {
                    	  
                    	  plateau.setCurrentPlayers(plateau.getListPlayer().get(0));
                      }
                      stage.setScene(plateau.showPlateau(stage,plateau.getCurrentPlayers()));
                      }};
	                this.nextEvents.add(removeEvent2);
	                a++;
				}
				*/
			}
		}
	}
	
	public ArrayList<EventHandler<MouseEvent>> getPlayerEvent()
	{
		return this.events;
	}
	public ArrayList<EventHandler<MouseEvent>> getPlayerNextEvent()
	{
		return this.nextEvents;
	}
	
	/*
	public void createPieces(Color color,Plateau plateau)
	{
		int j = 0;
		int a ,b ;
		if(color.equals(Color.BLACK))
		{
			 a =0;
			 b = 1;
		}else {
			 a = 7;
		     b =6;
		}
		
			this.pieces.add(new Tour(1,color,plateau.getGrille().get(0).get(a)));
			this.pieces.add(new Tour(2,color,plateau.getGrille().get(7).get(a)));

			this.pieces.add(new Cheval(1,color,plateau.getGrille().get(1).get(a)));
			this.pieces.add(new Cheval(2,color,plateau.getGrille().get(6).get(a)));
		
			this.pieces.add(new Fou(1,color,plateau.getGrille().get(2).get(a)));

			this.pieces.add(new Fou(2,color,plateau.getGrille().get(5).get(a)));
			this.pieces.add(new Reine(1,color,plateau.getGrille().get(3).get(a)));
			this.pieces.add(new Roi(1,color,plateau.getGrille().get(4).get(a)));
			
	   for(int i =1;i<9;i++)
	   {
			   this.pieces.add(new Pion(i,color,plateau.getGrille().get(i-1).get(b))); 
	   }   

	}
	*/
	public Piece getTour(int number)
	{
		return this.pieces.get(0+(number-1));
	}
	public Piece getFou(int number)
	{
		return this.pieces.get(3+number);
	}
	public Piece getCheval(int number)
	{
		return this.pieces.get(1+ number);
	}
	public Piece getReine()
	{
		return this.pieces.get(6);
	}
	public Piece getRoi()
	{
		return this.pieces.get(7);
	}
	public Piece getPion(int number)
	{
		return this.pieces.get(7+number);
	}
	public ArrayList<Piece> getPieces()
	{
		return this.pieces;
	}
	public String toString()
	{
		return " name player : " + this.name;
	}
}
