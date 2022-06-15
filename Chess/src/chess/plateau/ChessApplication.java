package chess.plateau;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChessApplication extends Application{
	private StackPane sp_mainlayout;
	private PlateauF plateauf;
/*
	public void start(Stage stage) { 
	    Alert alert = new Alert(Alert.AlertType.INFORMATION ," ahozieeuazy");
        Plateau plateau = new Plateau();
	    Player player1= new Player("BlackPlayer");
	    Player player2= new Player("WhitePlayer");
	    //plateau.setPlayers(player1, player2);
	    
	    player1.createPieces(Color.BLACK, plateau);
	    player2.createPieces(Color.WHITE, plateau);
	  
        plateau.createPlateau(80);
        plateau.initializePiecePlateau(stage,player1,player2);
        

      Scene scene =  plateau.showPlateau(stage,player2);

     
    stage.setScene(scene);
    stage.show();
    
	}
*/
	@Override
	public void init() {
		// initialize the layout, create a CustomControl and it to the layout
		sp_mainlayout = new StackPane();
		plateauf = new PlateauF();
		sp_mainlayout.getChildren().add(plateauf);
	}
	
	// overridden start method
	@Override
	public void start(Stage primaryStage) {
		// set the title and scene, and show the stage
		primaryStage.setTitle("Chess game");
		primaryStage.setScene(new Scene(sp_mainlayout, 560,560));
		primaryStage.setMinWidth(300);
		primaryStage.setMinHeight(300);
		primaryStage.show();
	}

	// overridden stop method
	@Override
	public void stop() {
	}
	
	// entry point into our program to launch our JavaFX application
	public static void main(String[] args) {
			launch(args);
	}
}
