package com.hakiba.spacucumber

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @author hakiba
 */
fun <T : Any> T.prepareLogger(): Logger = LoggerFactory.getLogger(javaClass)
