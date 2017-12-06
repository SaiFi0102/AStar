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
    Node grid[][] = new Node[AStar.GRID_ROWS][AStar.GIRD_COLUMNS];
            
    public GridGraph()
    {
        setLayout(new GridLayout(AStar.GRID_ROWS, AStar.GIRD_COLUMNS));
        
        //Add nodes
        for(int row = 0; row < AStar.GRID_ROWS; ++row)
        {
            for(int col = 0; col < AStar.GIRD_COLUMNS; ++col)
            {
                Node n = new Node(this);
                grid[row][col] = n;
                add(n);
            }
        }
        
        start = grid[0][0];
        end = grid[AStar.GRID_ROWS-1][AStar.GIRD_COLUMNS-1];
        
        start.setText("S");
        end.setText("E");
        
        double adjDistance = AStar.NODE_WIDTH;
        double diagDistance = Math.sqrt(2) * AStar.NODE_WIDTH;
        for(int row = 0; row < AStar.GRID_ROWS; ++row)
        {
            for(int col = 0; col < AStar.GIRD_COLUMNS; ++col)
            {
                if(row != 0)
                    addConnection(grid[row-1][col], grid[row][col], adjDistance);
                if(row < AStar.GRID_ROWS-1)
                    addConnection(grid[row+1][col], grid[row][col], adjDistance);
                if(col != 0)
                    addConnection(grid[row][col-1], grid[row][col], adjDistance);
                if(col < AStar.GIRD_COLUMNS-1)
                    addConnection(grid[row][col+1], grid[row][col], adjDistance);

                if(row != 0 && col != 0)
                    addConnection(grid[row-1][col-1], grid[row][col], diagDistance);
                if(row != 0 && col < AStar.GIRD_COLUMNS-1)
                    addConnection(grid[row-1][col+1], grid[row][col], diagDistance);
                if(row < AStar.GRID_ROWS-1 && col < AStar.GIRD_COLUMNS-1)
                    addConnection(grid[row+1][col+1], grid[row][col], diagDistance);
                if(row < AStar.GRID_ROWS-1 && col != 0)
                    addConnection(grid[row+1][col-1], grid[row][col], diagDistance);
            }
        }
    }
}
