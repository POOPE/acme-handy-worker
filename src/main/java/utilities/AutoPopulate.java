
package utilities;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
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

				//build dependecy tree
				final ArrayList<Class<?>> dependencyTree = new ArrayList<>();
				final ArrayList<Class<?>> cList = new ArrayList<>();
				cList.addAll(AutoPopulate.classList);
				for (final Class<?> c : cList)
					//get attributes
					if (!dependencyTree.contains(c) && AutoPopulate.findSuper(c) == null)
						for (int i = 1; i < iter + 1; i++) {
							final Element bean = document.createElement("bean");
							final Attr beanId = document.createAttribute("id");
							beanId.setValue(c.getSimpleName() + i);
							final Attr beanClass = document.createAttribute("class");
							beanClass.setValue(c.getName());

							bean.setAttributeNode(beanId);
							bean.setAttributeNode(beanClass);

							final Field[] fields = c.getDeclaredFields();
							for (final Field field : fields) {
								final Class<?> f = field.getClass();

								if (AutoPopulate.classList.contains(f) && !dependencyTree.contains(c))
									dependencyTree.add(f);

							}
							root.appendChild(bean);
							dependencyTree.add(c);
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
