package DorzhievZhargalB7621;

class Book implements Publication {
    private String title;
    private String author;
    private boolean isPaid;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public void createContract(String clientName) {
        System.out.println("Договор создан для книги: " + title + ", Клиент: " + clientName);
    }

    @Override
    public void editContent(String content) {
        System.out.println("Книга редактирована: " + content);
    }

    @Override
    public void format() {
        System.out.println("Книга сверстана: " + title);
    }

    @Override
    public void sendToPrint() {
        System.out.println("Книга отправлена на печать: " + title);
    }

    @Override
    public void cancel() {
        System.out.println("Публикация книги отменена: " + title);
    }

    @Override
    public void pay() {
        this.isPaid = true;
        System.out.println("Книга оплачена: " + title);
    }

    @Override
    public void resume() {
        if (isPaid) {
            System.out.println("Издание книги возоблено: " + title);
        } else {
            System.out.println("Необходима плата для возобновления издания книги: " + title);
        }
    }

    @Override
    public void close() {
        System.out.println("Издание книги закрыто: " + title);
    }
}