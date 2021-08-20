package com.newton.resources;

import com.newton.interfaces.ITree;

public class Tree implements ITree {
    private Node root;
    private Integer quantity_nodes;

    public Tree(Object element) {
        this.root = new Node(element);
        this.quantity_nodes = 1;
    }

    @Override
    public void insert(Object element) {

    }

    @Override
    public void upHeap(Node node) {

    }

    @Override
    public Node removeMin() {
        return null;
    }

    @Override
    public void downHeap(Node node) {

    }

    @Override
    public Integer height() {
        return null;
    }

    @Override
    public Integer size() {
        return null;
    }

    @Override
    public Boolean isEmpty() {
        return null;
    }

    @Override
    public Node min() {
        return null;
    }

    @Override
    public void print() {

    }
}
