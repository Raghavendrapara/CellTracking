package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Guitest {
	static JTextField cnt;
	static JButton ok;
	public static final Font FONT = new Font( "Arial", Font.BOLD, 13 );
	final static ActionEvent CANCEL = new ActionEvent( new Guitest(), 7, "cancel" );
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame trainCount=new JFrame();
		trainCount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		trainCount.getContentPane().setBackground( Color.GRAY );
		trainCount.setSize(600,500);
		trainCount.setLocationRelativeTo(null);
		cnt=new JTextField();
		//cnt.setBounds(250, 250, 100, 100);
		JPanel controlFrame= new JPanel();
		controlFrame.setLayout(null);
		controlFrame.setBackground(Color.GRAY );
		JLabel projectName= new JLabel("Project Name *:");
		projectName.setFont(new Font( "Arial", Font.PLAIN, 20 ));
		projectName.setBounds( 50, 100, 200, 30 );
		controlFrame.add(projectName);
		cnt.setColumns(20);
		cnt.setBounds( 200, 100, 250, 30 );
		controlFrame.add(cnt);
		controlFrame.add(addButton("Cancel",null,270, 350, 200, 60, CANCEL));
		String a=cnt.getText();
		System.out.println(a);
	//	ok=new JButton("OK");
	//	ok.setBounds(50, 50, 100, 60);
     //   trainCount.add(cnt);			
    //    trainCount.add(ok);
        trainCount.add(controlFrame);
        trainCount.setVisible(true);
	}
	private static void doAction( final ActionEvent event )
	{
		
		if(event==CANCEL)
		{
			System.out.println(cnt.getText());
		}
		
	}
	private static JButton addButton( final String label, final ImageIcon icon, final int x,
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
	
}
