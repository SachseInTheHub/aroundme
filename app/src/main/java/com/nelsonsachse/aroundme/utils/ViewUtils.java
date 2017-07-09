package com.nelsonsachse.aroundme.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

public class ViewUtils {

    public static void hideKeyboard(final Context context) {
        final InputMethodManager inputMethodManager
                = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }
}
