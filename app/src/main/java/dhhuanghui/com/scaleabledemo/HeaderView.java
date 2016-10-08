package dhhuanghui.com.scaleabledemo;


import android.app.Activity;
import android.content.Context;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HeaderView extends RelativeLayout {
	private View mLayoutLeft;
	private View mLayoutRight;
	private View mHeader;
	private TextView mTvMiddle;
	private TextView mTvRight;
	private ImageView mIvRight;
	private RelativeLayout mLayoutBg;
	private Context mContext;
	
	public HeaderView(Context context) {
		super(context);
		mContext = context;
		inflate(null);
	}

	public HeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		inflate(attrs);
	}

	private void inflate(AttributeSet attrs) {
		LayoutInflater infalter = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mHeader = infalter.inflate(R.layout.header, this, true);
		mLayoutLeft = findViewById(R.id.left);
		mLayoutBg = (RelativeLayout) findViewById(R.id.layout_headerview);
		mLayoutRight = findViewById(R.id.right);
		mTvRight = (TextView) findViewById(R.id.tv_right);
		mTvMiddle = (TextView) findViewById(R.id.tv_middle);
		mIvRight = (ImageView) findViewById(R.id.iv_right);
		setDefaultLeftClickListener();
	}

	/**
	 * 设置背景颜色
	 * 
	 * @param c
	 */
	public void setLayoutBg(int c) {
		mLayoutBg.setBackgroundColor(c);
	}

	public void setRightImageVisibleAndSrc(int resID) {
		mLayoutRight.setVisibility(View.VISIBLE);
		mTvRight.setVisibility(View.GONE);
		mIvRight.setVisibility(View.VISIBLE);
		mIvRight.setImageResource(resID);
	}

	public View getRightBtn() {
		return mLayoutRight;
	}

	public void setTvRight(int resID) {
		mLayoutRight.setVisibility(View.VISIBLE);
		mTvRight.setVisibility(View.VISIBLE);
		mTvRight.setText(resID);
	}

	public void setTvRight(String content) {
		mTvRight.setText(content);
	}

	public void setTvRight(String content, int color) {
		mTvRight.setTextColor(color);
		setTvRight(content);
	}

	/** 设置中间的文字加粗 */
	public void setTvMiddleBold() {
		TextPaint paint = mTvMiddle.getPaint();
		paint.setFakeBoldText(true);
	}

	/** 设置线性布局中文本的内容 */
	public void setTvMidText(String content) {
		mTvMiddle.setText(content);
		mTvMiddle.setVisibility(View.VISIBLE);
	}
	

	public void setTvMidText(int resid) {
		mTvMiddle.setText(resid);
		//mLayoutRight.setVisibility(View.VISIBLE);
		mTvMiddle.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置中间文本可见，文本内容以及文本颜色
	 */
	public void setTvMidVisibleAndTextAndTextColor(int resId, int color) {
		mTvMiddle.setVisibility(View.VISIBLE);
		mTvMiddle.setText(resId);
		mTvMiddle.setTextColor(color);
	}

	public void setTvMidVisibleAndTextAndTextColor(String content, int color) {
		mTvMiddle.setVisibility(View.VISIBLE);
		mTvMiddle.setText(content);
		mTvMiddle.setTextColor(color);
	}

	// public void setTvMidTextColor(int color) {
	// mTvMiddle.setTextColor(color);
	// }
	/** 设置TitleBar背景颜色 */
	public void setHeaderBackgroundColor(int color) {
		mHeader.setBackgroundResource(color);
	}

	/** 设置TitleBar背景图片 */
	public void setHeaderBackground(int resId) {
		mHeader.setBackgroundDrawable(getResources().getDrawable(resId));
	}

	/** 设置TitleBar只有左边控件可见 */
	public void setLeftVisible() {
		mTvMiddle.setVisibility(View.INVISIBLE);
		mLayoutRight.setVisibility(View.INVISIBLE);
		mLayoutLeft.setVisibility(View.VISIBLE);
	}

	public void setAllVisible() {
		mTvMiddle.setVisibility(View.VISIBLE);
		mLayoutRight.setVisibility(View.VISIBLE);
		mLayoutLeft.setVisibility(View.VISIBLE);
	}

	public void setRightSingleVisible() {
		mLayoutRight.setVisibility(View.VISIBLE);
	}

	/** 设置TitleBar只有右边控件可见 */
	public void setRightVisible() {
		mLayoutLeft.setVisibility(View.INVISIBLE);
		mTvMiddle.setVisibility(View.INVISIBLE);
		mLayoutRight.setVisibility(View.VISIBLE);
	}

	public void setMiddleVisible() {
		mTvMiddle.setVisibility(View.VISIBLE);
	}

	/** 设置TitleBar中间控件不可见 */
	public void setMiddleInVisible() {
		mTvMiddle.setVisibility(View.INVISIBLE);
	}

	/** 设置TitleBar左边控件不可见 */
	public void setLeftInVisible() {
		mLayoutLeft.setVisibility(View.INVISIBLE);
	}

	/** 设置TitleBar右边控件不可见 */
	public void setRightInVisible() {
		mLayoutRight.setVisibility(View.INVISIBLE);
	}

	/** 设置TitleBar所有控件不可见 */
	public void setAllInVisible() {
		mLayoutRight.setVisibility(View.INVISIBLE);
		mLayoutLeft.setVisibility(View.INVISIBLE);
		mTvMiddle.setVisibility(View.INVISIBLE);
	}
	
	private void setDefaultLeftClickListener(){
		mLayoutLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((Activity)mContext).finish();
			}
		});
	}

	public void setRightOnClickListener(OnClickListener l) {
		mLayoutRight.setOnClickListener(l);
	}

	public void setLeftOnClickListener(OnClickListener l) {
		mLayoutLeft.setOnClickListener(l);
	}

	public void setHeaderClickListener(OnClickListener l) {
		setOnClickListener(l);
	}

	public void setMiddleOnClickListener(OnClickListener l) {
		mTvMiddle.setOnClickListener(l);
	}
}
