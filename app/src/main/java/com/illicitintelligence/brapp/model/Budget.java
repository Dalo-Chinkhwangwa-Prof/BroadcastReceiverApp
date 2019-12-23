package com.illicitintelligence.brapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Budget implements Parcelable {

    private List<Purchase> purchaseList;
    private String budgetDate;
    private String overPurchase;

    @Override
    public String toString() {
        return "Budget{" +
                "purchaseList=" + purchaseList +
                ", budgetDate='" + budgetDate + '\'' +
                ", overPurchase='" + overPurchase + '\'' +
                '}';
    }

    public Budget(List<Purchase> purchaseList, String budgetDate, String overPurchase) {
        this.purchaseList = purchaseList;
        this.budgetDate = budgetDate;
        this.overPurchase = overPurchase;
    }

    protected Budget(Parcel in) {
        budgetDate = in.readString();
        overPurchase = in.readString();
        purchaseList = in.readArrayList();
    }

    public static final Creator<Budget> CREATOR = new Creator<Budget>() {
        @Override
        public Budget createFromParcel(Parcel in) {
            return new Budget(in);
        }

        @Override
        public Budget[] newArray(int size) {
            return new Budget[size];
        }
    };

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public String getBudgetDate() {
        return budgetDate;
    }

    public void setBudgetDate(String budgetDate) {
        this.budgetDate = budgetDate;
    }

    public String getOverPurchase() {
        return overPurchase;
    }

    public void setOverPurchase(String overPurchase) {
        this.overPurchase = overPurchase;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(budgetDate);
        dest.writeString(overPurchase);
        dest.writeList(purchaseList);
    }
}
