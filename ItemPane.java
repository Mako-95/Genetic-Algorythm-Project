package Suitcase;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ItemPane extends JPanel
{
	JPanel[][] cells;
	ArrayList<Item> items;
	
	public ItemPane(int columns, int rows, ArrayList<Item> list)
	{
		super();
		this.setBackground(Color.gray);
		loadItems(columns, rows, list);
	}
	
	public void loadItems(int columns, int rows, ArrayList<Item> list)
	{
		this.setLayout(new GridLayout(columns, rows));
		this.setPreferredSize(new Dimension(700, 700*columns/rows));
		this.items = list;
		
		if(cells != null)
		{
			for(int i = 0; i < cells.length; i++)
			{
				for(int j = 0; j < cells[i].length; j++)
				{
					this.remove(cells[i][j]);
				}
			}
		}
		
		cells = new JPanel[columns][rows+2];
		for(int i = 0; i < cells.length; i++)
		{
			for(int j = 0; j < cells[i].length; j++)
			{
				cells[i][j] = new JPanel();
				cells[i][j].setBackground(Color.lightGray);
				cells[i][j].setPreferredSize(new Dimension(500/Math.max(columns,rows), 500/Math.max(columns,rows)));
				this.add(cells[i][j]);
			}
		}
		int tmp = 0;
		for(Item item : items)
		{
			for(int i = 0; i < item.width; i++)
			{
				for(int j = 0; j < item.height; j++)
				{
					if(item.shape[i][j]) cells[i][tmp + j].setBackground(item.color);
				}
			}
			tmp += item.height;
		}
	}
}