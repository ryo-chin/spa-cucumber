package com.hakiba.spacucumber

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @author hakiba
 */
// ref: https://www.baeldung.com/kotlin-logging
fun <T : Any> T.prepareLogger(): Logger = LoggerFactory.getLogger(javaClass)
