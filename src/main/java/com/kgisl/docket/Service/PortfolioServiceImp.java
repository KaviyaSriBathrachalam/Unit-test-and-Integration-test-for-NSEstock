package com.kgisl.docket.Service;

import java.util.List;

import com.kgisl.docket.Repository.PortfolioRepository;
import com.kgisl.docket.model.Portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("portfolioService")
public class PortfolioServiceImp implements PortfolioService {

    @Autowired
    private PortfolioRepository portfoilioRepository;

    @Override
    public List<Portfolio> getAll() {
        System.out.println("********GETALL*******");
        return portfoilioRepository.findAll();
    }

    @Override
    public void delete(Long pId) {
        System.out.println("******delete in service");
        portfoilioRepository.deleteById(pId);
    }

    @Override
    public Portfolio update(Portfolio portfolio, Long pId) {
     System.out.println("Update method in service");
        portfolio.setId(pId);
      return  portfoilioRepository.saveAndFlush(portfolio);
       
    }

   

    @Override
    public List<Portfolio> save(List<Portfolio> p) {
        System.out.println("Save method in Service"+p);
        for (Portfolio obj : p) {
            portfoilioRepository.saveAndFlush(obj);
        }
        return portfoilioRepository.findAll();
    }
}