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

public class GUIFrame extends JFrame
{
    public static final int FRAME_HEIGHT = AStar.GRAPH_HEIGHT+50;
    
    GraphPanel graphPanel = AStar.createGraph();
    JPanel controlPanel = new JPanel();
    JPanel mainPanel = new JPanel();
    JButton runBtn = new JButton("Run");
    JButton enableAll = new JButton("Enable all nodes");
    JCheckBox stepByStepCB = new JCheckBox("Step by step");
    
    public GUIFrame()
    {
        super("A* Search Demonstration");
        setSize(AStar.GRAPH_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        graphPanel.setSize(AStar.GRAPH_WIDTH, AStar.GRAPH_HEIGHT);
        
        controlPanel.setSize(AStar.GRAPH_WIDTH, 10);
        controlPanel.setMinimumSize(new Dimension(AStar.GRAPH_WIDTH, 0));
        controlPanel.setMaximumSize(new Dimension(AStar.GRAPH_WIDTH, 0));
        
        runBtn.addActionListener(e->graphPanel.aStarSearch());
        stepByStepCB.addActionListener(e->graphPanel.setStepByStep(stepByStepCB.isSelected()));
        enableAll.addActionListener(e->graphPanel.enableAllNodes());
        
        controlPanel.add(runBtn);
        controlPanel.add(stepByStepCB);
        controlPanel.add(enableAll);
        
        mainPanel.add(controlPanel);
        mainPanel.add(graphPanel);
        add(mainPanel);
        
        setVisible(true);
    }
}



