package com.sorokin.yamob.cashmaster.data.entity

class Wallet(id: Int, name: String, var money: Double, val currency: String, val type: Wallet.Type): IdNameEntity(id, name) {
    enum class Type{
        CASH, BANK_CARD, BANK_ACCOUNT
    }
}