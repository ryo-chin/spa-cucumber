package com.hakiba.spacucumber.base

import com.hakiba.spacucumber.page.LoginPage
import com.hakiba.spacucumber.page.MyPage
import com.hakiba.spacucumber.page.SignUpPage
import com.hakiba.spacucumber.page.UserProfilePage
import kotlin.reflect.KClass

/**
 * @author hakiba
 */
object WebPageHolder {
    private val pageMap: Map<KClass<*>, BasePage> = mapOf(
            SignUpPage::class to SignUpPage(),
            LoginPage::class to LoginPage(),
            UserProfilePage::class to  UserProfilePage(),
            MyPage::class to MyPage()
    )

    fun <T: BasePage> retrieve(clazz: KClass<T>): T {
        return  (pageMap[clazz] ?: throw IllegalArgumentException()) as T
    }
}
