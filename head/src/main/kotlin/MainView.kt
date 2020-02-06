import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.text.Font

class MainView() {
    val root: Parent

    init {
        root = VBox().apply {
            children += Label("Hello World").apply {
                font = Font(font.name, 20.0)
            }
        }
    }
}
