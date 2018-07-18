package com.github.dyvoker.shadowutils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.github.dyvoker.shadow_lib.ShadowUtils;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Sample with text.
		DrawableTextShadowSample drawableTextShadowSample =
			new DrawableTextShadowSample("Sample Text", 32);
		FrameLayout textSample = findViewById(R.id.text_sample);
		textSample.setBackgroundDrawable(drawableTextShadowSample);

		// Sample with canvas.
		DrawableShadowSample drawableShadowSample = new DrawableShadowSample();
		FrameLayout canvasSample = findViewById(R.id.canvas_sample);
		canvasSample.setBackgroundDrawable(drawableShadowSample);

		// Sample with bitmap.
		Bitmap iconHeart = BitmapFactory.decodeResource(getResources(), R.drawable.ic_heart);
		Bitmap iconHeartWithShadow = ShadowUtils.addShadow(
			iconHeart,
			0x80000000,
			3,
			2,
			2
		);
		BitmapDrawable bitmapDrawable = new BitmapDrawable(iconHeartWithShadow);
		FrameLayout bitmapSample = findViewById(R.id.bitmap_sample);
		bitmapSample.setBackgroundDrawable(bitmapDrawable);
	}
}
