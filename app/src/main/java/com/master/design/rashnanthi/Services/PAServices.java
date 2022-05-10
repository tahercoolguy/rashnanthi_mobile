package com.master.design.rashnanthi.Services;


//import io.opencensus.stats.Stats;


import com.master.design.rashnanthi.DataModel.AboutUsDM;
import com.master.design.rashnanthi.DataModel.BannerDM;
import com.master.design.rashnanthi.DataModel.EventsDM;
import com.master.design.rashnanthi.DataModel.NewsDM;
import com.master.design.rashnanthi.DataModel.RestaurentDM;
import com.master.design.rashnanthi.DataModel.ShopsDM;
import com.master.design.rashnanthi.DataModel.SignUpDM;
import com.master.design.rashnanthi.DataModel.SocialMediaDM;
import com.master.design.rashnanthi.DataModel.VideoDM;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.mime.MultipartTypedOutput;

public interface PAServices {
    @Headers("Cache-Control: no-cache;")
    @POST("/auth/signin")
    void SignUp(@Body MultipartTypedOutput multipartTypedOutput, Callback<SignUpDM> signUpDMCallback);

    @Headers("Cache-Control: no-cache;")
    @POST("/auth/record_video")
    void RecordedVideo(@Body MultipartTypedOutput multipartTypedOutput, Callback<VideoDM> videoDMCallback);

    @Headers("Cache-Control: no-cache;")
    @POST("/auth/user_login")
    void LoginIn(@Body MultipartTypedOutput multipartTypedOutput, Callback<SignUpDM> signUpDMCallback);

    @Headers("Cache-Control: no-cache;")
    @GET("/user/view_home_banner")
    void Banner(Callback<BannerDM> bannerDMCallback);

    @Headers("Cache-Control: no-cache;")
    @GET("/user/view_news")
    void News(Callback<NewsDM> newsDMCallback);

    @Headers("Cache-Control: no-cache;")
    @GET("/user/view_events")
    void Events(Callback<EventsDM> eventsDMCallback);

    @Headers("Cache-Control: no-cache;")
    @GET("/user/view_restaurants")
    void Restaurent(Callback<RestaurentDM> restaurentDMCallback);


    @Headers("Cache-Control: no-cache;")
    @GET("/user/view_shops")
    void Shops(Callback<ShopsDM> shopsDMCallback);

    //(01)
// About us  10-05-2022
    @POST("/aboutus")
    void Aboutus(Callback<AboutUsDM> aboutUsDMCallback);


    //(02)
// Terms  10-05-2022
    @POST("/termsandcond")
    void TermsAndCondition(Callback<AboutUsDM> aboutUsDMCallback);


    //(03)
//Privacy policy  10-05-2022
    @POST("/privacypolicy")
    void PrivacyPolicy(Callback<AboutUsDM> aboutUsDMCallback);

    //(04)
//Contact us  10-05-2022
    @POST("/contactus")
    void ContactUS(Callback<AboutUsDM> aboutUsDMCallback);

    //(05)
//Social Media DM 10-05-2022
    @POST("/socialnetworklinks")
    void SocialMedia(Callback<SocialMediaDM> socialMediaDMCallback);
}
