package compiler.phase.regall;

import compiler.phase.asmgen.ASM;
import compiler.phase.imcgen.IMC;

import java.util.HashSet;

import java.util.HashMap;

public class Graph {

    private HashSet<Node> nodes;
    private HashMap<ASM.TempOperand, Node> temporaryMappings;

    public Graph() {
        nodes = new HashSet<Node>();
        temporaryMappings = new HashMap<ASM.TempOperand, Node>();
    }

    public void clear() {
        this.temporaryMappings.clear();
        this.nodes.clear();
    }

    public Node getNode(ASM.TempOperand temporary) {
        return this.temporaryMappings.get(temporary);
    }

    public Node addNode(ASM.TempOperand temporary) {
        Node existingNode = this.getNode(temporary);
        if (existingNode != null)
            return existingNode;

        Node node = new Node(temporary);
        this.nodes.add(node);
        this.temporaryMappings.put(temporary, node);
        return node;
    }

    public Node addNode(Node node) {
        this.nodes.add(node);
        this.temporaryMappings.put(node.temporary, node);
        return node;
    }

    public void removeNode(ASM.TempOperand temporary) {
        Node node = this.getNode(temporary);
        if (node != null) {
            this.nodes.remove(node);
            this.temporaryMappings.remove(temporary);
            for (Node neighbour : node.neighbours) {
                neighbour.removeEdge(node);
            }
        }
    }

    public void addEdge(ASM.TempOperand first, ASM.TempOperand second) {
        Node firstNode = this.getNode(first);
        if (firstNode == null)
            firstNode = this.addNode(first);
        
        Node secondNode = this.getNode(second);
        if (secondNode == null)
            secondNode = this.addNode(second);
        
        this.addEdge(firstNode, secondNode);
    }

    public void removeEdge(ASM.TempOperand first, ASM.TempOperand second) {
        Node firstNode = this.getNode(first);
        Node secondNode = this.getNode(second);
        if (firstNode != null && secondNode != null)
            this.removeEdge(firstNode, secondNode);
    }

    public void addEdge(Node first, Node second) {
        if (first == second)
            return;
        
        first.addEdge(second);
        second.addEdge(first);
    }

    private void removeEdge(Node first, Node second) {
        first.removeEdge(second);
        second.removeEdge(first);
    }

    public HashSet<Node> nodes() {
        return new HashSet<Node>(this.nodes);
    }

    public HashMap<Node, HashSet<Node>> edges() {
        HashMap<Node, HashSet<Node>> edges = new HashMap<Node, HashSet<Node>>();
        for (Node node : this.nodes) {
            edges.put(node, node.neighbours);
        }
        return edges;
    }

    public boolean isEmpty() {
        return this.nodes.isEmpty();
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (Node node : this.nodes) {
            buffer.append(node.toString());
            buffer.append(" -> ");
            buffer.append(node.neighbours.toString());
            buffer.append('\n');
        }
        if (buffer.length() > 0)
            buffer.deleteCharAt(buffer.length() - 1);
        return buffer.toString();
    }

}