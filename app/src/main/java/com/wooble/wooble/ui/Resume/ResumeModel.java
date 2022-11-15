package com.wooble.wooble.ui.Resume;

public class ResumeModel {

    String title;
    String resume;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public ResumeModel(String title, String resume) {
        this.title = title;
        this.resume = resume;
    }
}
