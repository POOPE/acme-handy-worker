
package utilities;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

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

	public static Document			doc;


	private static Element createListElement() {
		final Element res = AutoPopulate.doc.createElement("list");
		return res;
	}

	public static void main(final String[] args) {
		//ask iter
		final Scanner sc = new Scanner(System.in);
		System.out.println("Autopopulate v2.0");
		System.out.println("Create __ of each class");
		final Integer iter = Integer.valueOf(sc.nextLine());
		System.out.println("Creating " + iter + " of each class");
		//get all class names in package domain
		AutoPopulate.classList = ClassFinder.find("domain");

		//for all classes domainentity will have to be ignored
		final Class<?> cdomainEntity = ClassFinder.findByName("domain.DomainEntity");
		AutoPopulate.classList.remove(cdomainEntity);

		if (AutoPopulate.classList != null) {
			try {
				//build xml document
				final DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
				final DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
				final Document document = documentBuilder.newDocument();
				AutoPopulate.doc = document;
				// root element
				final Element root = AutoPopulate.doc.createElement("beans");
				final Attr xmlns = AutoPopulate.doc.createAttribute("xmlns");
				xmlns.setValue("http://www.springframework.org/schema/beans");

				final Attr xmlnsxsi = AutoPopulate.doc.createAttribute("xmlns:xsi");
				xmlnsxsi.setValue("http://www.w3.org/2001/XMLSchema-instance");

				final Attr xsischemaLocation = AutoPopulate.doc.createAttribute("xsi:schemaLocation");
				xsischemaLocation.setValue("http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd");

				root.setAttributeNode(xmlns);
				root.setAttributeNode(xmlnsxsi);
				root.setAttributeNode(xsischemaLocation);

				AutoPopulate.doc.appendChild(root);
				final List<Class<?>> cList = ClassFinder.find("domain");
				System.out.println();
				//for each class in domain
				for (final Class<?> c : cList) {
					System.out.println();
					System.out.println("Class: " + c.getName());
					System.out.println("Skip class? Y/N");
					Boolean skipClass = false;
					final String yn = sc.nextLine();
					if (yn.equalsIgnoreCase("y")) {
						skipClass = true;
					}
					if (skipClass) {
						System.out.println("Skipping");
					}
					//get declared fields
					if (!skipClass) {
						for (int i = 1; i < iter + 1; i++) {
							// out: class + id
							System.out.println();
							System.out.println("Class: " + c.getName() + " Id: " + c.getSimpleName() + i);

							System.out.println("Skip class? Y/N");
							final String ynn = sc.nextLine();
							if (ynn.equalsIgnoreCase("y")) {
								System.out.println("Skipping");
								break;
							}

							final Element bean = AutoPopulate.doc.createElement("bean");
							final Attr beanId = AutoPopulate.doc.createAttribute("id");
							beanId.setValue(c.getSimpleName() + i);
							final Attr beanClass = AutoPopulate.doc.createAttribute("class");
							beanClass.setValue(c.getName());

							bean.setAttributeNode(beanId);
							bean.setAttributeNode(beanClass);

							//get declared fields
							final List<Field> fields = new ArrayList<Field>(Arrays.asList(c.getDeclaredFields()));
							final Class<?> superC = AutoPopulate.findSuper(c);
							if (superC != null) {
								fields.addAll(Arrays.asList(superC.getDeclaredFields()));
							}

							//for each field in class
							for (final Field field : fields) {
								// out: property name
								System.out.println("Property: " + field.getName());
								final Class<?> f = field.getType();

								final Element property = AutoPopulate.doc.createElement("property");
								final Attr propertyName = AutoPopulate.doc.createAttribute("name");
								propertyName.setValue(field.getName());
								property.setAttributeNode(propertyName);

								//check if class of field is a collection
								if (Collection.class.isAssignableFrom(f)) {
									//ask for list iterations
									System.out.print("List iterations: ");
									final Integer listIter = Integer.valueOf(sc.nextLine());

									final Element list = AutoPopulate.createListElement();

									final Type collectionType = c.getField(field.getName()).getGenericType();
									final ParameterizedType pt = (ParameterizedType) collectionType;
									final Class<?> pc = (Class<?>) pt.getActualTypeArguments()[0];

									//check if class is in domain class list
									final boolean inDomain = AutoPopulate.classList.contains(pc);
									for (int j = 1; j < listIter + 1; j++) {
										//TODO: CHECK INHERITANCE
										if (!inDomain) {

											final Element beanListValue = AutoPopulate.doc.createElement("value");
											System.out.println("Value: ");
											final String val = sc.nextLine();
											beanListValue.appendChild(AutoPopulate.doc.createTextNode(val));
											list.appendChild(beanListValue);
										} else {
											//if class is in domain add ref attribute
											System.out.println("Ref bean " + pc.getSimpleName() + ": ");
											final String val = sc.nextLine();
											final Element beanListRef = AutoPopulate.doc.createElement("ref");
											final Attr beanRef = AutoPopulate.doc.createAttribute("bean");
											beanRef.setValue(val);
											beanListRef.setAttributeNode(beanRef);
											list.appendChild(beanListRef);
										}
									}
									property.appendChild(list);
								} else if (!AutoPopulate.classList.contains(f)) {
									//if not a collection and not in domain class list
									System.out.print(" Value: ");
									final String val = sc.nextLine();
									final Attr propertyValue = AutoPopulate.doc.createAttribute("value");
									propertyValue.setValue(val);
									property.setAttributeNode(propertyValue);
								} else {
									//if not a collection and in domain class list
									System.out.print(" Ref: ");
									final String val = sc.nextLine();
									final Attr propertyRef = AutoPopulate.doc.createAttribute("ref");
									propertyRef.setValue(val);
									property.setAttributeNode(propertyRef);
								}
								bean.appendChild(property);
							}
							root.appendChild(bean);
						}
					}
				}
				//DOM to xml
				final TransformerFactory transformerFactory = TransformerFactory.newInstance();
				final Transformer transformer = transformerFactory.newTransformer();
				final DOMSource domSource = new DOMSource(AutoPopulate.doc);
				final StreamResult streamResult = new StreamResult(new File(AutoPopulate.xmlFilePath));
				transformer.transform(domSource, streamResult);
				System.out.println("Done creating XML File");
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}

	}
	private static Class<?> findSuper(final Class<?> root) {
		Class<?> res = null;
		AutoPopulate.classList.remove(root);
		for (final Class<?> classC : AutoPopulate.classList) {
			if (classC.isAssignableFrom(root)) {
				res = classC;
				break;
			}
		}
		return res;
	}

}
