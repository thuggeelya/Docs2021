package system;
import javax.swing.DefaultListModel;

import gui.AddButton;
import gui.DeleteButton;
import gui.Filter;
import gui.List;
import gui.Mediator;
import gui.MediatorClass;
import gui.SaveButton;
import gui.TextBox;
import gui.Title1;
import gui.Title2;
import gui.Title3;
import gui.Title4;
import gui.TitleDen;
import gui.TitleMax;
import gui.TitleMin;
import gui.TitlePosMin;

public class Application {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		Mediator mediator = new MediatorClass();
        mediator.registerComponent(new Title1());
        mediator.registerComponent(new Title2());
        mediator.registerComponent(new Title3());
        mediator.registerComponent(new Title4());
        mediator.registerComponent(new TitleMin());
        mediator.registerComponent(new TitleMax());
        mediator.registerComponent(new TitleDen());
        mediator.registerComponent(new TitlePosMin());
        mediator.registerComponent(new TextBox());
        mediator.registerComponent(new AddButton());
        mediator.registerComponent(new DeleteButton());
        mediator.registerComponent(new SaveButton());
        mediator.registerComponent(new List(new DefaultListModel()));
        mediator.registerComponent(new Filter());
        mediator.createGUI();
	}
}