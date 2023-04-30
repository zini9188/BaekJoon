import java.util.*;

class Solution {
    static class Node{
        int node, dist;
        ArrayList<Integer> nodes;
        public Node(int n, int d){
            this.node = n;
            this.dist = d;
            nodes = new ArrayList<>();
        }
        
        public void addNode(int node){
            nodes.add(node);
        }
    }
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        Node[] nodes = new Node[n + 1];
        for(int i = 1; i <= n; i++){
            nodes[i] = new Node(i, 500000);
        }
        
        for(int[] node : edge){
            nodes[node[0]].addNode(node[1]);
            nodes[node[1]].addNode(node[0]);
        }
        
        Queue<Node> queue = new LinkedList<>();
        nodes[1].dist = 0;
        queue.add(nodes[1]);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            
            for(int i = 0; i < node.nodes.size(); i++){
                int nextNode = node.nodes.get(i);
                if(nodes[nextNode].dist > node.dist + 1){
                    nodes[nextNode].dist = node.dist + 1;
                    queue.add(nodes[nextNode]);
                }
            }
        }
         
        int maxDist = 0;        
        for(int i = 1; i <= n; i++){
            maxDist = Math.max(nodes[i].dist, maxDist);
        }
        
        for(int i = 1; i <= n; i++){
            if(maxDist == nodes[i].dist){
                answer++;
            }
        }
        
        
        return answer;
    }
    
    private ArrayList<Integer> addNode(ArrayList<Integer> nodes, int child){
        nodes.add(child);
        return nodes;
    }
}