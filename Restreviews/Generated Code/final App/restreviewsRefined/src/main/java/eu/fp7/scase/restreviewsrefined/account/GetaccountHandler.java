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
* Programmer		   : RESTful MDE Engine created by Christoforos Zolotas / Design patterns implementations created by Dontsios Dimitrios
* Contact			   : christopherzolotas@issel.ee.auth.gr / dontsios@gmail.com
*/


package eu.fp7.scase.restreviewsrefined.account;


import javax.ws.rs.core.UriInfo;
import eu.fp7.scase.restreviewsrefined.account.JavaaccountModelRepresentation;
import java.util.Arrays;

import eu.fp7.scase.restreviewsrefined.utilities.HypermediaLink;
import eu.fp7.scase.restreviewsrefined.utilities.HibernateController;

/* This class processes GET requests for account resources and creates the hypermedia to be returned to the client*/
public class GetaccountHandler{


    private HibernateController oHibernateController;
    private UriInfo oApplicationUri; //Standard datatype that holds information on the URI info of this request
	private String strResourcePath; //relative path to the current resource
	private JavaaccountModelRepresentation oJavaaccountModelRepresentation;
    private JavaaccountModel oJavaaccountModel;

    public GetaccountHandler(int accountId,String representationName,  UriInfo oApplicationUri){
        oJavaaccountModel = new JavaaccountModel();
        oJavaaccountModel.setaccountId(accountId);
        oJavaaccountModelRepresentation = new JavaaccountModelRepresentation();
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


	public JavaaccountModelRepresentation getJavaaccountModelRepresentation(String representationName){
		oJavaaccountModel = oHibernateController.getaccount(oJavaaccountModel);
		String[] representationNames = { "account_email_username",};
		if (Arrays.asList(representationNames).contains(representationName) ) {
		if(representationName.contains("email")){
			oJavaaccountModelRepresentation.email(oJavaaccountModel.getemail()).build();
		}			//email 
		//RESTfulServicePSM.impl.PSMComponentPropertyImpl@15ede14e (name: email, type: String, bIsUnique: true, bIsNamingProperty: false, bIsPrimaryIdentifier: false)RESTfulServicePSM.impl.PSMComponentPropertyImpl@2cf4dd07 (name: username, type: String, bIsUnique: true, bIsNamingProperty: true, bIsPrimaryIdentifier: false)
			oJavaaccountModelRepresentation.password(oJavaaccountModel.getpassword()).build();
		//password 
		//RESTfulServicePSM.impl.PSMComponentPropertyImpl@15ede14e (name: email, type: String, bIsUnique: true, bIsNamingProperty: false, bIsPrimaryIdentifier: false)RESTfulServicePSM.impl.PSMComponentPropertyImpl@2cf4dd07 (name: username, type: String, bIsUnique: true, bIsNamingProperty: true, bIsPrimaryIdentifier: false)
		if(representationName.contains("username")){
			oJavaaccountModelRepresentation.username(oJavaaccountModel.getusername()).build();
		}			//username 
		//RESTfulServicePSM.impl.PSMComponentPropertyImpl@15ede14e (name: email, type: String, bIsUnique: true, bIsNamingProperty: false, bIsPrimaryIdentifier: false)RESTfulServicePSM.impl.PSMComponentPropertyImpl@2cf4dd07 (name: username, type: String, bIsUnique: true, bIsNamingProperty: true, bIsPrimaryIdentifier: false)
			oJavaaccountModelRepresentation.accountId(oJavaaccountModel.getaccountId()).build();
		//accountId 
		//RESTfulServicePSM.impl.PSMComponentPropertyImpl@15ede14e (name: email, type: String, bIsUnique: true, bIsNamingProperty: false, bIsPrimaryIdentifier: false)RESTfulServicePSM.impl.PSMComponentPropertyImpl@2cf4dd07 (name: username, type: String, bIsUnique: true, bIsNamingProperty: true, bIsPrimaryIdentifier: false)
			oJavaaccountModelRepresentation.linklist(oJavaaccountModel.getlinklist()).build();
		//linklist 
		//RESTfulServicePSM.impl.PSMComponentPropertyImpl@15ede14e (name: email, type: String, bIsUnique: true, bIsNamingProperty: false, bIsPrimaryIdentifier: false)RESTfulServicePSM.impl.PSMComponentPropertyImpl@2cf4dd07 (name: username, type: String, bIsUnique: true, bIsNamingProperty: true, bIsPrimaryIdentifier: false)
			oJavaaccountModelRepresentation.SetOfJavaproductModel(oJavaaccountModel.getSetOfJavaproductModel()).build();
		//SetOfJavaproductModel 
		//RESTfulServicePSM.impl.PSMComponentPropertyImpl@15ede14e (name: email, type: String, bIsUnique: true, bIsNamingProperty: false, bIsPrimaryIdentifier: false)RESTfulServicePSM.impl.PSMComponentPropertyImpl@2cf4dd07 (name: username, type: String, bIsUnique: true, bIsNamingProperty: true, bIsPrimaryIdentifier: false)
		}else{
			oJavaaccountModelRepresentation.setaccountRepresentationName("account_email_username");
		}
		oJavaaccountModelRepresentation.accountId(oJavaaccountModel.getaccountId()); //.email(oJavaaccountModel.getString()).password(oJavaaccountModel.getString()).username(oJavaaccountModel.getString()).accountId(oJavaaccountModel.getint()).ListOfHypermediaLink(oJavaaccountModel.getListOfHypermediaLink()).SetOfJavaproductModel(oJavaaccountModel.getSetOfJavaproductModel()).build();
		return createHypermedia(oJavaaccountModelRepresentation);
	}

    



	
    /* This function produces hypermedia links to be sent to the client so as it will be able to forward the application state in a valid way.*/
	 public JavaaccountModelRepresentation createHypermedia(JavaaccountModelRepresentation oJavaaccountModelRepresentation){

        /* Create hypermedia links towards this specific account resource. These can be GET, PUT and/or delete depending on what was specified in the service CIM.*/
        oJavaaccountModelRepresentation.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath), "Get the account", "GET", "Sibling"));
        oJavaaccountModelRepresentation.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath), "Update the account", "PUT", "Sibling"));
        oJavaaccountModelRepresentation.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath), "Delete the account", "DELETE", "Sibling"));

        /* Calculate the relative path towards any related resources of this one. Then add each new hypermedia link with that relative path to the hypermedia linklist to be sent back to client.*/
        String oRelativePath;
		oRelativePath = this.strResourcePath;
        oJavaaccountModelRepresentation.getlinklist().add(new HypermediaLink(String.format("%s%s/%s/%s", oApplicationUri.getBaseUri(), "multiproductManager", oRelativePath, "product"), "Get all the products of this account", "GET", "Child"));
        oJavaaccountModelRepresentation.getlinklist().add(new HypermediaLink(String.format("%s%s/%s/%s", oApplicationUri.getBaseUri(), "multiproductManager", oRelativePath, "product"), "Create a new product for this account", "POST", "Child"));

        /* Finally, truncate the current URI so as to point to the resource manager of which this resource is related.
        Then create the hypermedia links towards the parent resources.*/
        int iLastSlashIndex = String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath).lastIndexOf("/");
        oJavaaccountModelRepresentation.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath).substring(0, iLastSlashIndex), "Create a new account", "POST", "Parent"));
        oJavaaccountModelRepresentation.getlinklist().add(new HypermediaLink(String.format("%s%s", oApplicationUri.getBaseUri(), this.strResourcePath).substring(0, iLastSlashIndex), "Get all accounts", "GET", "Parent"));
        return oJavaaccountModelRepresentation;
    }
}

