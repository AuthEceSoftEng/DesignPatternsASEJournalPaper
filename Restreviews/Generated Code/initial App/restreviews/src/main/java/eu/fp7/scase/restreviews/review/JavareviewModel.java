/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI
 * Copyright (C) 2015
 * Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering
 * Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 *
 * Project             : restreviews
 * WorkFile            : 
 * Compiler            : 
 * File Description    : 
 * Document Description: 
* Related Documents	   : 
* Note				   : 
* Programmer		   : RESTful MDE Engine created by Christoforos Zolotas
* Contact			   : christopherzolotas@issel.ee.auth.gr
*/


package eu.fp7.scase.restreviews.review;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


import org.hibernate.annotations.ForeignKey;

import eu.fp7.scase.restreviews.utilities.HypermediaLink;
import eu.fp7.scase.restreviews.product.JavaproductModel;


/* This class models the data of a review resource. It is enhanced with JAXB annotations for automated representation
parsing/marshalling as well as with Hibernate annotations for ORM transformations.*/
@XmlRootElement
@Entity
@Table(name="\"review\"")
public class JavareviewModel{


    /* There follows a list with the properties that model the review resource, as prescribed in the service CIM*/
		@Column(name = "\"review\"")
		private String review;

		@Column(name = "\"title\"")
		private String title;

		@Column(name = "\"rating\"")
		private int rating;

		@Id
		@GeneratedValue
		@Column(name = "\"reviewid\"")
		private int reviewId;

		// The Linklist property holds all the hypermedia links to be sent back to the client
		@Transient
		private List<HypermediaLink> linklist = new ArrayList<HypermediaLink>();

		// This property models the Many to One relationship between two resources as it is defined by the Hibernate syntax below.
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name="productId")
		@ForeignKey(name = "fk_product_review")
		private JavaproductModel product;

	@OneToMany(fetch = FetchType.EAGER, mappedBy="observableJavareviewModel",orphanRemoval=true)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Set<JavareviewObserver> SetOfJavareviewObserver = new HashSet<JavareviewObserver>();
	
	@Transient
	private int observerId;

	public int getobserverId(){
    	return this.observerId;
	}

	public void setobserverId(int id){
    	this.observerId = id;
	}

    /* There follows a list of setter and getter functions.*/
	    public void setreview(String review){
        	this.review = review;
    	}

	    public void settitle(String title){
        	this.title = title;
    	}

	    public void setrating(int rating){
        	this.rating = rating;
    	}

	    public void setreviewId(int reviewId){
        	this.reviewId = reviewId;
    	}

	    public void setlinklist(List<HypermediaLink> linklist){
        	this.linklist = linklist;
    	}

		@XmlTransient
	    public void setproduct(JavaproductModel product){
        	this.product = product;
    	}

	    public String getreview(){
        	return this.review;
    	}

	    public String gettitle(){
        	return this.title;
    	}

	    public int getrating(){
        	return this.rating;
    	}

	    public int getreviewId(){
        	return this.reviewId;
    	}

	    public List<HypermediaLink> getlinklist(){
        	return this.linklist;
    	}

	    public JavaproductModel getproduct(){
        	return this.product;
    	}


}
