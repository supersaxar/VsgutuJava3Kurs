package DorzhievZhargalB7621;

class Journal implements Publication {
    private String title;
    private String issue;

    public Journal(String title, String issue) {
        this.title = title;
        this.issue = issue;
    }

    @Override
    public void createContract(String clientName) {
        System.out.println("Договор создан для журнала: " + title + ", Клиент: " + clientName);
    }

    @Override
    public void editContent(String content) {
        System.out.println("Журнал редактирован: " + content);
    }

    @Override
    public void format() {
        System.out.println("Журнал сверстан: " + title);
    }

    @Override
    public void sendToPrint() {
        System.out.println("Журнал отправлен на печать: " + title);
    }

    @Override
    public void cancel() {
        System.out.println("Публикация журнала отменена: " + title);
    }

    @Override
    public void pay() {
        System.out.println("Журнал оплачен: " + title);
    }

    @Override
    public void resume() {
        System.out.println("Издание журнала возобновлено: " + title);
    }

    @Override
    public void close() {
        System.out.println("Издание журнала закрыто: " + title);
    }
}