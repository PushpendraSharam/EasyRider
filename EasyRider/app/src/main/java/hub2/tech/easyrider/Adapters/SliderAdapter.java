package hub2.tech.easyrider.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import hub2.tech.easyrider.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    int[] images = {
            R.drawable.slider_image_1,
            R.drawable.slider_image_2,
            R.drawable.slider_image_3,

    };

    String[] sliderTittle = {
            "Discover Your Ride",
            "Journey with Confidence",
            "Your Ride, Your Way",
    };
    String[] sliderSubTittle = {
            "Experience the convenience of reliable, on-demand transportation that fits seamlessly into your life.",
            "Relax and enjoy the journey with our trusted drivers, ensuring your safety and security throughout.",
            "Empower your travel with customizable routes and a range of vehicles, giving you ultimate choice and comfort.",
    };

    public SliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return sliderTittle.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);
        ImageView image = view.findViewById(R.id.sliderImage);
        TextView tittle = view.findViewById(R.id.sliderTittle);
        TextView subTittle = view.findViewById(R.id.sliderSubTittle);
        image.setImageResource(images[position]);
        tittle.setText(sliderTittle[position]);
        subTittle.setText(sliderSubTittle[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
