package com.company;

public class Main {

	public static void main(String[] args) {
		
		GameOfLife window = new GameOfLife();
		
		AfisareThread t = new AfisareThread();
		t.setWindow(window);
		t.setSize(8);
		
		window.setThread(t);
		t.start();
		
	}

}
