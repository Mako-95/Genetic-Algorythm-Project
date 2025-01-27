package Suitcase;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main extends JFrame
{
	CenterPane centerPane;
	
	public Main()
	{
		super("GeneCase");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(700, 800+23+30);
		this.setLocationRelativeTo(null);
		
		centerPane = new CenterPane();
		this.add(centerPane, BorderLayout.CENTER);
		
		JPanel bottomPane = new JPanel();
		bottomPane.setPreferredSize(new Dimension(getWidth(), 30));
		bottomPane.setLayout(new GridLayout(1,5));
		this.add(bottomPane, BorderLayout.PAGE_END);
		
		JButton suitButton = new JButton("Suitcase");
		suitButton.addActionListener(e ->
		{
			centerPane.unitUp();
			centerPane.suitUp();
			this.pack();
		});
		bottomPane.add(suitButton);
		
		JButton itemButton = new JButton("Items");
		itemButton.addActionListener(e ->
		{
			centerPane.newItems();
			centerPane.itemUp();
			this.pack();
		});
		bottomPane.add(itemButton);
		
		Timer timer = new Timer(1, e ->
		{
			try
			{
				centerPane.mutate();
			}
			catch(IOException ex)
			{
				throw new RuntimeException(ex);
			}
		});
		
		JButton mutateButton = new JButton("Mutate");
		mutateButton.addActionListener(e -> timer.start());
		bottomPane.add(mutateButton);
		
		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(e -> timer.stop());
		bottomPane.add(stopButton);
		
		JButton testButton = new JButton("test");
		testButton.addActionListener(e ->
		{
			try
			{
				centerPane.test();
			}
			catch(IOException ex)
			{
				throw new RuntimeException(ex);
			}
		});
		bottomPane.add(testButton);
		
		this.pack();
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(() ->
		{
			Main frame;
			frame = new Main();
			frame.setVisible(true);
		});
	}
}