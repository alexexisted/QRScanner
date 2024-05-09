package com.alexadiamant.qrscannerapp.logic.contracts

interface LinksContract {

    fun getEndpoint(link: String): String

    fun getLink(link: String): String

}