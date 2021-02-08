package com.mi.cims.aop.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**   
 * @ClassName: Operation   
 * @Description: 操作权限注解 
 * @author: 刘伟
 * @date: 2017年9月7日 下午4:30:57   
 */
// 注解用于方法
@Target(ElementType.METHOD)
// 运行时可被读取和使用
@Retention(RetentionPolicy.RUNTIME)
// 可以被 javadoc工具记录
@Documented
// 最高优先级
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface Operation {

    // 权限名称
    String[] value() default {};

}

