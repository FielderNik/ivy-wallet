package com.ivy.wallet.domain.action.transaction

import com.ivy.frp.action.FPAction
import com.ivy.frp.action.thenMap
import com.ivy.core.data.model.Transaction
import com.ivy.wallet.domain.pure.data.ClosedTimeRange
import com.ivy.core.data.db.dao.TransactionDao
import javax.inject.Inject

class DueTrnsAct @Inject constructor(
    private val transactionDao: TransactionDao
) : FPAction<ClosedTimeRange, List<Transaction>>() {

    override suspend fun ClosedTimeRange.compose(): suspend () -> List<Transaction> = suspend {
        io {
            transactionDao.findAllDueToBetween(
                startDate = from,
                endDate = to
            )
        }
    } thenMap { it.toDomain() }
}
