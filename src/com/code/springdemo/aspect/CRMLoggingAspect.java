package com.code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect 
{
	//Setup Logger
	private Logger myLogger = Logger.getLogger(getClass().getName());

	//Setup Pointcut declarations
	@Pointcut("execution(* com.code.springdemo.controller.*.*(..))")
	private void forControllerPackage(){}
	

	@Pointcut("execution(* com.code.springdemo.service.*.*(..))")
	private void forServicePackage(){}
	

	@Pointcut("execution(* com.code.springdemo.dao.*.*(..))")
	private void forDaoPackage(){}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow(){}
	
	
	//add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint)
	{
		//Display method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("=>>>>>> in @Before: calling method: "+theMethod);
		//Display the arguments to the method
		
		//get the arguemnts
		Object[] args = theJoinPoint.getArgs();
		//loop through and display argus
		for(Object tempArg : args)
		{
			myLogger.info("=>>>>>>>> Arguements: "+tempArg);
		}
	}
	
	@AfterReturning
	(
	  pointcut = "forAppFlow()",
	  returning="theResult"
	)
	public void afterReturning(JoinPoint theJoinPoint, Object theResult)
	{
		//Display method we are returning form
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("=>>>>>> in @After Returning: calling method: "+theMethod);
		
		//Display data returned
		myLogger.info("=>>>>>> Result: "+theResult);
	}
	
	
	
	
	
}
