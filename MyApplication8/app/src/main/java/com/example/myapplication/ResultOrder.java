package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gd185082 on 4/19/2018.
 */

public class ResultOrder {

    @SerializedName("success")
    private String resultCode;

    @SerializedName("message")
    private String resultMessage;

    @SerializedName("items")
    private List<Order> resultItems;


    public ResultOrder(String resultCode, String resultMessage, List<Order> resultItems) {
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

    public List<Order> getResultItems() { return resultItems; }

    public void setResultItems(List<Order> resultItems) { this.resultItems = resultItems; }

}
