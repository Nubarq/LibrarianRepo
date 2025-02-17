INSERT INTO authority_list (authority, librarian, student) VALUES
                                                               ('DELETE_BOOK', 1, 0),
                                                               ('EDIT_BOOK', 1, 0),
                                                               ('SEARCH_BOOK', 1, 0),
                                                               ('ADD_BOOK', 1, 0),

                                                               ('GET_BORROWED_BOOKS_BY_STUDENT', 1, 1),
                                                               ('GET_BORROWED_BOOKS_BY_BOOK', 1, 1),
                                                               ('GIVE_BOOK_TO_STUDENT', 1, 0),
                                                               ('GET_BORROWED_BOOKS', 1, 0),
                                                               ('GET_RETURNED_BOOKS', 1, 0),
                                                               ('RETURN_BOOK', 0, 1),

                                                               ('DELETE_STUDENT', 1, 0),
                                                               ('EDIT_STUDENT', 1, 0),
                                                               ('SEARCH_STUDENTS', 1, 0),
                                                               ('ADD_STUDENT', 1, 0);

-- Insert users
INSERT INTO users (email, password, created_at, updated_at) VALUES
                                                                           ('librarian1@gmail.com', '$2a$12$Fepv69GcYqnJTHIYN3xf7upIdjuzWuJai/Vha0AfPlDSrR5C5QErC', '2024-07-15 10:30:00', '2024-07-20 12:00:00'), --1234
                                                                           ('student1@gmail.com', '$2a$12$Fepv69GcYqnJTHIYN3xf7upIdjuzWuJai/Vha0AfPlDSrR5C5QErC', '2024-06-10 09:15:00', '2024-06-18 14:45:00');
INSERT INTO authorities (user_id, authority)
SELECT 1, authority FROM authority_list WHERE librarian = 1;

INSERT INTO authorities (user_id, authority)
SELECT 2, authority FROM authority_list WHERE student = 1;

-- Insert librarians
INSERT INTO librarians (name, email, created_at, user_id) VALUES
    ('Alice Johnson', 'librarian1@gmail.com', '2024-05-22 11:00:00', 1);

INSERT INTO students (name, email, created_at, user_id) VALUES
    ('Alice Johnson', 'student1@gmail.com', '2024-05-22 11:00:00', 2);

-- Insert books
INSERT INTO books (title, author, isbn, publication_year, quantity, created_at, updated_at) VALUES
                                                                                               ('The Catcher in the Rye', 'J.D. Salinger', '9780316769488', 1951, 5, '2024-08-01 13:20:00', '2024-08-10 16:45:00'),
                                                                                               ('To Kill a Mockingbird', 'Harper Lee', '9780061120084', 1960, 3, '2024-07-25 14:10:00', '2024-08-05 10:30:00'),
                                                                                               ('1984', 'George Orwell', '9780451524935', 1949, 4, '2024-06-15 09:30:00', '2024-07-01 17:20:00');

-- Insert borrowed books
INSERT INTO borrowed_books (book_id, student_id, borrowed_date, due_date, returned_date, is_overdue) VALUES
                                                                                                       (1, 1, '2024-09-02 08:00:00', '2024-09-16 08:00:00', NULL, FALSE);

-- Insert returned books
INSERT INTO returned_books (book_id, student_id, returned_date, fine_amount) VALUES
                                                                               (1, 1, '2024-09-18 11:30:00', 0.00);
