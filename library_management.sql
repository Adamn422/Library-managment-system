CREATE DATABASE library_management;

USE library_management;

CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    ISBN VARCHAR(20) UNIQUE NOT NULL,
    isAvailable BOOLEAN DEFAULT TRUE
);

CREATE TABLE borrowers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    borrowerId VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    contactDetails VARCHAR(255)
);

CREATE TABLE transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bookId INT,
    borrowerId INT,
    issueDate DATE NOT NULL,
    returnDate DATE,
    FOREIGN KEY (bookId) REFERENCES books(id),
    FOREIGN KEY (borrowerId) REFERENCES borrowers(id)
);
