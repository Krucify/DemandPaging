package com.main;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.SwingUtilities;

import com.utility.Frame;
import com.utility.Page;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class GraphicController extends javax.swing.JFrame {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JSlider delaySlider;
	private JTable pageTable;
	private JScrollPane virtMemScrollPane;
	private JTable virtualMemory;
	private JTable mainMemory;
	private JTable TLB;
	private JDesktopPane backdropPane;
	private JTextPane processInfo;
	private JTable victimQueueTable;
	private JTextPane consolePane;
	private JPanel CPUContainer;
	private JTextPane referencePanel;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public  void start() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GraphicController inst = new GraphicController();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public GraphicController() {
		super();
		initGUI();
		//start();
		
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			getContentPane().setBackground(new java.awt.Color(192,192,192));
			{
				// Delay Slider
				delaySlider = new JSlider();
				getContentPane().add(delaySlider, BorderLayout.SOUTH);
				delaySlider.setPreferredSize(new java.awt.Dimension(584, 37));
				delaySlider.setBorder(BorderFactory.createTitledBorder(""));
			}
			{
				// Backdrop
				backdropPane = new JDesktopPane();
				getContentPane().add(backdropPane, BorderLayout.CENTER);
				backdropPane.setBorder(BorderFactory.createTitledBorder(""));
				backdropPane.setBackground(new java.awt.Color(211,255,168));
				{
					// Main Memory Table
					String[][] rows = new String[30][];
					TableModel mainMemoryModel = 
							new DefaultTableModel(
									rows,
									new String[] { "Column 1"});
					mainMemory = new JTable();
					
					backdropPane.add(mainMemory, JLayeredPane.DEFAULT_LAYER);
					mainMemory.setModel(mainMemoryModel);
					mainMemory.setRowHeight(16);
					mainMemory.setBounds(614, 25, 75, 480);
					mainMemory.setToolTipText("This is the Main Memory");
				}
				{
					// Virtual Memory Table
					virtMemScrollPane = new JScrollPane();
					backdropPane.add(virtMemScrollPane, JLayeredPane.DEFAULT_LAYER);
					virtMemScrollPane.setBounds(498, 25, 100, 16*30);
					virtMemScrollPane.setToolTipText("Virtual Memory Frames");
					{
						String[][] rows = new String[500][];
						TableModel virtualMemoryModel = 
								new DefaultTableModel(
										rows,
										new String[] { "Virtual"});
						virtualMemory = new JTable();
						virtMemScrollPane.setViewportView(virtualMemory);
						virtualMemory.setModel(virtualMemoryModel);
						virtualMemory.setRowHeight(16);
						//virtualMemory.setBounds(100, 25, 75, 11*30);
						virtualMemory.setToolTipText("This is the Virtual Memory");
					}
				}
				{
					// Victim Queue Table
					String[][] rows = new String[30][];
					TableModel victimQueueTableModel = 
							new DefaultTableModel(
									rows,
									new String[] { "Column 1"});
					victimQueueTable = new JTable();
					backdropPane.add(victimQueueTable, JLayeredPane.DEFAULT_LAYER);
					victimQueueTable.setRowHeight(16);
					victimQueueTable.setModel(victimQueueTableModel);
					victimQueueTable.setBounds(701, 25, 75, 480);
				}
				{
					// Panel that holds Process and Reference and TLB
					CPUContainer = new JPanel();
					backdropPane.add(CPUContainer, JLayeredPane.DEFAULT_LAYER);
					CPUContainer.setBounds(13, 25, 228, 181);
					CPUContainer.setBackground(new java.awt.Color(233,254,226));
					{
						// Process text box display
						processInfo = new JTextPane();
						CPUContainer.add(processInfo);
						processInfo.setBackground(new java.awt.Color(201,254,188));
						processInfo.setBorder(BorderFactory.createTitledBorder(""));
						processInfo.setFont(Font.getFont(Font.SANS_SERIF));
						processInfo.setToolTipText("The current process in use.");
						processInfo.setBounds(13, 25, 72, 18);
						processInfo.setPreferredSize(new java.awt.Dimension(89, 18));
						processInfo.setText("process");
					}
					{
						// Reference text box display
						referencePanel = new JTextPane();
						CPUContainer.add(referencePanel);
						//referencePanel.setText(info);
						referencePanel.setBackground(new java.awt.Color(201,254,188));
						referencePanel.setToolTipText("The current process in use.");
						referencePanel.setBorder(BorderFactory.createTitledBorder(""));
						referencePanel.setBounds(13, 25, 72, 18);
						referencePanel.setPreferredSize(new java.awt.Dimension(94, 18));
						referencePanel.setText("reference");
					}
					{
						// TLB Table
						TLB = new JTable();
						CPUContainer.add(TLB);
						String[] names = new String[1];
						DefaultTableModel model = new DefaultTableModel(names, 5);
						TLB.setModel(model);
						TLB.setBounds(288, 232, 109, 80);
						TLB.setToolTipText("This is the Translation Look-Aside Buffer.");
					}
				}
				{
					// Page Table Table
					pageTable = new JTable();
					backdropPane.add(pageTable, JLayeredPane.DEFAULT_LAYER);
					pageTable.setBounds(317, 58, 163, 175);
					pageTable.setBorder(BorderFactory.createTitledBorder(""));
					pageTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					pageTable.setToolTipText("This is the Page Table");
				}
				{
					// Console Output
					consolePane = new JTextPane();
					backdropPane.add(consolePane, JLayeredPane.DEFAULT_LAYER);
					consolePane.setText("console");
					consolePane.setBounds(317, 270, 148, 201);
				}
			}
			pack();
			setSize(800, 600);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getDelaySlider() {
		return delaySlider.getValue();
	}
	
	public void setProcessInfo(String info)	{
		processInfo.setText(info);
	}
	
	public void setReferencePane(String info) {
		referencePanel.setText(info);
		//referencePanel.set
	}
	
	/*
	 * Update Table Methods
	 */
	public void updatePageTable(Page[][] table) {
		String[] names = new String[table[0].length];
		Integer[][] values = new Integer[table.length][table[0].length];
		for (int i=0; i<table.length; i++) {
			for (int j=0; j<table[i].length; j++) {
				try {
					values[i][j] = table[i][j].getMemIndex();
					//pageTable.setValueAt(table[i][j].getMemIndex(), i, j);
				} catch (Exception e) {
					//Space in table not taken yet
				}
			}
		}
		
		TableModel model = new DefaultTableModel(values, names);
		pageTable.setModel(model);
	}
	
	
	public void updateTLB(int page, int row) {
		try {
			TLB.setValueAt(page, row, 0);	
		} catch (Exception e) {
			//Space in table not taken yet
		}
	}
	
	private void updateMainMemory(Frame[] main) {
		for (int i=0; i<main.length; i++) 
			if (main[i]!=null) 
				mainMemory.setValueAt(main[i].getValue(), i, 0);
	}
	
	private void updateVirtualMemory(Frame[] virt) {
	for (int i=0; i<virt.length; i++) 
		if (virt[i]!=null) 
			virtualMemory.setValueAt(virt[i].getValue(), i, 0);
	}
	
	private void updateVictimQueue(Page[] queue) {
		for (int i=0; i<queue.length; i++)
			if (queue[i]!=null)
				victimQueueTable.setValueAt(queue[i].getMemIndex(), i, 0);
	}
	
	/*
	 * Invoke Methods
	 */
	public void invokeUpdateTLB(final int page, final int row) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            updateTLB(page, row);
	        }
	    }); 
	}
	
	public void invokeUpdateMainMemory(final Frame[] main) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            updateMainMemory(main);
	        }
	    }); 
	}
	public void invokeUpdateVirtualMemory(final Frame[] virt) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            updateVirtualMemory(virt);
	        }
	    }); 
	}
	
	public void invokeUpdatePageTable(final Page[][] table) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            updatePageTable(table);
	        }
	    }); 
	}
	
	public void invokeUpdateVictimQueue(final Page[] queue) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            updateVictimQueue(queue);
	        }
	    }); 
	}
}
