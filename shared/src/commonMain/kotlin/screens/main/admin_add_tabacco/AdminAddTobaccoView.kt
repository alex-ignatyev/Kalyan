package screens.main.admin_add_tabacco

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kalyan.shared.AppRes
import com.kalyan.shared.strings.AppResStrings
import di.LocalPlatform
import di.Platform.iOS
import model.admin.CompanyResponse
import ru.alexgladkov.odyssey.compose.extensions.present
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalSheetConfiguration
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.AddTobaccoClick
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ChangeCompany
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ChangeLine
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ChangeStrengthByCompany
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ChangeTaste
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.OnBackClick
import ui.KalyanTheme
import ui.components.KalyanButton
import ui.components.KalyanCircularProgress
import ui.components.KalyanSelect
import ui.components.KalyanTextField
import ui.components.KalyanToolbar

@Composable
fun AdminAddTobaccoView(state: AdminAddTobaccoState, obtainEvent: (AdminAddTobaccoEvent) -> Unit) {
    val platformProvider = LocalPlatform.current
    val rootController = LocalRootController.current
    val modalController = rootController.findModalController()

    Scaffold(
        modifier = Modifier.padding(top = if (platformProvider == iOS) 32.dp else 0.dp),
        topBar = {
            KalyanToolbar(
                title = AppResStrings.title_admin_add_tobacco,
                onBackClick = {
                    obtainEvent.invoke(OnBackClick())
                })
        },
        backgroundColor = KalyanTheme.colors.primaryBackground
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            KalyanSelect(title = AppResStrings.text_admin_company, text = state.company) {
                modalController.present(ModalSheetConfiguration()) { key ->
                    CompanyBottomSheet(key, state.companies, obtainEvent)
                }
            }

            KalyanTextField(
                value = state.taste,
                placeholder = AppResStrings.text_admin_taste,
                enabled = !state.isLoading,
                isError = state.error.isNotBlank(),
            ) {
                obtainEvent(ChangeTaste(it))
            }

            KalyanSelect(title = AppResStrings.text_admin_line, text = state.line) {
                val lines = state.companies.findLast { it.company == state.company }?.lines ?: return@KalyanSelect
                modalController.present(ModalSheetConfiguration()) { key ->
                    LineBottomSheet(key, lines, obtainEvent)
                }
            }

            KalyanTextField(
                value = state.strengthByCompany,
                placeholder = AppResStrings.text_admin_straight,
                enabled = !state.isLoading,
                isError = state.error.isNotBlank()
            ) {
                obtainEvent(ChangeStrengthByCompany(it))
            }

            KalyanButton(
                modifier = Modifier.padding(vertical = 32.dp),
                text = if (state.isLoading) null else AppRes.string.text_account_create,
                enabled = !state.isLoading,
                content = {
                    KalyanCircularProgress()
                },
                onClick = {
                    obtainEvent(AddTobaccoClick())
                })

            Text(
                text = state.error,
                color = KalyanTheme.colors.errorColor,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun CompanyBottomSheet(screenKey: String, companies: List<CompanyResponse>, obtainEvent: (AdminAddTobaccoEvent) -> Unit) {
    val rootController = LocalRootController.current
    val modalController = rootController.findModalController()

    LazyColumn(modifier = Modifier.height(400.dp).padding(16.dp)) {
        items(companies) {
            Text(it.company,
                style = KalyanTheme.typography.body,
                modifier = Modifier.clickable {
                    obtainEvent.invoke(ChangeCompany(it.company))
                    modalController.popBackStack(screenKey)
                })
        }

    }
}

@Composable
fun LineBottomSheet(screenKey: String, lines: List<String>, obtainEvent: (AdminAddTobaccoEvent) -> Unit) {
    val rootController = LocalRootController.current
    val modalController = rootController.findModalController()

    LazyColumn(modifier = Modifier.height(400.dp).padding(16.dp)) {
        items(lines) {
            Text(it,
                style = KalyanTheme.typography.body,
                modifier = Modifier.clickable {
                    obtainEvent.invoke(ChangeLine(it))
                    modalController.popBackStack(screenKey)
                })
        }

    }
}
