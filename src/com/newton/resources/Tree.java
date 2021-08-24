package com.newton.resources;

import com.newton.interfaces.ITree;

public class Tree implements ITree {
    private Node root;
    private Integer quantity_nodes;
    private Node tail;

    public Tree(Object element) {
        this.root = new Node(element);
        this.quantity_nodes = 1;
        this.tail = this.root;
    }

    @Override
    public void insert(Object element) {
        if (this.root == null) {
            this.root = new Node(element);
            this.tail = this.root;
        } else if (this.tail.getLeftChild() == null) { //Verifica se a esquerda da causa está vazia
            this.tail.setLeftChild(new Node(element));
            //É necessario definir os pais dos nodes para que os mesmos não caiam na condição exclusiva do root no setTail
            this.tail.getLeftChild().setParent(this.tail);
            upHeap(this.tail.getLeftChild());
        } else {
            this.tail.setRightChild(new Node(element));
            //É necessario definir os pais dos nodes para que os mesmos não caiam na condição exclusiva do root no setTail
            this.tail.getRightChild().setParent(this.tail);
            upHeap(this.tail.getRightChild());
            setTail(this.tail);
        }
        this.quantity_nodes++;
    }

    @Override
    public void upHeap(Node node) {
        //Verifica se o node é menor que o seu pai
        if (Integer.parseInt(node.getKey().toString()) < Integer.parseInt(node.getParent().getKey().toString())) {
            Node parent = node.getParent();
            Node grandparent = parent.getParent();

            //Verifica se um dos elementos a ser mudado de posição é o root
            if (grandparent == null) {
                //Armazena os filhos do node
                Node left_children = node.getLeftChild();
                Node right_children = node.getRightChild();

                //Realiza o swap entre o node e seu pai, incluindo o irmão do node passa a ser seu filho
                if (parent.getLeftChild() == node) {
                    node.setLeftChild(parent);
                    node.setRightChild(parent.getRightChild());
                } else if (parent.getRightChild() == node) {
                    node.setLeftChild(parent.getLeftChild());
                    node.setRightChild(parent);
                }

                //O que antes era pai do node agora é seu filho, sendo agora pai dos ex filhos do node
                node.setParent(null);
                parent.setParent(node);
                parent.setLeftChild(left_children);
                parent.setRightChild(right_children);
                //Atualiza o root
                this.root = node;
            } else {
                //Realiza a substituição das posições, iniciamente troca o node filho do avó
                if (grandparent.getLeftChild() == parent) {
                    grandparent.setLeftChild(node);
                } else if (grandparent.getRightChild() == parent) {
                    grandparent.setRightChild(node);
                }

                node.setParent(grandparent);

                //Realiza a substituição das posições do node pai
                if (parent.getLeftChild() == node) {
                    node.setLeftChild(parent);
                    node.setRightChild(parent.getRightChild());
                } else if (parent.getRightChild() == node) {
                    node.setRightChild(parent);
                    node.setLeftChild(parent.getLeftChild());
                }

                parent.setLeftChild(null);
                parent.setRightChild(null);

                upHeap(node);
            }
        }
    }

    @Override
    public Node removeMin() {
        return null;
    }

    @Override
    public void downHeap(Node node) {

    }

    @Override
    public void swapNodes(Node node) {

    }

    @Override
    public void setTail(Node node) { //O node recebido é a tail atual da árvore, e deve ser substituida
        //Caso um nivel seja completamente preenchido, a proxima tail deve ser o elemento mais esquerda
        if (node.getParent() == null) {
            this.tail = node;

            //Vai decendo de nível sempre pela esquerda, até chegar ao elemento mais extremo, na ponta da árvore
            while (this.tail.getLeftChild() != null) {
                this.tail = this.tail.getLeftChild();
            }
        } else if (node.getParent().getLeftChild() == node) { //Se a tail atual é um elemento da esquerda, a proxima cauda será a direita
            //Já que o node da esquerda está ocupado, seu irmão da direita será a proxima tail
            this.tail = node.getParent().getRightChild();

            //Buscar o elemento mais a esquerda da nova tail, até achar um espaço vazio
            while (this.tail.getLeftChild() != null) {
                this.tail = this.tail.getLeftChild();
            }
        } else if (node.getParent().getRightChild() == node) { //Se a tail atual é um elemento da direita, buscar o proximo elemento da esquerda que esta vazio
            setTail(node.getParent());
        }
    }

    @Override
    public Node getTail() {
        return this.tail;
    }

    @Override
    public Integer height() {
        return (int)Math.ceil(Math.log(6 + 1) / Math.log(2)) - 1;
    }

    @Override
    public double doubleHeight() {
        return (Math.log(6 + 1) / Math.log(2)) - 1;
    }

    @Override
    public Integer size() {
        return quantity_nodes;
    }

    @Override
    public Boolean isEmpty() {
        return (this.root == null);
    }

    @Override
    public Node min() {
        if (isEmpty()) {
            //exception
        }

        return this.root;
    }

    @Override
    public void print(Node node, Integer spaces) {
        spaces = spaces + 10;

        if (node.isInternal() && node.getRightChild() != null) {
            print(node.getRightChild(), spaces);
        }

        System.out.print("\n");
        for (int index = 10; index < spaces; index++) {
            System.out.print(" ");
        }
        System.out.print(node.getKey() + "\n");

        if (node.isInternal() && node.getLeftChild() != null) {
            print(node.getLeftChild(), spaces);
        }
    }
}
