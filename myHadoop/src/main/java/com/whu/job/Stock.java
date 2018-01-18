package com.whu.job;

/**
 * Created by hulichao on 2018/1/18
 */
public class Stock {
    private String stockCode;
    
    private String stockName;
    
    private String tradeVolume;
    
    private String previousPrice;
    
    private String openingPrice;
    
    private String topPrice;
    
    private String bottomPrice;
    
    private String finishingPrice;
    
    private String turnOver;
    
    private String riseFallMoney;
    
    private String riseFallRate;
    
    private String exchangeHandRate;
    
    private String AcirculationMarketValue;
    
    private String PriceEarningsRatio;
    
    private String PriceBookRatio;
    
    private String PriceSaleRatio;
    
    private String PriceCashRatio;
    
    private String date;

    public Stock(String acirculationMarketValue) {
        AcirculationMarketValue = acirculationMarketValue;
    }

    public Stock(String stockCode, String stockName, String tradeVolume, String previousPrice, String openingPrice, String topPrice, String bottomPrice, String finishingPrice, String turnOver, String riseFallMoney, String riseFallRate, String exchangeHandRate, String acirculationMarketValue, String priceEarningsRatio, String priceBookRatio, String priceSaleRatio, String priceCashRatio, String date) {
        this.stockCode = stockCode;
        this.stockName = stockName;
        this.tradeVolume = tradeVolume;
        this.previousPrice = previousPrice;
        this.openingPrice = openingPrice;
        this.topPrice = topPrice;
        this.bottomPrice = bottomPrice;
        this.finishingPrice = finishingPrice;
        this.turnOver = turnOver;
        this.riseFallMoney = riseFallMoney;
        this.riseFallRate = riseFallRate;
        this.exchangeHandRate = exchangeHandRate;
        AcirculationMarketValue = acirculationMarketValue;
        PriceEarningsRatio = priceEarningsRatio;
        PriceBookRatio = priceBookRatio;
        PriceSaleRatio = priceSaleRatio;
        PriceCashRatio = priceCashRatio;
        this.date = date;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getTradeVolume() {
        return tradeVolume;
    }

    public void setTradeVolume(String tradeVolume) {
        this.tradeVolume = tradeVolume;
    }

    public String getPreviousPrice() {
        return previousPrice;
    }

    public void setPreviousPrice(String previousPrice) {
        this.previousPrice = previousPrice;
    }

    public String getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(String openingPrice) {
        this.openingPrice = openingPrice;
    }

    public String getTopPrice() {
        return topPrice;
    }

    public void setTopPrice(String topPrice) {
        this.topPrice = topPrice;
    }

    public String getBottomPrice() {
        return bottomPrice;
    }

    public void setBottomPrice(String bottomPrice) {
        this.bottomPrice = bottomPrice;
    }

    public String getFinishingPrice() {
        return finishingPrice;
    }

    public void setFinishingPrice(String finishingPrice) {
        this.finishingPrice = finishingPrice;
    }

    public String getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(String turnOver) {
        this.turnOver = turnOver;
    }

    public String getRiseFallMoney() {
        return riseFallMoney;
    }

    public void setRiseFallMoney(String riseFallMoney) {
        this.riseFallMoney = riseFallMoney;
    }

    public String getRiseFallRate() {
        return riseFallRate;
    }

    public void setRiseFallRate(String riseFallRate) {
        this.riseFallRate = riseFallRate;
    }

    public String getExchangeHandRate() {
        return exchangeHandRate;
    }

    public void setExchangeHandRate(String exchangeHandRate) {
        this.exchangeHandRate = exchangeHandRate;
    }

    public String getAcirculationMarketValue() {
        return AcirculationMarketValue;
    }

    public void setAcirculationMarketValue(String acirculationMarketValue) {
        AcirculationMarketValue = acirculationMarketValue;
    }

    public String getPriceEarningsRatio() {
        return PriceEarningsRatio;
    }

    public void setPriceEarningsRatio(String priceEarningsRatio) {
        PriceEarningsRatio = priceEarningsRatio;
    }

    public String getPriceBookRatio() {
        return PriceBookRatio;
    }

    public void setPriceBookRatio(String priceBookRatio) {
        PriceBookRatio = priceBookRatio;
    }

    public String getPriceSaleRatio() {
        return PriceSaleRatio;
    }

    public void setPriceSaleRatio(String priceSaleRatio) {
        PriceSaleRatio = priceSaleRatio;
    }

    public String getPriceCashRatio() {
        return PriceCashRatio;
    }

    public void setPriceCashRatio(String priceCashRatio) {
        PriceCashRatio = priceCashRatio;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
