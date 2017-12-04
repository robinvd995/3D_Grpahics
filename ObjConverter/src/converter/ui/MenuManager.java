package converter.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import converter.io.IOManager;
import converter.model.IndexedModel;

public class MenuManager implements ActionListener{
	
	private final ModelScene scene;
	
	public MenuManager(ModelScene scene){
		this.scene = scene;
	}
	
	public void addMenuBar(JFrame frame){
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuFile = new JMenu("File");
		
		JMenuItem itemNew = createMenuItem(EnumAction.NEW);
		JMenuItem itemOpen = createMenuItem(EnumAction.OPEN);
		JMenuItem itemSave = createMenuItem(EnumAction.SAVE);
		JMenuItem itemExit = createMenuItem(EnumAction.EXIT);
		
		menuFile.add(itemNew);
		menuFile.addSeparator();
		menuFile.add(itemOpen);
		menuFile.add(itemSave);
		menuFile.addSeparator();
		menuFile.add(itemExit);
		
		menuBar.add(menuFile);
		
		frame.setJMenuBar(menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent evnt) {
		EnumAction action = EnumAction.getActionFromCommand(evnt.getActionCommand());
		switch(action){
		case NEW:
			
			break;
			
		case OPEN:
			openFile();
			break;
			
		case SAVE:
			saveFile();
			break;
			
		case EXIT:
			
			break;
		}
	}
	
	private void openFile(){
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION){
			File file = chooser.getSelectedFile();
			IndexedModel model = IOManager.loadModel(file);
			scene.setModel(model);
		}
	}
	
	private void saveFile(){
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION){
			File file = chooser.getSelectedFile();
			IOManager.exportModel(scene.getModel(), file);
		}
	}
	
	private JMenuItem createMenuItem(EnumAction action){
		JMenuItem item = new JMenuItem(action.getActionName());
		item.setActionCommand(action.getActionCommand());
		item.addActionListener(this);
		return item;
	}
}
