package gui;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class Title2 extends JTextField implements Component {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6916466801558568489L;
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
        return "Title2";
    }
}