package my.quize.myquize.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.concurrent.TimeUnit;

import my.quize.myquize.Fragments.MyOrder;
import my.quize.myquize.Fragments.Record;
import my.quize.myquize.R;
import my.quize.myquize.databinding.ActivityColorPredicationBinding;
import my.quize.myquize.databinding.BottomSheetBinding;

public class ColorPredication extends AppCompatActivity {
    ActivityColorPredicationBinding binding;
    String defaultBid = "5";
    String PARITY = "Parity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityColorPredicationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction().replace(R.id.color_predication_container, new Record()).commit();
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ColorPredication.this);
        BottomSheetBinding bottomSheetBinding = BottomSheetBinding.inflate(getLayoutInflater());
        bottomSheetDialog.setContentView(bottomSheetBinding.getRoot());
        bottomSheetBinding.numberEditText.setText(defaultBid);

        new CountDownTimer(180000, 1000) { // 5 minutes countdown with 1 second interval

            public void onTick(long millisUntilFinished) {
                // Code to be executed every second during countdown
                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60;
                binding.tvMintue.setText(String.valueOf(minutes));
                binding.tvFirstSecond.setText(String.valueOf(seconds / 10));
                binding.tvLastSecond.setText(String.valueOf(seconds % 10));
            }

            public void onFinish() {
                // Code to be executed when countdown finishes
            }
        }.start();
        binding.recordLine.setVisibility(View.VISIBLE);
        binding.myorderLine.setVisibility(View.INVISIBLE);
        binding.recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.recordLine.setVisibility(View.VISIBLE);
                binding.myorderLine.setVisibility(View.INVISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.color_predication_container, new Record()).commit();

            }
        });
        binding.myOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.myorderLine.setVisibility(View.VISIBLE);
                binding.recordLine.setVisibility(View.INVISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.color_predication_container, new MyOrder()).commit();


            }
        });

        binding.joinGreenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBinding.tvPartySubPart.setText("Green");
                bottomSheetBinding.tvPartySubPart.setTextColor(getColor(R.color.colorGreen));
                bottomSheetDialog.show();
            }
        });
        binding.joinVioletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBinding.tvPartySubPart.setText("Voilet");
                bottomSheetBinding.tvPartySubPart.setTextColor(getColor(R.color.colorVoilet));
                bottomSheetDialog.show();
            }
        });
        binding.joinRedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBinding.tvPartySubPart.setText("Red");
                bottomSheetBinding.tvPartySubPart.setTextColor(getColor(R.color.colorRed));
                bottomSheetDialog.show();
            }
        });
        binding.tvNumberOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBinding.tvPartySubPart.setText("1");
                bottomSheetBinding.tvPartySubPart.setTextColor(getColor(R.color.black));
                bottomSheetDialog.show();
            }
        });
        binding.tvNumberTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBinding.tvPartySubPart.setText("2");
                bottomSheetBinding.tvPartySubPart.setTextColor(getColor(R.color.black));
                bottomSheetDialog.show();
            }
        });
        binding.tvNumberThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBinding.tvPartySubPart.setText("3");
                bottomSheetBinding.tvPartySubPart.setTextColor(getColor(R.color.black));
                bottomSheetDialog.show();
            }
        });
        binding.tvNumberFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBinding.tvPartySubPart.setText("4");
                bottomSheetBinding.tvPartySubPart.setTextColor(getColor(R.color.black));
                bottomSheetDialog.show();
            }
        });
        binding.tvNumberFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBinding.tvPartySubPart.setText("5");
                bottomSheetBinding.tvPartySubPart.setTextColor(getColor(R.color.black));
                bottomSheetDialog.show();
            }
        });
        binding.tvNumberSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBinding.tvPartySubPart.setText("6");
                bottomSheetBinding.tvPartySubPart.setTextColor(getColor(R.color.black));
                bottomSheetDialog.show();
            }
        });
        binding.tvNumberSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBinding.tvPartySubPart.setText("7");
                bottomSheetBinding.tvPartySubPart.setTextColor(getColor(R.color.black));
                bottomSheetDialog.show();
            }
        });
        binding.tvNumberEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBinding.tvPartySubPart.setText("8");
                bottomSheetBinding.tvPartySubPart.setTextColor(getColor(R.color.black));
                bottomSheetDialog.show();
            }
        });
        binding.tvNumberNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBinding.tvPartySubPart.setText("9");
                bottomSheetBinding.tvPartySubPart.setTextColor(getColor(R.color.black));
                bottomSheetDialog.show();
            }
        });
        binding.tvNumberZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBinding.tvPartySubPart.setText("0");
                bottomSheetBinding.tvPartySubPart.setTextColor(getColor(R.color.black));
                bottomSheetDialog.show();
            }
        });
        bottomSheetBinding.tvNumberTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBinding.tvNumberTen.setBackground(getDrawable(R.drawable.brown_item_bg));
                bottomSheetBinding.tvNumberTenThousand.setBackground(getDrawable(R.drawable.bottom_sheet_item_bg));
                bottomSheetBinding.tvNumberHundred.setBackground(getDrawable(R.drawable.bottom_sheet_item_bg));
                bottomSheetBinding.tvNumberThousand.setBackground(getDrawable(R.drawable.bottom_sheet_item_bg));

                bottomSheetBinding.tvNumberTen.setTextColor(getColor(R.color.white));
                bottomSheetBinding.tvNumberThousand.setTextColor(getColor(R.color.black));
                bottomSheetBinding.tvNumberTenThousand.setTextColor(getColor(R.color.black));
                bottomSheetBinding.tvNumberHundred.setTextColor(getColor(R.color.black));
            }
        });
        bottomSheetBinding.tvNumberHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetBinding.tvNumberTen.setBackground(getDrawable(R.drawable.bottom_sheet_item_bg));
                bottomSheetBinding.tvNumberTenThousand.setBackground(getDrawable(R.drawable.bottom_sheet_item_bg));
                bottomSheetBinding.tvNumberHundred.setBackground(getDrawable(R.drawable.brown_item_bg));
                bottomSheetBinding.tvNumberThousand.setBackground(getDrawable(R.drawable.bottom_sheet_item_bg));

                bottomSheetBinding.tvNumberTen.setTextColor(getColor(R.color.black));
                bottomSheetBinding.tvNumberThousand.setTextColor(getColor(R.color.black));
                bottomSheetBinding.tvNumberTenThousand.setTextColor(getColor(R.color.black));
                bottomSheetBinding.tvNumberHundred.setTextColor(getColor(R.color.white));

            }
        });
        bottomSheetBinding.tvNumberThousand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBinding.tvNumberTen.setBackground(getDrawable(R.drawable.bottom_sheet_item_bg));
                bottomSheetBinding.tvNumberTenThousand.setBackground(getDrawable(R.drawable.bottom_sheet_item_bg));
                bottomSheetBinding.tvNumberHundred.setBackground(getDrawable(R.drawable.bottom_sheet_item_bg));
                bottomSheetBinding.tvNumberThousand.setBackground(getDrawable(R.drawable.brown_item_bg));

                bottomSheetBinding.tvNumberTen.setTextColor(getColor(R.color.black));
                bottomSheetBinding.tvNumberThousand.setTextColor(getColor(R.color.white));
                bottomSheetBinding.tvNumberTenThousand.setTextColor(getColor(R.color.black));
                bottomSheetBinding.tvNumberHundred.setTextColor(getColor(R.color.black));
            }
        });
        bottomSheetBinding.tvNumberTenThousand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBinding.tvNumberTen.setBackground(getDrawable(R.drawable.bottom_sheet_item_bg));
                bottomSheetBinding.tvNumberTenThousand.setBackground(getDrawable(R.drawable.brown_item_bg));
                bottomSheetBinding.tvNumberHundred.setBackground(getDrawable(R.drawable.bottom_sheet_item_bg));
                bottomSheetBinding.tvNumberThousand.setBackground(getDrawable(R.drawable.bottom_sheet_item_bg));

                bottomSheetBinding.tvNumberTen.setTextColor(getColor(R.color.black));
                bottomSheetBinding.tvNumberThousand.setTextColor(getColor(R.color.black));
                bottomSheetBinding.tvNumberTenThousand.setTextColor(getColor(R.color.white));
                bottomSheetBinding.tvNumberHundred.setTextColor(getColor(R.color.black));
            }
        });
        bottomSheetBinding.tvPlusFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(defaultBid);
                defaultBid = String.valueOf(number + 5);
                bottomSheetBinding.numberEditText.setText(defaultBid);


            }
        });
        bottomSheetBinding.tvMinusFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(defaultBid);
                if (number > 5) {
                    defaultBid = String.valueOf(number - 5);
                }
                bottomSheetBinding.numberEditText.setText(defaultBid);


            }
        });
        bottomSheetBinding.tvPlusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(defaultBid);
                defaultBid = String.valueOf(number + 1);
                bottomSheetBinding.numberEditText.setText(defaultBid);


            }
        });
        bottomSheetBinding.tvMinusOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(defaultBid);
                if (number > 1) {
                    defaultBid = String.valueOf(number - 1);
                }
                bottomSheetBinding.numberEditText.setText(defaultBid);


            }
        });
    }
}