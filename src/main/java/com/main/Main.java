package com.main;

public class Main {
	public static void main(String[] args)
	{
		
		GraphicController graphController = new GraphicController();
		ProcessController procController = new ProcessController(graphController);
		try {
			procController.work();
		} catch (InterruptedException e) {
			System.err.println("FATALITY: error in delay.");
			e.printStackTrace();
		}
	}
}
