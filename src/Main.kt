import java.io.FileInputStream
import java.io.Serializable
import java.io.ObjectOutputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.time.LocalDateTime

fun main(args: Array<String>) {

    //TEST NOTE, Save to file
    /*val newNote = Note("New Note", "Description here", "No category")
    val newNote2 = Note("Insert note here", "Insert desc here", "Insert category here")

    var notes = mutableSetOf<Note>(newNote)
    notes.add(newNote2)

    save_to_file(notes, "notes.dat")
    notes.display()
    display_set_of_notes(load_from_file("notes.dat"))*/

    //TEST USER INPUT
    /*val string = get_user_input("Please type anything: ")
    println(string)*/

    //TEST create_note()
    val newNote = create_note()
    newNote.display()
}

fun load_home_page() {
    println("Welcome! Here are your notes:")
    val notes = ""
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
    for (note in notes) {
        note.display()
    }
}

data class Note(val title: String, val description: String, val category: String, val date_time: LocalDateTime): Serializable {

    public fun display() {
        println("Title: $title")
        println("Description: $description")
        println("Category: $category\n")
        println("Date and Time: $date_time")
    }
}

fun save_to_file(notes: Collection<Note>, path: String) {
    ObjectOutputStream(FileOutputStream(path)).use { out ->
        out.writeObject(notes)
    }
}

fun load_from_file(path: String): Collection<Note> {
    return ObjectInputStream(FileInputStream(path)).use { input ->
        input.readObject() as Collection<Note>
    }
}