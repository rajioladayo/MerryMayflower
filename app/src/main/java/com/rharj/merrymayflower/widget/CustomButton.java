package com.rharj.merrymayflower.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.rharj.merrymayflower.util.CustomFontHelper;

/**
 * Created by Raji Oladayo on 11/11/2015.
 */
public class CustomButton extends Button {

    public CustomButton(Context context) {
        super(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomFontHelper.setCustomFont(this, "fonts/Roboto-Bold.otf", context);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        CustomFontHelper.setCustomFont(this, "fonts/Roboto-Bold.otf", context);
    }
}
