package com.alexadiamant.qrscannerapp.logic.implementations

import com.alexadiamant.qrscannerapp.logic.contracts.LinksContract

class LinksContractImpl: LinksContract {

    //method to cut endpoint from link
    override fun getEndpoint(link: String): String {
        val endpoint = link.substringAfterLast("/")

        return endpoint
    }
}