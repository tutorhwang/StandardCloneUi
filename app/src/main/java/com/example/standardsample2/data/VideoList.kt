package com.example.standardclonui.data

import android.net.Uri

object VideoList {
    private val list: MutableList<Video> = mutableListOf()
    var imageUri: Uri? = null

    init {
        add(
            Video(
                "MrGamerJay",
                "I tried Creepy Minecraft Seeds \uD83D\uDC80",
                "https://i.ytimg.com/vi/WGJLOTDEUqI/hqdefault.jpg",
                "Discord: https://discord.com/invite/mrgamerjay Instagram:https://www.instagram.com/mrgamerjay/ Facebook ..."
            )
        )
        add(
            Video(
                "\"Ryguyrocky\"",
                "I Survived 100 Days as the GRINCH in Minecraft",
                "https://i.ytimg.com/vi/C7iRz37V2yY/hqdefault.jpg",
                "Buy the brand new merch I just released at ryguy.shop !!!!! Want to be in a future video? Join my Discord!"
            )
        )
        add(
            Video(
                "aCookieGod",
                "I Farmed 1,162,728 Cookies in Minecraft Hardcore",
                "https://i.ytimg.com/vi/guF3Ghe2qXk/hqdefault.jpg",
                "I Crafted Over a MILLION COOKIES in Hardcore Minecraft • watch from EPISODE #1: ..."
            )
        )
        add(
            Video(
                "Clyde Charge",
                "Escape From Abstracted POMNI Digital Circus in Minecraft!",
                "https://i.ytimg.com/vi/3iH-CLnzAfI/hqdefault.jpg",
                "Escape From POMNI Digital Circus in Minecraft! Game: Minecraft Modded Aphmau, Maizen, Nico and Cash Parody! Pepesan TV ..."
            )
        )
        add(
            Video(
                "Компот",
                "КАК МНЕ ПЕРЕЖИТЬ ГРОЗУ В МАЙНКРАФТ | Компот Minecraft",
                "https://i.ytimg.com/vi/8LVLlMRo1no/hqdefault.jpg",
                "Мои аккаунты: »Мой сайт- https://compot.fun »Второй канал- https://www.youtube.com/c/ПирожоксНичем »Основной канал- ..."
            )
        )
    }

    fun get(index: Int): Video {
        return list[index]
    }

    fun add(video: Video) {
        list.add(video)
    }

    fun remove(video: Video) {
        list.remove(video)
    }

    fun find(channelTitle: String): Video? {
        return list.find { it.channelTitle == channelTitle }
    }
}