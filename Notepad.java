package Lesson12ClassProgramms;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class Notepad extends JFrame implements ActionListener
{
	JTextArea document = new JTextArea();
	JMenuBar bar = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenuItem open = new JMenuItem("Open");
	JMenuItem save = new JMenuItem("Save");
	JMenuItem exit = new JMenuItem("Exit");

	String filename = "";

	public Notepad()
	{
		super("Notepad");
		setSize(460,200);
		setVisible(true);

		open.addActionListener(this);
		save.addActionListener(this);
		exit.addActionListener(this);

		setLayout(new BorderLayout());
		file.add(open);
		file.add(save);
		file.add(exit);
		bar.add(file);
		setJMenuBar(bar);
		
		add(document,BorderLayout.CENTER);

	}

	public void actionPerformed(ActionEvent e)
	{
if (e.getSource() == open)
		{
			String content = "";

			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("TXT","txt"));
			int answer = chooser.showOpenDialog(this); 
			if (answer == JFileChooser.APPROVE_OPTION)
			{
				filename = chooser.getSelectedFile().getName();		
				try
				{
					BufferedReader b = new BufferedReader(new FileReader(filename));
					int character = 0;
					do
					{
						character = b.read();
						if (character != -1) content += ""+(char)character;
					} while (character != -1);
					b.close();
					document.setText(content);

				} catch(IOException ee)
				{
					System.out.println(ee.getMessage());
				}
			}			
		}

		if (e.getSource() == save)
		{
			String content = document.getText();

			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(new FileNameExtensionFilter("TXT","txt"));
			int answer = chooser.showSaveDialog(this); 
			if (answer == JFileChooser.APPROVE_OPTION)
			{
				filename = chooser.getSelectedFile().getName();		
				try
				{
					BufferedWriter b = new BufferedWriter(new FileWriter(filename));
					b.write(content);
					b.close();
				} catch(IOException ee)
				{
					System.out.println(ee.getMessage());
				}
			}
		}
		if (e.getSource() == exit)
		{
			int answer = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (answer == JOptionPane.YES_OPTION) System.exit(0);
		}

	}

	public static void main(String[] args)
	{
		Notepad n = new Notepad();
		n.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}