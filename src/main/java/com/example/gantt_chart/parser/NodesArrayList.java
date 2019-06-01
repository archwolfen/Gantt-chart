package com.example.gantt_chart.parser;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class NodesArrayList extends ArrayList<Node> {

    public NodesArrayList() {
        super();
    }

    public NodesArrayList(NodeList nodeList) {

        for (int i = 0; i < nodeList.getLength(); i++) {
            add(nodeList.item(i));
        }

        this.trimVoidNodes();
    }

    public void trimVoidNodes() {

        NodesArrayList newList = new NodesArrayList();

        for (Node currNode : this) {
            if (((Node) currNode).getNodeType() == Node.ELEMENT_NODE) {
                newList.add(currNode);
            }
        }

        this.clear();
        this.addAll(newList);
    }

    // return index of node with name "elem", in case this node does not exists return -1

    public int indexOfElement(String elem) {
        for (Node currNode : this) {
            if (currNode.getNodeName().equals(elem)) {
                return super.indexOf(currNode);
            }
        }

        return -1;
    }

    public boolean hasElement(String elem) {
        for (Node currNode : this) {
            if (currNode.getNodeName().equals(elem)) {
                return true;
            }
        }

        return false;
    }

}
