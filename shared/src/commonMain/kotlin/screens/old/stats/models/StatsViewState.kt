package screens.old.stats.models

import screens.old.stats.views.StatisticCellModel

data class StatsViewState(
    val activeProgress: List<StatisticCellModel> = emptyList(),
    val pastActivities: List<StatisticCellModel> = emptyList()
)