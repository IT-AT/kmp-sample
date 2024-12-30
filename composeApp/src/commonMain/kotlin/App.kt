import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import api.MainApi
import kmmfirstapp.composeapp.generated.resources.Res
import kmmfirstapp.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.launch
import model.Todo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val coroutineScope = rememberCoroutineScope()
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Hello from Ahmed TARAFI")
                }
            }
            val todos = mutableStateOf(emptyList<Todo>())
            coroutineScope.launch {
                 todos.value = MainApi().getTodos()
                return@launch
            }
            LazyColumn(modifier = Modifier.padding(15.dp)) {
                for (todo in todos.value) {
                    item {
                        Text("${todo.id}- ${todo.title.toString()}")
                    }
                }
            }
        }
    }
}