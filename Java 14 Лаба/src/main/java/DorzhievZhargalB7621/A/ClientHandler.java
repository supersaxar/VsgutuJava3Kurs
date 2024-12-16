package DorzhievZhargalB7621.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {
    private Socket socket;
    private List<ClientHandler> clients;
    private PrintWriter out;
    private BufferedReader in;
    private String clientName;

    public ClientHandler(Socket socket, List<ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sendMessage("Введи свое имя:");
            clientName = in.readLine();
            sendMessage("Добро пожаловать " + clientName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            if (clients.size() > 1) {
                sendMessage("Клиенты в онлайне:");
                synchronized (clients) {
                    for (ClientHandler client : clients) {
                        if (client != this) {
                            sendMessage(client.getClientName());
                        }
                    }
                }
            } else {
                sendMessage("Никого нету в онлайне.");
            }

            String message;
            while ((message = in.readLine()) != null) {
                handleClientMessage(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                synchronized (clients) {
                    clients.remove(this);
                    Server.updateClientList();
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleClientMessage(String message) {
        String[] parts = message.split(": ");
        if (parts.length == 2) {
            String targetClientName = parts[0];
            String msg = parts[1];
            synchronized (clients) {
                for (ClientHandler client : clients) {
                    if (client != this && client.getClientName().equals(targetClientName)) {
                        client.sendMessage(clientName + " говорит тебе: " + msg);
                        return;
                    }
                }
            }
            sendMessage("Пользователь не найден");
        } else {
            sendMessage("Неверный ввод. Используй '<ник пользователя>: <сообщение>'");
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public String getClientName() {
        return clientName;
    }
}