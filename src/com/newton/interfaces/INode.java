package com.newton.interfaces;

import com.newton.resources.Node;

public interface INode {
    Object getKey();
    void setKey(Object key);
    Object removeKey();
    Node getLeftChild();
    Node getRightChild();
    Node getParent();
    void setLeftChild(Node node);
    void setRightChild(Node node);
    void setParent(Node node);
    Node removeLeftChild();
    Node removeRightChild();
    Node removeParent();
    boolean isInternal();
    String toString();
    boolean isRoot(Node node);
}
