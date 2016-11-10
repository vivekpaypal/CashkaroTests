package com.cashkaro.element.wrappers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class CustomElementHandler implements InvocationHandler{

	private final ElementLocator locator;
    private final Class<?> wrappingType;

    /* Generates a handler to retrieve the WebElement from a locator for 
       a given WebElement interface descendant. */
    public <T> CustomElementHandler(Class<T> interfaceType, ElementLocator locator) {
        this.locator = locator;
        if (!HtmlField.class.isAssignableFrom(interfaceType)) {
            throw new RuntimeException("interface not assignable to Element.");
        }

        this.wrappingType = getWrapperClass(interfaceType);
    }
    
    public Class<?> getWrapperClass(Class<?> type){
    	
    	return type.getClass();
    	
    }

    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        WebElement element = locator.findElement();

        if ("getWrappedElement".equals(method.getName())) {
            return element;
        }
        Constructor cons = wrappingType.getConstructor(WebElement.class);
        Object thing = cons.newInstance(element);
        try {
            return method.invoke(wrappingType.cast(thing), objects);
        } catch (InvocationTargetException e) {
            // Unwrap the underlying exception
            throw e.getCause();
        }
    }

}
