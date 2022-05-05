package controller;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Course;
import model.Schedule;
import model.Module;
import model.Name;
import model.StudentProfile;
import view.ModuleChooserRootPane;
import view.OverviewSelectionPane;
import view.ReserveModulesPane;
import view.SelectModulesPane;
import view.Term1RMPane;
import view.Term2RMPane;
import view.CreateStudentProfilePane;
import view.ModuleChooserMenuBar;

public class ModuleChooserController {

	//fields to be used throughout class
	private ModuleChooserRootPane view;
	private StudentProfile model;
	
	private OverviewSelectionPane osp;
	private CreateStudentProfilePane cspp;
	private ModuleChooserMenuBar mstmb;
	private SelectModulesPane smp;
	private ReserveModulesPane rmp;
	private Term1RMPane t1rmp;
	private Term2RMPane t2rmp;

	
	
	public ModuleChooserController(ModuleChooserRootPane view, StudentProfile model) {
		//initialise view and model fields
		this.view = view;
		this.model = model;
		
		//initialise view subcontainer fields
		cspp = view.getCreateStudentProfilePane();
		mstmb = view.getModuleSelectionToolMenuBar();
		smp = view.getSelectModulesPane();
		rmp = view.getReserveModulesPane();
		osp = view.getOverviewSelectionPane();
		t1rmp = view.getTerm1ModulePane();
		t2rmp = view.getTerm2ModulePane();
		
		//add courses to combobox in create student profile pane using the generateAndGetCourses helper method below
		cspp.addCoursesToComboBox(generateAndGetCourses());
				//attach event handlers to view using private helper method
		this.attachEventHandlers();	
	}

	
	//helper method - used to attach event handlers
	private void attachEventHandlers() {
		//attach an event handler to the create student profile pane
		cspp.addCreateStudentProfileHandler(new CreateStudentProfileHandler());
		smp.addTerm1ModulesHandler(new addTerm1ModulesHandler());
		smp.removeTerm1ModulesHandler(new removeTerm1ModulesHandler());
		smp.addTerm2ModulesHandler(new addTerm2ModulesHandler());
		smp.removeTerm2ModulesHandler(new removeTerm2ModulesHandler());
		smp.resetHandler(new resetHandler());
		smp.submitHandler(new submitHandler());
		t1rmp.addTerm1ReserveModulesHandler(new addTerm1ReserveModulesHandler());
		t1rmp.RemoveTerm1ReserveModulesHandler(new RemoveTerm1ReserveModulesHandler());
		t1rmp.ConfirmReserveModules1Handler(new ConfirmReserveModules1Handler());
		t2rmp.addTerm2ReserveModulesHandler(new addTerm2ReserveModulesHandler());
		t2rmp.RemoveTerm2ReserveModulesHandler(new RemoveTerm2ReserveModulesHandler());
		t2rmp.ConfirmReserveModules2Handler(new ConfirmReserveModules2Handler());
		osp.SaveOverviewHandler(new SaveHandler());

		//attach an event handler to the menu bar that closes the application
		mstmb.addExitHandler(e -> System.exit(0));
		mstmb.addSaveHandler(new SaveHandler());
		mstmb.addLoadHandler(new LoadHandler());
		mstmb.addAboutHandler(e -> alertDialogBuilder(AlertType.INFORMATION, "About", null, "This Final Year Module Selection Tool allows user to pick thier final year modules"));
	}
	
