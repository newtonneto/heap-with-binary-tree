package com.newton;

import com.newton.resources.Tree;

public class Main {

    public static void main(String[] args) {
        Tree heap = new Tree(1);

        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        heap.insert(5);
        heap.insert(6);
        heap.insert(7);

        heap.print(heap.min(), 10);
    }
}
