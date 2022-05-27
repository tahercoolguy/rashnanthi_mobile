package com.master.design.rashnanthi.Services;


//import io.opencensus.stats.Stats;


import com.master.design.rashnanthi.DataModel.AboutUsDM;
import com.master.design.rashnanthi.DataModel.AddEventByCreatorRootDM;
import com.master.design.rashnanthi.DataModel.ChangePasswordRootDM;
import com.master.design.rashnanthi.DataModel.ConfirmEventRootDM;
import com.master.design.rashnanthi.DataModel.CountryRootDM;
import com.master.design.rashnanthi.DataModel.DeleteEventOutput;
import com.master.design.rashnanthi.DataModel.DeleteEventRootDM;
import com.master.design.rashnanthi.DataModel.EditEventRootDM;
import com.master.design.rashnanthi.DataModel.EventRegisterDM;
import com.master.design.rashnanthi.DataModel.EventsDetailsRootDM;
import com.master.design.rashnanthi.DataModel.ForgotPasswordRootDM;
import com.master.design.rashnanthi.DataModel.GetCoachsByCountryRootDM;
import com.master.design.rashnanthi.DataModel.LoginRootDM;
import com.master.design.rashnanthi.DataModel.MyEventRootDM1;
import com.master.design.rashnanthi.DataModel.MyEventsRootDM;
import com.master.design.rashnanthi.DataModel.MyProfileRootDM;
import com.master.design.rashnanthi.DataModel.OtpScrenRootDM;
import com.master.design.rashnanthi.DataModel.ProfilePictureRootDM;
import com.master.design.rashnanthi.DataModel.SocialMediaDM;
import com.master.design.rashnanthi.DataModel.SummaryForPaidEventRootDM;
import com.master.design.rashnanthi.DataModel.UpdateProfileRootDM;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.mime.MultipartTypedOutput;


public interface PAServices {


    @POST("/aboutus")
    void Aboutus(Callback<AboutUsDM> aboutUsDMCallback);


    @POST("/termsandcond")
    void TermsAndCondition(Callback<AboutUsDM> aboutUsDMCallback);


    @POST("/privacypolicy")
    void PrivacyPolicy(Callback<AboutUsDM> aboutUsDMCallback);


    @POST("/contactus")
    void ContactUS(Callback<AboutUsDM> aboutUsDMCallback);


    @Headers("Cache-Control: no-cache;")
    @GET("/countries")
    void Countries(Callback<CountryRootDM> countryRootDMCallback);


    @POST("/socialnetworklinks")
    void SocialMedia(Callback<SocialMediaDM> socialMediaDMCallback);


    @Headers("Cache-Control: no-cache;")
    @POST("/eventcreatorreg")
    void EventCreatorReg(@Body MultipartTypedOutput multipartTypedOutput, Callback<EventRegisterDM> eventRegisterOutputCallback);


    @Headers("Cache-Control: no-cache;")
    @POST("/coachreg")
    void CoachReg(@Body MultipartTypedOutput multipartTypedOutput, Callback<EventRegisterDM> eventRegisterOutputCallback);


    @FormUrlEncoded
    @POST("/otpscreen")
    void OtpVerify(@Field("mobile") String mobile,
                   @Field("otp") String otp,
                   Callback<OtpScrenRootDM> otpScrenRootDMCallback);

    @FormUrlEncoded
    @POST("/login")
    void Login(@Field("countrycode") String countrycode,
               @Field("mobile") String mobile,
               @Field("password") String password,
               Callback<LoginRootDM> loginRootDMCallback);

    @FormUrlEncoded
    @POST("/forgotpassword")
    void ForgotPassword(@Field("email") String email,
                        Callback<ForgotPasswordRootDM> forgotPasswordRootDMCallback);


    @FormUrlEncoded
    @POST("/changepassword")
    void ChangePassword(@Field("loginid") String loginid,
                        @Field("oldpass") String oldpass,
                        @Field("newpass") String newpass,
                        @Field("retypepass") String retypepass,
                        Callback<ChangePasswordRootDM> changePasswordRootDMCallback);

