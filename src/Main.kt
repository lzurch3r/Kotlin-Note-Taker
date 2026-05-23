import java.io.FileInputStream
import java.io.Serializable
import java.io.ObjectOutputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.time.LocalDateTime
import java.io.File
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    //If there are notes, loads display of note collection and menu options
    val notes: MutableCollection<Note> = loadHomePage()
    var sortType = 1

    var option = ""
    option = getUserInput("Type a number and press Enter: ")
    while (option.length != 1) {
        println("Invalid input. Please enter a number")
        option = getUserInput("")
    }
    when (option.toByteOrNull()) {
        1.toByte() -> { loadCreateNotePage(notes, sortType) }
        2.toByte() if (notes.isNotEmpty()) -> { loadDeleteNotePage(notes, sortType) }
        3.toByte() -> { loadAssignCategoryPage(notes) }
        4.toByte() -> { sortType = loadSortNotePage(notes) }
        5.toByte() -> {
            println("Exiting note-taking program...")
            println("See you!")
        }
        else -> println("Invalid option")
    }
}

fun loadHomePage(): MutableCollection<Note> {
    val path = "notes.dat"
    val notesFile = File(path)
    var notes: MutableCollection<Note> = mutableListOf<Note>()

    if (!notesFile.exists()) {
        println("You have no notes!")
    }

    else if (notesFile.exists()) {
        notes = loadFromFile("notes.dat")
        println("Notes list:\n")
        displaySetOfNotes(notes, 1)
    }

    println("MAIN MENU")
    println("1. Create new note")
    if (notes.isNotEmpty()) {
        println("2. Delete existing note")
        println("3. Assign existing note to category")
        println("4. Sort notes")
    }
    println("5. Exit program")

    return notes
}

fun getUserInput(prompt: String): String {
    print(prompt)
    return readln()
}

fun loadCreateNotePage(notes: MutableCollection<Note>, sortType: Int) {
    val newNote = createNote()
    notes.add(newNote)

    saveToFile(notes.sortedByDescending { it.dateTime }, "notes.dat")
    println("Note saved!")
    displaySetOfNotes(notes, sortType)
}

fun loadDeleteNotePage(notes: MutableCollection<Note>, sortType: Int) {
    var noteNum = getUserInput("Type the number of the note you want to delete: ")

    while (noteNum.toInt() > notes.size || noteNum.toInt() <= 0) {
        noteNum = getUserInput("Note doesn't exist. Please enter a number within range: ")
    }
    println("Deleting note $noteNum...")

    notes.remove(notes.elementAt(noteNum.toInt() - 1))

    saveToFile(notes.sortedByDescending { it.dateTime }, "notes.dat")
    println("Note deleted!")
    displaySetOfNotes(notes, sortType)
}

fun loadSortNotePage(notes: MutableCollection<Note>): Int {
    var count = 0
    println("Note sorting options:")
    count++
    println("$count. Most recent first")
    count++
    println("$count. Alphabetical order (by title)")
    count++
    println("$count. Alphabetical order (by category)")
    var option = getUserInput("Enter a method of sorting: ")

    while (option.length != 1 && (option.toIntOrNull() !in 1..count)) {
        option = getUserInput("Invalid option. Please enter a number within range: ")
    }

    val sortType = option.toInt()

    displaySetOfNotes(notes, sortType)
    return sortType
}

fun loadAssignCategoryPage(notes: MutableCollection<Note>) {
    var localNotes = notes
    val sortType = 1

    //Prompt user to change a note
    var noteNum = getUserInput("Type the number of the note you want to assign: ")
    while (noteNum.toInt() > localNotes.size || noteNum.toInt() <= 0) {
        noteNum = getUserInput("Note doesn't exist. Please enter a number within range: ")
    }

    //Get note and prompt user to change to a category
    var note = localNotes.elementAt(noteNum.toInt() - 1)
    note.display()

    var newCategory = getUserInput("Type in a new category (or leave blank to assign it to none): ")

    //Change category and display notes in sorted order
    note.category = newCategory

    //Save to "notes.dat"
    localNotes.remove(localNotes.elementAt(noteNum.toInt() - 1))
    localNotes.add(note)
    saveToFile(localNotes.sortedByDescending { it.dateTime }, "notes.dat")
    println("Note saved!\n")
    displaySetOfNotes(localNotes, sortType)
}

fun createNote(): Note {
    println("Let's create a note!")
    var title: String = getUserInput("Title: ")
    while (title.trim().isEmpty()) {
        println("Title cannot be empty!")
        title = getUserInput("Title: ")
    }
    var description: String = getUserInput("Description: ")
    while (description.trim().isEmpty()) {
        println("Description cannot be empty!")
        description = getUserInput("Description: ")
    }
    //category is optional
    val category = getUserInput("Category (optional): ")

    return Note(title, description, category, LocalDateTime.now())
}

fun displaySetOfNotes(notes: Collection<Note>, sortType: Int) {
    val localNotes = when (sortType) {
        1 -> notes.sortedByDescending { it.dateTime }
        2 -> notes.sortedBy { it.title }
        3 -> notes.sortedBy { it.category }
        else -> notes.sortedByDescending { it.dateTime }
    }
    var count = 1
    for (note in localNotes) {
        print("$count. ")
        note.display()
        println("")
        count++
    }
}

data class Note(val title: String, val description: String, var category: String, val dateTime: LocalDateTime): Serializable {
    fun display() {
        //Format Date and Time to be more readable
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val formatted = dateTime.format(formatter)

        //Display all components of Note object
        println("Title: $title")
        println("   Description: $description")
        if (category.isEmpty()) {
            println("   Category: <none>")
        }
        else println("   Category: $category")
        println("   Date and Time: $formatted")
    }
}

fun saveToFile(notes: Collection<Note>, path: String) {
    ObjectOutputStream(FileOutputStream(path)).use { out ->
        out.writeObject(notes)
    }
}

fun loadFromFile(path: String): MutableCollection<Note> {
    return ObjectInputStream(FileInputStream(path)).use { input ->
        @Suppress("UNCHECKED_CAST")
        val loaded = input.readObject() as? Collection<Note> ?: emptyList()
        mutableListOf<Note>().apply { addAll(loaded) }
    }
}