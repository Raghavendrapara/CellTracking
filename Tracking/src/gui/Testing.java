package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ij.ImageJ;
import ij.ImagePlus;
//import activeSegmentation.gui.Gui;
import ij.WindowManager;


public class Testing {
	
	static String filePath;
	
	
	final static ActionEvent SELECT_BUTTON_PRESSED = new ActionEvent(new Testing(), 1, "create" );
	/** This {@link ActionEvent} is fired when the 'next' button is pressed. */
	final static ActionEvent OPEN_BUTTON_PRESSED = new ActionEvent(new  Testing() , 2, "open" );
	
	final static ActionEvent CREATE_BUTTON_PRESSED = new ActionEvent(new  Testing() , 2, "create" );
	final static ActionEvent EDIT_BUTTON_PRESSED = new ActionEvent(new  Testing() , 2, "edit" );
	final static ActionEvent DELETE_BUTTON_PRESSED = new ActionEvent(new  Testing() , 2, "delete" );
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
			file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			file.setAcceptAllFileFilterUsed(false);
			int rVal = file.showOpenDialog(null);
			if (rVal == JFileChooser.APPROVE_OPTION) {
			 File f=file.getSelectedFile();
			 String fileName=f.getPath(); 
			 filePath=fileName;
			 Process(fileName);
			// new Gui(projectManager);
			// new Gui2(projectManager);
			}
		}

		else if(event ==OPEN_BUTTON_PRESSED ){
			//JFileChooser fileChooser = new JFileChooser();
System.out.println("Start to Label");

			
			// new Gui2(projectManager);
			}
		else if(event == CREATE_BUTTON_PRESSED)
		{
			
			List<String> images=loadImages(filePath);
			JFrame trainCount=new JFrame();
			JTextField cnt=new JTextField();
			cnt.setBounds(50, 50, 100, 100);
			JButton ok=new JButton("OK");
			ok.setBounds(250, 250, 50, 50);
            trainCount.add(cnt);			
            trainCount.add(ok);
		}
		else if(event == EDIT_BUTTON_PRESSED)
		{
			
			
			
		}
		else if(event == DELETE_BUTTON_PRESSED)
		{
			
			
			
			
			
		}
		}


	
/*
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

/*
	controlFrame.setLocation(0, 0);
	mainFrame.add(controlFrame);
	mainFrame.setVisible(true);
	return mainFrame;

}
*/
static void Process(String fileName)
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
	List<String> images=loadImages(fileName);
	if(images.size()<10)
		System.out.println("Dataset too small for labelling and training");
	else {
	System.out.println("Size=  "+images.size());
	new ImageJ();
	System.out.println("Size=  "+images.get(1));
	int i=1;
	ImagePlus fi=new ImagePlus(fileName+"/"+images.get(i));
	fi.show();
	}
	
	
	//ImageIcon ss=new ImageIcon();
	//java.net.URL imgURL = Testing.class.getResource("/home/raghavendra/Desktop/index,png");
	//ss=new ImageIcon(imgURL,"FFF");
	controlFrame.add(addButton("create",createImageIcon("addProject.png","add"), 30, 250, 100, 60, CREATE_BUTTON_PRESSED));
	controlFrame.add(addButton("edit",createImageIcon("openProject.png","add"),210, 250, 100, 60, EDIT_BUTTON_PRESSED));
	controlFrame.add(addButton("delete",createImageIcon("openProject.png","add"),400, 250, 100, 60, DELETE_BUTTON_PRESSED));
	
	
	controlFrame.setLocation(0, 0);
	events.add(controlFrame);
	events.setVisible(true);}



private static List<String> loadImages(String directory){
	List<String> imageList= new ArrayList<String>();
	File folder = new File(directory);
	File[] images = sortImages(folder.listFiles());
	
	for (File file : images) {
		if (file.isFile()) {
			imageList.add(file.getName());
		}
	}
	
	return imageList;
}
private static File[] sortImages(File[] images) {
	final Pattern p = Pattern.compile("\\d+");
	Arrays.sort(images, new  Comparator<File>(){
		public int compare(File o1, File o2) {
			Matcher m = p.matcher(o1.getName());
			Integer number1 = null;
			if (!m.find()) {
				return o1.getName().compareTo(o2.getName());
			}
			else {
				Integer number2 = null;
				number1 = Integer.parseInt(m.group());
				m = p.matcher(o2.getName());
				if (!m.find()) {
					return o1.getName().compareTo(o2.getName());
				}
				else {
					number2 = Integer.parseInt(m.group());
					int comparison = number1.compareTo(number2);
					if (comparison != 0) {
						return comparison;
					}
					else {
						return o1.getName().compareTo(o2.getName());
					}
				}
			}
		}}
			);
	return images;
}
}