	//================event handling for Select Module Pane=============================
	private class addTerm1ModulesHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent event) {
			Module module = smp.getTerm1UnselectedModule();
	
			if(smp.getUnSelected1Modules().isEmpty()) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Please Select the Module to Add from Unselected Term 1 modules list!");
			}else {
				smp.addTerm1SelectedModule(module);
				}
			}
	}

	private class removeTerm1ModulesHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent event) {
			Module module = smp.getTerm1SelectedModule();
			if(smp.getSelected1Modules().isEmpty()) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Please Select the Module to Selected Term 1 modules list!");
			}else if(smp.getTerm1SelectedModule().isMandatory()== true){
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: This module is Mandatory and can't be removed!");
			}else {
				smp.removeTerm1SelectedModule(module);
				}
			}
		}
	
	private class addTerm2ModulesHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent event) {
			Module module = smp.getTerm2UnselectedModule();
			if(smp.getUnSelected2Modules().isEmpty()) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Please Select the Module to Add from Unselected Term 2 modules list!");
		}else {
			smp.addTerm2SelectedModule(module);
			}
			}
		}
	
	private class removeTerm2ModulesHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent event) {
			Module module = smp.getTerm2SelectedModule();
			if(smp.getSelected2Modules().isEmpty()) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Please Select the Module to Remove from Selected Term 2 modules list!");
			}else if(smp.getTerm2SelectedModule().isMandatory()== true){
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: This module is Mandatory and can't be removed!");
			}else {
				smp.removeTerm2SelectedModule(module);
				}
			}
		}
	
	private class resetHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent event) {
			t1rmp.clearModules();
			t2rmp.clearModules();
			osp.clearViewList();
			Course course = cspp.getSelectedCourse();
	
			if(smp.getUSelectedterm2modules().isEmpty()) {
				
			}else {
			if (course.toString() == "Computer Science") {
			smp.addModulesToviewList(generateAndGetCourses()[0].getAllModulesOnCourse(),course);
			}else {
				smp.addModulesToviewList(generateAndGetCourses()[1].getAllModulesOnCourse(),course);
	
			}
			}
		}
	}
	
	
	private class submitHandler implements EventHandler<ActionEvent>{
		public void handle(ActionEvent event) {
			int term1credit = smp.getTerm1Credit();
			int term2credit = smp.getTerm2Credit();
		
			if (term1credit != 60) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Credit for Term 1 must be 60!");
			
			}else if(term2credit != 60){
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Credit for Term 2 must be 60!");
			}else {
				t1rmp.clearModules();
				t2rmp.clearModules();
				t1rmp.addModulesToviewList(smp.getUSelectedterm1modules());
				t2rmp.addModulesToviewList(smp.getUSelectedterm2modules());
				view.changeTab(2);
			}
		}
	}
	
	
	//event handler (currently empty), which can be used for creating a profile
	private class CreateStudentProfileHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
			Course course = cspp.getSelectedCourse();
			String pnumber = cspp.getStudentPnumber();
			Name name = cspp.getStudentName();
			String email = cspp.getStudentEmail();
			LocalDate date = cspp.getStudentDate();
			Term1RMPane t = t1rmp;
			Term2RMPane t2 = t2rmp;
			
			if (course.toString() == "Computer Science")
			{
			//validating pnumber
				if(pnumber.isEmpty()) {
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: PNumber must not be empty!");
			//validating name
				}else if (name.getFirstName().isEmpty()) {
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: First name must not be empty!");
			//validating name
				}else if (name.getFamilyName().isEmpty()) {
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Surname must not be empty!");
				//validating email
				}else if(email.isEmpty()) {
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Email must not be empty!");
			//validating date
				}else if(date == null) {
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Date must not be empty!");
				}else {
					smp.addModulesToviewList(generateAndGetCourses()[0].getAllModulesOnCourse(),course);
					view.changeTab(1);
					}
			}else {
				if(pnumber.isEmpty()) {
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: PNumber must not be empty!");
			//validating name
				}else if (name.getFirstName().isEmpty()) {
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: First name must not be empty!");
			//validating name
				}else if (name.getFamilyName().isEmpty()) {
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Surname must not be empty!");
				//validating email
				}else if(email.isEmpty()) {
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Email must not be empty!");
			//validating date
				}else if(date == null) {
					alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Date must not be empty!");
				}else {
					smp.addModulesToviewList(generateAndGetCourses()[1].getAllModulesOnCourse(),course);
					t.clearModules();
					t2.clearModules();
					view.changeTab(1);
					}
				}
			}
		}
	
	//================event handling for Reserve Module Pane=============================
	private class addTerm1ReserveModulesHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
		
			Module module = t1rmp.getTerm1UnselectedModule();
			if(t1rmp.getTerm1UnselectedModules().isEmpty()) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Please Select the Module to Reserve!");
		}else {
			t1rmp.addToReservedModule(module);
			}
			}
		}
	
	private class addTerm2ReserveModulesHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Module module = t2rmp.getTerm2UnselectedModule();
			if(t2rmp.getTerm2UnselectedModules().isEmpty()) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Please Select the Module to Reserve!");
		}else {
			t2rmp.addToReservedModule(module);
			}
			}
		}
	
	private class RemoveTerm1ReserveModulesHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Module module = t1rmp.getTerm1ReservedModule();
			if(t1rmp.getTerm1ReservedModules().isEmpty()) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Please Select the Module to remove from Reserve!");
		}else {
			t1rmp.removeFromReservedModule(module);
			}
		}
		}
	

	
	private class RemoveTerm2ReserveModulesHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Module module = t2rmp.getTerm2ReservedModule();
			if(t2rmp.getTerm2ReservedModules().isEmpty()) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Please Select the Module to remove from Reserve!");
		}else {
			t2rmp.removeFromReservedModule(module);
			}
			
		}
		}
	
	private class ConfirmReserveModules1Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			Course course = cspp.getSelectedCourse();
			String pnumber = cspp.getStudentPnumber();
			Name name = cspp.getStudentName();
			String email = cspp.getStudentEmail();
			LocalDate date = cspp.getStudentDate();
			
			rmp.changetoTerm2Pane();
			if(t1rmp.getReserveCredit() != 30) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Please make sure that you reserve 30 worth of credit in term 1!");
				 rmp.changetoTerm1Pane();
			}else if(t2rmp.getReserveCredit() !=30) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Please make sure that you reserve 30 worth of credit in term 2!");
				rmp.changetoTerm2Pane();
			}else {
				osp.AddProfiletoViewList(name, pnumber.toString(), email.toString(), date.toString(), course.toString());
				osp.addSelectedModulesToviewList(smp.getSelectedterm1modules(),smp.getSelectedterm2modules(),smp.getYearLongModules());
				osp.addReservedModulestoViewList(t1rmp.getResrvedterm1modules(),t2rmp.getResrvedterm2modules());
				view.changeTab(3);
			}
		}
		}
	
	
	private class ConfirmReserveModules2Handler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			
			Course course = cspp.getSelectedCourse();
			String pnumber = cspp.getStudentPnumber();
			Name name = cspp.getStudentName();
			String email = cspp.getStudentEmail();
			LocalDate date = cspp.getStudentDate();
			
			
			if(t1rmp.getReserveCredit() != 30) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Please make sure that you reserve 30 worth of credit in term 1!");
				 rmp.changetoTerm1Pane();
			}else if(t2rmp.getReserveCredit() !=30) {
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "Error: Please make sure that you reserve 30 worth of credit in term 2!");
				rmp.changetoTerm2Pane();
			}else {
				osp.AddProfiletoViewList(name, pnumber.toString(), email.toString(), date.toString(), course.toString());
				osp.addSelectedModulesToviewList(smp.getSelectedterm1modules(),smp.getSelectedterm2modules(),smp.getYearLongModules());
				osp.addReservedModulestoViewList(t1rmp.getResrvedterm1modules(),t2rmp.getResrvedterm2modules());
				view.changeTab(3);
			}
			
			
		}
		}	
	//==================Overview Pane Buttons================
	private class SaveHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
		if(osp.getProfileView().isEmpty()) {
			alertDialogBuilder(AlertType.ERROR, "Information Dialog", "Error", "Error: Can't save an empty list!");
		}else {
			String pr = osp.getPrString();
			try (ObjectOutputStream prr = new ObjectOutputStream(new FileOutputStream("Profile.dat"));) {
				prr.writeObject(pr);
				prr.flush();
			}
			catch (IOException ioExcep){
				System.out.println("Error saving");
			}
		
		String sm = osp.getSMString();
		try (ObjectOutputStream sms = new ObjectOutputStream(new FileOutputStream("SelectedModules.txt"));) {
			sms.writeObject(sm);
			sms.flush();
		}
		catch (IOException ioExcep){
			System.out.println("Error saving");
		}
		
		String rm = osp.getRMString();
		try (ObjectOutputStream rms = new ObjectOutputStream(new FileOutputStream("ReservedModules.txt"));) {
			rms.writeObject(rm);
			rms.flush();
			alertDialogBuilder(AlertType.INFORMATION, "Information Dialog", "Save success", "Profile saved to Profile.txt\n"
					+ "Selected module saved to SelectedModules.txt\nReserved modules saved to ReservedModules.txt");
		}
		catch (IOException ioExcep){
			System.out.println("Error saving");
		}
		
		}
		}
}

	private class LoadHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			osp.clearViewList();
			try (ObjectInputStream prs = new ObjectInputStream(new FileInputStream("Profile.dat"));
					ObjectInputStream sms = new ObjectInputStream(new FileInputStream("SelectedModules.txt"));
					ObjectInputStream rms = new ObjectInputStream(new FileInputStream("ReservedModules.txt"));) {

				String pr = "";
				String sm = "";
				String rm = "";

				while ((pr = (String) prs.readObject()) != null && (sm = (String) sms.readObject()) !=null&& (rm = (String) rms.readObject()) !=null) {
					osp.AddLoadProfile(pr);
					osp.AddLoadReservedModules(rm);
					osp.AddLoadSelectedModules(sm);
				}	
				prs.close(); 
				sms.close();
				rms.close();
			}
			catch (IOException ioExcep){
				System.out.println("Error loading");
			}
			catch (ClassNotFoundException c) {
				System.out.println("Class Not found");
			}
			alertDialogBuilder(AlertType.INFORMATION, "Information Dialog", "Load success", "Profile loaded from Profile.txt\nSelected modules loaded from "
					+"SelectedModules.txt\nReserved modules loaded from ReservedModules.txt");
		}
	}
	
	//helper method - generates course and module data and returns courses within an array
	private Course[] generateAndGetCourses() {
		Module imat3423 = new Module("IMAT3423", "Systems Building: Methods", 15, true, Schedule.TERM_1);
		Module ctec3451 = new Module("CTEC3451", "Development Project", 30, true, Schedule.YEAR_LONG);
		Module ctec3902_SoftEng = new Module("CTEC3902", "Rigorous Systems", 15, true, Schedule.TERM_2);	
		Module ctec3902_CompSci = new Module("CTEC3902", "Rigorous Systems", 15, false, Schedule.TERM_2);
		Module ctec3110 = new Module("CTEC3110", "Secure Web Application Development", 15, false, Schedule.TERM_1);
		Module ctec3605 = new Module("CTEC3605", "Multi-service Networks 1", 15, false, Schedule.TERM_1);	
		Module ctec3606 = new Module("CTEC3606", "Multi-service Networks 2", 15, false, Schedule.TERM_2);	
		Module ctec3410 = new Module("CTEC3410", "Web Application Penetration Testing", 15, false, Schedule.TERM_2);
		Module ctec3904 = new Module("CTEC3904", "Functional Software Development", 15, false, Schedule.TERM_2);
		Module ctec3905 = new Module("CTEC3905", "Front-End Web Development", 15, false, Schedule.TERM_2);
		Module ctec3906 = new Module("CTEC3906", "Interaction Design", 15, false, Schedule.TERM_1);
		Module ctec3911 = new Module("CTEC3911", "Mobile Application Development", 15, false, Schedule.TERM_1);
		Module imat3410 = new Module("IMAT3104", "Database Management and Programming", 15, false, Schedule.TERM_2);
		Module imat3406 = new Module("IMAT3406", "Fuzzy Logic and Knowledge Based Systems", 15, false, Schedule.TERM_1);
		Module imat3611 = new Module("IMAT3611", "Computer Ethics and Privacy", 15, false, Schedule.TERM_1);
		Module imat3613 = new Module("IMAT3613", "Data Mining", 15, false, Schedule.TERM_1);
		Module imat3614 = new Module("IMAT3614", "Big Data and Business Models", 15, false, Schedule.TERM_2);
		Module imat3428_CompSci = new Module("IMAT3428", "Information Technology Services Practice", 15, false, Schedule.TERM_2);


		Course compSci = new Course("Computer Science");
		compSci.addModuleToCourse(imat3423);
		compSci.addModuleToCourse(ctec3451);
		compSci.addModuleToCourse(ctec3902_CompSci);
		compSci.addModuleToCourse(ctec3110);
		compSci.addModuleToCourse(ctec3605);
		compSci.addModuleToCourse(ctec3606);
		compSci.addModuleToCourse(ctec3410);
		compSci.addModuleToCourse(ctec3904);
		compSci.addModuleToCourse(ctec3905);
		compSci.addModuleToCourse(ctec3906);
		compSci.addModuleToCourse(ctec3911);
		compSci.addModuleToCourse(imat3410);
		compSci.addModuleToCourse(imat3406);
		compSci.addModuleToCourse(imat3611);
		compSci.addModuleToCourse(imat3613);
		compSci.addModuleToCourse(imat3614);
		compSci.addModuleToCourse(imat3428_CompSci);

		Course softEng = new Course("Software Engineering");
		softEng.addModuleToCourse(imat3423);
		softEng.addModuleToCourse(ctec3451);
		softEng.addModuleToCourse(ctec3902_SoftEng);
		softEng.addModuleToCourse(ctec3110);
		softEng.addModuleToCourse(ctec3605);
		softEng.addModuleToCourse(ctec3606);
		softEng.addModuleToCourse(ctec3410);
		softEng.addModuleToCourse(ctec3904);
		softEng.addModuleToCourse(ctec3905);
		softEng.addModuleToCourse(ctec3906);
		softEng.addModuleToCourse(ctec3911);
		softEng.addModuleToCourse(imat3410);
		softEng.addModuleToCourse(imat3406);
		softEng.addModuleToCourse(imat3611);
		softEng.addModuleToCourse(imat3613);
		softEng.addModuleToCourse(imat3614);

		Course[] courses = new Course[2];
		courses[0] = compSci;
		courses[1] = softEng;
		
		
		
		return courses;
	}

	private void alertDialogBuilder(AlertType type, String title, String header, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
	
	
