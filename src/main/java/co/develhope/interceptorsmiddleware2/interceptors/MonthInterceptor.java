package co.develhope.interceptorsmiddleware2.interceptors;

import co.develhope.interceptorsmiddleware2.entities.Month;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Component
public class MonthInterceptor implements HandlerInterceptor{
    private static final List<Month> months = Arrays.asList(
            new Month(1,"Gennaio","January","Januar"),
            new Month(2,"Febbraio","February","Februar"),
            new Month(3,"Marzo","March","März"),
            new Month(4,"Aprile","April","April"),
            new Month(5,"Maggio","May","Mai"),
            new Month(6,"Giugno","June","Juni"));

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
        String header = request.getHeader("monthNumber");
        if(header == null || header.isEmpty()){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return false;
        }
        int monthNumber = Integer.parseInt(header);
        Month month = months.stream().filter(m -> m.getMonthNumber() == monthNumber).findFirst().orElse(
                new Month(0,"nope","nope","nope"));
        request.setAttribute("month",month);
        response.setStatus(HttpStatus.OK.value());
        return true;
    }

    public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception{
    }

    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,
                                @Nullable Exception ex) throws Exception{
    }

}
