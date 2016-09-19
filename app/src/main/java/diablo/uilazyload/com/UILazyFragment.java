package diablo.uilazyload.com;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ProgressBar;

/**
 * Created by Diablo on 16/9/19.
 */
public class UILazyFragment extends Fragment implements ViewStub.OnInflateListener {

    private ViewStub firstStub;
    private ViewStub secondStub;
    private ViewStub thirdStub;

    private ProgressBar firstBar;
    private ProgressBar secondBar;
    private ProgressBar thirdBar;

    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View base = inflater.inflate(R.layout.activity_main, null);
        initStub(base);
        return base;
    }

    private void initStub(View base) {
        firstBar = (ProgressBar) base.findViewById(R.id.firstProgress);
        secondBar = (ProgressBar) base.findViewById(R.id.secondProgress);
        thirdBar = (ProgressBar) base.findViewById(R.id.thirdProgress);

        firstStub = (ViewStub) base.findViewById(R.id.firstBlock);
        secondStub = (ViewStub) base.findViewById(R.id.secondBlock);
        thirdStub = (ViewStub) base.findViewById(R.id.thirdBlock);

        handler = new Handler();
    }

    private void inflateViews() {
        firstStub.setOnInflateListener(this);
        firstStub.inflate();
    }

    @Override
    public void onResume() {
        super.onResume();
        inflateViews();
    }

    @Override
    public void onInflate(ViewStub viewStub, View view) {
        if (viewStub.equals(firstStub)) {
            //注意设置获取加载后跟布局view 通过inflateId
            firstBar.setVisibility(View.GONE);
            secondStub.setOnInflateListener(this);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    secondStub.inflate();
                }
            }, 700);
        } else if (viewStub.equals(secondStub)) {
            secondBar.setVisibility(View.GONE);
            thirdStub.setOnInflateListener(this);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    thirdStub.inflate();
                }
            }, 1000);
        } else if (viewStub.equals(thirdStub)) {
            thirdBar.setVisibility(View.GONE);
        }
    }
}
