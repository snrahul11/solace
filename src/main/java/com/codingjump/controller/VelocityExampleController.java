package com.codingjump.controller;

import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VelocityExampleController {
    @GetMapping("velocity-hello-world")
    public String helloWorldExample()
    {
        VelocityEngine engine = new VelocityEngine();
        Properties properties = new Properties();
        engine.setProperty(RuntimeConstants.RESOURCE_LOADERS, "file,class,classpath");
        engine.setProperty("resource.loader.class.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        engine.setProperty("resource.loader.file.path", "classpath");
        engine.init(properties);

        VelocityContext context = new VelocityContext();
        context.put("names", List.of("CodingJump", "Rahul", "John"));

        StringWriter stringWriter = new StringWriter();
        engine.mergeTemplate("hello-world.vm", "UTF8", context, stringWriter);

        return stringWriter.toString();
    }
}
