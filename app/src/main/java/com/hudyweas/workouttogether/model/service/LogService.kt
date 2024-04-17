package com.hudyweas.workouttogether.model.service

interface LogService {
  fun logNonFatalCrash(throwable: Throwable)
}
