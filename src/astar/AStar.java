/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author Saif
 */
public class AStar
{

    /**
     * @param args the command line arguments
     */
    
    //===Edit it or play around
    //You can change it to change the number of nodes in the GridGraph
    public static final int GRAPH_WIDTH = 500;
    public static final int GRAPH_HEIGHT = 500;
    public static final int NODE_WIDTH = 30;
    
    //This will affect the animation speed
    public static final int ANIMATION_SLEEP = 35; //in ms
    //You can change it to test the effects of overestimation and underestimation
    public static final double HEURISTIC_MULTIPLIER = 1;
    
    //===Edit these only if you know what're doing
    public static final int FINISHED_WAIT_SLEEP = 1000;
    public static final int POLL_STEP_SLEEP = 10; //for polling the continue button click in step by step mode
    
    public static final int GRID_ROWS = GRAPH_HEIGHT / NODE_WIDTH;
    public static final int GIRD_COLUMNS = GRAPH_WIDTH / NODE_WIDTH;
    
    //This function determines which implentation of GraphPanel to use
    public static GraphPanel createGraph()
    {
        //GraphPanel is the base class for GUI Graph and A* implementation
        //GridGraph is derived from GraphPanel and creates a grid of nodes connected to their spacially adjacent nodes
        //CustomGraph is our custom graph for the presentation demonstration
        
        //Change it to new CustomGraph() to see our custom graph for the presentation
        return new GridGraph();
    }
    
    public static void main(String[] args)
    {
        //Create a GUI Window which create the GraphPanel
        GUIFrame frame = new GUIFrame();
    }
    
}
