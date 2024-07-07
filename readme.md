# Library Management System (Java)

## Features

### For Librarians

- Register and remove members.
- Add and remove books from the library.
- View all available books.

### For Members

- List available books.
- List borrowed books.
- Issue books (if eligible).
- Return books.

## Code Structure

Classes
Book
Book represents a book in the library.
It has private fields for book ID, title, author, and total copies.
Provides methods to get book information, check available copies, decrease available copies when borrowed, and increase available copies when returned.
Member
Member represents a library member.
It has private fields for name, age, college ID, phone number, borrowed books, and balance due.
Provides methods to access member information, borrow and return books, check if a member can borrow more books, and handle balance dues.
LibraryManagementSystem
LibraryManagementSystem is the main class that orchestrates the library operations.
It manages lists of books and members and keeps track of the currently logged-in member.
Provides methods for adding/removing books and members, logging in as a member, borrowing/returning books, listing books and members, and calculating fines for late returns.
Offers distinct menus for Librarian and Member functionalities.
The run() method runs the main loop of the application, allowing users to interact with the system.

## Usages

Start the application by running the main method in the LibraryManagementSystem class.
You will be prompted to choose your user role: Librarian or Member.
Librarians can add/remove books and members, list books and members, and calculate fines.
Members can log in, borrow/return books, list books and members, and calculate fines.
Follow the on-screen prompts to perform the desired operations.

## Features

Librarians can manage the library's inventory by adding and removing books, as well as registering and removing members.
Members can log in and borrow/return books, provided they have not exceeded their borrowing limit and have no outstanding dues.
Both Librarians and Members can list available books and registered members.
A fine system calculates dues for late returns.

## Usage

### Librarian Menu

1. Register a member.
2. Remove a member.
3. Add a book.
4. Remove a book.
5. View all books.
6. Exit as librarian.

### Member Menu

1. List available books.
2. List my books.
3. Issue a book.
4. Return a book.
5. Exit as a member.
   (i have not differentated between a entering as a member or a librarian but the above methods can be carried out irrespective of user being librarian or member. )

## Error Handling

The code includes error handling for invalid inputs, such as incorrect menu choices, incorrect method calling like calculating fine when book is not borrowed. It provides informative error messages to guide users.

## Contributors

- Kratik Gupta

## Acknowledgments

This project was inspired by the need for an efficient library management system and was created to demonstrate good coding practices in Java.
