package Manager;

class Node<Task> {
    private Task task;
    private Node<Task> next;
    private Node<Task> previous;


    public Node(Task task, Node<Task> previous, Node<Task> next) {
        this.task = task;
        this.previous = previous;
        this.next = next;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Node<Task> getNext() {
        return next;
    }

    public void setNext(Node<Task> next) {
        this.next = next;
    }

    public Node<Task> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<Task> previous) {
        this.previous = previous;
    }
}

