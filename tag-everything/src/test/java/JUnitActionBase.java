import com.gaolong.tageverything.model.tag.Tag;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Collections;

public class JUnitActionBase {

    private static HandlerMapping handlerMapping;
    private static HandlerAdapter handlerAdapter;

    /*@BeforeClass
    public static void setUp() {
        if (handlerMapping == null) {
            String[] configs = { "file:src/springConfig/springMVC.xml" };
            XmlWebApplicationContext context = new XmlWebApplicationContext();
            context.setConfigLocations(configs);
            MockServletContext msc = new MockServletContext();
            context.setServletContext(msc);
            context.refresh();
            msc.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);
            handlerMapping = (HandlerMapping) context.getBean(DefaultAnnotationHandlerMapping.class);
            handlerAdapter = (HandlerAdapter) context.getBean(context.getBeanNamesForType(AnnotationMethodHandlerAdapter.class)[0]);
        }
    }

    */

    /**
     * 执行request对象请求的action
     * <p>
     * //     * @param request
     * //     * @param response
     *
     * @return
     * @throws Exception
     *//*
    public ModelAndView excuteAction(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HandlerExecutionChain chain = handlerMapping.getHandler(request);
        final ModelAndView model = handlerAdapter.handle(request, response, chain.getHandler());
        return model;
    }*/
    @Test
    public void handle41Test() {
        RestTemplate restTemplate = new RestTemplate();
//        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
//        form.add("userName", "tom");
//        form.add("password", "123456");
//        form.add("age", "45");
//        restTemplate.postForLocation("http://localhost:8080/test/test", form);
        String s = restTemplate.getForObject("http://localhost:8080/test/test", String.class);
        System.out.println(s);
    }

    @Test
    public void handle51Test() {
        RestTemplate restTemplate = buildRestTemplate();

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("test");

        HttpHeaders entityHeaders = new HttpHeaders();
        entityHeaders.setContentType(MediaType.valueOf("application/xml;UTF-8"));
        entityHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));

        HttpEntity<Tag> requestEntity = new HttpEntity<>(tag, entityHeaders);
        ResponseEntity<Tag> responseEntity = restTemplate.exchange("http://localhost:8080/test/handle51", HttpMethod.POST, requestEntity, Tag.class);

        Tag responseTag = responseEntity.getBody();
        Assert.assertNotNull(responseTag);
        Assert.assertEquals(1, responseTag.getId());
        Assert.assertEquals("test", responseTag.getName());

    }

    private RestTemplate buildRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        XStreamMarshaller xmlMarshaller = new XStreamMarshaller();
        xmlMarshaller.setStreamDriver(new StaxDriver());
        xmlMarshaller.setAnnotatedClasses(new Class[]{Tag.class});

        MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();
        xmlConverter.setMarshaller(xmlMarshaller);
        xmlConverter.setUnmarshaller(xmlMarshaller);
        restTemplate.getMessageConverters().add(xmlConverter);

        /*MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        restTemplate.getMessageConverters().add(jsonConverter);*/
        return restTemplate;
    }
}

