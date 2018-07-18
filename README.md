# Shadow Utils
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)

### Preview
![Preview screenshot](https://raw.githubusercontent.com/dyvoker/shadow-utils/master/sample_screenshot.png)

### Usage ([sample](https://github.com/dyvoker/shadow-utils/tree/master/sample))
Add dependency:
```gradle
dependencies {
    implementation 'com.github.dyvoker:shadow-lib:1.0'
}
```

Using canvas:
```java
@Override
public void draw(@NonNull Canvas canvas) {
   CanvasWithShadow shadow = new CanvasWithShadow(canvas);
   Canvas tempCanvas = shadow.getCanvas();

   // Draw primitives.
   float centerX = tempCanvas.getWidth() / 2;
   float centerY = tempCanvas.getHeight() / 2;
   float radius = Math.min(centerX, centerY) - 50.0f;
   tempCanvas.drawCircle(centerX, centerY, radius, paint);
   tempCanvas.drawRect(centerX, centerY, centerX + radius, centerY + radius, paint);

   // Draw shadow.
   shadow.draw(canvas, 0x80000000, 3, 2, 2);
}
```

Using bitmap:
```java
Bitmap iconHeart = BitmapFactory.decodeResource(getResources(), R.drawable.ic_heart);
Bitmap iconHeartWithShadow = ShadowUtils.addShadow(iconHeart, 0x80000000, 3, 2, 2);
BitmapDrawable bitmapDrawable = new BitmapDrawable(iconHeartWithShadow);
FrameLayout bitmapSample = findViewById(R.id.bitmap_sample);
bitmapSample.setBackgroundDrawable(bitmapDrawable);
```
