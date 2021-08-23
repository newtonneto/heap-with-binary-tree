package com.newton.interfaces;

import com.newton.resources.Node;

public interface ITree {
    void insert(Object element);
    void upHeap(Node node);
    Node removeMin();
    void downHeap(Node node);
    void setTail(Node node);
    Node getTail();
    Integer height();
    double doubleHeight();
    Integer size();
    Boolean isEmpty();
    Node min();
    void print(Node node, Integer spaces);
}
