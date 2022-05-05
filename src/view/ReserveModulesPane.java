package view;

import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

public class ReserveModulesPane extends Accordion{
	
	private Term1RMPane T1;
	private Term2RMPane T2;
	private TitledPane t1, t2;
	
	
	public ReserveModulesPane() {
	
		
		
		T1 = new Term1RMPane();
		T2 = new Term2RMPane();
		
		t1 = new TitledPane("Term 1 modules", T1);
		t2 = new TitledPane("Term 2 modules", T2);
		
		this.getPanes().addAll(t1, t2);
		
		this.setExpandedPane(t1);

}
	
	public Term1RMPane getTerm1ModulePane() {
		return T1;
	}

	public Term2RMPane getTerm2ModulePane() {
		return T2;
	
}
	
	public void changetoTerm1Pane() {
		this.setExpandedPane(t1);
		this.getExpandedPane();

	}
	
	public void changetoTerm2Pane() {
		this.setExpandedPane(t2);
		this.getExpandedPane();
	}
	

}