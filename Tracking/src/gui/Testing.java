package gui;



import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
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

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import ij.ImageJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.plugin.frame.RoiManager;
import ij.process.ImageProcessor;
import static gui.ASCommon.*;

public class Testing {
	
	static String filePath;
	private static ImageOverlay resultOverlay;
	static JTextField cn;
	static int trainCnt;
	static int imageNum;
	static ImageStack train;
	private static ActionEvent NEXT_BUTTON_PRESSED = new ActionEvent( new Testing(), 0, "Next" );
	/** This {@link ActionEvent} is fired when the 'previous' button is pressed. */
	private static ActionEvent PREVIOUS_BUTTON_PRESSED = new ActionEvent( new Testing(), 1, "Previous" );
	private static ActionEvent COMPUTE_BUTTON_PRESSED  = new ActionEvent( new Testing(), 5, "TRAIN" );
	private static ActionEvent SAVE_BUTTON_PRESSED  = new ActionEvent( new Testing(), 6, "SAVEDATA" );
	private static ActionEvent TOGGLE_BUTTON_PRESSED = new ActionEvent( new Testing(), 7, "TOGGLE" );
	private static ActionEvent MASKS_BUTTON_PRESSED = new ActionEvent( new Testing(), 8, "MASKS" );
 

	final static ActionEvent SELECT_BUTTON_PRESSED = new ActionEvent(new Testing(), 1, "select" );
	final static ActionEvent OK_BUTTON_PRESSED = new ActionEvent(new Testing(), 2, "select" );
	static int overlayOpacity = 33;
	static SimpleCanvas ic;
    static Composite overlayAlpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, overlayOpacity / 100f);
	/** This {@link ActionEvent} is fired when the 'next' button is pressed. */
	final static ActionEvent OPEN_BUTTON_PRESSED = new ActionEvent(new  Testing() , 3, "open" );
	final static ActionEvent ADD_BUTTON_PRESSED = new ActionEvent(new  Testing() , 4, "add" );
	final static ActionEvent CREATE_BUTTON_PRESSED = new ActionEvent(new  Testing() , 5, "create" );
	final static ActionEvent EDIT_BUTTON_PRESSED = new ActionEvent(new  Testing() , 6, "edit" );
	final static ActionEvent DELETE_BUTTON_PRESSED = new ActionEvent(new  Testing() , 7, "delete" );
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
			//trainCount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			trainCount.getContentPane().setBackground( Color.GRAY );
			trainCount.setSize(300,400);
			trainCount.setLocationRelativeTo(null);
			cn=new JTextField();
	
			JPanel controlFrame= new JPanel();
			controlFrame.setLayout(null);
			controlFrame.setBackground(Color.GRAY );
			JLabel trainNum= new JLabel("NumOfTrainImages");
			trainNum.setFont(new Font( "Arial", Font.PLAIN, 20 ));
			trainNum.setBounds( 50, 100, 200, 100 );
			controlFrame.add(trainNum);
			//cn.setColumns(20);
			cn.setBounds(  220, 140, 30, 30 );
			controlFrame.add(cn);
			controlFrame.add(addButton("OK",null,150, 300, 100, 50,OK_BUTTON_PRESSED ));
			trainCount.add(controlFrame);
	        trainCount.setVisible(true);
	        int i=3;
	        	ImagePlus frame1=new ImagePlus(filePath+"/"+images.get(i-1));
	        	ImagePlus frame2=new ImagePlus(filePath+"/"+images.get(i));
	        	ImagePlus frame3=new ImagePlus(filePath+"/"+images.get(i+1));
	        	ImageStack gtLabel=new ImageStack();
	        	ImageProcessor iP1=frame1.getProcessor();
	        	ImageProcessor iP2=frame2.getProcessor();
	        	ImageProcessor iP3=frame3.getProcessor();
	        	gtLabel.addSlice(iP1);
	        	gtLabel.addSlice(iP2);
	        	gtLabel.addSlice(iP3);
	        	train=gtLabel;
	            ImagePlus xy=new ImagePlus();
	            xy.setStack("Sequence:"+i, gtLabel);
	          // xy.show();
	            JFrame frame = new JFrame("Marking");	     
	    		frame.setSize(1200,1200);
	    		frame.setResizable(true);
	     		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    		DefaultListModel<String> l=new DefaultListModel<>();
	    		l.addElement("f");l.addElement("dd");
	    		JList<String> frameList=new JList<String>(l);
	    		frameList.setVisibleRowCount(5);
	    		frameList.setFixedCellHeight(20);
	    		frameList.setFixedCellWidth(100);
	    		frameList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	    		frameList.setForeground(Color.BLACK);
	    		
	    		
	    		
	    		JPanel panel = new JPanel();
	    		panel.setLayout(null);
	    		panel.setFont(new Font("Arial",Font.BOLD,13));
	    		panel.setBackground(Color.GRAY);
	    		
	    		JPanel imagePanel=new JPanel();
	    		
	    		
	    		/*
	    		 * image panel
	    		 */
	    		imagePanel.setLayout(new BorderLayout());
	    		
	    	     ic=new SimpleCanvas(xy);
	    		ic.setMinimumSize(new Dimension(IMAGE_CANVAS_DIMENSION, IMAGE_CANVAS_DIMENSION));
	    		
	    		setOverlay();
	    		imagePanel.setBackground(Color.GRAY);		
	    		imagePanel.add(ic,BorderLayout.CENTER);
	    		imagePanel.setBounds( 10, 10, IMAGE_CANVAS_DIMENSION, IMAGE_CANVAS_DIMENSION );		
	    		panel.add(imagePanel);
	            frame.add(panel);
	            frame.setVisible(true);
	        
	       RoiManager roiman=new RoiManager();
	       //roiman.actionPerformed(EDIT_BUTTON_PRESSED);
	       roiman.setVisible(true);
	 /*      JPanel classPanel=new JPanel();
	   	classPanel.setBounds(605,20,350,100);
		classPanel.setPreferredSize(new Dimension(350, 100));
		classPanel.setBorder(BorderFactory.createTitledBorder("Browse"));
		
		JScrollPane classScrolPanel = new JScrollPane(classPanel);
		classScrolPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		classScrolPanel.setBounds(605,20,350,80);
		addClassPanel();
		panel.add(classScrolPanel);
		*/
		
		/*
		 * features
		 */
		JPanel features= new JPanel();
		features.setBounds(605,120,350,100);
		features.setBorder(BorderFactory.createTitledBorder("Training"));
		
		addButton(new JButton(), "PREVIOUS",null , 610, 130, 120, 20,features,PREVIOUS_BUTTON_PRESSED,null );
		
		JTextField imageNum= new JTextField();
		imageNum.setColumns(5);
		imageNum.setBounds( 630, 130, 10, 20 );
		JLabel dasedLine= new JLabel("/");
		dasedLine.setFont(new Font( "Arial", Font.PLAIN, 15 ));
		dasedLine.setForeground(Color.BLACK);
		dasedLine.setBounds(  670, 130, 10, 20 );
		JLabel total= new JLabel("Total");
		total.setFont(new Font( "Arial", Font.PLAIN, 15 ));
		total.setForeground(Color.BLACK);
		total.setBounds( 500, 600, 80, 30);		
		imageNum.setText("1");
		total.setText("15");
		features.add(imageNum);
		features.add(dasedLine);
		features.add(total);
		
		/*
		 * compute panel
		 */
		
		JPanel computePanel = new JPanel();
		addButton(new JButton(), "Next",null , 800, 130, 80, 20,features,NEXT_BUTTON_PRESSED,null );
		addButton(new JButton(), "Train",null, 550,550,350,100,computePanel, COMPUTE_BUTTON_PRESSED,null);
		addButton(new JButton(), "Save",null, 550,550,350,100,computePanel, SAVE_BUTTON_PRESSED,null);
		addButton(new JButton(), "Overlay",null, 550,550,350,100,computePanel, TOGGLE_BUTTON_PRESSED,null);
		addButton(new JButton(), "Masks",null, 550,550,350,100,computePanel, MASKS_BUTTON_PRESSED,null);
		
		features.add(computePanel);
		frame.add(features);
		
	       
		}
		
		else if(event == OK_BUTTON_PRESSED)
		{
		
			trainCnt=Integer.valueOf(cn.getText());
	
			
		}

			

				// force limit size of image window
				if(ic.getWidth()>IMAGE_CANVAS_DIMENSION) {
					int x_centre = ic.getWidth()/2+ic.getX();
					int y_centre = ic.getHeight()/2+ic.getY();
					ic.zoomIn(x_centre,y_centre);
				}
				//imagePanel.add(ic);
			
			

			


	}


	private static JButton addButton(final JButton button ,final String label, final Icon icon, final int x,
			final int y, final int width, final int height,
			JComponent panel, final ActionEvent action,final Color color )
	{
		panel.add(button);
		button.setText( label );
		button.setIcon( icon );
		button.setFont( panelFONT );
		button.setBorderPainted(false); 
		button.setFocusPainted(false); 
		button.setBackground(new Color(192, 192, 192));
		button.setForeground(Color.WHITE);
		if(color!=null){
			button.setBackground(color);
		}
		button.setBounds( x, y, width, height );
		button.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed( final ActionEvent e )
			{
				//System.out.println(e.toString());
				doAction(action);
			}
		});

		return button;
	}
	
	private static void setOverlay(){
		resultOverlay = new ImageOverlay();
		resultOverlay.setComposite( overlayAlpha );
		((OverlayedImageCanvas)ic).addOverlay(resultOverlay);
	}
/*	private void setOverlay(){
		resultOverlay = new ImageOverlay();
		resultOverlay.setComposite( overlayAlpha );
		((OverlayedImageCanvas)ic).addOverlay(resultOverlay);
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
		JOptionPane.showMessageDialog(events,"Dataset too small for labelling and training");
	else {
	JOptionPane.showMessageDialog(events,"Size=  "+images.size());
	new ImageJ();
 
    new JTextField();
    
	//ImagePlus fi=new ImagePlus(fileName+"/"+images.get(1));
	//fi.show();
	
	
	
	//ImageIcon ss=new ImageIcon();
	//java.net.URL imgURL = Testing.class.getResource("/home/raghavendra/Desktop/index,png");
	//ss=new ImageIcon(imgURL,"FFF");
	controlFrame.add(addButton("create",createImageIcon("addProject.png","add"), 30, 250, 100, 60, CREATE_BUTTON_PRESSED));
	controlFrame.add(addButton("edit",createImageIcon("openProject.png","add"),210, 250, 100, 60, EDIT_BUTTON_PRESSED));
	controlFrame.add(addButton("delete",createImageIcon("openProject.png","add"),400, 250, 100, 60, DELETE_BUTTON_PRESSED));
	}
	
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




