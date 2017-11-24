/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;
import java.util.Comparator;

/**
 *
 * @author Saif
 */
public class MinPriorityQueue<V>
{
    MinHeap<V> heap;

    public MinPriorityQueue(Comparator<V> c)
    {
        heap = new MinHeap<>(c);
    }
    
    public boolean isEmpty()
    {
        return heap.isEmpty();
    }
    public void enqueue(V value)
    {
        heap.insert(value);
    }
    public V dequeue()
    {
        return heap.removeRoot();
    }
    public V peek()
    {
        return heap.minVal();
    }
}
