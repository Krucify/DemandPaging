package com.main;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
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
	private JScrollPane jScrollPane1;
	private JLabel pageTblLabel;
	private JLabel processLabel;
	private JLabel virtMemLabel;
	private JLabel victimLabel;
	private JLabel mainMemLabel;
	private JTable pageTable;
	private JScrollPane virtMemScrollPane;
	private JTable virtualMemory;
	private JTable mainMemory;
	private JTable TLB;
	private JDesktopPane backdropPane;
	private JTextPane processInfo;
	private JTable victimQueueTable;
	private JTextArea consoleArea;
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
					mainMemory.setBounds(593, 32, 75, 480);
					mainMemory.setToolTipText("This is the Main Memory");
					mainMemory.setEnabled(false);
				}
				{
					// Virtual Memory Table
					virtMemScrollPane = new JScrollPane();
					backdropPane.add(virtMemScrollPane, JLayeredPane.DEFAULT_LAYER);
					virtMemScrollPane.setBounds(58, 33, 100, 480);
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
					victimQueueTable.setBounds(696, 32, 75, 480);
					victimQueueTable.setEnabled(false);
				}
				{
					// Panel that holds Process and Reference and TLB
					CPUContainer = new JPanel();
					backdropPane.add(CPUContainer, JLayeredPane.DEFAULT_LAYER);
					CPUContainer.setBounds(274, 23, 228, 119);
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
					pageTable.setRowHeight(32);
					pageTable.setBounds(230, 169, 320, 160);
					pageTable.setBorder(BorderFactory.createTitledBorder(""));
					pageTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					pageTable.setToolTipText("This is the Page Table");
					pageTable.setEnabled(false);
				}
				{
					mainMemLabel = new JLabel();
					backdropPane.add(mainMemLabel, JLayeredPane.DEFAULT_LAYER);
					mainMemLabel.setText("Main Memory");
					mainMemLabel.setBounds(593, 10, 75, 16);
				}
				{
					victimLabel = new JLabel();
					backdropPane.add(victimLabel, JLayeredPane.DEFAULT_LAYER);
					victimLabel.setText("Victim Queue");
					victimLabel.setBounds(696, 9, 79, 18);
				}
				{
					virtMemLabel = new JLabel();
					backdropPane.add(virtMemLabel, JLayeredPane.DEFAULT_LAYER);
					virtMemLabel.setText("Virtual Memory");
					virtMemLabel.setBounds(64, 10, 83, 21);
				}
				{
					processLabel = new JLabel();
					backdropPane.add(processLabel, JLayeredPane.DEFAULT_LAYER);
					processLabel.setText("Processes, References, and the TLB");
					processLabel.setBounds(292, 10, 189, 12);
				}
				{
					pageTblLabel = new JLabel();
					backdropPane.add(pageTblLabel, JLayeredPane.DEFAULT_LAYER);
					pageTblLabel.setText("The Page Table");
					pageTblLabel.setBounds(345, 148, 94, 18);
				}
				{
					jScrollPane1 = new JScrollPane();
					backdropPane.add(jScrollPane1, JLayeredPane.DEFAULT_LAYER);
					jScrollPane1.setBounds(230, 341, 320, 180);
					{
						consoleArea = new JTextArea();
						jScrollPane1.setViewportView(consoleArea);
						DefaultCaret caret = (DefaultCaret)consoleArea.getCaret();
						caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
						consoleArea.setText("This is Demand Paging!\n");
						consoleArea.setBounds(230, 341, 320, 180);
					}
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
				if (table[i][j]!=null && table[i][j].isValid()) {
					//pageTable.setValueAt("v", i, j);
					pageTable.setValueAt(table[i][j].getMemIndex() + "-v", i, j);
					Color color = getTableCellBackground(pageTable, i, j);
					pageTable.getColumnModel().getColumn(j).setCellRenderer(new CustomRenderer());
					
				}
				else if (table[i][j]!=null && !table[i][j].isValid()) {
					//pageTable.setValueAt("i", i, j);
					pageTable.setValueAt(table[i][j].getMemIndex() + "-i", i, j);
					Color color = getTableCellBackground(pageTable, i, j);
					pageTable.getColumnModel().getColumn(j).setCellRenderer(new CustomRenderer());
					
				}
			}
		}
	}
	
	
	public void updateTLB(Page[] pages) {
		for (int i=0; i<5; i++) {
			if (pages[i]!=null && pages[i].isValid()) {
				TLB.setValueAt(pages[i].getMemIndex(), i, 0);
			}
			else if (pages[i]!=null && !pages[i].isValid()) {
				TLB.setValueAt(pages[i].getMemIndex(), i, 0);
			}
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
			if (queue[i]!=null && queue[i].isDirty()) {
				victimQueueTable.setValueAt(queue[i].getMemIndex() + "-d", i, 0);
				victimQueueTable.getColumnModel().getColumn(0).setCellRenderer(new CustomRenderer());
			}
			else if (queue[i]!=null && !queue[i].isDirty()) {
				victimQueueTable.setValueAt(queue[i].getMemIndex() + "-c", i, 0);
				victimQueueTable.getColumnModel().getColumn(0).setCellRenderer(new CustomRenderer());
				
			}
	}
	
	private void updateConsole(String line) {
		//consoleArea.getDocument().insertString(consoleArea.getDocument().getLength(), line + "\n", null);
		consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
		consoleArea.append(line + "\n");
	}
	
	public Color getTableCellBackground(JTable table, int row, int col) {
	    TableCellRenderer renderer = table.getCellRenderer(row, col);
	    Component component = table.prepareRenderer(renderer, row, col);
	    return component.getBackground();
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
	
	public void invokeUpdateConsole(final String line) {
	    SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            updateConsole(line);
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

    public CustomRenderer() {

	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (value==null)
        	cellComponent.setBackground(Color.WHITE);
        else if (value.toString().contains("v"))
        	cellComponent.setBackground(Color.GREEN);
        else if (value.toString().contains("i"))
        	cellComponent.setBackground(Color.RED);
        else if (value.toString().contains("d"))
        	cellComponent.setBackground(Color.YELLOW);
        else if (value.toString().contains("c"))
        	cellComponent.setBackground(Color.CYAN);

        return cellComponent;
    }
}
