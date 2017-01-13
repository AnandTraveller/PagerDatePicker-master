package pl.rspective.pagerdatepicker.sample;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;

import pl.rspective.pagerdatepicker.sample.ui.fragments.DatePickerFragment;


public class MainActivity extends ActionBarActivity {
    public static int width;
    public static int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_main_container, DatePickerFragment.newInstance())
                .commit();
    }

}
