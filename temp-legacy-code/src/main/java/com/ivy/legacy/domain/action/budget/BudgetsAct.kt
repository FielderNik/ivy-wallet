package com.ivy.wallet.domain.action.budget

import com.ivy.frp.action.FPAction
import com.ivy.frp.action.thenMap
import com.ivy.frp.then
import com.ivy.core.data.model.Budget
import com.ivy.core.data.db.dao.BudgetDao
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject

class BudgetsAct @Inject constructor(
    private val budgetDao: BudgetDao
) : FPAction<Unit, ImmutableList<Budget>>() {
    override suspend fun Unit.compose(): suspend () -> ImmutableList<Budget> = suspend {
        budgetDao.findAll()
    } thenMap { it.toDomain() } then { it.toImmutableList() }
}
