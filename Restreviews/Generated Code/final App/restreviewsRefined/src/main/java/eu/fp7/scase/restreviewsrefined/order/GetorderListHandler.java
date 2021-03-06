/*
 * ARISTOSTLE UNIVERSITY OF THESSALONIKI
 * Copyright (C) 2015
 * Aristotle University of Thessaloniki
 * Department of Electrical & Computer Engineering
 * Division of Electronics & Computer Engineering
 * Intelligent Systems & Software Engineering Lab
 *
 * Project             : restreviewsRefined
 * WorkFile            : 
 * Compiler            : 
 * File Description    : 
 * Document Description: 
* Related Documents	   : 
* Note				   : 
* Programmer		   : RESTful MDE Engine created by Christoforos Zolotas
* Contact			   : christopherzolotas@issel.ee.auth.gr
*/


package eu.fp7.scase.restreviewsrefined.order;


import javax.ws.rs.core.UriInfo;

import java.util.Iterator;
import eu.fp7.scase.restreviewsrefined.utilities.HypermediaLink;
import eu.fp7.scase.restreviewsrefined.utilities.HibernateController;
import java.util.Set;
import java.util.HashSet;

/* This class processes GET requests for order resources and creates the hypermedia to be returned to the client*/

public class GetorderListHandler{

    private HibernateController oHibernateController;
    private UriInfo oApplicationUri; //Standard datatype that holds information on the URI info of this request
	private String strResourcePath; //relative path to the current resource
    private Set<JavaorderModel> SetOforderModel = new HashSet<JavaorderModel>(); // Since this resource is not related of any other, this HashSet will hold the list to be sent back to client.

    public GetorderListHandler(UriInfo oApplicationUri){
        this.oHibernateController = HibernateController.getHibernateControllerHandle();
        this.oApplicationUri = oApplicationUri;
		this.strResourcePath = calculateProperResourcePath();
    }

	public String calculateProperResourcePath(){
    	if(this.oApplicationUri.getPath().lastIndexOf('/') == this.oApplicationUri.getPath().length() - 1){
        	return this.oApplicationUri.getPath().substring(0, this.oApplicationUri.getPath().length() - 1);
    	}
    	else{
        	return this.oApplicationUri.getPath();
    	}
	}

    public JavaorderModelManager getJavaorderModelManager(){


        this.SetOforderModel = oHibernateController.getorderList(this.SetOforderModel);
        return createHypermedia();
    }


    /* This function produces hypermedia links to be sent to the client so as it will be able to forward the application state in a valid way.*/
    public JavaorderModelManager createHypermedia(){
        JavaorderModelManager oJavaorderModelManager = new JavaorderModelManager();

        /* Create hypermedia links towards this specific order resource. These must be GET and POST as it is prescribed in the meta-models.*/
        oJavaorderModelManager.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath), "Get all orders", "GET", "Sibling"));
        oJavaorderModelManager.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath), "Create a new order", "POST", "Sibling"));

        /* Then calculate the relative path to any related resource of this one and add for each one a hypermedia link to the Linklist.*/
        String oRelativePath;
        oRelativePath = this.strResourcePath;
        Iterator<JavaorderModel> setIterator = this.SetOforderModel.iterator();
        while(setIterator.hasNext()){
            JavaorderModel oNextJavaorderModel = new JavaorderModel();
            oNextJavaorderModel = setIterator.next();
            oJavaorderModelManager.getlinklist().add(new HypermediaLink(String.format("%s%s/%d", oApplicationUri.getBaseUri(), oRelativePath, oNextJavaorderModel.getorderId()), String.valueOf(oNextJavaorderModel.getstatus()), "GET", "Child", oNextJavaorderModel.getorderId()));
        }
        return oJavaorderModelManager;
    }
}
