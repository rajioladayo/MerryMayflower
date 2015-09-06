package com.rharj.merrymayflower.custom;

/**
 * Created by Raji Oladayo on 9/6/15.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.graphics.Typeface;
import android.widget.TextView;

public class CustomTextView extends TextView{
    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(this.getContext().getAssets(), "Roboto-Regular.ttf");
            setTypeface(tf);
        }
    }
}
