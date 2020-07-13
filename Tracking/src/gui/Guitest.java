package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class Guitest {
	static JTextField cnt;
	static JButton ok;
	public static final Font FONT = new Font( "Arial", Font.BOLD, 13 );
	final static ActionEvent CANCEL = new ActionEvent( new Guitest(), 7, "cancel" );
	public static void main(String[] args) {
	    JFrame frame = new JFrame("Marking");	     
		frame.setSize(1000, 1000);
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
	frame.add(frameList);
	frame.setVisible(true);
	}
		// TODO Auto-generated method stub
	/*	JFrame trainCount=new JFrame();
		trainCount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		trainCount.getContentPane().setBackground( Color.GRAY );
		trainCount.setSize(300,400);
		trainCount.setLocationRelativeTo(null);
		cnt=new JTextField();
		//cnt.setBounds(250, 250, 100, 100);
		JPanel controlFrame= new JPanel();
		controlFrame.setLayout(null);
		controlFrame.setBackground(Color.GRAY );
		JLabel projectName= new JLabel("Project Name *:");
		projectName.setFont(new Font( "Arial", Font.PLAIN, 20 ));
		projectName.setBounds( 50, 100, 200, 100 );
		controlFrame.add(projectName);
		//cnt.setColumns(20);
		cnt.setBounds( 220, 140, 30, 30 );
		controlFrame.add(cnt);
		controlFrame.add(addButton("Cancel",null,150, 300, 100, 50, CANCEL));
		String a=cnt.getText();
		System.out.println(a);
	//	ok=new JButton("OK");
	//	ok.setBounds(50, 50, 100, 60);
     //   trainCount.add(cnt);			
    //    trainCount.add(ok);
		JOptionPane.showMessageDialog(trainCount,"Dataset too small for labelling and training");
        trainCount.add(controlFrame);
        trainCount.setVisible(true);
	}
	private static void doAction( final ActionEvent event )
	{
		
		if(event==CANCEL)
		{
			System.out.println(cnt.getText());
		}
		
	}*/
/*	private static JButton addButton( final String label, final ImageIcon icon, final int x,
			final int y, final int width, final int height,final ActionEvent action)
	{
		final JButton button =  new JButton(label, icon);
		button.setFont( FONT );
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
		} );

		return button;
	}
	*/
}
