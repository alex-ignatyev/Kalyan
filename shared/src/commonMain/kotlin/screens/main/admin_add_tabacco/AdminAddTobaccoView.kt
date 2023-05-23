package screens.main.admin_add_tabacco

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.kalyan.shared.AppRes
import com.kalyan.shared.strings.AppResStrings
import di.LocalPlatform
import di.Platform.iOS
import model.admin.CompanyResponse
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.AddTobaccoClick
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ChangeCompany
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ChangeLine
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ChangeManual
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ChangeStrengthByCompany
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.ChangeTaste
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.OnBackClick
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.OnCompanyClick
import screens.main.admin_add_tabacco.AdminAddTobaccoEvent.OnLineClick
import ui.KalyanTheme
import ui.components.KalyanButton
import ui.components.KalyanCircularProgress
import ui.components.KalyanSelect
import ui.components.KalyanTextField
import ui.components.KalyanToolbar

@Composable
fun AdminAddTobaccoView(state: AdminAddTobaccoState, obtainEvent: (AdminAddTobaccoEvent) -> Unit) {
    val platformProvider = LocalPlatform.current

    Scaffold(
        modifier = Modifier.padding(top = if (platformProvider == iOS) 32.dp else 0.dp),
        topBar = {
            KalyanToolbar(
                title = AppResStrings.title_admin_add_tobacco,
                onBackClick = {
                    obtainEvent(OnBackClick())
                })
        },
        backgroundColor = KalyanTheme.colors.primaryBackground
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Ручной ввод",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = KalyanTheme.colors.primaryText,
                    modifier = Modifier.weight(1f)
                )

                Switch(state.isManual, colors = colors(
                    checkedThumbColor = KalyanTheme.colors.generalColor,
                    checkedTrackColor = KalyanTheme.colors.primaryText,
                    uncheckedTrackColor = KalyanTheme.colors.primaryText
                ), onCheckedChange = {
                    obtainEvent(ChangeManual(it))
                })
            }

            if (state.isManual) {
                KalyanTextField(
                    value = state.company,
                    placeholder = AppResStrings.text_admin_company,
                    enabled = !state.isLoading,
                    isError = state.error.isNotBlank()
                ) {
                    obtainEvent(ChangeCompany(it))
                }
            } else {
                KalyanSelect(title = AppResStrings.text_admin_company, text = state.company) {
                    obtainEvent(OnCompanyClick())
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

            if (state.isManual) {
                KalyanTextField(
                    value = state.line,
                    placeholder = AppResStrings.text_admin_line,
                    enabled = !state.isLoading,
                    isError = state.error.isNotBlank()
                ) {
                    obtainEvent(ChangeLine(it))
                }
            } else {
                KalyanSelect(title = AppResStrings.text_admin_line, text = state.line) {
                    val lines = state.companies.findLast { it.companyName == state.company }?.lines ?: return@KalyanSelect
                    obtainEvent(OnLineClick(lines))
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

data class CompanyBottomSheet(val companies: List<CompanyResponse>, val obtainEvent: (AdminAddTobaccoEvent) -> Unit) : Screen {

    @Composable
    override fun Content() {
        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        LazyColumn(modifier = Modifier.wrapContentHeight().padding(16.dp)) {
            items(companies) {
                Text(
                    text = it.companyName,
                    style = KalyanTheme.typography.body,
                    modifier = Modifier.fillMaxWidth().clickable {
                        obtainEvent(ChangeCompany(it.companyName))
                        bottomSheetNavigator.hide()
                    })
            }
        }
    }
}

data class LineBottomSheet(val lines: List<String>, val obtainEvent: (AdminAddTobaccoEvent) -> Unit) : Screen {

    @Composable
    override fun Content() {
        val bottomSheetNavigator = LocalBottomSheetNavigator.current

        LazyColumn(modifier = Modifier.wrapContentHeight().padding(16.dp)) {
            items(lines) {
                Text(
                    text = it,
                    style = KalyanTheme.typography.body,
                    modifier = Modifier.fillMaxWidth().clickable {
                        obtainEvent(ChangeLine(it))
                        bottomSheetNavigator.hide()
                    })
            }
        }
    }
}
