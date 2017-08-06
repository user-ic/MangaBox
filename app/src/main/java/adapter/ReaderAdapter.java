package adapter;

/**
 * Created by android on 02-11-2016.
 */

import android.content.Context;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sylach.mangacube.R;

import java.util.ArrayList;


public class ReaderAdapter extends PagerAdapter {
    private Context context;
    private String[] IMAGES = null;
    private View view;
    private RelativeLayout rlTop, rlBottom;
    private TextView tvCMenu;

    public ReaderAdapter(Context context, String[] IMAGES, RelativeLayout rlTop, RelativeLayout rlBottom, TextView tvCMenu) {
        this.IMAGES = IMAGES;
        this.context = context;
        this.rlTop = rlTop;
        this.rlBottom = rlBottom;
        this.tvCMenu = tvCMenu;

        this.rlTop.setVisibility(View.VISIBLE);
        this.rlBottom.setVisibility(View.VISIBLE);
    }

    @Override
    public int getCount() {
        return IMAGES.length;
    }


    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.li_page, null);
        final View view2 = inflater.inflate(R.layout.activity_read, null);
        ((ViewPager) collection).addView(view);

        final WebView wvPage = (WebView) view.findViewById(R.id.webViewPage);
        final RelativeLayout wvPOnLoad = (RelativeLayout) view.findViewById(R.id.relativeLayoutOnLoad);
        final RelativeLayout wvPOnError = (RelativeLayout) view.findViewById(R.id.relativeLayoutOnError);

        wvPOnLoad.setVisibility(View.VISIBLE);
        wvPOnError.setVisibility(View.GONE);
        //.
        wvPage.getSettings().setBuiltInZoomControls(true);
        wvPage.getSettings().setDisplayZoomControls(false);
        wvPage.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        wvPage.requestFocusFromTouch();
        wvPage.getSettings().setLoadWithOverviewMode(true);
        wvPage.getSettings().setUseWideViewPort(true);
        wvPage.loadUrl(IMAGES[position]);
        wvPage.setWebViewClient(new WebViewClient());
        wvPage.getSettings().setLoadsImagesAutomatically(true);
        wvPage.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                wvPOnLoad.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                wvPOnError.setVisibility(View.VISIBLE);
            }
        });

        wvPage.setOnTouchListener(new View.OnTouchListener() {

            public final static int FINGER_RELEASED = 0;
            public final static int FINGER_TOUCHED = 1;
            public final static int FINGER_DRAGGING = 2;
            public final static int FINGER_UNDEFINED = 3;

            private int fingerState = FINGER_RELEASED;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent)
            {
                switch (motionEvent.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        if (fingerState == FINGER_RELEASED)
                            fingerState = FINGER_TOUCHED;
                        else
                            fingerState = FINGER_UNDEFINED;
                        break;
                    case MotionEvent.ACTION_UP:
                        if(fingerState != FINGER_DRAGGING)
                        {
                            fingerState = FINGER_RELEASED;
                            if(rlBottom.getVisibility() == View.VISIBLE && rlTop.getVisibility() == View.VISIBLE)
                            {
                                rlTop.setVisibility(View.GONE);
                                rlBottom.setVisibility(View.GONE);
                            }
                            else
                            {
                                rlTop.setVisibility(View.VISIBLE);
                                rlBottom.setVisibility(View.VISIBLE);
                            }
                        }
                        else if (fingerState == FINGER_DRAGGING)
                            fingerState = FINGER_RELEASED;
                        else
                            fingerState = FINGER_UNDEFINED;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (fingerState == FINGER_TOUCHED || fingerState == FINGER_DRAGGING)
                            fingerState = FINGER_DRAGGING;
                        else
                            fingerState = FINGER_UNDEFINED;
                        break;
                    default:
                        fingerState = FINGER_UNDEFINED;
                }
                return false;
            }
        });

        Handler handlerTimer = new Handler();
        handlerTimer.postDelayed(new Runnable(){
            public void run() {
                rlTop.setVisibility(View.GONE);
                rlBottom.setVisibility(View.GONE);
            }}, 4000);

        tvCMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "menu", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}