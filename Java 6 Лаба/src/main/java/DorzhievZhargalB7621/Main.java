package DorzhievZhargalB7621;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Publication> publications = new ArrayList<>();

        publications.add(new Book("Книга", "Автор книги"));
        publications.add(new Journal("Журнал", "2024-12"));
        publications.add(new Manual("Учебное пособие", "Тема учебного пособия"));

        for (Publication pub : publications) {
            pub.createContract("Клиент 1");
            pub.editContent("Издание редактировано");
            pub.format();
            pub.sendToPrint();
            pub.cancel();
            pub.pay();
            pub.resume();
            pub.close();
        }
    }
}