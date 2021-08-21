package gui;
import javax.swing.*;

public interface Mediator {
	void addNewNote(Note note);
    void deleteNote();
    void getInfoFromList(Note note);
    void saveChanges();
    void markNote();
    void clear();
    void sendToFilter(@SuppressWarnings("rawtypes") ListModel listModel);
    void setElementsList(@SuppressWarnings("rawtypes") ListModel list);
    void registerComponent(Component component);
    void hideElements(boolean flag);
    void createGUI();
}