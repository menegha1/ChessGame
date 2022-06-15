package chess.plateau;
import java.util.ArrayList;
import java.util.List;



import chess.piece.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Plateau{
	
	private ArrayList<List<Case>> grille = new ArrayList<List<Case>>();
	private ArrayList<List<Rectangle>> rectangles = new ArrayList<List<Rectangle>>();
	private ArrayList<List<Button>> buttons = new ArrayList<List<Button>>();
	private ArrayList<Rectangle> rectangleChange = new ArrayList<Rectangle>();
	private ArrayList<Button> buttonChange = new ArrayList<Button>();
	private Player  currentPlayer;
	private ArrayList<Player> listPlayer = new ArrayList<Player>();
	private ArrayList<EventHandler<MouseEvent>>events = new ArrayList<EventHandler<MouseEvent>>();
	private GridPane plateau = new GridPane();
    private Piece currentPiece ;
    private Player ancienPlayer;
    private int tour ;
    
	private boolean bool;
	
	public Plateau()
	{
		for(int i = 0;i<8;i++)
			{
			ArrayList<Case> column = new ArrayList<Case>();
			 for(int j =0;j<8;j++)
				 {
				   Case c = new Case(i,j);
				   column.add(c);
				   }
			 this.grille.add(column);
			}
		this.tour = 0;
	}
	public ArrayList<List<Case>> getGrille()
	{
		return  this.grille;
	}
	public void setCurrentPlayers(Player player)
	{
		this.currentPlayer = player ;
	}
	public Player getCurrentPlayers()
	{
		return this.currentPlayer;
	}
	public void setAncienPlayer(Player player)
	{
		this.ancienPlayer = player;
	}
	public Player getAncienPlayers()
	{
		return this.ancienPlayer;
	}
	public ArrayList<List<Rectangle>> getRectangle()
	{
		return this.rectangles;
	}
	public ArrayList<List<Button>> getButton()
	{
		return this.buttons;
	}
	public void setListPlayer(Player player1, Player  player2)
	{
		
		this.listPlayer.add(player1);
		this.listPlayer.add(player2);
	}
	public List<Player>  getListPlayer()
	{
		return this.listPlayer;
	}
	
	public void setCurrentPiece(Piece piece)
	{
		this.currentPiece = piece;
	}
	public Piece getCurrentPiece()
	{
		return this.currentPiece ;
	}
	public void createPlateau(int number)
	{
		for(int i = 0;i<8;i++)
		{
			ArrayList<Rectangle> columnRec = new ArrayList<Rectangle>();
			ArrayList<Button> columnB = new ArrayList<Button>();
		 for(int j =0;j<8;j++)
			 {
			 Rectangle rec = new Rectangle(number,number);
			 Button  button = new Button(" couleur ");
			 button.setMinSize(number,number);
			 if((i%2 == 1 && j%2 == 1)|| (i%2== 0 && j%2 == 0))
			 {
				 rec.setFill(Color.BEIGE);
				 button.setTextFill(Color.BEIGE);
				 button.setFont(new Font(20));
			 }else {
				rec.setFill(Color.BROWN);
				button.setTextFill(Color.BROWN);
				 button.setFont(new Font(20));
			 }
			 GridPane.setColumnIndex(rec, i);
		     GridPane.setRowIndex(rec, j);  
		     GridPane.setColumnIndex(button, i);
		     GridPane.setRowIndex(button, j); 
		     columnRec.add(rec);
		     columnB.add(button);
		     this.plateau.getChildren().addAll(button);	  
			 }
		 this.rectangles.add(columnRec);
		 this.buttons.add(columnB);
		}
	}
	public GridPane getPlateau()
	{
		return this.plateau;
	}
	
	
	public GridPane initializePiecePlateau(Stage stage , Player player1, Player player2)
	{
		this.setListPlayer(player1, player2);
		
		for(int j =0;j<2;j++)
		{
		  for(int i=0; i< this.listPlayer.get(j).getPieces().size(); i++)
		   {
			Piece p = this.listPlayer.get(j).getPieces().get(i);
			
			/*
		    StackPane s = new StackPane();
			s.getChildren().addAll(p.getImage());
		
		    */
			//GridPane.setColumnIndex(p.getImage(), p.getCurrentCase().getXPosition());
			//GridPane.setRowIndex(p.getImage(), p.getCurrentCase().getYPosition());

			this.plateau.getChildren().addAll(p.getImage());	
		    }
		}
		return this.plateau;
	}

	public Scene showPlateau(Stage stage, Player player)
	{
		VBox root = new VBox(50);
		Label label = new Label(" nombre du tour : " );
		this.currentPlayer = player;
		
		if(tour != 0)
		{
			for(int i = 0;i<8 ; i++)
			{
				for(int j = 0; j<8;j++)
				{
					Case c = this.getGrille().get(i).get(j);
					this.buttons.get(i).get(j).removeEventHandler(MouseEvent.MOUSE_CLICKED, null);
				}
			}
		}
		
		tour = tour + 1;
		System.out.println(" current player : " + this.currentPlayer);
		player.createPieceEvent(stage, player, this);
	
		for(int i=0;i< player.getPieces().size();i++)
		{
			//Case c = currentPlayer.getPieces().get(i).getCurrentCase();
			//player.getPieces().get(i).getImage().addEventHandler(MouseEvent.MOUSE_CLICKED,player.getPlayerEvent().get(i));
			//this.getRectangle().get(c.getXPosition()).get(c.getYPosition()).addEventHandler(MouseEvent.MOUSE_CLICKED,currentPlayer.getPlayerEvent().get(i));
			//this.getButton().get(c.getXPosition()).get(c.getYPosition()).addEventHandler(MouseEvent.MOUSE_CLICKED,currentPlayer.getPlayerEvent().get(i));
		}
	
		root.getChildren().addAll(this.plateau,label);
		Scene scene = new Scene(root,1000,1000);
		return scene;
	}
	
	public Scene nextPlateau( Stage stage  ,Player player)
	{
		VBox root = new VBox(50);
		Scene scene = new Scene(root,1000,1000);
		Label label = new Label(" nombre du tour : ");
		this.showPossibleCase(stage, this.currentPiece);
		
		for(int i=0;i< currentPlayer.getPieces().size();i++)
		{
			//Case c = currentPlayer.getPieces().get(i).getCurrentCase();
			
			//this.getRectangle().get(c.getXPosition()).get(c.getYPosition()).removeEventHandler(MouseEvent.MOUSE_CLICKED,currentPlayer.getPlayerEvent().get(i));
			//this.getButton().get(c.getXPosition()).get(c.getYPosition()).removeEventHandler(MouseEvent.MOUSE_CLICKED,currentPlayer.getPlayerEvent().get(i));
		}
		
		//player.createNextEvent(stage, player, this, this.currentPiece.getCurrentCase());
		int number = 0;
		int a =0;
		for(int i = 0;i<8 ; i++)
		{
			for(int j = 0; j<8;j++)
			{
				/*
				Case c = this.getGrille().get(i).get(j);
				if(c.equals(this.currentPiece.getCurrentCase()) || this.currentPiece.getMovePossible(this).contains(c) )
				  {
					System.out.println(" Event n° " + a + " coordonnee x : "+ i + "  | y : " + j);
					 //this.rectangles.get(i).get(j).addEventHandler(MouseEvent.MOUSE_CLICKED, player.getPlayerNextEvent().get(number));
					 this.buttons.get(i).get(j).addEventHandler(MouseEvent.MOUSE_CLICKED, player.getPlayerNextEvent().get(number));
					 //this.rectangleChange.add(this.rectangles.get(i).get(j));
					 
					  
			}
			*/
		    }
		}
		
		root.getChildren().addAll(this.plateau,label);
		
		return scene;
		 
	}
	
	public void addMovePlateau(Stage stage, Player player)
	{
	
		  for(int i=0; i< player.getPieces().size(); i++)
		   {
			  Piece p = player.getPieces().get(i);
              p.getImage().setOnMouseClicked(player.getPlayerEvent().get(i));
		   };
	}
	public void removeMovePlateau(Stage stage, Player player)
	{
		for(int i=0; i< player.getPieces().size(); i++)
		   {
			 Piece p = player.getPieces().get(i);
			System.out.println(" \nEventHandler avant :  " + p.getImage().getOnMouseClicked().toString() );
			  p.getImage().removeEventHandler(MouseEvent.MOUSE_CLICKED, player.getPlayerEvent().get(i));
			  System.out.println(" \nEventHandler après :  " + p.getImage().getOnMouseClicked().toString() );
			  }
	}
	
	public  void showPossibleCase( Stage stage , Piece piece)
	{
	
	/*
		for(Case c : piece.getMovePossible(this))
		{
			
			this.rectangles.get(c.getXPosition()).get(c.getYPosition()).setFill(Color.RED);
			
			int x = c.getXPosition();
			int y = c.getYPosition();
			Button buttonToColor = this.buttons.get(x).get(y);	
			ColorCase(stage ,buttonToColor, piece);
			
		}	
	*/
	}
	public  void ColorCase(Stage stage ,Button button, Piece piece)
	{
		button.setStyle(" -fx-background-color:#00ff00;");
		button.setOnAction(foo
                -> movePiece(stage,button, piece));
		resetColorCase(stage);
		showPlateau(stage,this.currentPlayer);
	}
	public void movePiece(Stage stage ,Button button, Piece piece)
	{
		//piece.getCurrentCase().removePawn(piece);
		button.setGraphic(piece.getImage());
	}
	
public GridPane resetColorCase(Stage stage)
	
	{
	for(int i = 0;i<8;i++)
	{

	 for(int j =0;j<8;j++)
		 {
		 // Rectangle rec = this.rectangles.get(i).get(j);
		  Button button = this.buttons.get(i).get(j);
		 if((i%2 == 1 && j%2 == 1)|| (i%2== 0 && j%2 == 0))
		 // rec.setFill(Color.BEIGE);
		 button.setTextFill(Color.BEIGE);
		 else {
			//rec.setFill(Color.BROWN);
			button.setTextFill(Color.BROWN);
		 }
			}
		}
		return this.plateau;
	}
/*
public void choixPiece(Stage stage , Case currentCase,Player player)
{
	Piece piece = currentPiece;
	for(int i = 0;i<8 ; i++)
	{
		for(int j = 0; j<8;j++)
		{
			Rectangle rec = this.rectangles.get(i).get(j);
			Case c = this.getGrille().get(i).get(j);
		   if(c.equals(currentCase))
		   {
			   piece.getImage().setOnMouseClicked(new EventHandler<MouseEvent>() {
				   
			        public void handle(MouseEvent t) {
			        	 resetColorCase(stage);
			        	 stage.setScene(showPlateau(4,stage,player));
			        }
					});	
		   }
			else if(c.getPiecesCase().isEmpty() && piece.getMovePossible(this).contains(c))
            {
			rec.setOnMouseClicked(new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent t) {
		        	piece.getCurrentCase().removePawn(piece);
		        	piece.setCurrentCase(c);
	                System.out.println(" case courante " + piece.getCurrentCase());
	                GridPane.setColumnIndex(piece.getImage(), piece.getCurrentCase().getXPosition());
	                GridPane.setRowIndex(piece.getImage(), piece.getCurrentCase().getYPosition());
	                resetColorCase(stage);
	                stage.setScene(showPlateau(4,stage,player));
	                
	                /*
	               
	                if(player.equals(getPlayers().get(0)))
	                {
	                	System.out.println(" JE SUIS LA  \n  ");
	                	removeMovePlateau(stage,getPlayers().get(0));
	                	stage.setScene(showPlateau(4,stage,getPlayers().get(1)));
	                }else 
	                {
	                	System.out.println(" JE SUIS LA  2 \n  ");
	                	removeMovePlateau(stage,getPlayers().get(1));
	                stage.setScene(showPlateau(4,stage,getPlayers().get(0)));
	                }
	                
	                }
				});	

		    }
            }
     }
     */
}
