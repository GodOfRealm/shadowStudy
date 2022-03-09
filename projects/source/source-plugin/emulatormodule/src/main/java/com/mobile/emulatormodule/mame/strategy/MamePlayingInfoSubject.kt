package com.mobile.emulatormodule.mame.strategy

import com.mobile.emulatormodule.mame.interfaces.IMameGameObserver
import com.mobile.emulatormodule.mame.interfaces.MameGamePlayingObserver
import java.util.concurrent.ConcurrentHashMap

class MamePlayingInfoSubject {
    private val observerMap = ConcurrentHashMap<String, IMameGameObserver>()

    fun attach(key: String, observer: IMameGameObserver) {
        observerMap[key] = observer
    }

    fun detach(key: String) {
        observerMap.remove(key)
    }

    fun clear() {
        observerMap.clear()
    }

    private inline fun <reified T : IMameGameObserver> observer(call: (T?) -> Unit) {
        observerMap.mapKeys {
            call(it.value as? T)
        }
    }

    fun notifyLoadingStep(step: Int) {
        observer<MameGamePlayingObserver> {
            it?.notifyLoadingStep(step)
        }
    }


    fun notifyError(msg: String?) {
        observer<MameGamePlayingObserver> {
            it?.notifyErrorCode(msg)
        }

    }

    fun notifyPlaying() {
        observer<MameGamePlayingObserver> {
            it?.notifyPlaying()
        }

    }

    fun notifyShowLoading(show: Boolean) {
        observer<MameGamePlayingObserver> {
            it?.notifyShowLoading(show)
        }

    }

    fun notifyExit() {
        observer<MameGamePlayingObserver> {
            it?.notifyExit()
        }
    }

    fun notifyArchive() {
        observer<MameGamePlayingObserver> {
            it?.notifyArchive()
        }
    }

    fun notifyShock(shock: Boolean) {
        observer<MameGamePlayingObserver> {
            it?.notifyShock(shock)
        }
    }

    fun notifySelect() {
        observer<MameGamePlayingObserver> {
            it?.notifySelect()
        }
    }

    fun notifyStart() {
        observer<MameGamePlayingObserver> {
            it?.notifyStart()
        }
    }

}