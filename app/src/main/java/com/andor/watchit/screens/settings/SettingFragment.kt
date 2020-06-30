package com.andor.watchit.screens.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.andor.watchit.R
import com.andor.watchit.core.MainApplication
import com.andor.watchit.core.utils.ThemeUtils
import kotlinx.android.synthetic.main.setting_fragment.*

class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.setting_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkCurrentTheme()
        setThemeSubmitClickListener()
    }

    private fun checkCurrentTheme() {
        val mainApplication = requireActivity().application as MainApplication
        themeRadioGroup.check(
            when (mainApplication.themeInt) {
                ThemeUtils.THEME_YOUR_CUSTOM_LIGHT -> R.id.themeLight
                ThemeUtils.THEME_YOUR_CUSTOM_DARK -> R.id.themeDark
                else -> R.id.themeDefault
            }
        )
    }

    private fun setThemeSubmitClickListener() {
        themeSubmitButton.setOnClickListener {
            when (themeRadioGroup.checkedRadioButtonId) {
                R.id.themeDark -> {
                    setActivityTheme(ThemeUtils.THEME_YOUR_CUSTOM_DARK)
                }

                R.id.themeLight -> {
                    setActivityTheme(ThemeUtils.THEME_YOUR_CUSTOM_LIGHT)
                }
                else -> {
                    setActivityTheme(ThemeUtils.THEME_MATERIAL_DEFAULT)
                }
            }
        }
    }

    private fun setActivityTheme(themeInt: Int) {
        val mainApplication = requireActivity().application as MainApplication
        if (mainApplication.themeInt != themeInt) {
            ThemeUtils.changeToTheme(requireActivity(), themeInt)
            mainApplication.themeInt = themeInt
        }
    }
}
