package com.main;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args)
	{		
		GraphicController graphController = new GraphicController();
		
		// start JFrame
/*		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GraphicController graphController = new GraphicController();
				graphController.setLocationRelativeTo(null);
				graphController.setVisible(true);
			}
		});*/
		
		ProcessController procController = new ProcessController(graphController);
		try {
			procController.work();
		} catch (InterruptedException e) {
			System.err.println("FATALITY: error in delay.");
			e.printStackTrace();
		}
	}
}
