package com.main;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


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
			javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JSlider delaySlider;
	private JTextPane processInfo;
	private JPanel processPanel;

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
			{
				delaySlider = new JSlider();
				getContentPane().add(delaySlider, BorderLayout.CENTER);
				delaySlider.setPreferredSize(new java.awt.Dimension(384, 131));
				//delaySlider.setSize(250, 150);
			}
			{
				processPanel = new JPanel();
				CardLayout processPanelLayout = new CardLayout();
				processPanel.setLayout(processPanelLayout);
				getContentPane().add(processPanel, BorderLayout.NORTH);
				processPanel.setPreferredSize(new java.awt.Dimension(384, 174));
				processPanel.setBackground(new java.awt.Color(128,255,0));
				{
					processInfo = new JTextPane();
					processPanel.add(processInfo, "jTextPane1");
					processInfo.setPreferredSize(new java.awt.Dimension(250, 106));
					processInfo.setBackground(new java.awt.Color(201,254,188));
				}
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	public int getDelaySlider() {
		return delaySlider.getValue();
	}
	
	
	
	public void setProcessInfo(String info) {
		processInfo.setText(info);
	}

}
