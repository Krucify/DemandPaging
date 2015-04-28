package com.main;

import java.util.Scanner;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args)
	{		
		int numProc = -1;
		String input = "";
		// Ask for Inputs
		boolean isValid = false;
		System.out.print("This is Demand Paging. Please enter number of processes: ");
		Scanner sc = new Scanner(System.in);
		while(numProc < 0){
			while (!isNumeric(input = sc.nextLine())) {
				System.err.print("Enter a valid integer: ");
			}
			
			numProc = Integer.parseInt(input);
			if(numProc < 0)
				System.err.print("Enter a non-negative integer: ");
		}
		GraphicController graphController = new GraphicController(numProc);
		ProcessController procController = new ProcessController(graphController, numProc);
		try {
			procController.work();
		} catch (InterruptedException e) {
			System.err.println("FATALITY: error in delay.");
			e.printStackTrace();
		}
	}
	
	public static boolean isNumeric(String str) {
		try {
			Integer i = Integer.parseInt(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
