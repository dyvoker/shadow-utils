package com.github.dyvoker.shadow_lib;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

/**
 * Helping class for working with canvas.
 * Get {@link Canvas} and manually draw on it,
 * then call {@link #draw(Canvas, int, float, float, float, boolean)}.
 * It automatically draw canvas content with shadow to another canvas.
 */
@SuppressWarnings({ "WeakerAccess", "unused" })
public class CanvasWithShadow {

	@NonNull
	private final Bitmap canvasBitmap;
	@NonNull
	private final Canvas canvas;

	private Bitmap bitmapWithShadow; // Cached bitmap with shadow.

	/**
	 * @param canvas Canvas of view when you want to draw.
	 */
	public CanvasWithShadow(@NonNull Canvas canvas) {
		this(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
	}

	/**
	 * @param canvas Canvas of view when you want to draw.
	 * @param config Config of bitmap to draw on.
	 */
	public CanvasWithShadow(@NonNull Canvas canvas, @NonNull Bitmap.Config config) {
		this(canvas.getWidth(), canvas.getHeight(), config);
	}

	/**
	 * @param width Custom width of canvas.
	 * @param height Custom height of canvas.
	 */
	public CanvasWithShadow(int width, int height) {
		this(width, height, Bitmap.Config.ARGB_8888);
	}

	/**
	 * @param width Custom width of canvas.
	 * @param height Custom height of canvas.
	 * @param config Config of bitmap to draw on.
	 */
	public CanvasWithShadow(int width, int height, @NonNull Bitmap.Config config) {
		canvasBitmap = Bitmap.createBitmap(width, height, config);
		canvas = new Canvas(canvasBitmap);
	}

	/**
	 * @return Special canvas. You must draw on it,
	 * it needs for working a method {@link #draw(Canvas, int, float, float, float, boolean)}.
	 */
	@NonNull
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * Draw canvas content on another canvas and add shadow by parameters.
	 *
	 * @param canvas Canvas to draw content with shadow/
	 * @param shadowColor Color of a shadow.
	 * @param shadowRadiusDp Blur-radius of a shadow corner (dp).
	 * @param offsetXDp Shadow translation by X-axis (dp).
	 * @param offsetYDp Shadow translation by Y-axis (dp).
	 * @param forceRedraw If true - redraw cached bitmap with shadow.
	 */
	public void draw(
		@NonNull Canvas canvas,
		@ColorInt int shadowColor,
		float shadowRadiusDp,
		float offsetXDp,
		float offsetYDp,
		boolean forceRedraw
	) {
		// Redraw cached image, only if it needed.
		if (bitmapWithShadow == null || forceRedraw) {
			if (bitmapWithShadow != null) {
				bitmapWithShadow.recycle();
			}
			bitmapWithShadow = ShadowUtils.addShadow(
				canvasBitmap,
				shadowColor,
				shadowRadiusDp,
				offsetXDp,
				offsetYDp
			);
			canvas.drawBitmap(bitmapWithShadow, 0, 0, null);
			return;
		}
		canvas.drawBitmap(bitmapWithShadow, 0, 0, null);
	}

	/**
	 * @param canvas Canvas to compare with.
	 * @return Return true - if cached bitmap with shadow have same width and height as canvas.
	 */
	public boolean isSameSize(@NonNull Canvas canvas) {
		return isSameSize(canvas.getWidth(), canvas.getHeight());
	}

	/**
	 * @param width Width to compare with.
	 * @param height Height to compare with.
	 * @return Return true - if cached bitmap with shadow have same width and height.
	 */
	public boolean isSameSize(int width, int height) {
		if (bitmapWithShadow == null) {
			return false;
		}
		int bitmapWidth = bitmapWithShadow.getWidth();
		int bitmapHeight = bitmapWithShadow.getHeight();
		return bitmapWidth == width && bitmapHeight == height;
	}

	/**
	 * Remove cached bitmap.
	 */
	public void recycle() {
		if (bitmapWithShadow != null) {
			bitmapWithShadow.recycle();
		}
	}
}
