package DataStructures_Wk2;
import java.util.*;

public class MergingTables {
    public static class Query{
        public int source;
        public int destination;
        public Query(int dest, int src){
           this.destination = dest;
           this.source = src;
        }
    }

    public static class DisjointSet{
        public int[] parent;
        public int[] numRows;
        public int maxRows;
        public DisjointSet(int n){
            this.parent = new int[n];
            this.numRows = new int[n];
            maxRows = 0;
        }

        public void makeSet(int i, int rows){
            parent[i] = i;
            numRows[i] = rows;
            if(rows > maxRows){
                maxRows = rows;
            }
        }

        public int find(int i){
            Set<Integer> setOfNodesInPath = new HashSet<>();
            while(i != parent[i]){
                setOfNodesInPath.add(i);
                i = parent[i];
            }
            for(int j:setOfNodesInPath){
                parent[j] = i;
            }
            return i;
        }

        public void union(int i, int j){
            int i_root = find(i);
            //System.out.println("i_root---->"+i_root);
            int j_root = find(j);
            //System.out.println("j_root---->"+j_root);
            if(i_root == j_root){
                return;
            }
            parent[j_root] = i_root;
            numRows[i_root] = numRows[i_root]+numRows[j_root];
            if(numRows[i_root] > maxRows){
                maxRows = numRows[i_root];
            }
        }

        public int getMaxRows(){
            return maxRows;
        }
    }

    public static void getNumRowsForEachQuery(Query[] queries, DisjointSet disjointSet ){
        for(int i = 0; i<queries.length; i++){
            int dest = queries[i].destination - 1;
            int src = queries[i].source - 1;
            disjointSet.union(dest,src);
            System.out.println(disjointSet.getMaxRows());
        }
    }

    public static void main(String args[]){
       Scanner sc = new Scanner(System.in);
       int n = sc.nextInt();
       int m = sc.nextInt();
       DisjointSet disjointSet = new DisjointSet(n);
       Query[] queries = new Query[m];
       for(int i = 0; i<n; i++){
           int rows = sc.nextInt();
           disjointSet.makeSet(i,rows);
       }
       for(int i = 0; i<m; i++){
           int dest = sc.nextInt();
           int src = sc.nextInt();
           queries[i] = new Query(dest,src);
       }

        getNumRowsForEachQuery(queries, disjointSet);

    }
}
