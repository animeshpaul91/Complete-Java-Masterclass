package org.animesh.javabrains.rest.paramconverter;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import org.animesh.javabrains.rest.model.MyDate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class MyDateConverterProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if (rawType.getName().equals(MyDate.class.getName())) {
//            return new ParamConverter<T>() {
//                @Override
//                public T fromString(String value) {
//                    Calendar requestedDate = Calendar.getInstance();
//                    if (value.equalsIgnoreCase("tomorrow"))
//                        requestedDate.add(Calendar.DATE, 1);
//                    if (value.equalsIgnoreCase("yesterday"))
//                        requestedDate.add(Calendar.DATE, -1);
//
//                    MyDate myDate = new MyDate(requestedDate.get(Calendar.DATE),
//                            requestedDate.get(Calendar.MONTH),
//                            requestedDate.get(Calendar.YEAR));
//
//                    return rawType.cast(myDate);
//                }
//
//                @Override
//                public String toString(T myDate) {
//                    return myDate != null ? myDate.toString() : null;
//                }
//            };

            return new MyDateConverter<T>(rawType);
        }
        return null;
    }
}
