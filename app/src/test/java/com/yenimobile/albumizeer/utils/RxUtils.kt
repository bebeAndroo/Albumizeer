package com.yenimobile.albumizeer.utils

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

fun ioThread(): Scheduler {
    return Schedulers.trampoline()
}

fun androidThread(): Scheduler {
    return Schedulers.trampoline()
}