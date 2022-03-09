package com.mobile.emulatormodule.mame.strategy

import  androidx.annotation.IntDef

/**
 *
 * description:游戏控制器类型
 */
interface MameInterfaceMode {

    companion object {
        /**
         * 正常页
         */
        const val STATUS_NORMAL = 0x01
        /**
         * 加载页
         */
        const val STATUS_LOADING = 0x01 shl 1


        const val normal = STATUS_NORMAL
        const val loading = STATUS_LOADING



    }

    @IntDef(normal, loading)
    annotation class Val

}


fun Int.isStatusEnabled(@MameInterfaceMode.Val mode: Int): Boolean {
    return (mode and this) != 0
}