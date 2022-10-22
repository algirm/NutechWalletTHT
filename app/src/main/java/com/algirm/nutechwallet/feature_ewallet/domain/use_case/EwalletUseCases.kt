package com.algirm.nutechwallet.feature_ewallet.domain.use_case

data class EwalletUseCases(
    val getBalance: GetBalance,
    val topUp: TopUp,
    val transfer: Transfer,
    val getTransactionHistory: GetTransactionHistory,
    val getUsers: GetUsers,
    val addUser: AddUser,
    val deleteUser: DeleteUser
)
