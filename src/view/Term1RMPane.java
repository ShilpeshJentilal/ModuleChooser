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



public class Term1RMPane extends GridPane{
	
	private Button btnAdd,btnRemove,btnConfirm;
	private Label lblUT1M,lblRT1M,lblRM;
	private ListView<Module> lstUT1M,lstRT1M;
	private int reserveCredit;
	
	public Term1RMPane() {
		//styling
		this.setStyle("-fx-background-color: BEIGE;");

		this.setPadding(new Insets(25,25,25,25));
		this.setAlignment(Pos.CENTER);
		
		ColumnConstraints column0 = new ColumnConstraints();
		column0.setHalignment(HPos.LEFT);
		this.getColumnConstraints().addAll(column0);
		

		lblRM = new Label("Reserve 30 credits worth of term 1 modules ");
		lblUT1M = new Label("Unselected Term 1 modules ");
		lblRT1M = new Label("Reserved Term 1 modules ");
		
		lstUT1M = new ListView<>();
		lstUT1M.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		lstRT1M = new ListView<>();
		lstRT1M.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		btnAdd = new Button("Add");
		btnRemove = new Button("Remove");
		btnConfirm = new Button("Confirm");
		
		btnAdd.setPrefSize(80, 20);
		btnRemove.setPrefSize(80, 20);
		btnConfirm.setPrefSize(80, 20);
		

		VBox left  = new VBox(0);
		left.getChildren().addAll(lblUT1M,lstUT1M);
		this.add(left, 0, 0);
		
		HBox label = new HBox();
		label.getChildren().addAll(lblRM);
		label.setPadding(new Insets(15,0,0,0));
		label.setAlignment(Pos.BOTTOM_RIGHT);
		this.add(label, 0, 1);

		
		VBox vbox1  = new VBox(0);
		vbox1.getChildren().addAll(lblRT1M,lstRT1M);
		vbox1.setPadding(new Insets(0,0,0,25));
		this.add(vbox1, 1, 0);
		
		HBox hbox = new HBox(15);
		hbox.getChildren().addAll(btnAdd,btnRemove,btnConfirm);
		hbox.setPadding(new Insets(15,0,0,25));
		this.add(hbox, 1, 1);

		

		
		lstUT1M.prefHeightProperty().bind(this.heightProperty());
		lstRT1M.prefHeightProperty().bind(this.heightProperty());
		lstUT1M.prefWidthProperty().bind(this.widthProperty());
		lstRT1M.prefWidthProperty().bind(this.widthProperty());

}

	public void clearModules() {
		lstUT1M.getItems().clear();
		lstRT1M.getItems().clear();
		btnAdd.setDisable(false);
	}
	
	//transfers modules form SelectModulePane to this viewlist.
	public void addModulesToviewList(Collection<Module> collection) {
		lstUT1M.getItems().clear();
		lstUT1M.getItems().addAll(collection);
	}
	
	//Event handlings for buttons
	public void addTerm1ReserveModulesHandler(EventHandler<ActionEvent> handler) {
		btnAdd.setOnAction(handler);
	}
	
	public void RemoveTerm1ReserveModulesHandler(EventHandler<ActionEvent> handler) {
		btnRemove.setOnAction(handler);
	}
	
	public void ConfirmReserveModules1Handler(EventHandler<ActionEvent> handler) {
		btnConfirm.setOnAction(handler);
	}
	
	//get methods for selected modudles in the viewlist
	public model.Module getTerm1UnselectedModule() {
		return lstUT1M.getSelectionModel().getSelectedItem();
	}
	
	public model.Module getTerm1ReservedModule() {
		return lstRT1M.getSelectionModel().getSelectedItem();
	}
	
	//observable list
	public ObservableList<model.Module> getTerm1UnselectedModules() {
		return lstUT1M.getSelectionModel().getSelectedItems();
	}
	
	public ObservableList<model.Module> getTerm1ReservedModules() {
		return lstRT1M.getSelectionModel().getSelectedItems();
	}

	//adding modules to the reserved list
	public void addToReservedModule(Module module) {
		lstRT1M.getItems().add(module);
		lstUT1M.getItems().remove(module);
		reserveCredit = reserveCredit + module.getModuleCredits();
		lstUT1M.getSelectionModel().clearSelection();
		if(reserveCredit>=30) {
			btnAdd.setDisable(true);
		}else {
			btnAdd.setDisable(false);
		}
	}
	
	public void removeFromReservedModule(Module module) {
		lstUT1M.getItems().add(module);
		lstRT1M.getItems().remove(module);
		reserveCredit = reserveCredit - module.getModuleCredits();
		lstUT1M.getSelectionModel().clearSelection();
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
	public ObservableList<Module> getResrvedterm1modules() {
		return lstRT1M.getItems();
	}
	
	//get button
	
	
}
