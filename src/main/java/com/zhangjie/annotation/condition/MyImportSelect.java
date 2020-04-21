package com.zhangjie.annotation.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
// 自定义逻辑，返回需要注册进容器的组件

public class MyImportSelect implements ImportSelector {
    @Override

    // 返回值就是要注册到容器中的组件的全类名数组
    // AnnotationMetadata 当前标注了@Import注解的类的所有注解信息
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //importingClassMetadata.get
        return new String[]{"com.zhangjie.annotation.bean.Blue","com.zhangjie.annotation.bean.Yellow"};
    }
}
