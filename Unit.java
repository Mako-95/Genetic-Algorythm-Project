package Suitcase;

import java.util.ArrayList;
import java.util.Random;

public class Unit
{
	ArrayList<Item> items;
	boolean[][] suitCase;
	int fit;
	Random random = new Random();
	
	public Unit(ArrayList<Item> list, int width, int height)
	{
		items = list;
		fit = items.size();
		suitCase = new boolean[width][height];
		for(int i = 0; i < suitCase.length; i++)
			for(int j = 0; j < suitCase[0].length; j++)
				suitCase[i][j] = false;
		items.trimToSize();
	}
	
	public Unit(Unit oldUnit)
	{
		this.items = oldUnit.items;
		this.suitCase = oldUnit.suitCase;
		this.fit = oldUnit.fit;
		this.random = oldUnit.random;
	}
	
	public boolean checkNoOverlap(Item item)
	{
		for(int i = 0; i < item.width; i++)
		{
			for(int j = 0; j < item.height; j++)
			{
				if(item.shape[i][j] && suitCase[item.x+i][item.y+j])
					return false;
			}
		}
		return true;
	}
	
	public void setItem(Item item, boolean insert)
	{
		for(int i = 0; i < item.width; i++)
		{
			for(int j = 0; j < item.height; j++)
			{
				if(item.shape[i][j]) suitCase[item.x+i][item.y+j] = insert;
			}
		}
		item.placed = insert;
		fit += insert ? -1 : 1;
	}
	
	public void mutate()
	{
		int index = random.nextInt(items.size());
		Item tmp = new Item(items.get(index));
		boolean placed = tmp.placed;
		if(placed) setItem(tmp, false);
		if(random.nextDouble() < 0.2) tmp.rotate(random.nextBoolean());
		tmp.x = random.nextInt(suitCase.length - tmp.width + 1);
		tmp.y = random.nextInt(suitCase[0].length - tmp.height + 1);
		if(checkNoOverlap(tmp))
		{
			items.remove(index);
			items.add(index, tmp);
			setItem(items.get(index), true);
		}
		else if(placed)
			setItem(items.get(index), true);
	}
}