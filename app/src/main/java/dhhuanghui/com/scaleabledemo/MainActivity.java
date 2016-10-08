package dhhuanghui.com.scaleabledemo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity implements ScaleAbleControler.ScaleListener {

    private ArrayList<ImageView> mImageViewList = new ArrayList<ImageView>();

    public static View mTopView;
    private int width;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        width = getResources().getDisplayMetrics().widthPixels;
        height = getResources().getDisplayMetrics().heightPixels;
        final MyNoScrollListView listView = (MyNoScrollListView) findViewById(R.id.listView);
        final HeaderView headerView = (HeaderView) findViewById(R.id.headerView);
//        View header = getLayoutInflater().inflate(R.layout.layout_vendordetail_headview1, null);
//        mTopView = header.findViewById(R.id.vendor_info_gallery);
//        ViewPagerFixed viewPager = (ViewPagerFixed) header.findViewById(R.id.vendor_info_pics);
        View header = getLayoutInflater().inflate(R.layout.header_lv, null);
        mTopView = header.findViewById(R.id.layout_topview);
        ViewPager viewPager = (ViewPager) header.findViewById(R.id.viewPager);
        VPAdapter vpAdapter = new VPAdapter(this, new PhotoImage() {
            @Override
            public void addImageView(ImageView imageView) {
                mImageViewList.add(imageView);
                ScaleAbleControler.matrix(width, imageView, mTopView.getLayoutParams());
//                if (imageView instanceof PhotoView && RuntimeUtils.isReachEnd) {
//                    PhotoView photoView = (PhotoView) imageView;
//                    if (!photoView.canZoom()) {
//                        photoView.setZoomable(true);
//                    }
//                }
            }

            @Override
            public void removeImageView(ImageView imageView) {
                mImageViewList.remove(imageView);
            }
        });
        viewPager.setAdapter(vpAdapter);
        listView.addHeaderView(header);

        View header2 = getLayoutInflater().inflate(R.layout.header2_lv, null);
        final View bottomView = header2.findViewById(R.id.layout_bottom);
        listView.addHeaderView(header2);

        MyAdapter myAdapter = new MyAdapter(this);
        listView.setAdapter(myAdapter);
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ScaleAbleControler scaleAbleControler = new ScaleAbleControler(MainActivity.this);
                scaleAbleControler.init(MainActivity.this, bottomView, listView, headerView);
                scaleAbleControler.setScaleListener(MainActivity.this);
            }
        }, 3000);
    }

    @Override
    public void onClickEnd() {

    }

    @Override
    public void onClickStart() {

    }

    @Override
    public void onReachEnd() {
//        RuntimeUtils.isReachEnd = true;
        openZoom();
    }

    @Override
    public void onReachStart() {
//        RuntimeUtils.isReachEnd = false;
        closeZoom();
//        findViewById(R.id.verdor_bottom).setVisibility(View.VISIBLE);
    }

    private void openZoom() {
//        Iterator localIterator = mImageViewList.iterator();
//        while (localIterator.hasNext()) {
//            PhotoView localImageView = (PhotoView) localIterator.next();
//            localImageView.setZoomable(true);
//        }
    }

    private void closeZoom() {
//        Iterator localIterator = mImageViewList.iterator();
//        while (localIterator.hasNext()) {
//            PhotoView localImageView = (PhotoView) localIterator.next();
//            localImageView.setZoomable(false);
//        }
    }

    @Override
    public void onScale(int bannerMinHeight, int bannerMaxHeight, int variableHeight) {
        if (isFinishing()) {
            return;
        }
        Iterator localIterator = mImageViewList.iterator();
        while (localIterator.hasNext()) {
            if (mTopView == null) {
                break;
            }
            ImageView localImageView = (ImageView) localIterator.next();
            ScaleAbleControler.matrix(width, localImageView, mTopView.getLayoutParams());
        }
//        View localView = findViewById(R.id.verdor_bottom);
//        if (bannerMinHeight == variableHeight)
//            localView.setVisibility(View.VISIBLE);
//        else {
//            if (localView.getVisibility() == View.VISIBLE)
//                localView.setVisibility(View.GONE);
//        }
    }

}
