package net.ccbluex.liquidbounce.ui.i18n

import java.io.InputStreamReader
import java.util.*
import kotlin.collections.HashMap

class Language(val locale: String, private val defaultLocale: String) {

    private val translateMap=HashMap<String, String>()

    constructor(defaultLocale: String) : this(defaultLocale, defaultLocale)

    init {
        if(defaultLocale != locale)
            read(defaultLocale)

        read(locale)
    }

    private fun read(locale: String){
        val prop=Properties()

        prop.load(InputStreamReader(LanguageManager::class.java.classLoader.getResourceAsStream("assets/minecraft/fdpclient/locale/$locale.lang")))

        for((key, value) in prop.entries){
            if(key is String&&value is String)
                translateMap[key] = value
        }
    }

    fun get(key: String):String{
        return translateMap[key] ?: key
    }
}