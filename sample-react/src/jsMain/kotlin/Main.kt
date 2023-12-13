import com.emarsys.Emarsys
import com.emarsys.EmarsysConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import react.dom.client.createRoot
import react.FC
import react.Props
import react.create
import web.dom.Element
import web.dom.document

suspend fun main() {
    val root = document.createElement("div")
        .also { document.body.appendChild(it) }

    createRoot(root as Element)
        .render(App.create())
    
    Emarsys.initialize()
    Emarsys.enableTracking(EmarsysConfig("EMS11-C3FD3"))
}

private val App = FC<Props> {
    +"Hello, SDK!"
}