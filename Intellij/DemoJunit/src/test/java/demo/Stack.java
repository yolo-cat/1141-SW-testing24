package demo;

import java.util.EmptyStackException;
import java.util.LinkedList;

public class Stack<T> {
    private LinkedList<T> elements = new LinkedList<>();

    // Pushes an element onto the stack
    public void push(T element) {
        elements.addFirst(element);
    }

    // Pops the top element from the stack
    public T pop() {
        if (elements.isEmpty()) {
            throw new EmptyStackException();
        }
        System.out.println(elements);
        return elements.removeFirst();
    }

    // Peeks at the top element of the stack without removing it
    public T peek() {
        if (elements.isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.getFirst();
    }

    // Checks if the stack is empty
    public boolean isEmpty() {
        return elements.isEmpty();
    }
}
