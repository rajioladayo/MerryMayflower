package com.rharj.merrymayflower.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.rharj.merrymayflower.util.CustomFontHelper;

/**
 * Created by Raji Oladayo on 11/11/2015.
 */
public class BoldCustomTextView extends TextView {

    public BoldCustomTextView(Context context){super(context);}

    public BoldCustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomFontHelper.setCustomFont(this, "fonts/Roboto-Bold.ttf", context);
    }

    public BoldCustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        CustomFontHelper.setCustomFont(this, "fonts/Roboto-Bold.ttf", context);
    }
}
