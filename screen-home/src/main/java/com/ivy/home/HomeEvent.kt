package com.ivy.home

import com.ivy.legacy.data.model.TimePeriod
import com.ivy.home.customerjourney.CustomerJourneyCardModel
import com.ivy.core.data.model.Transaction

sealed class HomeEvent {
    data object Start : HomeEvent()

    data class SetUpcomingExpanded(val expanded: Boolean) : HomeEvent()
    data class SetOverdueExpanded(val expanded: Boolean) : HomeEvent()

    data object BalanceClick : HomeEvent()
    data object HiddenBalanceClick : HomeEvent()

    data object SwitchTheme : HomeEvent()

    data class SetBuffer(val buffer: Double) : HomeEvent()

    data class SetCurrency(val currency: String) : HomeEvent()

    data class SetPeriod(val period: TimePeriod) : HomeEvent()

    data class PayOrGetPlanned(val transaction: Transaction) : HomeEvent()
    data class SkipPlanned(val transaction: Transaction) : HomeEvent()
    data class SkipAllPlanned(val transactions: List<Transaction>) : HomeEvent()

    data class DismissCustomerJourneyCard(val card: CustomerJourneyCardModel) : HomeEvent()

    data object SelectNextMonth : HomeEvent()
    data object SelectPreviousMonth : HomeEvent()
}
