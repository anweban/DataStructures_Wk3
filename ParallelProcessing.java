package DataStructures_Wk2;
import java.util.*;

public class ParallelProcessing {

    public static class Heap{
        public int size;
        public Node[] heap;

        public void add(long queueNo, long time){
            heap[size] = new Node(queueNo, time);
            size++;
            pushUp(size-1);
            for(int i = 0; i<size; i++) {

                //System.out.println("q-->" + heap[i].queueNo);
                //System.out.println("t-->" + heap[i].endTime);
            }
        }

        public Node extractMax(){
           Node max = heap[0];
           heap[0] = heap[size-1];
           size--;
           shiftDown(0);
           for(int i = 0; i<size; i++) {
               //System.out.println("qmmmm-->" + heap[i].queueNo);
               //System.out.println("tmmmm-->" + heap[i].endTime);
           }
           return max;


        }

        public Heap(int n){
            heap = new Node[n];
            size = 0;
        }

        public void pushUp(int i){
            if((i-1) / 2 >= 0){
                Boolean change = false;
                Node parent = heap[(i-1)/2];
                if(parent.endTime > heap[i].endTime){
                    change = true;
                }else if(parent.endTime == heap[i].endTime){
                    if(parent.queueNo > heap[i].queueNo){
                        change = true;
                    }
                }
                if(change){
                    heap[(i-1)/2] = heap[i];
                    heap[i] = parent;
                    pushUp((i-1)/2);
                }
            }
        }

        public void shiftDown(int i){
            int problemIndex = i;
            int left = 2*i + 1;
            int right = 2*i + 2;
            if(left < size) {
                if (heap[left].endTime < heap[problemIndex].endTime) {
                    problemIndex = left;
                } else if (heap[left].endTime == heap[problemIndex].endTime) {
                    if (heap[left].queueNo < heap[problemIndex].queueNo) {
                        problemIndex = left;
                    }
                }
            }

            if(right < size) {
                if (heap[right].endTime < heap[problemIndex].endTime) {
                    problemIndex = right;
                } else if (heap[right].endTime == heap[problemIndex].endTime) {
                    if (heap[right].queueNo < heap[problemIndex].queueNo) {
                        problemIndex = right;
                    }
                }
            }

            if(problemIndex != i){
                Node temp = heap[i];
                heap[i] = heap[problemIndex];
                heap[problemIndex] = temp;
                shiftDown(problemIndex);
            }
        }
    }

    public static class Node{
        public long queueNo;
        public long endTime;
        public Node(long queueNo, long endTime){
            this.queueNo = queueNo;
            this.endTime = endTime;
        }
    }

    public static void assignQueue(long[] jobs, Heap h){
        for(long jobDuration:jobs){
            Node n = h.extractMax();
            long queue = n.queueNo;
            long start = n.endTime;
            System.out.print(queue+" ");
            System.out.print(start);
            h.add(queue, start+jobDuration);
            System.out.println();
        }
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int queues = sc.nextInt();
        int jobNo = sc.nextInt();
        Heap h = new Heap(queues);
        for(int j=0; j<queues; j++){
            h.add(j, 0);
        }
        long[] jobs = new long[jobNo];
        for(int i = 0; i<jobNo; i++){
            jobs[i] = sc.nextInt();
        }

        assignQueue(jobs, h);

    }
}
