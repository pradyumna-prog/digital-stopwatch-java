package Stopwatch;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class DigitalStopwatch extends Applet implements Runnable, ActionListener{
	String time;
	Label display;
	Panel panel;
	int hour,minute,second,milisecond;
	Button start, stop, reset;
	boolean on;
	public void init(){
		on = false;
		
		panel = new Panel();
		panel.setLayout(new GridLayout(4,1,6,10));
		
		hour = minute = second = milisecond = 0;
		
		display = new Label();
		time = "00:00:00:00";
		display.setText(time);
		panel.add(display);
		
		start = new Button("Start");
		start.addActionListener(this);
		panel.add(start);
		
		stop = new Button("Stop");
		stop.addActionListener(this);
		panel.add(stop);
		
		reset = new Button("Reset");
		reset.addActionListener(this);
		panel.add(reset);
		
		add(panel);
		
		new Thread(this).start();	
	}
	public void reset() {
		try {
			Thread.sleep(1);
		}catch(Exception e) {
			System.out.println(e);
		}
		hour = minute = second = milisecond = 0;
	}
	public void update() {
		milisecond++;
		if(milisecond == 1000) {
			milisecond = 0;
			second++;
			if(second == 60) {
				second = 0;
				minute++;
				if(minute == 60) {
					minute = 0;
					hour++;
				}
			}
		}
	}
	public void changeLabel() {

        // Properly formatting the display of the timer 
        if (hour < 10) 
            time = "0" + hour + " : "; 
        else
        	time = hour + " : "; 
  
        if (minute < 10) 
        	time += "0" + minute + " : "; 
        else
        	time += minute + " : "; 
  
        if (second < 10) 
        	time += "0" + second + " : "; 
        else
        	time += second + " : "; 
  
        if (milisecond < 10) 
        	time += "00" + milisecond; 
        else if (milisecond < 100) 
        	time += "0" + milisecond; 
        else
        	time += milisecond; 
        
        display.setText(time);
	}
	public void run() {
		while(on) {
		try {
			Thread.sleep(1);
			update();
			changeLabel();
		}catch(Exception e) {
			System.out.println(e);
		}
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start) {
			on = true;
			new Thread(this).start();
		}
		if(e.getSource() == stop) {
			on = false;
		}
		if(e.getSource() == reset) {
			on = false;
			reset();
			changeLabel();
		}
	}
}
/*
<applet code = "DigitalStopwatch">
</applet>
*/