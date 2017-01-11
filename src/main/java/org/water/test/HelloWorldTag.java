package org.water.test;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by 帝 on 2017/1/10.
 */
public class HelloWorldTag extends SimpleTagSupport {
    private String collection;
    private String item;

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }


    @Override
    public void doTag() throws JspException, IOException{
        //getJspContext().getOut().write("Hello world " + new java.util.Date());
        Collection itemlist = (Collection)getJspContext().getAttribute(collection);

        for (Object s:itemlist){
            getJspContext().setAttribute(item, s);
            getJspBody().invoke(null);
        }
    }
}
