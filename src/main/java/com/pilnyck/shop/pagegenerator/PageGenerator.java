package com.pilnyck.shop.pagegenerator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Map;

public class PageGenerator {
    private static final String HTML_PATH = "templates/shop/pages";

    private static PageGenerator pageGenerator;
    private final Configuration configuration;

    private PageGenerator() {
        configuration = new Configuration();
    }

    public static PageGenerator instance() {
        if (pageGenerator == null) {
            pageGenerator = new PageGenerator();
        }
        return pageGenerator;
    }

    public String getPage(String filename) {
        return getPage(filename, Collections.EMPTY_MAP);
    }

    public String getPage(String filename, Map<String, Object> data) {
        Writer writer = new StringWriter();
        try {
            Template template = configuration.getTemplate(HTML_PATH + File.separator + filename);
            template.process(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }
}
