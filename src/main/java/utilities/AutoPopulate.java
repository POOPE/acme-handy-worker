
package utilities;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class AutoPopulate {

	public static final String		xmlFilePath	= "C:\\Documents and Settings\\Student\\Desktop\\xmlfile.xml";

	public static List<Class<?>>	classList	= new ArrayList<>();


	public static void main(final String[] args) {
		//by default create 3 intances of each class
		final int iter = 3;

		//get all class names in package domain
		AutoPopulate.classList = ClassFinder.find("domain");
		//for all classes domainentity will have to be ignored

		if (AutoPopulate.classList != null)
			try {
				//build xml document
				final DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
				final DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
				final Document document = documentBuilder.newDocument();

				// root element
				final Element root = document.createElement("beans");
				final Attr xmlns = document.createAttribute("xmlns");
				xmlns.setValue("http://www.springframework.org/schema/beans");

				final Attr xmlnsxsi = document.createAttribute("xmlns:xsi");
				xmlnsxsi.setValue("http://www.w3.org/2001/XMLSchema-instance");

				final Attr xsischemaLocation = document.createAttribute("xsi:schemaLocation");
				xsischemaLocation.setValue("http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd");

				root.setAttributeNode(xmlns);
				root.setAttributeNode(xmlnsxsi);
				root.setAttributeNode(xsischemaLocation);

				document.appendChild(root);

				//for each class in domain
				for (final Class<?> c : AutoPopulate.classList)
					//get declared fields
					if (AutoPopulate.findSuper(c) == null)
						for (int i = 1; i < iter + 1; i++) {
							final Element bean = document.createElement("bean");
							final Attr beanId = document.createAttribute("id");
							beanId.setValue(c.getSimpleName() + i);
							final Attr beanClass = document.createAttribute("class");
							beanClass.setValue(c.getName());

							bean.setAttributeNode(beanId);
							bean.setAttributeNode(beanClass);

							//for each field in class
							final Field[] fields = c.getDeclaredFields();
							for (final Field field : fields) {
								final Class<?> f = field.getType();

								final Element property = document.createElement("property");
								final Attr propertyName = document.createAttribute("name");
								propertyName.setValue(field.getName());
								property.setAttributeNode(propertyName);

								//check if class of field is a collection
								if (Collection.class.isAssignableFrom(f)) {
									final Element list = document.createElement("list");

									final Type collectionType = c.getField(field.getName()).getGenericType();
									final ParameterizedType pt = (ParameterizedType) collectionType;
									final Class<?> pc = (Class<?>) pt.getActualTypeArguments()[0];

									//check if class is in domain class list
									final boolean inDomain = AutoPopulate.classList.contains(pc);
									for (int j = 1; j < iter + 1; j++) {
										final Element listBean = document.createElement("bean");
										//TODO: CHECK INHERITANCE
										if (!inDomain) {
											//if its not:
											//set name for bean
											final Attr listBeanClass = document.createAttribute("class");
											listBeanClass.setValue(pc.getName());
											listBean.setAttributeNode(listBeanClass);
											//add a property for each declared type
											final Field[] beanFields = pc.getDeclaredFields();
											for (int x = 0; x < beanFields.length; x++) {
												final Element listProperty = document.createElement("property");
												final Attr listPropertyName = document.createAttribute("name");
												listPropertyName.setValue(beanFields[x].getName());
												listProperty.setAttributeNode(listPropertyName);

												final Attr listPropertyValue = document.createAttribute("value");
												listPropertyValue.setValue("TODO");
												listProperty.setAttributeNode(listPropertyValue);
												listBean.appendChild(listProperty);
											}
										} else {
											//if class is in domain add ref attribute
											final Attr beanRef = document.createAttribute("ref");
											beanRef.setValue("TODO");
											listBean.setAttributeNode(beanRef);
										}
										list.appendChild(listBean);
									}
									property.appendChild(list);
								} else if (!AutoPopulate.classList.contains(f)) {
									//if not a collection and not in domain class list
									final Attr propertyValue = document.createAttribute("value");
									propertyValue.setValue("TODO");
									property.setAttributeNode(propertyValue);
								} else {
									//if not a collection and in domain class list
									final Attr propertyRef = document.createAttribute("ref");
									propertyRef.setValue("TODO");
									property.setAttributeNode(propertyRef);
								}
								bean.appendChild(property);
							}
							root.appendChild(bean);
						}

				//DOM to xml
				final TransformerFactory transformerFactory = TransformerFactory.newInstance();
				final Transformer transformer = transformerFactory.newTransformer();
				final DOMSource domSource = new DOMSource(document);
				final StreamResult streamResult = new StreamResult(new File(AutoPopulate.xmlFilePath));
				transformer.transform(domSource, streamResult);
				System.out.println("Done creating XML File");

			} catch (final Exception e) {
				e.printStackTrace();
			}
	}

	private static Class<?> findSuper(final Class<?> root) {
		Class<?> res = null;
		AutoPopulate.classList.remove(root);
		for (final Class<?> classC : AutoPopulate.classList)
			if (classC.isAssignableFrom(root)) {
				res = classC;
				break;
			}
		return res;
	}

}
