package com.nguyen.jetreader.screens.login

data class LoadingState(val status: Status, val message: String? = null) {
    enum class Status {
        SUCCESS,
        FAILED,
        LOADING,
        IDLE
    }

    companion object {
        val IDLE = LoadingState(Status.IDLE)
        val LOADING = LoadingState(Status.LOADING)
        val SUCCESS = LoadingState(Status.SUCCESS)
        val FAILED = LoadingState(Status.FAILED)
    }
}