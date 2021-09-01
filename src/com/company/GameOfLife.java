package com.company;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;


@SuppressWarnings("serial")
public class GameOfLife extends JFrame {
	
	private JLabel generatii;
	private JLabel alive;
	private JPanel panel;
	private Universe u;
	private DrawLines d;
	private AfisareThread thread;
	private int indexCuloare =0;
	private final Color [] culori = {Color.BLACK, Color.BLUE, Color.DARK_GRAY, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.RED, Color.YELLOW, new Color(0, 137, 255)};  
	
	public GameOfLife(){
        super("Game of life");
        setSize(648, 717);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLayout(null);

        generatii = new JLabel();
        generatii.setName("GenerationLabel");
        generatii.setText("Generation #0");
        //generatii.setBounds(0, 0, 50, 50);

        alive = new JLabel();
        alive.setName("AliveLabel");
        alive.setText("Alive: 0");
        //alive.setBounds(0, 50, 50, 50);

        //panel-ul cu textul
        panel = new JPanel();
        panel.add(generatii);
        panel.add(alive);


        adaugareButoane();
        
        JPanel panel1 = new JPanel();
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 99, 50);
        
        slider.addChangeListener((e) -> thread.setSpeed(slider.getValue()));
        
        panel1.add(new JLabel("Speed"));
        panel1.add(slider);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        add(panel);
        add(panel1);
        
        d = new DrawLines();
        add(d);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        

    }
	
	private void adaugareButoane() {
		
		JButton butonPause = new JButton("Pause");
        butonPause.setName("PlayToggleButton");
        butonPause.addActionListener(new ActionListener() {

            @SuppressWarnings("deprecation")
			@Override
            public void actionPerformed(ActionEvent e) {
                if(butonPause.getText().equals("Resume")) {
                    thread.resume();
                    butonPause.setText("Pause");
                }
                else {
                    thread.suspend();
                    butonPause.setText("Resume");
                }
            }
        });
        //butonPause.setBounds(0, 100, 50, 50);
        
        panel.add(butonPause);

        JButton butonReset = new JButton("Reset");
        butonReset.setName("ResetButton");
        butonReset.addActionListener(new ActionListener() {

        	@SuppressWarnings("deprecation")
			@Override
            public void actionPerformed(ActionEvent e) {
                thread.set(new Universe(u.getSize(), true), 0);
                if(butonPause.getText().equals("Resume")) {
                    thread.resume();
                    butonPause.setText("Pause");
                }

            }
        });
        //butonReset.setBounds(0, 150, 50, 50);

        panel.add(butonReset);
        
        JButton butonMarire = new JButton();
        butonMarire.setText("Increase size");
        butonMarire.addActionListener((e) -> thread.setSize(u.getSize() + 1));
        panel.add(butonMarire);
        
        JButton butonMicsorare = new JButton();
        butonMicsorare.setText("Decrease size");
        butonMicsorare.addActionListener((e) -> thread.setSize(u.getSize() - 1));
		panel.add(butonMicsorare);
		
		
		JButton butonCuloare = new JButton();
		butonCuloare.setText("Change color");
		butonCuloare.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(indexCuloare < culori.length-1)
					indexCuloare++;
				else
					indexCuloare =0;
			}
		});
		panel.add(butonCuloare);
	}
	
	
	public void afisareGeneratii(int g) {
		generatii.setText("Generation #" + g);
	}
	
	public void afisareAlive(int a) {
		alive.setText("Alive: " + a);
	}
	
	public void setUniverse(Universe u) {
		remove(d);
		this.u = u;
		d = new DrawLines();
		add(d);
	}
	
	private class DrawLines extends JComponent
	{
		
		public void paint(Graphics g) {
			int latime = 620/u.getSize();
			int inaltime = latime*u.getSize();
			
			
			Graphics2D graph = (Graphics2D)g;
			if(u != null) {
				for(int i=0; i < u.getSize() ; i++){
					for(int j =0 ;j < u.getSize() ;j++) {
						
						if(u.get(i+1, j+1)) {
							graph.setColor(culori[indexCuloare]);
						}
						else {
							graph.setColor(Color.WHITE);
						}
						graph.fillRect(j*latime, i*latime, latime, latime);
						
					}
				}
			}
			graph.setColor(Color.BLACK);
			
			for(int i=0; i<u.getSize()+1 ;i++) {
				graph.drawLine(i*latime, 0, i*latime, inaltime);
			}
			
			for(int i=0; i<u.getSize()+1 ;i++) {
				graph.drawLine(0, i*latime, inaltime, i*latime);
			}
		}
	}
	
	public void setThread(AfisareThread t) {
		thread = t;
	}
}
