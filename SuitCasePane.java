package Suitcase;

import javax.swing.*;
import java.awt.*;

public class SuitCasePane extends JPanel
{
	JPanel[][] cells;
	
	public SuitCasePane(int columns, int rows)
	{
		super();
		this.setBackground(Color.gray);
		this.setLayout(new GridLayout(columns, rows));
		this.setPreferredSize(new Dimension(700, 700));
		
		cells = new JPanel[columns][rows];
		for(int i = 0; i < columns; i++)
		{
			for(int j = 0; j < rows; j++)
			{
				cells[i][j] = new JPanel();
				cells[i][j].setBackground(Color.lightGray);
				cells[i][j].setPreferredSize(new Dimension(500/columns, 500/rows));
				this.add(cells[i][j]);
			}
		}
	}
	
	public void clear()
	{
		for(int i = 0; i < cells.length; i++)
		{
			for(int j = 0; j < cells[0].length; j++)
			{
				cells[i][j].setBackground(Color.lightGray);
			}
		}
	}
	
	public void fill(Unit unit)
	{
		clear();
		for(Item item : unit.items)
		{
			if(item.placed)
			{
				for(int i = 0; i < item.width; i++)
				{
					for(int j = 0; j < item.height; j++)
					{
						if(item.shape[i][j]) cells[item.x+i][item.y+j].setBackground(item.color);
					}
				}
			}
		}
	}
}