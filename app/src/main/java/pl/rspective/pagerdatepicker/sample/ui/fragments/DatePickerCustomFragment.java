package pl.rspective.pagerdatepicker.sample.ui.fragments;

import android.app.DatePickerDialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;

import pl.rspective.pagerdatepicker.PagerDatePickerDateFormat;
import pl.rspective.pagerdatepicker.adapter.DatePagerFragmentAdapter;
import pl.rspective.pagerdatepicker.model.DateItem;
import pl.rspective.pagerdatepicker.sample.MainActivity;
import pl.rspective.pagerdatepicker.sample.R;
import pl.rspective.pagerdatepicker.sample.custom.CustomDateAdapter;
import pl.rspective.pagerdatepicker.view.DateRecyclerView;
import pl.rspective.pagerdatepicker.view.RecyclerViewInsetDecoration;

public class DatePickerCustomFragment extends Fragment {

    private DateRecyclerView dateList;
    private ViewPager pager;
    private CustomDateAdapter dateAdapter;


    public static DatePickerCustomFragment newInstance() {
        return new DatePickerCustomFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_picker_custom, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pager = (ViewPager) view.findViewById(R.id.pager);
        dateList = (DateRecyclerView) view.findViewById(R.id.date_list);

        // dateList.addItemDecoration(new RecyclerViewInsetDecoration(getActivity(), R.dimen.date_card_insets));


        Date start = null;
        Date end = null;
        Date defaultDate = null;

        try {
            start = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse("01-08-2016");
            end = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse("13-04-2017");

            defaultDate = PagerDatePickerDateFormat.DATE_PICKER_DD_MM_YYYY_FORMAT.parse("12-01-2017");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        dateAdapter = new CustomDateAdapter(start, end, defaultDate);
        dateList.setAdapter(dateAdapter);

        DatePagerFragmentAdapter fragmentAdapter = new DatePagerFragmentAdapter(getFragmentManager(), dateList.getDateAdapter()) {
            @Override
            protected Fragment getFragment(int position, long date) {
                return SimplePageFragment.newInstance(position, date);
            }
        };

        pager.setAdapter(fragmentAdapter);
        dateList.setPager(pager);

        dateList.setDatePickerListener(new DateRecyclerView.DatePickerListener() {
            @Override
            public void onDatePickerItemClick(DateItem dateItem, int position) {

                LinearLayoutManager llm = (LinearLayoutManager) dateList.getLayoutManager();
                llm.scrollToPositionWithOffset(position, 400);
                // dateAdapter.focusPosition(position);

                //   llm.focusPosition(agendaPosition);

                //llm.scrollToPositionWithOffset(position, 0);
                // llm.scrollToPosition(position);
                //llm.moveView((position-2),position);
                Toast.makeText(getActivity(), "Clicked: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDatePickerPageSelected(int position) {

            }

            @Override
            public void onDatePickerPageStateChanged(int state) {

            }

            @Override
            public void onDatePickerPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
        });

        // Set animation for current selected view
        // dateAdapter.setCurrentViewAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.blinking_animation));
    }
}
