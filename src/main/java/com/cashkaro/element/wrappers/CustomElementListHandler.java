package com.cashkaro.element.wrappers;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class CustomElementListHandler implements InvocationHandler {
	private final ElementLocator locator;

	  public <T> CustomElementListHandler(Class<T> interfaceType,ElementLocator locator) {
	    this.locator = locator;
	  }

	 

	public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
	    List<WebElement> elements = locator.findElements();

	    try {
	      return method.invoke(elements, objects);
	    } catch (InvocationTargetException e) {
	      // Unwrap the underlying exception
	      throw e.getCause();
	    }
	  }
}
