package id.husni.moviecatalogue.utils

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors @VisibleForTesting constructor(
    private val diskIO: Executor,
    private val netWorkIO: Executor,
    private val mainThread: Executor
) {
    companion object{
        private const val THREAD = 3
    }

    constructor(): this(
        Executors.newSingleThreadExecutor(),
        Executors.newFixedThreadPool(THREAD),
        MainThreadExecutors()
    )

    class MainThreadExecutors : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            mainThreadHandler.post(command)
        }

    }

    fun diskIO(): Executor = diskIO

    fun networkIO(): Executor = netWorkIO

    fun mainThread(): Executor = mainThread
}