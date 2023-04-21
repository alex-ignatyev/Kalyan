package screens.old.compose.models

sealed class ComposeError {
    object SendingGeneric : ComposeError()
}