    @FormUrlEncoded
    @POST("/updateprofile")
    void UpdateProfile(@Field("userid") String userid,
                       @Field("name") String name,
                       @Field("email") String email,
                       @Field("mobile") String mobile,
                       @Field("countrycode") String countrycode,
                       Callback<UpdateProfileRootDM> updateProfileRootDMCallback);

    @FormUrlEncoded
    @POST("/myevents")
    void MyEvents(@Field("userid") String userid,
                  @Field("countryid") String countryid,
                  Callback<MyEventRootDM1> myEventRootDM1Callback);

    @FormUrlEncoded
    @POST("/myprofile")
    void MyProfile(@Field("userid") String userid,
                   Callback<MyProfileRootDM> myProfileRootDMCallback);


    @FormUrlEncoded
    @POST("/getcoachsbycountry")
    void GetCoachsByCountry(@Field("countryid") String countryid,
                            Callback<GetCoachsByCountryRootDM> myProfileRootDMCallback);


    @Headers("Cache-Control: no-cache;")
    @POST("/addeventbycreator")
    void AddEventByCreator(@Body MultipartTypedOutput multipartTypedOutput, Callback<AddEventByCreatorRootDM> addEventByCreatorRootDMCallback);


    @FormUrlEncoded
    @POST("/summaryforpaidevent")
    void SummaryForPaidEvent(@Field("eventid") String eventid,
                             Callback<SummaryForPaidEventRootDM> summaryForPaidEventRootDMCallback);


    @FormUrlEncoded
    @POST("/editevent")
    void EditEvent(@Body MultipartTypedOutput multipartTypedOutput, Callback<EditEventRootDM> editEventRootDMCallback);


    @FormUrlEncoded
    @POST("/eventdetails")
    void EventDetails(@Field("eventid") String eventid,
                      Callback<EventsDetailsRootDM> eventsDetailsRootDMCallback);

    @FormUrlEncoded
    @POST("/confirmevent")
    void ConfirmEvent(@Field("eventid") String eventid,
                      Callback<ConfirmEventRootDM> confirmEventRootDMCallback);

    @FormUrlEncoded
    @POST("/deletevent")
    void DeletEvent(@Field("eventid") String eventid,
                    Callback<DeleteEventRootDM> deleteEventRootDMCallback);

    @FormUrlEncoded
    @POST("/profilepicture")
    void ProfilePicture(@Body MultipartTypedOutput multipartTypedOutput, Callback<ProfilePictureRootDM> profilePictureRootDMCallback);


//    @Headers("Cache-Control: no-cache;")
//    @POST("/auth/signin")
//    void SignUp(@Body MultipartTypedOutput multipartTypedOutput, Callback<SignUpDM> signUpDMCallback);
//
//    @Headers("Cache-Control: no-cache;")
//    @POST("/auth/record_video")
//    void RecordedVideo(@Body MultipartTypedOutput multipartTypedOutput, Callback<VideoDM> videoDMCallback);
//
//    @Headers("Cache-Control: no-cache;")
//    @POST("/auth/user_login")
//    void LoginIn(@Body MultipartTypedOutput multipartTypedOutput, Callback<SignUpDM> signUpDMCallback);
//
//    @Headers("Cache-Control: no-cache;")
//    @GET("/user/view_home_banner")
//    void Banner(Callback<BannerDM> bannerDMCallback);
//
//    @Headers("Cache-Control: no-cache;")
//    @GET("/user/view_news")
//    void News(Callback<NewsDM> newsDMCallback);
//
//    @Headers("Cache-Control: no-cache;")
//    @GET("/user/view_events")
//    void Events(Callback<EventsDM> eventsDMCallback);
//
//    @Headers("Cache-Control: no-cache;")
//    @GET("/user/view_restaurants")
//    void Restaurent(Callback<RestaurentDM> restaurentDMCallback);
//
//
//    @Headers("Cache-Control: no-cache;")
//    @GET("/user/view_shops")
//    void Shops(Callback<ShopsDM> shopsDMCallback);


}



