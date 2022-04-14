Ciaran MacDermott, C20384993

Java Assignment, Search Engine ReadMe file.

Classes:

Control.java
The control class of the project. This is where the SearchEngineGUI object called "searchGUIobject" is created. 
It contains a main function to do this.

SearchEngineGUI.java
This is where the GUI code of the search engine is. The JFrame and components are all created and set up here. It contains
functions for displaying results data and chosen search parameters after every update. A FileProcessor object 
is made in the constructor of the class called "fProcessor". Input is taken using actionlisteners and passed to the fProcessor
object functions. The search is performed when the Search button is clicked.


FileProcessor.java
Contains methods for comparing file(s) to the search term(s) and parameters the user has entered. The main function is 
compareString(). This is where comparisons between files and search terms are carried out. The setters also contain error checking.
setFileName() will default to setting the chosen filename string as "" if the entered name can't be found. compareString() will
check for this when deciding whether to search one file in the directory or all of them.
After the files have been searched, the data found on each one is stored in a separate TextFile object. Each TextFile object is then
stored in an ArrayList and passed back to the searchGUIobject object.

TextFile.java
Represents each textfile read as an object, allowing them to be stored in an ArrayList. Stores the file name, number of matches
found in the file, and what percentage of the words in the file were search terms. The setMatchPercentage() function uses the
Maths round() function to prevent long decimal places.



2.) Core functionality
This project presents the user with a GUI. The GUI contains a results area to display search results, with the 
most relevant files being shown at the top. There is a search parameters area which shows how the search will be performed.
The file can be selected in the file text field. The search terms are entered in the search terms text field. The search button 
will begin the search, but only if search term(s) have been entered. 


3.) Optional functionality
The user has 4 options for how to match the search terms with the words in the file: Separate search, Combined search, Separate 
with case matching, and combined with case matching.

Separate: Each search term will be searched for in any case, e.g. the term "day" will match with "Day","dAy","day!".
For multiple search terms, separate search will match each search term, E.g. "turkey month" will match with "turkey" and "month"
and count this as 2 matches in the file.
 
Combined: This option is for combined search terms. The search terms in the given order will be searched for,
e.g. "garbage day" will match with "Garbage Day","GARBAGE day","garbage day?", but not with "Garbage not day", "day", "garbage" etc. 

Separate (Case Matching): This is the same as separate search, except only words with the same cases as the search term(s) match.
E.g. "day" won't match with "Day","DAY","dAy", it will only match with "day".

Combined (Case Matching): This is the same as combined search but with case matching. 
E.g. "garbage day" won't match with "Garbage Day", "GARBAGE DAY", it will only match with "garbage day".

The user has the option to search one particular file or every file. Whichever one they picked will be displayed in the 
search parameters text area of the GUI. If a selected file couldn't be found, the program will default to searching all files and 
inform the user of this.

Each file with as least one search term match will be stored as a TextFile.java object and returned in an ArrayList. If the search 
was for multiple files, the ArrayList is sorted before being returned. Single file searches will still return the file in an ArrayList.

The sorted list is based on the number of search term matches that was found in each file. The files with the highest number of matches
are displayed first. Each file has a percentage calculated for them. This percentage is how many of the words in the file were the 
search terms. It is calculated using this formula: 

(Number of matches in the file/Number of words in the file) * 100

The percentage for each file is also displayed with the results.


4.) Features that could be added
The search engine in its current state performs very well, but it would benefit from 1 or 2 more features. Being able to select the 
directory where the files are stored would be more convenient. Allowing some form of editing of the files from the GUI and displaying
them would be highly useful. This feature could then be expanded to allow the user to enter in another word that would replace each
search term found in the file.

5.) Video URL

https://www.youtube.com/watch?v=cCA24TBC_v8

