package com.cashkaro.element.wrappers;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

public class CustomFieldDecorator implements FieldDecorator {

	protected ElementLocatorFactory factory;

	  public CustomFieldDecorator(ElementLocatorFactory factory) {
	    this.factory = factory;
	  }

	  public Object decorate(ClassLoader loader, Field field) {
	    if (!(WebElement.class.isAssignableFrom(field.getType())
	          || isDecoratableList(field))) {
	      return null;
	    }

	    ElementLocator locator = factory.createLocator(field);
	    if (locator == null) {
	      return null;
	    }
	    
	    Class<?> fieldType = field.getType();
        if (WebElement.class.equals(fieldType)) {
            fieldType = HtmlField.class;
        }
        
	    if (WebElement.class.isAssignableFrom(field.getType())) {
	      return proxyForLocator(loader,fieldType, locator);
	    } else if (List.class.isAssignableFrom(field.getType())) {
	      return proxyForListLocator(loader,fieldType, locator);
	    } else {
	      return null;
	    }
	  }

	  protected boolean isDecoratableList(Field field) {
	    if (!List.class.isAssignableFrom(field.getType())) {
	      return false;
	    }

	    // Type erasure in Java isn't complete. Attempt to discover the generic
	    // type of the list.
	    Type genericType = field.getGenericType();
	    if (!(genericType instanceof ParameterizedType)) {
	      return false;
	    }

	    Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];

	    if (!WebElement.class.equals(listType)) {
	      return false;
	    }

	    if (field.getAnnotation(FindBy.class) == null &&
	        field.getAnnotation(FindBys.class) == null &&
	        field.getAnnotation(FindAll.class) == null) {
	      return false;
	    }

	    return true;
	  }

	  /* Generate a type-parameterized locator proxy for the element in question. */
	    protected <T> T proxyForLocator(ClassLoader loader, Class<T> interfaceType, ElementLocator locator) {
	        InvocationHandler handler = new CustomElementHandler(interfaceType, locator);

	        T proxy;
	        proxy = interfaceType.cast(Proxy.newProxyInstance(
	                loader, new Class[]{interfaceType, WebElement.class, WrapsElement.class, Locatable.class}, handler));
	        return proxy;
	    }

	    /* generates a proxy for a list of elements to be wrapped. */
	    @SuppressWarnings("unchecked")
	    protected <T> List<T> proxyForListLocator(ClassLoader loader, Class<T> interfaceType, ElementLocator locator) {
	        InvocationHandler handler = new CustomElementListHandler(interfaceType, locator);

	        List<T> proxy;
	        proxy = (List<T>) Proxy.newProxyInstance(
	                loader, new Class[]{List.class}, handler);
	        return proxy;
	    }

}
