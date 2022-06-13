package com.garasipelosok.softgainapi.Model.Auth;

import android.media.session.MediaSession;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("success")
    @Expose
    private Token success;

    public Token getSuccess() {
        return success;
    }
}
