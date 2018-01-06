package com.bizsoft.fmcgv2.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.internal.LinkedTreeMap;

/**
 * Created by shri on 9/8/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true,value = {"UOM","UnderStockGroup"})
public class Product {

            Long POQty;
            Long PQty;
            Long PRQty;
            Long SOQty;
            Long SQty;
            Long SRQty;
            Long AvailableStock;
            boolean IsReOrderLevel;
            Long SInQty;
            Long SOutQty;
            boolean IsReadOnly;
            boolean IsEnabled;
            Long Id;
            String ProductName;
            Long StockGroupId;
            String ItemCode;
            Long UOMId;
            String PurchaseRate;
            String SellingRate;
            Long MaxSellingRate;
            Long MinSellingRate;
            boolean status;

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    double finalPrice;


    boolean IsResale;

    public boolean isResale() {
        return IsResale;
    }

    public void setResale(boolean resale) {
        IsResale = resale;
    }

    public String getParticulars() {
        return Particulars;
    }

    public void setParticulars(String particulars) {
        this.Particulars = particulars;
    }

    String Particulars;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean added) {
        isAdded = added;
    }

    double MRP;
            boolean isAdded;


    public String getLedgerName() {
        return LedgerName;
    }

    public void setLedgerName(String ledgerName) {
        LedgerName = ledgerName;
    }

    String LedgerName;

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    double Amount;

    public boolean isReOrderLevel() {
        return IsReOrderLevel;
    }

    public void setReOrderLevel(boolean reOrderLevel) {
        IsReOrderLevel = reOrderLevel;
    }

    public Long getQty() {
        return Qty;
    }

    public void setQty(Long qty) {
        Qty = qty;
    }

    double DiscountAmount;
            double OpeningStock;
            double ReOrderLevel;
            String ProductImage;
            Long Qty;

    public Long getPOQty() {
        return POQty;
    }

    public void setPOQty(Long POQty) {
        this.POQty = POQty;
    }

    public Long getPQty() {
        return PQty;
    }

    public void setPQty(Long PQty) {
        this.PQty = PQty;
    }

    public Long getPRQty() {
        return PRQty;
    }

    public void setPRQty(Long PRQty) {
        this.PRQty = PRQty;
    }

    public Long getSOQty() {
        return SOQty;
    }

    public void setSOQty(Long SOQty) {
        this.SOQty = SOQty;
    }

    public Long getSQty() {
        return SQty;
    }

    public void setSQty(Long SQty) {
        this.SQty = SQty;
    }

    public Long getSRQty() {
        return SRQty;
    }

    public void setSRQty(Long SRQty) {
        this.SRQty = SRQty;
    }

    public Long getAvailableStock() {
        return AvailableStock;
    }

    public void setAvailableStock(Long availableStock) {
        AvailableStock = availableStock;
    }

    public boolean getIsReOrderLevel() {
        return IsReOrderLevel;
    }

    public void setIsReOrderLevel(boolean isReOrderLevel) {
        IsReOrderLevel = isReOrderLevel;
    }

    public Long getSInQty() {
        return SInQty;
    }

    public void setSInQty(Long SInQty) {
        this.SInQty = SInQty;
    }

    public Long getSOutQty() {
        return SOutQty;
    }

    public void setSOutQty(Long SOutQty) {
        this.SOutQty = SOutQty;
    }

    public boolean isReadOnly() {
        return IsReadOnly;
    }

    public void setReadOnly(boolean readOnly) {
        IsReadOnly = readOnly;
    }

    public boolean isEnabled() {
        return IsEnabled;
    }

    public void setEnabled(boolean enabled) {
        IsEnabled = enabled;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public Long getStockGroupId() {
        return StockGroupId;
    }

    public void setStockGroupId(Long stockGroupId) {
        StockGroupId = stockGroupId;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public Long getUOMId() {
        return UOMId;
    }

    public void setUOMId(Long UOMId) {
        this.UOMId = UOMId;
    }

    public String getPurchaseRate() {
        return PurchaseRate;
    }

    public void setPurchaseRate(String purchaseRate) {
        PurchaseRate = purchaseRate;
    }

    public String getSellingRate() {
        return SellingRate;
    }

    public void setSellingRate(String sellingRate) {
        SellingRate = sellingRate;
    }

    public Long getMaxSellingRate() {
        return MaxSellingRate;
    }

    public void setMaxSellingRate(Long maxSellingRate) {
        MaxSellingRate = maxSellingRate;
    }

    public Long getMinSellingRate() {
        return MinSellingRate;
    }

    public void setMinSellingRate(Long minSellingRate) {
        MinSellingRate = minSellingRate;
    }

    public double getMRP() {
        return MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    public double getDiscountAmount() {
        return DiscountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        DiscountAmount = discountAmount;
    }

    public double getOpeningStock() {
        return OpeningStock;
    }

    public void setOpeningStock(double openingStock) {
        OpeningStock = openingStock;
    }

    public double getReOrderLevel() {
        return ReOrderLevel;
    }
    @JsonIgnore

    public void setReOrderLevel(double reOrderLevel) {
        ReOrderLevel = reOrderLevel;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getUOMName() {
        return UOMName;
    }

    public void setUOMName(String UOMName) {
        this.UOMName = UOMName;
    }

    public String getStockGroupName() {
        return StockGroupName;
    }

    public void setStockGroupName(String stockGroupName) {
        StockGroupName = stockGroupName;
    }

    public String getUOM() {
        return UOM;
    }

    public void setUOM(String UOM) {
        this.UOM = UOM;
    }

    public com.bizsoft.fmcgv2.dataobject.StockGroup getStockGroup() {
        return StockGroup;
    }

    public void setStockGroup(com.bizsoft.fmcgv2.dataobject.StockGroup stockGroup) {
        StockGroup = stockGroup;
    }

    String UOMName;
            String StockGroupName;
            String UOM;

            StockGroup StockGroup;
}
