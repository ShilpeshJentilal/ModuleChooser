package view;

import java.util.Collection;

import java.util.stream.Collectors;


import javafx.collections.FXCollections;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Course;
import model.Module;
import model.Schedule;

public class SelectModulesPane extends GridPane{
	private ObservableList<Module> term1Modules,term2Modules,sylModules;
	private Label lblUT1M, lblUT2M , lblT1,lblT2, lblSYLM, lblST1M, lblST2M,lblCT1C,lblCT2C;
	private ListView<Module> lstUT1M,lstUT2M,lstSYLM, lstST1M, lstST2M;
	private Button btnAdd1, btnRemove1,btnAdd2, btnRemove2, btnReset,btnSubmit;
	private TextField txtCredit1,txtCredit2;
	private int term1credit, term2credit;


	public SelectModulesPane() {
		
		this.setStyle("-fx-background-color: BEIGE;");

		this.setPadding(new Insets(25,25,25,25));
		this.setAlignment(Pos.CENTER);
		
		ColumnConstraints column0 = new ColumnConstraints();
		column0.setHalignment(HPos.LEFT);
		this.getColumnConstraints().addAll(column0);
		
		//creating observable
		term1Modules = FXCollections.observableArrayList();
		term2Modules = FXCollections.observableArrayList();
		sylModules = FXCollections.observableArrayList();
		
		//create labels
		lblUT1M = new Label("Uselected Term 1 modules "); 
		lblUT2M = new Label("Uselected Term 2 modules ");
		lblT1 = new Label("Term 1 ");
		lblT2 = new Label("Term 2 ");
		lblSYLM = new Label("Selected Year Long modules ");
		lblST1M = new Label("Selected Term 1 modules ");
		lblST2M = new Label("Selected Term 2 modules ");
		lblCT1C = new Label("Current term 1 credits: ");
		lblCT2C = new Label("Current term 2 credits: ");

		
		//create list
		lstUT1M = new ListView<>(term1Modules);
		lstUT1M.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		lstUT2M = new ListView<>(term2Modules);
		lstUT2M.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		lstSYLM = new ListView<>(sylModules);
		lstSYLM.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		lstST1M = new ListView<>();
		lstST1M.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		lstST2M = new ListView<>();
		lstST2M.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		

		//create TextField
		txtCredit1 = new TextField();
		txtCredit1.setText("0");
		txtCredit1.setEditable(false);
		txtCredit1.setPrefSize(50, 20);
		txtCredit2 = new TextField();
		txtCredit2.setText("0");
		txtCredit2.setEditable(false);
		txtCredit2.setPrefSize(50,20);
		
		//Create Buttons
		btnAdd1 = new Button("Add");
		btnRemove1 = new Button("Remove");
		btnAdd2 = new Button("Add");
		btnRemove2 = new Button("Remove");
		btnReset = new Button("Reset");
		btnSubmit = new Button("Submit");
		
		//button properties
		btnAdd1.setPrefSize(80, 20);
		btnAdd2.setPrefSize(80, 20);
		btnRemove1.setPrefSize(80, 20);
		btnRemove2.setPrefSize(80, 20);
		btnReset.setPrefSize(80, 20);
		btnSubmit.setPrefSize(80, 20);
	
		//Left side term1
		VBox UT1M = new VBox();
		UT1M.getChildren().addAll(lblUT1M,lstUT1M);
		
		
		//buttons for left side term 1
		HBox btnT1 = new HBox(15);
		btnT1.getChildren().addAll(lblT1,btnAdd1,btnRemove1);
		btnT1.setPadding(new Insets(15,0,0,0));
		btnT1.setAlignment(Pos.CENTER);
		
		//Left side term2
		VBox UT2M = new VBox(0);
		UT2M.getChildren().addAll(lblUT2M,lstUT2M);
		lblUT2M.setPadding(new Insets(10,0,0,0));

		//buttons for left side term 2
		HBox btnT2 = new HBox(15);
		btnT2.getChildren().addAll(lblT2,btnAdd2,btnRemove2);
		btnT2.setPadding(new Insets(15,0,0,0));
		btnT2.setAlignment(Pos.CENTER);
		btnT2.setPadding(new Insets(20,10,10,60));

	
		//left side credit score
		HBox CS = new HBox(15);
		CS.getChildren().addAll(lblCT1C,txtCredit1);
		CS.setPadding(new Insets(25,50,0,0));
		CS.setAlignment(Pos.BASELINE_RIGHT);
		this.add(CS, 0, 1);
		//left button reset
		HBox btnResets = new HBox(0);
		btnResets.getChildren().addAll(btnReset);
		btnResets.setPadding(new Insets(25,0,0,320));

		//left side
		VBox left = new VBox();
		left.getChildren().addAll(UT1M,btnT1,UT2M,btnT2);
		btnResets.setPadding(new Insets(15,0,0,0));
		btnResets.setAlignment(Pos.BASELINE_RIGHT);
		this.add(left, 0, 0);

		
		
		//right side
		VBox vbox1 = new VBox(0);
		vbox1.getChildren().addAll(lblSYLM,lstSYLM,lblST1M,lstST1M,lblST2M,lstST2M);
		vbox1.setPadding(new Insets(0,0,0,30));
		lblST1M.setPadding(new Insets(20,0,0,0));
		lblST2M.setPadding(new Insets(20,0,0,0));

		
		HBox hbox3 = new HBox(15);
		hbox3.getChildren().addAll(lblCT2C,txtCredit2);
		hbox3.setPadding(new Insets(25,0,0,80));
		hbox3.setAlignment(Pos.BASELINE_LEFT);
		this.add(hbox3, 1, 1);
		
		HBox hbox5 = new HBox(0);
		hbox5.getChildren().addAll(btnSubmit);
		hbox5.setPadding(new Insets(15,0,0,25));
		hbox5.setAlignment(Pos.BASELINE_LEFT);
		this.add(btnResets, 0, 2);

		this.add(hbox5, 1, 2);
		
		
		//Right Side
		VBox right = new VBox();
		right.getChildren().addAll(vbox1);
		this.add(right, 1, 0);
	
		right.prefWidthProperty().bind(this.widthProperty());
		left.prefWidthProperty().bind(this.widthProperty());


		
	}
	

//generates the viewlist with modules
	public void addModulesToviewList(Collection<model.Module> collection, Course course) {
		
		lstUT1M.getItems().clear();
		lstUT2M.getItems().clear();
		lstSYLM.getItems().clear();
		lstST1M.getItems().clear();	
		lstST2M.getItems().clear();
		term1credit = 0;
		term2credit = 0;
		txtCredit1.setText(String.valueOf(term1credit));
		txtCredit2.setText(String.valueOf(term2credit));
		btnAdd1.setDisable(false);
		btnAdd2.setDisable(false);
		
		lstUT1M.getItems().addAll(collection.stream().filter(s ->s.getDelivery() == Schedule.TERM_1).collect(Collectors.toList()));
		lstSYLM.getItems().addAll(collection.stream().filter(s -> s.getDelivery()==Schedule.YEAR_LONG).collect(Collectors.toList()));
		lstUT2M.getItems().addAll(collection.stream().filter(s ->s.getDelivery() == Schedule.TERM_2).collect(Collectors.toList()));
		if (course.toString() == "Computer Science") {
		
			lstST1M.getItems().addAll(collection.stream().filter(s -> s.isMandatory() == true && s.getDelivery() == Schedule.TERM_1).collect(Collectors.toList()));
			lstUT1M.getItems().removeAll(collection.stream().filter(s -> s.isMandatory() == true && s.getDelivery() == Schedule.TERM_1).collect(Collectors.toList()));
			lstST2M.getItems().addAll(collection.stream().filter(s -> s.isMandatory() == true && s.getDelivery() == Schedule.TERM_2).collect(Collectors.toList()));
			lstUT2M.getItems().removeAll(collection.stream().filter(s -> s.isMandatory() == true && s.getDelivery() == Schedule.TERM_2).collect(Collectors.toList()));

			for (Module module : lstST1M.getItems()) {
				term1credit = module.getModuleCredits();
			}
			for (Module module : lstST2M.getItems()) {
				term2credit = module.getModuleCredits();
			}
			txtCredit1.setText(String.valueOf(term1credit));
			txtCredit2.setText(String.valueOf(term2credit));
			
		}else {
			lstST1M.getItems().addAll(collection.stream().filter(s -> s.isMandatory() == true && s.getDelivery() == Schedule.TERM_1).collect(Collectors.toList()));
			lstUT1M.getItems().removeAll(collection.stream().filter(s -> s.isMandatory() == true && s.getDelivery() == Schedule.TERM_1).collect(Collectors.toList()));
			lstST2M.getItems().addAll(collection.stream().filter(s -> s.isMandatory() == true && s.getDelivery() == Schedule.TERM_2).collect(Collectors.toList()));
			lstUT2M.getItems().removeAll(collection.stream().filter(s -> s.isMandatory() == true && s.getDelivery() == Schedule.TERM_2).collect(Collectors.toList()));	
			for (Module module : lstST1M.getItems()) {
				term1credit = module.getModuleCredits();
			}

			for (Module module : lstST2M.getItems()) {
				term2credit = module.getModuleCredits();
			}
			txtCredit1.setText(String.valueOf(term1credit));
			txtCredit2.setText(String.valueOf(term2credit));

		}
		
	}
	
