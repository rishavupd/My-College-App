package com.example.collegeapp.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.collegeapp.R;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

public class HomeFragment extends Fragment {

    private SliderLayout sliderLayout;
    private ImageView map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        sliderLayout = view.findViewById(R.id.slider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(2);


        setSliderViews();

        map = view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
        return view;
    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0, 0?q=Bangalore Technological Institute, Chikkakannalli, Bengaluru, Karnataka");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);

    }

    private void setSliderViews() {
        for(int i = 0; i<4; i++) {
            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i) {
                case 0: 
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/my-college-app-3394e.appspot.com/o/Gallery%2FImage%2F%5BB%4055238bcjpg?alt=media&token=c375c512-8a2f-4e58-9115-fd99af646016");
                    break;
            case 1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/my-college-app-3394e.appspot.com/o/Gallery%2FImage%2F%5BB%40b8a815jpg?alt=media&token=d50e1a40-af98-490d-ada1-36b363f54799");
                    break;
            case 2: 
                sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/my-college-app-3394e.appspot.com/o/Gallery%2FImage%2F%5BB%404ca084djpg?alt=media&token=de7d22db-b377-4b4f-b60f-c7cd172a34b6");
                break;
            case 3:
                sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/my-college-app-3394e.appspot.com/o/Gallery%2FImage%2F%5BB%40e5b0bc8jpg?alt=media&token=611c9af6-4c2e-4c3c-a58f-7d8e99fe67bb");
                break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderLayout.addSliderView(sliderView);
        }
    }
}