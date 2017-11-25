/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

/**
 *
 * @author Saif
 */
public class CustomGraph extends GraphPanel
{
    public CustomGraph()
    {
        setLayout(null);
        Insets insets = getInsets();
        
        Node n;
        start = n = new Node(this);
        n.setBounds(5+insets.left, 5+insets.top, NODE_WIDTH, NODE_WIDTH);
        add(n);
        
        n = new Node(this);
        n.setBounds(100+insets.left, 60+insets.top, NODE_WIDTH, NODE_WIDTH);
        add(n);
        
        addConnection(nodes.get(0), nodes.get(1), 130);
        
        n = new Node(this);
        n.setBounds(40+insets.left, 120+insets.top, NODE_WIDTH, NODE_WIDTH);
        add(n);
        
        addConnection(nodes.get(0), nodes.get(2), 135);
        addConnection(nodes.get(1), nodes.get(2), 95);
        
        n = new Node(this);
        n.setBounds(240+insets.left, 110+insets.top, NODE_WIDTH, NODE_WIDTH);
        add(n);
        
        addConnection(nodes.get(1), nodes.get(3), 135);
        
        n = new Node(this);
        n.setBounds(100+insets.left, 200+insets.top, NODE_WIDTH, NODE_WIDTH);
        add(n);
        
        addConnection(nodes.get(2), nodes.get(4), 105);
        
        n = new Node(this);
        n.setBounds(140+insets.left, 360+insets.top, NODE_WIDTH, NODE_WIDTH);
        add(n);
        
        addConnection(nodes.get(4), nodes.get(5), 170);
        addConnection(nodes.get(3), nodes.get(5), 200);
        
        n = new Node(this);
        n.setBounds(95+insets.left, 5+insets.top, NODE_WIDTH, NODE_WIDTH);
        add(n);
        
        addConnection(nodes.get(0), nodes.get(6), 70);
        
        n = new Node(this);
        n.setBounds(240+insets.left, 50+insets.top, NODE_WIDTH, NODE_WIDTH);
        add(n);
        
        addConnection(nodes.get(6), nodes.get(7), 90);

        end = n = new Node(this);
        n.setBounds(insets.left+450, insets.top+420, NODE_WIDTH, NODE_WIDTH);
        add(n);
        
        n = new Node(this);
        n.setBounds(insets.left+450, insets.top+220, NODE_WIDTH, NODE_WIDTH);
        add(n);
        
        addConnection(nodes.get(7), nodes.get(9), 250);
        
        n = new Node(this);
        n.setBounds(insets.left+350, insets.top+220, NODE_WIDTH, NODE_WIDTH);
        add(n);
        addConnection(nodes.get(3), nodes.get(10), 150);
        addConnection(nodes.get(5), nodes.get(10), 250);
        
        n = new Node(this);
        n.setBounds(insets.left+350, insets.top+320, NODE_WIDTH, NODE_WIDTH);
        add(n);
        
        addConnection(nodes.get(10), nodes.get(11), 110);
        
        n = new Node(this);
        n.setBounds(insets.left+270, insets.top+365, NODE_WIDTH, NODE_WIDTH);
        add(n);
        
        addConnection(nodes.get(5), nodes.get(12), 130);
        addConnection(nodes.get(11), nodes.get(8), 150);
        addConnection(nodes.get(12), nodes.get(8), 190);
        
        n = new Node(this);
        n.setBounds(insets.left+5, insets.top+420, NODE_WIDTH, NODE_WIDTH);
        add(n);
        
        addConnection(nodes.get(0), nodes.get(13), 415);
        addConnection(nodes.get(13), nodes.get(8), 455);
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Font f = new Font("Arial", Font.PLAIN, 10);
        g.setFont(f);
        
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        for(Connection c : connections)
        {
            int x1 = c.n1.getBounds().x + c.n1.getBounds().width/2;
            int y1 = c.n1.getBounds().y + c.n1.getBounds().height/2;
            int x2 = c.n2.getBounds().x + c.n2.getBounds().width/2;
            int y2 = c.n2.getBounds().y + c.n2.getBounds().height/2;
            
            g2.drawLine(x1, y1, x2, y2);
            
            int centerX = x1 + ((x2-x1)/2);
            int centerY = y1 + ((y2-y1)/2);

            //get the angle in degrees
            double deg = Math.toDegrees(Math.atan2(centerY - y2, centerX - x2)+ Math.PI);
            //need this in order to flip the text to be more readable within angles 90<deg<270
            if(deg>90 && deg<270)
                deg += 180;
            double angle = Math.toRadians(deg);

            double euclideanDist = heuristicDistance(x1, y1, x2, y2);
            String text = String.format("w%.0f,d%.0f", c.weight, euclideanDist);
            FontMetrics fm = g2.getFontMetrics(f);
            int sw =  fm.stringWidth(text);

            g2.rotate(angle, centerX, centerY);
            g2.drawString(text, centerX - (sw/2), centerY - 2);
            g2.rotate(-angle, centerX, centerY);
        }
        g2.dispose();
    }
}
