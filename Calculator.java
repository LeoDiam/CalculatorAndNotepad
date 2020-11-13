package Lesson12ClassProgramms;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener
{
	private static final int NUMBER_OF_BUTTONS = 12;
	JPanel up = new JPanel();
	JPanel down = new JPanel();
	JTextField screen = new JTextField();
	JButton[] b = new JButton[NUMBER_OF_BUTTONS];
	String content = "";
	int number = 0;
	String lastOperation = "";

	public Calculator()
	{
		super("Calculator");
		setSize(150,250);
		setVisible(true);

		setLayout(new BorderLayout());
		
		// Panel 1
		up.setLayout(new GridLayout(1,1));
		up.add(screen);

		// Panel 2
		down.setLayout(new GridLayout(4,3));
		for (int i = 0; i < NUMBER_OF_BUTTONS; i++)
		{
			String buttonLabel = ""+(i+1);
			if (i == 9) buttonLabel = "0";
			if (i == 10) buttonLabel = "+";
			if (i == 11) buttonLabel = "=";

			b[i] = new JButton(buttonLabel);
			b[i].addActionListener(this);
			down.add(b[i]);
		}

		add(up, BorderLayout.NORTH);
		add(down, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e)
	{
		for (int i = 0; i < 10; i++)
		{
			if (e.getSource() == b[i]) content += e.getActionCommand();
		}
		if (e.getSource() == b[10])
		{
			number += Integer.parseInt(content);
			content = "";
			lastOperation = "+";
		}
		if (e.getSource() == b[11])
		{
			if (lastOperation.equals("+")) number += Integer.parseInt(content);
			// if you have more operations, e.g., *,/,-, then add respective if statements here.
			content = ""+number;
			lastOperation = "";
			number = 0;
		}


		screen.setText(content);
	}

	public static void main(String[] args)
	{
		Calculator c = new Calculator();
		c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
