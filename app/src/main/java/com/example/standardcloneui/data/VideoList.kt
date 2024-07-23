package com.example.standardcloneui.data

import android.net.Uri

object VideoList {
    private val _list: MutableList<ListItem> = mutableListOf()
    val list: List<ListItem>
        get() = _list
    var imageUri: Uri? = null

    init {
        // Add temp data
        add(ListItem.HeaderItem("https://ssl.pstatic.net/melona/libs/1478/1478846/dd09bffd4f4941005810_20240112112107105.jpg"))
        add(
            ListItem.VideoItem(
                "dreamyas", //channel title
                "(REWORKS + UPDATE 12.75) Skibid Toilet Morphs", // title
                "https://i.ytimg.com/vi/aTm259SBqK4/mqdefault.jpg", //thumbnails.medium
                "skibidi toilt Be a stranger or a good guy!!111111 First game with 1000 visits I really appreciated, I really do First game with 1M ..." //description
            )
        )
        add(
            ListItem.VideoItem(
                "대형 토일렛맨 전기톱 토일렛맨", //channel title
                "skibidi toilet 69 (paet 2)", // title
                "https://i.ytimg.com/vi/OpYLRi4ea8E/mqdefault.jpg", //thumbnails.medium
                "https://www.youtube.com/channel/UCVr33j5MQxxJqLAm_uKp3xA @DottyTV @share1004 @user-ot9zi4ov4u @AquaAnimation ..." //description
            )
        )
        add(
            ListItem.VideoItem(
                "GorTheMovieGod", //channel title
                "Gor's \"skibidi toilet 69 (full episode) by @DaFuqBoom\" REACTION", // title
                "https://i.ytimg.com/vi/MHIP7l9fsEE/mqdefault.jpg", //thumbnails.medium
                "skibidi toilet 69 (full episode) https://www.youtube.com/watch?v=wGc0jClpTPQ Follow me on Twitter: ..." //description
            )
        )
        add(
            ListItem.VideoItem(
                "Tasti", //channel title
                "Skibidi Characters React to SKIBIDI TOILET 69 (Full Episode) - Gacha React", // title
                "https://i.ytimg.com/vi/knpKalmGfck/mqdefault.jpg", //thumbnails.medium
                "in this video you will see new secret G-Man scenes, and infiltration squad is moving towards skibidi scientist, while outside ..." //description
            )
        )
        add(ListItem.HeaderItem("https://naverpa-phinf.pstatic.net/MjAyNDA2MjhfMTc4/MDAxNzE5NTUxMzI2Mjcy.v_a8AV_8-b-0V9odCBzh0s5iP9B84uXNz0ScczkehcMg.QVqMhZO34YNQ_oz-3jfQpwHR_5bWyepwrsDBlnnHKwAg.PNG/Gillette_Shopping_DA_300x200_%EA%B9%94%EB%81%94%ED%95%A8%EC%9D%98_%EC%B0%A8%EC%9D%B4%EA%B0%80_%ED%81%B4%EB%9D%BC%EC%8A%A4_%EC%B0%A8%EC%9D%B4_17195513262596827232059420559299.png"))
        add(
            ListItem.VideoItem(
                "QUIZy House", //channel title
                "GUESS THE MONSTERS VOICE #96 DJ SKIBIDI TOILET GIANT SAINT TOILET Skibidi Toilet's Quiz", // title
                "https://i.ytimg.com/vi/_PtHg_AtG_M/mqdefault.jpg", //thumbnails.medium
                "GUESS THE MONSTERS VOICE #96 DJ SKIBIDI TOILET GIANT SAINT TOILET Skibidi Toilet's Quiz Thanks for watching the ..." //description
            )
        )
        add(
            ListItem.VideoItem(
                "Bùm", //channel title
                "Phân Tích Bí Ẩn Skibidi Toilet 69 Tập Full", // title
                "https://i.ytimg.com/vi/dyuFvdd1Le4/mqdefault.jpg", //thumbnails.medium
                "Phân Tích Bí Ẩn Skibidi Toilet 69 Tập Full --- Shopacc: https://bumroblox.net/k1 Kênh Phụ: ..." //description
            )
        )
        add(
            ListItem.VideoItem(
                "Ski Funny", //channel title
                "Skibidi Toilet Multiverse In Real Life: TV MAN Becomes The Bad Guy 10 | Skibidi Toilet in Real Life", // title
                "https://i.ytimg.com/vi/PNnhx3Tm65Q/mqdefault.jpg", //thumbnails.medium
                "Skibidi Toilet Multiverse In Real Life: TV MAN Becomes The Bad Guy 10 | Skibidi Toilet in Real Life: ..." //description
            )
        )
        add(
            ListItem.VideoItem(
                "VinsAlvin", //channel title
                "AKHIRNYA BISA LANJUT LAGI REACTION SEMUA FANMADE SKIBIDI TOILET PILIHAN KALIAN!!!", // title
                "https://i.ytimg.com/vi/SFbrGDG8vCU/mqdefault_live.jpg", //thumbnails.medium
                "BUAT KALIAN PECINTA SKIBIDI TOILET WAJIB NONTON !!! Donate Pake Suara Dan Video Sudah Bisa ..." //description
            )
        )
        add(
            ListItem.VideoItem(
                "Dasher", //channel title
                "SKIBIDI BLASTER (Sonic Wave x Skibidi Toilet) MASHUP", // title
                "https://i.ytimg.com/vi/kmO4V36R8jc/mqdefault.jpg", //thumbnails.medium
                "Skibidi Blaster Mashup DOWNLOAD LINK ..." //description
            )
        )
        add(
            ListItem.VideoItem(
                "Tasti", //channel title
                "Skibidi Characters React to SKIBIDI TOILET 69 (Full Episode) - Gacha React", // title
                "https://i.ytimg.com/vi/knpKalmGfck/mqdefault.jpg", //thumbnails.medium
                "in this video you will see new secret G-Man scenes, and infiltration squad is moving towards skibidi scientist, while outside ..." //description
            )
        )
        add(
            ListItem.VideoItem(
                "Pixel Loquendo", //channel title
                "skibidi toilet 69-2 titanes de preparan para batalla final 🤯 #shorts #skibidi #skibiditoilet #meme", // title
                "https://i.ytimg.com/vi/jR-fcPzczt0/mqdefault.jpg", //thumbnails.medium
                "" //description
            )
        )
        add(
            ListItem.VideoItem(
                "Bùm", //channel title
                "Phân Tích Bí Ẩn Skibidi Toilet 69 Tập Full", // title
                "https://i.ytimg.com/vi/dyuFvdd1Le4/mqdefault.jpg", //thumbnails.medium
                "Phân Tích Bí Ẩn Skibidi Toilet 69 Tập Full --- Shopacc: https://bumroblox.net/k1 Kênh Phụ: ..." //description
            )
        )
    }


    fun get(index: Int): ListItem {
        return _list[index]
    }

    private fun add(video: ListItem) {
        _list.add(video)
    }

    fun addFirst(video: ListItem) {
        _list.add(0, video)
    }

    fun removeAt(position: Int) {
        _list.removeAt(position)
    }

    fun removeLast() {
        _list.removeAt(_list.size - 1)
    }
}