package com.example.mysharedpreference

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.CheckBoxPreference
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat

class MyPreferenceFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var keyName: String
    private lateinit var keyEmail: String
    private lateinit var keyAge: String
    private lateinit var keyPhone: String
    private lateinit var keyLove: String
    private lateinit var namePreference: EditTextPreference
    private lateinit var emailPreference: EditTextPreference
    private lateinit var agePreference: EditTextPreference
    private lateinit var phonePreference: EditTextPreference
    private lateinit var isLoveRMPreference: CheckBoxPreference

    companion object {
        private const val DEFAULT_VALUE = "Tidak Ada"
    }

    override fun onCreatePreferences(bundle: Bundle?, s: String?) {
        addPreferencesFromResource(R.xml.preferences)
        init()
        setSummaries()
    }

    private fun init() {
        keyName = resources.getString(R.string.key_name)
        keyEmail = resources.getString(R.string.key_email)
        keyAge = resources.getString(R.string.key_age)
        keyPhone = resources.getString(R.string.key_phone)
        keyLove = resources.getString(R.string.key_love)
        namePreference = findPreference<EditTextPreference> (keyName) as EditTextPreference
        emailPreference = findPreference<EditTextPreference>(keyEmail) as EditTextPreference
        agePreference = findPreference<EditTextPreference>(keyAge) as EditTextPreference
        phonePreference = findPreference<EditTextPreference>(keyPhone) as EditTextPreference
        isLoveRMPreference = findPreference<CheckBoxPreference>(keyLove) as CheckBoxPreference
    }

    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences
        namePreference.summary = sh?.getString(keyName, DEFAULT_VALUE)
        emailPreference.summary = sh?.getString(keyEmail, DEFAULT_VALUE)
        agePreference.summary = sh?.getString(keyAge, DEFAULT_VALUE)
        phonePreference.summary = sh?.getString(keyPhone, DEFAULT_VALUE)
        isLoveRMPreference.isChecked = sh?.getBoolean(keyLove, false) ?: false
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }
    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            keyName -> {
                namePreference.summary = sharedPreferences?.getString(keyName, DEFAULT_VALUE)
            }

            keyEmail -> {
                emailPreference.summary = sharedPreferences?.getString(keyEmail, DEFAULT_VALUE)
            }

            keyAge -> {
                agePreference.summary = sharedPreferences?.getString(keyAge, DEFAULT_VALUE)
            }

            keyPhone -> {
                phonePreference.summary = sharedPreferences?.getString(keyPhone, DEFAULT_VALUE)
            }

            keyLove -> {
                isLoveRMPreference.isChecked = sharedPreferences?.getBoolean(keyLove, false) ?: false
            }
        }
    }
}