package com.jmx.listener;


import com.jmx.dao.ProductDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ConsumerServiceImpl implements ConsumerService {


    @Override
    public ProductDetails onMessage(String message) {
     System.out.println("Message in OnMessage: "+message.toString());
        List<String> list = Arrays.asList(message.split(","));
            ProductDetails productDetails = new ProductDetails();
            productDetails.setId(list.get(0));
            productDetails.setInstrumentName(list.get(1));
            productDetails.setBidPrice(Double.parseDouble(list.get(2)));
            productDetails.setAskPrice(Double.parseDouble(list.get(3)));
            productDetails.setTimeStamp(list.get(4));
        double askPrice= addCommission(productDetails.getAskPrice());
        double bidPrice= subCommission(productDetails.getBidPrice());
      productDetails.setBidPrice(bidPrice);
      productDetails.setAskPrice(askPrice);
        System.out.println("After update: "+productDetails.toString());
      return productDetails;
    }

    private double addCommission(double askPrice){
        double commissionPercentage = Double.parseDouble("0.1");
        double commission = (commissionPercentage/100)*askPrice;
        return askPrice+commission;
    }
    private double subCommission(double bidPrice) {
        double commissionPercentage = Double.parseDouble(("0.1"));
        double commission = (commissionPercentage / 100) * bidPrice;
        return bidPrice-commission;
    }

}
