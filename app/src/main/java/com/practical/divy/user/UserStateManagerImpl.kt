package com.practical.divy.user

import com.practical.divy.preference.PreferenceHelper
import com.google.gson.Gson
import javax.inject.Inject

class UserStateManagerImpl @Inject constructor(
    private val preferenceHelper: PreferenceHelper,
    private val gson: Gson,
) : UserStateManager {
//
//    override fun markOnBoardingAsComplete() {
//        preferenceHelper.setValue(Constants.PREF_IS_INTRODUCTION_FINISHED_BOOL, true)
//    }
//
//    override fun isOnBoardingCompleted(): Boolean {
//        return preferenceHelper.getValue(
//            Constants.PREF_IS_INTRODUCTION_FINISHED_BOOL, false
//        )
//    }
//
//    override fun isUserLoggedIn(): Boolean {
//        return !TextUtils.isEmpty(
//            preferenceHelper.getValue(Constants.PREF_LOGIN_DATA, "")
//        )
//    }
//
//    override fun getFirebaseToken(): String {
//        return preferenceHelper.getValue(Constants.PREF_FIREBASE_TOKEN, "")
//    }
//
//    override fun saveFirebaseToken(token: String) {
//        preferenceHelper.setValue(Constants.PREF_FIREBASE_TOKEN, token)
//    }
//
//    override fun getAuthToken(): String {
//        return preferenceHelper.getValue(Constants.PREF_BEARER_TOKEN, "")
//    }
//
//    override fun saveAuthToken(token: String) {
//        preferenceHelper.setValue(Constants.PREF_BEARER_TOKEN, token)
//    }
//
//    override fun getUserProfile(): LoginResponse? {
//        val loginResponseJson = preferenceHelper.getValue(Constants.PREF_LOGIN_DATA, "")
//        if (loginResponseJson.isNotEmpty())
//            return gson.fromJson(loginResponseJson, LoginResponse::class.java)
//        return null
//    }
//
//    override fun saveUserProfile(loginResponse: LoginResponse) {
//        preferenceHelper.setValue(Constants.PREF_LOGIN_DATA,
//            gson.toJson(loginResponse))
//            saveAuthToken(loginResponse.token!!)
//    }

//
//    override fun saveLoginDetails(loginDetails: LoginDetails?) {
//        preferenceHelper.setValue(Constants.PREF_LOGIN_DETAILS,gson.toJson(loginDetails))
//    }
//
//    override fun getLoginDetails(): LoginDetails? {
//        val loginDetails = preferenceHelper.getValue(Constants.PREF_LOGIN_DETAILS, "")
//        if (loginDetails.isNotEmpty()){
//            val type: Type = object : TypeToken<LoginDetails?>() {}.getType()
//            return gson.fromJson(loginDetails,type)
//        }else{
//            return null
//        }
//    }
//
//    override fun removeLoginDetails() {
//        preferenceHelper.clearAll(Constants.PREF_IS_INTRODUCTION_FINISHED_BOOL)
//    }
//
//
//    override fun logout(activity: Activity) {
//        preferenceHelper.clearAll(
//            Constants.PREF_FIREBASE_TOKEN,
//            Constants.PREF_IS_INTRODUCTION_FINISHED_BOOL,
//            Constants.PREF_LOGIN_DETAILS
//        )
//        activity.gotoActivity(
//            LoginContainerActivity::class.java,
//            clearAllActivity = true
//        )
//    }

}