package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component

//TimeTraceAop를 등록하는 두가지
//Component 스캔에 등록하기, SpringConfig에 정의하여 사용하기
public class TimeTraceAop {

    //hello hellospring 아래에 있는 모든 로직 체크, 내가 적용할 범위(패키지나 서비스 등등)를 적는다.
    @Around(("execution(* hello.hellospring..*(..))"))
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try{
            return joinPoint.proceed();
        }finally{
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: "+ joinPoint.toString() +" " + timeMs + "ms");
        }
    }
}
