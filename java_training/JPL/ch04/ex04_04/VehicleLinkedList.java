package ex04_04;

public interface VehicleLinkedList {
	int getVehicleNumber();
	Vehicle getVehicle();
	void setVehicle(Vehicle target);
	VehicleLinkedList getNextVehicle();
	void setNextVehicle(VehicleLinkedListImpl target);
}
