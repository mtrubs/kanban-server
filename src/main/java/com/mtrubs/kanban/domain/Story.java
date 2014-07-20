package com.mtrubs.kanban.domain;

/**
 * @author mrubino
 * @since 2014-07-14
 */
public class Story {

    private Integer id;
    private String title;
    private String description;
    private Integer points;
    
    public Story(){}

    public Story(String title, String description, int points) {
        this.title = title;
        this.description = description;
        this.points = points;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPoints() {
        return this.points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
