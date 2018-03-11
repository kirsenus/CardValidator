/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.cardvalidator9.handler;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.w3c.dom.Document;

/**
 *
 * @author artem
 */
public class MacAddressValidatorHandler implements SOAPHandler<SOAPMessageContext> {
    
    @Override
    public boolean handleMessage(SOAPMessageContext messageContext) {
        System.out.println("Server : handleMessage()......");
         SOAPMessage message =   messageContext.getMessage();
        
         
	Boolean isRequest = (Boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (!isRequest)
        {
            
             Map http_headers = (Map) messageContext.get(MessageContext.HTTP_REQUEST_HEADERS);
             System.out.println("HTTP_REQUEST_METHOD="+ messageContext.get(MessageContext.HTTP_REQUEST_METHOD));
              System.out.println("PATH_INFO="+ messageContext.get(MessageContext.PATH_INFO));
                System.out.println("QUERY_STRING="+ messageContext.get(MessageContext.QUERY_STRING));
                 System.out.println("SERVLET_REQUEST="+ messageContext.get(MessageContext.SERVLET_REQUEST));
                
            System.out.println("http_headersHH="+http_headers);
            List autorizList = (List) http_headers.get("authorization");

            String authorization =autorizList.get(0).toString() ;
            authorization =authorization.substring(6);
            System.out.println("decodebase64HH=" + new String(Base64.decode(authorization)));
        }
          System.out.println("myVarServerHendler="+messageContext.get("myVar"));
             
	try{
	    SOAPMessage soapMsg = messageContext.getMessage();
	    SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
            SOAPHeader soapHeader = soapEnv.getHeader();
            System.out.println(printSoapMessage(soapMsg));
          // System.out.println( convertToString(soapMsg.getSOAPBody()));

// soapMsg.writeTo(System.out);
        
            //if no header, add one
	    if (soapHeader == null){
	            soapHeader = soapEnv.addHeader();
	            //throw exception
	          //  generateSOAPErrMessage(soapMsg, "No SOAP header.");
	     }
        }catch(SOAPException e)
        {
		System.err.println(e);
	} catch (TransformerFactoryConfigurationError ex) {
                Logger.getLogger(MacAddressValidatorHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException ex) {
                Logger.getLogger(MacAddressValidatorHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
            Logger.getLogger(MacAddressValidatorHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        //tracking
	//           }
        return true;
    }
    
    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }
    
    public boolean handleFault(SOAPMessageContext messageContext) {
        return true;
    }
    
    public void close(MessageContext context) {
    }
    
    public static String printSoapMessage(final SOAPMessage soapMessage) throws TransformerFactoryConfigurationError,
            TransformerConfigurationException, SOAPException, TransformerException
    {
        final TransformerFactory transformerFactory = TransformerFactory.newInstance();
        final Transformer transformer = transformerFactory.newTransformer();

        // Format it
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        final Source soapContent = soapMessage.getSOAPPart().getContent();

        final ByteArrayOutputStream streamOut = new ByteArrayOutputStream();
        final StreamResult result = new StreamResult(streamOut);
        transformer.transform(soapContent, result);

        return streamOut.toString();
    }
    
    private String convertToString (SOAPBody message) throws Exception{
   Document doc = message.extractContentAsDocument();
   StringWriter sw = new StringWriter();
   TransformerFactory tf = TransformerFactory.newInstance();
   Transformer transformer = tf.newTransformer();

     transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

   
   transformer.transform(new DOMSource(doc), new StreamResult(sw));
   return sw.toString();
  }
}
