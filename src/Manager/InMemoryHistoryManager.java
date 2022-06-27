package Manager;

import Tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryHistoryManager implements HistoryManager {
    private final ArrayList<Task> historyOfRequestsList;
    private final HashMap<Integer, Node<Task>> tasksIdInHistory;
    private Node<Task> head;
    private Node<Task> tail;

    public InMemoryHistoryManager() {
        this.historyOfRequestsList = new ArrayList<>();
        this.tasksIdInHistory = new HashMap<>();
        this.head = null;
        this.tail = null;
    }

    @Override
    public void add(Task task) {
        if (task != null) {
            if (tasksIdInHistory.containsKey(task.getUid())) {
                remove(task.getUid());
            }
            tasksIdInHistory.put(task.getUid(), linkLast(task));
        }
    }

    private Node<Task> linkLast(Task task) {
        Node<Task> node;
        if (head == null) {
            node = new Node<>(task, null, null);
            head = node;
        } else {
            node = new Node<>(task, tail, null);
            tail.setNext(node);
        }
        tail = node;
        return node;
    }

    @Override
    public void remove(int id) {
        removeNode(tasksIdInHistory.get(id));
    }

    @Override
    public ArrayList<Task> getHistory() {
        return getTasks();
    }

   private ArrayList<Task> getTasks() {
        historyOfRequestsList.clear();
        if (head != null) {
            Node<Task> currentNode = head;
            while (currentNode.getNext() != null) {
                historyOfRequestsList.add(currentNode.getTask());
                currentNode = currentNode.getNext();
            }
            historyOfRequestsList.add(tail.getTask());
        }
        return historyOfRequestsList;
    }

    private void removeNode(Node<Task> node) {
        if (node != null) {
            Node<Task> previousNode = node.getPrevious();
            Node<Task> nextNode = node.getNext();
            if (node == head && nextNode != null) {
                nextNode.setPrevious(null);
                head = nextNode;
                node = null;
                return;
            }
            if (node == tail && previousNode != null) {
                previousNode.setNext(null);
                tail = previousNode;
                node = null;
                return;
            }
            if (node == head && node == tail) {
                head = null;
                tail = null;
                node = null;
                return;
            }
            if (node != tail && node != head) {
                previousNode.setNext(nextNode);
                nextNode.setPrevious(previousNode);
                return;
            }
        }
    }
}
