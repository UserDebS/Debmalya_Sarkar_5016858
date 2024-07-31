//Question 6

//Linear search is a search algorithm that traverse the entire array to find an element. Here, if the given element matches with the current element, it will return the index of the current element. This process has a time complexity of O(n)

//Binary search on the other hand is a search algorithm that follows the divide and conquer approach. It doesn't traverse the entire array, instead it divides the entire array into two by directly jumping to the middle element, then again repeats the process by jumping to the middle of current middle and lowest or highest index, depending on the current middle element(if it is greater or lesser than the searched element). If greater it will move to the left, else right. If same, then it will return the middle index. Time complexity is O(logn)
//**For this method to work, the array needs to be in a sorted manner.
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

    //Time complextiy of linear search is O(n) and binary search is O(logn).

    //For a collection of huge size linear search won't be efficient. Traversing the entire collection will take a lot of time. So for this case the better approach would be to use binary search only if the collection is in a sorted order by the searched attribute.
}