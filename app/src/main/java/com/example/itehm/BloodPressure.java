package com.example.itehm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BloodPressure extends AppCompatActivity implements View.OnClickListener {

    private EditText systolicEditText;
    private EditText diastolicEditText;
    private Button recordButton;
    private TextView resultTextView;
    private Button educationalResourcesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure);

        systolicEditText = findViewById(R.id.systolicEditText);
        diastolicEditText = findViewById(R.id.diastolicEditText);
        recordButton = findViewById(R.id.recordButton);
        resultTextView = findViewById(R.id.resultTextView);
        educationalResourcesButton = findViewById(R.id.educationalResourcesButton);
        ImageView LeftIcon = findViewById(R.id.left_icon);
        ImageView RightIcon = findViewById(R.id.right_icon);
        TextView title = findViewById(R.id.toolbar_title);

        LeftIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(BloodPressure.this, MainHealth.class));
            }
        });
        RightIcon.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(BloodPressure.this, MainActivity.class));
            }
        });

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordBloodPressure();
            }
        });

        educationalResourcesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEducationalResources();
            }
        });
        ImageView disclaimerIcon = findViewById(R.id.disclaimerIcon);
        disclaimerIcon.setOnClickListener(this);
    }

    private void recordBloodPressure() {
        String systolicStr = systolicEditText.getText().toString();
        String diastolicStr = diastolicEditText.getText().toString();

        if (systolicStr.isEmpty() || diastolicStr.isEmpty()) {
            resultTextView.setText("Please enter both systolic and diastolic pressure.");
            return;
        }

        try {
            int systolic = Integer.parseInt(systolicStr);
            int diastolic = Integer.parseInt(diastolicStr);

            if (systolic >= 90 && systolic <= 120 && diastolic >= 60 && diastolic <= 80) {
                resultTextView.setText("Blood pressure is within the normal range.");
            } else if (systolic > 120 || diastolic > 80) {
                resultTextView.setText("High blood pressure detected.");
                resultTextView.append("\n\n Information:\n Follow a healthy diet, low in sodium\n Engage in regular physical activity\n Manage stress\n Limit alcohol consumption\n Quit smoking");
            } else {
                resultTextView.setText("Low blood pressure detected.");
                resultTextView.append("\n\n Information:\n Increase fluid and salt intake\n Eat smaller, more frequent meals\n Avoid standing up too quickly\n Wear compression stockings\n- Engage in regular physical activity");
            }
        } catch (NumberFormatException e) {
            resultTextView.setText("Invalid input. Please enter valid numbers for systolic and diastolic pressure.");
        }
    }

    private void openEducationalResources() {
        String title;
        String content;
        String result = resultTextView.getText().toString(); // Get the result text

        if (result.contains("High blood pressure")) {
            title = "High Blood Pressure";
            content = "Your blood pressure is the pressure of your blood in your circulatory system. Blood pressure is recorded as systolic blood pressure over diastolic blood pressure. Systolic blood pressure indicates how much pressure your blood exerts against your artery walls when your heart beats, whereas diastolic blood pressure indicates how much pressure your blood is exerting against your artery walls while your heart is resting between beats. Normal blood pressure is categorized as a systolic reading below 120 mmHg and a diastolic reading below 80 mmHg.\n" +
                    "\n" +
                    "Negative Health Effects of High Blood Pressure (Hypertension)\n" +
                    "High blood pressure has negative health implications beyond heart disease. In Part I of this two-part blog post, we’ll share how hypertension negatively affects the health of multiple systems within your body. In Part II, we’ll wrap up the negative effects of hypertension and conclude with a final section offering suggestions for addressing high blood pressure.\n" +
                    "\n"+"Arteries and Heart\n" +
                    "Most obvious on the list of negative health effects caused by hypertension are damage to your arteries and heart. Hypertension increases the pressure of blood flowing through your arteries and can cause damaged and narrowed arteries and aneurysm.\n" +
                    "\n" +
                    "Damaged/narrowed arteries: Hypertension causes damage to the cells of your arteries’ inner lining and causes them to become less elastic which limits blood flow throughout your body.\n" +
                    "\n" +
                    "Aneurysm: Pressure from blood moving through a weakened artery can cause a wall section to enlarge and form a bulge known as an aneurysm. Aneurysms can form in any artery throughout your body, but are most common in your aorta (largest artery in your body). A ruptured aneurysm can cause life-threatening internal bleeding.\n" +
                    "\n" +
                    "Reference:" +
                    "\n" +
                    "Myzone. (2021, November 16). Part I: High Blood Pressure - What Does It Mean for Your Health? High Blood Pressure.";

        } else if (result.contains("Low blood pressure")) {
            title = "Low Blood Pressure";
            content = "Low blood pressure occurs when blood flows through your blood vessels at lower than normal pressures. The medical term for low blood pressure is hypotension." +
                    "For most adults, a normal blood pressure is usually less than 120/80 mm Hg. Low blood pressure is blood pressure that is lower than 90/60 mm Hg.\n" +
                    "\n" +
                    "Some people have low blood pressure all the time, and it is normal for them. Other people experience a sudden drop in blood pressure or have low blood pressure that may be linked to a health problem. This can be dangerous, as it could mean your heart, brain, or other vital organs are not getting enough blood flow and you are at risk for a heart attack or stroke." +
                    "\n" +
                    "If your blood pressure drops too low, your body’s vital organs do not get enough oxygen and nutrients. When this happens, low blood pressure can lead to shock, which requires immediate medical attention. Signs of shock include cold and sweaty skin, rapid breathing, a blue skin tone, or a weak and rapid pulse." +
                    "Depending on your symptoms, treatment may include drinking more fluids to prevent dehydration, taking medicines to raise your blood pressure, or adjusting medicines that cause low blood pressure.\n" +
                    "\n" +
                    "Your doctor may talk to you about lifestyle changes, including changing what and how you eat and how you sit and stand up. Your doctor may also recommend compression stockings if you stand for long periods." +
                    "\n\n\n" +
                    "Reference:" +
                    "\n" +
                    "Low blood pressure | NHLBI, NIH. (2022, March 24). NHLBI, NIH. https://www.nhlbi.nih.gov/health/low-blood-pressure#:~:text=If%20your%20blood%20pressure%20drops,a%20weak%20and%20rapid%20pulse.";
        } else {
            title = "Normal Blood Pressure";
            content = "Normal blood flow delivers nutrients and oxygen to all parts of your body, including important organs like your heart, brain, and kidneys. Your beating heart helps to push blood through your vast network of blood vessels, both large and small. Your blood vessels, in turn, constantly adjust. They become narrower or wider to maintain your blood pressure and keep blood flowing at a healthy rate.\n" +
                    "\n" +
                    "It’s normal for your blood pressure to go up and down throughout each day. Blood pressure is affected by time of day, exercise, the foods you eat, stress, and other factors. Problems can arise, though, if your blood pressure stays too high for too long.\n" +
                    "\n" +
                    "High blood pressure can make your heart work too hard and lose strength. The high force of blood flow can damage your blood vessels, making them weak, stiff, or narrower. Over time, hypertension can harm several important organs, including your heart, kidneys, brain, and eyes." +
                    "Blood pressure is given as 2 numbers. The first number represents the pressure in your blood vessels as the heart beats (called systolic pressure). The second is the pressure as your heart relaxes and fills with blood (diastolic pressure). Experts generally agree that the safest blood pressure—or “normal” blood pressure—is 120/80 or lower, meaning systolic blood pressure is 120 or less and diastolic pressure is 80 or less." +
                    "\n\n\n" +
                    "Reference:" +
                    "\n" +
                    "Blood pressure matters. (2018, February 6). NIH News in Health. https://newsinhealth.nih.gov/2016/01/blood-pressure-matters";
        }

        Intent intent = new Intent(BloodPressure.this, EducationalResourcesActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        intent.putExtra("result", result); // Pass the result text as an intent extra
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        showDisclaimerDialog();
    }

    private void showDisclaimerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Disclaimer and References")
                .setMessage("DISCLAIMER: HealthMate's Blood pressure calculate is provided for informational purposes only and should not be considered a substitute for professional medical advice, diagnosis, or treatment; always consult a qualified healthcare professional for personalized guidance.\n\n")

                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

