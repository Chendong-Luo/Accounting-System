# My Personal Project

## A Introduction

Q&A:
- ##### What will the application do?  
*This app is a financial record keeping app which is designed for users to record their transactions.* 
- *The app allows users to record their transaction(income or cost) in the app.*
- *The app allows users to delete a transaction(income or cost) in the app.*
- *The app will show a summary of income and cost of a specific year or of a specific month in a year.*
- *The app allows users to select a month in a year and view the transactions in detail.*
- *The app allows users to set a yearly or monthly budget, and the budget will show up when they check
the statement in a year of a month in a year.*


             

- ##### Who will use it?
*The indented uses are university students because they are the first time leaving their homes 
and parents for schools. They need to take care of their own lives starting from managing their money.
Of course, anyone who is not a student is also encouraged to use it.*



- ##### Why is this project of interest to you?
*The number of potential users is big. I am currently a student from a university. To some extent,
I know the necessity of this app, and I know the importance of balancing the incomes and bills.
Some students mess their lives by overlooking their money management, So recording transactions and 
setting the budget as a preparation for the future is essential for good lives in schools.*




## B User Stories
- As a user, I want to be able to add a transaction
- As a user, I want to be able to delete a transaction
- As a user, I want to be able to view the transactions
- As a user, I want to be able to calculate the total cost and income of transactions of a selected year
- As a user, I want to be able to save transactions to a file
- As a user, I want to be able to load transactions from a file when program starts

## C Instructions for Grader
- You can generate the first required event by clicking "Click here to add" in the menu bar on the top left of the frame
and you can see there are two items on the menu. The one is "Add Income", and the other one is "Add Cost". You can click
any one of them to add the transaction. When you click them, there will be a pop-up window to collect the data of the
transaction you want to add. Double click each cell under "Year", "Month", "Date", "Amount", "Description", and type
numbers for the first four cells, and anything for the last one. **Make sure you type "Enter" on your keyboard after you 
finish each cell, or the cell will not record things you enter**. After you finish all cells, click the "AddCost" or 
"AddIncome" button.

- You can generate the second required event by clicking one row of the transactions on the panel and
click the "Delete" button at the bottom of the frame.

- You can trigger my audio component by clicking buttons in my program. Most of the buttons in my program have their own
special sounds. And if you want, you can locate my visual component by successfully adding transactions. There will be a 
pop-up window containing a picture.

- You can save the state of my application by clicking the "SaveQuit" button.

- You can reload the state of my application by clicking the "Reload" button. **Notice that when you start my project 
there is a pop-up message to remind you to click the "Reload" button before you click other things. This is because the 
reload function is not automatic, you should reload old data saved by clicking "Reload" button before you generate other
functions. If you click other buttons without clicking the "Reload" button first, the program will assume you want 
a new file, so the "Reload" button will disappear, and old data saved may be lost.**


## D Phase 4: Task 2
- The option I choose to complete is the second one "Include a type hierarchy". Those classes are in the ui package,
The super class is the "Bill" class, and the subclasses are "MonthBill" and "YearBill" class. Please focus on my last 
override method, the actionPerformed method. If you still think they are not fulfill the requirements, 
please look at my completion of "Test and design a class that is robust" option, the method in the "Statement"
class in the model package called "addTransaction" throws an exception, and the tests can be found in the
"StatementTest" class in the test package, and please focus on the last few tests.


## E Phase 4: Task 3
- Improved the cohesion. I separated my audio component from the "TransactionRecorder" class which is my GUI class,
because I wanted my GUI class more focused rather than dealing with multiple functions at the same time. I therefore 
made a new class "PlayButtonMusic" focus on playing button musics in the ui package. In this way I could delete 
all "private static final..." lines of sound files in the "TransactionRecorder" class, and I put all of them into 
the new class and made them public. Other classes in the ui package used to call 
"transactionRecorder.soundForButton(...)" to play sounds. Now they are able to initialize a new PlayButtonMusic(...) 
to replace that. Therefore, the "TransactionRecorder"(GUI) class becomes more cohesive.
 
- Reduced coupling. In each of the "AddCost" and the "AddIncome" class, there were methods named "actionPerformed" which 
overrode the abstract method "actionPerformed" in the super class, the "Bill" class. Both "actionPerformed" methods 
in two subclasses were similar, and the function of each of them was getting information of a transaction from the user, 
and adding the new transaction to the list of transaction, and then printing the new list of transaction on the panel 
of GUI. As a result, I put the duplicated code into the abstract method in their shared super class.

- Reduced coupling. Noticed that both the "TransactionRecorder" class and the "MonthBill" class had a nearly same
function, which was "fillTableModel(...)". The function of it is filling the panel with a list of 
transaction. The only difference between these two methods was the parameter passed in. If one day 
I want to change the sequence of transactions filled in, I may forget to change the methods in the both places. So, in 
order to reduce coupling, I deleted the "fillTableModel(...)" in the "MonthBill" class, and changed the parameter 
passed in in the "TransactionRecorder" class into the shared super type which is a list. Therefore, when I want to use
"fillTableModel(...)" method in "MonthBill" class, I will call "transactionRecorder.fillTableModel(...)".

- In the course of constructing my project, I found that a good way to improve cohesion and reduce the coupling for the
code is setting up type hierarchy. From phase 1 to phase 4, I was continuously thinking about setting appropriate 
type hierarchy to reduce coupling. As you can see, most of my packages have at least one abstract class or interface. 