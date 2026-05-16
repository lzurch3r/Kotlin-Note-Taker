import java.io.Serializable
import java.io.ObjectOutputStream
import java.io.FileOutputStream

fun main(args: Array<String>) {
    //TEST NOTE, Save to file
    val newNote = Note("New Note", "Description here", "No category")

    save_to_file(newNote, "notes.dat")
}

fun load_home_page() {
    println("Welcome! Here are your notes:")
    val notes = ""
}

fun get_user_input() {

}

fun load_create_note_page() {

}

fun create_note() {

}

fun cancel_action() {

}

fun get_category() {

}

data class Note(val title: String, val description: String, val category: String) {
    private var date = ""
}

fun save_to_file(note: Note, path: String) {
    ObjectOutputStream(FileOutputStream(path)).use { out ->
        out.writeObject(note)
    }
}