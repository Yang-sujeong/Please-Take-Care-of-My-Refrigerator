package spms.annotation;


import java.lang.annotation.*;

/* 클래스 파일에 이 정보가 기록됨
 * 실행시에도 유지됨
 * (즉, 실행시에 클래스에 기록된 애노테이션값을 참조할 수 있다)
 * */;
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
	String value() default "";
}
