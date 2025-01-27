package Suitcase;

import java.awt.*;
import java.util.Random;

public class Item
{
	int width, height, x, y;
	boolean[][] shape;
	boolean placed;
	Color color;
	Random random = new Random();
	
	public Item(int maxSize)
	{
		x = 0;
		y = 0;
		this.width = Math.min(random.nextInt(1, maxSize+1), random.nextInt(1, maxSize+1));
		this.height = Math.min(random.nextInt(1, maxSize-height+1), random.nextInt(1, maxSize-height+1));
		color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
		this.shape = new boolean[width][height];
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				shape[i][j] = random.nextBoolean() || random.nextBoolean();
			}
		}
		shape[0][0] = true;
		placed = false;
		trim();
	}
	
	public Item(Item oldItem)
	{
		this.width = oldItem.width;
		this.height = oldItem.height;
		this.x = oldItem.x;
		this.y = oldItem.y;
		this.shape = oldItem.shape;
		this.placed = oldItem.placed;
		this.color = oldItem.color;
		this.random = oldItem.random;
	}
	public void rotate(boolean clockwise)
	{
		boolean[][] newShape = new boolean[height][width];
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				newShape[j][i] = clockwise ? shape[i][height-(j+1)] : shape[width-(i+1)][j];
			}
		}
		shape = newShape;
		width = shape.length;
		height = shape[0].length;
	}
	
	public void trim()
	{
		boolean flag = true;
		boolean[][] tmp;
		boolean notEmpty;
		int index = 0;
		
		while(flag)
		{
			flag = false;
			notEmpty = false;
			for(int i = 0; i < width; i++)
			{
				notEmpty = notEmpty || shape[i][0];
			}
			if(!notEmpty)
			{
				tmp = new boolean[width][height - 1];
				for(int i = 0; i < width; i++)
				{
					if(height - 1 >= 0) System.arraycopy(shape[i], 1, tmp[i], 0, height - 1);
				}
				shape = tmp;
				height--;
				if(index != 0) index = 0;
				flag = true;
			}
			else if(index < 4)
			{
				rotate(true);
				index++;
				flag = true;
			}
		}
		rotate(true);
	}
}