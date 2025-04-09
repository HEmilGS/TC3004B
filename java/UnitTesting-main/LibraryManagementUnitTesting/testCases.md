### **Clase: Library**

| Test Case ID | Unit Test Case Name             | Test Scenario                                                                 | Input                                                                 | Expected Outcome                     | Remarks                     |
|--------------|----------------------------------|-------------------------------------------------------------------------------|-----------------------------------------------------------------------|--------------------------------------|-----------------------------|
| LIB-001      | testCheckOutBookSuccess         | Successful book checkout by patron                                            | Book, Patron, 14 days                                                | true, book.isCheckedOut() = true     | Happy path                  |
| LIB-002      | testReturnBookSuccess           | Successful book return by patron                                              | Book checked out by Patron                                            | true, book.isCheckedOut() = false    | Happy path                  |
| LIB-003      | testCheckOutNonexistentBook     | Attempt to check out non-existent book                                        | Non-existent Book, Patron                                             | false                                 | Negative case               |
| LIB-004      | testReturnBookNotCheckedOut     | Attempt to return book not checked out                                        | Patron with no checked out books                                      | false                                 | Negative case               |
| FINE-001     | testNoFineWhenNotOverdue        | Calculate fine when book is not overdue                                       | Book not overdue                                                     | 0.0                                  | Happy path                  |
| FINE-002     | testFineCalculationWhenOverdue  | Calculate fine when book is overdue                                           | Book overdue by 5 days                                               | 2.50                                 | Business logic              |
| FINE-003     | testNoFineForNotCheckedOutBook  | Calculate fine when patron has no checked out books                           | Patron with no books                                                 | 0.0                                  | Edge case                   |
| LIST-001     | testListAvailableBooks          | Verify listing of available books                                             | Multiple books, some checked out                                      | Correct available books count        | State verification          |
| LIST-002     | testListPatrons                 | Verify listing of library patrons                                             | Multiple patrons                                                     | Correct patron count and content     | Basic functionality         |
| DUP-001      | testAddDuplicateBook            | Attempt to add duplicate book to library                                      | Same book added twice                                                 | Only one book in library             | Business rule               |
| DUP-002      | testRemoveBook                  | Verify book removal from library                                              | Book added then removed                                               | Empty book list                      | Basic functionality         |
| EDGE-001     | testCheckOutAlreadyCheckedOutBook | Attempt to check out already checked out book                                 | Book checked out by another patron                                   | false                                 | Concurrency-like case       |
| EDGE-002     | testReturnBookByWrongPatron      | Attempt to return book by wrong patron                                        | Book checked out by different patron                                  | false                                 | Authorization case          |

---

### **Clase: Book**

| Test Case ID | Unit Test Case Name             | Test Scenario                                                                 | Input                                                                 | Expected Outcome                     | Remarks                     |
|--------------|----------------------------------|-------------------------------------------------------------------------------|-----------------------------------------------------------------------|--------------------------------------|-----------------------------|
| BOOK-001     | testBookCreation                | Verify book creation with correct attributes                                  | Title, Author                                                         | Correct title, author, initial state | Basic functionality         |
| BOOK-002     | testCheckOutAndReturn           | Verify book checkout and return functionality                                 | 14 days checkout period                                               | Correct state changes                | State verification          |
| BOOK-003     | testSetDueDateWhileCheckedOut   | Set due date while book is checked out                                        | New due date                                                          | Due date updated                     | State-dependent operation   |
| BOOK-004     | testSetDueDateWhenNotCheckedOut | Attempt to set due date when book not checked out                             | New due date                                                          | Throws IllegalStateException         | Exception case              |
| BEDGE-001    | testCheckOutWithZeroDays        | Check out book with zero days period                                          | 0 days checkout                                                      | Due date = today                     | Boundary case               |
| BEDGE-002    | testCheckOutWithNegativeDays    | Attempt to check out with negative days period                                | -1 day checkout                                                      | Throws IllegalArgumentException      | Invalid input case          |

---

### **Clase: Patron**

| Test Case ID | Unit Test Case Name             | Test Scenario                                                                 | Input                                                                 | Expected Outcome                     | Remarks                     |
|--------------|----------------------------------|-------------------------------------------------------------------------------|-----------------------------------------------------------------------|--------------------------------------|-----------------------------|
| PAT-001      | testPatronCreation              | Verify patron creation with correct attributes                                | Patron name                                                           | Correct name, empty book list        | Basic functionality         |
| PAT-002      | testCheckOutAndReturnBook       | Verify patron's book checkout and return operations                           | Multiple books                                                        | Correct book list changes            | State verification          |
| PAT-003      | testHasCheckedOutBook           | Verify hasCheckedOutBook method                                               | Book checked out/not checked out                                      | Correct boolean result               | Query method test           |

---

