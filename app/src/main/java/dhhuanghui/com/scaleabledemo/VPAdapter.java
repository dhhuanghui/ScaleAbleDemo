package dhhuanghui.com.scaleabledemo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by THINK on 2016/9/28.
 */
public class VPAdapter extends PagerAdapter {
    private int[] resId = new int[]{R.mipmap.banner11, R.mipmap.banner22, R.mipmap.banner33};
    private Context mContext;
    private PhotoImage photoImage;

    public VPAdapter(Context context, PhotoImage photoImage) {
        mContext = context;
        this.photoImage = photoImage;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = (ImageView) View.inflate(mContext, R.layout.ui_photo_viewpager_item, null);
        imageView.setImageResource(resId[position]);
        photoImage.addImageView(imageView);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        photoImage.removeImageView((ImageView) object);
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return resId.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
