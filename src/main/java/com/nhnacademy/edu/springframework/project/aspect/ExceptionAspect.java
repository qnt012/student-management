package com.nhnacademy.edu.springframework.project.aspect;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ExceptionAspect {

    @Around("execution(* com.nhnacademy.edu.springframework.project.repository.CsvScores.*(..))")
    public Object csvScoresLoadCheck(ProceedingJoinPoint pjp) throws Throwable{
        if (pjp.toString().contains("load")) return pjp.proceed();
        CsvScores scores = (CsvScores) pjp.getTarget();
        if (!scores.isLoaded()) throw new IllegalStateException();
        return pjp.proceed();
    }

    @Around("execution(* com.nhnacademy.edu.springframework.project.repository.CsvStudents.*(..))")
    public Object csvStudentsLoadCheck(ProceedingJoinPoint pjp) throws Throwable{
        if (pjp.toString().contains("load")) return pjp.proceed();
        CsvStudents students = (CsvStudents) pjp.getTarget();
        if (!students.isLoaded()) throw new IllegalStateException();
        return pjp.proceed();
    }
}
