package com.ivy.wallet.domain.deprecated.logic

import androidx.compose.ui.graphics.toArgb
import com.ivy.core.data.model.Account
import com.ivy.wallet.domain.deprecated.logic.model.CreateAccountData
import com.ivy.wallet.domain.pure.util.nextOrderNum
import com.ivy.core.data.db.dao.AccountDao
import com.ivy.legacy.utils.ioThread
import javax.inject.Inject

class AccountCreator @Inject constructor(
    private val accountDao: AccountDao,
    private val accountLogic: WalletAccountLogic
) {

    suspend fun createAccount(
        data: CreateAccountData,
        onRefreshUI: suspend () -> Unit
    ) {
        val name = data.name
        if (name.isBlank()) return


        val newAccount = ioThread {
            val account = Account(
                name = name,
                currency = data.currency,
                color = data.color.toArgb(),
                icon = data.icon,
                includeInBalance = data.includeBalance,
                orderNum = accountDao.findMaxOrderNum().nextOrderNum(),
                isSynced = false
            )
            accountDao.save(account.toEntity())

            accountLogic.adjustBalance(
                account = account,
                actualBalance = 0.0,
                newBalance = data.balance
            )
            account
        }

        onRefreshUI()
    }

    suspend fun editAccount(
        account: Account,
        newBalance: Double,
        onRefreshUI: suspend () -> Unit
    ) {
        val updatedAccount = account.copy(
            isSynced = false
        )

        ioThread {
            accountDao.save(updatedAccount.toEntity())
            accountLogic.adjustBalance(
                account = updatedAccount,
                actualBalance = accountLogic.calculateAccountBalance(updatedAccount),
                newBalance = newBalance
            )
        }

        onRefreshUI()
    }
}
