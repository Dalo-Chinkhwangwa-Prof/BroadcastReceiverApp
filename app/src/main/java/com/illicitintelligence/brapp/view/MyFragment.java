package com.illicitintelligence.brapp.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.illicitintelligence.brapp.R;
import com.illicitintelligence.brapp.model.Budget;
import com.illicitintelligence.brapp.model.Purchase;
import com.illicitintelligence.brapp.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFragment extends Fragment {


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Constants.ACTIVITY_ACTION.equals(action)) {
                Budget budget = (Budget) intent.getParcelableExtra(Constants.BUNDLE_KEY);
                fragmentTextView.setText(budget.toString());
            }
        }
    };

    @BindView(R.id.textView)
    TextView fragmentTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        getContext().registerReceiver(broadcastReceiver, new IntentFilter(Constants.ACTIVITY_ACTION));
    }

    @OnClick(R.id.send_messag_button)
    public void sendMessageClick(View view) {
        Intent messageIntent = new Intent(Constants.FRAGMENT_ACTION);
        messageIntent.setAction(Constants.FRAGMENT_ACTION);
        messageIntent.putExtra(Constants.BUNDLE_KEY, createMockBudget(10));
        getContext().sendBroadcast(messageIntent);
    }


    private Budget createMockBudget(int values) {
        List<Purchase> purchases = new ArrayList<>();

        for (int i = 0; i < values; i++) {
            purchases.add(new Purchase(getString(R.string.store_gen, i),
                    12.0, true));
        }
        return new Budget(purchases, "12/23/2019", "No");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getContext().unregisterReceiver(broadcastReceiver);
    }
}