/*
1st try
DesignPatternsLayerPSM.impl.AnnJavaResourceModelImpl@671d7871DesignPatternsLayerPSM.impl.AnnJavaResourceModelImpl@39fcefeaDesignPatternsLayerPSM.impl.AnnJavaResourceModelImpl@f124162DesignPatternsLayerPSM.impl.AnnJavaAlgoResourceModelImpl@6f6363DesignPatternsLayerPSM.impl.AnnJavaAlgoResourceModelImpl@5ff8ddb5DesignPatternsLayerPSM.impl.AnnPSMComponentPropertyImpl@820d605DesignPatternsLayerPSM.impl.AnnPSMComponentPropertyImpl@58b7b0d7DesignPatternsLayerPSM.impl.AnnJavaHTTPActivityHandlerImpl@555bcb75DesignPatternsLayerPSM.impl.AnnJavaHTTPActivityHandlerImpl@7048095e

2nd try
DesignPatternsLayerPSM.impl.AnnJavaResourceModelImpl@671d7871

wtf is going on here
DesignPatternsLayerPSM.impl.AnnJavaResourceModelImpl@671d7871

wtf is going on here 2
true

wtf is going on here 3
DesignPatternsLayerPSM.impl.AnnJavaResourceModelImpl@39fcefea

wtf is going on here 4
false


3rd
DesignPatternsLayerPSM.impl.AnnJavaResourceModelImpl@39fcefeaDesignPatternsLayerPSM.impl.AnnJavaAlgoResourceModelImpl@6f6363DesignPatternsLayerPSM.impl.AnnPSMComponentPropertyImpl@58b7b0d7DesignPatternsLayerPSM.impl.AnnJavaResourceModelImpl@671d7871DesignPatternsLayerPSM.impl.AnnJavaHTTPActivityHandlerImpl@7048095eDesignPatternsLayerPSM.impl.AnnJavaAlgoResourceModelImpl@5ff8ddb5DesignPatternsLayerPSM.impl.AnnPSMComponentPropertyImpl@820d605DesignPatternsLayerPSM.impl.AnnJavaResourceModelImpl@f124162DesignPatternsLayerPSM.impl.AnnJavaHTTPActivityHandlerImpl@555bcb75

Does it recognise AnnPSMCOMPONENTPROPERTY
true

AnnPSMComponentProperty list?
DesignPatternsLayerPSM.impl.AnnPSMComponentPropertyImpl@820d605DesignPatternsLayerPSM.impl.AnnPSMComponentPropertyImpl@58b7b0d7

test
DesignPatternsLayerPSM.impl.JavaBuilderPatternImpl@63828988

test 2 correct
1

test 3 correct
DesignPatternsLayerPSM.impl.JavaConcreteBuilderImpl@74d5c83

test 4 correct
DesignPatternsLayerPSM.impl.JavaConcreteBuilderImpl@74d5c83

test 5 correct
DesignPatternsLayerPSM.impl.JavaConcreteBuilderImpl@74d5c83

test6 correct
	account

test7 correct
	RESTfulServicePSM.impl.PSMComponentPropertyImpl@15ede14e (name: email, type: String, bIsUnique: true, bIsNamingProperty: false, bIsPrimaryIdentifier: false)RESTfulServicePSM.impl.PSMComponentPropertyImpl@2cf4dd07 (name: username, type: String, bIsUnique: true, bIsNamingProperty: true, bIsPrimaryIdentifier: false)

test8
	emailusername

test9 corrext
		email
		username

*/

