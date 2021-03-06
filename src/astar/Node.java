/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.util.ArrayList;
import java.awt.Insets;

/**
 *
 * @author Saif
 */

public class Node extends JButton
{
    //To compare Node using its f-Value
    public static class Comparator implements java.util.Comparator<Node>
    {
        @Override
        public int compare(Node n1, Node n2) { return Double.compare(n1.f, n2.f); }
    }
    
    ArrayList<GraphPanel.Connection> connections = new ArrayList<>(); //Adjacency List
    boolean traversable = true;
    
    //Transient varables
    Node p = null;
    double g = Double.POSITIVE_INFINITY;
    double h = Double.POSITIVE_INFINITY;
    double f = Double.POSITIVE_INFINITY;
    
    public Node(GraphPanel panel)
    {
        setFont(new Font(getFont().getFontName(), Font.PLAIN, 10));
        setMargin(new Insets(0, 0, 0, 0));
        
        panel.nodes.add(this);
        
        //Add click listener to enable/disable the node
        addActionListener(e->toggleTraversable());
    }
    
    public void toggleTraversable()
    {
        setTraversable(!traversable);
    }
    public void setTraversable(boolean v)
    {
        traversable = v;
        setTraversableBackground();
    }
    
    private void setTraversableBackground()
    {
        if(traversable)
            setBackground(null);
        else
            setBackground(Color.BLACK);
    }

    public Node getSuccessorFromConn(GraphPanel.Connection c)
    {
        if(c.n1 == this)
            return c.n2;
        else
            return c.n1;
    }
    
    public void reset()
    {
        p = null;
        g = Double.POSITIVE_INFINITY;
        h = Double.POSITIVE_INFINITY;
        f = Double.POSITIVE_INFINITY;
        
        setTraversableBackground();
        setText(null);
    }
}
