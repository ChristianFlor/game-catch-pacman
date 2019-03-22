package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HallOfFameController {
	
    @FXML
    private Label congra2;

    @FXML
    private TextField tFName;
    @FXML
    private GridPane table;

	private String name=" ";
    @FXML
    private Button closeButton;

	@FXML
    private Label congra;

    public void init() {
    	table= new GridPane();
    	tFName = new TextField();
    	closeButton= new Button();
    }
	@FXML
    void finish(ActionEvent event) {
    	setName(tFName.getText());
    	
    	((Stage)(((Button)event.getSource()).getScene().getWindow())).close(); 
    }
	
    public GridPane getTable() {
		return table;
	}

	public void setTable(GridPane table) {
		this.table = table;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Label getCongra2() {
		return congra2;
	}
	public TextField gettFName() {
		return tFName;
	}
	public Button getCloseButton() {
		return closeButton;
	}
	public Label getCongra() {
		return congra;
	}
    
}
