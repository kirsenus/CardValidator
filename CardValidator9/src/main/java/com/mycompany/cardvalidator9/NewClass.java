/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardvalidator9;

import ru.vipconnect.schema.CreditCard;
import ru.vipconnect.ws.CardValidatorException;
import ru.vipconnect.ws.CardValidatorService;


/**
 *
 * @author artem
 */
public class NewClass {
     public static void main(String[] args) throws CardValidatorException {
        System.out.println("ASASSS");
     //   try { // Call Web Service Operation
           CardValidatorService service = new CardValidatorService();
            ru.vipconnect.ws.CardValidator port = service.getCardValidatorPort();
            // TODO initialize WS operation arguments here
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
