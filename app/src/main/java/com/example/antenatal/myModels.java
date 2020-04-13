package com.example.antenatal;

public class myModels {

    public class tipsModel{
        String tipsDescription;
        public tipsModel(String tipsDescription) {
            this.tipsDescription = tipsDescription;
        }

        public String getTipsDescription() {
            return tipsDescription;
        }
    }


    public class trimesterModel{
        String trimmester,title, fullContent, subContent;

        public trimesterModel(String trimmester, String title, String fullContent, String subContent) {
            this.trimmester = trimmester;
            this.title = title;
            this.fullContent = fullContent;
            this.subContent = subContent;
        }

        public String getTrimmester() {
            return trimmester;
        }

        public String getTitle() {
            return title;
        }

        public String getFullContent() {
            return fullContent;
        }

        public String getSubContent() {
            return subContent;
        }
    }

    public class notice {

        private String body;
        private String title;
        private String author;
        private String date;
        private String noticeId;

        public notice(String body, String title, String author, String date, String noticeId) {
            this.body = body;
            this.title = title;
            this.author = author;
            this.date = date;
            this.noticeId = noticeId;
        }

        public String getBody() {
            return body;
        }

        public String getNoticeID() {
            return noticeId;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getDate() {
            return date;
        }
    }

    public class Doctor{
        String docName, docPhone, docEmail;

        public Doctor(String docName, String docPhone, String docEmail) {
            this.docName = docName;
            this.docPhone = docPhone;
            this.docEmail = docEmail;
        }

        public String getDocName() {
            return docName;
        }

        public String getDocPhone() {
            return docPhone;
        }

        public String getDocEmail() {
            return docEmail;
        }
    }
    public class Appointment{
        String  dateSchedule, valid, timeSchedule, doctype, docname, purpose, outcome;

        public Appointment(String dateSchedule, String valid, String timeSchedule, String doctype, String docname, String purpose, String outcome) {
            this.dateSchedule = dateSchedule;
            this.valid = valid;
            this.timeSchedule = timeSchedule;
            this.doctype = doctype;
            this.docname = docname;
            this.purpose = purpose;
            this.outcome = outcome;
        }

        public String getDateSchedule() {
            return dateSchedule;
        }

        public String getValid() {
            return valid;
        }

        public String getTimeSchedule() {
            return timeSchedule;
        }

        public String getDoctype() {
            return doctype;
        }

        public String getDocname() {
            return docname;
        }

        public String getPurpose() {
            return purpose;
        }

        public String getOutcome() {
            return outcome;
        }
    }
}
