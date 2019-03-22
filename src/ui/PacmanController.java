/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Icesi University (Cali - Colombia)
 * TIC Department- Algorithms and programming II
 * Third Lab
 * @Author: Christian Flor christian.flor1@correo.icesi.edu.co> 
 * @Date: 9 March 2019
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Game;
import threads.PacmanThread;
import threads.Refresh;

public class PacmanController {

	public static final int WIDTH = 800;
	public static final int HEIGHT= 600;
	private HallOfFameController hfC;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label bounces;
    private String path;
    public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	private GameZone zone;
    private Game game;
    private PacmanThread[] pts;
    private Refresh refresh;
    @FXML
    private ImageView inicio;

    @FXML
    void initialize() {
		
    	try {
    		hfC= new HallOfFameController();
			game = new Game(1, WIDTH, HEIGHT);
		} catch (IllegalArgumentException e) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("File not found");
			alert.show();
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("File not found");
			alert.show();
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("There was an error trying to load the file which contains all the scores in the hall of fame");
			alert.show();
			e.printStackTrace();
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("File not found");
			alert.show();
			e.printStackTrace();
		}
    	zone = new GameZone(game.getPacmans());
    	borderPane.setCenter(zone);
    	refresh = new Refresh(zone);
    	refresh.setDaemon(true);
    	refresh.start();
    	pts = new PacmanThread[game.getPacmans().length];
    	
    	
    	
    	for (int i = 0; i < game.getPacmans().length; i++) {
    		PacmanThread pt = new PacmanThread(game.getPacmans()[i]);
        	pt.setDaemon(true);
        	pt.start();
        	pts[i] = pt;
    	}
		
    }
    public void loadGame(String path) {
    	try {
			game.load(path);
		}catch (FileNotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("The file with the information about the last game was not found");
			alert.show();
    	}catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("It hasn't been possible to load the game, please try again");
			alert.show();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
    	clearThreads();
    	zone = new GameZone(game.getPacmans());
    	borderPane.setCenter(zone);
    	refresh = new Refresh(zone);
    	refresh.setDaemon(true);
    	refresh.start();
    	pts = new PacmanThread[game.getPacmans().length];
    	for (int i = 0; i < game.getPacmans().length; i++) {
    		PacmanThread pt = new PacmanThread(game.getPacmans()[i]);
        	pt.setDaemon(true);
        	pt.start();
        	pts[i] = pt;
		}
    }
    @FXML
    void level0(ActionEvent event) {
    	setPath(Game.FILE_PATH);
    	loadGame(path);
    }
    @FXML
    void level1(ActionEvent event) {
    	setPath(Game.FILE_PATH_1);
    	loadGame(path);
    }

    @FXML
    void level2(ActionEvent event) {
    	setPath(Game.FILE_PATH_2);
    	loadGame(path);
    }
    
    public void loadHall(Stage s) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("hallOfFame.fxml"));
    	Scene scene = new Scene(root);
        s.setScene(scene);
        s.setResizable(false);
		s.resizableProperty().setValue(Boolean.FALSE);
        s.show();
		
	}
    
	private Stage s;
    
	@FXML
    void stop(ActionEvent event) throws Exception {
    	clearThreads();
    	game.updateTotalBounces();
    	bounces.setText("Bounces: "+game.getNumberOfBounces());
    	
    	if(game.getNumberOfBounces()<300) {
    	s= new Stage();
    	loadHall(s);
    	String name="";
    	if(hfC.getName()!= " ") {
    		name= hfC.getName();
    		game.addScore(name, game.getNumberOfBounces());
        	game.loadHallOfFame();
    	}
    	}
    	
    }
    
    @FXML
    void exit(ActionEvent event) {
    	clearThreads();
    	((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    	
    }
    

    @FXML
    void save(ActionEvent event) {
    	clearThreads();
    	
    	try {
			game.save(getPath());
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setContentText("The game was saved successfully");
			alert.show();
		} catch (FileNotFoundException e) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("File not found");
			alert.show();
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    }
    
    @FXML
    void viewBestScores(ActionEvent event) throws Exception {
    	Alert info = new Alert(AlertType.INFORMATION);
    	info.setTitle("PacMan");
    	info.setHeaderText(null);
    	info.initStyle(StageStyle.UTILITY);
    	String msg= "The hall of fame is:"+"\n";
    	for (int i = 0; i < game.getHallOfFame().length; i++) {
			if(game.getHallOfFame()[i] != null) {
				msg+=game.getHallOfFame()[i].getScore()+"\n";
			}
		}
    	info.setContentText(msg+"");
    	info.show();
    	
    	
    }

    @FXML
    void viewGameInfo(ActionEvent event) {
    	Alert info = new Alert(AlertType.INFORMATION);
    	info.setTitle("PacMan");
    	info.setHeaderText(null);
    	info.initStyle(StageStyle.UTILITY);
    	info.setContentText(" INSTRUCTIONS \n\n"+
    	" Choose a level of dificulty in the ''load Game'' option\n"+
    	" Click on the Pac-Mans before they bounce on the walls "+
    	" Every time they bounce you get a point get as less point you can"+
    	" so you can be in our hall of fame"+
    	" \n\nGood luck ");
    	info.show();
    }
    
    public void clearThreads() {
    	if(refresh != null) {
    		refresh.setStop(true);
    	}
    	for (int i = 0; i < pts.length; i++) {
			if(pts[i] != null) {
				pts[i].setStop(true);
			}
		}
    }
    public Stage getS() {
		return s;
	}
	public void setS(Stage s) {
		this.s = s;
	}
    
	public GameZone getZone() {
		return zone;
	}

	public void setZone(GameZone zone) {
		this.zone = zone;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
}
