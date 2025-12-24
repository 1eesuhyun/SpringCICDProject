package com.sist.web.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect // 공통모듈 => 지정된 모든 클래스에 적용
/*
 *  공통사용 : 메소드 / 클래스 => Callback
 *  핵심사용 : 개발자 코딩
 */
public class SeoulAndBusanAOP {
	@Around("execution(* com.sist.web.restcontroller.*Controller.*(..))")
	public Object log(ProceedingJoinPoint jp) throws Throwable
	{
		System.out.println("호출된 메소드:"+jp.getSignature().getName());
		long start=System.currentTimeMillis();
		Object obj=jp.proceed();
		long end=System.currentTimeMillis();
		System.out.println("수행시간:"+(end-start)+"MS");
		return obj;
	}
}
