package org.pyg.cp;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RoomDesk {

    public void newDeskRoom(String[] rooms, int target) {

        String patternString = "^\\[\\d+\\]";
        Pattern p = Pattern.compile(patternString);

        String[] targetPeople = {};
        SortedMap<Integer, List<String>> roomMap = new TreeMap();
        for (int i = 0; i < rooms.length; i++) {
            Matcher m = p.matcher(rooms[i]);
            if (m.find()) {
                String matchingString = m.group();
                String[] roomPeople = rooms[i].replaceAll(patternString, "").split(",");
                int roomNum = Integer.parseInt(matchingString.replaceAll("\\[", "").replaceAll("\\]", ""));
                if (roomNum == target) {
                    targetPeople = roomPeople;
                } else {
                    int keyVal = Math.abs(roomNum - target);
                    List<String> peopleList = Arrays.asList(roomPeople);
                    Collections.reverse(peopleList);
                    if (roomMap.keySet().contains(keyVal)) {
                        roomMap.get(keyVal).addAll(peopleList);
                    } else {
                        roomMap.put(keyVal, peopleList);
                    }
                }
            }
        }

        List<String> roomPeople = roomMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println(roomPeople);

        roomPeople.removeAll(Arrays.asList(targetPeople));

        System.out.println(roomPeople);
    }
}
