package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gd185082 on 4/19/2018.
 */

public class ResultDelivery {

    @SerializedName("success")
    private String resultCode;

    @SerializedName("message")
    private String resultMessage;

    @SerializedName("items")
    private List<Delivery> resultItems;


    public ResultDelivery(String resultCode, String resultMessage, List<Delivery> resultItems) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.resultItems = resultItems;
    }
    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public List<Delivery> getResultItems() { return resultItems; }

    public void setResultItems(List<Delivery> resultItems) { this.resultItems = resultItems; }

}
