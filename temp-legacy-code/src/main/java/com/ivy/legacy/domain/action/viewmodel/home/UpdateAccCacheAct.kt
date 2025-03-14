package com.ivy.wallet.domain.action.viewmodel.home

import com.ivy.frp.action.FPAction
import com.ivy.core.data.model.Account
import com.ivy.legacy.IvyWalletCtx
import javax.inject.Inject

class UpdateAccCacheAct @Inject constructor(
    private val ivyWalletCtx: IvyWalletCtx
) : FPAction<List<Account>, List<Account>>() {
    override suspend fun List<Account>.compose(): suspend () -> List<Account> = suspend {
        val accounts = this

        ivyWalletCtx.accountMap.clear()
        ivyWalletCtx.accountMap.putAll(accounts.map { it.id to it })

        accounts
    }
}
