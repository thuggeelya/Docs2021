package gui;

import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class AddButton extends JButton implements Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2355657687612902226L;
	private Mediator mediator;

    public AddButton() {
        super("Add");
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent actionEvent) {
        mediator.addNewNote(new Note());
    }

    @Override
    public String getName() {
        return "AddButton";
    }

}