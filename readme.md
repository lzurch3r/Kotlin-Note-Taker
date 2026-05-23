# Kotlin Note Taker

A terminal-based program written in Kotlin that lets the user create notes, delete them, assign them to a category, and sort them in different ways.

## Instructions for Build and Use

Steps to build and/or run the software:

1. Download the latest version of IntelliJ IDEA 
2. Run IntelliJ IDEA and click "Open"
3. Navigate to where you downloaded the folder for Kotlin Note Taker and select it
4. On the left-hand side, navigate to the "src" folder and double-click "Main.kt" to open it
5. Press the green play button at the top to build the software and run it.

Instructions for using the software:

1. At the start, Kotlin Note Taker will load a home page that displays any notes created in the software. If no notes are in the file, a message will display indicating no notes are available and the menu options will be limited. If there are notes, the following menu options are available:

   1. Create new note
   2. Delete existing note
   3. Assign existing note to category
   4. Sort notes
   5. Exit program
2. The user can type a number and hit Enter, and the corresponding menu option will run.
3. In "Create new note", the user can input a title and a description as desired. The user will be prompted to input a category, but this is optional. Once all fields have been entered, the note will be saved to a collection of notes in a file and all notes will be displayed onscreen from the most recently saved note to the oldest.
4. In "Delete existing note", the user can select a note from the list by entering the number of the corresponding note, after which the note will be deleted from the list and the list will again be displayed in order from most recent to least recent.
5. In "Assign existing note to category", the user can type a number to choose a note. He can then input a new category and the note will be assigned to that category.
6. In "Sort notes", the user can type a number to choose from the following list of sorting options:

   1. Most recent first
   2. Alphabetical order (by title)
   3. Alphabetical order (by category)

&#x09;The list of notes will be sorted as depicted in the options.

7\. If the user selects "Exit program", the user will be thanked and the program will end!

## Development Environment

To recreate the development environment, you need the following software and/or libraries with the specified versions:

* IntelliJ IDEA 2026.1.2 - Build #IU-261.24374.151, built on May 14, 2026
* Kotlin: 261.24374.151-IJ
* java.io.FileInputStream
* java.io.Serializable
* java.io.ObjectOutputStream
* java.io.FileOutputStream
* java.io.ObjectInputStream
* java.time.LocalDateTime
* java.io.File
* java.time.format.DateTimeFormatter

## Useful Websites to Learn More

I found these websites useful in developing this software:

* [Programiz - Learn Kotlin Programming](https://www.programiz.com/kotlin-programming)
* [Get Started with Kotlin | Kotlin Documentation](https://kotlinlang.org/docs/getting-started.html)

## Future Work

The following items I plan to fix, improve, and/or add to this project in the future:

* \[ ] Loop to main menu after executing each option (except option 5)
* \[ ] Needs more error handling
* \[ ] Design project for Android implementation

