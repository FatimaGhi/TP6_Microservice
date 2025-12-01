package dcc.etudiant_service.FeignClient;


import dcc.etudiant_service.DTO.Filiere;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(
        name = "Filiere-Service",
        url = "http://localhost:8081/v1/filieres" // URL de  microservice filiere
)
@CircuitBreaker(name = "Filiere-Service",fallbackMethod = "Filiere_fallbackMethod")
//@TimeLimiter(name = "Filiere-Service",fallbackMethod ="Filiere_fallbackMethod")
//@RateLimiter(name = "Filiere-Servicer",fallbackMethod ="Filiere_fallbackMethod_GA")
//@Retry(name = "Filiere-Service",fallbackMethod ="Filiere_fallbackMethod_GA")
//@Bulkhead(name = "Filiere-Service",fallbackMethod ="Filiere_fallbackMethod_GA")

//@Cacheable(value = "filiere-cache", key = "#id")
public interface FiliereClient {


    @GetMapping("/{id}")
    public Filiere getFiliereById(@PathVariable("id") Integer id);

    @GetMapping
    public List<Filiere> getAll();


    default Filiere Filiere_fallbackMethod(Integer id,Exception e){
        return new Filiere(id ,"No_exist","No_exist");
    }
    default List<Filiere> Filiere_fallbackMethod_GA(Integer id,Exception e){
        return List.of(new Filiere());
    }



//    default List<Filiere> Filiere_fallbackMethod_GA(Integer id){
//
//        List<Filiere> filieres = new ArrayList<>();
//        filieres = FiliereClient.getAll();
//        return filieres;
//
//    }





}
