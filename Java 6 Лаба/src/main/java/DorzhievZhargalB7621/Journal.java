package DorzhievZhargalB7621;

class Journal extends PublicationBase {
    private String issue;

    public Journal(String title, String issue) {
        super(title);
        this.issue = issue;
    }

    @Override
    public void format() {
        super.format();
        System.out.println("Журнал сверстан для выпуска: " + issue);
    }

}
