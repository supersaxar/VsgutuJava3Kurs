package DorzhievZhargalB7621.B;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class User_Interface implements Serializable {
    Map mapUser, mapUser2;
    boolean mode;
    Client user;
    String name;

    public User_Interface(String name) {
        this.name = name;
        mapUser = new Map(this.name);
    }

    public void newGame() {
        while (true) {
            try {
                System.out.println("Введите любую кнопку");
                Scanner scan = new Scanner(System.in);
                scan.nextLine();
                mode = false;
                break;
            }
            catch (InputMismatchException _)
            {}
        }
        try {
            user = new Client(name);
            user.connect();
            user.send(name);
            System.out.println(user.receive().toString());
            mapUser2 = new Map(user.receive().toString());
            mapUser.map = (char[][]) user.receive();
            mapUser2.map = (char[][]) user.receive();
            web_game(user);
        } catch (NullPointerException e) {
            System.out.println("Сервер вырублен. Нужно запустить сервер.");
            newGame();
        }
    }

    public void web_game(Client user) {
        int x, y;
        String str;
        Scanner scan;
        mapUser.mapOut();
        mapUser2.mapOut(true);
        try {
            while (true) {
                scan = new Scanner(System.in);
                str = (String) user.receive();
                if (!str.equals("Введите координаты ячейки (x, y): ")) System.out.println(str);
                else {
                    System.out.print(str);
                    x = scan.nextInt();
                    y = scan.nextInt();
                    user.send(new int[]{x, y});
                }
                if (str.equals(mapUser.name + " попал в корабль!") | str.equals(mapUser.name + " уничтожил корабль!") | str.equals(mapUser.name + " промахнулся!")) {
                    mapUser2.map = (char[][]) user.receive();
                    mapUser.mapOut();
                    mapUser2.mapOut(true);
                } else if (str.equals(mapUser2.name + " попал в корабль!") | str.equals(mapUser2.name + " уничтожил корабль!") | str.equals(mapUser2.name + " промахнулся!")) {
                    mapUser.map = (char[][]) user.receive();
                    mapUser.mapOut();
                    mapUser2.mapOut(true);
                } else if ((str.equals(mapUser.name + " выиграл!!! Игра окончена!")) | (str.equals(mapUser2.name + " выиграл!!! Игра окончена!")))
                    break;
            }
        }
        catch (NullPointerException e) {e.printStackTrace();}
    }

    public static boolean isMapEmpty(Map map) {
        for (int i = 0; i < map.map.length; i++) {
            for (int j = 0; j < map.map[i].length; j++) {
                if (map.map[i][j] == 'S')
                    return false;
            }
        }
        return true;
    }

    public static boolean isKilled(Map map, int x, int y) {
        for (int i = 0; i < map.listships.length; i++) {
            for (int j = 0; j < map.listships[i].size; j++) {
                if (map.listships[i].coords[j][0] == x & map.listships[i].coords[j][1] == y)
                    map.listships[i].hit++;
            }
            if (map.listships[i].hit == map.listships[i].size) {
                for (int k = map.listships[i].xlu; k < map.listships[i].xrd; k++) {
                    for (int u = map.listships[i].ylu; u < map.listships[i].yrd; u++) {
                        if (k >= 0 & k < map.map.length & u >= 0 & u < map.map.length)
                            if (map.map[k][u] != '˟')
                                map.map[k][u] = '·';
                    }
                }
                map.listships[i].hit = -1;
                return true;
            }
        }
        return false;
    }
}