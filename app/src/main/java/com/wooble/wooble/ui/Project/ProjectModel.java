package com.wooble.wooble.ui.Project;

public class ProjectModel {

    String entry_id;
    String work_title;
    String work_description;
    String email_id;
    String image_1;
    String image_2;
    String image_3;
    String image_4;
    String image_5;
    String image_6;
    String video;
    String conclusion;
    String pdf_file;
    String aim_of_work;


    public ProjectModel(String entry_id, String work_title, String work_description, String email_id, String image_1, String image_2, String image_3, String image_4, String image_5, String image_6, String video, String conclusion, String pdf_file, String aim_of_work) {
        this.entry_id = entry_id;
        this.work_title = work_title;
        this.work_description = work_description;
        this.email_id = email_id;
        this.image_1 = image_1;
        this.image_2 = image_2;
        this.image_3 = image_3;
        this.image_4 = image_4;
        this.image_5 = image_5;
        this.image_6 = image_6;
        this.video = video;
        this.conclusion = conclusion;
        this.pdf_file = pdf_file;
        this.aim_of_work = aim_of_work;
    }

    public String getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(String entry_id) {
        this.entry_id = entry_id;
    }

    public String getWork_title() {
        return work_title;
    }

    public void setWork_title(String work_title) {
        this.work_title = work_title;
    }

    public String getWork_description() {
        return work_description;
    }

    public void setWork_description(String work_description) {
        this.work_description = work_description;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getImage_1() {
        return image_1;
    }

    public void setImage_1(String image_1) {
        this.image_1 = image_1;
    }

    public String getImage_2() {
        return image_2;
    }

    public void setImage_2(String image_2) {
        this.image_2 = image_2;
    }

    public String getImage_3() {
        return image_3;
    }

    public void setImage_3(String image_3) {
        this.image_3 = image_3;
    }

    public String getImage_4() {
        return image_4;
    }

    public void setImage_4(String image_4) {
        this.image_4 = image_4;
    }

    public String getImage_5() {
        return image_5;
    }

    public void setImage_5(String image_5) {
        this.image_5 = image_5;
    }

    public String getImage_6() {
        return image_6;
    }

    public void setImage_6(String image_6) {
        this.image_6 = image_6;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getPdf_file() {
        return pdf_file;
    }

    public void setPdf_file(String pdf_file) {
        this.pdf_file = pdf_file;
    }

    public String getAim_of_work() {
        return aim_of_work;
    }

    public void setAim_of_work(String aim_of_work) {
        this.aim_of_work = aim_of_work;
    }
}
