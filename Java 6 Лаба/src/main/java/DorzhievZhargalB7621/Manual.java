package DorzhievZhargalB7621;

class Manual extends PublicationBase {
    private String subject;

    public Manual(String title, String subject) {
        super(title);
        this.subject = subject;
    }

    @Override
    public void format() {
        super.format();
        System.out.println("Учебное пособие сверстано по теме: " + subject);
    }
}
