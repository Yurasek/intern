package com.example.customkeyboard;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

public class CustomView extends View{

    private final String MAX_TEXT_LENGTH = "$";

    private TextPaint numberPaint;

    private String displayedText = " ";

    private int canvasWidth;

    private int canvasHeight;

    private float centerX;

    private float baselineY;

    private float textWidth;

    private float textX;

    private TextView textView;

    public CustomView(Context context) {
        super(context);
    }
    public CustomView(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.CustomView);
        try {
            displayedText = typedArray.getString(R.styleable.CustomView_text);
        } finally {
            typedArray.recycle();
        }
        numberPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        numberPaint.setColor(ContextCompat.getColor(context, android.R.color.black));
        numberPaint.setTextSize(Math.round(32f * getResources().getDisplayMetrics().scaledDensity));
    }


    @Override
    protected void onDraw(Canvas canvas) {

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        centerX = canvasWidth * 0.5f;
        baselineY = Math.round(canvasHeight*0.8f);

        textWidth = numberPaint.measureText(displayedText);
        textX = Math.round(centerX - textWidth * 0.5f);

        canvas.drawText(displayedText, textX, baselineY, numberPaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        final Paint.FontMetrics fontMetrics = numberPaint.getFontMetrics();

        final int maxTextWidth = Math.round(numberPaint.measureText(MAX_TEXT_LENGTH)*3);
        final int maxTextHeight = Math.round(-fontMetrics.top + fontMetrics.bottom);

        final int measuredWidth = resolveSizeAndState(maxTextWidth, widthMeasureSpec, 0);
        final int measuredHeight = resolveSizeAndState(maxTextHeight, heightMeasureSpec, 0);

        //final int lolWidth = getMeasuredWidth();
        //final int lolHeight = getMeasuredHeight();

        //Log.d("lol", Integer.toString(lolWidth) + ", " + Integer.toString(lolHeight));

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

}

