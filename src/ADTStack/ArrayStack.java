package ADTStack;

import java.util.EmptyStackException;

/**
 * An array-based implementation of the StackInterface.
 *
 * @param <T> The type of elements stored in the stack
 */
public class ArrayStack<T> implements StackInterface<T> {

    // Default initial capacity of the stack
    private static final int DEFAULT_CAPACITY = 10;

    // Array to store stack elements
    private T[] stack;

    // Index of the top element in the stack
    private int topIndex;

    /**
     * Constructor that creates an empty stack
     */
    public ArrayStack() {
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[]) new Object[DEFAULT_CAPACITY];
        stack = tempStack;
        topIndex = -1; // Stack is empty when topIndex is -1
    }

    /**
     * Adds a new entry to the top of the stack
     *
     * @param newEntry The object to be added to the stack
     */
    @Override
    public void push(T newEntry) {
        ensureCapacity();
        stack[++topIndex] = newEntry;
    }

    /**
     * Removes and returns the top entry of the stack
     *
     * @return The object at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        T top = stack[topIndex];
        stack[topIndex] = null; // Avoid memory leaks
        topIndex--;
        return top;
    }

    /**
     * Retrieves the top entry of the stack without removing it
     *
     * @return The object at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public T peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return stack[topIndex];
    }

    /**
     * Checks whether the stack is empty
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return topIndex < 0;
    }

    /**
     * Removes all entries from the stack
     */
    @Override
    public void clear() {
        while (!isEmpty()) {
            pop();
        }
    }

    /**
     * Ensures that the stack has enough capacity.
     * If full, the stack size is doubled.
     */
    private void ensureCapacity() {
        if (topIndex == stack.length - 1) {
            int newLength = stack.length * 2;

            @SuppressWarnings("unchecked")
            T[] newStack = (T[]) new Object[newLength];

            // Copy old elements to new array
            for (int i = 0; i <= topIndex; i++) {
                newStack[i] = stack[i];
            }

            stack = newStack;
        }
    }
}
