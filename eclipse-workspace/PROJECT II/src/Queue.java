public class Queue {

	private int rear, front;
	private Object[] elements;

	Queue(int capacity) {
		elements = new Object[capacity];
		front = 0;
		rear = -1;
	}

	void Enqueue(Object data) {
		if (isFull())
			System.out.println("Queue overflow");
		else {
			rear++;
			elements[rear] = data;
		}
	}

	Object Dequeue() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
			return null;
		} else {
			Object retData = elements[front];
			elements[front] = null;
			front++;
			return retData;
		}
	}

	Object Peek() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
			return null;
		} else {
			Object retData = elements[front];

			return retData;
		}
	}

	boolean isEmpty() {
		return rear < front;
	}

	boolean isFull() {
		return rear + 1 == elements.length;
	}

	int Size() {
		return rear - front + 1;

	}

}