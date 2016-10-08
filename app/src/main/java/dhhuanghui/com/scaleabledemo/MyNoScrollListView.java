package dhhuanghui.com.scaleabledemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class MyNoScrollListView extends ListView {

	private MotionEventListener touchListener;

	public MyNoScrollListView(Context context) {
		super(context);
	}

	public MyNoScrollListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyNoScrollListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setTouchListener(MotionEventListener e) {
		this.touchListener = e;
	}

	public static abstract interface MotionEventListener {
		public abstract boolean onTouchEvent(MotionEvent e);

		public abstract boolean onTouch(MotionEvent e);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		boolean b = true;
		if (touchListener != null) {
			b = touchListener.onTouch(ev);
		}
		if (b) {
			return super.onInterceptTouchEvent(ev);
		}
		return b;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (touchListener != null) {
			touchListener.onTouchEvent(ev);
		}
		try {
			return super.dispatchTouchEvent(ev);
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		touchListener = null;
		super.onDetachedFromWindow();
	}
}