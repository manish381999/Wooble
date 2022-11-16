package com.wooble.wooble.ui.Project;

public class ProjectModel {

    String file_id;
    String email_id;
    String project_name;
    String aim_of_project;
    String description;
    String image_1;
    String image_2;
    String image_3;
    String image_4;
    String image_5;
    String image_6;
    String video;
    String pdf_file;
    String conclusion;

    public ProjectModel(String file_id, String email_id, String project_name, String aim_of_project, String description, String image_1, String image_2, String image_3, String image_4, String image_5, String image_6, String video, String pdf_file, String conclusion) {
        this.file_id = file_id;
        this.email_id = email_id;
        this.project_name = project_name;
        this.aim_of_project = aim_of_project;
        this.description = description;
        this.image_1 = image_1;
        this.image_2 = image_2;
        this.image_3 = image_3;
        this.image_4 = image_4;
        this.image_5 = image_5;
        this.image_6 = image_6;
        this.video = video;
        this.pdf_file = pdf_file;
        this.conclusion = conclusion;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getAim_of_project() {
        return aim_of_project;
    }

    public void setAim_of_project(String aim_of_project) {
        this.aim_of_project = aim_of_project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getPdf_file() {
        return pdf_file;
    }

    public void setPdf_file(String pdf_file) {
        this.pdf_file = pdf_file;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }
}
