package hf.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class HFLogger {
	
	private static final Logger log =  LoggerFactory.getLogger(HFLogger.class);

	@Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void PostMapping(){ }
	
	@Around("PostMapping()")
	public Object Around(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("Around Logging Start====");
		try {
			Object result = joinPoint.proceed();
			log.info("RESULT Given");
			return result;
		} catch (Exception e) {
			log.error("Around Exception====" + e);
			return null;
		}
	}
}
