package com.pilnyck.shop.pagegenerator;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageGenerator {
    private static final String HTML_PATH = "resources/shop";

    private static PageGenerator pageGenerator;
    private final Configuration configuration;

    public static PageGenerator instance(){
        if (pageGenerator == null){
            pageGenerator = new PageGenerator();
        }
        return pageGenerator;
    }

    public String getPage(String filename, Map<String, Object> data){
        Writer writer = new StringWriter();
        try{
            Template template = configuration.getTemplate(HTML_PATH + File.separator + filename);
            template.process(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    private PageGenerator() {
        configuration = new Configuration();
    }
}
