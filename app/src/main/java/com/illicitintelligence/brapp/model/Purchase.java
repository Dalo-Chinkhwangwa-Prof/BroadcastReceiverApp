package com.illicitintelligence.brapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Purchase implements Parcelable {

    private String storeName;
    private double purchaseCost;
    private boolean important;

    public Purchase(String storeName, double purchaseCost, boolean important) {
        this.storeName = storeName;
        this.purchaseCost = purchaseCost;
        this.important = important;
    }

    protected Purchase(Parcel in) {
        storeName = in.readString();
        purchaseCost = in.readDouble();
        important = in.readByte() != 0;
    }

    public static final Creator<Purchase> CREATOR = new Creator<Purchase>() {
        @Override
        public Purchase createFromParcel(Parcel in) {
            return new Purchase(in);
        }

        @Override
        public Purchase[] newArray(int size) {
            return new Purchase[size];
        }
    };

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public double getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(storeName);
        dest.writeDouble(purchaseCost);
        dest.writeByte((byte) (important ? 1 : 0));
    }
}
