package chess.plateau;

import chess.piece.Cheval;
import chess.piece.Fou;
import chess.piece.Piece;
import chess.piece.Pion;
import chess.piece.Reine;
import chess.piece.Roi;
import chess.piece.Tour;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlateauF extends Pane {
	private Rectangle background;
	private int boardWidth = 8;
	private int boardHeight = 8;
	private boolean isBlack;
	private final int EMPTY = 0;
	private Color[][] board;
	private int cell_height;
	private int cell_width;
	private Color currentPlayer;
	private Color white_player = Color.WHITE;
	private Color  black_player= Color.BLACK;
	private static final int dragViewOffset = 25;
	private Piece currentPiece;
    private Piece Roi_BLACK ;
    private Piece Roi_WHITE;
	
	private Piece[][] pieces;
	private CaseAffichage [][] caseAffichage;
	private EchecRegle ruleChess = new EchecRegle();

	
	public PlateauF() {
		
		background = new Rectangle();
		background.setFill(Color.WHITE);
		getChildren().add(background);
		
		board = new Color[boardWidth][boardHeight];
		pieces = new Piece[boardWidth][boardHeight];

		caseAffichage = new CaseAffichage[boardWidth][boardHeight];
		for (int i = 0; i < 8; i++) {
			if(i%2 == 0 || i ==0){
				isBlack =false;
			}
			else 
				isBlack = true;
			for (int j = 0; j < 8; j++) {
				board[i][j] = null;
				if(isBlack){
					caseAffichage[i][j] = new CaseAffichage(0); //Black 
					isBlack=false;
				}else{
					caseAffichage[i][j] = new CaseAffichage(1); //White
					isBlack = true; 
				}
				/*
				GridPane.setColumnIndex(caseAffichage[i][j], i);
				GridPane.setRowIndex(caseAffichage[i][j], j);
				*/
				getChildren().add(caseAffichage[i][j]);
				pieces[i][j] = null;
			}
		}
		
		initPiece();
		this.currentPlayer = white_player; // valeur 1;
		setOnDragDetected(this::onDragDetected);
		setOnDragOver(this::onDragOver);
		setOnDragDropped(this::onDragDropped);
		setOnDragDone(this::onDragDone);
		
		int height =560;
		int width = 560;
		background.setWidth(height);
		background.setHeight(width);
		
		cell_width = (int) (width / 8.0);
		cell_height = (int) (height / 8.0);
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				
				if (board[i][j] != null) {
					
					pieces[i][j].relocate(i * cell_width, j * cell_height);
					pieces[i][j].resize(cell_width, cell_height);
					
				}
				caseAffichage[i][j].relocate(i * cell_width, j * cell_height);
				caseAffichage[i][j].resize(cell_width, cell_height);
				
			}
		}
		
	
	}
	private void onDragDetected(MouseEvent e) {
		
		
	        
			Dragboard db = startDragAndDrop(TransferMode.MOVE);
			int indexX = (int) (e.getX()/ cell_width);
			int indexY = (int) (e.getY()/ cell_height);
			currentPiece = pieces[indexX][indexY];
			this.ruleChess.setRoiEchec(false);
			this.ruleChess.getDiagonaleProtecting().clear();
			this.ruleChess.getSideProtecting().clear();
			
			
			if(this.ruleChess.isRoiEchec(this) == true)
			{
				this.ruleChess.setRoiEchec(true);
				
			}
		
			
			/*
				if( pieces[indexX][indexY] != null && pieces[indexX][indexY].equals(this.getKingPlayer()))
				{
					db.setDragView( currentPiece.getImage().getImage());
				    db.setDragViewOffsetX(dragViewOffset);
				    db.setDragViewOffsetY(dragViewOffset);
				    ClipboardContent content = new ClipboardContent();
				    content.put(DataFormat.IMAGE,currentPiece.getImage().getImage() );
				    db.setContent(content);
				    System.out.println(" je suis dans detected ");
				    currentPiece.getMovePossible(this);
				}
			*/
			
		        if(pieces[indexX][indexY] != null && board[indexX][indexY].equals(currentPlayer))
		        {
			
			    db.setDragView( currentPiece.getImage().getImage());
			    db.setDragViewOffsetX(dragViewOffset);
			    db.setDragViewOffsetY(dragViewOffset);
			    ClipboardContent content = new ClipboardContent();
			    content.put(DataFormat.IMAGE,currentPiece.getImage().getImage() );
			    db.setContent(content);
			    currentPiece.getMovePossible(this,ruleChess);
		        }
			
			e.consume();
		  
		}
	
	private void onDragOver(DragEvent e) {
		
		if (e.getDragboard().hasContent(DataFormat.IMAGE)) {
			e.acceptTransferModes(TransferMode.MOVE);
		}
		e.consume();
	}
	
	private void onDragDropped(DragEvent e)
	{
		Dragboard db = e.getDragboard();
		boolean success = false;
		if(db.hasContent(DataFormat.IMAGE))
		{
			success = true;
		}
	    e.setDropCompleted(success);
	    int indexX = (int) (e.getX()/ cell_width);
		int indexY = (int) (e.getY()/ cell_height);
		int x = currentPiece.getXPos();
		int y = currentPiece.getYPos();
		
	    if(caseAffichage[indexX][indexY].isHighlightSquare() && caseAffichage[indexX][indexY] != caseAffichage[x][y])
	    {
	        currentPiece.relocate(indexX*cell_width,indexY*cell_height);
	        unHighlightPlateau();
            currentPiece.movePiece(this, indexX, indexY);// on bouge la piece
           
          // currentPiece.capturePiece(this);
           
           // on change de joueur
           if(currentPlayer == black_player)
           {
         	currentPlayer = white_player; 
           }else { currentPlayer = black_player; } 
           
           /*
           if(this.ruleChess.isEchecMat(this))
 		  {
 			 
 			   System.out.println( " \n LA PARTI EST FINI ");
 		  }
*/
	    }else {
	    	// si on depose pas sur une case possible , ça revient a 0
	    	unHighlightPlateau();
	    }
	    e.consume();
	    
	}
	
	private void onDragDone(DragEvent e)
	{
		Dragboard db = e.getDragboard();
		if(db.hasContent(DataFormat.IMAGE))
		{
		  
		}
		e.consume();
	}
	
    public void initPiece()
    {
    	Pion[][] pions = new Pion[2][8];
    	
      Color color = Color.BLACK;
      
    	Tour Tour_Black_1 = new Tour(color,0,0);
    	Tour Tour_Black_2 = new Tour(color,7,0);
    	
    	Cheval Cheval_Black_1 = new Cheval(color,1,0);
    	Cheval Cheval_Black_2 = new Cheval(color,6,0);
    	
    	Fou Fou_Black_1 = new Fou(color,2,0);
    	Fou Fou_Black_2 = new Fou(color,5,0);
    	Reine  Reine_Black = new Reine(color,3,0);
    	Roi_BLACK = new Roi(color,4,0);
    	
    	for(int i =0; i<8 ;i++)
    	{
    	      pions[0][i] = new Pion(color,i,1);
    	      pieces[i][1] = pions[0][i];
    	}
    	
    	
    color = Color.WHITE;
        Tour Tour_White_1 = new Tour(color,0,7);
 	    Tour Tour_White_2 = new Tour(color,7,7);
 	
 	    Cheval Cheval_White_1 = new Cheval(color,1,7);
     	Cheval Cheval_White_2 = new Cheval(color,6,7);
 	
 	    Fou Fou_White_1 = new Fou(color,2,7);
     	Fou Fou_White_2 = new Fou(color,5,7);
     	Reine  Reine_White = new Reine(color,3,7);
     	Roi_WHITE = new Roi(color,4,7);
     	for(int i =0; i<8 ;i++)
    	{
    	      pions[1][i] = new Pion(color,i,6);
    	      pieces[i][6] = pions[1][i];
    	}
    	

     	for (int y = 2; y < 6; y++)
		{
			for (int x = 0; x < boardWidth; x++)
			{
				pieces[x][y] = null;
			}
		}
		
     	pieces[0][0] = Tour_Black_1;
		pieces[1][0] = Cheval_Black_1;
		pieces[2][0] = Fou_Black_1;
		pieces[3][0] = Reine_Black;
		pieces[4][0] = Roi_BLACK;
		pieces[5][0] = Fou_Black_2;
		pieces[6][0] = Cheval_Black_2;
		pieces[7][0] = Tour_Black_2 ;
		
		pieces[0][7] = Tour_White_1;
		pieces[1][7] = Cheval_White_1;
		pieces[2][7] = Fou_White_1;
		pieces[3][7] =  Reine_White;
		pieces[4][7] = Roi_WHITE ;
		pieces[5][7] = Fou_White_2;
		pieces[6][7] = Cheval_White_2;
		pieces[7][7] = Tour_White_2;
		
		for (int y = 0; y < boardHeight; y++)
		{
			for (int x = 0; x < boardWidth; x++)
			{
				if (y == 0 || y == 1)
					board[x][y] = Color.BLACK; // couleur  noir 
				else if (y == 6 || y == 7)
					board[x][y] = Color.WHITE; // couleur blanche
				else
					board[x][y] = null;
			}
		}
		for(int i = 0; i < 8; i++){
			/*
			GridPane.setColumnIndex(pieces[i][0].getImage(), i);
			GridPane.setColumnIndex(pieces[i][7].getImage(), i);
			GridPane.setColumnIndex(pieces[i][1].getImage(), i);
			GridPane.setColumnIndex(pieces[i][6].getImage(), i);
			GridPane.setRowIndex(pieces[i][0].getImage(), 0);
			GridPane.setRowIndex(pieces[i][7].getImage(), 7);
			GridPane.setRowIndex(pieces[i][1].getImage(), 1);
			GridPane.setRowIndex(pieces[i][6].getImage(), 6);
			*/
			getChildren().addAll(pieces[i][0].getImage(), pieces[i][7].getImage(), pieces[i][1].getImage(),pieces[i][6].getImage());
		}
    }
	 public Color getPosition(int x , int y )
	 {
		 return this.board[x][y];
	 }
	public void  colorSquare(int x, int y , boolean selectedPiece)
	{
		if (selectedPiece)
		     caseAffichage[x][y].highlightSquare(Color.ORANGE);
		else
			caseAffichage[x][y].highlightSquare(Color.GREEN);	
	}
	public int getWidthBoard()
	{
		return this.boardWidth;
	}
	public int getHeightBoard()
	{
		return this.boardHeight;
	}
	public void setPiece(int x , int y, Piece piece)
	{
		this.pieces[x][y] = piece;
	}
	public void setBoard(int x , int y , Color color)
	{
		this.board[x][y] = color;
	}
	public Piece getPiece(int x , int y)
	{
		return this.pieces[x][y];
				
	}
	public boolean isKing(int x , int y)
	{
		if( this.pieces[x][y] == Roi_BLACK  || this.pieces[x][y] == Roi_WHITE)
		{
			return true;
		}else {
			return false;
		}
	}
	public Piece getKingPlayer()
	{
		if(this.currentPlayer.equals(white_player))
		{
			return this.Roi_WHITE;
		}
		else {
			return this.Roi_BLACK;
		}
	}
	public void unHighlightPlateau()
	{
		for(int y=0; y< this.getHeightBoard() ; y++)
		{
			for(int x=0;x< this.getWidthBoard() ; x++)
			{
				caseAffichage[x][y].unHighlightSquare();
			}
		}
	}

}
