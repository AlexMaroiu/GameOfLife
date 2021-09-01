package com.company;

public class AfisareThread extends Thread {

	private GameOfLife window;
	private int i;
	private Universe universe;
	private int size;
	private int speed  = 50;
	
	@Override
	public void run() {
		
		
		universe = new Universe(size, true);
		universe.expandUniverse();
		Universe nextUniverse;
		
		
		i=1;
		while(true) {	
			
			window.setUniverse(universe);
			window.afisareGeneratii(i);	
			window.afisareAlive(universe.numarareCelule());
			
			/*
			System.out.println("Generation: #" + (i));
			System.out.println("Alive: " + universe.numarareCelule());
			universe.afisare();
			System.out.println();
			*/
			
			nextUniverse = universe.generation();
			universe = nextUniverse.copyArray();
			universe.expandUniverse();
			
			try {
				Thread.sleep((100-speed) * 20);
			}
			catch (Exception e) {
			}
			
			i++;
		}
	}
	public void setWindow(GameOfLife window) {
		this.window = window;
	}

	public void set(Universe u, int i) {
		universe = u;
		this.i = i;
	}
	
	public void setSize(int size) {
		if(size == 0)
			return;
		this.size = size;
		universe = new Universe(size, true);
		i = 0;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
