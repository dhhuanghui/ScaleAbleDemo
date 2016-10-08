package dhhuanghui.com.scaleabledemo;

import android.app.Activity;
import android.graphics.Matrix;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;

/**
 * 商家详情页面效果控制类，不要随意更改
 * 
 * @author huanghui
 * 
 */
public class ScaleAbleControler implements AnimatorListener, AnimatorUpdateListener {

	public static final String TAG = "ScaleAbleControler";
	// private View mTopView;
	private View mBottomView;
	private GestureDetector mGestureDetector;
	private LayoutParams lp;
	private int topMiniHeight;
	private int[] location = new int[2];;
	private int titleHeight;
	private ObjectAnimator animator;
	private float velocityY;
	private float lastY;
	private int contentHeight;
	private long currentPlayTime = 0L;
	private long totalPlayTime = 600L;
	private ScaleListener scaleListener;
	private static Matrix matrix = new Matrix();
	private MyNoScrollListView mListView;
	private HeaderView mHeaderView;
	public boolean flagScaleSmall = true;
//	private View header;

	public ScaleAbleControler(Activity context) {
		mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				return false;
			}

			/** 确定是单次点击 */
			@Override
			public boolean onSingleTapConfirmed(MotionEvent e) {
				boolean bool = true;
				float f = e.getY();
				mBottomView.getLocationOnScreen(location);
				if ((f < location[1] - titleHeight) && (f > 0.0F)) {
					scaleInAnimation();
				} else {
					bool = false;
				}
				return bool;
			}

		});
	}

	public void init(Activity context, View bottomView, MyNoScrollListView myListView, HeaderView headerView) {
		myListView.setTouchListener(new MyNoScrollListView.MotionEventListener() {

			@Override
			public boolean onTouchEvent(MotionEvent e) {
				return dispatchTouchEvent(e);
			}

			@Override
			public boolean onTouch(MotionEvent e) {
				return judgeTouch(e);
			}

		});
		this.mHeaderView = headerView;
		this.mBottomView = bottomView;
		this.mListView = myListView;
		lp = MainActivity.mTopView.getLayoutParams();
		topMiniHeight = lp.height;
		scaleListener = new ScaleListener() {
			public void onClickEnd() {
			}

			public void onClickStart() {
			}

			public void onReachEnd() {
			}

			public void onReachStart() {
			}

			public void onScale(int param1, int param2, int param3) {
			}
		};
		View localView = context.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
		int headerViewHeight = mHeaderView.getHeight();
		if (headerViewHeight == 0) {
			int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
			int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
			mHeaderView.measure(w, h);
			headerViewHeight = mHeaderView.getMeasuredHeight();
		}
//		int h = localView.getMeasuredHeight();
		contentHeight = localView.getMeasuredHeight() - headerViewHeight;
		mHeaderView.postDelayed(new Runnable() {

			@Override
			public void run() {
				int[] arrayOfInt = new int[2];
				MainActivity.mTopView.getLocationOnScreen(arrayOfInt);
				titleHeight = arrayOfInt[1];
			}
		}, 500);
	}


	public boolean judgeTouch(MotionEvent e) {
		boolean bool = true;
		float f = e.getY();
		mBottomView.getLocationOnScreen(location);
		if ((f < location[1] - titleHeight) && (f > 0.0F)) {
			bool = false;
		}
		return bool;
	}

	private float y = 0;
	private float x = 0;

	public boolean dispatchTouchEvent(MotionEvent e) {
		boolean bool = false;

		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			y = e.getY();
			x = e.getX();
			break;
		case MotionEvent.ACTION_UP:
			float currentY = e.getY();
			float currentX = e.getX();
			int delY = (int) (currentY - y);
			int delX = (int) (currentX - x);
			if (flagScaleSmall && Math.abs(delY) > Math.abs(delX)) {// 滚动更接近垂直位置并且当前没有放大
				mBottomView.getLocationOnScreen(location);
				if ((y < location[1] - titleHeight) && (y > 0.0F)) {
					scaleInAnimation();
				}
			}
			break;
		default:
			break;
		}

		bool = mGestureDetector.onTouchEvent(e);
		return bool;
	}

	private void cancelAnimator() {
		if ((animator != null) && (animator.isRunning())) {
			currentPlayTime = animator.getCurrentPlayTime();
			animator.cancel();
			animator = null;
		}
	}

	public boolean getScaleSmallFlag() {
		return flagScaleSmall;
	}

	public void setH(int paramInt) {
//		Log.d("setH", ""+paramInt);
		lp.height = paramInt;
		MainActivity.mTopView.setLayoutParams(lp);
	}

	public void scaleInAnimation() {
		MainActivity.mTopView.scrollTo(0, 0);
		mListView.scrollTo(0, 0);
		if (lp.height == topMiniHeight) {
			cancelAnimator();
			flagScaleSmall = false;
			int[] arrayOfInt2 = new int[2];
			arrayOfInt2[0] = lp.height;
			arrayOfInt2[1] = contentHeight;
			Log.d("lp.height", ""+lp.height);
			Log.d("contentHeight", ""+contentHeight);
			animator = ObjectAnimator.ofInt(this, "h", arrayOfInt2).setDuration(totalPlayTime - currentPlayTime);
			animator.start();
			animator.addListener(this);
			animator.addUpdateListener(this);
			scaleListener.onClickStart();
		} else if (lp.height == contentHeight) {
			cancelAnimator();
			flagScaleSmall = true;
			int[] arrayOfInt1 = new int[2];
			arrayOfInt1[0] = lp.height;
			arrayOfInt1[1] = topMiniHeight;
			animator = ObjectAnimator.ofInt(this, "h", arrayOfInt1).setDuration(totalPlayTime - currentPlayTime);
			animator.start();
			animator.addListener(this);
			animator.addUpdateListener(this);
			scaleListener.onClickEnd();
		}
	}

	@Override
	public void onAnimationCancel(Animator arg0) {
	}

	@Override
	public void onAnimationEnd(Animator arg0) {
		currentPlayTime = 0L;
		mListView.smoothScrollToPosition(0);
		// updateExceptRect();
		if (((Integer) ((ObjectAnimator) arg0).getAnimatedValue()).intValue() == topMiniHeight) {
			scaleListener.onReachStart();
		} else {
			scaleListener.onReachEnd();
		}
	}

	@Override
	public void onAnimationRepeat(Animator arg0) {

	}

	@Override
	public void onAnimationStart(Animator arg0) {
		mListView.smoothScrollToPosition(0);
	}

	@Override
	public void onAnimationUpdate(ValueAnimator arg0) {
		scaleListener.onScale(topMiniHeight, contentHeight, ((Integer) arg0.getAnimatedValue()).intValue());
		// updateExceptRect();
	}

	public void setScaleListener(ScaleListener paramScaleListener) {
		scaleListener = paramScaleListener;
	}

	public static abstract interface ScaleListener {
		public abstract void onClickEnd();

		public abstract void onClickStart();

		public abstract void onReachEnd();

		public abstract void onReachStart();

		public abstract void onScale(int paramInt1, int paramInt2, int paramInt3);
	}

	public static void matrix(int screenWidth, ImageView imageView, LayoutParams layoutParams) {
		int intrinsicWidth = imageView.getDrawable().getIntrinsicWidth();
		float f = screenWidth * 1.0f / intrinsicWidth;
		matrix.reset();
		float g = 0;
		matrix.setScale(f, f);
		g = layoutParams.height - f * imageView.getDrawable().getIntrinsicHeight();
		matrix.postTranslate(0.0F, g / 2.0F);
		imageView.setImageMatrix(matrix);
	}

}
