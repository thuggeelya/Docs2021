package gui;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class TitleMin extends JTextField implements Component {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2913334477417457586L;
	private Mediator mediator;

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    protected void processComponentKeyEvent(KeyEvent keyEvent) {
        mediator.markNote();
    }

    @Override
    public String getName() {
        return "TitleMin";
    }
}