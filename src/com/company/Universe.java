package com.company;

import java.util.Random;

public class Universe {

	private int size;
	
	private boolean [][] universe;

	public Universe(int size, int seed) {
		super();
		this.size = size;

		Random random = new Random(seed);

		universe = new boolean[size + 2][size + 2];

		for (int i = 1; i < size + 1; i++) {
			for (int j = 1; j < size + 1; j++) {
				universe[i][j] = random.nextBoolean();
			}
		}
	}
	
	public Universe(int size, boolean b) {
		super();
		this.size = size;
		
		universe = new boolean[size+2][size+2];
		
		if(b) {
			Random random = new Random();
			for (int i = 1; i < size + 1; i++) {
				for (int j = 1; j < size + 1; j++) {
					universe[i][j] = random.nextBoolean();
				}
			}
			
		}
	}

	public void expandUniverse() {

		for (int i = 1; i < size + 1; i++) {
			universe[0][i] = universe[size][i];
			universe[size + 1][i] = universe[1][i];
			universe[i][0] = universe[i][size];
			universe[i][size + 1] = universe[i][1];
		}

		universe[0][0] = universe[size][size];
		universe[0][size + 1] = universe[size][1];
		universe[size + 1][0] = universe[1][size];
		universe[size + 1][size + 1] = universe[1][1];

	}
	
	public Universe generation() {

		int count;
		count =0;
		Universe nextUniverse = new Universe(size, false);

		for (int i = 1; i < size + 1; i++) {
			for (int j = 1; j < size + 1; j++) {
				count = 0;
				
				if (universe[i - 1][j - 1] == true)
					count++;
				if (universe[i - 1][j] == true)
					count++;
				if (universe[i - 1][j + 1] == true)
					count++;
				if (universe[i][j - 1] == true)
					count++;
				if (universe[i][j + 1] == true)
					count++;
				if (universe[i + 1][j - 1] == true)
					count++;
				if (universe[i + 1][j] == true)
					count++;
				if (universe[i + 1][j + 1] == true)
					count++;
				
				if(universe[i][j] == true ) {
					if (count == 2 || count == 3) {
						nextUniverse.set(i, j, true);
						
					}
					else
						nextUniverse.set(i, j, false);
					
				} else {
					if (count == 3) {
						nextUniverse.set(i, j, true);
						
					}
					else 
						nextUniverse.set(i, j, false);
					
				}
				
			}
			
		}
		
		return nextUniverse;

	}
	
	public Universe copyArray() {
		Universe nextUniverse = new Universe(size, false);
		
		for (int i = 1; i < size+1; i++) {
			for (int j = 1; j < size+1; j++) {
				nextUniverse.set(i, j, universe[i][j]);;
			}
		}
		return nextUniverse;
	}
	
	public boolean get(int i, int j)
	{
		return universe[i][j];
	}
	
	public void set(int i, int j, boolean b) {
		universe[i][j] = b;
	}
	
	public void afisare()
	{
		for (int i = 1; i < size+1; i++) {
			for (int j = 1; j < size+1; j++) {
				System.out.print(universe[i][j] ? "O" : " ");
			}
			System.out.println();
		}
	}
	
	public void afisareComplet() {
		for(int i=0; i < universe.length ;i++)
		{
			for (int j = 0; j < universe.length; j++) {
				System.out.print(universe[i][j] ? "0" : "X");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public int numarareCelule() {
		int count =0;
		for(int i =0 ; i < size+1; i++){
			for(int j=1; j < size+1; j++) {
				if(universe[i][j])
					count++;
			}
		}
		return count;
	}
	
	public int getSize() {
		return size;
	}

	
}
