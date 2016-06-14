package com.clouter.clouterutil.velocity;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

public class VelocityConverter {
	private VelocityEngine engine;
	
	public VelocityConverter(){
		engine = new VelocityEngine();
//		engine.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, "");
		engine.setProperty(Velocity.RESOURCE_LOADER, "class");
		engine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		engine.setProperty("runtime.log", "");
		engine.init();
	}
	
	public void convert(ConverterParam param){
		Template template = engine.getTemplate(param.getVmFile(), param.getEncodeStr());
		VelocityContext context = new VelocityContext();
		context.put("inputData", param.getInputData());
		
		StringWriter writer = new StringWriter();
		template.merge(context, writer);
		
		param.getFileContent().setContent(writer.toString());
	}
}
