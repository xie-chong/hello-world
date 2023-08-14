package com.example.demo.copyutils;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.DefaultBeanIntrospector;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * 功能描述:
 * java里，数据类型有基本数据类型，引用数据类型。
 * <p>
 * 浅拷贝和深拷贝就是在这个基础上做区分的。
 * <p>
 * 浅拷贝：对基本数据类型进行值传递，对引用数据类型进行引用传递般的拷贝，此为浅拷贝
 * <p>
 * 深拷贝：对基本数据类型进行值传递，对引用数据类型，创建一个新的对象，并复制其内容，此为深拷贝。
 * <p>
 * org.apache.commons.beanutils.BeanUtils.copyProperties(personDest, personSource);
 * org.springframework.beans.BeanUtils.copyProperties(personSource, personDest);
 * 上面2个方法都属于浅拷贝。
 * <p>
 * Apache下的BeanUtils对于对象拷贝加了很多的检验，包括类型的转换，甚至还会检验对象所属的类的可访问性,
 * 可谓相当复杂，这也造就了它的差劲的性能。在阿里巴巴Java开发规约插件上也明确指出：避免用Apache Beanutils进行属性的copy。
 * 可以使用 Spring的BeanUtils
 *
 * <p>
 * 深拷贝：
 * 使用 ObjectMapper（来自Jackson库）进行序列化和反序列化：ObjectMapper 可以将对象序列化为 JSON 字符串，
 * 然后再将 JSON 字符串反序列化为新的对象。这样可以实现深拷贝，因为在反序列化过程中会创建一个全新的对象。
 * （对象需要有默认构造函数、操作过程需要忽略未识别的字段）
 *
 * @param
 * @author chong.xie
 * @return
 * @date 2023/8/10 11:28
 */
@Slf4j
class ShallowAndDeepCloneTest {

    /**
     * org.apache.commons.beanutils.BeanUtils.copyProperties
     * 获取描述指定bean类的{@code BeanIntrospectionData}对象。该对象在内部缓存中查找。
     * 如果有必要，现在对受影响的bean类执行内省，并创建结果对象(PropertyUtilsBean#fetchIntrospectionData)。
     * 然后通过getIntrospectionData(beanClass).getDescriptors();获取对象属性。
     * <p>
     * IntrospectionData对象的获取流程：
     *
     * @author chong.xie
     * 2023/8/10 11:13
     * @see DefaultBeanIntrospector#introspect --> Introspector#getBeanInfo
     * --> Introspector#getTargetPropertyInfo--> Introspector#processPropertyDescriptors
     */
    @Test
    public void excuteApacheOrSpringBeanUtilsTest() throws Exception {
        // 浅拷贝
//        beanUtilsTest(APACHE_BEAN_UTILS);
//        beanUtilsTest(SPRING_BEAN_UTILS);
        // 深拷贝
        beanUtilsTest("000");
    }


    public void beanUtilsTest(String strategy) throws Exception {

        Student student = new Student();
        student.setPassword("原始密码");
        PersonSource personSource = new PersonSource(1, "pjmike", "12345", 21);
        personSource.setStudent(student);

        personSource.setOtherMap(new HashMap<>() {{
            put("key", "value_test");
        }});
        log.info("personSource: " + personSource);
        PersonDest personDest = new PersonDest();

        String SPRING_BEAN_UTILS = "springBeanUtils";
        String APACHE_BEAN_UTILS = "apacheBeanUtils";
        if (APACHE_BEAN_UTILS.equals(strategy)) {
            org.apache.commons.beanutils.BeanUtils.copyProperties(personDest, personSource);
        } else if (SPRING_BEAN_UTILS.equals(strategy)) {
            org.springframework.beans.BeanUtils.copyProperties(personSource, personDest);
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            String json = objectMapper.writeValueAsString(personSource);
            personDest = objectMapper.readValue(json, PersonDest.class);
        }

         log.info("personDest: " + personDest);

         log.info("----------------------modify reference object after------------------------");
        System.out.print("map is same : ");
         log.info("{}", personSource.getOtherMap() == personDest.getOtherMap());

        System.out.print("student is same : ");
         log.info("{}", personSource.getStudent() == personDest.getStudent());

        personDest.getOtherMap().put("key", "value_test_modify");

        personDest.getStudent().setPassword("新密码");

         log.info("personSource modify reference object after: " + personSource);
         log.info("personDest modify reference object after: " + personDest);
    }
}


