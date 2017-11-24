/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.awt.Color;
import javax.swing.JButton;
import java.util.ArrayList;
import java.awt.Insets;

/**
 *
 * @author Saif
 */

public class Node extends JButton
{
    static class Comparator implements java.util.Comparator<Node>
    {
        @Override
        public int compare(Node n1, Node n2) { return Double.compare(n1.f, n2.f); }
    }
    
    ArrayList<GraphPanel.Connection> connections = new ArrayList<>();
    GraphPanel panel;
    
    //Transient varables
    Node p = null;
    double g = Double.NaN;
    double h = Double.NaN;
    double f = Double.NaN;
    
    public Node(GraphPanel panel)
    {
        setMargin(new Insets(0, 0, 0, 0));
        this.panel = panel;
        
        //setText(String.valueOf(panel.nodes.size()));
        panel.nodes.add(this);
    }

    Node getAdjFromConn(GraphPanel.Connection c)
    {
        if(c.n1 == this)
            return c.n2;
        else
            return c.n1;
    }
}
