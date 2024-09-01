package com.example.collegeapp.ui.about;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.collegeapp.R;

import java.util.ArrayList;
import java.util.List;


public class AboutFragment extends Fragment {

    private ViewPager viewPager;
    private BranchAdapter adapter;
    private List<BranchModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        list = new ArrayList<>();
        list.add(new BranchModel(R.drawable.ic_aiml, "Artificial Intelligence and Machine Learning", "The department of Artificial Intelligence and Machine Learning was started in the year 2020 with an intake of 60 students. The department is headed by Dr. Asha Joseph who has more than twenty-two years of experience in teaching in reputed engineering colleges and Industry.The faculty members in the department are well qualified, experienced and committed towards the upliftment of the department, specialized in object-oriented system analysis and design, artificial intelligence, python programming, expert systems, multimedia, RDBMS, Machine Learning, neural networks, data communication, data structures and algorithms, operating system and network security."));
        list.add(new BranchModel(R.drawable.ic_civil, "Civil Engineering", "The department of Civil Engineering was started in the year 2010 with an intake of 60 students.Later on, the intake was increased to 120 students in the year 2014.The department is headed by Dr .B .M. RamalingaReddy who has more than thirty four years of experience in teaching from reputed engineering colleges.The faculty members in the department are well qualified, experienced and dedicate themselves for the upliftment of the department."));
        list.add(new BranchModel(R.drawable.ic_cse, "Computer Science Engineering", "The department of Computer Science and Engineering was started in the year 2010 with an intake of 60 students. Later on, the intake was increased to 120 students in the year 2014.The department is headed by Dr. Sohan Kumar Gupta who has more than twenty years of experience in teaching in reputed engineering colleges."));
        list.add(new BranchModel(R.drawable.ic_electrical, "Electrical Engineering", "Welcome to the Department of Electronics & Communication Engineering, Bangalore Technological Institute, Bangalore. The Department has been functioning for the past Ten years (since 2010) with an intake of 60 students and department has defined vision & mission and continually striving towards the set goals."));
        list.add(new BranchModel(R.drawable.ic_mechanical, "Mechanical  Engineering", "The Department of Mechanical Engineering was established in the Year 2010 with an intake of 60 students. The intake is enhanced to 120 during the academic year 2013-14. The intake was revised to 60 from the academic year 2020-21 onwards. The Main objective of the Department is to provide academic excellence, knowledge and nurture talent in the area of Mechanical Engineering."));

        adapter = new BranchAdapter(getContext(), list);
        viewPager = view.findViewById(R.id.viewPager);

        viewPager.setAdapter(adapter);

        ImageView imageView= view.findViewById(R.id.college_image);
        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/my-college-app-3394e.appspot.com/o/Gallery%2FImage%2F%5BB%40cbf12f3jpg?alt=media&token=86e53c9c-4694-4d5a-a581-9c332dff9e14")
                .into(imageView);

        return view;
    }
}