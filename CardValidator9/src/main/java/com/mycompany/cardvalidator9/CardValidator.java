/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardvalidator9;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.smartcardio.CardException;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import ru.vipconnect.schema.CreditCard;
import ru.vipconnect.ws.CardValidatorException;


/**
 *
 * @author artem
 */
@WebService(serviceName = "CardValidatorService",
        portName = "CardValidatorPort", 
        endpointInterface = "ru.vipconnect.ws.CardValidator", 
        targetNamespace = "http://ws.vipconnect.ru/")
@HandlerChain(file = "CardValidatorService_handler.xml")
public class CardValidator {
    
    @Resource
    WebServiceContext wsctx;
    
      public boolean validate(CreditCard creditCard) throws CardValidatorException {
        //TODO implement this method
            MessageContext mctx = wsctx.getMessageContext();
            Map http_headers = (Map) mctx.get(MessageContext.HTTP_REQUEST_HEADERS);
            System.out.println("http_headers="+http_headers);
            List autorizList = (List) http_headers.get("authorization");

            String authorization =autorizList.get(0).toString() ;
            authorization =authorization.substring(6);
            System.out.println("decodebase64=" + new String(Base64.decode(authorization)));
        
       
            Character lastDigit = creditCard.getNumber().charAt(
            creditCard.getNumber().length() - 1);
            if (Integer.parseInt(lastDigit.toString()) % 2 != 0) {
                return true;
            } else {
                    throw new CardValidatorException("Неверный номер карты", "efefeef");
            }
        }
}
