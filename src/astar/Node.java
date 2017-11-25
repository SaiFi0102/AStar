/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import javax.swing.JButton;
import java.util.ArrayList;
import java.awt.Insets;

/**
 *
 * @author Saif
 */

public class Node extends JButton
{
    public static class Comparator implements java.util.Comparator<Node>
    {
        @Override
        public int compare(Node n1, Node n2) { return Double.compare(n1.f, n2.f); }
    }
    
    ArrayList<GraphPanel.Connection> connections = new ArrayList<>();
    
    //Transient varables
    Node p = null;
    double g = Double.POSITIVE_INFINITY;
    double h = Double.POSITIVE_INFINITY;
    double f = Double.POSITIVE_INFINITY;
    
    public Node(GraphPanel panel)
    {
        setMargin(new Insets(0, 0, 0, 0));
        //setText(String.valueOf(panel.nodes.size()));
        panel.nodes.add(this);
    }

    public Node getAdjFromConn(GraphPanel.Connection c)
    {
        if(c.n1 == this)
            return c.n2;
        else
            return c.n1;
    }
    
    public void resetTransientValues()
    {
        //A more efficient but less understandable way would be simply to reset
        //g-value which is compared before updating the transient values
        p = null;
        g = Double.POSITIVE_INFINITY;
        h = Double.POSITIVE_INFINITY;
        f = Double.POSITIVE_INFINITY;
    }
}
