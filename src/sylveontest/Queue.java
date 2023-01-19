package sylveontest;

public class Queue {
    static int[] queue = new int[100];
    static int head = 0;
    static int tail = 0;

    public static void reset() {
        head = 0;
        tail = 0;
    }
    public static void queuePush(int data) {
        queue[tail++] = data;
    }
    public static int queuePop() {
        return queue[head++];
    }
    public static boolean queueEmpty() {
        return head == tail;
    }
}
