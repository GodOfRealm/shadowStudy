package com.mobile.emulatormodule.mame.interfaces

interface MameGamePlayingObserver : IMameGameObserver {
    fun notifyLoadingStep(step: Int)
    fun notifyPlaying()
    fun notifyErrorCode(msg: String?)
    fun notifyShowLoading(show: Boolean)
    fun notifyExit()
    fun notifyArchive()
    fun notifyShock(shock: Boolean)
    fun notifySelect()
    fun notifyStart()
}