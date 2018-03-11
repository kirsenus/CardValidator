/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardvalidator9client;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;
import ru.vipconnect.schema.CreditCard;
import ru.vipconnect.ws.CardValidator;
import ru.vipconnect.ws.CardValidatorException;
import ru.vipconnect.ws.CardValidatorService;



/**
 *
 * @author artem
 */
public class NewClass {
     public static void main(String[] args) throws CardValidatorException {
        System.out.println("ASASSS");
        System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
        
        
        
     //   try { // Call Web Service Operation
            CardValidatorService service = new CardValidatorService();
            CardValidator port = service.getCardValidatorPort();
            javax.xml.ws.BindingProvider bp = (javax.xml.ws.BindingProvider)port;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8080/CardValidator9/CardValidatorService");
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY,"artem");
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY,"mypasssword");
            bp.getRequestContext().put("myVar","blabla");
             Binding binding = bp.getBinding();
             List<Handler> handlerList = binding.getHandlerChain();
             handlerList.add(new NewMessageHandler());
              binding.setHandlerChain(handlerList);
            
            
    /*        
            Map<String, List<String>> headers = new HashMap<String, List<String>>();
            headers.put("Username", Collections.singletonList("mkyong"));
            headers.put("Password", Collections.singletonList("password"));
             bp.getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, headers);
           
// TODO initialize WS operation arguments here
            System.out.println(bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY));
             System.out.println(bp.getRequestContext().get(BindingProvider.PASSWORD_PROPERTY));
              System.out.println(bp.getRequestContext().get(BindingProvider.USERNAME_PROPERTY));
             */
           CreditCard creditCard = new CreditCard();
            // TODO process result here
            creditCard.setNumber("121212121232");
            boolean result = port.validate(creditCard);
            System.out.println("Result = "+result);
       // } catch (Exception ex) {
            // TODO handle custom exceptions here
        //}

    }
}
