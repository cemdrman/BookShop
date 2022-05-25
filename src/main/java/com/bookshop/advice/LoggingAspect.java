package com.bookshop.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

	@Around("execution(* com.bookshop..*(..)))")
	public Object logMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		Object[] args = proceedingJoinPoint.getArgs();
	
		Object result = proceedingJoinPoint.proceed();

		// Log method execution time
		log.info("[" + methodSignature.getName()+"] :: "
					 + args[0]
					 + methodSignature.getDeclaringType().getSimpleName() );// Class Name
	

		return result;
	}

}
