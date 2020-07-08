package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//import activeSegmentation.gui.Gui;
import ij.WindowManager;


public class Testing {
	final static ActionEvent SELECT_BUTTON_PRESSED = new ActionEvent(new Testing(), 1, "create" );
	/** This {@link ActionEvent} is fired when the 'next' button is pressed. */
	final static ActionEvent OPEN_BUTTON_PRESSED = new ActionEvent(new  Testing() , 2, "open" );
public static void main(String args[])
{
	JFrame mainFrame = new JFrame();
	mainFrame.getContentPane().setBackground( Color.GRAY );
	//mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	mainFrame.setSize(600,600);
	mainFrame.setLocationRelativeTo(null);
	JPanel controlFrame= new JPanel();
	controlFrame.setLayout(null);
	controlFrame.setBackground(Color.GRAY );
	//setControls(controlFrame);
	//JLabel logo= new JLabel(createImageIcon("images/logo1.png","logo"));
	//logo.setBounds( 10, 10, 450, 200 );
	//controlFrame.add(logo);
	JLabel label= new JLabel("Ground Truth Label");
	label.setFont(new Font( "Arial", Font.BOLD, 32 ));
	label.setBounds( 100, 150, 450, 100 );
	label.setForeground(Color.BLACK);
	controlFrame.add(label);
	//ImageIcon ss=new ImageIcon();
	//java.net.URL imgURL = Testing.class.getResource("/home/raghavendra/Desktop/index,png");
	//ss=new ImageIcon(imgURL,"FFF");
	controlFrame.add(addButton("Select Sequence",createImageIcon("addProject.png","add"), 30, 250, 220, 60, SELECT_BUTTON_PRESSED));
	controlFrame.add(addButton("Open Project",createImageIcon("openProject.png","add"),270, 250, 200, 60, OPEN_BUTTON_PRESSED));
	controlFrame.setLocation(0, 0);
	mainFrame.add(controlFrame);
	mainFrame.setVisible(true);}
private static ImageIcon createImageIcon(String path, String description) {
	java.net.URL imgURL = Testing.class.getResource(path);
	if (imgURL != null) {
		return new ImageIcon(imgURL, description);
	} else {            
		//System.err.println("Couldn't find file: " + path);
		return null;
	}
}   
private static JButton addButton( final String label, final ImageIcon icon, final int x,
		final int y, final int width, final int height,final ActionEvent action)
{
	final JButton button =  new JButton(label, icon);

	button.setBorderPainted(false); 
	button.setFocusPainted(false); 
	button.setBackground(new Color(192, 192, 192));
	button.setForeground(Color.WHITE);
	button.setBounds( x, y, width, height );
	button.addActionListener( new ActionListener()
	{
		@Override
		public void actionPerformed( final ActionEvent e )
		{
			//System.out.println("CLICKED");
			doAction(action);
		}
	} );return button;
}

	private static void doAction( final ActionEvent event )
	{ 
		if(event ==SELECT_BUTTON_PRESSED ){
			JFileChooser file=new JFileChooser();
			file.setFileSelectionMode(JFileChooser.FILES_ONLY);
			file.setAcceptAllFileFilterUsed(false);
			int rVal = file.showOpenDialog(null);
			if (rVal == JFileChooser.APPROVE_OPTION) {
			 File f=file.getSelectedFile();
			 String fileName=f.getPath(); 
			 
			 Process(f);
			// new Gui(projectManager);
			// new Gui2(projectManager);
			}
		}

		if(event ==OPEN_BUTTON_PRESSED ){
			JFileChooser fileChooser = new JFileChooser();

			
			// new Gui2(projectManager);
			}
		}


	
private static JFrame createProject(){
	
	JFrame mainFrame = new JFrame("Create Project");
	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainFrame.getContentPane().setBackground( Color.GRAY );
	mainFrame.setSize(600,500);
	mainFrame.setLocationRelativeTo(null);
	JPanel controlFrame= new JPanel();
	controlFrame.setLayout(null);
	controlFrame.setBackground(Color.GRAY );
	JLabel label= new JLabel("Create Project");
	label.setFont(new Font( "Arial", Font.BOLD, 32 ));
	label.setBounds( 50, 0, 450, 100 );
	label.setForeground(Color.ORANGE);
	controlFrame.add(label);
	JLabel projectName= new JLabel("Project Name *:");
	projectName.setFont(new Font( "Arial", Font.PLAIN, 20 ));
	projectName.setBounds( 50, 100, 200, 30 );
	controlFrame.add(projectName);
	
	JLabel projectDesc= new JLabel("Project Desc :");
	projectDesc.setFont(new Font( "Arial", Font.PLAIN, 20 ));
	projectDesc.setBounds( 50, 140, 200, 30 );
	controlFrame.add(projectDesc);
	JLabel projectType= new JLabel("Project Type :");
	projectType.setFont(new Font( "Arial", Font.PLAIN, 20 ));
	projectType.setBounds( 50, 180, 250, 30 );
	controlFrame.add(projectType);


	if(null == WindowManager.getCurrentImage()) {
		JLabel trainingImage= new JLabel("Training Image *:");
		trainingImage.setFont(new Font( "Arial", Font.PLAIN, 20 ));
		trainingImage.setBounds( 50, 260, 200, 30 );
		controlFrame.add(trainingImage);		
	}
	
	/*JLabel testImage= new JLabel("Plugin Dir :");
	testImage.setFont(new Font( "Arial", Font.PLAIN, 20 ));
	testImage.setBounds( 50, 300, 200, 30 );
	controlFrame.add(testImage);		*/
	//pluginsDir.setColumns(200);
	//pluginsDir.setBounds( 200, 300, 250, 30 );
	//controlFrame.add(pluginsDir);	
	

	//controlFrame.add(addButton("Browse",null, 460, 300, 100, 30, TESTINGF_BUTTON_PRESSED));


	controlFrame.setLocation(0, 0);
	mainFrame.add(controlFrame);
	mainFrame.setVisible(true);
	return mainFrame;

}
static void Process(File file)
{
	JFrame events=new JFrame();
	events.getContentPane().setBackground( Color.GRAY );
	//mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	events.setSize(600,600);
	events.setLocationRelativeTo(null);
	JPanel controlFrame= new JPanel();
	controlFrame.setLayout(null);
	controlFrame.setBackground(Color.GRAY );
	//setControls(controlFrame);
	//JLabel logo= new JLabel(createImageIcon("images/logo1.png","logo"));
	//logo.setBounds( 10, 10, 450, 200 );
	//controlFrame.add(logo);
	JLabel label= new JLabel("Event");
	label.setFont(new Font( "Arial", Font.BOLD, 32 ));
	label.setBounds( 190, 150, 450, 100 );
	label.setForeground(Color.BLACK);
	controlFrame.add(label);
	//ImageIcon ss=new ImageIcon();
	//java.net.URL imgURL = Testing.class.getResource("/home/raghavendra/Desktop/index,png");
	//ss=new ImageIcon(imgURL,"FFF");
	controlFrame.add(addButton("create",createImageIcon("addProject.png","add"), 30, 250, 100, 60, SELECT_BUTTON_PRESSED));
	controlFrame.add(addButton("edit",createImageIcon("openProject.png","add"),210, 250, 100, 60, OPEN_BUTTON_PRESSED));
	controlFrame.add(addButton("delete",createImageIcon("openProject.png","add"),400, 250, 100, 60, OPEN_BUTTON_PRESSED));
	
	controlFrame.setLocation(0, 0);
	events.add(controlFrame);
	events.setVisible(true);}

}





