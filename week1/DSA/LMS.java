import java.util.Arrays;

public class LMS {
    public static void main(String[] args) {
        LibMS lib = new LibMS();
        lib.linearSearch("Book5");
        Arrays.sort(lib.books);
        lib.binarySearch(0, lib.maxsize, "Book6");
    }    
}

class Book implements Comparable<Book>{
    private static int autoGenId = 100;
    int bookId;
    String title, author;
    Book(String title, String author) {
        this.bookId = autoGenId++;
        this.title = title;
        this.author = author;
    }

    void show() {
        System.out.printf("Book ID : %d, Title : %s, Author : %s\n", this.bookId, this.title, this.author);
    }

    public int compareTo(Book b) {
        return this.title.compareTo(b.title);
    }
}

class LibMS {
    Book[] books;
    int maxsize = 20;

    LibMS() {
        books = new Book[maxsize];
        for(int i = 0; i < maxsize; i++) {
            books[i] = new Book("Book" + String.valueOf(i + 1), "Author" + String.valueOf(i + 100));
        }
    }

    void linearSearch(String title) {
        for(int i = 0; i < maxsize; i++) {
            if(books[i].title.equals(title)){
                books[i].show();
                return;
            };
        }
    }

    void binarySearch(int low, int high, String title) {
        if(low < high) {
            int mid = (low + high) / 2;
            if(books[mid].title.equals(title)) {
                books[mid].show();
                return;
            }
            else if(books[mid].title.compareTo(title) > 0) binarySearch(low, mid, title);
            else binarySearch(mid, high, title);
        }
    }

    void print() {
        for(Book book : books) book.show();
    }
}