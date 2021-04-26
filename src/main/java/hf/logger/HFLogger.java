package hf.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class HFLogger {
	@Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void PostMapping(){ }
	
	@Around("PostMapping()")
	public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("====AspectJ TEST  : Around Logging Start====");
		log.info("Around Logging Start====");
		try {
			Object result = joinPoint.proceed();
			log.info("====AspectJ RESULT : " + result);
			log.info("====AspectJ TEST  : Around Logging END====");
			log.info("RESULT Given");
			return result;
		} catch (Exception e) {
			log.error("====AspectJ Around Exception====");
			log.error(e.toString());
			log.error("Around Exception====" + e);
			return null;
		}
	}
}
