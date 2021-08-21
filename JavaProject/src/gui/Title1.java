package gui;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Title1 extends JTextField implements Component {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8833974218345114821L;
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
        return "Title1";
    }
}