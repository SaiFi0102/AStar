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
    
    Node start = null; //Starting node for A*
    Node end = null; //Destination node for A*
    ArrayList<Node> nodes = new ArrayList<>();
    ArrayList<Connection> connections = new ArrayList<>();
    
    boolean stepByStep = false;
    boolean run = false;
    boolean running = false;
    
    public void aStarSearch()
    {
        if(running)
        {
            //The value of run is polled to determine when to run the next step
            //in step-by-step mode
            if(stepByStep)
                run = true;
        }
        else
        {
            //Run the A* Search in a new thread so that it does not interfere with
            //the GUI's thread when Thread.sleep() is called for animation pause
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() { _aStarSearch(); }
            });
        }
    }
    private void _aStarSearch()
    {
        //So that this function will not be called until the previous execution
        //of A* is complete
        running = true;
        
        //Reset nodes and their transient values(g,h,f,p,...)
        for(Node n : nodes)
            n.reset();
        
        HashSet<Node> visited = new HashSet<>(); //Closed set
        MinPriorityQueue<Node> toVisit = new MinPriorityQueue<>(new Node.Comparator()); //Open set
        
        //First step: insert the starting node into the toVisit set
        start.g = 0;
        start.h = heuristicDistance(start, end);
        start.f = start.h;
        start.setText(String.format("%.0f", start.f));
        toVisit.enqueue(start);
        
        boolean found = false;
        while(!toVisit.isEmpty())
        {
            Node current = toVisit.dequeue();
            visited.add(current);
            
            current.setBackground(Color.BLUE);
            animationWait();
            
            if(current == end)
            {
                found = true;
                break;
            }
            
            for(Connection conn : current.connections)
            {
                //Connections has 2 nodes, this function determines which one is the successor
                Node successor = current.getSuccessorFromConn(conn);
                
                //Skip visited or disabled nodes
                if(!successor.traversable || visited.contains(successor))
                    continue;
                
                double newG = current.g + conn.weight;
                double newH = heuristicDistance(successor, end);
                double newF = newG + newH;
                
                //Replace a node's path information only if there was no better path computed before
                //Node's default g value is infinity therefore by default newG < successor.g == true
                if(newG < successor.g)
                {
                    //This is an inefficient solution which runs in O(n) and a
                    //more efficient solution is out of context for this project...
                    //One possible efficient solution is to keep track of nodes in
                    //min heap and reduce its key and reheapify only from that point
                    //insteading of removing linearly
                    if(!Double.isInfinite(successor.f)) //successor.f == inf, it means it was never added to the toVisit set before
                        toVisit.heap.remove(successor);
                
                    successor.p = current;
                    successor.g = newG;
                    successor.h = newH;
                    successor.f = newF;
                    toVisit.enqueue(successor);
                    
                    successor.setText(String.format("%.0f", successor.f));
                    successor.setBackground(Color.YELLOW);
                    animationWait();
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
        
        //This is just to prevent clicking the run button after A* has finished
        //when clicking repeatedly which would reset all the nodes by mistake
        try
        {
            Thread.sleep(AStar.FINISHED_WAIT_SLEEP);
        }
        catch(InterruptedException ex)
        {
            Logger.getLogger(GraphPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Release the thread lock
        running = false;
    }
    
    private void animationWait()
    {
        if(!running)
            return;
        
        try
        {
            //If not step-by-step mode, sleep for ANIMATION_SLEEP then return
            if(!stepByStep)
                Thread.sleep(AStar.ANIMATION_SLEEP);
            
            //Otherwise mark run as false and keep polling it till it becomes true
            run = false;
            while(stepByStep && !run)
                Thread.sleep(AStar.POLL_STEP_SLEEP);
        }
        catch(InterruptedException ex)
        {
            Logger.getLogger(GraphPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public final void addConnection(Node n1, Node n2, double w)
    {
        //Adds an undirected connection in the connection and adjacency lists
        Connection conn = new Connection(n1, n2, w);
        connections.add(conn);
        n1.connections.add(conn);
        n2.connections.add(conn);
    }
    
    public double heuristicDistance(Node n1, Node n2)
    {
        int x1 = n1.getBounds().x + n1.getBounds().width/2;
        int y1 = n1.getBounds().y + n1.getBounds().height/2;
        int x2 = n2.getBounds().x + n2.getBounds().width/2;
        int y2 = n2.getBounds().y + n2.getBounds().height/2;
        
        return AStar.HEURISTIC_MULTIPLIER * euclideanDistance(x1, y1, x2, y2);
    }
    private double euclideanDistance(int x1, int y1, int x2, int y2)
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
