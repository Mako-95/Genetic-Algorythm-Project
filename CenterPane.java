package Suitcase;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.ArrayList;

public class CenterPane extends JPanel
{
	SuitCasePane suitCasePane;
	ItemPane itemPane;
	ArrayList<Item> items;
	Unit order;
	int rows, columns;
	int itemCount = 40;
	int suitWidth = 10;
	int suitHeight = 10;
	//Path path = Paths.get("/Users/maks/java-workspace/Gene/src/Suitcase/text.csv");
	//String string = "";
	public CenterPane()
	{
		super();
		
		this.setBackground(Color.darkGray);
		this.setLayout(new BorderLayout());
		
		newItems();
		itemPane = new ItemPane(columns, rows, items);
		this.add(itemPane, BorderLayout.PAGE_END);
		this.itemUp();
		
		unitUp();
		
		suitCasePane = new SuitCasePane(suitWidth, suitHeight);
		this.add(suitCasePane);
		this.suitUp();
		//string += order.fit;
	}
	
	public void suitUp()
	{
		suitCasePane.fill(order);
	}
	
	public void itemUp()
	{
		itemPane.loadItems(columns, rows, items);
	}
	
	public void newItems()
	{
		rows = 0;
		columns = 0;
		if(items == null) items = new ArrayList<>();
		else items.clear();
		Item tmp;
		for(int i = 0; i < itemCount; i++)
		{
			tmp = new Item(5);
			items.add(tmp);
			rows += tmp.height;
			if(tmp.width > columns) columns = tmp.width;
		}
		items.trimToSize();
	}
	
	public void unitUp()
	{
		order = new Unit(items, suitWidth, suitHeight);
		System.out.println("Fit: "+order.fit);
	}
	
	public void mutate() throws IOException
	{
		Unit newUnit = new Unit(order);
		newUnit.mutate();
		//string = string + "," + System.lineSeparator() +  newUnit.fit;
		//Files.writeString(path, string, StandardCharsets.UTF_8);
		if(newUnit.fit < order.fit)
		{
			order = new Unit(newUnit);
			System.out.println(newUnit.fit);
		}
		suitUp();
	}
	
	public void test() throws IOException
	{
		for(int i = 0; i < 1000; i++)
		{
			mutate();
		}
	}
}