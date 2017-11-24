/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Saif
 */
public class MinHeap<V>
{
    public Comparator<V> comparator;
    public ArrayList<V> values;
    
    public MinHeap(Comparator<V> c)
    {
        comparator = c;
        values = new ArrayList<>();
        values.add(null);
    }
    
    public boolean compare(V l, V r)
    {
        return comparator.compare(r, l) > 0;
    }
    
    public void printLevelOrder()
    {
        System.out.print("Level order: ");
        for(int i = 1; i < values.size(); ++i)    
            System.out.print(values.get(i).toString() + " ");
        System.out.println("");
    }
    
    public boolean isEmpty()
    {
        return values.size() <= 1;
    }
    
    public V minVal()
    {
        if(isEmpty())
            return null;
        return values.get(1);
    }
    
    public void insert(V value)
    {
        if(isEmpty())
            values.add(1, value);
        else
        {
            values.add(value);
            heapifyUp();
        }
    }
    
    public V removeRoot()
    {
        return removeIdx(1);
    }
    
    public boolean remove(V val)
    {
        if(val == null)
            return false;
        
        for(int p = 1; p < values.size(); ++p)
        {
            if(val.equals(values.get(p)))
            {
                removeIdx(p);
                return true;
            }
        }
        return false;
    }
    
    public V removeIdx(int p)
    {
        if(isEmpty())
            return null;
        
        swapIdx(p, values.size()-1);
        V removedVal = values.get(values.size()-1);
        values.remove(values.size()-1);
        
        heapifyDown(p);
        return removedVal;
    }
    
    public void heapifyUp()
    {
        int p = values.size()-1;
        while(p > 1 && compare(values.get(p), values.get(p/2)))
        {
            swapIdx(p, p/2);
            p = p/2;
        }
    }
    public void heapifyDown(int p)
    {
        int minIdx = p;
        
        //Left node
        if(p*2 < values.size() && compare(values.get(p*2), values.get(minIdx)))
            minIdx = p*2;
        
        //Right node
        if(p*2+1 < values.size() && compare(values.get(p*2+1), values.get(minIdx)))
            minIdx = p*2+1;
        
        //Swap with child node and heapifyDown recursively downward until
        //both child are greater than the current node
        if(minIdx != p)
        {
            swapIdx(p, minIdx);
            heapifyDown(minIdx);
        }
    }
    
    private void swapIdx(int a, int b)
    {
        Collections.swap(values, a, b);
    }
}
