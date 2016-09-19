package diablo.uilazyload.com;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment, new UILazyFragment())
                .commit();
    }

}
