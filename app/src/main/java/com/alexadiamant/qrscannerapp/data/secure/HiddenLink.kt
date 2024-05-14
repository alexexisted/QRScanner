package com.alexadiamant.qrscannerapp.data.secure

class HiddenLink private constructor() {
    private var hiddenLink = "https://api.mockfly.dev/mocks/060e9d53-0e78-4171-80cc-c4084031cad7"

    //getter to get the link
    fun getLink(): String{
        return hiddenLink
    }

    //singleton instance of this class with basic link
    companion object {
        val instance by lazy { Holder.INSTANCE }
    }

    private object Holder {
        val INSTANCE = HiddenLink()
    }
}