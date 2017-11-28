/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.awt.Color;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Saif
 */
public class GraphPanel extends JPanel
{
    public class Connection
    {
        Node n1, n2;
        double weight;
        public Connection(Node n1, Node n2, double w)
        {
            this.weight = w;
            this.n1 = n1;
            this.n2 = n2;
        }
    }
    
    final int NODE_WIDTH = 30;
    final int ANIMATION_SLEEP = 50;
    final int FINISHED_WAIT_SLEEP = 2000;
    final int POLL_STEP_SLEEP = 10;
    
    Node start = null;
    Node end = null;
    ArrayList<Node> nodes = new ArrayList<>();
    ArrayList<Connection> connections = new ArrayList<>();
    
    boolean stepByStep = false;
    boolean run = false;
    boolean running = false;
    
    private void animationWait()
    {
        if(!running)
            return;
        
        try
        {
            if(!stepByStep)
                Thread.sleep(ANIMATION_SLEEP);
            
            run = false;
            while(stepByStep && !run)
                Thread.sleep(POLL_STEP_SLEEP);
        }
        catch(InterruptedException ex)
        {
            Logger.getLogger(GraphPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void aStarSearch()
    {
        if(running)
        {
            if(stepByStep)
                run = true;
        }
        else
        {
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() { _aStarSearch(); }
            });
        }
    }
    private void _aStarSearch()
    {
        running = true;
        
        //Reset nodes
        for(Node n : nodes)
            n.reset();
        
        HashSet<Node> visited = new HashSet<>();
        MinPriorityQueue<Node> toVisit = new MinPriorityQueue<>(new Node.Comparator());
        
        start.g = 0;
        start.h = heuristicDistance(start, end);
        start.f = start.h;
        start.setText(String.format("%.0f", start.f));
        toVisit.enqueue(start);
        
        boolean found = false;
        while(!toVisit.isEmpty())
        {
            Node current = toVisit.dequeue();
            current.setBackground(Color.BLUE);
            animationWait();
            visited.add(current);
            
            if(current == end)
            {
                found = true;
                break;
            }
            
            for(Connection conn : current.connections)
            {
                Node successor = current.getAdjFromConn(conn);
                if(!successor.traversable || visited.contains(successor))
                    continue;
                
                double newG = current.g + conn.weight;
                double newH = heuristicDistance(successor, end);
                double newF = newG + newH;
                
                if(newG < successor.g)
                {
                    //This is an inefficient solution which runs in O(n) and a
                    //more efficient solution is out of context for this project...
                    //One possible efficient solution is to keep track of nodes in
                    //min heap and reduce its key and reheapify only from that point
                    //insteading of calling remove
                    if(!Double.isInfinite(successor.f))
                        toVisit.heap.remove(successor);
                
                    successor.p = current;
                    successor.g = newG;
                    successor.h = newH;
                    successor.f = newF;
                    successor.setText(String.format("%.0f", successor.f));
                    successor.setBackground(Color.YELLOW);
                    animationWait();
                    
                    toVisit.enqueue(successor);
                }
            }
            current.setBackground(Color.LIGHT_GRAY);
            if(found)
                break;
        }
        
        //Back track
        if(found)
        {
            Node c = end;
            while(c != null)
            {
                c.setBackground(Color.GREEN);
                animationWait();
                c = c.p;
            }
        }
        
        try
        {
            Thread.sleep(FINISHED_WAIT_SLEEP);
        }
        catch(InterruptedException ex)
        {
            Logger.getLogger(GraphPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        running = false;
    }
    
    public final void addConnection(Node n1, Node n2, double w)
    {
        Connection conn = new Connection(n1, n2, w);
        connections.add(conn);
        n1.connections.add(conn);
        n2.connections.add(conn);
    }
    
    public double heuristicDistance(Node n1, Node n2)
    {
        //EUCLIDEAN DISTANCE
        int x1 = n1.getBounds().x + n1.getBounds().width/2;
        int y1 = n1.getBounds().y + n1.getBounds().height/2;
        int x2 = n2.getBounds().x + n2.getBounds().width/2;
        int y2 = n2.getBounds().y + n2.getBounds().height/2;
        return heuristicDistance(x1, y1, x2, y2);
    }
    public double heuristicDistance(int x1, int y1, int x2, int y2)
    {
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }
    
    public void setStepByStep(boolean v)
    {
        stepByStep = v;
    }
    
    public void enableAllNodes()
    {
        for(Node n : nodes)
        {
            n.setTraversable(true);
            n.reset();
        }
    }
}
