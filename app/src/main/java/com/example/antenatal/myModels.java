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
}
