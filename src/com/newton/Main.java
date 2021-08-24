package com.newton;

import com.newton.resources.Tree;

public class Main {

    public static void main(String[] args) {
        Tree heap = new Tree(5);

        heap.insert(10);
        heap.insert(15);
        heap.insert(20);
        heap.insert(25);
        heap.insert(30);
        heap.insert(1);

        heap.print(heap.min(), 10);
    }
}
