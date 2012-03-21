package com.blucargo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Table(name="comment")
public class Comment implements Serializable {
	
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long otherCommentId;
    private Long cargoOfferId;
    
    @Column(nullable=false)
    private String author;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    private String content;

    
    //-1 negative
    //0 positive
    //1 neutral
    private Integer positivity;

    
    public Comment(){
    	this.setDate(new Date());
    }

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getOtherCommentId() {
		return otherCommentId;
	}


	public void setOtherCommentId(Long otherCommentId) {
		this.otherCommentId = otherCommentId;
	}


	public Long getCargoOfferId() {
		return cargoOfferId;
	}


	public void setCargoOfferId(Long cargoOfferId) {
		this.cargoOfferId = cargoOfferId;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Integer getPositivity() {
		return positivity;
	}


	public void setPositivity(Integer positivity) {
		this.positivity = positivity;
	}
    
    
    
}
