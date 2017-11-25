/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.awt.GridLayout;

/**
 *
 * @author Saif
 */
public class GridGraph extends GraphPanel
{
    public GridGraph()
    {
        int rows = UIFrame.FRAME_HEIGHT / NODE_WIDTH;
        int cols = UIFrame.FRAME_WIDTH / NODE_WIDTH;
        setLayout(new GridLayout(rows, cols));
        
        //Temporarily create a 2D array just for ease of creating connections
        Node grid[][] = new Node[rows][cols];
        
        //Add nodes
        for(int row = 0; row < rows; ++row)
        {
            for(int col = 0; col < cols; ++col)
            {
                Node n = new Node(this);
                grid[row][col] = n;
                add(n);
            }
        }
        
        start = grid[0][0];
        end = grid[rows-1][cols-1];
        
        //Add connections
        for(int row = 0; row < rows; ++row)
        {
            for(int col = 0; col < cols; ++col)
            {
                if(row != 0)
                {
                    addConnection(grid[row-1][col], grid[row][col], NODE_WIDTH);
                    if(col != 0)
                        addConnection(grid[row-1][col-1], grid[row][col], Math.sqrt(2*NODE_WIDTH));
                }
                if(row < rows-1)
                {
                    addConnection(grid[row+1][col], grid[row][col], NODE_WIDTH);
                    if(col < cols-1)
                        addConnection(grid[row+1][col+1], grid[row][col], Math.sqrt(2*NODE_WIDTH));
                }
                if(col != 0)
                {
                    addConnection(grid[row][col-1], grid[row][col], NODE_WIDTH);
                    if(row != 0)
                        addConnection(grid[row-1][col-1], grid[row][col], Math.sqrt(2*NODE_WIDTH));
                }
                if(col < cols-1)
                {
                    addConnection(grid[row][col+1], grid[row][col], NODE_WIDTH);
                    if(rows < rows-1)
                        addConnection(grid[row+1][col+1], grid[row][col], Math.sqrt(2*NODE_WIDTH));
                }
            }
        }
    }   
}
