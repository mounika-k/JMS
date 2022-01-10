package com.jmx.dao;

import com.opencsv.bean.CsvBindByPosition;

public class ProductDetails {
    @CsvBindByPosition(position = 0)
    private String id;
    @CsvBindByPosition(position = 1)
    private String instrumentName;
    @CsvBindByPosition(position = 2)
    private double bidPrice;
    @CsvBindByPosition(position = 3)
    private double askPrice;
    @CsvBindByPosition(position = 4)
    //@CsvDate("dd-MM-yyyy hh:mm:ss:mmm")
    private String timeStamp;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(double askPrice) {
        this.askPrice = askPrice;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return  id +
                ','+
               instrumentName + ',' +
                + bidPrice +','+
                askPrice +','+
              timeStamp;
    }
}
