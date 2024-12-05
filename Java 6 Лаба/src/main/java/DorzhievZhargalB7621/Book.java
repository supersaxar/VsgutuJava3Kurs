package DorzhievZhargalB7621;

class Book extends PublicationBase {
    private String author;

    public Book(String title, String author) {
        super(title);
        this.author = author;
    }

    @Override
    public void format() {
        super.format();
        System.out.println("Книга свертстана с авторскими деталями: " + author);
    }
}
