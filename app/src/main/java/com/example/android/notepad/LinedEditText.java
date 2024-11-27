package com.example.android.notepad;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import android.widget.EditText;

public class LinedEditText extends EditText {
    private Paint linePaint;

    public LinedEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        // 初始化画笔
        linePaint = new Paint();
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setColor(0xFFAAAAAA); // 浅灰色线条
        linePaint.setStrokeWidth(1f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int height = getHeight();
        int lineHeight = getLineHeight();
        int totalLines = height / lineHeight;

        int baseline = getBaseline();
        for (int i = 0; i < totalLines; i++) {
            canvas.drawLine(0, baseline + i * lineHeight, getWidth(), baseline + i * lineHeight, linePaint);
        }

        super.onDraw(canvas);
    }
}