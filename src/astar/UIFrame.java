/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Saif
 */

public class UIFrame extends JFrame
{
    public static final int FRAME_WIDTH = 500;
    public static final int FRAME_HEIGHT = 500;
    
    CustomGraph panel = new CustomGraph();
    
    public UIFrame()
    {
        super("A* Search Demonstration");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel);
        setVisible(true);
        
        panel.aStarSearch();
    }
}



