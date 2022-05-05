package view;

import java.util.Collection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Module;
import model.Name;

public class OverviewSelectionPane extends GridPane{
	
	private ListView<String> lstpr,lstSM,lstRM;
	private Button btnSO;
	
	public OverviewSelectionPane() {
		this.setStyle("-fx-background-color: BEIGE;");

		this.setPadding(new Insets(25,25,25,25));
		this.setAlignment(Pos.CENTER);
		
		ColumnConstraints column0 = new ColumnConstraints();
		column0.setHalignment(HPos.LEFT);
		this.getColumnConstraints().addAll(column0);
		
		lstpr = new ListView<>();
		lstpr.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		lstSM = new ListView<>();
		lstSM.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		lstRM = new ListView<>();
		lstRM.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		btnSO = new Button("Save Overview");
		btnSO.setPrefSize(100, 20);
		
		VBox vbox = new VBox(0);
		vbox.getChildren().addAll(lstpr);
		vbox.setPadding(new Insets(0,0,25,0));
		vbox.setPrefSize(0, 500);
		this.add(vbox, 0, 0);

		HBox hbox = new HBox(25);
		hbox.getChildren().addAll(lstSM,lstRM);
		hbox.setPadding(new Insets(10,0,25,0));
		this.add(hbox, 0, 1);
		
		HBox hbox1 = new HBox();
		hbox1.getChildren().addAll(btnSO);
		//hbox1.setPadding(new Insets(8,0,25,300));
		hbox1.setAlignment(Pos.CENTER);
		this.add(hbox1, 0, 2);
		
	
		lstpr.prefWidthProperty().bind(this.widthProperty());
		lstSM.prefWidthProperty().bind(this.widthProperty());
		lstRM.prefWidthProperty().bind(this.widthProperty());
		lstpr.prefHeightProperty().bind(this.heightProperty());
		lstSM.prefHeightProperty().bind(this.heightProperty());;
		lstRM.prefHeightProperty().bind(this.heightProperty());
		
	}
	//method to attach the create student profile button event handler
	public void SaveOverviewHandler(EventHandler<ActionEvent> handler) {
		btnSO.setOnAction(handler);
	}
	
	//methods to get items from view lists
	public ObservableList<String> getProfileView(){
		return lstpr.getItems();
	}
	
	public String getPrString() {
		String c = "";
		for (String list : lstpr.getItems()) {
			c = c+ list+"\n";
		}
		return c;
	}
	
	
	public String getSMString() {
		String c ="";
		for (String list : lstSM.getItems()) {
			c = c+ list+"\n";
		}
		return c;
	}
	
	public String getRMString() {
		String c ="";
		for (String list : lstRM.getItems()) {
			c = c+ list+"\n";
		}
		return c;
	}
	
	
	
	public ObservableList<String> getSelectedModuleView(){
		return lstSM.getItems();
	}
	
	public ObservableList<String> getReservedModuleView(){
		return lstRM.getItems();
	}
	
	//methods to add items to the view lists
	public void AddLoadProfile(String c) {
		lstpr.getItems().add(c);
	}
	
	public void AddLoadSelectedModules(String c) {
		lstSM.getItems().add(c);
	}
	
	public void AddLoadReservedModules(String c) {
		lstRM.getItems().add(c);
	}
	
	public void AddProfiletoViewList(Name name,String pno,String email, String date, String course) {
		lstpr.getItems().clear();
		lstpr.getItems().addAll("Name: "+name.getFullName().toString(), "PNo: "+pno, "Email: "+email,"Date: "+date,"Course: "+course);
	}
	
	//transfers modules form SelectModulePane to this viewlist.
	public void addSelectedModulesToviewList(Collection<Module> t1collection,Collection<Module> t2Collection, Collection<Module> YearLongModules) {
		lstSM.getItems().clear();
		
		
		
		lstSM.getItems().add("Selected modules: \n ===========");
		
		for (Module module : YearLongModules) {
			lstSM.getItems().add("Module code: "+module.getModuleCode()+", Module name: "+module.getModuleName()
			+",\n Credits: "+module.getModuleCredits()+", Mandatory on your course? "+module.isMandatory()
			+", Delivery: "+module.getDelivery()+"\n");
			lstSM.getItems().add("");
		}
		
		for (Module module : t1collection) {
			lstSM.getItems().add("Module code: "+module.getModuleCode()+", Module name: "+module.getModuleName()
			+",\n Credits: "+module.getModuleCredits()+", Mandatory on your course? "+module.isMandatory()
			+", Delivery: "+module.getDelivery()+"\n");
			lstSM.getItems().add("");
		}

		for (Module module : t2Collection) {
			lstSM.getItems().add("Module code: "+module.getModuleCode()+", Module name: "+module.getModuleName()
			+",\n Credits: "+module.getModuleCredits()+", Mandatory on your course? "+module.isMandatory()
			+", Delivery: "+module.getDelivery()+"\n");
			lstSM.getItems().add("");
		}
	}
	
	public void addReservedModulestoViewList(Collection<Module> r1Collection,Collection<Module> r2Collection ) {
		lstRM.getItems().clear();
		lstRM.getItems().add("Reserved modules: \n ===========");
		
		for (Module module : r1Collection) {
		lstRM.getItems().add("Module code: "+module.getModuleCode()+", Module name: "+module.getModuleName()
		+",\n Credits: "+module.getModuleCredits()+", Mandatory on your course? "+module.isMandatory()
		+", Delivery: "+module.getDelivery()+"\n");
		lstRM.getItems().add("");
		}
		
		for (Module module : r2Collection) {
			lstRM.getItems().add("Module code: "+module.getModuleCode()+", Module name: "+module.getModuleName()
			+",\n Credits: "+module.getModuleCredits()+", Mandatory on your course? "+module.isMandatory()
			+", Delivery: "+module.getDelivery()+"\n");
			lstRM.getItems().add("");	
		}
	}
	
	public void clearViewList() {
		lstpr.getItems().clear();
		lstSM.getItems().clear();
		lstRM.getItems().clear();
	}
	
	
}
