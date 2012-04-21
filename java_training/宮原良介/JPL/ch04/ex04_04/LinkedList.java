package ex04_04;

public interface LinkedList {
	int getObjectNumber();
	Object getObject();
	void setObject(Object target);
	LinkedList getNextObject();
	void setNextObject(LinkedListImpl target);
}
