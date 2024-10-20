import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;


import data.Attribute;
import data.DataFactory;
import data.Entity;
import data.StringType;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataFactory factory = DataFactory.eINSTANCE;
		
		StringType stringType = factory.createStringType();
		
		Entity entity = factory.createEntity();
		entity.setName("Student");
		
		Attribute attribute = factory.createAttribute();
		attribute.setName("Name");
		attribute.setType(stringType);
		
		entity.getAttribute().add(attribute);
		
		System.out.println(entity.toString());
		
		//save stuff
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		
		m.put("data", new XMIResourceFactoryImpl());
		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();
		
		//create a resource
		Resource resource = resSet.createResource(URI.createURI("My.data"));
		resource.getContents().add(stringType);
		resource.getContents().add(entity);
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
	}

}
