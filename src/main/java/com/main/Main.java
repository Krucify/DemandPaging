package com.main;

public class Main {
	public static void main(String[] args)
	{
		
		GraphicController graphController = new GraphicController();
		ProcessController procController = new ProcessController(graphController);
	}
}
