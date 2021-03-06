package com.newton.interfaces;

import com.newton.resources.Node;

public interface ITree {
    void insert(Object element);
    void upHeap(Node node);
    Node removeMin();
    void downHeap(Node node_down, Node node_up);
    void setTail(Node node);
    Node getTail();
    void reallocTail(Node node);
    Integer height();
    double doubleHeight();
    Integer size();
    Boolean isEmpty();
    Node min();
    void print(Node node, Integer spaces);
}
