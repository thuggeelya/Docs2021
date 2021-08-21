package gui;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class TitlePosMin extends JTextField implements Component {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3657251690291284349L;
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
        return "TitlePosMin";
    }
}