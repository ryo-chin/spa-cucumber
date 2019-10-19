package com.hakiba.spacucumber.page

import com.hakiba.spacucumber.base.BasePage

/**
 * @author hakiba
 */
class MyPage : BasePage("/mypage") {
    fun isDisplayedName(userName: String): Boolean {
        // TODO
        return true
    }
}