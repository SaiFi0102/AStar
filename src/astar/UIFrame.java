/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import javax.swing.JFrame;

/**
 *
 * @author Saif
 */

public class UIFrame extends JFrame
{
    GraphPanel panel = new GraphPanel();
    
    public UIFrame()
    {
        super("UIFrame");
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel);    
        setVisible(true);
        
        panel.aStarSearch();
    }
}



