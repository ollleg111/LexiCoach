package ua.dp.oleg.maliy.lexicoach;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView extends TextView {

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        int textStyle = attrs.getAttributeIntValue(Const.ANDROID_TYPEFACE, "textStyle", Typeface.NORMAL);

        Typeface customFont = FontCache.getTypeface(context, "Gruppo-Regular");

        setTypeface(customFont);
    }

    private Typeface selectTypeface(Context context, int textStyle) {

        switch (textStyle) {
            case Typeface.BOLD:
                return FontCache.getTypeface(context, "HKGrotesk-Bold");

            case Typeface.ITALIC:
                return FontCache.getTypeface(context, "HKGrotesk-Italic");

            case Typeface.NORMAL:
            default:
                return FontCache.getTypeface(context, "Gruppo-Regular");
        }
    }
}
