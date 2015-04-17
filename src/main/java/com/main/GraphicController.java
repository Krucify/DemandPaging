package com.main;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
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
									new String[] { "Main"});
					mainMemory = new JTable();
					
					backdropPane.add(mainMemory, JLayeredPane.DEFAULT_LAYER);
					mainMemory.setModel(mainMemoryModel);
					mainMemory.setRowHeight(16);
					mainMemory.setBounds(614, 25, 75, 480);
					mainMemory.setToolTipText("This is the Main Memory");
					mainMemory.setEnabled(false);
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
						virtualMemory.setEnabled(false);
					}
				}
				{
					// Victim Queue Table
					String[][] rows = new String[30][];
					TableModel victimQueueTableModel = 
							new DefaultTableModel(
									rows,
									new String[] { "Victims"});
					victimQueueTable = new JTable();
					backdropPane.add(victimQueueTable, JLayeredPane.DEFAULT_LAYER);
					victimQueueTable.setRowHeight(16);
					victimQueueTable.setModel(victimQueueTableModel);
					victimQueueTable.setBounds(701, 25, 75, 480);
					victimQueueTable.setEnabled(false);
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
						processInfo.setEditable(false);
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
						referencePanel.setEditable(false);
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
						TLB.setEnabled(false);
					}
				}
				{
					// Page Table Table
					pageTable = new JTable();
					String[] names = new String[10];
					Integer[][] values = new Integer[5][10];
					TableModel model = new DefaultTableModel(values, names);
					pageTable.setModel(model);
					backdropPane.add(pageTable, JLayeredPane.DEFAULT_LAYER);
					pageTable.setBounds(317, 58, 163, 175);
					pageTable.setBorder(BorderFactory.createTitledBorder(""));
					pageTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					pageTable.setToolTipText("This is the Page Table");
					pageTable.setEnabled(false);
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
		
		for (int i=0; i<table.length; i++) {
			for (int j=0; j<table[i].length; j++) {
				//try {
					if (table[i][j]!=null && table[i][j].isValid()) {
						pageTable.setValueAt(table[i][j].getMemIndex(), i, j);
						pageTable.getColumnModel().getColumn(j).setCellRenderer(new CustomRenderer(true, i));
					}
					else if (table[i][j]!=null && !table[i][j].isValid()) {
						pageTable.setValueAt(table[i][j].getMemIndex(), i, j);
						pageTable.getColumnModel().getColumn(j).setCellRenderer(new CustomRenderer(false, i));
					}
		/*		} catch (Exception e) {
					//Space in table not taken yet
				}*/
					//pageTable.getColumnModel().getColumn(0).setCellRenderer(new CustomRenderer(true, i));
			}
			
		}
	}
	
	
	public void updateTLB(Page[] pages) {
		for (int i=0; i<5; i++) 
			if (pages[i]!=null && pages[i].isValid()) {
				TLB.setValueAt(pages[i].getMemIndex(), i, 0);
				TLB.getColumnModel().getColumn(0).setCellRenderer(new CustomRenderer(true, i));
			}
			else if (pages[i]!=null && !pages[i].isValid()) {
				TLB.setValueAt(pages[i].getMemIndex(), i, 0);
				TLB.getColumnModel().getColumn(0).setCellRenderer(new CustomRenderer(false, i));
			}
		/*try {
			TLB.setValueAt(page, row, 0);	
		} catch (Exception e) {
			//Space in table not taken yet
		}*/
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
			if (queue[i]!=null && queue[i].isValid()) {
				victimQueueTable.setValueAt(queue[i].getMemIndex(), i, 0);
				victimQueueTable.getColumnModel().getColumn(0).setCellRenderer(new CustomRenderer(true, i));
			}
			else if (queue[i]!=null && !queue[i].isValid()) {
				victimQueueTable.setValueAt(queue[i].getMemIndex(), i, 0);
				victimQueueTable.getColumnModel().getColumn(0).setCellRenderer(new CustomRenderer(false, i));
			}
	}
	
	/*
	 * Invoke Methods
	 */
	public void invokeUpdateTLB(final Page[] pages) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            updateTLB(pages);
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

/**
 * Custom Class that colors cells based on content
 * @author Jessel
 *
 */
class CustomRenderer extends DefaultTableCellRenderer {
	//private static final long serialVersionUID = 6703872492730589499L;
	private boolean isValid;
	private int cellRow;

    public CustomRenderer(boolean isValid, int row) {
		this.isValid = isValid;
		this.cellRow = row;
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

       // if(table.getValueAt(row, column))

        if(isValid && cellRow==row){
            cellComponent.setBackground(Color.GREEN.brighter());
        } else if (!isValid && cellRow==row) {
            cellComponent.setBackground(Color.RED);
        } else if (value==null)
        	cellComponent.setBackground(Color.WHITE);
        else
        	cellComponent.setBackground(Color.GREEN);
        return cellComponent;
    }
}

/*class PageTableModel extends AbstractTableModel {

	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}*/
