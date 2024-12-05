package DorzhievZhargalB7621;

interface Publication {
    void createContract(String clientName);
    void editContent(String content);
    void format();
    void sendToPrint();
    void cancel();
    void pay();
    void resume();
    void close();
}
