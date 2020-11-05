 package com.example.bloodbank.datamodeling;

 import com.google.gson.annotations.Expose;
 import com.google.gson.annotations.SerializedName;

 public class RequestDataModel {

     @SerializedName("id")
     @Expose
     private String id;
     @SerializedName("message")
     @Expose
     private String message;
     @SerializedName("url")
     @Expose
     private String url;
     @SerializedName("phone")
     @Expose
     private String phone;

     public String getId() {
         return id;
     }

     public void setId(String id) {
         this.id = id;
     }

     public String getMessage() {
         return message;
     }

     public void setMessage(String message) {
         this.message = message;
     }

     public String getUrl() {
         return url;
     }

     public void setUrl(String url) {
         this.url = url;
     }

     public String getPhone() {
         return phone;
     }

     public void setPhone(String phone) {
         this.phone = phone;
     }

 }