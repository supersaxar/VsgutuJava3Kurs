package DorzhievZhargalB7621;

class Manual implements Publication {
    private String title;
    private String subject;

    public Manual(String title, String subject) {
        this.title = title;
        this.subject = subject;
    }

    @Override
    public void createContract(String clientName) {
        System.out.println("Договор создан для учебного пособия: " + title + ", Клиент: " + clientName);
    }

    @Override
    public void editContent(String content) {
        System.out.println("Учебное пособие редактирована: " + content);
    }

    @Override
    public void format() {
        System.out.println("Учебное пособие сверстано: " + title);
    }

    @Override
    public void sendToPrint() {
        System.out.println("Учебное пособие отправлено на печать: " + title);
    }

    @Override
    public void cancel() {
        System.out.println("Публикация учебного пособия отменено: " + title);
    }

    @Override
    public void pay() {
        System.out.println("Учебное пособие оплачено: " + title);
    }

    @Override
    public void resume() {
        System.out.println("Издание учебного пособия возобновлено: " + title);
    }

    @Override
    public void close() {
        System.out.println("Издание учебного пособия закрыто: " + title);
    }
}