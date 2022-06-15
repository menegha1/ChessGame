package chess.plateau;

import java.util.ArrayList;


import chess.piece.Piece;
import javafx.scene.paint.Color;

public class EchecRegle {

	private int numberEchec;
	private boolean roiEchec;
	private boolean roiEchecMat;
	private ArrayList<int []>  sidePossibleProtecting= new ArrayList<int[]>();
	private ArrayList<int []>  diagonalePossibleProtecting= new ArrayList<int []>();
	
	
	 public EchecRegle()
	 {
		 
	 }
	public int getNumberEchec()
	{
		return this.numberEchec;
	}
	public boolean isEchecMat(PlateauF plateau)
	{
        int caseDispo = 0;
        Piece king = plateau.getKingPlayer();
        int xPos = king.getXPos();
        int  yPos = king.getYPos();
		for( int y = yPos-1; y <=  yPos+1 ; y++)
		{
			for(int x = xPos-1; x<= xPos+1 ; x++)
			{
				if( x == xPos && y == yPos)
				{	
				}else {
					if( y>=0 && y< plateau.getHeightBoard() && x >=0  && x < plateau.getWidthBoard() && plateau.getPosition(x, y) != king.getColor() && !plateau.isKing(x,y))
					{
						if(!this.isEchec(king.getColor(), plateau, x, y))
						{
							caseDispo++;
						}	
					}
				}
			}
		}
		if(caseDispo == 0 && this.isRoiEchec(plateau) == true)
		{
			return true;
		}else { 
			return false;
		}
	}
	public boolean isRoiEchec(PlateauF plateau)
	{
		Piece king = plateau.getKingPlayer();
		if(this.isEchec(king.getColor(), plateau, king.getXPos() , king.getYPos()))
		{
			this.roiEchec = true;
			System.out.println("\n LE ROI EST EN ECHEC \n");
			return true;
		}
		return false;
		
	}
	public  boolean getRoiEchec()
	{
		return this.roiEchec;
	}
	public void setRoiEchec(boolean bool)
	{
		this.roiEchec = bool;
	}
	public  boolean getRoiEchecMat()
	{
		return this.roiEchecMat;
	}
	public void setRoiEchecMat(boolean bool)
	{
		this.roiEchecMat = bool;
	}
	 public final boolean isEchec(Color color,PlateauF plateau,int x , int y)
	 {
		
		this.numberEchec = 0;
		
		if(this.sideEchec(plateau, x , y ) == true ||  this.diagonaleEchec(plateau,x , y)== true)
		{
			return true;
		}
		return false;
		
	 }
	 public ArrayList<int[]> getSideProtecting()
	 {
		 return this.sidePossibleProtecting;
	 }
	 public ArrayList<int[]> getDiagonaleProtecting()
	 {
		 return this.diagonalePossibleProtecting;
	 }
	 public boolean sideEchec(PlateauF plateau , int coX , int coY)
	 {
		 Piece king = plateau.getKingPlayer();
		 int xPos = coX;
		 int yPos = coY;
		 for( int x = xPos+1 ; x < plateau.getHeightBoard() ; x++)
		 {
			 if(plateau.getPosition(x, yPos) != null)
			 {
				 if(plateau.getPosition(x, yPos).equals(king.getColor()))
				 {
					 x = plateau.getWidthBoard();
				 } else if(plateau.getPiece(x, yPos).getName().equals("tour") || plateau.getPiece(x, yPos).getName().equals("reine"))
				 {
					 this.numberEchec ++;
					 int[] position = new int[2];
						position[0]= x ;
						position[1] = yPos;
						System.out.println(" \n On ajoute la case de coordonnee3 ; x : " + x + " | y  : " + yPos);
						this.diagonalePossibleProtecting.add(position);
					 return true;
			     }
			 }
			 int[] position = new int[2];
				position[0]= x ;
				position[1] = yPos;
				this.sidePossibleProtecting.add(position);
		}
		 this.numberEchec = 0;
		 for( int x = xPos-1 ; x >= 0 ; x--)
		 {
			 if(plateau.getPosition(x, yPos) != null)
			 {
				 if(plateau.getPosition(x, yPos).equals(king.getColor()))
				 {
					 x = -1;
			      }else if(plateau.getPiece(x, yPos).getName().equals("tour") || plateau.getPiece(x, yPos).getName().equals("reine"))
				 {
			    	  int[] position = new int[2];
						position[0]= x ;
						position[1] = yPos;
						System.out.println(" \n On ajoute la case de coordonnee3 ; x : " + x + " | y  : " + yPos);
						this.diagonalePossibleProtecting.add(position);
			    	  this.numberEchec ++;
			    	  return true;
			     }
		   }
			 int[] position = new int[2];
				position[0]= x ;
				position[1] = yPos;
				this.sidePossibleProtecting.add(position);
		 }
		 this.numberEchec = 0;
		 for( int y = yPos+1 ; y < plateau.getHeightBoard() ; y++)
		 {
			 if(plateau.getPosition(xPos , y) != null)
			 {
				 if(plateau.getPosition(xPos,y).equals(king.getColor()))
				 {
					 y = plateau.getWidthBoard();
				 } else if(plateau.getPiece(xPos, y).getName().equals("tour") || plateau.getPiece(xPos,y).getName().equals("reine"))
				 {
					 int[] position = new int[2];
						position[0]= xPos ;
						position[1] = y;
						System.out.println(" \n On ajoute la case de coordonnee3 ; x : " + xPos + " | y  : " + y);
						this.diagonalePossibleProtecting.add(position);
					 this.numberEchec ++;
					 return true;
			     }
		     }
			 int[] position = new int[2];
				position[0]= xPos;
				position[1] = y;
				this.sidePossibleProtecting.add(position);
		 }
		 this.numberEchec = 0;
		 for( int y = yPos-1 ; y >= 0 ; y--)
		 {
			 if(plateau.getPosition(xPos, y) != null)
			 {
				 
				 if(plateau.getPosition(xPos,y).equals(king.getColor()))
				 {
					 y = -1;
				 } else if(plateau.getPiece(xPos, y).getName().equals("tour") || plateau.getPiece(xPos,y).getName().equals("reine"))
				 {
					 int[] position = new int[2];
						position[0]= xPos;
						position[1] = y;
						System.out.println(" \n On ajoute la case de coordonnee3 ; x : " + xPos + " | y  : " + y);
						this.diagonalePossibleProtecting.add(position);
					 this.numberEchec ++;
					 return true;
			     }
			 }
			 int[] position = new int[2];
				position[0]= xPos ;
				position[1] = y;
				this.sidePossibleProtecting.add(position);
		 }
		 return false;
	 }
	 public boolean diagonaleEchec(PlateauF plateau ,int coX , int coY)
	 {
		 Piece king = plateau.getKingPlayer();
		 int xPos = coX;
		 int yPos = coY;
		 int y = yPos +1;	
		 this.numberEchec = 0;
			for(int x = xPos +1; x < plateau.getWidthBoard() && y < plateau.getHeightBoard() ; x++ , y++)
			{
				
				if(plateau.getPosition(x, y) != null)
				 {
					
					if(plateau.getPosition(x, y).equals(king.getColor()))
					 {
						 x = plateau.getWidthBoard();
				      }else if(plateau.getPiece(x,y).getName().equals("fou") || plateau.getPiece(x,y).getName().equals("reine"))
					 {
				    	  this.numberEchec ++;
				    	  return true;
					 }
				 }
				if(x < plateau.getWidthBoard() && y < plateau.getHeightBoard() && xPos == king.getXPos() && yPos == king.getYPos())
				{
				int[] position = new int[2];
				position[0]= x ;
				position[1] = y;
				System.out.println(" \n On ajoute la case de coordonnee1 ; x : " + x + " | y  : " + y);
				this.diagonalePossibleProtecting.add(position);
				}
			}

			this.numberEchec = 0;
			y = yPos -1 ;
			for(int x = xPos -1; x >=0 && y >= 0 ; x-- , y--)
			{
				if(plateau.getPosition(x, y) != null)
				 {
					
					if(plateau.getPosition(x, y).equals(king.getColor()))
					 {
						 x = -1;
				      }else if(plateau.getPiece(x,y).getName().equals("fou") || plateau.getPiece(x,y).getName().equals("reine"))
					 {
				    	  this.numberEchec ++;
				    	  return true;
					 }
				 }
				if(x>= 0 && y>= 0 && xPos == king.getXPos() && yPos == king.getYPos())
				 {
				int[] position = new int[2];
				position[0]= x ;
				position[1] = y;
				System.out.println(" \n On ajoute la case de coordonnee2 ; x : " + x + " | y  : " + y);
				this.diagonalePossibleProtecting.add(position);
				 }
				}
			this.numberEchec = 0;
			y= yPos +1;
			for(int x = xPos -1; x >= 0 && y < plateau.getHeightBoard() ; x-- , y++)
			{
				if(plateau.getPosition(x, y) != null)
				 {
					if(plateau.getPosition(x, y).equals(king.getColor()))
					 {
						 y = plateau.getWidthBoard();
				      }else if(plateau.getPiece(x,y).getName().equals("fou") || plateau.getPiece(x,y).getName().equals("reine"))
					 {
				    	  this.numberEchec ++;
				    	  int[] position = new int[2];
							position[0]= x ;
							position[1] = y;
							System.out.println(" \n On ajoute la case de coordonnee3 ; x : " + x + " | y  : " + y);
							this.diagonalePossibleProtecting.add(position);
				    	  
				    	  return true;
					 }
				 }
				if(x >= 0 && y < plateau.getHeightBoard() && xPos == king.getXPos() && yPos == king.getYPos())
				{
				int[] position = new int[2];
				position[0]= x ;
				position[1] = y;
				System.out.println(" \n On ajoute la case de coordonnee3 ; x : " + x + " | y  : " + y);
				this.diagonalePossibleProtecting.add(position);
				}
			}
			this.numberEchec = 0;
			y= yPos -1;
			for(int x = xPos +1; x < plateau.getWidthBoard() && y >= 0 ; x++ , y--)
			{
				if(plateau.getPosition(x, y) != null)
				 {
					if(plateau.getPosition(x, y).equals(king.getColor()))
					 {
						 x = plateau.getWidthBoard();
				      }else if(plateau.getPiece(x,y).getName().equals("fou") || plateau.getPiece(x,y).getName().equals("reine"))
					 {
				    	  this.numberEchec ++;
				    	  int[] position = new int[2];
							position[0]= x ;
							position[1] = y;
							System.out.println(" \n On ajoute la case de coordonnee3 ; x : " + x + " | y  : " + y);
							this.diagonalePossibleProtecting.add(position);
				    	  return true;
					 }
				 }
				if(x < plateau.getWidthBoard() && y >= 0 && xPos == king.getXPos() && yPos == king.getYPos() )
				{
				int[] position = new int[2];
				position[0]= x ;
				position[1] = y;
				System.out.println(" \n On ajoute la case de coordonnee4 ; x : " + x + " | y  : " + y);
				this.diagonalePossibleProtecting.add(position);
				}
			}
			this.numberEchec = 0;
			if(king.getColor().equals(Color.BLACK))
			{
				if(xPos+1 < plateau.getHeightBoard() &&  yPos+1 < plateau.getHeightBoard() )
				{
					if(plateau.getPosition(xPos+1,yPos+1) != null && !plateau.getPosition(xPos+1,yPos+1).equals(king.getColor()) && plateau.getPiece(xPos+1,yPos+1).getName().equals("pion"))
					{
						this.numberEchec ++;
						return true;
					}
				}
				if(xPos-1 >= 0 &&  yPos+1 < plateau.getHeightBoard() )
				{
					if(plateau.getPosition(xPos-1,yPos+1) != null &&  !plateau.getPosition(xPos+1,yPos+1).equals(king.getColor()) && plateau.getPiece(xPos-1,yPos+1).getName().equals("pion"))
					{
						this.numberEchec ++;
						return true;
					}
				}
			}else {
				if(xPos+1 < plateau.getHeightBoard()  &&  yPos-1 >= 0 )
				{
					if(plateau.getPosition(xPos+1,yPos-1) != null && !plateau.getPosition(xPos+1,yPos-1).equals(king.getColor()) && plateau.getPiece(xPos+1,yPos-1).getName().equals("pion"))
					{
						this.numberEchec ++;
						return true;
					}
				}
				if(xPos-1 >= 0  &&  yPos-1 >= 0 )
				{
					if(plateau.getPosition(xPos-1,yPos-1) != null &&  !plateau.getPosition(xPos-1,yPos-1).equals(king.getColor()) && plateau.getPiece(xPos-1,yPos-1).getName().equals("pion"))
					{
						this.numberEchec ++;
						return true;
					}
				}
			}
			return false;
	 }
	 
	 public boolean SideListContainCase(int x , int y )
	 {
		 for( int i = 0; i < this.sidePossibleProtecting.size(); i++)
		 {
			 if(this.sidePossibleProtecting.get(i)[0] == x && this.sidePossibleProtecting.get(i)[1] == y )
			 {
				 return true;
			 }
		 }
		 return false;
	 }
	 public boolean DiagonaleListContainCase(int x , int y )
	 {
		 for( int i = 0; i < this.diagonalePossibleProtecting.size(); i++)
		 {
			 if(this.diagonalePossibleProtecting.get(i)[0] == x && this.diagonalePossibleProtecting.get(i)[1] == y )
			 {
				 return true;
			 }
		 }
		 return false;
	 }
}
