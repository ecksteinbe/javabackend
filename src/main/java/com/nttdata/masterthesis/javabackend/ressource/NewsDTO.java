/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.ressource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nttdata.masterthesis.javabackend.helper.CustomJsonDateTimeSerializer;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MATHAF
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsDTO {

    private String message;
    private Date date;
    private String image;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonSerialize(using=CustomJsonDateTimeSerializer.class)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
