//Доржиев Жаргал Группа Б-762-1 Вариант 1
package DorzhievZhargalB7621.B;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;


public class Server extends Thread{
    private static final String HOST = "localhost";
    private static final int PORT = 5005;
    public static ArrayList<Connect> connects = new ArrayList<>();
    private ServerSocket serverSocket;
    private Map MapUser1, MapUser2;

    public static int getPort() { return PORT; }
    public static String getHost() { return HOST; }

    public Server() {
        try {
            InetAddress address = InetAddress.getByName(HOST);
            serverSocket = new ServerSocket(PORT, 0, address);
            System.out.println("Сервер запущен: " + serverSocket.getLocalSocketAddress());
        } catch (IOException e) {e.printStackTrace();}
    }

    public void run() {
        System.out.println("Ожидание игроков");
        while (!isStart()) {
            try {
                Connect clientConnection = new Connect(serverSocket.accept());
                Server.connects.add(clientConnection);
                clientConnection.run();
                System.out.println("Игрок подключился: " + clientConnection.name);
                if (clientConnection.getName().equals("Thread-1")) MapUser1 = new Map(clientConnection.name);
                else if (clientConnection.getName().equals("Thread-2")) MapUser2 = new Map(clientConnection.name);
            } catch (IOException ex) {
                disconnect();
            }
        }
        System.out.println("Игроки подключились");
        MapUser1.randomLoc();
        MapUser2.randomLoc();
        Server.connects.get(0).send(MapUser2.name);
        Server.connects.get(1).send(MapUser1.name);
        Server.connects.get(0).send(MapUser1.getSecure());
        Server.connects.get(0).send(MapUser2.getSecure(true));
        Server.connects.get(1).send(MapUser2.getSecure());
        Server.connects.get(1).send(MapUser1.getSecure(true));
        sendMessageAll("Игра начинается");
        Random rd = new Random();
        boolean first_way = rd.nextBoolean();
        ways(first_way, MapUser1, MapUser2);
        killThreads();
        run();
    }

    private void sendMessageAll(String msg) {
        for(Connect client : connects) {
            client.send(msg);
        }
    }

    private void disconnect() {
        try { serverSocket.close(); }
        catch (IOException _) {}
    }

    private void ways(boolean first_way, Map mapus, Map mapen) {
        if (first_way) sendMessageAll(mapus.name + " ходит первым!");
        else sendMessageAll(mapen.name + " ходит первым!");
        while (true) {
            if (first_way) {
                if (!userWay(mapus, mapen)) break;
                if (isGameOver(mapus, mapen)) break;
                if (!userWay(mapen, mapus)) break;
                if (isGameOver(mapen, mapus)) break;
            }
            else {
                if (!userWay(mapen, mapus)) break;
                if (isGameOver(mapen, mapus)) break;
                if (!userWay(mapus, mapen)) break;
                if (isGameOver(mapus, mapen)) break;
            }
        }
    }

    private boolean isGameOver(Map mapus, Map mapen) {
        if (User_Interface.isMapEmpty(mapen)) {
            sendMessageAll(mapus.name + " выиграл!!! Игра окончена!");
            return true;
        }
        return false;
    }

    private boolean userWay(Map mapus, Map mapen) {
        int x, y;
        int[] coords;
        int index;
        if (mapus.name.equals(MapUser1.name)) index = 0;
        else index = 1;
        sendMessageAll(mapus.name + " ход:");
        Server.connects.get(index).send("Для выхода введите: -1 -1");
        Server.connects.get(index).send("Введите координаты ячейки (x, y): ");
        while (true) {
            try {
                coords = (int[]) Server.connects.get(index).receive();
                x = coords[0];
                y = coords[1];
                if (x == -1 & y == -1) {
                    sendMessageAll(mapen.name + " выиграл!!! Игра окончена!");
                    return false;
                }
                else if (x >= 10 | x < 0 | y >= 10 | y < 0) { throw new InputMismatchException(); }
                else break;
            }
            catch (InputMismatchException e) {
                Server.connects.get(index).send(mapus.name + " ввел некорректные данные!");
                Server.connects.get(index).send("Введите правильные координаты:");
            }
        }
        Server.connects.get(Math.abs(index - 1)).send(mapus.name + " выбрал координаты: " + x + " " + y);
        if (mapen.map[x][y] == 'S') {
            mapen.map[x][y] = '˟';
            if (!User_Interface.isKilled(mapen, x, y))
                sendMessageAll(mapus.name + " попал в корабль!");
            else
                sendMessageAll(mapus.name + " уничтожил корабль!");
            Server.connects.get(index).send(mapen.getSecure(true));
            Server.connects.get(Math.abs(index - 1)).send(mapen.getSecure());
            return userWay(mapus, mapen);
        }
        else if (mapen.map[x][y] == ' ' | mapen.map[x][y] == '·') {
            sendMessageAll(mapus.name + " промахнулся!");
            mapen.map[x][y] = '·';
        }
        else if (mapen.map[x][y] == '˟') {
            sendMessageAll(mapus.name + " промахнулся!");
        }
        Server.connects.get(index).send(mapen.getSecure(true));
        Server.connects.get(Math.abs(index - 1)).send(mapen.getSecure());
        return true;
    }

    private static boolean isStart() { return Server.connects.size() == 2; }

    private static void killThreads() {
        for (Connect conn: Server.connects)
            conn.interrupt();
        Server.connects.clear();
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}