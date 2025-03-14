package com.ivy.wallet.domain.action.transaction

import com.ivy.frp.action.FPAction
import com.ivy.frp.action.thenMap
import com.ivy.core.data.model.Transaction
import com.ivy.core.data.db.dao.TransactionDao
import javax.inject.Inject

class AllTrnsAct @Inject constructor(
    private val transactionDao: TransactionDao
) : FPAction<Unit, List<Transaction>>() {
    override suspend fun Unit.compose(): suspend () -> List<Transaction> = suspend {
        transactionDao.findAll()
    } thenMap { it.toDomain() }
}
