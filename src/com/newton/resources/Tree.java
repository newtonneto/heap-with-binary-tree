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
        } else if (this.tail.getLeftChild() == null) { //Verifica se a esquerda da tail está vazia
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
        if (this.isEmpty()) {
            //exception
        }
        Node next_tail;
        Node old_root = this.root;
        Integer left_child_value = Integer.parseInt(old_root.getLeftChild().toString());
        Integer right_child_value = Integer.parseInt(old_root.getRightChild().toString());

        //Verifica se a heap é uma arvore binaria perfeita
        //Caso seja, a tail tem que ser buscada novamente, pois a referencia da mesma vai estar apontando
        //para o node na extrema esquerda, onde seria o proximo insert, porem, como isso é um remove, a tail
        //deve ser o elemento removido (ocupando o lugar do root), e em uma arvore binaria perfeita esse
        //elemento se encontra na extrema direita. Pro caso da arvore não ser perfeita, pode-se usar a
        //referencia normal da tail. É possivel verificar se a árvore é perfeita ou não atraves do valor em double
        //da sua altura, se for um número fracionado a mesma não é perfeita
        //Esse processo é necessario pois a causa é definida pelo proximo node a receber nodes, e não necessariamente
        //pelo último node
        double fractional_part = this.doubleHeight() % 1;

        //Verifica se o root e a tail são o mesmo elemento
        if (this.root == this.tail) {

        }
        else if (fractional_part == 0.0) {
            //Como a árvore é perfeita, é necessario buscar a tail real, que ta na extrema direita
            this.reallocTail(this.root);

            //Remove as referencias do pai da tail real
            if (this.tail.getParent().getLeftChild() == this.tail) {
                this.tail.getParent().setLeftChild(null);
            } else {
                this.tail.getParent().setRightChild(null);
            }

            //Passa os filhos do root para a tail
            this.tail.setLeftChild(this.root.getLeftChild());
            this.tail.setRightChild(this.root.getRightChild());
            //Salva a referência da proxima tail, será o pai do tail atual (não é a tail real, apenas a que irá receber novos nodes)
            next_tail = this.tail.getParent();
            //Remove a referência do pai do tail, assim ele efetivamente se tornara o root
            this.tail.setParent(null);
            //Remove as referencias do antigo root
            this.root.setParent(null);
            this.root.setLeftChild(null);
            this.root.setRightChild(null);
            this.root = this.tail;
            this.tail = next_tail;

            Integer root_value = Integer.parseInt(this.root.getKey().toString());

            //Verifica qual dos filhos do root é o menor
            if (left_child_value < right_child_value) {
                //Agora verifica se o novo root é maior que o seu menor filho
                if (root_value > left_child_value) {
                    downHeap(this.root, this.root.getLeftChild());
                } else if (root_value > right_child_value) { //Caso não seja maior que o menor filho, verifica se não é maior que o outro filho
                    downHeap(this.root, this.root.getRightChild());
                }
            } else {
                if (root_value > right_child_value) {
                    downHeap(this.root, this.root.getRightChild());
                } else if (root_value > left_child_value) {
                    downHeap(this.root, this.root.getLeftChild());
                }
            }
        } else {
            //A tail real é um dos filhos da tail, verificar primeiro se o filho direito não é nulo, ser for a tail real é o filho esquerdo
            if (this.tail.getRightChild() != null) {
                next_tail = this.tail.getLeftChild();
                this.tail = this.tail.getRightChild();
            } else {
//                next_tail = this.tail.getParent().;
                this.tail = this.tail.getLeftChild();
            }

            //Verifica qual dos filhos do root é o menor
            if (left_child_value > right_child_value) {

            } else {

            }
        }

        return null;
    }

    @Override
    public void downHeap(Node node_down, Node node_up) {
        //Salva temporariamente os filhos do node que vai descer na heap
        Node downed_node_left_children = node_down.getLeftChild();
        Node downed_node_right_children = node_down.getRightChild();
        Node downed_node_parent = node_down.getParent();

        //Altera os filhos e o pai do node que vai descer na heap
        node_down.setLeftChild(node_up.getLeftChild());
        node_down.setRightChild(node_up.getRightChild());
        node_down.setParent(node_up);

        //Altera os filhos e o pai do node que vai subir na heap
        node_up.setParent(downed_node_parent);
        if (downed_node_left_children == node_up) {
            node_up.setLeftChild(node_down);
            node_up.setRightChild(downed_node_right_children);
        } else if (downed_node_right_children == node_up) {
            node_up.setLeftChild(downed_node_left_children);
            node_up.setRightChild(node_down);
        }

        //Verifica se o node que ta subindo vai ser root
        if (downed_node_parent == null) {
            this.root = node_up;
        }
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
            System.out.println("node: " + node.getKey().toString());
            setTail(node.getParent());
        }
    }

    @Override
    public Node getTail() {
        return this.tail;
    }

    @Override
    public void reallocTail(Node node) { //Metodo responsavel por redefinir a posição da tail quando necessario
        this.tail = node;

        //Vai decendo de nível sempre pela direita, até chegar ao elemento mais extremo, na ponta da árvore
        while (this.tail.getRightChild() != null) {
            this.tail = this.tail.getRightChild();
        }
    }

    @Override
    public Integer height() {
        return (int)Math.ceil(Math.log(this.quantity_nodes + 1) / Math.log(2)) - 1;
    }

    @Override
    public double doubleHeight() {
        return (Math.log(this.quantity_nodes + 1) / Math.log(2)) - 1;
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
        if (this.isEmpty()) {
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
