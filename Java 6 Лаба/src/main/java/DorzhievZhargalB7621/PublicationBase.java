package DorzhievZhargalB7621;

abstract class PublicationBase implements Publication {
    protected String title;
    protected boolean isFormatted;
    protected boolean isPaid;
    protected boolean isSentToPrint;
    protected boolean isCanceled;
    protected boolean isClosed;

    public PublicationBase(String title) {
        this.title = title;
        this.isFormatted = false;
        this.isPaid = false;
        this.isSentToPrint = false;
        this.isCanceled = false;
        this.isClosed = false;
    }
    @Override
    public void createContract(String clientName) {
        System.out.println("Договор издания создан: " + title + ", Клиент: " + clientName);
    }

    @Override
    public void editContent(String content) {
        System.out.println("Издание редактировано: " + content);
    }

    @Override
    public void format() {
        this.isFormatted = true;
        System.out.println("Издание сверстана: " + title);
    }

    @Override
    public void sendToPrint() {
        if (isFormatted && !isSentToPrint) {
            this.isSentToPrint = true;
            System.out.println("Издание отправлена на печать: " + title);
        } else if (isSentToPrint) {
            System.out.println("Издание уже отправлено в печать: " + title);
        } else {
            System.out.println("Невозможно отправить в печать. Издание не сверстано: " + title);
        }
    }

    @Override
    public void cancel() {
        if (!isCanceled) {
            this.isCanceled = true;
            System.out.println("Издание отменено: " + title);
        } else {
            System.out.println("Издание уже отменено: " + title);
        }
    }

    @Override
    public void pay() {
        if (!isPaid) {
            this.isPaid = true;
            System.out.println("Издание оплачено: " + title);
        } else {
            System.out.println("Издание уже оплачено: " + title);
        }
    }

    @Override
    public void resume() {
        if (isPaid && isCanceled) {
            this.isCanceled = false;
            System.out.println("Издание возобновлено: " + title);
        } else if (!isPaid) {
            System.out.println("Необходима оплата чтобы продолжить издание: " + title);
        } else {
            System.out.println("Издание не отменено и не нуждается в возобновлении: " + title);
        }
    }

    @Override
    public void close() {
        if (!isClosed) {
            this.isClosed = true;
            System.out.println("Издание закрыто: " + title);
        } else {
            System.out.println("Издание уже закрыто");
        }
    }
}
