package com.example.semesterprojectarir;

/**
 *
 * @author Arir Allana
 * @version 1.0
 *
 * The class for linked stack with static Node class
 * This is used by Hanoi.java class to store disks indices as values and rod indices as nodes
 *
 * */

class LL {
    private static class Node {
        int val;
        Node  next;

        /**
         *
         * @param _val
         * @param _next
         * initializes each node with its value and next node object
         */
        Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    /**
     * head: top node on linked stack
     * elm: number of elements in stack
     */
    private Node head;
    private int elm;

    /**
     * method to create empty linked stack
     */
    LL() {
        head = null;
        elm = 0;
    }

    /**
     *
     * @param value
     * adds node to top of stack with value passed as parameter
     * increases number of elements in stack by 1
     */
    void add(int value) {
        head = new Node(value, head);
        elm++;
    }


    /**
     * pops node from top of stack
     * sets top node value to next node
     * decreases number of elements in stack by 1
     * @return elm1
     * returns value of removed (top) node
     */
    int remove() {
        int elm1 = head.val;
        head = head.next;
        elm--;
        return elm1;
    }

    /**
     *
     * @return value of node on top of stack
     */
    int view() {
        return head.val;
    }

    /**
     *
     * iterates to node at given index
     * @param index
     * @return value of node on current index
     */
    int get(int index) {
        Node pointer = head;
        for (int curr = 0; curr < index; curr++) {
            pointer = pointer.next;
        }
        return pointer.val;
    }

    /**
     *
     * @return returns if top node is false
     */
    boolean isEmpty() {
        return head == null;
    }

    /**
     *
     * @return returns the number of elements in stack
     */
    int len() {
        return elm;
    }
}

