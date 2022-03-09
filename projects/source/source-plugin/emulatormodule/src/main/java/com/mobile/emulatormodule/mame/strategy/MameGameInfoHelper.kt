package com.mobile.emulatormodule.mame.strategy

/**
 *  author : zhangws
 *  date : 2021/1/12 11:18
 *  description :mame游戏 数据相关
 */
class MameGameInfoHelper {

    //游戏名称
    var gameName: String? = null

    //游戏图标
    var gameIcon: String? = null

    //游戏名称
    var gameID: String? = null

    //so文件的md5
    var soMD5: String? = null

    //游戏文件的md5
    var gameMD5: String? = null

    //操作指南列表
    var operateGuideList: MutableList<String>? = null

    //操作指南列表title
    var operateTitle: String? = null

    //so文件下载链接
    var soDownUrl: String? = null

    //游戏下载链接
    var gameDownUrl: String? = null

    //云游戏排队位置
    var cloudQueuePosition: Int = -1

    fun clear() {
        gameIcon = null
        gameName = null
        gameID = null
        soMD5 = null
        gameMD5 = null
        soDownUrl = null
        gameDownUrl = null
        operateGuideList = null
        operateTitle = null
        cloudQueuePosition = -1
    }

}