	//Event handlings for buttons
	public void addTerm1ModulesHandler(EventHandler<ActionEvent> handler) {
		btnAdd1.setOnAction(handler);
	}
	
	public void removeTerm1ModulesHandler(EventHandler<ActionEvent> handler) {
		btnRemove1.setOnAction(handler);
	}

	public void addTerm2ModulesHandler(EventHandler<ActionEvent> handler) {
		btnAdd2.setOnAction(handler);
	}
	
	public void removeTerm2ModulesHandler(EventHandler<ActionEvent> handler) {
		btnRemove2.setOnAction(handler);
	}
	
	public void resetHandler(EventHandler<ActionEvent> handler) {
		btnReset.setOnAction(handler);
	}
	
	public void submitHandler(EventHandler<ActionEvent> handler) {
		btnSubmit.setOnAction(handler);
	}
	
	
	

	//get methods for selected modudles in the viewlist
	public model.Module getTerm1UnselectedModule() {
		return lstUT1M.getSelectionModel().getSelectedItem();
	}
	
	public model.Module getTerm2UnselectedModule() {
		return lstUT2M.getSelectionModel().getSelectedItem();
	}
	
	public model.Module getTerm1SelectedModule() {
		return lstST1M.getSelectionModel().getSelectedItem();
	}
	
