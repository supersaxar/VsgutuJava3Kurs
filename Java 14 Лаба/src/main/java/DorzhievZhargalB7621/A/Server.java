//Доржиев Жаргал Группа Б-762-1 Вариант 1
package DorzhievZhargalB7621.A;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 12345;
    private static final List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        System.out.println("Сервер запущен...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, clients);
                synchronized (clients) {
                    clients.add(clientHandler);
                    updateClientList();
                }
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateClientList() {
        StringBuilder clientNames = new StringBuilder("Пользователи онлайн: ");
        synchronized (clients) {
            for (ClientHandler client : clients) {
                clientNames.append(client.getClientName()).append(", ");
            }
        }
        String clientList = clientNames.length() > 0
                ? clientNames.substring(0, clientNames.length() - 2)
                : "Никого нету онлайн";

        synchronized (clients) {
            for (ClientHandler client : clients) {
                client.sendMessage(clientList);
            }
        }
    }
}