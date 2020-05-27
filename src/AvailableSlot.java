/**
 *
 */
public class AvailableSlot {

    public AvailableSlot(int startTime, int endTime, int capacity, String roomNumber) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.roomNumber = roomNumber;
    }

    int startTime;

    int endTime;

    int capacity;

    String roomNumber;

}
