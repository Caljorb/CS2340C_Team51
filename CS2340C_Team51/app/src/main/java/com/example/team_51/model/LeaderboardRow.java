package com.example.team_51.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class LeaderboardRow implements Parcelable {
    private String name;
    private long score;
    private String date;

    public LeaderboardRow(String name, long score, String date) { // store 3 elements for a row
        this.name = name;
        this.score = score;
        this.date = date;
    }

    protected LeaderboardRow(Parcel in) {
        name = in.readString();
        score = in.readLong();
        date = in.readString();
    }

    public static final Creator<LeaderboardRow> CREATOR = new Creator<LeaderboardRow>() {
        @Override
        public LeaderboardRow createFromParcel(Parcel in) {
            return new LeaderboardRow(in);
        }

        @Override
        public LeaderboardRow[] newArray(int size) {
            return new LeaderboardRow[size];
        }
    };
    // what is this

    public String getName() {
        return name;
    }

    public long getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0; // what do i do with this
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) { // what is this
        parcel.writeString(name);
        parcel.writeLong(score);
        parcel.writeString(date);
    }

    @Override
    public String toString() {
        return name + " " + score + " " + date;
    }
}
