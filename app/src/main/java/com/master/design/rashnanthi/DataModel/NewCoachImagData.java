package com.master.design.rashnanthi.DataModel;

public class NewCoachImagData {

        private String imagevideo;

        private String date;

        private String eventid;

        private String image;

        private String filetype;

        private String eventimage;

        private String storyimage;

        private String eventorstory;

        private String id;

        private String countryfee;

        private String eventtotal;

        private String countryid;

        public String getImagevideo ()
        {
            return imagevideo;
        }

        public void setImagevideo (String imagevideo)
        {
            this.imagevideo = imagevideo;
        }

        public String getDate ()
        {
            return date;
        }

        public void setDate (String date)
        {
            this.date = date;
        }

        public String getEventid ()
        {
            return eventid;
        }

        public void setEventid (String eventid)
        {
            this.eventid = eventid;
        }

        public String getImage ()
        {
            return image;
        }

        public void setImage (String image)
        {
            this.image = image;
        }

        public String getFiletype ()
        {
            return filetype;
        }

        public void setFiletype (String filetype)
        {
            this.filetype = filetype;
        }

        public String getEventimage ()
        {
            return eventimage;
        }

        public void setEventimage (String eventimage)
        {
            this.eventimage = eventimage;
        }

        public String getStoryimage ()
    {
        return storyimage;
    }

        public void setStoryimage (String storyimage)
        {
            this.storyimage = storyimage;
        }

        public String getEventorstory ()
    {
        return eventorstory;
    }

        public void setEventorstory (String eventorstory)
        {
            this.eventorstory = eventorstory;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getCountryfee ()
        {
            return countryfee;
        }

        public void setCountryfee (String countryfee)
        {
            this.countryfee = countryfee;
        }

        public String getEventtotal ()
        {
            return eventtotal;
        }

        public void setEventtotal (String eventtotal)
        {
            this.eventtotal = eventtotal;
        }

        public String getCountryid ()
        {
            return countryid;
        }

        public void setCountryid (String countryid)
        {
            this.countryid = countryid;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [imagevideo = "+imagevideo+", date = "+date+", eventid = "+eventid+", image = "+image+", filetype = "+filetype+", eventimage = "+eventimage+", storyimage = "+storyimage+", eventorstory = "+eventorstory+", id = "+id+", countryfee = "+countryfee+", eventtotal = "+eventtotal+", countryid = "+countryid+"]";
        }

}
