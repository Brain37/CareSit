package com.example.brianscott.caresit;

/**
 * Created by Brian Scott on 4/25/2016.
 */
public class Request
{
    String startTime;
    String endTime;
    String startDate;
    String endDate;

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    String description;

    public Request()
    {
        this.startDate = "";
        this.startTime = "";
        this.endDate = "";
        this.endTime = "";
        this.description = "";
    }
}
