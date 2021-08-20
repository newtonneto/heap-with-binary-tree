package com.newton.resources;

import com.newton.interfaces.INode;

public class Node implements INode {
    private Object key;
    private Node left_child;
    private Node right_child;
    private Node parent;

    public Node(Object key) {
        this.parent = null;
        this.left_child = null;
        this.right_child = null;
        this.key = key;
    }

    public Node(Node node, Object key) {
        this.parent = node;
        this.left_child = null;
        this.right_child = null;
        this.key = key;
    }

    @Override
    public Object getKey() {
        return this.key;
    }

    @Override
    public void setKey(Object key) {
        this.key = key;
    }

    @Override
    public Object removeKey() {
        Object key = this.key;
        this.key = null;
        return key;
    }

    @Override
    public Node getLeftChild() {
        return this.left_child;
    }

    @Override
    public Node getRightChild() {
        return this.right_child;
    }

    @Override
    public Node getParent() {
        return this.parent;
    }

    @Override
    public void setLeftChild(Node node) {
        this.left_child = node;
    }

    @Override
    public void setRightChild(Node node) {
        this.right_child = node;
    }

    @Override
    public void setParent(Node node) {
        this.parent = node;
    }

    @Override
    public Node removeLeftChild() {
        Node node = this.left_child;
        this.left_child = null;
        return node;
    }

    @Override
    public Node removeRightChild() {
        Node node = this.right_child;
        this.right_child = null;
        return node;
    }

    @Override
    public Node removeParent() {
        Node node = this.parent;
        this.parent = null;
        return node;
    }

    @Override
    public boolean isInternal() {
        return (this.left_child != null || this.right_child != null);
    }

    @Override
    public String toString() {
        return this.key.toString();
    }

    @Override
    public boolean isRoot(Node node) {
        return (this.parent == null);
    }
}
