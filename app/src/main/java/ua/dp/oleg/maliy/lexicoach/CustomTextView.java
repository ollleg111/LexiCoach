package ua.dp.oleg.maliy.lexicoach;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Owner on 09.12.2016.
 */
public class CustomTextView extends TextView {

    public static final String ANDROID_SCHEMA = "android.schema";

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);

        Typeface customFont = FontCache.getTypeface(context, "Gruppo-Regular");

        setTypeface(customFont);
    }

    private Typeface selectTypeface(Context context, int textStyle) {

        switch (textStyle) {
            case Typeface.BOLD: // bold
                return FontCache.getTypeface(context, "HKGrotesk-Italic");

            case Typeface.ITALIC: // italic
                return FontCache.getTypeface(context, "HKGrotesk-Italic");

            case Typeface.BOLD_ITALIC: // bold italic
                return FontCache.getTypeface(context, "HKGrotesk-Italic");

            case Typeface.NORMAL: // regular
            default:
                return FontCache.getTypeface(context, "HKGrotesk-Italic");
        }
    }
}
