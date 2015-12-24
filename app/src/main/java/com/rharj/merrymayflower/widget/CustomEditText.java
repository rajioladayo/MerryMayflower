package com.rharj.merrymayflower.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.rharj.merrymayflower.util.CustomFontHelper;

/**
 * Created by Raji Oladayo on 11/11/2015.
 */
public class CustomEditText extends EditText {

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomFontHelper.setCustomFont(this, "fonts/Roboto-Light.ttf", context);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        CustomFontHelper.setCustomFont(this, "fonts/Roboto-Light.ttf", context);
    }
}
