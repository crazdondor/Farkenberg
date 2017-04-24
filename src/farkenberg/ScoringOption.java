package farkenberg;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class ScoringOption extends JButton {
	private static final long serialVersionUID = 7195395238158101950L;
	
	public final int points;
	public final String label;

	private boolean is_selected = false;

	private Hand future;

	public ScoringOption(int points, String label, Hand future_hand) {
		this.points = points;
		this.label = label;
		this.future = future_hand.copy();
		
    	setFocusPainted(false);
    	setRolloverEnabled(false);
    	setMargin(new Insets(5,5,5,5));
    	setBorder(new LineBorder(new Color(0,0,0,0), 5));
    	setOpaque(true);
    	setText(label);
    	
	}
	
	public void set_listener(Consumer<ScoringOption> listener) {
    	addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ScoringOption.this.set_selected(!is_selected);
				listener.accept(ScoringOption.this);
			}
    		
    	});
	}
	
	public Hand get_futureHand() {
		return future;
	}
	
	public boolean is_selected() {
		return is_selected;
	}
	
	public void set_selected(boolean state) {
		is_selected = state;
		
		if (is_selected) {
			setBorder(new LineBorder(Color.YELLOW, 5));
		} else {
			setBorder(new LineBorder(new Color(0,0,0,0), 5));
		}
	}
	
}
