package gate.util.spring.xml;

import gate.util.spring.ExtraGatePlugin;
import gate.util.spring.Init;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

/**
 * Bean definition parser for
 * <code>&lt;gate:extra-plugin&gt;path&lt;/gate:extra-plugin&gt;</code>
 * producing the equivalent of
 * 
 * <pre>
 * &lt;bean class="gate.util.spring.ExtraGatePlugin"&gt;
 *   &lt;property name="location" value="path" /&gt;
 * &lt;/bean&gt;
 * </pre>
 * 
 * Or for a Maven-style plugin <code>&lt;gate:extra-plugin
 * group-id="uk.ac.gate.plugins" artifact-id="annie" version="8.5" /&gt;</code>
 * 
 * While the element can take an <code>id</code> it is not normally
 * necessary to provide one as the {@link Init} bean enumerates all
 * {@link ExtraGatePlugin} beans by type, ignoring their IDs.
 */
public class ExtraGatePluginBeanDefinitionParser
                                                extends
                                                  AbstractSimpleBeanDefinitionParser {

  @Override
  protected void postProcess(BeanDefinitionBuilder builder, Element element) {
    String location = DomUtils.getTextValue(element);
    if(location != null && !"".equals(location)) {
      builder.addPropertyValue("location", location);
    }
  }

  @Override
  protected Class<?> getBeanClass(Element element) {
    return ExtraGatePlugin.class;
  }

  @Override
  protected boolean shouldGenerateIdAsFallback() {
    return true;
  }
}
