package com.illicitintelligence.brapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.illicitintelligence.brapp.R;
import com.illicitintelligence.brapp.model.Budget;
import com.illicitintelligence.brapp.model.Purchase;
import com.illicitintelligence.brapp.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_textview)
    TextView mainTextView;

    private BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Constants.FRAGMENT_ACTION.equals(action)) {
                Budget budget = (Budget) intent.getParcelableExtra(Constants.BUNDLE_KEY);
                mainTextView.setText(budget.toString());
            }
        }
    };

    private MyFragment fragment = new MyFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_framelayout, fragment)
                .addToBackStack(fragment.getTag())
                .commit();
    }

    @OnClick(R.id.send_message)
    public void sendMessageClick(View view) {
        Intent messageIntent = new Intent(Constants.ACTIVITY_ACTION);
        messageIntent.setAction(Constants.ACTIVITY_ACTION);
        messageIntent.putExtra(Constants.BUNDLE_KEY, createMockBudget(9));
        sendBroadcast(messageIntent);
    }


    private Budget createMockBudget(int values) {
        List<Purchase> purchases = new ArrayList<>();

        for (int i = 0; i < values; i++) {
            purchases.add(new Purchase(getString(R.string.store_gen, i),
                    12.0, true));
        }
        return new Budget(purchases, "12/23/2019", "No");
    }
}
