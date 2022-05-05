package view;


import java.util.Collection;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Module;


public class Term2RMPane extends GridPane{

	private Button btnAdd,btnRemove,btnConfirm;
	private Label lblUT2M,lblRT2M,lblRM;
	private ListView<model.Module> lstUT2M,lstRT2M;
	private int reserveCredit;
	
	public Term2RMPane(){
		this.setStyle("-fx-background-color: BEIGE;");

		this.setPadding(new Insets(25,25,25,25));
		this.setAlignment(Pos.CENTER);
		
		ColumnConstraints column0 = new ColumnConstraints();
		column0.setHalignment(HPos.LEFT);
		this.getColumnConstraints().addAll(column0);
		
		btnAdd = new Button("Add");
		btnRemove = new Button("Remove");
		btnConfirm = new Button("Confirm");
		
		btnAdd.setPrefSize(80, 20);
		btnRemove.setPrefSize(80, 20);
		btnConfirm.setPrefSize(80, 20);
		
		lblRM = new Label("Reserve 30 credits worth of term 2 modules ");
		
		lblUT2M = new Label("Unselected Term 2 modules ");
		lblRT2M = new Label("Reserved Term 2 modules ");
		
		lstUT2M = new ListView<>();
		lstUT2M.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		lstRT2M = new ListView<>();
		lstRT2M.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lstRT2M.setPadding(new Insets(0,0,0,0));
		
	
		
		VBox vbox  = new VBox();
		
		vbox.getChildren().addAll(lblUT2M,lstUT2M);
		this.add(vbox, 0, 0);
		
		VBox vbox1  = new VBox();
		
		vbox1.getChildren().addAll(lblRT2M,lstRT2M);
		vbox1.setPadding(new Insets(0,0,0,25));
		this.add(vbox1, 1, 0);
		
		
		HBox label = new HBox();
		label.getChildren().addAll(lblRM);
		label.setPadding(new Insets(15,0,0,0));
		label.setAlignment(Pos.BOTTOM_RIGHT);
		this.add(label, 0, 1);
		
		HBox hbox = new HBox(15);
		hbox.getChildren().addAll(btnAdd,btnRemove,btnConfirm);
		hbox.setPadding(new Insets(15,0,0,25));
		this.add(hbox, 1, 1);
		
		
		lstUT2M.prefHeightProperty().bind(this.heightProperty());
		lstRT2M.prefHeightProperty().bind(this.heightProperty());
		lstUT2M.prefWidthProperty().bind(this.widthProperty());
		lstRT2M.prefWidthProperty().bind(this.widthProperty());

		
		
	}
	
	public void clearModules() {
		lstUT2M.getItems().clear();
		lstRT2M.getItems().clear();
		btnAdd.setDisable(false);

	}
	
	//transfers modules form SelectModulePane to this viewlist.
	public void addModulesToviewList(Collection<Module> collection) {
		lstUT2M.getItems().clear();
		lstUT2M.getItems().addAll(collection);
	}
	
	//Event handlings for buttons
	public void addTerm2ReserveModulesHandler(EventHandler<ActionEvent> handler) {
		btnAdd.setOnAction(handler);
	}
	
	public void RemoveTerm2ReserveModulesHandler(EventHandler<ActionEvent> handler) {
		btnRemove.setOnAction(handler);
	}
	
	public void ConfirmReserveModules2Handler(EventHandler<ActionEvent> handler) {
		btnConfirm.setOnAction(handler);
	}
	
	//get methods for selected modudles in the viewlist
	public model.Module getTerm2UnselectedModule() {
		return lstUT2M.getSelectionModel().getSelectedItem();
	}
	
	public model.Module getTerm2ReservedModule() {
		return lstRT2M.getSelectionModel().getSelectedItem();
	}
	
	//observable list
	public ObservableList<model.Module> getTerm2UnselectedModules() {
		return lstUT2M.getSelectionModel().getSelectedItems();
	}
	
	public ObservableList<model.Module> getTerm2ReservedModules() {
		return lstRT2M.getSelectionModel().getSelectedItems();
	}
	
	//adding modules to the reserved list
	public void addToReservedModule(Module module) {
		lstRT2M.getItems().add(module);
		lstUT2M.getItems().remove(module);
		reserveCredit = reserveCredit + module.getModuleCredits();
		lstUT2M.getSelectionModel().clearSelection();
		if(reserveCredit>=30) {
			btnAdd.setDisable(true);
		}else {
			btnAdd.setDisable(false);
		}
		
	}
	
	//adding modules from reserved list
	public void removeFromReservedModule(Module module) {
		lstUT2M.getItems().add(module);
		lstRT2M.getItems().remove(module);
		reserveCredit = reserveCredit - module.getModuleCredits();
		lstUT2M.getSelectionModel().clearSelection();
		if(reserveCredit<30) {
			btnAdd.setDisable(false);
		}else {
			btnAdd.setDisable(true);
		}
	}
	
	//get credit score
	public int getReserveCredit() {
		return reserveCredit;
	}	
	
	//get method to get all the items from viewlist from this pane
	public ObservableList<Module> getResrvedterm2modules() {
		return lstRT2M.getItems();
	}
	
}
