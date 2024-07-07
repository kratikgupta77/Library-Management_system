package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Book {
    private int bookId;
    private String title;
    private String author;
    private int totalCopies;

    public Book(int bookId, String title, String author, int totalCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
    }
    public int getBookId() {
        return bookId;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getAvailableCopies() {
        return totalCopies;
    }

    public void decreaseAvailableCopies() {
        totalCopies--;
    }

    public void increaseAvailableCopies() {
        totalCopies++;
    }
}

class Member {
    private String name;
    private int age;
    private String collegeID;
    private String phoneNumber;
    private List<Book> borrowedBooks;
    private int balanceDue;

    public Member(String name, int age, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.collegeID = "";
        this.borrowedBooks = new ArrayList<>();
        this.balanceDue = 0;
    }

    public String getCollegeID() {
        return collegeID;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public int getBalanceDue() {
        return balanceDue;
    }

    public boolean canBorrowBook() {
        return borrowedBooks.size() < 2;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.decreaseAvailableCopies();
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.increaseAvailableCopies();
    }
}

public class LibraryManagementSystem {
    private List<Book> books;
    private List<Member> members;
    private Member currentMember;
    private Scanner scanner;

    public LibraryManagementSystem() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addBook(int bookId, String title, String author, int totalCopies) {
        Book book = new Book(bookId, title, author, totalCopies);
        books.add(book);
    }

    public void removeBook(int bookId) {
        Book bookToRemove = null;
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                bookToRemove = book;
                break;
            }
        }
        if (bookToRemove != null) {
            books.remove(bookToRemove);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found with the given ID.");
        }
    }

    public void registerMember(String name, int age, String collegeID) {
        Member member = new Member(name, age, collegeID);
        members.add(member);
    }

    public void removeMember(String phone) {
        int a = 0;
        for (Member Member : members) {
            if (Member.getPhoneNumber().equals(phone)) {
                members.remove(Member);
                System.out.println("member removed successfully.");
                a = 1;
                break;
            }
        }
        if (a == 0) {
            System.out.println("member not found with the given ID.");
        }
    }

    public void enterAsMember(String name, String phoneNumber) {
        Member member = null;
        for (Member mem : members) {
            if (mem.getPhoneNumber().equals(phoneNumber)) {
                member = mem;
                break;
            }
        }

        if (member != null) {
            currentMember = member;
            System.out.println("Logged in as " + member.getName());
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }


    public void issueBook(int bookId) {
        if (currentMember == null) {
            System.out.println("Please log in first.");
            return;
        }

        if (currentMember.getBalanceDue() > 0) {
            System.out.println("Please clear your dues before borrowing a book.");
            return;
        }

        // Iterate through the books array to find the book with the matching bookId
        Book foundBook = null;
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                foundBook = book;
                break;
            }
        }

        if (foundBook != null) {
            if (foundBook.getAvailableCopies() > 0) {
                if (currentMember.canBorrowBook()) {
                    currentMember.borrowBook(foundBook);
                    System.out.println("Book borrowed successfully.");
                } else {
                    System.out.println("You have reached the borrowing limit.");
                }
            } else {
                System.out.println("The selected book is not available.");
            }
        } else {
            System.out.println("Invalid book ID.");
        }
    }


    public void returnBook(int bookId) {
        if (currentMember == null) {
            System.out.println("Please log in first.");
            return;
        }

        // Iterate through the books array to find the book with the matching bookId
        Book foundBook = null;
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                foundBook = book;
                break;
            }
        }

        if (foundBook != null) {
            if (currentMember.getBorrowedBooks().contains(foundBook)) {
                currentMember.returnBook(foundBook);
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("You have not borrowed this book.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    public void listBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            System.out.println("ID: " + book.getBookId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor()
                    + ", Available Copies: " + book.getAvailableCopies());
        }
    }

    public void listMembers() {
        System.out.println("Registered Members:");
        for (Member member : members) {
            System.out.println("Name: " + member.getName() + ", Age: " + member.getAge() + ", Phone Number: "
                    + member.getPhoneNumber());
        }
    }

    public void calculateFine(int bookId, int daysLate) {
        if (currentMember == null) {
            System.out.println("Please log in first.");
            return;
        }

        Book foundBook = null;
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                foundBook = book;
                break;
            }
        }

        if (foundBook == null) {
            System.out.println("Book not found.");
            return;
        }

        // Check if the book has been issued by the current member
        if (!currentMember.getBorrowedBooks().contains(foundBook)) {
            System.out.println("You have not issued the book \"" + foundBook.getTitle() + "\".");
            return;
        }

        int dueDays = 10; // Due date is 10 days after borrowing
        int fineAmount = 0;

        if (daysLate > dueDays) {
            // Calculate the fine amount for each day late
            fineAmount = (daysLate - dueDays) * 3; // Assuming 1 day = 1 second
        }

        if (fineAmount > 0) {
            System.out.println("Fine for book \"" + foundBook.getTitle() + "\" is " + fineAmount + " rupees.");
        } else {
            System.out.println("No fine is required to be paid for book \"" + foundBook.getTitle() + "\".");
        }
    }

    public void displayMenu() {
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Register Member");
        System.out.println("4. Remove Member");
        System.out.println("5. Enter as Member");
        System.out.println("6. Issue Book");
        System.out.println("7. Return Book");
        System.out.println("8. List Books");
        System.out.println("9. List Members");
        System.out.println("10. Calculate Fine");
        System.out.println("11. Exit");
    }

    public void run() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (choice == 1) {
                System.out.print("Enter Book ID: ");
                int bookId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter Title: ");
                String title = scanner.nextLine();
                System.out.print("Enter Author: ");
                String author = scanner.nextLine();
                System.out.print("Enter Total Copies: ");
                int totalCopies = scanner.nextInt();
                addBook(bookId, title, author, totalCopies);
            } else if (choice == 2) {
                System.out.print("Enter Book ID to remove: ");
                int bookIdToRemove = scanner.nextInt();
                removeBook(bookIdToRemove);
            } else if (choice == 3) {
                System.out.print("Enter Name: ");
                String memberName = scanner.nextLine();
                System.out.print("Enter Age: ");
                int memberAge = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter Phone Number: ");
                String phoneNumber = scanner.nextLine();
                registerMember(memberName, memberAge, phoneNumber);
            } else if (choice == 4) {
                System.out.print("Enter Phone Number to remove: ");
                String phoneNumberToRemove = scanner.nextLine();
                removeMember(phoneNumberToRemove);
            } else if (choice == 5) {
                System.out.print("Enter Name: ");
                String loginName = scanner.nextLine();
                System.out.print("Enter Phone Number: ");
                String loginPhoneNumber = scanner.nextLine();
                enterAsMember(loginName, loginPhoneNumber);
            } else if (choice == 6) {
                System.out.print("Enter Book ID to borrow: ");
                int bookIdToBorrow = scanner.nextInt();
                issueBook(bookIdToBorrow);
            } else if (choice == 7) {
                System.out.print("Enter Book ID to return: ");
                int bookIdToReturn = scanner.nextInt();
                returnBook(bookIdToReturn);
            } else if (choice == 8) {
                listBooks();
            } else if (choice == 9) {
                listMembers();
            } else if (choice == 10) {
                System.out.print("Enter Book ID: ");
                int bookIdForFine = scanner.nextInt();
                System.out.print("Enter Days Late: ");
                int daysLate = scanner.nextInt();
                calculateFine(bookIdForFine, daysLate);
            } else if (choice == 11) {
                running = false;
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }
        scanner.close();
    }


    public static void main(String[] args) {
        LibraryManagementSystem librarySystem = new LibraryManagementSystem();
        librarySystem.run();
    }
}