	public model.Module getTerm2SelectedModule() {
		return lstST2M.getSelectionModel().getSelectedItem();
	}
	

	//observable list
	public ObservableList<model.Module> getSelected1Modules() {
		return lstST1M.getSelectionModel().getSelectedItems();
	}
	
	public ObservableList<model.Module> getSelected2Modules() {
		return lstST2M.getSelectionModel().getSelectedItems();
	}
	
	public ObservableList<model.Module> getUnSelected1Modules() {
		return lstUT1M.getSelectionModel().getSelectedItems();
	}
	
	public ObservableList<model.Module> getUnSelected2Modules() {
		return lstUT2M.getSelectionModel().getSelectedItems();
	}	
	
	
	
	//method for adding and removing from view list & updating the credit score
	public void removeTerm1SelectedModule(Module module) {
		lstUT1M.getItems().add(module);
		lstST1M.getItems().remove(module);
		term1credit = term1credit- module.getModuleCredits();
		txtCredit1.setText(String.valueOf(term1credit));
		lstST1M.getSelectionModel().clearSelection();
		if(term1credit<60) {
			btnAdd1.setDisable(false);
		}else {
			btnAdd1.setDisable(true);
		}
	}

	public void addTerm1SelectedModule(Module module) {
		lstST1M.getItems().add(module);
		lstUT1M.getItems().remove(module);
		term1credit = term1credit+ module.getModuleCredits();
		txtCredit1.setText(String.valueOf(term1credit));
		lstUT1M.getSelectionModel().clearSelection();
		if(term1credit >=60) {
			btnAdd1.setDisable(true);
		}else {
			btnAdd1.setDisable(false);
		}
		
	}
	
	public void removeTerm2SelectedModule(Module module) {
		lstUT2M.getItems().add(module);
		lstST2M.getItems().remove(module);
		term2credit = term2credit- module.getModuleCredits();
		txtCredit2.setText(String.valueOf(term2credit));
		lstST2M.getSelectionModel().clearSelection();
		if(term2credit<60) {
			btnAdd2.setDisable(false);
		}else {
			btnAdd2.setDisable(true);
		}
	}

	public void addTerm2SelectedModule(Module module) {
		lstST2M.getItems().add(module);
		lstUT2M.getItems().remove(module);
		term2credit = term2credit+ module.getModuleCredits();
		txtCredit2.setText(String.valueOf(term2credit));
		lstUT2M.getSelectionModel().clearSelection();
		if(term2credit >=60) {
			btnAdd2.setDisable(true);
		}else {
			btnAdd2.setDisable(false);
		}
		
	}

	
	//get credit score
	public int getTerm1Credit() {
		return term1credit;
	}
	
	public int getTerm2Credit() {
		return term2credit;
	}


	
	//methods used to tranfers unlsected term1 and term2 modules to reserve modules tab.
	public ObservableList<Module> getUSelectedterm1modules() {
		return lstUT1M.getItems();
	}

	public ObservableList<Module> getUSelectedterm2modules() {
		return lstUT2M.getItems();
	}

	public ObservableList<Module> getSelectedterm1modules() {
		return lstST1M.getItems();
	}

	public ObservableList<Module> getSelectedterm2modules() {
		return lstST2M.getItems();
	}
	
	
	public ObservableList<Module> getYearLongModules() {
		return lstSYLM.getItems();
	}

	public Button getTerm1AddButton() {
		return btnAdd1;
	}


	public Button getSubmitButton() {
		return btnSubmit;
	}
	
}




