package com.github.dyvoker.shadowutils;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.TypedValue;

import com.github.dyvoker.shadow_lib.CanvasWithShadow;

/**
 * Drawable for testing shadows of text.
 */
public class DrawableTextShadowSample extends Drawable {

	private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	@NonNull
	private final String text;

	public DrawableTextShadowSample(@NonNull String text, float textSizeDp) {
		paint.setColor(Color.WHITE);
		float textSize = TypedValue.applyDimension(
			TypedValue.COMPLEX_UNIT_DIP,
			textSizeDp,
			Resources.getSystem().getDisplayMetrics()
		);
		paint.setTextSize(textSize);
		this.text = text;
	}

	@Override
	public void draw(@NonNull Canvas canvas) {
		CanvasWithShadow shadow = new CanvasWithShadow(canvas);
		Canvas tempCanvas = shadow.getCanvas();
		float textY = paint.getTextSize();
		tempCanvas.drawText(text, 50, textY, paint);
		// Draw shadow.
		shadow.draw(canvas, 0x80000000, 2, 1, 1);
	}

	@Override
	public void setAlpha(int alpha) {
		paint.setAlpha(alpha);
	}

	@Override
	public void setColorFilter(@Nullable ColorFilter colorFilter) {
		paint.setColorFilter(colorFilter);
	}

	@Override
	public int getOpacity() {
		return PixelFormat.OPAQUE;
	}
}
