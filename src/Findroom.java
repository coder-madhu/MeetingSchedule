
import java.util.*;
import java.io.*;
import java.io.InputStreamReader;
public class Findroom
{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] meetingRoomsDB = new String[6];
        meetingRoomsDB[1] = "8.23,6,10:00,11:00,14:00,15:00";
        meetingRoomsDB[2] = "8.43,7,11:30,12:30,17:00,17:30";
        meetingRoomsDB[3] = "9.511,9,9:30,10:30,12:00,12:15,15:15,16:15";
        meetingRoomsDB[4] = "9.527,4,9:00,11:00,14:00,16:00";
        meetingRoomsDB[5] = "9.547,8,10:30,11:30,13:30,15:30,16:30,17:30";
        System.out.println("Please enter the input\n");
        String input = br.readLine(); // reading input 5,8,10:30,11:30  5,8,10:30,11:30 # 5 team members, located on the 8th floor, meeting time 10:30 - 11:30
        String[] inputTokens = input.split(","); //split input into token 5 8 10:30 11:30

        List<AvailableSlot> availableSlots = new ArrayList<>();
        for (int i = 0; i < meetingRoomsDB.length; i++) {
            String[] roomInfo = meetingRoomsDB[i].split(",");
            String meetingRoom = roomInfo[0]; // getting meeting room info
            int capacity = Integer.parseInt(roomInfo[1]); // parse and create capacity

            for (int j = 2; j < roomInfo.length - 1; j++) {
                int startTime = changeTimeToNum(roomInfo[j]); // convert start time to int
                int endTime = changeTimeToNum(roomInfo[j + 1]); //convert end time to int
                //System.out.println(startTime + " " + endTime + " " + capacity + " " + meetingRoom);
                availableSlots.add(new AvailableSlot(startTime, endTime, capacity, meetingRoom)); //build available slots and add it to list
                j = j + 1;
            }
        }

        availableSlots.sort(new Comparator<AvailableSlot>() { // sort with the start time and end time
            @Override
            public int compare(AvailableSlot o1, AvailableSlot o2) {
                if (o1.startTime != o2.startTime) {
                    return o1.startTime - o2.startTime;
                }
                return o2.endTime - o1.endTime;
            }
        });

        boolean foundOneRoom = false;

        List<String> singleMeetingRooms = new ArrayList<>();
        List<String> multiMeetingRooms = new ArrayList<>();
       //parsing and reading the input
        int requiredCapacity = Integer.parseInt(inputTokens[0]);
        int teamFloor = Integer.parseInt(inputTokens[1]);
        int requiredStartTime = changeTimeToNum(inputTokens[2]);
        int requiredEndTime = changeTimeToNum(inputTokens[3]);

        int currentStartTime = requiredStartTime;

        for (int i = 0; i < availableSlots.size(); i++) { //iterating the availableslots
            AvailableSlot currentSlot = availableSlots.get(i);
            //System.out.println(currentSlot.startTime + " " + currentSlot.endTime + " " + currentSlot.capacity + " " + currentSlot.roomNumber);
            if (currentSlot.capacity >= requiredCapacity) {  //comparing with the input
                if (currentSlot.startTime <= requiredStartTime && currentSlot.endTime >= requiredEndTime) {  //comparing with the input
                    foundOneRoom = true; //setting the found flag
                    singleMeetingRooms.add(currentSlot.roomNumber); // adding to singlemeeting list
                } else if (!foundOneRoom && (currentSlot.startTime <= currentStartTime && currentSlot.endTime >= currentStartTime && currentStartTime <= requiredEndTime)) { // if not found single room add all rooms that can fit
                    multiMeetingRooms.add(currentSlot.roomNumber);
                    currentStartTime = currentSlot.endTime;
                }
            }
        }

        if (foundOneRoom) {  // if found single room
            int floorDifference = Integer.MAX_VALUE;
            String closestMeetingRoom = null;
            for (int i = 0; i < singleMeetingRooms.size(); i++) {
                String meetingRoom = singleMeetingRooms.get(i);
                //System.out.println(meetingRoom);
                String[] meetingRoomSplit = meetingRoom.split("\\.");
                String meetingRoomFloor = meetingRoomSplit[0];
                //System.out.println(meetingRoomFloor);
                int meetingFloor = Integer.parseInt(meetingRoomSplit[0]);
                if (floorDifference > Math.abs(teamFloor - meetingFloor)) {  //find the closest
                    floorDifference = Math.abs(teamFloor - meetingFloor);
                    closestMeetingRoom = meetingRoom;
                }
            }
            System.out.println("The closest meeting room is " + closestMeetingRoom);
        } else if (currentStartTime >= requiredEndTime) {
            System.out.print("Single meeting room not found, instead can use all these conference rooms to split your meeting ");
            for (int i = 0; i < multiMeetingRooms.size(); i++) {  // print all multiple meeting rooms
                System.out.print(multiMeetingRooms.get(i) + " ");
            }
        } else {
            System.out.println("No single or multi meeting room found");
        }
    }

    private static int changeTimeToNum(String input) {
        String[] inputSplit = input.split(":");
        int output = (100*Integer.parseInt(inputSplit[0])) + Integer.parseInt(inputSplit[1]);
        return output;
    }
}

