package ex04_03;

public interface LinkedList {
	int getObjectNumber();
	Object getObject();
	void setObject(Object target);
	LinkedList getNextObject();
	void setNextObject(LinkedListImpl target);
}
