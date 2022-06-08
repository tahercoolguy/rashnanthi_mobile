package com.master.design.rashnanthi.DataModel;

public class ConfirmEventOutput {
    private String success;
    private String paymentLink;
    private String invoiceId;
    public String getSuccess() {
        return success;
    }
    public void setSuccess(String success) {
        this.success = success;
    }
    public String getPaymentLink() {
        return paymentLink;
    }
    public void setPaymentLink(String paymentLink) {
        this.paymentLink = paymentLink;
    }
    public String getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }
}
