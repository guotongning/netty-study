package com.ning.netty.server.ddz.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 大厅
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0.0
 */
public class Hall {

    private static final Map<String, Room> rooms = new ConcurrentHashMap<>();

    public static void addRoom(Room room) {
        rooms.put(room.getRoomId(), room);
    }

    public static void delRoom(String roomId) {
        rooms.remove(roomId);
    }

    public List<Room> roomList() {
        return new ArrayList<>(rooms.values());
    }
}
