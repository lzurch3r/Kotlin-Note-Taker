import java.io.FileInputStream
import java.io.Serializable
import java.io.ObjectOutputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.time.LocalDateTime
import java.io.File

fun main(args: Array<String>) {
    val notes: MutableCollection<Note> = load_home_page()

    var option = ""
    option = get_user_input("Type a number and press Enter: ")
    while (option.length > 1) {
        println("Invalid input. Please enter a number")
        option = get_user_input("")
    }
    when (option.toByteOrNull()) {
        1.toByte() -> {
            val newNote = create_note()
            notes.add(newNote)

            save_to_file(notes, "notes.dat")
            println("Note saved!")
            display_set_of_notes(notes)
        }
        2.toByte() -> {
            var noteNum = get_user_input("Type the number of the note you want to delete: ")
            println("You typed: $noteNum")
            while (noteNum.toInt() > get_collection_length(notes) || noteNum.toInt() <= 0) {
                print("Note doesn't exist. Please enter a number within range: ")
                noteNum = get_user_input("")
            }
        }
        3.toByte(), 4.toByte() -> println("Not implemented yet")
        else -> println("Invalid option")
    }
}

fun load_home_page(): MutableCollection<Note> {
    val path = "notes.dat"
    val notesFile = File(path)
    var notes: MutableCollection<Note> = mutableSetOf<Note>()

    if (!notesFile.exists()) {
        println("You have no notes!")
    }

    else if (notesFile.exists()) {
        notes = load_from_file("notes.dat")
        println("Notes list:\n")
        display_set_of_notes(notes)
    }

    println("MAIN MENU")
    println("1. Create new note")
    println("2. Delete existing note")
    println("3. Link category to existing note")
    println("4. Sort notes")

    return notes
}

fun get_user_input(prompt: String): String {
    print(prompt)
    return readln()
}

fun load_create_note_page() {

}

fun create_note(): Note {
    println("Let's create a note!")
    var title: String = get_user_input("Title: ")
    while (title.trim().isEmpty()) {
        println("Title cannot be empty!")
        title = get_user_input("Title: ")
    }
    var description: String = get_user_input("Description: ")
    while (description.trim().isEmpty()) {
        println("Description cannot be empty!")
        description = get_user_input("Description: ")
    }
    //category is optional
    val category = get_user_input("Category (optional): ")

    return Note(title, description, category, LocalDateTime.now())
}

fun cancel_action() {

}

fun get_category() {

}

fun display_set_of_notes(notes: Collection<Note>) {
    var count: Int = 1
    for (note in notes) {
        print("$count. ")
        note.display()
        println("")
        count++
    }
}

fun get_collection_length(notes: MutableCollection<Note>): Int {
    var count: Int = 0
    for (note in notes) {
        count++
    }
    return count
}

data class Note(val title: String, val description: String, val category: String, val date_time: LocalDateTime): Serializable {
    public fun display() {
        println("Title: $title")
        println("   Description: $description")
        println("   Category: $category")
        println("   Date and Time: $date_time")
    }
}

fun save_to_file(notes: Collection<Note>, path: String) {
    ObjectOutputStream(FileOutputStream(path)).use { out ->
        out.writeObject(notes)
    }
}

fun load_from_file(path: String): MutableCollection<Note> {
    return ObjectInputStream(FileInputStream(path)).use { input ->
        input.readObject() as MutableCollection<Note>
    }
}