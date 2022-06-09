package com.master.design.rashnanthi.Services;


//import io.opencensus.stats.Stats;


import com.master.design.rashnanthi.DataModel.AboutUsDM;
import com.master.design.rashnanthi.DataModel.AddEventByCreatorRootDM;
import com.master.design.rashnanthi.DataModel.ChangePasswordRootDM;
import com.master.design.rashnanthi.DataModel.CoachesWithPostsRootDM;
import com.master.design.rashnanthi.DataModel.ConfirmEventRootDM;
import com.master.design.rashnanthi.DataModel.CountryRootDM;
import com.master.design.rashnanthi.DataModel.DeleteEventRootDM;
import com.master.design.rashnanthi.DataModel.EditEventRootDM;
import com.master.design.rashnanthi.DataModel.EventRegisterDM;
import com.master.design.rashnanthi.DataModel.EventsDetailsRootDM;
import com.master.design.rashnanthi.DataModel.ForgotPasswordRootDM;
import com.master.design.rashnanthi.DataModel.GetCoachsByCountryRootDM;
import com.master.design.rashnanthi.DataModel.GetEventsByCountryDateRootDM;
import com.master.design.rashnanthi.DataModel.LoginRootDM;
import com.master.design.rashnanthi.DataModel.MyEventRootDM1;
import com.master.design.rashnanthi.DataModel.MyNotificationRootDM;
import com.master.design.rashnanthi.DataModel.MyProfileRootDM;
import com.master.design.rashnanthi.DataModel.OtpScrenRootDM;
import com.master.design.rashnanthi.DataModel.ProfilePictureRootDM;
import com.master.design.rashnanthi.DataModel.SocialMediaDM;
import com.master.design.rashnanthi.DataModel.StoriesByDateRootDM;
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

    //1      done
    @POST("/aboutus")
    void Aboutus(Callback<AboutUsDM> aboutUsDMCallback);

    //2    done

    @POST("/termsandcond")
    void TermsAndCondition(Callback<AboutUsDM> aboutUsDMCallback);

    //3    done
    @POST("/privacypolicy")
    void PrivacyPolicy(Callback<AboutUsDM> aboutUsDMCallback);

    //4    done
    @POST("/contactus")
    void ContactUS(Callback<AboutUsDM> aboutUsDMCallback);

    //5    done
    @Headers("Cache-Control: no-cache;")
    @GET("/countries")
    void Countries(Callback<CountryRootDM> countryRootDMCallback);

    //6        done
    @POST("/socialnetworklinks")
    void SocialMedia(Callback<SocialMediaDM> socialMediaDMCallback);

    //7      done
    @Headers("Cache-Control: no-cache;")
    @POST("/eventcreatorreg")
    void EventCreatorReg(@Body MultipartTypedOutput multipartTypedOutput, Callback<EventRegisterDM> eventRegisterOutputCallback);

    //8           done
    @Headers("Cache-Control: no-cache;")
    @POST("/coachreg")
    void CoachReg(@Body MultipartTypedOutput multipartTypedOutput, Callback<EventRegisterDM> eventRegisterDMCallback);

    //9         done
    @FormUrlEncoded
    @POST("/otpscreen")
    void OtpVerify(@Field("mobile") String mobile,
                   @Field("otp") String otp,
                   Callback<OtpScrenRootDM> otpScrenRootDMCallback);

    //10         done
    @FormUrlEncoded
    @POST("/login")
    void Login(@Field("countrycode") String countrycode,
               @Field("mobile") String mobile,
               @Field("password") String password,
               Callback<LoginRootDM> loginRootDMCallback);

    //11             done
    @FormUrlEncoded
    @POST("/forgotpassword")
    void ForgotPassword(@Field("email") String email,
                        Callback<ForgotPasswordRootDM> forgotPasswordRootDMCallback);

    //12            done
    @FormUrlEncoded
    @POST("/changepassword")
    void ChangePassword(@Field("loginid") String loginid,
                        @Field("oldpass") String oldpass,
                        @Field("newpass") String newpass,
                        @Field("retypepass") String retypepass,
                        Callback<ChangePasswordRootDM> changePasswordRootDMCallback);

    //13                 done
    @FormUrlEncoded
    @POST("/updateprofile")
    void UpdateProfile(@Field("userid") String userid,
                       @Field("name") String name,
                       @Field("email") String email,
                       @Field("mobile") String mobile,
                       @Field("countrycode") String countrycode,
                       Callback<UpdateProfileRootDM> updateProfileRootDMCallback);

    //14              done         ,country id now static have to pass it
    @FormUrlEncoded
    @POST("/myevents")
    void MyEvents(@Field("userid") String userid,
                  @Field("countryid") String countryid,
                  Callback<MyEventRootDM1> myEventRootDM1Callback);

    //15            done
    @FormUrlEncoded
    @POST("/myprofile")
    void MyProfile(@Field("userid") String userid,
                   Callback<MyProfileRootDM> myProfileRootDMCallback);

    //16             done
    @FormUrlEncoded
    @POST("/getcoachsbycountry")
    void GetCoachsByCountry(@Field("countryid") String countryid,
                            Callback<GetCoachsByCountryRootDM> myProfileRootDMCallback);

    //17                        coachcrator , paynow ,video add functionality not now availaible
    @Headers("Cache-Control: no-cache;")
    @POST("/addeventbycreator")
    void AddEventByCreator(@Body MultipartTypedOutput multipartTypedOutput, Callback<AddEventByCreatorRootDM> addEventByCreatorRootDMCallback);

    //18                    done
    @Headers("Cache-Control: no-cache;")
    @POST("/summaryforpaidevent")
    void SummaryForPaidEvent(@Body MultipartTypedOutput multipartTypedOutput,
                             Callback<SummaryForPaidEventRootDM> summaryForPaidEventRootDMCallback);

    //19                      coachcrator , paynow ,video add functionality not now availaible
    @Headers("Cache-Control: no-cache;")
    @POST("/editevent")
    void EditEvent(@Body MultipartTypedOutput multipartTypedOutput, Callback<EditEventRootDM> editEventRootDMCallback);

    //20                        done
    @FormUrlEncoded
    @POST("/eventdetails")
    void EventDetails(@Field("eventid") String eventid,
                      Callback<EventsDetailsRootDM> eventsDetailsRootDMCallback);

    //21
    // //  done
    @POST("/confirmevent")
    void ConfirmEvent(@Body MultipartTypedOutput multipartTypedOutput,
                      Callback<ConfirmEventRootDM> confirmEventRootDMCallback);

    //21
    // //  done
    @FormUrlEncoded
    @POST("/confirmevent")
    void ConfirmEvent1(@Field("eventid") String eventid, @Field("paymentmethod") String paymentmethod,
                       Callback<ConfirmEventRootDM> confirmEventRootDMCallback);

    //22       done
    @FormUrlEncoded
    @POST("/deletevent")
    void DeletEvent(@Field("eventid") String eventid,
                    Callback<DeleteEventRootDM> deleteEventRootDMCallback);

    //    23          done
    @Headers("Cache-Control: no-cache;")
    @POST("/profilepicture")
    void ProfilePicture(@Body MultipartTypedOutput multipartTypedOutput, Callback<ProfilePictureRootDM> profilePictureRootDMCallback);


    //    24            running
    @Headers("Cache-Control: no-cache;")
    @POST("/getallcoacheswithposts")
    void GetAllCoachesWithPosts(@Body MultipartTypedOutput multipartTypedOutput, Callback<CoachesWithPostsRootDM> coachesWithPostsRootDMCallback);


    //    25      running
    @Headers("Cache-Control: no-cache;")
    @POST("/geteventsbycountrydate")
    void GetEventsByCountryDate(@Body MultipartTypedOutput multipartTypedOutput, Callback<GetEventsByCountryDateRootDM> getEventsByCountryDateRootDMCallback);

    //  26           done
    @Headers("Cache-Control: no-cache;")
    @POST("/storiesbydate")
    void StoriesByDate(@Body MultipartTypedOutput multipartTypedOutput, Callback<StoriesByDateRootDM> storiesByDateRootDMCallback);

    //27     done
    @FormUrlEncoded
    @POST("/geteventsbycountrymonth")
    void AllEvent(@Field("countryid") String userid,
                  @Field("fromdate") String fromdate,
                  @Field("todate") String todate,
                  Callback<MyEventRootDM1> myEventRootDM1Callback);

    //28
    @FormUrlEncoded
    @POST("/mynotifications")
    void MyNotifications(@Field("userid") String userid,
                         Callback<MyNotificationRootDM> myNotificationRootDMCallback);
}



