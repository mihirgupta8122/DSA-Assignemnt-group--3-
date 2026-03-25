package ADTStack;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;

public class StackImplementation<T> implements StackInterface<T> {
	ArrayList<T> items = new ArrayList<>();

	@Override
	public void push(T newEntry) {
		items.add(newEntry);
	}

	@Override
	public T pop() throws EmptyStackException {
		int totalItems = items.size();
		if (totalItems == 0) {
			throw new EmptyStackException();
		}
		T toReturn = items.get(totalItems - 1);
		items.remove(totalItems - 1);
		return toReturn;
	}

	@Override
	public T peek() throws EmptyStackException {
		int totalItems = items.size();
		if (totalItems == 0) {
			throw new EmptyStackException();
		}
		return items.get(totalItems - 1);
	}

	@Override
	public boolean isEmpty() {
		int totalItems = items.size();
		return (totalItems == 0);
	}

	@Override
	public void clear() {
		items.clear();
	}

	public Integer getTotal() {
		return (items.size());
	}

	public static void main(String[] args) {
		System.out.println("Testing Stack Implementation");
		StackImplementation<String> textInputs = new StackImplementation<>();
		Scanner getInput = new Scanner(System.in);
		while (true) {
			System.out
					.println(
							"Enter something to add to the stack,\n'print' to print,\n'count' to get the total number of items in the stack,\n'remove' to remove the top item from the stack\n'break' to end program");
			String input = getInput.nextLine().trim();
			if (input.isEmpty()) {
				continue;
			} else if (input.equalsIgnoreCase("print")) {
				System.out.println(textInputs.peek());
			} else if (input.equalsIgnoreCase("remove")) {
				System.out.println(textInputs.pop());
			} else if (input.equalsIgnoreCase("count")) {
				System.out.println(textInputs.getTotal());
			} else if (input.equalsIgnoreCase("break")) {
				break;
			} else {
				System.out.println(input);
				try {
					textInputs.push("Added " + input);
				} catch (NumberFormatException e) {
					System.out.println("Use a whole number, no letters nor decimals allowed");
				}
			}
		}
		getInput.close();
	}

}
