package com.sorokin.yamob.cashmaster.data.entity

class Wallet(id: Int, name: String, var money: Double, val currency: String, val type: Int, val drawable: Int): IdNameEntity(id, name) {
    companion object{
        val CASH = 1
        val BANK_CARD = 2
        val BANK_ACCOUNT = 3
    }
}