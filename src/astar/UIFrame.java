/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;

/**
 *
 * @author Saif
 */

public class UIFrame extends JFrame
{
    public static final int GRAPH_WIDTH = 500;
    public static final int GRAPH_HEIGHT = 500;
    public static final int FRAME_HEIGHT = GRAPH_HEIGHT+50;
    
    GraphPanel graphPanel = new GridGraph();
    JPanel controlPanel = new JPanel();
    JPanel mainPanel = new JPanel();
    JButton runBtn = new JButton("Run");
    JButton enableAll = new JButton("Enable all nodes");
    JCheckBox stepByStepCB = new JCheckBox("Step by step");
    
    public UIFrame()
    {
        super("A* Search Demonstration");
        setSize(GRAPH_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        graphPanel.setSize(GRAPH_WIDTH, GRAPH_HEIGHT);
        
        controlPanel.setSize(GRAPH_WIDTH, 10);
        controlPanel.setMinimumSize(new Dimension(GRAPH_WIDTH, 0));
        controlPanel.setMaximumSize(new Dimension(GRAPH_WIDTH, 0));
        
        runBtn.addActionListener(e->graphPanel.aStarSearch());
        stepByStepCB.addActionListener(e->graphPanel.setStepByStep(stepByStepCB.isSelected()));
        enableAll.addActionListener(e->graphPanel.enableAllNodes());
        
        controlPanel.add(runBtn);
        controlPanel.add(stepByStepCB);
        
        mainPanel.add(graphPanel);
        mainPanel.add(enableAll);
        mainPanel.add(controlPanel);
        
        add(mainPanel);
        setVisible(true);
    }
}



