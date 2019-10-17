package com.hakiba.spacucumber.spring

import com.hakiba.spacucumber.spring.config.TestConfig
import org.junit.runner.RunWith
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author hakiba
 */

@SpringBootApplication
class SpringTestApplication

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [SpringTestApplication::class])
@ContextConfiguration(classes = [TestConfig::class])
abstract class SpringTestBase
